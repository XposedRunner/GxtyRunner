package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

/* compiled from: UiSettingsDelegateImp */
class j implements lm {
    final Handler a = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ j a;

        public void handleMessage(Message message) {
            if (message != null && this.a.b != null) {
                try {
                    switch (message.what) {
                        case 0:
                            this.a.b.a(this.a.h);
                            return;
                        case 1:
                            this.a.b.e(this.a.j);
                            return;
                        case 2:
                            this.a.b.d(this.a.i);
                            return;
                        case 3:
                            this.a.b.c(this.a.f);
                            return;
                        case 4:
                            this.a.b.b(this.a.n);
                            return;
                        case 5:
                            this.a.b.h(this.a.k);
                            return;
                        case 6:
                            this.a.b.y();
                            return;
                        default:
                            return;
                    }
                } catch (Throwable th) {
                    gz.c(th, "UiSettingsDelegateImp", "handleMessage");
                }
                gz.c(th, "UiSettingsDelegateImp", "handleMessage");
            }
        }
    };
    private lj b;
    private boolean c = true;
    private boolean d = true;
    private boolean e = true;
    private boolean f = false;
    private boolean g = true;
    private boolean h = true;
    private boolean i = true;
    private boolean j = false;
    private boolean k = true;
    private int l = 0;
    private int m = 1;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;

    j(lj ljVar) {
        this.b = ljVar;
    }

    public boolean isIndoorSwitchEnabled() throws RemoteException {
        return this.n;
    }

    public void setIndoorSwitchEnabled(boolean z) throws RemoteException {
        this.n = z;
        this.a.obtainMessage(4).sendToTarget();
    }

    public void setScaleControlsEnabled(boolean z) throws RemoteException {
        this.j = z;
        this.a.obtainMessage(1).sendToTarget();
    }

    public void setZoomControlsEnabled(boolean z) throws RemoteException {
        this.h = z;
        this.a.obtainMessage(0).sendToTarget();
    }

    public void setCompassEnabled(boolean z) throws RemoteException {
        this.i = z;
        this.a.obtainMessage(2).sendToTarget();
    }

    public void setMyLocationButtonEnabled(boolean z) throws RemoteException {
        this.f = z;
        this.a.obtainMessage(3).sendToTarget();
    }

    public void setScrollGesturesEnabled(boolean z) throws RemoteException {
        this.d = z;
    }

    public void setZoomGesturesEnabled(boolean z) throws RemoteException {
        this.g = z;
    }

    public void setTiltGesturesEnabled(boolean z) throws RemoteException {
        this.e = z;
    }

    public void setRotateGesturesEnabled(boolean z) throws RemoteException {
        this.c = z;
    }

    public void setAllGesturesEnabled(boolean z) throws RemoteException {
        setRotateGesturesEnabled(z);
        setTiltGesturesEnabled(z);
        setZoomGesturesEnabled(z);
        setScrollGesturesEnabled(z);
    }

    public void setLogoPosition(int i) throws RemoteException {
        this.l = i;
        this.b.i(i);
    }

    public void setZoomPosition(int i) throws RemoteException {
        this.m = i;
        this.b.g(i);
    }

    public boolean isScaleControlsEnabled() throws RemoteException {
        return this.j;
    }

    public boolean isZoomControlsEnabled() throws RemoteException {
        return this.h;
    }

    public boolean isCompassEnabled() throws RemoteException {
        return this.i;
    }

    public boolean isMyLocationButtonEnabled() throws RemoteException {
        return this.f;
    }

    public boolean isScrollGesturesEnabled() throws RemoteException {
        return this.d;
    }

    public boolean isZoomGesturesEnabled() throws RemoteException {
        return this.g;
    }

    public boolean isTiltGesturesEnabled() throws RemoteException {
        return this.e;
    }

    public boolean isRotateGesturesEnabled() throws RemoteException {
        return this.c;
    }

    public int getLogoPosition() throws RemoteException {
        return this.l;
    }

    public int getZoomPosition() throws RemoteException {
        return this.m;
    }

    public void setZoomInByScreenCenter(boolean z) {
        this.o = z;
    }

    public boolean isZoomInByScreenCenter() {
        return this.o;
    }

    public void setLogoBottomMargin(int i) {
        this.b.j(i);
    }

    public void setLogoLeftMargin(int i) {
        this.b.k(i);
    }

    public float getLogoMarginRate(int i) {
        return this.b.l(i);
    }

    public void setLogoMarginRate(int i, float f) {
        this.b.a(i, f);
    }

    public void setGestureScaleByMapCenter(boolean z) throws RemoteException {
        this.p = z;
    }

    public boolean isGestureScaleByMapCenter() throws RemoteException {
        return this.p;
    }

    public void setLogoEnable(boolean z) {
        this.k = z;
        this.a.obtainMessage(5).sendToTarget();
    }

    public void requestRefreshLogo() {
        this.a.obtainMessage(6).sendToTarget();
    }

    public boolean isLogoEnable() {
        return this.k;
    }
}
