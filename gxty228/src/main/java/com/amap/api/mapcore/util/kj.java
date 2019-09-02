package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* compiled from: MapLocationManagerActionHandler */
public final class kj extends Handler {
    ki a = null;

    public kj(Looper looper, ki kiVar) {
        super(looper);
        this.a = kiVar;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                try {
                    this.a.a((Inner_3dMap_locationOption) message.obj);
                    return;
                } catch (Throwable th) {
                    kz.a(th, "ClientActionHandler", "ACTION_SET_OPTION");
                    return;
                }
            case 1002:
                try {
                    this.a.a((Inner_3dMap_locationListener) message.obj);
                    return;
                } catch (Throwable th2) {
                    kz.a(th2, "ClientActionHandler", "ACTION_SET_LISTENER");
                    return;
                }
            case PointerIconCompat.TYPE_HELP /*1003*/:
                try {
                    this.a.b((Inner_3dMap_locationListener) message.obj);
                    return;
                } catch (Throwable th22) {
                    kz.a(th22, "ClientActionHandler", "ACTION_REMOVE_LISTENER");
                    return;
                }
            case 1004:
                try {
                    this.a.a();
                    return;
                } catch (Throwable th222) {
                    kz.a(th222, "ClientActionHandler", "ACTION_START_LOCATION");
                    return;
                }
            case 1005:
                try {
                    this.a.b();
                    return;
                } catch (Throwable th2222) {
                    kz.a(th2222, "ClientActionHandler", "ACTION_GET_LOCATION");
                    return;
                }
            case PointerIconCompat.TYPE_CELL /*1006*/:
                try {
                    this.a.c();
                    return;
                } catch (Throwable th22222) {
                    kz.a(th22222, "ClientActionHandler", "ACTION_STOP_LOCATION");
                    return;
                }
            case PointerIconCompat.TYPE_CROSSHAIR /*1007*/:
                try {
                    this.a.d();
                    return;
                } catch (Throwable th222222) {
                    kz.a(th222222, "ClientActionHandler", "ACTION_DESTROY");
                    return;
                }
            default:
                return;
        }
    }
}
