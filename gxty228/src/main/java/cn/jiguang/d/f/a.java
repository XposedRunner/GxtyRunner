package cn.jiguang.d.f;

import android.os.Handler;
import android.os.HandlerThread;
import cn.jiguang.d.e.a.a.c;
import cn.jiguang.e.d;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;

public abstract class a {
    protected ByteBuffer a = ByteBuffer.allocate(20480);
    protected SocketChannel b;
    protected int c;
    protected Selector d;
    protected boolean e = false;
    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected Handler g;

    public int a(String str, int i) {
        if (this.g == null) {
            HandlerThread handlerThread = new HandlerThread("socketSendHandler");
            handlerThread.start();
            this.g = new b(this, handlerThread.getLooper());
        }
        if (this.a == null) {
            this.a = ByteBuffer.allocate(20480);
        }
        this.a.clear();
        this.c = 0;
        this.e = true;
        return 0;
    }

    public abstract int a(byte[] bArr);

    public abstract e a(int i);

    public void a() {
        if (!b()) {
            d.c("BaseSocket", "this connect has closed");
        }
        if (this.g != null) {
            this.g.removeCallbacksAndMessages(null);
            try {
                this.g.getLooper().quit();
            } catch (Exception e) {
                d.i("BaseSocket", "#unexcepted - looper quit failed cause by :" + e.getMessage());
            }
            this.g = null;
        }
        this.e = false;
        if (this.a != null) {
            this.a.clear();
        }
        this.c = 0;
    }

    protected final ByteBuffer b(int i) {
        if (this.c < i) {
            return null;
        }
        this.c -= i;
        byte[] bArr = new byte[i];
        this.a.flip();
        this.a.get(bArr, 0, i);
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.a.compact();
        return wrap;
    }

    public final boolean b() {
        return this.e && this.b != null && this.b.isConnected();
    }

    protected final c c() {
        if (this.c < 20) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(20);
        allocate.put(this.a.array(), 0, 20);
        return new c(false, allocate.array());
    }
}
