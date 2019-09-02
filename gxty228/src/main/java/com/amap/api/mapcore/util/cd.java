package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.RemoteException;
import android.view.animation.AnimationUtils;
import com.amap.api.mapcore.util.db.b;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.GL3DModelOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.animation.Animation;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.animation.GLAnimationSet;
import com.autonavi.amap.mapcore.animation.GLTransformation;
import com.autonavi.amap.mapcore.animation.GLTranslateAnimation;
import com.autonavi.amap.mapcore.interfaces.IglModel;
import java.util.List;

/* compiled from: Gl3DModelImp */
public class cd extends kn implements IglModel {
    private boolean A = false;
    private boolean B = false;
    private FPoint C = FPoint.obtain();
    private int D = 0;
    private int E = 0;
    private float F = 0.5f;
    private float G = 0.5f;
    private String H;
    private String I;
    private float J = -1.0f;
    float[] a = new float[16];
    float[] b = new float[16];
    Rect c = new Rect(0, 0, 0, 0);
    float d = 1.0f;
    private boolean e = true;
    private String f;
    private float[] g = new float[16];
    private lh h;
    private BitmapDescriptor i;
    private lj j;
    private int k;
    private int l;
    private LatLng m;
    private GLAnimation n;
    private boolean o = true;
    private boolean p = true;
    private Bitmap q;
    private b r;
    private float s;
    private Object t;
    private float u = 18.0f;
    private float v = -1.0f;
    private float w = 0.0f;
    private boolean x = false;
    private lf y;
    private int z;

    public cd(lf lfVar, GL3DModelOptions gL3DModelOptions, lj ljVar) {
        int i = 1;
        if (gL3DModelOptions != null && ljVar != null) {
            this.y = lfVar;
            this.j = ljVar;
            this.i = gL3DModelOptions.getBitmapDescriptor();
            List textrue = gL3DModelOptions.getTextrue();
            List vertext = gL3DModelOptions.getVertext();
            this.m = gL3DModelOptions.getLatLng();
            this.s = gL3DModelOptions.getAngle();
            setModelFixedLength(gL3DModelOptions.getModelFixedLength());
            if (this.m != null) {
                IPoint obtain = IPoint.obtain();
                GLMapState.lonlat2Geo(this.m.longitude, this.m.latitude, obtain);
                this.k = obtain.x;
                this.l = obtain.y;
            }
            if (!(textrue == null || textrue.size() <= 0 || vertext == null)) {
                int i2 = vertext.size() > 0 ? 1 : 0;
                if (this.i == null) {
                    i = 0;
                }
                if ((i2 & i) != 0) {
                    this.h = new lh(vertext, textrue);
                    this.h.a(this.s);
                }
            }
            this.a = new float[16];
            this.b = new float[4];
        }
    }

    public void a() {
        try {
            if (this.h != null) {
                if (this.r == null) {
                    this.r = (b) this.j.u(5);
                }
                if (this.v == -1.0f) {
                    this.v = this.j.v((int) this.u);
                }
                if (this.e) {
                    this.z = a(this.i.getBitmap());
                    this.h.a(this.z);
                    this.e = false;
                }
                o();
                int sx = this.k - this.j.getMapConfig().getSX();
                this.C.x = (float) sx;
                int sy = this.l - this.j.getMapConfig().getSY();
                this.C.y = (float) sy;
                Matrix.setIdentityM(this.g, 0);
                Matrix.multiplyMM(this.g, 0, this.j.getProjectionMatrix(), 0, this.j.getViewMatrix(), 0);
                Matrix.translateM(this.g, 0, (float) sx, (float) sy, 0.0f);
                if (this.x) {
                    this.d = n();
                } else {
                    this.d = m();
                }
                Matrix.scaleM(this.g, 0, this.d, this.d, this.d);
                this.h.a(this.r, this.g);
                if (this.B) {
                    this.j.j();
                    this.B = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float m() {
        float mapPerPixelUnitLength = this.j.getMapConfig().getMapPerPixelUnitLength();
        if (this.j.getMapConfig().getSZ() < this.u) {
            return mapPerPixelUnitLength / this.v;
        }
        this.J = mapPerPixelUnitLength;
        return mapPerPixelUnitLength / this.J;
    }

    private float n() {
        return (this.w * this.j.getMapConfig().getMapPerPixelUnitLength()) / this.h.a();
    }

    private int a(Bitmap bitmap) {
        if (bitmap != null) {
            this.q = bitmap;
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexParameterf(3553, 10241, 9728.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLUtils.texImage2D(3553, 0, this.q, 0);
        return iArr[0];
    }

    public void setPosition(LatLng latLng) {
        if (latLng != null) {
            this.m = latLng;
            IPoint obtain = IPoint.obtain();
            GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, obtain);
            this.k = obtain.x;
            this.l = obtain.y;
            obtain.recycle();
        }
        this.B = true;
        this.j.requestRender();
    }

    public void setRotateAngle(float f) {
        this.s = f;
        if (this.h != null) {
            this.h.a(this.s - this.j.getMapConfig().getSR());
        }
        this.B = true;
    }

    public float getRotateAngle() {
        return 0.0f;
    }

    public LatLng getPosition() {
        return this.m;
    }

    public void setAnimation(Animation animation) {
        if (animation != null) {
            this.n = animation.glAnimation;
        }
    }

    public boolean startAnimation() {
        if (this.n != null) {
            if (this.n instanceof GLAnimationSet) {
                GLAnimationSet gLAnimationSet = (GLAnimationSet) this.n;
                for (GLAnimation gLAnimation : gLAnimationSet.getAnimations()) {
                    a(gLAnimation);
                    gLAnimation.setDuration(gLAnimationSet.getDuration());
                }
            } else {
                a(this.n);
            }
            this.o = false;
            this.n.start();
        }
        return false;
    }

    public boolean remove() {
        if (this.j != null) {
            this.j.b(this.f);
        }
        return true;
    }

    public void setVisible(boolean z) {
        this.p = z;
    }

    public boolean isVisible() {
        return this.p;
    }

    public void setObject(Object obj) {
        this.t = obj;
    }

    public Object getObject() {
        return this.t;
    }

    public void setZoomLimit(float f) {
        this.u = f;
        this.v = this.j.v((int) this.u);
    }

    public void destroy() {
        if (this.q != null) {
            this.q.recycle();
        }
        this.y.a(this.z);
        this.h.c();
    }

    public void setGeoPoint(IPoint iPoint) {
        if (iPoint != null) {
            this.k = iPoint.x;
            this.l = iPoint.y;
            DPoint obtain = DPoint.obtain();
            GLMapState.geo2LonLat(this.k, this.l, obtain);
            this.m = new LatLng(obtain.y, obtain.x, false);
            obtain.recycle();
        }
        this.j.requestRender();
    }

    public void showInfoWindow() {
        try {
            this.j.a((kn) this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void a(GLAnimation gLAnimation) {
        if (gLAnimation instanceof GLTranslateAnimation) {
            ((GLTranslateAnimation) gLAnimation).mFromXDelta = (double) this.k;
            ((GLTranslateAnimation) gLAnimation).mFromYDelta = (double) this.l;
            IPoint obtain = IPoint.obtain();
            GLMapState.lonlat2Geo(((GLTranslateAnimation) gLAnimation).mToXDelta, ((GLTranslateAnimation) gLAnimation).mToYDelta, obtain);
            ((GLTranslateAnimation) gLAnimation).mToXDelta = (double) obtain.x;
            ((GLTranslateAnimation) gLAnimation).mToYDelta = (double) obtain.y;
            obtain.recycle();
        }
    }

    private void o() {
        if (this.o || this.n == null || this.n.hasEnded()) {
            this.o = true;
            return;
        }
        p();
        GLTransformation gLTransformation = new GLTransformation();
        this.n.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation);
        if (gLTransformation != null && !Double.isNaN(gLTransformation.x) && !Double.isNaN(gLTransformation.y)) {
            double d = gLTransformation.x;
            double d2 = gLTransformation.y;
            this.k = (int) d;
            this.l = (int) d2;
        }
    }

    private void p() {
        if (this.j != null) {
            this.j.setRunLowFrame(false);
        }
    }

    public void a(String str) {
        this.f = str;
    }

    public FPoint b() {
        return this.C;
    }

    public LatLng c() {
        return null;
    }

    public void a(boolean z) {
        this.A = z;
        this.B = true;
    }

    public int d() {
        return (int) ((this.h.b() * this.d) / this.j.getMapConfig().getMapPerPixelUnitLength());
    }

    public int e() {
        return (int) ((this.h.a() * this.d) / this.j.getMapConfig().getMapPerPixelUnitLength());
    }

    public int f() {
        return 0;
    }

    public int g() {
        return 0;
    }

    public int h() {
        return this.D;
    }

    public int i() {
        return this.E;
    }

    public boolean j() {
        return false;
    }

    public boolean isInfoWindowEnable() {
        return true;
    }

    public String getId() {
        return this.f;
    }

    public boolean k() {
        if (this.j.getMapConfig().getGeoRectangle().contains(this.k, this.l)) {
            return true;
        }
        return false;
    }

    public void setTitle(String str) {
        this.I = str;
    }

    public void setSnippet(String str) {
        this.H = str;
    }

    public void setModelFixedLength(int i) {
        if (i > 0) {
            this.w = (float) i;
            this.x = true;
            return;
        }
        this.w = 0.0f;
        this.x = false;
    }

    public String getTitle() {
        return this.I;
    }

    public String getSnippet() {
        return this.H;
    }

    public Rect l() {
        try {
            GLMapState c = this.j.c();
            int d = d();
            int e = e();
            FPoint obtain = FPoint.obtain();
            c.p20ToScreenPoint(this.k, this.l, obtain);
            Matrix.setIdentityM(this.a, 0);
            Matrix.rotateM(this.a, 0, -this.s, 0.0f, 0.0f, 1.0f);
            Matrix.rotateM(this.a, 0, this.j.getMapConfig().getSC(), 1.0f, 0.0f, 0.0f);
            Matrix.rotateM(this.a, 0, this.j.getMapConfig().getSR(), 0.0f, 0.0f, 1.0f);
            float[] fArr = new float[]{((float) (-d)) * this.F, ((float) e) * this.G, 0.0f, 1.0f};
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.set((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]), (int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = ((float) d) * (1.0f - this.F);
            this.b[1] = ((float) e) * this.G;
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = ((float) d) * (1.0f - this.F);
            this.b[1] = ((float) (-e)) * (1.0f - this.G);
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = ((float) (-d)) * this.F;
            this.b[1] = ((float) (-e)) * (1.0f - this.G);
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.D = this.c.centerX() - ((int) obtain.x);
            this.E = this.c.top - ((int) obtain.y);
            obtain.recycle();
            return this.c;
        } catch (Throwable th) {
            gz.c(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }
}
