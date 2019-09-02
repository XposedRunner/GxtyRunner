package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.g.h;

/* compiled from: ResourceRecycler */
class j {
    private boolean a;
    private final Handler b = new Handler(Looper.getMainLooper(), new a());

    /* compiled from: ResourceRecycler */
    private static class a implements Callback {
        private a() {
        }

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((i) message.obj).d();
            return true;
        }
    }

    j() {
    }

    public void a(i<?> iVar) {
        h.a();
        if (this.a) {
            this.b.obtainMessage(1, iVar).sendToTarget();
            return;
        }
        this.a = true;
        iVar.d();
        this.a = false;
    }
}
