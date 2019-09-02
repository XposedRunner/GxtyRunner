package com.amap.api.mapcore.util;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.IPoint;
import com.github.mikephil.charting.utils.Utils;

/* compiled from: MyLocationOverlay */
public class ct {
    a a = null;
    ValueAnimator b;
    AnimatorListener c = new AnimatorListener(this) {
        final /* synthetic */ ct a;

        {
            this.a = r1;
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            this.a.i();
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    };
    AnimatorUpdateListener d = new AnimatorUpdateListener(this) {
        final /* synthetic */ ct a;

        {
            this.a = r1;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            try {
                if (this.a.g != null) {
                    LatLng latLng = (LatLng) valueAnimator.getAnimatedValue();
                    this.a.g.setCenter(latLng);
                    this.a.f.setPosition(latLng);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private lj e;
    private Marker f;
    private Circle g;
    private MyLocationStyle h = new MyLocationStyle();
    private LatLng i;
    private double j;
    private Context k;
    private i l;
    private int m = 4;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;

    /* compiled from: MyLocationOverlay */
    public static class a implements TypeEvaluator {
        public Object evaluate(float f, Object obj, Object obj2) {
            LatLng latLng = (LatLng) obj;
            LatLng latLng2 = (LatLng) obj2;
            return new LatLng(latLng.latitude + (((double) f) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (((double) f) * (latLng2.longitude - latLng.longitude)));
        }
    }

    public ct(lj ljVar, Context context) {
        this.k = context.getApplicationContext();
        this.e = ljVar;
        this.l = new i(this.k, ljVar);
        a(4, true);
    }

    public void a(MyLocationStyle myLocationStyle) {
        try {
            this.h = myLocationStyle;
            a(this.h.isMyLocationShowing());
            if (this.h.isMyLocationShowing()) {
                j();
                if (this.f != null || this.g != null) {
                    this.l.a(this.f);
                    a(this.h.getMyLocationType());
                    return;
                }
                return;
            }
            this.l.a(false);
            this.m = this.h.getMyLocationType();
        } catch (Throwable th) {
            gz.c(th, "MyLocationOverlay", "setMyLocationStyle");
            th.printStackTrace();
        }
    }

    public MyLocationStyle a() {
        return this.h;
    }

    public void a(int i) {
        a(i, false);
    }

    private void a(int i, boolean z) {
        this.m = i;
        this.n = false;
        this.p = false;
        this.o = false;
        this.r = false;
        this.s = false;
        switch (this.m) {
            case 1:
                this.o = true;
                this.p = true;
                this.q = true;
                break;
            case 2:
                this.o = true;
                this.q = true;
                break;
            case 3:
                this.o = true;
                this.s = true;
                break;
            case 4:
                this.o = true;
                this.r = true;
                break;
            case 5:
                this.r = true;
                break;
            case 7:
                this.s = true;
                break;
        }
        if (this.r || this.s) {
            if (this.s) {
                this.l.a(true);
                if (!z) {
                    try {
                        this.e.a(p.a(17.0f));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                b(45.0f);
            } else {
                this.l.a(false);
            }
            this.l.a();
            if (this.f != null) {
                this.f.setFlat(true);
                return;
            }
            return;
        }
        if (this.f != null) {
            this.f.setFlat(false);
        }
        h();
        g();
        f();
    }

    private void f() {
        this.l.b();
    }

    private void g() {
        b(0.0f);
    }

    private void h() {
        c(0.0f);
    }

    private void b(float f) {
        if (this.e != null) {
            try {
                this.e.a(p.c(f));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void c(float f) {
        if (this.e != null) {
            try {
                this.e.a(p.d(f));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(Location location) {
        if (location != null) {
            a(this.h.isMyLocationShowing());
            if (this.h.isMyLocationShowing()) {
                this.i = new LatLng(location.getLatitude(), location.getLongitude());
                this.j = (double) location.getAccuracy();
                if (this.f == null && this.g == null) {
                    j();
                }
                if (this.g != null) {
                    try {
                        if (this.j != -1.0d) {
                            this.g.setRadius(this.j);
                        }
                    } catch (Throwable th) {
                        gz.c(th, "MyLocationOverlay", "setCentAndRadius");
                        th.printStackTrace();
                    }
                }
                d(location.getBearing());
                if (this.i.equals(this.f.getPosition())) {
                    i();
                } else {
                    a(this.i);
                }
            }
        }
    }

    private void d(float f) {
        if (this.q) {
            float f2 = f % 360.0f;
            if (f2 > 180.0f) {
                f2 -= 360.0f;
            } else if (f2 < -180.0f) {
                f2 += 360.0f;
            }
            if (this.f != null) {
                this.f.setRotateAngle(-f2);
            }
        }
    }

    private void i() {
        if (this.i == null || !this.o) {
            return;
        }
        if (!this.p || !this.n) {
            this.n = true;
            try {
                Point obtain = IPoint.obtain();
                GLMapState.lonlat2Geo(this.i.longitude, this.i.latitude, obtain);
                this.e.b(p.a(obtain));
            } catch (Throwable th) {
                gz.c(th, "MyLocationOverlay", "moveMapToLocation");
                th.printStackTrace();
            }
        }
    }

    private void j() {
        if (this.h == null) {
            this.h = new MyLocationStyle();
            this.h.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            l();
            return;
        }
        if (this.h.getMyLocationIcon() == null || this.h.getMyLocationIcon().getBitmap() == null) {
            this.h.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
        }
        l();
    }

    public void b() throws RemoteException {
        k();
        if (this.l != null) {
            f();
            this.l = null;
        }
    }

    private void k() {
        if (this.g != null) {
            try {
                this.e.a(this.g.getId());
            } catch (Throwable th) {
                gz.c(th, "MyLocationOverlay", "locationIconRemove");
                th.printStackTrace();
            }
            this.g = null;
        }
        if (this.f != null) {
            this.f.remove();
            this.f = null;
            this.l.a(null);
        }
    }

    public void a(boolean z) {
        if (!(this.g == null || this.g.isVisible() == z)) {
            this.g.setVisible(z);
        }
        if (this.f != null && this.f.isVisible() != z) {
            this.f.setVisible(z);
        }
    }

    private void l() {
        try {
            if (this.g == null) {
                this.g = this.e.addCircle(new CircleOptions().zIndex(1.0f));
            }
            if (this.g != null) {
                if (this.g.getStrokeWidth() != this.h.getStrokeWidth()) {
                    this.g.setStrokeWidth(this.h.getStrokeWidth());
                }
                if (this.g.getFillColor() != this.h.getRadiusFillColor()) {
                    this.g.setFillColor(this.h.getRadiusFillColor());
                }
                if (this.g.getStrokeColor() != this.h.getStrokeColor()) {
                    this.g.setStrokeColor(this.h.getStrokeColor());
                }
                if (this.i != null) {
                    this.g.setCenter(this.i);
                }
                this.g.setRadius(this.j);
                this.g.setVisible(true);
            }
            if (this.f == null) {
                this.f = this.e.addMarker(new MarkerOptions().visible(false));
            }
            if (this.f != null) {
                if (!(this.f.getAnchorU() == this.h.getAnchorU() && this.f.getAnchorV() == this.h.getAnchorV())) {
                    this.f.setAnchor(this.h.getAnchorU(), this.h.getAnchorV());
                }
                if (this.f.getIcons() == null || this.f.getIcons().size() == 0) {
                    this.f.setIcon(this.h.getMyLocationIcon());
                } else if (!(this.h.getMyLocationIcon() == null || ((BitmapDescriptor) this.f.getIcons().get(0)).equals(this.h.getMyLocationIcon()))) {
                    this.f.setIcon(this.h.getMyLocationIcon());
                }
                if (this.i != null) {
                    this.f.setPosition(this.i);
                    this.f.setVisible(true);
                }
            }
            i();
            this.l.a(this.f);
        } catch (Throwable th) {
            gz.c(th, "MyLocationOverlay", "myLocStyle");
            th.printStackTrace();
        }
    }

    public void a(float f) {
        if (this.f != null) {
            this.f.setRotateAngle(f);
        }
    }

    public String c() {
        if (this.f != null) {
            return this.f.getId();
        }
        return null;
    }

    public String d() throws RemoteException {
        if (this.g != null) {
            return this.g.getId();
        }
        return null;
    }

    public void e() {
        this.g = null;
        this.f = null;
    }

    private void a(LatLng latLng) {
        LatLng position = this.f.getPosition();
        if (position == null) {
            position = new LatLng(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        }
        if (this.a == null) {
            this.a = new a();
        }
        if (this.b == null) {
            this.b = ValueAnimator.ofObject(new a(), new Object[]{position, latLng});
            this.b.addListener(this.c);
            this.b.addUpdateListener(this.d);
        } else {
            this.b.setObjectValues(new Object[]{position, latLng});
            this.b.setEvaluator(this.a);
        }
        if (position.latitude == Utils.DOUBLE_EPSILON && position.longitude == Utils.DOUBLE_EPSILON) {
            this.b.setDuration(1);
        } else {
            this.b.setDuration(1000);
        }
        this.b.start();
    }
}
