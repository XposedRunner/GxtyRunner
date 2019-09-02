package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.Animation.AnimationListener;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.animation.GLTransformation;
import com.autonavi.amap.mapcore.interfaces.IInfoWindowManager;
import com.autonavi.amap.mapcore.interfaces.IMarker;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import java.nio.FloatBuffer;

/* compiled from: PopupOverlay */
public class cx implements ab, cm, IInfoWindowManager {
    private boolean A = true;
    private Bitmap B = null;
    private Bitmap C = null;
    private Bitmap D = null;
    private Bitmap E = null;
    private boolean F = false;
    private boolean G = false;
    private GLAnimation H;
    private GLAnimation I;
    private boolean J = false;
    private boolean K = true;
    lj a = null;
    float[] b = new float[12];
    a c;
    float[] d = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    long e = 0;
    ac f;
    private Context g;
    private kn h;
    private boolean i = false;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private FPoint n;
    private FloatBuffer o = null;
    private String p;
    private boolean q = true;
    private FloatBuffer r;
    private float s = 0.5f;
    private float t = 1.0f;
    private boolean u;
    private Bitmap v;
    private Bitmap w;
    private Rect x = new Rect();
    private float y = 0.0f;
    private int z;

    /* compiled from: PopupOverlay */
    static class a extends da {
        int a;
        int b;
        int c;

        a(String str) {
            if (a(str)) {
                this.a = c("aMVP");
                this.b = b("aVertex");
                this.c = b("aTextureCoord");
            }
        }
    }

    public boolean d() {
        return this.A;
    }

    public void a(boolean z) {
        this.A = z;
    }

    public synchronized void a(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                this.B = bitmap;
            }
        }
    }

    private synchronized void c(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

    private synchronized void d(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                c(this.C);
                this.C = bitmap;
            }
        }
    }

    private synchronized void e(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                c(this.D);
                this.D = bitmap;
            }
        }
    }

    private synchronized void f(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                c(this.E);
                this.E = bitmap;
            }
        }
    }

    private synchronized Bitmap k() {
        return this.B;
    }

    private synchronized Bitmap l() {
        return this.D;
    }

    public cx(lj ljVar, Context context) {
        this.g = context;
        this.a = ljVar;
        this.p = getId();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e() {
        /*
        r2 = this;
        r1 = 0;
        monitor-enter(r2);	 Catch:{ Throwable -> 0x001c }
        r0 = r2.v;	 Catch:{ all -> 0x0019 }
        if (r0 == 0) goto L_0x0016;
    L_0x0006:
        r0 = r2.v;	 Catch:{ all -> 0x0019 }
        r0 = r0.isRecycled();	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x0016;
    L_0x000e:
        r0 = r2.v;	 Catch:{ all -> 0x0019 }
        r0 = r0.getWidth();	 Catch:{ all -> 0x0019 }
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
    L_0x0015:
        return r0;
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
        r0 = r1;
        goto L_0x0015;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
        throw r0;	 Catch:{ Throwable -> 0x001c }
    L_0x001c:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cx.e():int");
    }

    public int f() {
        int i = 0;
        try {
            if (!(this.v == null || this.v.isRecycled())) {
                i = this.v.getHeight();
            }
        } catch (Throwable th) {
        }
        return i;
    }

    public String getId() {
        if (this.p == null) {
            this.p = "PopupOverlay";
        }
        return this.p;
    }

    public synchronized void b(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    if (this.v == null || this.v.hashCode() != bitmap.hashCode()) {
                        if (this.v != null) {
                            if (this.B == null && this.C == null && this.D == null && this.E == null) {
                                c(this.w);
                                this.w = this.v;
                            } else if (!g(this.v)) {
                                c(this.w);
                                this.w = this.v;
                            }
                        }
                        this.G = false;
                        this.v = bitmap;
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    private boolean g(Bitmap bitmap) {
        if (this.B != null && bitmap.hashCode() == this.B.hashCode()) {
            return true;
        }
        if (this.D != null && bitmap.hashCode() == this.D.hashCode()) {
            return true;
        }
        if (this.C != null && bitmap.hashCode() == this.C.hashCode()) {
            return true;
        }
        if (this.E == null || bitmap.hashCode() != this.E.hashCode()) {
            return false;
        }
        return true;
    }

    public void setVisible(boolean z) {
        if (!this.q && z) {
            this.u = true;
        }
        this.q = z;
    }

    public boolean isVisible() {
        return this.q;
    }

    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        if (equals(iOverlay) || iOverlay.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean a(int i, int i2) {
        GLMapState c = this.a.c();
        if (this.n == null || c == null) {
            return false;
        }
        IPoint obtain = IPoint.obtain();
        MapConfig mapConfig = this.a.getMapConfig();
        if (!(mapConfig == null || c == null)) {
            FPoint obtain2 = FPoint.obtain();
            c.p20ToScreenPoint(mapConfig.getSX() + ((int) this.n.x), mapConfig.getSY() + ((int) this.n.y), obtain2);
            obtain.x = (int) obtain2.x;
            obtain.y = (int) obtain2.y;
            obtain2.recycle();
        }
        int e = e();
        int f = f();
        int i3 = (int) (((float) (obtain.x + this.j)) - (((float) e) * this.s));
        int i4 = (int) (((float) (obtain.y + this.k)) + (((float) f) * (1.0f - this.t)));
        obtain.recycle();
        if (i3 - e > i || i3 < (-e) * 2 || i4 < (-f) * 2 || i4 - f > i2) {
            return false;
        }
        if (this.v == null) {
            return false;
        }
        e = this.v.getWidth();
        int height = this.v.getHeight();
        if (this.r == null) {
            this.r = en.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
        }
        f = (int) ((((double) (1.0f - this.y)) * 0.5d) * ((double) e));
        this.b[0] = (float) (i3 + f);
        this.x.left = i3 + f;
        this.b[1] = (float) (i2 - i4);
        this.b[2] = 0.0f;
        this.b[3] = (float) ((i3 + e) - f);
        this.b[4] = (float) (i2 - i4);
        this.x.top = i4 - height;
        this.b[5] = 0.0f;
        this.b[6] = (float) ((i3 + e) - f);
        this.x.right = e + i3;
        this.b[7] = (float) ((i2 - i4) + height);
        this.x.bottom = i4;
        this.b[8] = 0.0f;
        this.b[9] = (float) (f + i3);
        this.b[10] = (float) (height + (i2 - i4));
        this.b[11] = 0.0f;
        if (this.o == null) {
            this.o = en.a(this.b);
        } else {
            this.o = en.a(this.b, this.o);
        }
        return true;
    }

    public void g() {
        this.c = new a("texture.glsl");
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null && i != 0) {
            if (this.c == null) {
                g();
            }
            this.c.a();
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
            GLES20.glEnableVertexAttribArray(this.c.b);
            GLES20.glVertexAttribPointer(this.c.b, 3, 5126, false, 12, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.c.c);
            GLES20.glVertexAttribPointer(this.c.c, 2, 5126, false, 8, floatBuffer2);
            GLES20.glUniformMatrix4fv(this.c.a, 1, false, this.d, 0);
            GLES20.glDrawArrays(6, 0, 4);
            GLES20.glDisableVertexAttribArray(this.c.b);
            GLES20.glDisableVertexAttribArray(this.c.c);
            GLES20.glBindTexture(3553, 0);
            GLES20.glUseProgram(0);
            GLES20.glDisable(3042);
        }
    }

    public void b(int i, int i2) {
        if (this.q && this.n != null && this.v != null) {
            h();
            if (this.v.isRecycled()) {
            }
            if (!(this.G || this.v.isRecycled())) {
                try {
                    if (this.z != 0) {
                        GLES20.glDeleteTextures(1, new int[]{this.z}, 0);
                    } else {
                        this.z = n();
                    }
                    if (!(this.v == null || this.v.isRecycled())) {
                        en.b(this.z, this.v, false);
                        this.G = true;
                    }
                } catch (Throwable th) {
                    gz.c(th, "PopupOverlay", "drawMarker");
                    th.printStackTrace();
                    return;
                }
            }
            m();
            if (a(i, i2)) {
                Matrix.setIdentityM(this.d, 0);
                Matrix.orthoM(this.d, 0, 0.0f, (float) i, 0.0f, (float) i2, 1.0f, -1.0f);
                a(this.z, this.o, this.r);
                if (this.u) {
                    this.u = false;
                    r();
                }
            }
        }
    }

    protected void h() {
        synchronized (this) {
            long c;
            if (this.f != null) {
                if (this.h instanceof cs) {
                    c = this.f.c(new Marker((IMarker) this.h));
                } else {
                    c = this.f.c(new GL3DModel((cd) this.h));
                }
                if (c <= 0) {
                    c = Long.MAX_VALUE;
                } else if (c <= 100) {
                    c = 100;
                }
            } else {
                c = 0;
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e > c) {
            if (this.e != 0) {
                try {
                    a(this.h);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            this.e = currentTimeMillis;
        }
    }

    private void m() {
        GLTransformation gLTransformation;
        if (!this.K && this.I != null && !this.I.hasEnded()) {
            this.J = true;
            gLTransformation = new GLTransformation();
            this.I.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation);
            if (gLTransformation != null && !Double.isNaN(gLTransformation.scaleX) && !Double.isNaN(gLTransformation.scaleY)) {
                this.y = (float) gLTransformation.scaleX;
            }
        } else if (this.H == null || this.H.hasEnded()) {
            this.y = 1.0f;
            this.J = false;
        } else {
            this.K = false;
            this.J = true;
            this.j = this.l;
            this.k = this.m;
            gLTransformation = new GLTransformation();
            this.H.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation);
            if (gLTransformation != null && !Double.isNaN(gLTransformation.scaleX) && !Double.isNaN(gLTransformation.scaleY)) {
                this.y = (float) gLTransformation.scaleX;
            }
        }
    }

    public void setInfoWindowAnimation(Animation animation, AnimationListener animationListener) {
    }

    public void setInfoWindowAppearAnimation(Animation animation) {
        if (this.I == null || !this.I.equals(animation.glAnimation)) {
            this.H = animation.glAnimation;
            return;
        }
        try {
            this.H = animation.glAnimation.clone();
        } catch (Throwable th) {
            gz.c(th, "PopupOverlay", "setInfoWindowDisappearAnimation");
        }
    }

    public void setInfoWindowBackColor(int i) {
    }

    public void setInfoWindowBackEnable(boolean z) {
    }

    public void setInfoWindowBackScale(float f, float f2) {
    }

    public void setInfoWindowDisappearAnimation(Animation animation) {
        if (this.H == null || !this.H.equals(animation.glAnimation)) {
            this.I = animation.glAnimation;
            return;
        }
        try {
            this.I = animation.glAnimation.clone();
        } catch (Throwable th) {
            gz.c(th, "PopupOverlay", "setInfoWindowDisappearAnimation");
        }
    }

    public void setInfoWindowMovingAnimation(Animation animation) {
    }

    public void startAnimation() {
    }

    private int n() {
        int[] iArr = new int[]{0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public void c(int i, int i2) throws RemoteException {
        if (this.J) {
            this.l = i;
            this.m = i2;
            return;
        }
        this.j = i;
        this.k = i2;
        this.l = i;
        this.m = i2;
    }

    public void setZIndex(float f) {
    }

    public float getZIndex() {
        return 0.0f;
    }

    public boolean a() {
        return false;
    }

    public void remove() throws RemoteException {
    }

    private void b(boolean z) {
        if (z) {
            b(k());
        } else {
            b(l());
        }
    }

    private void c(final boolean z) {
        if (this.I != null) {
            this.K = false;
            this.J = true;
            this.I.startNow();
            this.I.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ cx b;

                public void onAnimationStart() {
                }

                public void onAnimationEnd() {
                    if (this.b.H != null) {
                        this.b.J = true;
                        this.b.H.startNow();
                        this.b.b(z);
                    }
                }
            });
        } else if (this.H != null) {
            this.J = true;
            this.H.startNow();
            b(z);
        } else {
            b(z);
        }
    }

    private void o() {
        if (!this.A || this.v == null) {
            b(l());
        } else {
            c(false);
        }
        a(false);
    }

    private void p() {
        if (this.A || this.v == null) {
            b(k());
        } else {
            c(true);
        }
        a(true);
    }

    public void destroy() {
        if (this.i) {
            try {
                remove();
                q();
                if (this.r != null) {
                    this.r.clear();
                    this.r = null;
                }
                if (this.o != null) {
                    this.o.clear();
                    this.o = null;
                }
                this.n = null;
                this.z = 0;
            } catch (Throwable th) {
                gz.c(th, "PopupOverlay", "realDestroy");
                th.printStackTrace();
            }
        }
    }

    private synchronized void q() {
        if (this.v != null) {
            Bitmap bitmap = this.v;
            if (bitmap != null) {
                bitmap.recycle();
                this.v = null;
            }
        }
        if (!(this.w == null || this.w.isRecycled())) {
            this.w.recycle();
            this.w = null;
        }
        if (!(this.B == null || this.B.isRecycled())) {
            this.B.recycle();
        }
        if (!(this.C == null || this.C.isRecycled())) {
            this.C.recycle();
        }
        if (!(this.D == null || this.D.isRecycled())) {
            this.D.recycle();
        }
        if (!(this.E == null || this.E.isRecycled())) {
            this.E.recycle();
        }
    }

    public boolean c() {
        return false;
    }

    public void a(MapConfig mapConfig) throws RemoteException {
    }

    public void a(FPoint fPoint) {
        this.n = fPoint;
    }

    private void r() {
    }

    public boolean i() {
        return this.J;
    }

    public synchronized void a(kn knVar) throws RemoteException {
        if (knVar != null) {
            if (knVar.isInfoWindowEnable()) {
                if (!(this.h == null || this.h.getId().equals(knVar.getId()))) {
                    a_();
                }
                if (this.f != null) {
                    this.h = knVar;
                    knVar.a(true);
                    setVisible(true);
                    j();
                }
                this.F = true;
            }
        }
    }

    protected void j() {
        try {
            Bitmap a;
            if (this.h instanceof cs) {
                Marker marker = new Marker((IMarker) this.h);
                if (this.f != null) {
                    a = a(this.f.a((BasePointOverlay) marker));
                    if (a == null) {
                        View b = this.f.b((BasePointOverlay) marker);
                        if (b != null) {
                            if (b.getBackground() == null) {
                                b.setBackground(this.f.f());
                            }
                            a = a(b);
                        }
                    }
                    a(a);
                    d(a(this.f.a(marker)));
                    e(a(this.f.b(marker)));
                    f(a(this.f.c(marker)));
                }
            } else if (this.f != null) {
                BasePointOverlay gL3DModel = new GL3DModel((cd) this.h);
                a = a(this.f.a(gL3DModel));
                if (a == null) {
                    View b2 = this.f.b(gL3DModel);
                    if (b2 != null) {
                        if (b2.getBackground() == null) {
                            b2.setBackground(this.f.f());
                        }
                        a = a(b2);
                    }
                }
                a(a);
            }
        } catch (Throwable th) {
            gz.c(th, "PopupOverlay", "getInfoWindow");
            th.printStackTrace();
        }
    }

    private Bitmap a(View view) {
        if (view == null) {
            return null;
        }
        if ((view instanceof RelativeLayout) && this.g != null) {
            View linearLayout = new LinearLayout(this.g);
            linearLayout.setOrientation(1);
            linearLayout.addView(view);
            view = linearLayout;
        }
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(0);
        return en.a(view);
    }

    private Rect s() {
        return new Rect(this.x.left, this.x.top, this.x.right, this.x.top + u());
    }

    private Rect t() {
        return new Rect(this.x.left, this.x.top, this.x.right, this.x.top + v());
    }

    private int u() {
        if (this.B == null || this.B.isRecycled()) {
            return 0;
        }
        return this.B.getHeight();
    }

    private int v() {
        if (this.D == null || this.D.isRecycled()) {
            return 0;
        }
        return this.D.getHeight();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        r12 = this;
        r2 = 1;
        r3 = 0;
        monitor-enter(r12);	 Catch:{ Throwable -> 0x0073 }
        r0 = r12.h;	 Catch:{ all -> 0x0078 }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r0 = r12.h;	 Catch:{ all -> 0x0078 }
        r0 = r0.k();	 Catch:{ all -> 0x0078 }
        if (r0 != 0) goto L_0x0015;
    L_0x000f:
        r0 = 0;
        r12.setVisible(r0);	 Catch:{ all -> 0x0078 }
        monitor-exit(r12);	 Catch:{ all -> 0x0078 }
    L_0x0014:
        return;
    L_0x0015:
        monitor-exit(r12);	 Catch:{ all -> 0x0078 }
        r0 = 1;
        r12.setVisible(r0);	 Catch:{ Throwable -> 0x0073 }
        r0 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r4 = r0.l();	 Catch:{ Throwable -> 0x0073 }
        r5 = 2;
        r0 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r0 = r0.h();	 Catch:{ Throwable -> 0x0073 }
        r1 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r1 = r1.f();	 Catch:{ Throwable -> 0x0073 }
        r6 = r0 + r1;
        r0 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r0 = r0.i();	 Catch:{ Throwable -> 0x0073 }
        r1 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r1 = r1.g();	 Catch:{ Throwable -> 0x0073 }
        r0 = r0 + r1;
        r1 = r0 + r5;
        r0 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r0 = r0 instanceof com.amap.api.mapcore.util.cs;	 Catch:{ Throwable -> 0x0073 }
        if (r0 == 0) goto L_0x010d;
    L_0x0044:
        monitor-enter(r12);	 Catch:{ Throwable -> 0x0073 }
        r0 = r12.i();	 Catch:{ all -> 0x0070 }
        if (r0 == 0) goto L_0x0057;
    L_0x004b:
        r0 = r12.v;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x007b;
    L_0x004f:
        r0 = r12.B;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0057;
    L_0x0053:
        r0 = r12.D;	 Catch:{ all -> 0x0070 }
        if (r0 == 0) goto L_0x007b;
    L_0x0057:
        r0 = r2;
    L_0x0058:
        if (r0 == 0) goto L_0x00e3;
    L_0x005a:
        r0 = r12.h;	 Catch:{ all -> 0x0070 }
        r0 = (com.amap.api.mapcore.util.cs) r0;	 Catch:{ all -> 0x0070 }
        r0 = r0.getIMarkerAction();	 Catch:{ all -> 0x0070 }
        if (r0 == 0) goto L_0x007d;
    L_0x0064:
        r7 = r0.isInfoWindowEnable();	 Catch:{ all -> 0x0070 }
        if (r7 != 0) goto L_0x007d;
    L_0x006a:
        r0 = 0;
        r12.setVisible(r0);	 Catch:{ all -> 0x0070 }
        monitor-exit(r12);	 Catch:{ all -> 0x0070 }
        goto L_0x0014;
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x0070 }
        throw r0;	 Catch:{ Throwable -> 0x0073 }
    L_0x0073:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0014;
    L_0x0078:
        r0 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x0078 }
        throw r0;	 Catch:{ Throwable -> 0x0073 }
    L_0x007b:
        r0 = r3;
        goto L_0x0058;
    L_0x007d:
        r7 = 1;
        r12.setVisible(r7);	 Catch:{ all -> 0x0070 }
        if (r0 == 0) goto L_0x00fd;
    L_0x0083:
        r7 = r0.isInfoWindowAutoOverturn();	 Catch:{ all -> 0x0070 }
        if (r7 == 0) goto L_0x00fd;
    L_0x0089:
        r7 = r12.s();	 Catch:{ all -> 0x0070 }
        r8 = r12.t();	 Catch:{ all -> 0x0070 }
        r9 = r12.d();	 Catch:{ all -> 0x0070 }
        if (r9 == 0) goto L_0x00e6;
    L_0x0097:
        r9 = 0;
        r10 = r4.height();	 Catch:{ all -> 0x0070 }
        r11 = r7.height();	 Catch:{ all -> 0x0070 }
        r10 = r10 + r11;
        r10 = r10 + r5;
        r8.offset(r9, r10);	 Catch:{ all -> 0x0070 }
    L_0x00a5:
        r9 = r12.a;	 Catch:{ all -> 0x0070 }
        r7 = r9.a(r0, r7);	 Catch:{ all -> 0x0070 }
        r9 = r12.a;	 Catch:{ all -> 0x0070 }
        r0 = r9.a(r0, r8);	 Catch:{ all -> 0x0070 }
        if (r7 <= 0) goto L_0x00f6;
    L_0x00b3:
        if (r0 == 0) goto L_0x00b9;
    L_0x00b5:
        if (r0 <= 0) goto L_0x00f6;
    L_0x00b7:
        if (r7 >= r0) goto L_0x00f6;
    L_0x00b9:
        r0 = r2;
    L_0x00ba:
        if (r0 == 0) goto L_0x00f8;
    L_0x00bc:
        r0 = r12.h;	 Catch:{ all -> 0x0070 }
        r0 = r0.i();	 Catch:{ all -> 0x0070 }
        r1 = r12.h;	 Catch:{ all -> 0x0070 }
        r1 = r1.g();	 Catch:{ all -> 0x0070 }
        r0 = r0 + r1;
        r0 = r0 + r5;
        r1 = r4.height();	 Catch:{ all -> 0x0070 }
        r0 = r0 + r1;
        r1 = r8.height();	 Catch:{ all -> 0x0070 }
        r0 = r0 + r1;
        r12.o();	 Catch:{ all -> 0x0070 }
    L_0x00d7:
        r1 = r12.h;	 Catch:{ all -> 0x0070 }
        r1 = r1.b();	 Catch:{ all -> 0x0070 }
        r12.a(r1);	 Catch:{ all -> 0x0070 }
        r12.c(r6, r0);	 Catch:{ all -> 0x0070 }
    L_0x00e3:
        monitor-exit(r12);	 Catch:{ all -> 0x0070 }
        goto L_0x0014;
    L_0x00e6:
        r9 = 0;
        r10 = r4.height();	 Catch:{ all -> 0x0070 }
        r11 = r7.height();	 Catch:{ all -> 0x0070 }
        r10 = r10 + r11;
        r10 = r10 + r5;
        r10 = -r10;
        r7.offset(r9, r10);	 Catch:{ all -> 0x0070 }
        goto L_0x00a5;
    L_0x00f6:
        r0 = r3;
        goto L_0x00ba;
    L_0x00f8:
        r12.p();	 Catch:{ all -> 0x0070 }
        r0 = r1;
        goto L_0x00d7;
    L_0x00fd:
        r0 = r12.h;	 Catch:{ all -> 0x0070 }
        r0 = r0.b();	 Catch:{ all -> 0x0070 }
        r12.a(r0);	 Catch:{ all -> 0x0070 }
        r12.c(r6, r1);	 Catch:{ all -> 0x0070 }
        r12.p();	 Catch:{ all -> 0x0070 }
        goto L_0x00e3;
    L_0x010d:
        r0 = r12.i();	 Catch:{ Throwable -> 0x0073 }
        if (r0 == 0) goto L_0x011f;
    L_0x0113:
        r0 = r12.v;	 Catch:{ Throwable -> 0x0073 }
        if (r0 != 0) goto L_0x0014;
    L_0x0117:
        r0 = r12.B;	 Catch:{ Throwable -> 0x0073 }
        if (r0 != 0) goto L_0x011f;
    L_0x011b:
        r0 = r12.D;	 Catch:{ Throwable -> 0x0073 }
        if (r0 == 0) goto L_0x0014;
    L_0x011f:
        r0 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r0 = r0.isInfoWindowEnable();	 Catch:{ Throwable -> 0x0073 }
        if (r0 != 0) goto L_0x012d;
    L_0x0127:
        r0 = 0;
        r12.setVisible(r0);	 Catch:{ Throwable -> 0x0073 }
        goto L_0x0014;
    L_0x012d:
        r0 = 1;
        r12.setVisible(r0);	 Catch:{ Throwable -> 0x0073 }
        r0 = r12.h;	 Catch:{ Throwable -> 0x0073 }
        r0 = r0.b();	 Catch:{ Throwable -> 0x0073 }
        r12.a(r0);	 Catch:{ Throwable -> 0x0073 }
        r12.c(r6, r1);	 Catch:{ Throwable -> 0x0073 }
        r12.p();	 Catch:{ Throwable -> 0x0073 }
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cx.b():void");
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.q && this.h != null && this.F && en.a(this.x, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            return true;
        }
        return false;
    }

    public synchronized void a_() {
        setVisible(false);
        q();
        this.F = false;
    }

    public boolean isAboveMaskLayer() {
        return false;
    }

    public void setAboveMaskLayer(boolean z) {
    }

    public void a(ac acVar) {
        synchronized (this) {
            this.f = acVar;
        }
    }
}
