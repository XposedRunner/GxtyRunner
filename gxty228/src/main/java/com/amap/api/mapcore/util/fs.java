package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.Marker;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.listener.AMapWidgetListener;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;

/* compiled from: MapOverlayViewGroup */
public class fs extends ViewGroup implements ab {
    ac a;
    private lj b;
    private Context c;
    private fu d;
    private fr e;
    private fp f;
    private ft g;
    private fo h;
    private fq i;
    private ew j;
    private View k;
    private kn l;
    private Drawable m = null;
    private boolean n = true;
    private View o;
    private boolean p;

    /* compiled from: MapOverlayViewGroup */
    public static class a extends LayoutParams {
        public FPoint a = null;
        public int b = 0;
        public int c = 0;
        public int d = 51;

        public a(int i, int i2, FPoint fPoint, int i3, int i4, int i5) {
            super(i, i2);
            this.a = fPoint;
            this.b = i3;
            this.c = i4;
            this.d = i5;
        }
    }

    public fs(Context context, lj ljVar) {
        super(context);
        try {
            this.b = ljVar;
            this.c = context;
            setBackgroundColor(-1);
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(Context context) {
        int i;
        this.d = new fu(context, this.b);
        this.g = new ft(context, this.b);
        this.h = new fo(context);
        this.i = new fq(context);
        this.j = new ew(context, this.b);
        this.e = new fr(context, this.b);
        this.f = new fp(context, this.b);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        if (this.b.m() != null) {
            addView(this.b.m(), 0, layoutParams);
            i = 1;
        } else {
            i = 0;
        }
        addView(this.h, i, layoutParams);
        addView(this.d, layoutParams);
        addView(this.g, layoutParams);
        addView(this.i, new LayoutParams(-2, -2));
        addView(this.j, new a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        addView(this.e, new a(-2, -2, FPoint.obtain(0.0f, 0.0f), 0, 0, 83));
        addView(this.f, new a(-2, -2, FPoint.obtain(0.0f, 0.0f), 0, 0, 51));
        this.f.setVisibility(8);
        this.b.a(new AMapWidgetListener(this) {
            final /* synthetic */ fs a;

            {
                this.a = r1;
            }

            public void invalidateScaleView() {
                if (this.a.g != null) {
                    this.a.g.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.g.b();
                        }
                    });
                }
            }

            public void invalidateCompassView() {
                if (this.a.f != null) {
                    this.a.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.f.b();
                        }
                    });
                }
            }

            public void invalidateZoomController(final float f) {
                if (this.a.j != null) {
                    this.a.j.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            this.b.a.j.a(f);
                        }
                    });
                }
            }

            public void setFrontViewVisibility(boolean z) {
            }
        });
        try {
            if (!this.b.h().isMyLocationButtonEnabled()) {
                this.e.setVisibility(8);
            }
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImpGLSurfaceView", "locationView gone");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (this.i != null && z && this.b.n()) {
            this.i.a(true);
        }
    }

    public void b(boolean z) {
        if (this.j != null) {
            this.j.a(z);
        }
    }

    public void c(boolean z) {
        if (this.e != null) {
            if (z) {
                this.e.setVisibility(0);
            } else {
                this.e.setVisibility(8);
            }
        }
    }

    public void d(boolean z) {
        if (this.f != null) {
            this.f.a(z);
        }
    }

    public void e(boolean z) {
        if (this.g != null) {
            this.g.a(z);
        }
    }

    public void f(boolean z) {
        if (this.d != null) {
            this.d.setVisibility(z ? 0 : 8);
        }
    }

    public void a(String str, boolean z, int i) {
        if (this.d != null && !TextUtils.isEmpty(str)) {
            this.d.a(str, i);
            this.d.b(z);
        }
    }

    public void a(float f) {
        if (this.j != null) {
            this.j.a(f);
        }
    }

    public void a(int i) {
        if (this.j != null) {
            this.j.a(i);
        }
    }

    public void b(int i) {
        if (this.d != null) {
            this.d.a(i);
            this.d.postInvalidate();
            n();
        }
    }

    private void n() {
        if (this.g != null && this.g.getVisibility() == 0) {
            this.g.postInvalidate();
        }
    }

    public void c(int i) {
        if (this.d != null) {
            this.d.b(i);
            n();
        }
    }

    public void d(int i) {
        if (this.d != null) {
            this.d.c(i);
            n();
        }
    }

    public float e(int i) {
        if (this.d == null) {
            return 0.0f;
        }
        n();
        return this.d.d(i);
    }

    public void a(int i, float f) {
        if (this.d != null) {
            this.d.a(i, f);
            n();
        }
    }

    public void a(ac acVar) {
        this.a = acVar;
    }

    public Point c() {
        if (this.d == null) {
            return null;
        }
        return this.d.c();
    }

    public void g(boolean z) {
        if (this.d != null && z) {
            this.d.a(true);
        } else if (this.d != null) {
            this.d.a(false);
        }
    }

    public boolean d() {
        if (this.d != null) {
            return this.d.e();
        }
        return false;
    }

    public void e() {
        if (this.d != null) {
            this.d.d();
        }
    }

    public fo f() {
        return this.h;
    }

    public fq g() {
        return this.i;
    }

    public fr h() {
        return this.e;
    }

    public fp i() {
        return this.f;
    }

    public fu j() {
        return this.d;
    }

    public void a(CameraPosition cameraPosition) {
        if (!this.b.h().isLogoEnable()) {
            return;
        }
        if (MapsInitializer.isLoadWorldGridMap() && cameraPosition.zoom >= 7.0f && !ee.a(cameraPosition.target.latitude, cameraPosition.target.longitude)) {
            this.d.setVisibility(8);
        } else if (this.b.o() == -1) {
            this.d.setVisibility(0);
        }
    }

    public void k() {
        if (this.j != null) {
            this.j.a();
        }
        if (this.g != null) {
            this.g.a();
        }
        if (this.d != null) {
            this.d.a();
        }
        if (this.e != null) {
            this.e.a();
        }
        if (this.f != null) {
            this.f.a();
        }
        if (this.i != null) {
            this.i.b();
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != null) {
                    if (childAt.getLayoutParams() instanceof a) {
                        a(childAt, (a) childAt.getLayoutParams());
                    } else {
                        a(childAt, childAt.getLayoutParams());
                    }
                }
            }
            this.d.d();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(View view, LayoutParams layoutParams) {
        int[] iArr = new int[2];
        a(view, layoutParams.width, layoutParams.height, iArr);
        if (view instanceof fq) {
            a(view, iArr[0], iArr[1], 20, (this.b.l().y - 80) - iArr[1], 51);
            return;
        }
        a(view, iArr[0], iArr[1], 0, 0, 51);
    }

    private void a(View view, a aVar) {
        int[] iArr = new int[2];
        a(view, aVar.width, aVar.height, iArr);
        if (view instanceof ew) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], getHeight(), aVar.d);
        } else if (view instanceof fr) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], iArr[1], aVar.d);
        } else if (view instanceof fp) {
            a(view, iArr[0], iArr[1], 0, 0, aVar.d);
        } else if (aVar.a != null) {
            IPoint obtain = IPoint.obtain();
            MapConfig mapConfig = this.b.getMapConfig();
            GLMapState c = this.b.c();
            if (!(mapConfig == null || c == null)) {
                FPoint obtain2 = FPoint.obtain();
                c.p20ToScreenPoint(mapConfig.getSX() + ((int) aVar.a.x), mapConfig.getSY() + ((int) aVar.a.y), obtain2);
                obtain.x = (int) obtain2.x;
                obtain.y = (int) obtain2.y;
                obtain2.recycle();
            }
            obtain.x += aVar.b;
            obtain.y += aVar.c;
            a(view, iArr[0], iArr[1], obtain.x, obtain.y, aVar.d);
            obtain.recycle();
        }
    }

    public void a(kn knVar) {
        if (knVar != null) {
            try {
                if (!(this.a != null && this.a.a() && knVar.getTitle() == null && knVar.getSnippet() == null) && knVar.isInfoWindowEnable()) {
                    if (!(this.l == null || this.l.getId().equals(knVar.getId()))) {
                        a_();
                    }
                    if (this.a != null) {
                        this.l = knVar;
                        knVar.a(true);
                        this.p = true;
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    private View b(kn knVar) throws RemoteException {
        Throwable th;
        View a;
        View view = null;
        BasePointOverlay marker;
        if (knVar instanceof cs) {
            marker = new Marker((cs) knVar);
            try {
                if (this.m == null) {
                    this.m = eb.a(this.c, "infowindow_bg.9.png");
                }
            } catch (Throwable th2) {
                gz.c(th2, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
                th2.printStackTrace();
            }
            try {
                if (this.p) {
                    a = this.a.a(marker);
                    if (a == null) {
                        try {
                            a = this.a.b(marker);
                        } catch (Throwable th3) {
                            Throwable th4 = th3;
                            view = a;
                            th2 = th4;
                            gz.c(th2, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                            th2.printStackTrace();
                            return view;
                        }
                    }
                    this.o = a;
                    this.p = false;
                } else {
                    a = this.o;
                }
                if (a != null) {
                    view = a;
                } else if (this.a.a()) {
                    view = this.a.a(marker);
                }
                if (view != null) {
                    if (view.getBackground() == null) {
                        view.setBackground(this.m);
                    }
                }
            } catch (Throwable th5) {
                th2 = th5;
                gz.c(th2, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                th2.printStackTrace();
                return view;
            }
        }
        try {
            if (this.m == null) {
                this.m = eb.a(this.c, "infowindow_bg.9.png");
            }
        } catch (Throwable th22) {
            gz.c(th22, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
            th22.printStackTrace();
        }
        try {
            marker = new GL3DModel((cd) knVar);
            if (this.p) {
                a = this.a.a(marker);
                if (a == null) {
                    try {
                        a = this.a.b(marker);
                    } catch (Throwable th32) {
                        th4 = th32;
                        view = a;
                        th22 = th4;
                        gz.c(th22, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                        th22.printStackTrace();
                        return view;
                    }
                }
                this.o = a;
                this.p = false;
            } else {
                a = this.o;
            }
            if (a != null) {
                view = a;
            } else if (this.a.a()) {
                view = this.a.a(marker);
            }
            if (view.getBackground() == null) {
                view.setBackground(this.m);
            }
        } catch (Throwable th6) {
            th22 = th6;
            gz.c(th22, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
            th22.printStackTrace();
            return view;
        }
        return view;
    }

    public void b() {
        try {
            if (this.l == null || !this.l.k()) {
                if (this.k != null && this.k.getVisibility() == 0) {
                    this.k.setVisibility(8);
                }
            } else if (this.n) {
                int f = this.l.f() + this.l.h();
                int i = (this.l.i() + this.l.g()) + 2;
                View b = b(this.l);
                if (b != null) {
                    a(b, f, i);
                    if (this.k != null) {
                        a aVar = (a) this.k.getLayoutParams();
                        if (aVar != null) {
                            aVar.a = this.l.b();
                            aVar.b = f;
                            aVar.c = i;
                        }
                        onLayout(false, 0, 0, 0, 0);
                        if (this.a.a()) {
                            this.a.a(this.l.getTitle(), this.l.getSnippet());
                        }
                        if (this.k.getVisibility() == 8) {
                            this.k.setVisibility(0);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            gz.c(th, "MapOverlayViewGroup", "redrawInfoWindow");
            th.printStackTrace();
        }
    }

    private void a(View view, int i, int i2) throws RemoteException {
        int i3 = -2;
        if (view != null) {
            int i4;
            if (this.k != null) {
                if (view != this.k) {
                    this.k.clearFocus();
                    removeView(this.k);
                } else {
                    return;
                }
            }
            this.k = view;
            LayoutParams layoutParams = this.k.getLayoutParams();
            this.k.setDrawingCacheEnabled(true);
            this.k.setDrawingCacheQuality(0);
            this.l.l();
            if (layoutParams != null) {
                i4 = layoutParams.width;
                i3 = layoutParams.height;
            } else {
                i4 = -2;
            }
            addView(this.k, new a(i4, i3, this.l.b(), i, i2, 81));
        }
    }

    public void a_() {
        if (this.b != null && this.b.getMainHandler() != null) {
            this.b.getMainHandler().post(new Runnable(this) {
                final /* synthetic */ fs a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.k != null) {
                        this.a.k.clearFocus();
                        this.a.removeView(this.a.k);
                        en.a(this.a.k.getBackground());
                        en.a(this.a.m);
                        this.a.k = null;
                    }
                }
            });
            if (this.l != null) {
                this.l.a(false);
            }
            this.l = null;
        }
    }

    private void a(View view, int i, int i2, int[] iArr) {
        if (view instanceof ListView) {
            View view2 = (View) view.getParent();
            if (view2 != null) {
                iArr[0] = view2.getWidth();
                iArr[1] = view2.getHeight();
            }
        }
        if (i <= 0 || i2 <= 0) {
            view.measure(0, 0);
        }
        if (i == -2) {
            iArr[0] = view.getMeasuredWidth();
        } else if (i == -1) {
            iArr[0] = getMeasuredWidth();
        } else {
            iArr[0] = i;
        }
        if (i2 == -2) {
            iArr[1] = view.getMeasuredHeight();
        } else if (i2 == -1) {
            iArr[1] = getMeasuredHeight();
        } else {
            iArr[1] = i2;
        }
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 & 7;
        int i7 = i5 & 112;
        if (i6 == 5) {
            i3 -= i;
        } else if (i6 == 1) {
            i3 -= i / 2;
        }
        if (i7 == 80) {
            i4 -= i2;
        } else if (i7 == 17) {
            i4 -= i2 / 2;
        } else if (i7 == 16) {
            i4 = (i4 / 2) - (i2 / 2);
        }
        view.layout(i3, i4, i3 + i, i4 + i2);
        if (view instanceof lk) {
            this.b.b(i, i2);
        }
    }

    public void l() {
        a_();
        en.a(this.m);
        k();
        removeAllViews();
        this.o = null;
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.k == null || this.l == null || !en.a(new Rect(this.k.getLeft(), this.k.getTop(), this.k.getRight(), this.k.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return true;
    }

    public void a(Canvas canvas) {
        if (this.k != null && this.l != null) {
            Bitmap drawingCache = this.k.getDrawingCache(true);
            if (drawingCache != null) {
                canvas.drawBitmap(drawingCache, (float) this.k.getLeft(), (float) this.k.getTop(), new Paint());
            }
        }
    }

    public void m() {
    }
}
