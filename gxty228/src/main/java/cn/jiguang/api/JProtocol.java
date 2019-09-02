package cn.jiguang.api;

import cn.jiguang.api.utils.ProtocolUtil;
import cn.jiguang.d.e.a.a.c;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public abstract class JProtocol {
    public static final int DEFAULT_RESP_CODE = 0;
    public static final int DEFAULT_RID = 0;
    public static final int DEFAULT_SID = 0;
    public static final int NO_JUID = -1;
    public static final int NO_RESP_CODE = -1;
    public static final int NO_SID = -1;
    private static final int PACKET_SIZE = 7168;
    private static final String TAG = "JProtocol";
    protected ByteBuffer body;
    protected c head;
    private boolean isRequest;

    public JProtocol(boolean z, int i, int i2, long j) {
        this.isRequest = z;
        this.head = new c(z, i, i2, j);
        this.body = ByteBuffer.allocate(PACKET_SIZE);
    }

    public JProtocol(boolean z, int i, int i2, long j, int i3, long j2) {
        this.isRequest = z;
        this.head = new c(z, 0, i, i2, j, i3, j2);
        this.body = ByteBuffer.allocate(PACKET_SIZE);
    }

    public JProtocol(boolean z, Object obj, ByteBuffer byteBuffer) {
        this.isRequest = z;
        this.head = (c) obj;
        if (byteBuffer != null) {
            this.body = byteBuffer;
            parseBody();
            return;
        }
        d.g(TAG, "No body to parse.");
    }

    public JProtocol(boolean z, ByteBuffer byteBuffer, byte[] bArr) {
        this.isRequest = z;
        try {
            this.head = new c(z, bArr);
        } catch (Exception e) {
            d.g(TAG, "create JHead failed:" + e.getMessage());
        }
        if (byteBuffer != null) {
            this.body = byteBuffer;
            parseBody();
            return;
        }
        d.g(TAG, "No body to parse.");
    }

    public static byte[] parseHead(Object obj) {
        if (obj == null) {
            d.g(TAG, "object was null");
            return null;
        } else if (obj instanceof c) {
            return ((c) obj).g();
        } else {
            d.g(TAG, "unknow Object");
            return null;
        }
    }

    private final byte[] toBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = ProtocolUtil.getBytes(this.body);
        if (bytes == null) {
            d.g(TAG, "toBytes bodyBytes  is  null");
            return null;
        }
        this.head.a((this.isRequest ? 24 : 20) + bytes.length);
        try {
            byteArrayOutputStream.write(this.head.g());
            byteArrayOutputStream.write(bytes);
        } catch (Exception e) {
        }
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        d.c(TAG, "Final - len:" + toByteArray.length + ", bytes: " + k.a(toByteArray));
        return toByteArray;
    }

    public ByteBuffer getBody() {
        return this.body;
    }

    public int getCommand() {
        return this.head.a();
    }

    public c getHead() {
        return this.head;
    }

    public long getJuid() {
        return this.head.c();
    }

    public abstract String getName();

    public Long getRid() {
        return this.head.b();
    }

    public int getSid() {
        return this.head.d();
    }

    public int getVersion() {
        return this.head.e();
    }

    protected abstract boolean isNeedParseeErrorMsg();

    protected abstract void parseBody();

    public String toString() {
        return (this.isRequest ? "[Request]" : "[Response]") + " - " + this.head.toString();
    }

    protected abstract void writeBody();

    public final byte[] writeBodyAndToBytes() {
        this.body.clear();
        writeBody();
        this.body.flip();
        return toBytes();
    }

    protected void writeBytes(byte[] bArr) {
        this.body.put(bArr);
    }

    protected void writeInt1(int i) {
        this.body.put((byte) i);
    }

    protected void writeInt2(int i) {
        this.body.putShort((short) i);
    }

    protected void writeInt4(int i) {
        this.body.putInt(i);
    }

    protected void writeLong8(long j) {
        this.body.putLong(j);
    }

    protected void writeTlv2(String str) {
        this.body.put(ProtocolUtil.tlv2ToByteArray(str));
    }
}
