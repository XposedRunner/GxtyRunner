package cn.jiguang.d.f;

import android.support.v4.app.NotificationManagerCompat;
import cn.jiguang.e.d;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public final class c extends a {
    private ByteBuffer h = ByteBuffer.allocate(8192);

    private boolean b(byte[] bArr) {
        try {
            if (!b()) {
                d.c("NioSocketClient", "send error - connect was invalid");
                return false;
            } else if (bArr == null || bArr.length <= 0) {
                d.c("NioSocketClient", "send error - invalide buffer");
                return false;
            } else {
                int write = this.b.write(ByteBuffer.wrap(bArr));
                if (write > 0) {
                    d.a("NioSocketClient", "isWritable has send len:" + write);
                } else if (write < 0) {
                    d.a("NioSocketClient", "isWritable error:" + write);
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            d.h("NioSocketClient", "send data error:" + e.getMessage());
            a();
            return false;
        }
    }

    public final synchronized int a(String str, int i) {
        int i2 = -991;
        synchronized (this) {
            super.a(str, i);
            try {
                this.b = SocketChannel.open();
                this.d = Selector.open();
                this.b.configureBlocking(false);
                this.b.connect(new InetSocketAddress(str, i));
                d.c("NioSocketClient", "tcp connecting。。。");
                long currentTimeMillis = System.currentTimeMillis();
                while (!this.b.finishConnect()) {
                    if (this.e) {
                        if (System.currentTimeMillis() - currentTimeMillis > 3000) {
                            i2 = -994;
                            break;
                        }
                    }
                    d.c("NioSocketClient", "has close channel when connect...");
                    break;
                }
                if (this.e) {
                    d.c("NioSocketClient", "tcp connected");
                    this.b.register(this.d, 1);
                    i2 = 0;
                } else {
                    d.c("NioSocketClient", "has close channel when connected...");
                }
            } catch (Throwable th) {
                d.h("NioSocketClient", "tcp connect has failed:" + th);
                i2 = th instanceof SocketTimeoutException ? -994 : NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
            }
        }
        return i2;
    }

    public final int a(byte[] bArr) {
        return b(bArr) ? 0 : 103;
    }

    public final e a(int i) {
        Throwable th;
        if (!b()) {
            return new e(-991, "recv error,the connect is invalid");
        }
        ByteBuffer b;
        cn.jiguang.d.e.a.a.c c = c();
        if (c != null) {
            b = b(c.f());
            if (b != null) {
                return new e(0, b);
            }
        }
        int i2 = 0;
        int i3 = 1048576;
        while (b() && this.c < i3) {
            int select;
            if (i > 0) {
                select = this.d.select((long) i);
            } else {
                try {
                    select = this.d.select();
                } catch (Throwable th2) {
                    th = th2;
                    r2 = new e(-997, th.getMessage());
                    e eVar;
                    if (!(th instanceof SocketTimeoutException)) {
                        return eVar;
                    }
                    eVar.a(-994);
                    return eVar;
                }
            }
            if (select == 0) {
                d.c("NioSocketClient", "readSelect:" + select + ",time out:" + i);
                if (i > 0) {
                    return new e(-994, "recv time out");
                }
            } else {
                Iterator it = this.d.selectedKeys().iterator();
                int i4 = i2;
                while (it.hasNext()) {
                    SelectionKey selectionKey = (SelectionKey) it.next();
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    if (selectionKey.isReadable()) {
                        select = socketChannel.read(this.h);
                        if (select < 0) {
                            return new e(-996, "read len < 0:" + select);
                        }
                        this.h.flip();
                        i4 = this.h.limit();
                        if (this.a.remaining() < i4) {
                            return new e(-996, "the total buf remaining less than readLen,remaining:" + this.a.remaining() + ",readLen:" + i4);
                        }
                        this.a.put(this.h);
                        this.c += i4;
                        this.h.compact();
                        if (this.c < 20) {
                            d.c("NioSocketClient", "totalbuf can not parse head:" + this.c + ",peerNetData len:" + i4 + ",read:" + select);
                            i2 = i4;
                        } else {
                            c = c();
                            i3 = c != null ? c.f() : i3;
                            i2 = i4;
                        }
                    } else {
                        selectionKey.isWritable();
                        i2 = i4;
                    }
                    it.remove();
                    i4 = i2;
                }
                i2 = i4;
            }
        }
        if (i3 == 1048576) {
            return new e(-997, "recv empty data or tcp has close");
        }
        d.c("NioSocketClient", "read len:" + i2 + ",recvTotalLen:" + this.c + ",shouldLen:" + i3);
        b = b(i3);
        return b != null ? new e(0, b) : new e(-1001, "parse error");
    }

    public final void a() {
        d.c("NioSocketClient", "close this connect");
        super.a();
        if (this.d != null) {
            try {
                this.d.close();
            } catch (IOException e) {
            }
        }
        if (this.b != null) {
            try {
                this.b.close();
            } catch (Exception e2) {
                d.c("NioSocketClient", "close channel failed:" + e2.getMessage());
            }
        }
        this.b = null;
    }
}
