package com.amap.api.mapcore.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;
import com.autonavi.amap.mapcore.tools.GlMapUtil;

/* compiled from: MapFragmentDelegateImp */
public class lo implements IMapFragmentDelegate {
    public static volatile Context a;
    public int b = 0;
    private IAMap c;
    private int d = 0;
    private AMapOptions e;

    public lo(int i) {
        this.d = i % 3;
    }

    public void setContext(Context context) {
        a(context);
    }

    private static void a(Context context) {
        if (context != null) {
            a = context.getApplicationContext();
        }
    }

    public void setOptions(AMapOptions aMapOptions) {
        this.e = aMapOptions;
    }

    public IAMap getMap() throws RemoteException {
        if (this.c == null) {
            if (a == null) {
                Log.w("MapFragmentDelegateImp", "Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
                return null;
            }
            int i = a.getResources().getDisplayMetrics().densityDpi;
            if (i <= 120) {
                le.a = 0.5f;
            } else if (i <= GlMapUtil.DEVICE_DISPLAY_DPI_NORMAL) {
                le.a = 0.8f;
            } else if (i <= GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) {
                le.a = 0.87f;
            } else if (i <= GlMapUtil.DEVICE_DISPLAY_DPI_HIGH) {
                le.a = 1.0f;
            } else if (i <= GlMapUtil.DEVICE_DISPLAY_DPI_XHIGH) {
                le.a = 1.5f;
            } else if (i <= GlMapUtil.DEVICE_DISPLAY_DPI_XXHIGH) {
                le.a = 1.8f;
            } else {
                le.a = 0.9f;
            }
            if (this.d == 0) {
                this.c = new d(a).a();
            } else if (this.d == 1) {
                this.c = new e(a).a();
            } else {
                this.c = new bh(a).a();
            }
        }
        return this.c;
    }

    public void onInflate(Activity activity, AMapOptions aMapOptions, Bundle bundle) throws RemoteException {
        setContext(activity.getApplicationContext());
        this.e = aMapOptions;
    }

    public void onCreate(Bundle bundle) throws RemoteException {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws RemoteException {
        if (a == null && layoutInflater != null) {
            setContext(layoutInflater.getContext().getApplicationContext());
        }
        try {
            this.c = getMap();
            this.c.setVisibilityEx(this.b);
            if (this.e == null && bundle != null) {
                byte[] byteArray = bundle.getByteArray("MAP_OPTIONS");
                if (byteArray != null) {
                    Parcel obtain = Parcel.obtain();
                    obtain.unmarshall(byteArray, 0, byteArray.length);
                    obtain.setDataPosition(0);
                    this.e = AMapOptions.CREATOR.createFromParcel(obtain);
                }
            }
            a(this.e);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.c.getView();
    }

    void a(AMapOptions aMapOptions) throws RemoteException {
        if (aMapOptions != null && this.c != null) {
            CameraPosition camera = aMapOptions.getCamera();
            if (camera != null) {
                this.c.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
            }
            UiSettings aMapUiSettings = this.c.getAMapUiSettings();
            aMapUiSettings.setRotateGesturesEnabled(aMapOptions.getRotateGesturesEnabled());
            aMapUiSettings.setScrollGesturesEnabled(aMapOptions.getScrollGesturesEnabled());
            aMapUiSettings.setTiltGesturesEnabled(aMapOptions.getTiltGesturesEnabled());
            aMapUiSettings.setZoomControlsEnabled(aMapOptions.getZoomControlsEnabled());
            aMapUiSettings.setZoomGesturesEnabled(aMapOptions.getZoomGesturesEnabled());
            aMapUiSettings.setCompassEnabled(aMapOptions.getCompassEnabled());
            aMapUiSettings.setScaleControlsEnabled(aMapOptions.getScaleControlsEnabled());
            aMapUiSettings.setLogoPosition(aMapOptions.getLogoPosition());
            this.c.setMapType(aMapOptions.getMapType());
            this.c.setZOrderOnTop(aMapOptions.getZOrderOnTop());
        }
    }

    public void onResume() throws RemoteException {
        if (this.c != null) {
            this.c.onActivityResume();
        }
    }

    public void onPause() throws RemoteException {
        if (this.c != null) {
            this.c.onActivityPause();
        }
    }

    public void onDestroyView() throws RemoteException {
    }

    public void onDestroy() throws RemoteException {
        if (this.c != null) {
            this.c.clear();
            this.c.destroy();
            this.c = null;
        }
    }

    public void onLowMemory() throws RemoteException {
        Log.d("onLowMemory", "onLowMemory run");
    }

    public void onSaveInstanceState(Bundle bundle) throws RemoteException {
        if (this.c != null) {
            if (this.e == null) {
                this.e = new AMapOptions();
            }
            try {
                Parcel obtain = Parcel.obtain();
                this.e = this.e.camera(getMap().getCameraPosition());
                this.e.writeToParcel(obtain, 0);
                bundle.putByteArray("MAP_OPTIONS", obtain.marshall());
            } catch (Throwable th) {
            }
        }
    }

    public boolean isReady() throws RemoteException {
        return false;
    }

    public void setVisibility(int i) {
        this.b = i;
        if (this.c != null) {
            this.c.setVisibilityEx(i);
        }
    }
}
