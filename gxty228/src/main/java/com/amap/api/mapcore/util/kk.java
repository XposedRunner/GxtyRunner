package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_location;

/* compiled from: MapLocationManagerResultHandler */
public final class kk extends Handler {
    ki a = null;

    public kk(Looper looper, ki kiVar) {
        super(looper);
        this.a = kiVar;
    }

    public kk(ki kiVar) {
        this.a = kiVar;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                try {
                    if (this.a != null) {
                        this.a.a((Inner_3dMap_location) message.obj);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    kz.a(th, "ClientResultHandler", "RESULT_LOCATION_FINISH");
                    return;
                }
            default:
                return;
        }
    }
}
