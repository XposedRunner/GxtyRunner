package com.v5kf.java.websocket;

import android.support.v4.view.PointerIconCompat;
import com.v5kf.java.websocket.WebSocket.READYSTATE;
import com.v5kf.java.websocket.WebSocket.Role;
import com.v5kf.java.websocket.drafts.Draft;
import com.v5kf.java.websocket.drafts.Draft.CloseHandshakeType;
import com.v5kf.java.websocket.drafts.Draft.HandshakeState;
import com.v5kf.java.websocket.drafts.Draft_10;
import com.v5kf.java.websocket.drafts.Draft_17;
import com.v5kf.java.websocket.drafts.Draft_75;
import com.v5kf.java.websocket.drafts.Draft_76;
import com.v5kf.java.websocket.exceptions.IncompleteHandshakeException;
import com.v5kf.java.websocket.exceptions.InvalidDataException;
import com.v5kf.java.websocket.exceptions.InvalidHandshakeException;
import com.v5kf.java.websocket.exceptions.WebsocketNotConnectedException;
import com.v5kf.java.websocket.framing.CloseFrame;
import com.v5kf.java.websocket.framing.CloseFrameBuilder;
import com.v5kf.java.websocket.framing.Framedata;
import com.v5kf.java.websocket.framing.Framedata.Opcode;
import com.v5kf.java.websocket.handshake.ClientHandshake;
import com.v5kf.java.websocket.handshake.ClientHandshakeBuilder;
import com.v5kf.java.websocket.handshake.Handshakedata;
import com.v5kf.java.websocket.handshake.ServerHandshake;
import com.v5kf.java.websocket.server.WebSocketServer.WebSocketWorker;
import com.v5kf.java.websocket.util.Charsetfunctions;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WebSocketImpl implements WebSocket {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketImpl.class.desiredAssertionStatus());
    public static boolean DEBUG = false;
    public static int RCVBUF = 16384;
    public static final List<Draft> defaultdraftlist = new ArrayList(4);
    public ByteChannel channel;
    private Integer closecode;
    private Boolean closedremotely;
    private String closemessage;
    private Opcode current_continuous_frame_opcode;
    private Draft draft;
    private volatile boolean flushandclosestate;
    private ClientHandshake handshakerequest;
    public final BlockingQueue<ByteBuffer> inQueue;
    public SelectionKey key;
    private List<Draft> knownDrafts;
    public final BlockingQueue<ByteBuffer> outQueue;
    private READYSTATE readystate;
    private String resourceDescriptor;
    private Role role;
    private short statusCode;
    private ByteBuffer tmpHandshakeBytes;
    public volatile WebSocketWorker workerThread;
    private final WebSocketListener wsl;

    static {
        defaultdraftlist.add(new Draft_17());
        defaultdraftlist.add(new Draft_10());
        defaultdraftlist.add(new Draft_76());
        defaultdraftlist.add(new Draft_75());
    }

    public WebSocketImpl(WebSocketListener webSocketListener, List<Draft> list) {
        this(webSocketListener, null);
        this.role = Role.SERVER;
        if (list == null || list.isEmpty()) {
            this.knownDrafts = defaultdraftlist;
        } else {
            this.knownDrafts = list;
        }
    }

    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft) {
        this.flushandclosestate = false;
        this.readystate = READYSTATE.NOT_YET_CONNECTED;
        this.draft = null;
        this.current_continuous_frame_opcode = null;
        this.tmpHandshakeBytes = ByteBuffer.allocate(0);
        this.handshakerequest = null;
        this.closemessage = null;
        this.closecode = null;
        this.closedremotely = null;
        this.resourceDescriptor = null;
        this.statusCode = (short) 0;
        if (webSocketListener == null || (draft == null && this.role == Role.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.outQueue = new LinkedBlockingQueue();
        this.inQueue = new LinkedBlockingQueue();
        this.wsl = webSocketListener;
        this.role = Role.CLIENT;
        if (draft != null) {
            this.draft = draft.copyInstance();
        }
    }

    @Deprecated
    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft, Socket socket) {
        this(webSocketListener, draft);
    }

    @Deprecated
    public WebSocketImpl(WebSocketListener webSocketListener, List<Draft> list, Socket socket) {
        this(webSocketListener, (List) list);
    }

    public void decode(ByteBuffer byteBuffer) {
        if ($assertionsDisabled || byteBuffer.hasRemaining()) {
            if (DEBUG) {
                System.out.println("process(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining())) + "}");
            }
            if (this.readystate != READYSTATE.NOT_YET_CONNECTED) {
                decodeFrames(byteBuffer);
            } else if (decodeHandshake(byteBuffer)) {
                if (!$assertionsDisabled && this.tmpHandshakeBytes.hasRemaining() == byteBuffer.hasRemaining() && byteBuffer.hasRemaining()) {
                    throw new AssertionError();
                } else if (byteBuffer.hasRemaining()) {
                    decodeFrames(byteBuffer);
                } else if (this.tmpHandshakeBytes.hasRemaining()) {
                    decodeFrames(this.tmpHandshakeBytes);
                }
            }
            if (!$assertionsDisabled && !isClosing() && !isFlushAndClose() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
            return;
        }
        throw new AssertionError();
    }

    private boolean decodeHandshake(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        if (this.tmpHandshakeBytes.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.tmpHandshakeBytes.remaining() < byteBuffer.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(this.tmpHandshakeBytes.capacity() + byteBuffer.remaining());
                this.tmpHandshakeBytes.flip();
                allocate.put(this.tmpHandshakeBytes);
                this.tmpHandshakeBytes = allocate;
            }
            this.tmpHandshakeBytes.put(byteBuffer);
            this.tmpHandshakeBytes.flip();
            byteBuffer2 = this.tmpHandshakeBytes;
        }
        byteBuffer2.mark();
        try {
            if (this.draft == null && isFlashEdgeCase(byteBuffer2) == HandshakeState.MATCHED) {
                try {
                    write(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(this.wsl.getFlashPolicy(this))));
                    close(-3, "");
                } catch (InvalidDataException e) {
                    close(PointerIconCompat.TYPE_CELL, "remote peer closed connection before flashpolicy could be transmitted", true);
                }
                return false;
            }
            try {
                Handshakedata translateHandshake;
                if (this.role != Role.SERVER) {
                    if (this.role == Role.CLIENT) {
                        this.draft.setParseMode(this.role);
                        translateHandshake = this.draft.translateHandshake(byteBuffer2);
                        if (translateHandshake instanceof ServerHandshake) {
                            ServerHandshake serverHandshake = (ServerHandshake) translateHandshake;
                            this.statusCode = serverHandshake.getHttpStatus();
                            if (this.draft.acceptHandshakeAsClient(this.handshakerequest, serverHandshake) == HandshakeState.MATCHED) {
                                try {
                                    this.wsl.onWebsocketHandshakeReceivedAsClient(this, this.handshakerequest, serverHandshake);
                                    open(serverHandshake);
                                    return true;
                                } catch (InvalidDataException e2) {
                                    flushAndClose(e2.getCloseCode(), e2.getMessage(), false);
                                    return false;
                                } catch (Exception e3) {
                                    this.wsl.onWebsocketError(this, e3);
                                    flushAndClose(-1, e3.getMessage(), false);
                                    return false;
                                }
                            }
                            if (this.statusCode != (short) 101) {
                                this.wsl.onWebsocketError(this, new Exception("handshake failed"));
                            }
                            close(1002, "draft " + this.draft + " refuses handshake");
                        } else {
                            flushAndClose(1002, "wrong http function", false);
                            return false;
                        }
                    }
                    return false;
                } else if (this.draft == null) {
                    for (Draft copyInstance : this.knownDrafts) {
                        Draft copyInstance2 = copyInstance.copyInstance();
                        try {
                            copyInstance2.setParseMode(this.role);
                            byteBuffer2.reset();
                            translateHandshake = copyInstance2.translateHandshake(byteBuffer2);
                            if (translateHandshake instanceof ClientHandshake) {
                                r0 = (ClientHandshake) translateHandshake;
                                if (copyInstance2.acceptHandshakeAsServer(r0) == HandshakeState.MATCHED) {
                                    this.resourceDescriptor = r0.getResourceDescriptor();
                                    try {
                                        write(copyInstance2.createHandshake(copyInstance2.postProcessHandshakeResponseAsServer(r0, this.wsl.onWebsocketHandshakeReceivedAsServer(this, copyInstance2, r0)), this.role));
                                        this.draft = copyInstance2;
                                        open(r0);
                                        return true;
                                    } catch (InvalidDataException e22) {
                                        flushAndClose(e22.getCloseCode(), e22.getMessage(), false);
                                        return false;
                                    } catch (Exception e32) {
                                        this.wsl.onWebsocketError(this, e32);
                                        flushAndClose(-1, e32.getMessage(), false);
                                        return false;
                                    }
                                }
                                continue;
                            } else {
                                flushAndClose(1002, "wrong http function", false);
                                return false;
                            }
                        } catch (InvalidHandshakeException e4) {
                        }
                    }
                    if (this.draft == null) {
                        close(1002, "no draft matches");
                    }
                    return false;
                } else {
                    translateHandshake = this.draft.translateHandshake(byteBuffer2);
                    if (translateHandshake instanceof ClientHandshake) {
                        r0 = (ClientHandshake) translateHandshake;
                        if (this.draft.acceptHandshakeAsServer(r0) == HandshakeState.MATCHED) {
                            open(r0);
                            return true;
                        }
                        close(1002, "the handshake did finaly not match");
                        return false;
                    }
                    flushAndClose(1002, "wrong http function", false);
                    return false;
                }
            } catch (InvalidDataException e222) {
                close(e222);
            }
        } catch (IncompleteHandshakeException e5) {
            IncompleteHandshakeException incompleteHandshakeException = e5;
            if (this.tmpHandshakeBytes.capacity() == 0) {
                byteBuffer2.reset();
                int preferedSize = incompleteHandshakeException.getPreferedSize();
                if (preferedSize == 0) {
                    preferedSize = byteBuffer2.capacity() + 16;
                } else if (!$assertionsDisabled && incompleteHandshakeException.getPreferedSize() < byteBuffer2.remaining()) {
                    throw new AssertionError();
                }
                this.tmpHandshakeBytes = ByteBuffer.allocate(preferedSize);
                this.tmpHandshakeBytes.put(byteBuffer);
            } else {
                this.tmpHandshakeBytes.position(this.tmpHandshakeBytes.limit());
                this.tmpHandshakeBytes.limit(this.tmpHandshakeBytes.capacity());
            }
        }
    }

    private void decodeFrames(ByteBuffer byteBuffer) {
        try {
            for (Framedata framedata : this.draft.translateFrame(byteBuffer)) {
                if (DEBUG) {
                    System.out.println("matched frame: " + framedata);
                }
                Opcode opcode = framedata.getOpcode();
                boolean isFin = framedata.isFin();
                if (opcode == Opcode.CLOSING) {
                    int closeCode;
                    String message;
                    String str = "";
                    if (framedata instanceof CloseFrame) {
                        CloseFrame closeFrame = (CloseFrame) framedata;
                        closeCode = closeFrame.getCloseCode();
                        message = closeFrame.getMessage();
                    } else {
                        message = str;
                        closeCode = 1005;
                    }
                    if (this.readystate == READYSTATE.CLOSING) {
                        closeConnection(closeCode, message, true);
                    } else if (this.draft.getCloseHandshakeType() == CloseHandshakeType.TWOWAY) {
                        close(closeCode, message, true);
                    } else {
                        flushAndClose(closeCode, message, false);
                    }
                } else if (opcode == Opcode.PING) {
                    this.wsl.onWebsocketPing(this, framedata);
                } else if (opcode == Opcode.PONG) {
                    this.wsl.onWebsocketPong(this, framedata);
                } else if (!isFin || opcode == Opcode.CONTINUOUS) {
                    if (opcode != Opcode.CONTINUOUS) {
                        if (this.current_continuous_frame_opcode != null) {
                            throw new InvalidDataException(1002, "Previous continuous frame sequence not completed.");
                        }
                        this.current_continuous_frame_opcode = opcode;
                    } else if (isFin) {
                        if (this.current_continuous_frame_opcode == null) {
                            throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                        }
                        this.current_continuous_frame_opcode = null;
                    } else if (this.current_continuous_frame_opcode == null) {
                        throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                    }
                    try {
                        this.wsl.onWebsocketMessageFragment(this, framedata);
                    } catch (Exception e) {
                        this.wsl.onWebsocketError(this, e);
                    }
                } else if (this.current_continuous_frame_opcode != null) {
                    throw new InvalidDataException(1002, "Continuous frame sequence not completed.");
                } else if (opcode == Opcode.TEXT) {
                    try {
                        this.wsl.onWebsocketMessage(this, Charsetfunctions.stringUtf8(framedata.getPayloadData()));
                    } catch (Exception e2) {
                        this.wsl.onWebsocketError(this, e2);
                    }
                } else if (opcode == Opcode.BINARY) {
                    try {
                        this.wsl.onWebsocketMessage(this, framedata.getPayloadData());
                    } catch (Exception e22) {
                        this.wsl.onWebsocketError(this, e22);
                    }
                } else {
                    throw new InvalidDataException(1002, "non control or continious frame expected");
                }
            }
        } catch (InvalidDataException e3) {
            this.wsl.onWebsocketError(this, e3);
            close(e3);
        }
    }

    private void close(int i, String str, boolean z) {
        if (this.readystate != READYSTATE.CLOSING && this.readystate != READYSTATE.CLOSED) {
            if (this.readystate == READYSTATE.OPEN) {
                if (i != PointerIconCompat.TYPE_CELL) {
                    if (this.draft.getCloseHandshakeType() != CloseHandshakeType.NONE) {
                        if (!z) {
                            try {
                                this.wsl.onWebsocketCloseInitiated(this, i, str);
                            } catch (Exception e) {
                                this.wsl.onWebsocketError(this, e);
                            }
                        }
                        try {
                            sendFrame(new CloseFrameBuilder(i, str));
                        } catch (InvalidDataException e2) {
                            this.wsl.onWebsocketError(this, e2);
                            flushAndClose(PointerIconCompat.TYPE_CELL, "generated frame is invalid", false);
                        }
                    }
                    flushAndClose(i, str, z);
                } else if ($assertionsDisabled || !z) {
                    this.readystate = READYSTATE.CLOSING;
                    flushAndClose(i, str, false);
                    return;
                } else {
                    throw new AssertionError();
                }
            } else if (i != -3) {
                flushAndClose(-1, str, false);
            } else if ($assertionsDisabled || z) {
                flushAndClose(-3, str, true);
            } else {
                throw new AssertionError();
            }
            if (i == 1002) {
                flushAndClose(i, str, z);
            }
            this.readystate = READYSTATE.CLOSING;
            this.tmpHandshakeBytes = null;
        }
    }

    public void close(int i, String str) {
        close(i, str, false);
    }

    protected synchronized void closeConnection(int i, String str, boolean z) {
        if (this.readystate != READYSTATE.CLOSED) {
            if (this.key != null) {
                this.key.cancel();
            }
            if (this.channel != null) {
                try {
                    this.channel.close();
                } catch (Exception e) {
                    this.wsl.onWebsocketError(this, e);
                }
            }
            try {
                this.wsl.onWebsocketClose(this, i, str, z);
            } catch (Exception e2) {
                this.wsl.onWebsocketError(this, e2);
            }
            if (this.draft != null) {
                this.draft.reset();
            }
            this.handshakerequest = null;
            this.readystate = READYSTATE.CLOSED;
            this.outQueue.clear();
        }
    }

    protected void closeConnection(int i, boolean z) {
        closeConnection(i, "", z);
    }

    public void closeConnection() {
        if (this.closedremotely == null) {
            throw new IllegalStateException("this method must be used in conjuction with flushAndClose");
        }
        closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
    }

    public void closeConnection(int i, String str) {
        closeConnection(i, str, false);
    }

    protected synchronized void flushAndClose(int i, String str, boolean z) {
        if (!this.flushandclosestate) {
            this.closecode = Integer.valueOf(i);
            this.closemessage = str;
            this.closedremotely = Boolean.valueOf(z);
            this.flushandclosestate = true;
            this.wsl.onWriteDemand(this);
            try {
                this.wsl.onWebsocketClosing(this, i, str, z);
            } catch (Exception e) {
                this.wsl.onWebsocketError(this, e);
            }
            if (this.draft != null) {
                this.draft.reset();
            }
            this.handshakerequest = null;
        }
    }

    public void eot() {
        if (getReadyState() == READYSTATE.NOT_YET_CONNECTED) {
            closeConnection(-1, true);
        } else if (this.flushandclosestate) {
            closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
        } else if (this.draft.getCloseHandshakeType() == CloseHandshakeType.NONE) {
            closeConnection(1000, true);
        } else if (this.draft.getCloseHandshakeType() != CloseHandshakeType.ONEWAY) {
            closeConnection((int) PointerIconCompat.TYPE_CELL, true);
        } else if (this.role == Role.SERVER) {
            closeConnection((int) PointerIconCompat.TYPE_CELL, true);
        } else {
            closeConnection(1000, true);
        }
    }

    public void close(int i) {
        close(i, "", false);
    }

    public void close(InvalidDataException invalidDataException) {
        close(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    public void send(String str) throws WebsocketNotConnectedException {
        if (str == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        send(this.draft.createFrames(str, this.role == Role.CLIENT));
    }

    public void send(ByteBuffer byteBuffer) throws IllegalArgumentException, WebsocketNotConnectedException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        send(this.draft.createFrames(byteBuffer, this.role == Role.CLIENT));
    }

    public void send(byte[] bArr) throws IllegalArgumentException, WebsocketNotConnectedException {
        send(ByteBuffer.wrap(bArr));
    }

    private void send(Collection<Framedata> collection) {
        if (isOpen()) {
            for (Framedata sendFrame : collection) {
                sendFrame(sendFrame);
            }
            return;
        }
        throw new WebsocketNotConnectedException();
    }

    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        send(this.draft.continuousFrame(opcode, byteBuffer, z));
    }

    public void sendFrame(Framedata framedata) {
        if (DEBUG) {
            System.out.println("send frame: " + framedata);
        }
        write(this.draft.createBinaryFrame(framedata));
    }

    public boolean hasBufferedData() {
        return !this.outQueue.isEmpty();
    }

    private HandshakeState isFlashEdgeCase(ByteBuffer byteBuffer) throws IncompleteHandshakeException {
        byteBuffer.mark();
        if (byteBuffer.limit() > Draft.FLASH_POLICY_REQUEST.length) {
            return HandshakeState.NOT_MATCHED;
        }
        if (byteBuffer.limit() < Draft.FLASH_POLICY_REQUEST.length) {
            throw new IncompleteHandshakeException(Draft.FLASH_POLICY_REQUEST.length);
        }
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            if (Draft.FLASH_POLICY_REQUEST[i] != byteBuffer.get()) {
                byteBuffer.reset();
                return HandshakeState.NOT_MATCHED;
            }
            i++;
        }
        return HandshakeState.MATCHED;
    }

    public void startHandshake(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        if ($assertionsDisabled || this.readystate != READYSTATE.CONNECTING) {
            this.handshakerequest = this.draft.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
            this.resourceDescriptor = clientHandshakeBuilder.getResourceDescriptor();
            if ($assertionsDisabled || this.resourceDescriptor != null) {
                try {
                    this.wsl.onWebsocketHandshakeSentAsClient(this, this.handshakerequest);
                    write(this.draft.createHandshake(this.handshakerequest, this.role));
                    return;
                } catch (InvalidDataException e) {
                    throw new InvalidHandshakeException("Handshake data rejected by client.");
                } catch (Exception e2) {
                    this.wsl.onWebsocketError(this, e2);
                    throw new InvalidHandshakeException("rejected because of" + e2);
                }
            }
            throw new AssertionError();
        }
        throw new AssertionError("shall only be called once");
    }

    private void write(ByteBuffer byteBuffer) {
        if (DEBUG) {
            System.out.println("write(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array())) + "}");
        }
        this.outQueue.add(byteBuffer);
        this.wsl.onWriteDemand(this);
    }

    private void write(List<ByteBuffer> list) {
        for (ByteBuffer write : list) {
            write(write);
        }
    }

    private void open(Handshakedata handshakedata) {
        if (DEBUG) {
            System.out.println("open using draft: " + this.draft.getClass().getSimpleName());
        }
        this.readystate = READYSTATE.OPEN;
        try {
            this.wsl.onWebsocketOpen(this, handshakedata);
        } catch (Exception e) {
            this.wsl.onWebsocketError(this, e);
        }
    }

    public boolean isConnecting() {
        if ($assertionsDisabled || !this.flushandclosestate || this.readystate == READYSTATE.CONNECTING) {
            return this.readystate == READYSTATE.CONNECTING;
        } else {
            throw new AssertionError();
        }
    }

    public boolean isOpen() {
        if ($assertionsDisabled || this.readystate != READYSTATE.OPEN || !this.flushandclosestate) {
            return this.readystate == READYSTATE.OPEN;
        } else {
            throw new AssertionError();
        }
    }

    public boolean isClosing() {
        return this.readystate == READYSTATE.CLOSING;
    }

    public boolean isFlushAndClose() {
        return this.flushandclosestate;
    }

    public boolean isClosed() {
        return this.readystate == READYSTATE.CLOSED;
    }

    public READYSTATE getReadyState() {
        return this.readystate;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return this.wsl.getRemoteSocketAddress(this);
    }

    public InetSocketAddress getLocalSocketAddress() {
        return this.wsl.getLocalSocketAddress(this);
    }

    public Draft getDraft() {
        return this.draft;
    }

    public void close() {
        close(1000);
    }

    public String getResourceDescriptor() {
        return this.resourceDescriptor;
    }

    public short getStatusCode() {
        return this.statusCode;
    }
}
