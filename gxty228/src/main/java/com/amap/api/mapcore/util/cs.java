package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.util.Log;
import android.view.animation.AnimationUtils;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.Animation.AnimationListener;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.animation.GLAnimationSet;
import com.autonavi.amap.mapcore.animation.GLTransformation;
import com.autonavi.amap.mapcore.animation.GLTranslateAnimation;
import com.autonavi.amap.mapcore.interfaces.IAnimation;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlayImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MarkerDelegateImp */
public class cs extends kn implements ck, IAnimation, IMarkerAction {
    private static int g = 0;
    private boolean A = false;
    private boolean B = true;
    private int C = 5;
    private boolean D = true;
    private boolean E = true;
    private boolean F = false;
    private boolean G = false;
    private boolean H = false;
    private boolean I = true;
    private FPoint J = FPoint.obtain();
    private Point K = new Point();
    private float L;
    private float M;
    private int N = 0;
    private int O = 0;
    private f P;
    private f[] Q = null;
    private boolean R = false;
    private String S;
    private LatLng T;
    private LatLng U;
    private String V;
    private String W;
    private float X = 0.5f;
    private float Y = 1.0f;
    private boolean Z = false;
    float[] a;
    private boolean aa = true;
    private z ab;
    private Object ac;
    private boolean ad = false;
    private List<BitmapDescriptor> ae = new CopyOnWriteArrayList();
    private boolean af = false;
    private boolean ag = false;
    private boolean ah = true;
    private int ai = 0;
    private int aj = 20;
    private boolean ak = false;
    private int al;
    private int am;
    private long an = 0;
    private float ao = Float.MAX_VALUE;
    private float ap = Float.MIN_VALUE;
    private float aq = Float.MIN_VALUE;
    private float ar = Float.MAX_VALUE;
    float[] b;
    Rect c = new Rect(0, 0, 0, 0);
    GLAnimation d;
    GLAnimation e;
    Object f = new Object();
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private float k = 0.0f;
    private float l = 0.0f;
    private boolean m = false;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r;
    private int s;
    private FPoint t = FPoint.obtain();
    private float[] u = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float v = 0.0f;
    private float w = 1.0f;
    private float x = 1.0f;
    private float y = 1.0f;
    private MarkerOptions z;

    private static String a(String str) {
        g++;
        return str + g;
    }

    public void setRotateAngle(float f) {
        this.z.rotateAngle(f);
        this.l = f;
        this.k = (((-f) % 360.0f) + 360.0f) % 360.0f;
        this.j = true;
        r();
    }

    public void destroy(boolean z) {
        try {
            int i;
            this.R = true;
            if (z) {
                remove();
            }
            if (this.ab != null) {
                i = 0;
                while (this.Q != null && i < this.Q.length) {
                    f fVar = this.Q[i];
                    if (fVar != null) {
                        this.ab.a(fVar);
                        this.ab.d().c(fVar.j());
                    }
                    i++;
                }
            }
            i = 0;
            while (this.ae != null && i < this.ae.size()) {
                ((BitmapDescriptor) this.ae.get(i)).recycle();
                i++;
            }
            this.T = null;
            this.ac = null;
            this.Q = null;
        } catch (Throwable th) {
            gz.c(th, "MarkerDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "MarkerDelegateImp destroy");
        }
    }

    synchronized void m() {
        if (this.ae != null) {
            this.ae.clear();
        }
    }

    public synchronized void a(ArrayList<BitmapDescriptor> arrayList) {
        m();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) it.next();
                if (bitmapDescriptor != null) {
                    this.ae.add(bitmapDescriptor);
                }
            }
        }
        if (this.ae.size() > 0) {
            this.N = ((BitmapDescriptor) this.ae.get(0)).getWidth();
            this.O = ((BitmapDescriptor) this.ae.get(0)).getHeight();
        } else {
            this.ae.add(BitmapDescriptorFactory.defaultMarker());
            this.N = ((BitmapDescriptor) this.ae.get(0)).getWidth();
            this.O = ((BitmapDescriptor) this.ae.get(0)).getHeight();
        }
    }

    public cs(MarkerOptions markerOptions, z zVar) {
        this.ab = zVar;
        setMarkerOptions(markerOptions);
    }

    public int n() {
        try {
            return this.N;
        } catch (Throwable th) {
            return 0;
        }
    }

    public int o() {
        try {
            return this.O;
        } catch (Throwable th) {
            return 0;
        }
    }

    public Rect l() {
        if (this.u == null) {
            this.c.set(0, 0, 0, 0);
            return this.c;
        }
        try {
            GLMapState c = this.ab.d().c();
            if (c == null) {
                return new Rect(0, 0, 0, 0);
            }
            int n = n();
            int o = o();
            FPoint obtain = FPoint.obtain();
            if (this.ak) {
                obtain.x = (float) this.al;
                obtain.y = (float) this.am;
            } else {
                c.p20ToScreenPoint(this.r, this.s, obtain);
            }
            Matrix.setIdentityM(this.a, 0);
            Matrix.rotateM(this.a, 0, -this.k, 0.0f, 0.0f, 1.0f);
            if (this.m) {
                Matrix.rotateM(this.a, 0, this.ab.d().getMapConfig().getSC(), 1.0f, 0.0f, 0.0f);
                Matrix.rotateM(this.a, 0, this.ab.d().getMapConfig().getSR(), 0.0f, 0.0f, 1.0f);
            }
            float[] fArr = new float[]{((float) (-n)) * this.X, ((float) o) * this.Y, 0.0f, 1.0f};
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.set((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]), (int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = ((float) n) * (1.0f - this.X);
            this.b[1] = ((float) o) * this.Y;
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = ((float) n) * (1.0f - this.X);
            this.b[1] = ((float) (-o)) * (1.0f - this.Y);
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = ((float) (-n)) * this.X;
            this.b[1] = ((float) (-o)) * (1.0f - this.Y);
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.p = (int) (((float) this.c.centerX()) - obtain.x);
            this.q = (int) (((float) this.c.top) - obtain.y);
            obtain.recycle();
            return this.c;
        } catch (Throwable th) {
            gz.c(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }

    public boolean remove() {
        r();
        this.aa = false;
        if (this.ab != null) {
            return this.ab.a((cn) this);
        }
        return false;
    }

    private void r() {
        if (this.ab.d() != null) {
            this.ab.d().setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        if (!this.ak || this.t == null) {
            return this.T;
        }
        DPoint obtain = DPoint.obtain();
        IPoint obtain2 = IPoint.obtain();
        p();
        this.ab.d().a(this.t.x, this.t.y, obtain2);
        GLMapState.geo2LonLat(obtain2.x, obtain2.y, obtain);
        LatLng latLng = new LatLng(obtain.y, obtain.x);
        obtain2.recycle();
        obtain.recycle();
        return latLng;
    }

    public String getId() {
        if (this.S == null) {
            this.S = a("Marker");
        }
        return this.S;
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            gz.c(new AMapException("非法坐标值 latlng is null"), "setPosition", "Marker");
            return;
        }
        this.T = latLng;
        IPoint obtain = IPoint.obtain();
        if (this.af) {
            try {
                double[] a = kd.a(latLng.longitude, latLng.latitude);
                this.U = new LatLng(a[1], a[0]);
                GLMapState.lonlat2Geo(a[0], a[1], obtain);
            } catch (Throwable th) {
                this.U = latLng;
            }
        } else {
            GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, obtain);
        }
        this.r = obtain.x;
        this.s = obtain.y;
        this.ak = false;
        p();
        r();
        this.j = true;
        obtain.recycle();
    }

    public void setTitle(String str) {
        this.V = str;
        r();
        this.z.title(str);
        dv.a().a(this.T, this.V, this.W);
    }

    public String getTitle() {
        return this.V;
    }

    public void setSnippet(String str) {
        this.W = str;
        r();
        this.z.snippet(str);
    }

    public String getSnippet() {
        return this.W;
    }

    public void setDraggable(boolean z) {
        this.Z = z;
        this.z.draggable(z);
        r();
    }

    public synchronized void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null) {
            try {
                if (this.ae != null) {
                    a((ArrayList) arrayList);
                    this.ag = false;
                    this.h = false;
                    this.F = false;
                    r();
                    this.j = true;
                }
            } catch (Throwable th) {
                gz.c(th, "MarkerDelegateImp", "setIcons");
                th.printStackTrace();
            }
        }
    }

    public synchronized ArrayList<BitmapDescriptor> getIcons() {
        ArrayList<BitmapDescriptor> arrayList;
        if (this.ae == null || this.ae.size() <= 0) {
            arrayList = null;
        } else {
            ArrayList<BitmapDescriptor> arrayList2 = new ArrayList();
            for (BitmapDescriptor add : this.ae) {
                arrayList2.add(add);
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                if (this.ae != null) {
                    synchronized (this) {
                        this.ae.clear();
                        this.ae.add(bitmapDescriptor);
                        this.F = false;
                        this.ag = false;
                        this.h = false;
                        r();
                        this.j = true;
                        this.N = bitmapDescriptor.getWidth();
                        this.O = bitmapDescriptor.getHeight();
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "MarkerDelegateImp", "setIcon");
                th.printStackTrace();
            }
        }
    }

    private void s() {
        try {
            this.u[4] = this.P.b();
            this.u[5] = this.P.d();
            this.u[13] = this.P.c();
            this.u[14] = this.P.d();
            this.u[22] = this.P.c();
            this.u[23] = this.P.a();
            this.u[31] = this.P.b();
            this.u[32] = this.P.a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isDraggable() {
        return this.Z;
    }

    public boolean isRemoved() {
        try {
            return !this.ab.c(this);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void showInfoWindow() {
        if (this.aa && isInfoWindowEnable()) {
            this.ab.a((kn) this);
            r();
        }
    }

    public void hideInfoWindow() {
        if (isInfoWindowShown()) {
            this.ab.b((cn) this);
            r();
            this.i = false;
        }
        this.j = false;
    }

    public void a(boolean z) {
        this.i = z;
        this.j = true;
    }

    public boolean isInfoWindowShown() {
        return this.i;
    }

    public void setVisible(boolean z) {
        if (this.aa != z) {
            this.z.visible(z);
            this.aa = z;
            if (!z) {
                this.H = false;
                if (isInfoWindowShown()) {
                    this.ab.b((cn) this);
                }
            }
            r();
        }
    }

    public boolean isVisible() {
        return this.aa;
    }

    public void setAnchor(float f, float f2) {
        if (this.X != f || this.Y != f2) {
            this.z.anchor(f, f2);
            this.X = f;
            this.Y = f2;
            this.j = true;
            r();
        }
    }

    public float getAnchorU() {
        return this.X;
    }

    public float getAnchorV() {
        return this.Y;
    }

    public boolean equalsRemote(IOverlayImage iOverlayImage) throws RemoteException {
        if (equals(iOverlayImage) || iOverlayImage.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean p() {
        try {
            if (this.ab == null || this.ab.d() == null || this.ab.d().c() == null) {
                return false;
            }
            if (this.t == null) {
                this.t = FPoint.obtain();
            }
            if (this.ak) {
                IPoint obtain = IPoint.obtain();
                this.ab.d().a(this.al, this.am, obtain);
                this.r = obtain.x;
                this.s = obtain.y;
                obtain.recycle();
                this.ab.d().a(this.r, this.s, this.t);
            } else {
                this.ab.d().a(this.r, this.s, this.t);
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private void a(lj ljVar, float f, int i, int i2) throws RemoteException {
        float f2 = ((float) ((int) (this.w * ((float) i)))) * f;
        float f3 = ((float) ((int) (this.x * ((float) i2)))) * f;
        float f4 = this.t.x;
        float f5 = this.t.y;
        float sc = ljVar.getMapConfig().getSC();
        float f6 = this.k;
        if (this.m) {
            f6 -= ljVar.getMapConfig().getSR();
            sc = 0.0f;
        }
        float f7 = this.y;
        if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        if (f7 > 1.0f) {
            f7 = 1.0f;
        }
        this.u[0] = f4 - (this.X * f2);
        this.u[1] = ((1.0f - this.Y) * f3) + f5;
        this.u[2] = f4;
        this.u[3] = f5;
        this.u[6] = f6;
        this.u[7] = sc;
        this.u[8] = f7;
        this.u[9] = ((1.0f - this.X) * f2) + f4;
        this.u[10] = ((1.0f - this.Y) * f3) + f5;
        this.u[11] = f4;
        this.u[12] = f5;
        this.u[15] = f6;
        this.u[16] = sc;
        this.u[17] = f7;
        this.u[18] = ((1.0f - this.X) * f2) + f4;
        this.u[19] = f5 - (this.Y * f3);
        this.u[20] = f4;
        this.u[21] = f5;
        this.u[24] = f6;
        this.u[25] = sc;
        this.u[26] = f7;
        this.u[27] = f4 - (f2 * this.X);
        this.u[28] = f5 - (f3 * this.Y);
        this.u[29] = f4;
        this.u[30] = f5;
        this.u[33] = f6;
        this.u[34] = sc;
        this.u[35] = f7;
        if (this.ae != null && this.ae.size() > 0) {
            this.ai++;
            if (this.ai >= this.aj * this.ae.size()) {
                this.ai = 0;
            }
            if (this.aj == 0) {
                this.aj = 1;
            }
            this.P = this.Q[this.ai / this.aj];
            if (!this.ah) {
                r();
            }
        }
    }

    public void a(lj ljVar, float[] fArr, int i, float f) {
        Object obj = null;
        if (this.R || ((this.T == null && !this.ak) || this.ae == null)) {
            obj = 1;
        }
        if (obj == null) {
            try {
                if (!this.h) {
                    this.an = System.currentTimeMillis();
                    this.h = true;
                }
                if (this.ak && this.E) {
                    IPoint obtain = IPoint.obtain();
                    ljVar.a(this.al, this.am, obtain);
                    this.r = obtain.x;
                    this.s = obtain.y;
                    obtain.recycle();
                }
                this.t.x = (float) (this.r - ljVar.getMapConfig().getSX());
                FPoint fPoint;
                if (this.t.x > 1.34217728E8f) {
                    fPoint = this.t;
                    fPoint.x -= 2.68435456E8f;
                } else if (this.t.x < -1.34217728E8f) {
                    fPoint = this.t;
                    fPoint.x += 2.68435456E8f;
                }
                this.t.y = (float) (this.s - ljVar.getMapConfig().getSY());
                int n = n();
                int o = o();
                t();
                a(ljVar, f, n, o);
                if (!(this.F && this.ah)) {
                    s();
                    this.F = true;
                }
                a(fArr, i);
                if (this.j && isInfoWindowShown()) {
                    this.ab.d().j();
                    if (System.currentTimeMillis() - this.an > ((long) 1000)) {
                        this.j = false;
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "MarkerDelegateImp", "drawMarker");
            }
        }
    }

    private void a(float[] fArr, int i) {
        if (this.Q != null && this.Q.length > 0) {
            System.arraycopy(this.u, 0, fArr, i, this.u.length);
        }
    }

    public void a(lj ljVar) {
        if (!this.ag) {
            synchronized (this) {
                try {
                    int f;
                    if (this.Q != null) {
                        for (f fVar : this.Q) {
                            if (fVar != null) {
                                this.ab.a(fVar);
                            }
                        }
                    }
                    this.Q = null;
                    if (this.ae != null) {
                        this.Q = new f[this.ae.size()];
                        Object obj = VERSION.SDK_INT >= 12 ? 1 : null;
                        int i = 0;
                        for (BitmapDescriptor bitmapDescriptor : this.ae) {
                            f a;
                            if (obj != null) {
                                a = ljVar.a(bitmapDescriptor);
                                f = a != null ? a.f() : 0;
                            } else {
                                a = null;
                                f = 0;
                            }
                            if (a == null) {
                                a = new f(bitmapDescriptor, f);
                            }
                            if (f == 0) {
                                Bitmap bitmap = bitmapDescriptor.getBitmap();
                                if (!(bitmap == null || bitmap.isRecycled())) {
                                    this.N = bitmap.getWidth();
                                    this.O = bitmap.getHeight();
                                    f = this.ab.d().e();
                                    if (f == 0) {
                                        f = u();
                                        a.a(f);
                                        if (obj != null) {
                                            ljVar.a(a);
                                        }
                                        en.b(f, bitmap, false);
                                    } else {
                                        if (this.ab.a(bitmap, a)) {
                                            en.a(f, bitmap, (int) (a.b() * 512.0f), (int) (a.a() * 1024.0f));
                                            a.a(f);
                                        } else {
                                            f = u();
                                            en.b(f, bitmap, false);
                                            a.a(f);
                                        }
                                        if (obj != null) {
                                            ljVar.a(a);
                                        }
                                    }
                                }
                            }
                            a.g();
                            this.Q[i] = a;
                            i++;
                        }
                        if (this.ae.size() == 1) {
                            this.ah = true;
                        } else {
                            this.ah = false;
                        }
                        this.F = false;
                        this.ag = true;
                    }
                    p();
                } catch (Throwable th) {
                    gz.c(th, "MarkerDelegateImp", "loadtexture");
                }
            }
        }
    }

    private void t() {
        if (this.I || this.d == null || this.d.hasEnded()) {
            this.w = 1.0f;
            this.x = 1.0f;
            this.I = true;
            if (this.ae != null && this.ae.size() == 1) {
                this.ah = true;
                return;
            }
            return;
        }
        r();
        synchronized (this.f) {
            GLTransformation gLTransformation = new GLTransformation();
            this.d.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation);
            if (gLTransformation != null) {
                if (!(Double.isNaN(gLTransformation.scaleX) || Double.isNaN(gLTransformation.scaleY))) {
                    this.w = (float) gLTransformation.scaleX;
                    this.x = (float) gLTransformation.scaleY;
                }
                if (!Double.isNaN(gLTransformation.rotate)) {
                    setRotateAngle((float) gLTransformation.rotate);
                }
                if (!(Double.isNaN(gLTransformation.x) || Double.isNaN(gLTransformation.y))) {
                    double d = gLTransformation.x;
                    double d2 = gLTransformation.y;
                    if (this.ak) {
                        IPoint obtain = IPoint.obtain();
                        this.ab.d().a((int) d, (int) d2, obtain);
                        a(obtain.x, obtain.y);
                        obtain.recycle();
                        this.ak = true;
                    } else {
                        a((int) d, (int) d2);
                    }
                }
                if (!Double.isNaN(gLTransformation.alpha)) {
                    this.y = (float) gLTransformation.alpha;
                }
            }
        }
        this.j = true;
        this.ah = false;
    }

    private int u() {
        int[] iArr = new int[]{0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean a() {
        return this.ah;
    }

    public void setPeriod(int i) {
        if (i <= 1) {
            this.aj = 1;
        } else {
            this.aj = i;
        }
    }

    public void setObject(Object obj) {
        this.ac = obj;
    }

    public Object getObject() {
        return this.ac;
    }

    public void setPerspective(boolean z) {
        this.ad = z;
    }

    public boolean isPerspective() {
        return this.ad;
    }

    public int d() {
        int i = 0;
        try {
            if (this.ae != null && this.ae.size() > 0) {
                i = this.P.f();
            }
        } catch (Throwable th) {
        }
        return i;
    }

    public int getPeriod() {
        return this.aj;
    }

    public LatLng c() {
        try {
            if (!this.ak) {
                return this.af ? this.U : this.T;
            } else {
                DPoint obtain = DPoint.obtain();
                this.ab.d().b(this.al, this.am, obtain);
                LatLng latLng = new LatLng(obtain.y, obtain.y);
                obtain.recycle();
                return latLng;
            }
        } catch (Throwable th) {
            gz.c(th, "MarkerDelegateImp", "getRealPosition");
            return null;
        }
    }

    public void set2Top() {
        this.ab.a((ck) this);
    }

    public void setFlat(boolean z) throws RemoteException {
        this.m = z;
        r();
        this.z.setFlat(z);
    }

    public boolean isFlat() {
        return this.m;
    }

    public float getRotateAngle() {
        r();
        return this.l;
    }

    public int f() {
        return this.n;
    }

    public int g() {
        return this.o;
    }

    public void setPositionByPixels(int i, int i2) {
        this.al = i;
        this.am = i2;
        this.ak = true;
        p();
        r();
        this.j = true;
    }

    public int h() {
        return this.p;
    }

    public int i() {
        return this.q;
    }

    public FPoint b() {
        return this.t;
    }

    public boolean j() {
        return this.ak;
    }

    public void setZIndex(float f) {
        this.v = f;
        this.z.zIndex(f);
        if (this.H) {
            this.H = false;
            this.ab.a();
        }
        this.ab.g();
    }

    public float getZIndex() {
        return this.v;
    }

    public boolean k() {
        if (this.ak) {
            return true;
        }
        if (this.t != null) {
            if (!this.I) {
                return true;
            }
            this.K.x = this.r;
            this.K.y = this.s;
            if (this.ab.d().getMapConfig().getGeoRectangle().contains(this.r, this.s)) {
                return true;
            }
            v();
            this.J.x = this.t.x;
            this.J.y = this.t.y;
            FPoint[] mapRect = this.ab.d().getMapConfig().getMapRect();
            a(mapRect);
            if (en.a(this.J, mapRect)) {
                return true;
            }
        }
        return false;
    }

    public void setGeoPoint(IPoint iPoint) {
        this.ak = false;
        a(iPoint.x, iPoint.y);
    }

    private void a(int i, int i2) {
        this.r = i;
        this.s = i2;
        DPoint obtain = DPoint.obtain();
        GLMapState.geo2LonLat(this.r, this.s, obtain);
        this.T = new LatLng(obtain.y, obtain.x, false);
        if (!(this.ab == null || this.ab.d() == null)) {
            this.t.x = (float) (this.r - this.ab.d().getMapConfig().getSX());
            this.t.y = (float) (this.s - this.ab.d().getMapConfig().getSY());
        }
        obtain.recycle();
        r();
    }

    public IPoint getGeoPoint() {
        IPoint obtain = IPoint.obtain();
        if (this.ak) {
            this.ab.d().a(this.al, this.am, obtain);
        } else {
            obtain.set(this.r, this.s);
        }
        return obtain;
    }

    public void setAnimation(Animation animation) {
        IAnimation q = q();
        if (q != null) {
            q.setAnimation(animation == null ? null : animation.glAnimation);
        }
    }

    public void setAnimation(GLAnimation gLAnimation) {
        if (gLAnimation != null) {
            this.e = gLAnimation;
        }
    }

    public boolean startAnimation() {
        if (this.e != null) {
            synchronized (this.f) {
                if (this.e instanceof GLAnimationSet) {
                    GLAnimationSet gLAnimationSet = (GLAnimationSet) this.e;
                    for (GLAnimation gLAnimation : gLAnimationSet.getAnimations()) {
                        a(gLAnimation);
                        gLAnimation.setDuration(gLAnimationSet.getDuration());
                    }
                } else {
                    a(this.e);
                }
                this.I = false;
                this.d = this.e;
                this.d.start();
            }
            r();
        }
        return false;
    }

    private void a(GLAnimation gLAnimation) {
        if (gLAnimation instanceof GLTranslateAnimation) {
            if (this.ak) {
                this.T = getPosition();
                setPosition(this.T);
                this.ak = true;
            }
            if (this.ak) {
                ((GLTranslateAnimation) gLAnimation).mFromXDelta = (double) this.al;
                ((GLTranslateAnimation) gLAnimation).mFromYDelta = (double) this.am;
                IPoint obtain = IPoint.obtain();
                this.ab.d().b(((GLTranslateAnimation) gLAnimation).mToYDelta, ((GLTranslateAnimation) gLAnimation).mToXDelta, obtain);
                ((GLTranslateAnimation) gLAnimation).mToXDelta = (double) obtain.x;
                ((GLTranslateAnimation) gLAnimation).mToYDelta = (double) obtain.y;
                obtain.recycle();
                return;
            }
            ((GLTranslateAnimation) gLAnimation).mFromXDelta = (double) this.r;
            ((GLTranslateAnimation) gLAnimation).mFromYDelta = (double) this.s;
            IPoint obtain2 = IPoint.obtain();
            GLMapState.lonlat2Geo(((GLTranslateAnimation) gLAnimation).mToXDelta, ((GLTranslateAnimation) gLAnimation).mToYDelta, obtain2);
            ((GLTranslateAnimation) gLAnimation).mToXDelta = (double) obtain2.x;
            ((GLTranslateAnimation) gLAnimation).mToYDelta = (double) obtain2.y;
            obtain2.recycle();
        }
    }

    public void setAnimationListener(AnimationListener animationListener) {
        if (this.e != null) {
            this.e.setAnimationListener(animationListener);
        }
    }

    public IAnimation q() {
        return this;
    }

    public IMarkerAction getIMarkerAction() {
        return this;
    }

    public float getAlpha() {
        return this.y;
    }

    public void setAlpha(float f) {
        this.y = f;
        this.z.alpha(f);
    }

    public int getDisplayLevel() {
        return this.C;
    }

    public MarkerOptions getOptions() {
        return this.z;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        if (markerOptions != null) {
            this.z = markerOptions;
            this.T = this.z.getPosition();
            IPoint obtain = IPoint.obtain();
            this.af = this.z.isGps();
            if (this.z.getPosition() != null) {
                if (this.af) {
                    try {
                        double[] a = kd.a(this.z.getPosition().longitude, this.z.getPosition().latitude);
                        this.U = new LatLng(a[1], a[0]);
                        GLMapState.lonlat2Geo(a[0], a[1], obtain);
                    } catch (Throwable th) {
                        gz.c(th, "MarkerDelegateImp", "create");
                        this.U = this.z.getPosition();
                    }
                } else {
                    GLMapState.lonlat2Geo(this.T.longitude, this.T.latitude, obtain);
                }
            }
            this.r = obtain.x;
            this.s = obtain.y;
            this.X = this.z.getAnchorU();
            this.Y = this.z.getAnchorV();
            this.n = this.z.getInfoWindowOffsetX();
            this.o = this.z.getInfoWindowOffsetY();
            this.aj = this.z.getPeriod();
            this.v = this.z.getZIndex();
            this.G = this.z.isBelowMaskLayer();
            p();
            a(this.z.getIcons());
            this.aa = this.z.isVisible();
            this.W = this.z.getSnippet();
            this.V = this.z.getTitle();
            this.Z = this.z.isDraggable();
            this.S = getId();
            this.ad = this.z.isPerspective();
            this.m = this.z.isFlat();
            this.G = this.z.isBelowMaskLayer();
            this.y = this.z.getAlpha();
            setRotateAngle(this.z.getRotateAngle());
            this.C = this.z.getDisplayLevel();
            this.A = this.z.isInfoWindowAutoOverturn();
            this.B = this.z.isInfoWindowEnable();
            this.a = new float[16];
            this.b = new float[4];
            obtain.recycle();
            dv.a().a(this.T, this.V, this.W);
        }
    }

    public boolean isClickable() {
        return this.D;
    }

    public boolean isInfoWindowAutoOverturn() {
        return this.A;
    }

    public boolean isInfoWindowEnable() {
        return this.B;
    }

    public void b(boolean z) {
        this.H = z;
    }

    public boolean e() {
        return this.H;
    }

    public void setInfoWindowEnable(boolean z) {
        this.B = z;
        if (!z) {
            hideInfoWindow();
        }
        this.z.infoWindowEnable(z);
    }

    public void setAutoOverturnInfoWindow(boolean z) {
        this.A = z;
        this.z.autoOverturnInfoWindow(z);
    }

    public void setClickable(boolean z) {
        this.D = z;
    }

    public void setDisplayLevel(int i) {
        this.C = i;
        this.z.displayLevel(i);
    }

    public void setFixingPointEnable(boolean z) {
        this.E = z;
        if (!z) {
            boolean z2 = false;
            if (this.ak) {
                z2 = true;
            }
            this.T = getPosition();
            setPosition(this.T);
            if (z2) {
                this.ak = true;
            }
        } else if (this.ak && this.T != null) {
            FPoint obtain = FPoint.obtain();
            this.ab.d().c().p20ToScreenPoint(this.r, this.s, obtain);
            this.al = (int) obtain.x;
            this.am = (int) obtain.y;
            obtain.recycle();
        }
    }

    public void setPositionNotUpdate(LatLng latLng) {
        setPosition(latLng);
    }

    public void setRotateAngleNotUpdate(float f) {
    }

    public void setBelowMaskLayer(boolean z) {
        this.G = z;
    }

    private void a(FPoint[] fPointArr) {
        if (fPointArr != null) {
            v();
            if (this.L > 0.0f && this.M > 0.0f && fPointArr.length == 4) {
                this.ao = Math.min(fPointArr[0].x, fPointArr[1].x);
                this.ao = Math.min(this.ao, fPointArr[2].x);
                this.ao = Math.min(this.ao, fPointArr[3].x);
                this.ap = Math.max(fPointArr[0].x, fPointArr[1].x);
                this.ap = Math.max(this.ap, fPointArr[2].x);
                this.ap = Math.max(this.ap, fPointArr[3].x);
                this.ar = Math.min(fPointArr[0].y, fPointArr[1].y);
                this.ar = Math.min(this.ar, fPointArr[2].y);
                this.ar = Math.min(this.ar, fPointArr[3].y);
                this.aq = Math.max(fPointArr[0].y, fPointArr[1].y);
                this.aq = Math.max(this.aq, fPointArr[2].y);
                this.aq = Math.max(this.aq, fPointArr[3].y);
                if (this.t.x < (this.ao + this.ap) / ((float) 2)) {
                    this.J.x = this.t.x + (this.L / 2.0f);
                } else {
                    this.J.x = this.t.x - (this.L / 2.0f);
                }
                if (this.t.y < (this.ar + this.aq) / ((float) 2)) {
                    this.J.y = this.t.y;
                    return;
                }
                this.J.y = this.t.y - this.M;
            }
        }
    }

    private void v() {
        if (this.ab.d() != null && this.ab.d().getMapConfig() != null) {
            this.L = this.ab.d().getMapConfig().getMapPerPixelUnitLength() * ((float) n());
            this.M = this.ab.d().getMapConfig().getMapPerPixelUnitLength() * ((float) o());
        }
    }
}
