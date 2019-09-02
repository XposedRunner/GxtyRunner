package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.location.Location;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.ae.gmap.GLMapEngine;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.ae.gmap.listener.AMapWidgetListener;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.message.AbstractGestureMapMessage;

/* compiled from: IAMapDelegate */
public interface lj extends IAMap {
    float a(int i);

    int a(EAMapPlatformGestureInfo eAMapPlatformGestureInfo);

    int a(IMarkerAction iMarkerAction, Rect rect);

    f a(BitmapDescriptor bitmapDescriptor);

    f a(BitmapDescriptor bitmapDescriptor, boolean z);

    LatLngBounds a(LatLng latLng, float f, float f2, float f3);

    GLMapEngine a();

    void a(double d, double d2, FPoint fPoint);

    void a(double d, double d2, IPoint iPoint);

    void a(float f, float f2, IPoint iPoint);

    void a(int i, float f);

    void a(int i, int i2);

    void a(int i, int i2, PointF pointF);

    void a(int i, int i2, DPoint dPoint);

    void a(int i, int i2, FPoint fPoint);

    void a(int i, int i2, IPoint iPoint);

    void a(int i, MotionEvent motionEvent);

    void a(int i, IPoint iPoint);

    void a(int i, AbstractGestureMapMessage abstractGestureMapMessage);

    void a(Location location) throws RemoteException;

    void a(f fVar);

    void a(kn knVar) throws RemoteException;

    void a(AMapWidgetListener aMapWidgetListener);

    void a(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException;

    void a(String str, boolean z, int i);

    void a(boolean z);

    void a(boolean z, boolean z2);

    void a(boolean z, byte[] bArr);

    boolean a(String str) throws RemoteException;

    void b();

    void b(double d, double d2, IPoint iPoint);

    void b(int i, int i2);

    void b(int i, int i2, DPoint dPoint);

    void b(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException;

    void b(boolean z);

    boolean b(int i, MotionEvent motionEvent);

    boolean b(String str);

    GLMapState c();

    void c(int i);

    void c(String str);

    void c(boolean z);

    boolean c(int i, MotionEvent motionEvent);

    int d();

    String d(String str);

    void d(boolean z);

    boolean d(int i);

    int e();

    void e(boolean z);

    boolean e(int i);

    int f(int i);

    void f();

    float g();

    void g(int i);

    float h(int i);

    lm h();

    void h(boolean z);

    void i();

    void i(int i);

    void i(boolean z);

    void j();

    void j(int i);

    void k(int i);

    boolean k();

    float l(int i);

    Point l();

    View m();

    float n(int i);

    boolean n();

    float o(int i);

    int o();

    void q();

    float t();

    da u(int i);

    float v(int i);

    Context v();

    float[] w();

    db x();

    void y();
}
