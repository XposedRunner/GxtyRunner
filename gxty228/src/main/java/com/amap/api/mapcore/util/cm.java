package com.amap.api.mapcore.util;

import android.os.RemoteException;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.IOverlay;

/* compiled from: IOverlayDelegate */
public interface cm extends IOverlay {
    void a(MapConfig mapConfig) throws RemoteException;

    boolean a();

    boolean c();
}
