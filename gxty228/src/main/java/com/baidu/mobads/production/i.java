package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.error.XAdErrorCode;

class i implements Runnable {
    final /* synthetic */ b a;

    i(b bVar) {
        this.a = bVar;
    }

    public void run() {
        try {
            this.a.b();
            this.a.l();
            this.a.k();
            this.a.a(XAdErrorCode.REQUEST_TIMEOUT, "");
        } catch (Throwable e) {
            this.a.s.e(e);
        }
    }
}
