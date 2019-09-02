package cn.jiguang.d.d;

import java.net.InetAddress;

final class d extends Thread {
    private String a = null;
    private InetAddress b = null;

    public d(String str) {
        this.a = str;
    }

    final synchronized InetAddress a() {
        return this.b;
    }

    public final void run() {
        try {
            this.b = InetAddress.getByName(this.a);
        } catch (Throwable e) {
            cn.jiguang.e.d.d("ConnectingHelper", "Unknown host exception!", e);
        } catch (Throwable e2) {
            cn.jiguang.e.d.d("ConnectingHelper", "The failure appears to have been a lack of INTERNET !", e2);
        }
    }
}
