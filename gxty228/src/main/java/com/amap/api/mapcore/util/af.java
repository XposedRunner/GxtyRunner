package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.AMap.CommonInfoWindowAdapter;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnCacheRemoveListener;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnIndoorBuildingActiveListener;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMapLongClickListener;
import com.amap.api.maps.AMap.OnMapScreenShotListener;
import com.amap.api.maps.AMap.OnMapTouchListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.AMap.OnMultiPointClickListener;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.AMap.OnPOIClickListener;
import com.amap.api.maps.AMap.OnPolylineClickListener;
import com.amap.api.maps.AMap.onMapPrintScreenListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CustomRenderer;
import com.amap.api.maps.InfoWindowAnimationManager;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.AMapCameraInfo;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.model.Arc;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BuildingOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.CrossOverlay;
import com.amap.api.maps.model.CrossOverlayOptions;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.GL3DModelOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.RouteOverlay;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.animation.Animation.AnimationListener;
import com.autonavi.ae.gmap.GLMapEngine;
import com.autonavi.ae.gmap.GLMapEngine.MapViewInitParam;
import com.autonavi.ae.gmap.GLMapRender;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.ae.gmap.glinterface.MapLabelItem;
import com.autonavi.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.ae.gmap.gloverlay.CrossVectorOverlay;
import com.autonavi.ae.gmap.gloverlay.GLOverlayBundle;
import com.autonavi.ae.gmap.gloverlay.GLTextureProperty;
import com.autonavi.ae.gmap.listener.AMapWidgetListener;
import com.autonavi.ae.gmap.style.StyleItem;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage.Type;
import com.autonavi.amap.mapcore.AeUtil;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.FileUtil;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.amap.mapcore.animation.GLAlphaAnimation;
import com.autonavi.amap.mapcore.interfaces.IAMapListener;
import com.autonavi.amap.mapcore.interfaces.IArc;
import com.autonavi.amap.mapcore.interfaces.ICircle;
import com.autonavi.amap.mapcore.interfaces.IGroundOverlay;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import com.autonavi.amap.mapcore.interfaces.INavigateArrow;
import com.autonavi.amap.mapcore.interfaces.IPolygon;
import com.autonavi.amap.mapcore.interfaces.IPolyline;
import com.autonavi.amap.mapcore.message.AbstractGestureMapMessage;
import com.autonavi.amap.mapcore.tools.GLConvertUtil;
import com.github.mikephil.charting.utils.Utils;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: AMapDelegateImp */
public class af implements com.amap.api.mapcore.util.df.a, lj, IAMapListener {
    private OnPOIClickListener A;
    private OnMapLongClickListener B;
    private OnInfoWindowClickListener C;
    private OnIndoorBuildingActiveListener D;
    private OnMyLocationChangeListener E;
    private fa F;
    private onMapPrintScreenListener G = null;
    private OnMapScreenShotListener H = null;
    private AMapGestureListener I;
    private ac J;
    private cx K = null;
    private UiSettings L;
    private ll M;
    private final j N;
    private boolean O = false;
    private final lk P;
    private fs Q;
    private final ag R;
    private final r S;
    private boolean T = false;
    private int U;
    private boolean V = false;
    private lf W;
    private AMapWidgetListener X;
    private boolean Y = false;
    private boolean Z = false;
    protected boolean a = false;
    private dd aA;
    private df aB;
    private eh aC;
    private GLMapRender aD;
    private lg aE;
    private boolean aF = false;
    private float aG = 0.0f;
    private float aH = 1.0f;
    private float aI = 1.0f;
    private boolean aJ = true;
    private boolean aK = false;
    private boolean aL = false;
    private int aM = 0;
    private volatile boolean aN = false;
    private volatile boolean aO = false;
    private boolean aP = false;
    private boolean aQ = false;
    private Lock aR = new ReentrantLock();
    private int aS = 0;
    private int aT;
    private int aU;
    private b aV;
    private db aW;
    private li aX;
    private aj aY;
    private a aZ = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            try {
                this.a.setTrafficEnabled(this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private boolean aa = false;
    private ct ab;
    private LocationSource ac;
    private boolean ad = false;
    private Marker ae = null;
    private kn af = null;
    private boolean ag = false;
    private boolean ah = false;
    private boolean ai = false;
    private boolean aj = false;
    private boolean ak = false;
    private boolean al = true;
    private Rect am = new Rect();
    private int an = 1;
    private MyTrafficStyle ao = null;
    private Thread ap;
    private Thread aq;
    private boolean ar = false;
    private boolean as = false;
    private boolean at = false;
    private int au = 0;
    private CustomRenderer av;
    private final b aw;
    private int ax = -1;
    private int ay = -1;
    private List<f> az = new ArrayList();
    protected final z b;
    private a ba = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            try {
                this.a.setCenterToPixel(this.a.aT, this.a.aU);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private a bb = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            this.a.b(this.g, this.d, this.e, this.f);
        }
    };
    private a bc = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            this.a.setMapCustomEnable(this.c);
        }
    };
    private a bd = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            this.a.a(this.g, this.c);
        }
    };
    private a be = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            try {
                this.a.setMapTextEnable(this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private a bf = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            this.a.b(this.g, this.c);
        }
    };
    private a bg = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            try {
                this.a.setIndoorEnabled(this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private Runnable bh = new Runnable(this) {
        final /* synthetic */ af a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.Q != null) {
                fu j = this.a.Q.j();
                if (j != null) {
                    j.d();
                }
            }
        }
    };
    private a bi = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            this.a.c(this.g, this.c);
        }
    };
    private a bj = new a(this) {
        final /* synthetic */ af a;

        {
            this.a = r2;
        }

        public void run() {
            super.run();
            try {
                this.a.setMyTrafficStyle(this.a.ao);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private EAMapPlatformGestureInfo bk = new EAMapPlatformGestureInfo();
    private long bl = 0;
    private aa bm = null;
    private IPoint[] bn = null;
    protected MapConfig c = new MapConfig(true);
    protected aa d;
    protected Context e;
    protected GLMapEngine f;
    public int g;
    public int h;
    protected final Handler i = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ af a;

        public void handleMessage(Message message) {
            boolean z = true;
            if (message != null && !this.a.V) {
                try {
                    CameraPosition cameraPosition;
                    switch (message.what) {
                        case 2:
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Key验证失败：[");
                            if (message.obj != null) {
                                stringBuilder.append(message.obj);
                            } else {
                                stringBuilder.append(fy.b);
                            }
                            stringBuilder.append("]");
                            Log.w("amapsdk", stringBuilder.toString());
                            return;
                        case 10:
                            cameraPosition = (CameraPosition) message.obj;
                            if (cameraPosition != null && this.a.x != null) {
                                this.a.x.onCameraChange(cameraPosition);
                                return;
                            }
                            return;
                        case 11:
                            try {
                                cameraPosition = this.a.getCameraPosition();
                                if (!(cameraPosition == null || this.a.Q == null)) {
                                    this.a.Q.a(cameraPosition);
                                }
                                this.a.a(cameraPosition);
                                if (this.a.aL) {
                                    this.a.aL = false;
                                    if (this.a.R != null) {
                                        this.a.R.b(false);
                                    }
                                    this.a.g(true);
                                }
                                if (this.a.aj) {
                                    this.a.j();
                                    this.a.aj = false;
                                }
                                this.a.a(true, cameraPosition);
                                return;
                            } catch (Throwable th) {
                                gz.c(th, "AMapDelegateImp", "CameraUpdateFinish");
                                return;
                            }
                        case 12:
                            if (this.a.Q != null) {
                                this.a.Q.a(this.a.g());
                                return;
                            }
                            return;
                        case 13:
                            if (this.a.Q != null) {
                                fp i = this.a.Q.i();
                                if (i != null) {
                                    i.b();
                                    return;
                                }
                                return;
                            }
                            return;
                        case 14:
                            try {
                                if (this.a.z != null) {
                                    this.a.z.onTouch((MotionEvent) message.obj);
                                    return;
                                }
                                return;
                            } catch (Throwable th2) {
                                gz.c(th2, "AMapDelegateImp", "onTouchHandler");
                                th2.printStackTrace();
                                return;
                            }
                        case 15:
                            Bitmap bitmap = (Bitmap) message.obj;
                            int i2 = message.arg1;
                            if (bitmap == null || this.a.Q == null) {
                                if (this.a.G != null) {
                                    this.a.G.onMapPrint(null);
                                }
                                if (this.a.H != null) {
                                    this.a.H.onMapScreenShot(null);
                                    this.a.H.onMapScreenShot(null, i2);
                                }
                            } else {
                                Canvas canvas = new Canvas(bitmap);
                                fu j = this.a.Q.j();
                                if (j != null) {
                                    j.onDraw(canvas);
                                }
                                this.a.Q.a(canvas);
                                if (this.a.G != null) {
                                    this.a.G.onMapPrint(new BitmapDrawable(this.a.e.getResources(), bitmap));
                                }
                                if (this.a.H != null) {
                                    this.a.H.onMapScreenShot(bitmap);
                                    this.a.H.onMapScreenShot(bitmap, i2);
                                }
                            }
                            this.a.G = null;
                            this.a.H = null;
                            return;
                        case 16:
                            if (this.a.w != null) {
                                this.a.w.onMapLoaded();
                                return;
                            }
                            return;
                        case 17:
                            if (this.a.f.isInMapAnimation(1) && this.a.R != null) {
                                this.a.R.b(false);
                            }
                            if (this.a.R != null) {
                                ag i3 = this.a.R;
                                if (message.arg1 == 0) {
                                    z = false;
                                }
                                i3.a(z);
                                return;
                            }
                            return;
                        case 18:
                            if (this.a.J != null && this.a.aa) {
                                this.a.J.c();
                                return;
                            }
                            return;
                        case 19:
                            if (this.a.y != null) {
                                DPoint obtain = DPoint.obtain();
                                this.a.b(message.arg1, message.arg2, obtain);
                                try {
                                    this.a.y.onMapClick(new LatLng(obtain.y, obtain.x));
                                    obtain.recycle();
                                    return;
                                } catch (Throwable th22) {
                                    gz.c(th22, "AMapDelegateImp", "OnMapClickListener.onMapClick");
                                    th22.printStackTrace();
                                    return;
                                }
                            }
                            return;
                        case 20:
                            try {
                                this.a.A.onPOIClick((Poi) message.obj);
                                return;
                            } catch (Throwable th222) {
                                gz.c(th222, "AMapDelegateImp", "OnPOIClickListener.onPOIClick");
                                th222.printStackTrace();
                                return;
                            }
                        default:
                            return;
                    }
                } catch (Throwable th2222) {
                    gz.c(th2222, "AMapDelegateImp", "handleMessage");
                    th2222.printStackTrace();
                }
                gz.c(th2222, "AMapDelegateImp", "handleMessage");
                th2222.printStackTrace();
            }
        }
    };
    Point j = new Point();
    Rect k = new Rect();
    protected String l = null;
    float[] m = new float[16];
    float[] n = new float[16];
    float[] o = new float[16];
    float[] p = new float[12];
    String q = "precision highp float;\nattribute vec3 aVertex;//顶点数组,三维坐标\nuniform mat4 aMVPMatrix;//mvp矩阵\nvoid main(){\n  gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n}";
    String r = "//有颜色 没有纹理\nprecision highp float;\nvoid main(){\n  gl_FragColor = vec4(1.0,0,0,1.0);\n}";
    int s = -1;
    private OnMarkerClickListener t;
    private OnPolylineClickListener u;
    private OnMarkerDragListener v;
    private OnMapLoadedListener w;
    private OnCameraChangeListener x;
    private OnMapClickListener y;
    private OnMapTouchListener z;

    /* compiled from: AMapDelegateImp */
    private static abstract class a implements Runnable {
        boolean b;
        boolean c;
        int d;
        int e;
        int f;
        int g;

        private a() {
            this.b = false;
            this.c = false;
        }

        public void run() {
            this.b = false;
        }
    }

    /* compiled from: AMapDelegateImp */
    class b {
        final /* synthetic */ af a;

        b(af afVar) {
            this.a = afVar;
        }

        public void a(aa aaVar) {
            float f = 20.0f;
            if (this.a.c != null && this.a.c.isIndoorEnable()) {
                final fq g = this.a.Q.g();
                if (aaVar == null) {
                    try {
                        if (this.a.D != null) {
                            this.a.D.OnIndoorBuilding(aaVar);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (this.a.d != null) {
                        this.a.d.g = null;
                    }
                    if (g.d()) {
                        this.a.i.post(new Runnable(this) {
                            final /* synthetic */ b b;

                            public void run() {
                                g.a(false);
                            }
                        });
                    }
                    this.a.c.maxZoomLevel = this.a.c.isSetLimitZoomLevel() ? this.a.c.getMaxZoomLevel() : 20.0f;
                    try {
                        if (this.a.N.isZoomControlsEnabled()) {
                            this.a.X.invalidateZoomController(this.a.c.getSZ());
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (aaVar == null || this.a.d == null || !this.a.d.poiid.equals(aaVar.poiid) || !g.d()) {
                    if (aaVar != null && (this.a.d == null || !this.a.d.poiid.equals(aaVar.poiid) || this.a.d.g == null)) {
                        this.a.d = aaVar;
                        if (this.a.c != null) {
                            this.a.d.g = this.a.c.getMapGeoCenter();
                        }
                    }
                    try {
                        if (this.a.D != null) {
                            this.a.D.OnIndoorBuilding(aaVar);
                        }
                        MapConfig mapConfig = this.a.c;
                        if (this.a.c.isSetLimitZoomLevel()) {
                            f = this.a.c.getMaxZoomLevel();
                        }
                        mapConfig.maxZoomLevel = f;
                        if (this.a.N.isZoomControlsEnabled()) {
                            this.a.X.invalidateZoomController(this.a.c.getSZ());
                        }
                        if (this.a.N.isIndoorSwitchEnabled()) {
                            if (!g.d()) {
                                this.a.N.setIndoorSwitchEnabled(true);
                            }
                            this.a.i.post(new Runnable(this) {
                                final /* synthetic */ b b;

                                public void run() {
                                    try {
                                        g.a(this.b.a.d.floor_names);
                                        g.a(this.b.a.d.activeFloorName);
                                        if (!g.d()) {
                                            g.a(true);
                                        }
                                    } catch (Throwable th) {
                                        th.printStackTrace();
                                    }
                                }
                            });
                        } else if (!this.a.N.isIndoorSwitchEnabled() && g.d()) {
                            this.a.N.setIndoorSwitchEnabled(false);
                        }
                    } catch (Throwable th22) {
                        th22.printStackTrace();
                    }
                }
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    private class c implements com.amap.api.mapcore.util.fq.a {
        final /* synthetic */ af a;

        private c(af afVar) {
            this.a = afVar;
        }

        public void a(int i) {
            if (this.a.d != null) {
                this.a.d.activeFloorIndex = this.a.d.floor_indexs[i];
                this.a.d.activeFloorName = this.a.d.floor_names[i];
                try {
                    this.a.setIndoorBuildingInfo(this.a.d);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    private class d implements Runnable {
        final /* synthetic */ af a;
        private Context b;
        private OnCacheRemoveListener c;

        public d(af afVar, Context context, OnCacheRemoveListener onCacheRemoveListener) {
            this.a = afVar;
            this.b = context;
            this.c = onCacheRemoveListener;
        }

        public void run() {
            Throwable th;
            boolean z = true;
            try {
                boolean z2;
                Context applicationContext = this.b.getApplicationContext();
                String c = en.c(applicationContext);
                String a = en.a(applicationContext);
                File file = new File(c);
                if (file.exists()) {
                    z2 = FileUtil.deleteFile(file);
                } else {
                    z2 = true;
                }
                if (z2) {
                    try {
                        if (FileUtil.deleteFile(new File(a))) {
                            z2 = true;
                            if (!(z2 && en.e(applicationContext))) {
                                z = false;
                            }
                            if (this.a.R != null) {
                                this.a.R.h();
                            }
                            if (this.a.f != null && this.c != null) {
                                this.c.onRemoveCacheFinish(z);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        z = z2;
                        if (!(this.a.f == null || this.c == null)) {
                            this.c.onRemoveCacheFinish(z);
                        }
                        throw th;
                    }
                }
                z2 = false;
                z = false;
                if (this.a.R != null) {
                    this.a.R.h();
                }
                try {
                    if (this.a.f != null) {
                    }
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            } catch (Throwable th4) {
                th3 = th4;
                gz.c(th3, "AMapDelegateImp", "RemoveCacheRunnable");
                if (this.a.f != null) {
                }
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof d;
        }
    }

    public void a(AMapWidgetListener aMapWidgetListener) {
        this.X = aMapWidgetListener;
    }

    private void a(CameraPosition cameraPosition) {
        if (this.c.getMapLanguage().equals(AMap.ENGLISH)) {
            boolean b = b(cameraPosition);
            if (b != this.al) {
                this.al = b;
                b(1, this.al);
            }
        } else if (!this.al) {
            this.al = true;
            b(1, this.al);
        }
    }

    private boolean b(CameraPosition cameraPosition) {
        boolean z = true;
        if (cameraPosition.zoom < 7.0f) {
            return false;
        }
        if (!cameraPosition.isAbroad) {
            if (this.c != null) {
                try {
                    if (ee.a(this.c.getGeoRectangle().getClipRect())) {
                        z = false;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    z = false;
                }
            } else {
                z = false;
            }
        }
        return z;
    }

    public void setVisibilityEx(int i) {
        if (this.P != null) {
            try {
                this.P.setVisibility(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void onActivityPause() {
        this.Y = true;
        p(this.U);
    }

    public void onActivityResume() {
        this.Y = false;
        int i = this.U;
        if (i == 0) {
            i = this.f.getEngineIDWithType(0);
        }
        q(i);
    }

    public void queueEvent(Runnable runnable) {
        this.P.queueEvent(runnable);
    }

    public af(lk lkVar, Context context, AttributeSet attributeSet) {
        this.e = context;
        this.aC = new eh(context, this, lkVar);
        gz.a(this.e);
        dv.a().a(this.e);
        le.b = fx.c(context);
        dm.a(this.e);
        this.aE = new lg(this);
        this.f = new GLMapEngine(this.e, this);
        this.aD = new GLMapRender(this);
        this.P = lkVar;
        lkVar.setRenderer(this.aD);
        this.N = new j(this);
        this.Q = new fs(this.e, this);
        this.Q.g().a(new c());
        this.aV = new b(this);
        this.S = new r(this);
        this.R = new ag(this.e, this);
        this.b = new z(this.e, this);
        this.W = new lf(this.e, this);
        this.P.setRenderMode(0);
        this.aD.setRenderFps(15.0f);
        this.f.setMapListener(this);
        this.M = new h(this);
        this.F = new fa(this);
        this.K = new cx(this, context);
        this.J = new ac(this.e);
        this.J.a(this.Q);
        this.J.b(this.K);
        this.aw = new b();
        this.ap = new il(this.e, this);
        this.ac = new ad(this.e);
        this.aY = new aj(this);
        this.aX = new li();
        this.aA = new dd(this.e, this);
        this.aB = new df(this.e);
        this.aB.a((com.amap.api.mapcore.util.df.a) this);
    }

    public GLMapEngine a() {
        return this.f;
    }

    public void a(int i, int i2) {
        if (this.aS == 0 || i2 != 5) {
            this.aS = i2;
        }
    }

    public float a(int i) {
        if (this.c != null) {
            return this.c.getSZ();
        }
        return 0.0f;
    }

    public float b(int i) {
        if (this.c != null) {
            return getMapConfig().getSZ();
        }
        return 0.0f;
    }

    public boolean a(int i, int i2, int i3) {
        if (!this.aN || ((float) ((int) b(i))) >= this.c.getMaxZoomLevel()) {
            return false;
        }
        try {
            AbstractCameraUpdateMessage a;
            if (this.Z) {
                a = p.a(1.0f, null);
            } else if (this.N.isZoomInByScreenCenter()) {
                a = p.a(1.0f, null);
            } else {
                this.j.x = i2;
                this.j.y = i3;
                a = p.a(1.0f, this.j);
            }
            b(a);
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "onDoubleTap");
            th.printStackTrace();
        }
        resetRenderTime();
        return true;
    }

    public void c(int i) {
        if (this.aN && ((float) ((int) b(i))) > this.c.getMinZoomLevel()) {
            try {
                b(p.b());
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImp", "onDoubleTap");
                th.printStackTrace();
            }
            resetRenderTime();
        }
    }

    public boolean d(int i) {
        return false;
    }

    public boolean e(int i) {
        return c(i, 7);
    }

    private void w(final int i) {
        if (this.aN) {
            this.aE.a();
            this.aF = true;
            this.aK = true;
            try {
                stopAnimation();
            } catch (RemoteException e) {
            }
            queueEvent(new Runnable(this) {
                final /* synthetic */ af b;

                public void run() {
                    try {
                        this.b.f.clearAllMessages(i);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    private void x(int i) {
        this.aF = true;
        this.aK = false;
        if (this.ah) {
            this.ah = false;
        }
        if (this.ag) {
            this.ag = false;
        }
        if (this.ai) {
            this.ai = false;
        }
        this.ad = false;
        if (!(this.v == null || this.ae == null)) {
            try {
                this.v.onMarkerDragEnd(this.ae);
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImp", "OnMarkerDragListener.onMarkerDragEnd");
                th.printStackTrace();
            }
            this.ae = null;
        }
        this.P.postDelayed(new Runnable(this) {
            final /* synthetic */ af a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.aM = 1;
            }
        }, 300);
    }

    private void a(MotionEvent motionEvent) throws RemoteException {
        if (this.ad && this.ae != null && this.af != null) {
            int x = (int) motionEvent.getX();
            int y = (int) (motionEvent.getY() - 60.0f);
            LatLng c = this.af.c();
            if (c != null) {
                LatLng position = this.af.getPosition();
                DPoint obtain = DPoint.obtain();
                b(x, y, obtain);
                LatLng latLng = new LatLng((position.latitude + obtain.y) - c.latitude, (position.longitude + obtain.x) - c.longitude);
                obtain.recycle();
                this.ae.setPosition(latLng);
                if (this.v != null) {
                    this.v.onMarkerDrag(this.ae);
                }
            }
        }
    }

    public void b() {
        if (this.R != null) {
            this.R.h();
        }
    }

    public GLMapState c() {
        if (this.f != null) {
            return this.f.getMapState(1);
        }
        return null;
    }

    public int d() {
        if (this.aX != null) {
            return this.aX.a();
        }
        return 0;
    }

    public int f(int i) {
        if (this.aX != null) {
            return this.aX.a(i);
        }
        return 0;
    }

    public int e() {
        if (this.aX != null) {
            return this.aX.b();
        }
        return 0;
    }

    public void a(Location location) throws RemoteException {
        if (location != null) {
            try {
                if (!this.T || this.ac == null) {
                    if (this.ab != null) {
                        this.ab.b();
                    }
                    this.ab = null;
                    return;
                }
                if (this.ab == null) {
                    this.ab = new ct(this, this.e);
                }
                if (!(location.getLongitude() == Utils.DOUBLE_EPSILON || location.getLatitude() == Utils.DOUBLE_EPSILON)) {
                    this.ab.a(location);
                }
                if (this.E != null) {
                    this.E.onMyLocationChange(location);
                }
                resetRenderTime();
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImp", "showMyLocationOverlay");
                th.printStackTrace();
            }
        }
    }

    public boolean a(String str) throws RemoteException {
        resetRenderTime();
        return this.S.d(str);
    }

    public boolean b(String str) {
        try {
            this.W.a(str);
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "removeGLModel");
            th.printStackTrace();
        }
        return false;
    }

    public void f() {
        this.S.d();
    }

    public void a(double d, double d2, IPoint iPoint) {
        Point latLongToPixels = VirtualEarthProjection.latLongToPixels(d, d2, 20);
        iPoint.x = latLongToPixels.x;
        iPoint.y = latLongToPixels.y;
    }

    public void a(int i, int i2, FPoint fPoint) {
        fPoint.x = (float) (i - this.c.getSX());
        fPoint.y = (float) (i2 - this.c.getSY());
    }

    public void a(int i, int i2, DPoint dPoint) {
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) i, (long) i2, 20);
        dPoint.x = pixelsToLatLong.x;
        dPoint.y = pixelsToLatLong.y;
        pixelsToLatLong.recycle();
    }

    public float g() {
        return b(this.U);
    }

    public lm h() {
        return this.N;
    }

    public void a(kn knVar) throws RemoteException {
        if (knVar != null && this.J != null) {
            try {
                this.J.a(knVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void i() {
        if (this.J != null) {
            this.J.e();
        }
    }

    public void a(double d, double d2, FPoint fPoint) {
        IPoint obtain = IPoint.obtain();
        a(d, d2, obtain);
        a(obtain.x, obtain.y, fPoint);
        obtain.recycle();
    }

    public void b(int i, int i2, DPoint dPoint) {
        if (this.aN && this.f != null) {
            GLMapState mapState = this.f.getMapState(1);
            if (mapState != null) {
                Point obtain = IPoint.obtain();
                mapState.screenToP20Point(i, i2, obtain);
                DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) obtain.x, (long) obtain.y, 20);
                dPoint.x = pixelsToLatLong.x;
                dPoint.y = pixelsToLatLong.y;
                obtain.recycle();
                pixelsToLatLong.recycle();
            }
        }
    }

    protected void a(GLMapState gLMapState, int i, int i2, DPoint dPoint) {
        if (this.aN && this.f != null) {
            Point point = new Point();
            gLMapState.screenToP20Point(i, i2, point);
            DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) point.x, (long) point.y, 20);
            dPoint.x = pixelsToLatLong.x;
            dPoint.y = pixelsToLatLong.y;
            pixelsToLatLong.recycle();
        }
    }

    public void b(double d, double d2, IPoint iPoint) {
        if (this.aN && this.f != null) {
            try {
                Point latLongToPixels = VirtualEarthProjection.latLongToPixels(d, d2, 20);
                FPoint obtain = FPoint.obtain();
                b(latLongToPixels.x, latLongToPixels.y, obtain);
                if (obtain.x == ((float) -10000) && obtain.y == ((float) -10000)) {
                    GLMapState newMapState = this.f.getNewMapState(1);
                    newMapState.setCameraDegree(0.0f);
                    newMapState.recalculate();
                    newMapState.p20ToScreenPoint(latLongToPixels.x, latLongToPixels.y, obtain);
                    newMapState.recycle();
                }
                iPoint.x = (int) obtain.x;
                iPoint.y = (int) obtain.y;
                obtain.recycle();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(int i, int i2, IPoint iPoint) {
        if (this.aN && this.f != null) {
            GLMapState mapState = this.f.getMapState(1);
            if (mapState != null) {
                mapState.screenToP20Point(i, i2, iPoint);
            }
        }
    }

    public void b(int i, int i2, FPoint fPoint) {
        if (this.aN && this.f != null) {
            GLMapState mapState = this.f.getMapState(1);
            if (mapState != null) {
                mapState.p20ToScreenPoint(i, i2, fPoint);
            }
        }
    }

    public void j() {
        if (this.aN) {
            this.i.sendEmptyMessage(18);
        }
    }

    public void a(boolean z) {
        if (!this.V && this.Q != null) {
            this.Q.b(z);
        }
    }

    public void b(boolean z) {
        if (!this.V && this.Q != null) {
            this.Q.a(z);
        }
    }

    public void c(boolean z) {
        if (!this.V && this.Q != null) {
            this.Q.c(z);
        }
    }

    public void d(boolean z) {
        if (!this.V && this.Q != null) {
            this.Q.d(z);
        }
    }

    public void e(boolean z) {
        if (!this.V && this.Q != null) {
            this.Q.e(z);
        }
    }

    public void g(int i) {
        if (!this.V && this.Q != null) {
            this.Q.a(i);
        }
    }

    public LatLngBounds a(LatLng latLng, float f, float f2, float f3) {
        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        if (mapWidth <= 0 || mapHeight <= 0 || this.V) {
            return null;
        }
        float a = en.a(this.c, f);
        GLMapState gLMapState = new GLMapState(1, this.f.getNativeInstance());
        if (latLng != null) {
            IPoint obtain = IPoint.obtain();
            a(latLng.latitude, latLng.longitude, obtain);
            gLMapState.setCameraDegree(60.0f);
            gLMapState.setMapAngle(0.0f);
            gLMapState.setMapGeoCenter(obtain.x, obtain.y);
            gLMapState.setMapZoomer(a);
            gLMapState.recalculate();
            obtain.recycle();
        }
        DPoint obtain2 = DPoint.obtain();
        a(gLMapState, 0, 0, obtain2);
        LatLng latLng2 = new LatLng(obtain2.y, obtain2.x, false);
        a(gLMapState, mapWidth, mapHeight, obtain2);
        LatLng latLng3 = new LatLng(obtain2.y, obtain2.x, false);
        obtain2.recycle();
        gLMapState.recycle();
        return LatLngBounds.builder().include(latLng3).include(latLng2).build();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.Y || !this.aN || !this.aJ) {
            return false;
        }
        this.bk.mGestureState = 3;
        this.bk.mGestureType = 8;
        float[] fArr = new float[]{motionEvent.getX(), motionEvent.getY()};
        this.bk.mLocation = fArr;
        int a = a(this.bk);
        r();
        switch (motionEvent.getAction() & 255) {
            case 0:
                s();
                w(a);
                break;
            case 1:
                x(a);
                break;
        }
        if (motionEvent.getAction() == 2 && this.ad) {
            try {
                a(motionEvent);
                return true;
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImp", "onDragMarker");
                th.printStackTrace();
                return true;
            }
        }
        if (this.aF) {
            try {
                this.aE.a(motionEvent);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        if (this.z == null) {
            return true;
        }
        this.i.removeMessages(14);
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.what = 14;
        obtainMessage.obj = MotionEvent.obtain(motionEvent);
        obtainMessage.sendToTarget();
        return true;
    }

    public void a(int i, int i2, PointF pointF) {
        if (this.aN && !this.Y && this.f != null) {
            IPoint obtain = IPoint.obtain();
            a(i, i2, obtain);
            pointF.x = (float) (obtain.x - this.c.getSX());
            pointF.y = (float) (obtain.y - this.c.getSY());
            obtain.recycle();
        }
    }

    public void a(float f, float f2, IPoint iPoint) {
        iPoint.x = (int) (((float) this.c.getSX()) + f);
        iPoint.y = (int) (((float) this.c.getSY()) + f2);
    }

    public float h(int i) {
        if (!this.aN || this.Y || this.f == null) {
            return 0.0f;
        }
        return this.f.getMapState(1).getGLUnitWithWin(i);
    }

    public CameraPosition f(boolean z) {
        try {
            if (this.c == null) {
                return null;
            }
            if (!this.aN || this.Y || this.f == null) {
                DPoint obtain = DPoint.obtain();
                a(this.c.getSX(), this.c.getSY(), obtain);
                LatLng latLng = new LatLng(obtain.y, obtain.x);
                obtain.recycle();
                return CameraPosition.builder().target(latLng).bearing(this.c.getSR()).tilt(this.c.getSC()).zoom(this.c.getSZ()).build();
            }
            LatLng latLng2;
            if (z) {
                DPoint obtain2 = DPoint.obtain();
                b(this.c.getAnchorX(), this.c.getAnchorY(), obtain2);
                latLng2 = new LatLng(obtain2.y, obtain2.x, false);
                obtain2.recycle();
            } else {
                latLng2 = z();
            }
            return CameraPosition.builder().target(latLng2).bearing(this.c.getSR()).tilt(this.c.getSC()).zoom(this.c.getSZ()).build();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private LatLng z() {
        if (this.c == null) {
            return null;
        }
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) this.c.getSX(), (long) this.c.getSY(), 20);
        LatLng latLng = new LatLng(pixelsToLatLong.y, pixelsToLatLong.x, false);
        pixelsToLatLong.recycle();
        return latLng;
    }

    public boolean k() {
        return this.Z;
    }

    public Point l() {
        if (this.Q != null) {
            return this.Q.c();
        }
        return new Point();
    }

    public View m() {
        if (this.P instanceof View) {
            return (View) this.P;
        }
        return null;
    }

    public boolean n() {
        if (!(g() < ((float) 17) || this.d == null || this.d.g == null)) {
            FPoint obtain = FPoint.obtain();
            b(this.d.g.x, this.d.g.y, obtain);
            if (this.am.contains((int) obtain.x, (int) obtain.y)) {
                return true;
            }
        }
        return false;
    }

    private synchronized void A() {
        synchronized (this.az) {
            int size = this.az.size();
            for (int i = 0; i < size; i++) {
                ((f) this.az.get(i)).e().recycle();
            }
            this.az.clear();
        }
    }

    public void a(f fVar) {
        if (fVar != null && fVar.f() != 0) {
            synchronized (this.az) {
                this.az.add(fVar);
            }
        }
    }

    public void c(String str) {
        synchronized (this.az) {
            int i;
            int size = this.az.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((f) this.az.get(i2)).j().equals(str)) {
                    i = i2;
                    break;
                }
            }
            i = -1;
            if (i >= 0) {
                this.az.remove(i);
            }
        }
    }

    public f a(BitmapDescriptor bitmapDescriptor) {
        return a(bitmapDescriptor, false);
    }

    public f a(BitmapDescriptor bitmapDescriptor, boolean z) {
        if (bitmapDescriptor == null || bitmapDescriptor.getBitmap() == null || bitmapDescriptor.getBitmap().isRecycled()) {
            return null;
        }
        synchronized (this.az) {
            for (int i = 0; i < this.az.size(); i++) {
                f fVar = (f) this.az.get(i);
                if (!(z && fVar.f() == e()) && fVar.e().equals(bitmapDescriptor)) {
                    return fVar;
                }
            }
            return null;
        }
    }

    public int a(IMarkerAction iMarkerAction, Rect rect) {
        return 0;
    }

    public void i(int i) {
        if (this.Q != null) {
            this.Q.b(i);
        }
    }

    public void j(int i) {
        if (this.Q != null) {
            this.Q.c(i);
        }
    }

    public void k(int i) {
        if (this.Q != null) {
            this.Q.d(i);
        }
    }

    public float l(int i) {
        if (this.Q != null) {
            return this.Q.e(i);
        }
        return 0.0f;
    }

    public void a(int i, float f) {
        if (this.Q != null) {
            this.Q.a(i, f);
        }
    }

    public int o() {
        return this.ax;
    }

    public void a(Runnable runnable) {
        if (this.P != null) {
            this.P.post(runnable);
        }
    }

    public void a(int i, MotionEvent motionEvent) {
        try {
            this.ag = false;
            m(i);
            this.af = this.b.a(motionEvent);
            if (this.af != null && this.af.isDraggable()) {
                this.ae = new Marker((cs) this.af);
                LatLng position = this.ae.getPosition();
                LatLng c = this.af.c();
                if (!(position == null || c == null)) {
                    IPoint obtain = IPoint.obtain();
                    b(c.latitude, c.longitude, obtain);
                    obtain.y -= 60;
                    DPoint obtain2 = DPoint.obtain();
                    b(obtain.x, obtain.y, obtain2);
                    this.ae.setPosition(new LatLng((position.latitude + obtain2.y) - c.latitude, (position.longitude + obtain2.x) - c.longitude));
                    this.b.a((cs) this.af);
                    if (this.v != null) {
                        this.v.onMarkerDragStart(this.ae);
                    }
                    this.ad = true;
                    obtain.recycle();
                    obtain2.recycle();
                }
            } else if (this.B != null) {
                DPoint obtain3 = DPoint.obtain();
                b((int) motionEvent.getX(), (int) motionEvent.getY(), obtain3);
                this.B.onMapLongClick(new LatLng(obtain3.y, obtain3.x));
                this.ah = true;
                obtain3.recycle();
            }
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "onLongPress");
            th.printStackTrace();
            return;
        }
        this.aD.resetTickCount(30);
    }

    public boolean b(int i, MotionEvent motionEvent) {
        if (this.aN) {
            a(i, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return false;
    }

    public boolean c(int i, MotionEvent motionEvent) {
        if (!this.aN) {
            return false;
        }
        try {
            if (g(motionEvent) || e(motionEvent) || f(motionEvent) || d(motionEvent)) {
                return true;
            }
            b(motionEvent);
            return true;
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "onSingleTapUp");
            th.printStackTrace();
            return true;
        }
    }

    private void b(final MotionEvent motionEvent) {
        queueEvent(new Runnable(this) {
            final /* synthetic */ af b;

            public void run() {
                try {
                    Message obtain = Message.obtain();
                    Poi a = this.b.b((int) motionEvent.getX(), (int) motionEvent.getY(), 25);
                    if (this.b.A == null) {
                        this.b.c(motionEvent);
                    } else if (a != null) {
                        obtain.what = 20;
                        obtain.obj = a;
                        this.b.i.sendMessage(obtain);
                    } else {
                        this.b.c(motionEvent);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    private void c(final MotionEvent motionEvent) {
        this.i.post(new Runnable(this) {
            final /* synthetic */ af b;

            public void run() {
                Message obtain = Message.obtain();
                obtain.what = 19;
                obtain.arg1 = (int) motionEvent.getX();
                obtain.arg2 = (int) motionEvent.getY();
                this.b.i.sendMessage(obtain);
            }
        });
    }

    private boolean d(MotionEvent motionEvent) {
        if (this.u != null) {
            DPoint obtain = DPoint.obtain();
            b((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
            LatLng latLng = new LatLng(obtain.y, obtain.x);
            obtain.recycle();
            if (latLng != null) {
                cm a = this.S.a(latLng);
                if (a != null) {
                    this.u.onPolylineClick(new Polyline((cp) a));
                }
            }
        }
        return false;
    }

    private boolean e(MotionEvent motionEvent) throws RemoteException {
        if (this.b.b(motionEvent)) {
            kn e = this.b.e();
            if (e == null) {
                return true;
            }
            try {
                boolean onMarkerClick;
                Marker marker = new Marker((cs) e);
                this.b.a((ck) (cs) e);
                if (this.t != null) {
                    onMarkerClick = this.t.onMarkerClick(marker);
                    if (onMarkerClick || this.b.h() <= 0) {
                        return true;
                    }
                }
                onMarkerClick = true;
                a((cs) e);
                if (!e.j()) {
                    LatLng c = e.c();
                    if (c != null) {
                        Point obtain = IPoint.obtain();
                        a(c.latitude, c.longitude, (IPoint) obtain);
                        a(p.a(obtain));
                    }
                }
                return onMarkerClick;
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImp", "onMarkerTap");
                th.printStackTrace();
            }
        }
        return false;
    }

    private boolean f(MotionEvent motionEvent) {
        if (this.aY == null) {
            return false;
        }
        IPoint obtain = IPoint.obtain();
        if (this.f != null) {
            a((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
        }
        boolean a = this.aY.a(obtain);
        obtain.recycle();
        return a;
    }

    private boolean g(MotionEvent motionEvent) throws RemoteException {
        if (this.J == null || !this.J.a(motionEvent)) {
            return false;
        }
        if (this.C != null) {
            kn e = this.b.e();
            if (!e.isVisible() && e.isInfoWindowEnable()) {
                return true;
            }
            this.C.onInfoWindowClick(new Marker((cs) e));
        }
        return true;
    }

    public void drawFrame(GL10 gl10) {
        if (!this.V && this.f != null) {
            a(1, gl10);
            this.f.renderAMap();
            this.f.pushRendererState();
            if (this.av != null) {
                this.av.onDrawFrame(gl10);
            }
            a(gl10);
            C();
            if (!this.aP) {
                this.aP = true;
            }
            this.f.popRendererState();
            if (this.aC != null) {
                this.aC.a(new c(TinkerReport.KEY_APPLIED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND));
            }
        }
    }

    private void a(int i, GL10 gl10) {
        if (this.ay != -1) {
            this.aD.setRenderFps((float) this.ay);
            resetRenderTime();
        } else if (this.f.isInMapAction(i) || this.aK) {
            this.aD.setRenderFps(40.0f);
        } else if (this.f.isInMapAnimation(i)) {
            this.aD.setRenderFps(30.0f);
            this.aD.resetTickCount(15);
        } else {
            this.aD.setRenderFps(15.0f);
        }
        if (this.c.isWorldMapEnable() != MapsInitializer.isLoadWorldGridMap()) {
            g(true);
            this.c.setWorldMapEnable(MapsInitializer.isLoadWorldGridMap());
        }
    }

    private void a(GL10 gl10) {
        int i = 1;
        if (this.ak) {
            if (!this.f.canStopMapRender(1)) {
                i = 0;
            }
            Message obtainMessage = this.i.obtainMessage(15, en.a(0, 0, getMapWidth(), getMapHeight()));
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
            this.ak = false;
        }
    }

    public void p() {
        boolean z = false;
        if (this.c.getMapRect() == null || this.at) {
            B();
            this.at = false;
        }
        this.f.getCurTileIDs(1, this.c.getCurTileIds());
        GLMapState mapState = this.f.getMapState(1);
        if (mapState != null) {
            mapState.getViewMatrix(this.c.getViewMatrix());
            mapState.getProjectionMatrix(this.c.getProjectionMatrix());
            this.c.updateFinalMatrix();
            Point mapGeoCenter = mapState.getMapGeoCenter();
            this.c.setSX(mapGeoCenter.x);
            this.c.setSY(mapGeoCenter.y);
            this.c.setSZ(mapState.getMapZoomer());
            this.c.setSC(mapState.getCameraDegree());
            this.c.setSR(mapState.getMapAngle());
            if (this.c.isMapStateChange()) {
                this.c.setSkyHeight(mapState.getSkyHeight());
                DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) mapGeoCenter.x, (long) mapGeoCenter.y, 20);
                CameraPosition cameraPosition = new CameraPosition(new LatLng(pixelsToLatLong.y, pixelsToLatLong.x, false), this.c.getSZ(), this.c.getSC(), this.c.getSR());
                pixelsToLatLong.recycle();
                Message obtainMessage = this.i.obtainMessage();
                obtainMessage.what = 10;
                obtainMessage.obj = cameraPosition;
                this.i.sendMessage(obtainMessage);
                this.aL = true;
                j();
                B();
                try {
                    if (this.N.isZoomControlsEnabled() && this.c.isNeedUpdateZoomControllerState()) {
                        this.X.invalidateZoomController(this.c.getSZ());
                    }
                    if (this.c.getChangeGridRatio() != 1.0d) {
                        g(true);
                    }
                    if (this.N.isCompassEnabled() && (this.c.isTiltChanged() || this.c.isBearingChanged())) {
                        z = true;
                    }
                    if (z) {
                        this.X.invalidateCompassView();
                    }
                    if (this.N.isScaleControlsEnabled()) {
                        this.X.invalidateScaleView();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else if (!this.aK && this.f.getAnimateionsCount() == 0 && this.f.getStateMessageCount() == 0) {
                onChangeFinish();
            }
        }
    }

    private void B() {
        try {
            this.c.setMapRect(en.a((lj) this, true));
            GLMapState mapState = this.f.getMapState(1);
            if (mapState != null) {
                mapState.getPixel20Bound(this.k, getMapWidth(), getMapHeight());
                this.c.getGeoRectangle().updateRect(this.k, this.c.getSX(), this.c.getSY());
                this.c.setMapPerPixelUnitLength(mapState.getGLUnitWithWin(1));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void C() {
        if (this.bl < ((long) 2)) {
            this.bl++;
            return;
        }
        final fo f = this.Q.f();
        if (f != null && f.getVisibility() != 8) {
            if (!this.aa) {
                this.i.sendEmptyMessage(16);
                this.aa = true;
                g(true);
            }
            this.i.post(new Runnable(this) {
                final /* synthetic */ af b;

                public void run() {
                    if (!this.b.Y) {
                        try {
                            if (this.b.d != null) {
                                this.b.setIndoorBuildingInfo(this.b.d);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        f.a(false);
                    }
                }
            });
        }
    }

    void g(boolean z) {
        int i;
        Handler handler = this.i;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        handler.obtainMessage(17, i, 0).sendToTarget();
    }

    public void m(final int i) {
        queueEvent(new Runnable(this) {
            final /* synthetic */ af b;

            public void run() {
                if (this.b.aN && this.b.f != null) {
                    this.b.f.setHighlightSubwayEnable(i, false);
                }
            }
        });
    }

    private Poi b(int i, int i2, int i3) {
        if (!this.aN) {
            return null;
        }
        try {
            MapLabelItem mapLabelItem;
            ArrayList a = a(1, i, i2, i3);
            if (a == null || a.size() <= 0) {
                mapLabelItem = null;
            } else {
                mapLabelItem = (MapLabelItem) a.get(0);
            }
            if (mapLabelItem == null) {
                return null;
            }
            DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong((long) mapLabelItem.pixel20X, (long) mapLabelItem.pixel20Y, 20);
            Poi poi = new Poi(mapLabelItem.name, new LatLng(pixelsToLatLong.y, pixelsToLatLong.x, false), mapLabelItem.poiid);
            pixelsToLatLong.recycle();
            return poi;
        } catch (Throwable th) {
            return null;
        }
    }

    public ArrayList<MapLabelItem> a(int i, int i2, int i3, int i4) {
        if (!this.aN) {
            return null;
        }
        ArrayList<MapLabelItem> arrayList = new ArrayList();
        byte[] labelBuffer = this.f.getLabelBuffer(i, i2, i3, i4);
        if (labelBuffer == null) {
            return null;
        }
        int i5 = 4;
        int i6 = GLConvertUtil.getInt(labelBuffer, 0) >= 1 ? 1 : 0;
        for (int i7 = 0; i7 < i6; i7++) {
            MapLabelItem mapLabelItem = new MapLabelItem();
            int i8 = GLConvertUtil.getInt(labelBuffer, i5);
            i5 += 4;
            int i9 = GLConvertUtil.getInt(labelBuffer, i5);
            i5 += 4;
            mapLabelItem.x = i8;
            mapLabelItem.y = this.P.getHeight() - i9;
            mapLabelItem.pixel20X = GLConvertUtil.getInt(labelBuffer, i5);
            i8 = i5 + 4;
            mapLabelItem.pixel20Y = GLConvertUtil.getInt(labelBuffer, i8);
            i8 += 4;
            mapLabelItem.pixel20Z = GLConvertUtil.getInt(labelBuffer, i8);
            i8 += 4;
            mapLabelItem.type = GLConvertUtil.getInt(labelBuffer, i8);
            i8 += 4;
            mapLabelItem.mSublayerId = GLConvertUtil.getInt(labelBuffer, i8);
            i8 += 4;
            mapLabelItem.timeStamp = GLConvertUtil.getInt(labelBuffer, i8);
            i5 = i8 + 4;
            mapLabelItem.mIsFouces = labelBuffer[i5] != (byte) 0;
            i9 = i5 + 1;
            if (labelBuffer[i9] == (byte) 0) {
                mapLabelItem.poiid = null;
            } else {
                String str = "";
                i8 = 0;
                while (i8 < 20 && labelBuffer[i8 + i9] != (byte) 0) {
                    str = str + ((char) labelBuffer[i8 + i9]);
                    i8++;
                }
                mapLabelItem.poiid = str;
            }
            i8 = i9 + 20;
            i5 = i8 + 1;
            byte b = labelBuffer[i8];
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 = (byte) 0; b2 < b; b2++) {
                stringBuffer.append((char) GLConvertUtil.getShort(labelBuffer, i5));
                i5 += 2;
            }
            mapLabelItem.name = stringBuffer.toString();
            arrayList.add(mapLabelItem);
        }
        return arrayList;
    }

    public float n(int i) {
        if (this.c != null) {
            return this.c.getSR();
        }
        return 0.0f;
    }

    public void a(int i, IPoint iPoint) {
        if (this.c != null) {
            iPoint.x = this.c.getSX();
            iPoint.y = this.c.getSY();
        }
    }

    public float o(int i) {
        if (this.c != null) {
            return this.c.getSC();
        }
        return 0.0f;
    }

    public void a(int i, AbstractGestureMapMessage abstractGestureMapMessage) {
        if (this.aN && this.f != null) {
            try {
                abstractGestureMapMessage.isUseAnchor = this.Z;
                abstractGestureMapMessage.anchorX = this.c.getAnchorX();
                abstractGestureMapMessage.anchorY = this.c.getAnchorY();
                this.f.addGestureMessage(i, abstractGestureMapMessage, this.N.isGestureScaleByMapCenter(), this.c.getAnchorX(), this.c.getAnchorY());
            } catch (RemoteException e) {
            }
        }
    }

    public void p(int i) {
        if (this.aD != null) {
            this.aD.renderPause();
        }
        s(i);
    }

    public void q(int i) {
        s(i);
        if (this.aD != null) {
            this.aD.renderResume();
        }
    }

    public void q() {
        if (this.aD != null) {
            this.aD.resetTickCount(30);
        }
    }

    public void resetRenderTime() {
        if (this.aD != null) {
            this.aD.resetTickCount(2);
        }
    }

    public void r() {
        if (this.aD != null) {
            this.aD.resetTickCount(2);
        }
    }

    public void s() {
        if (this.aN && this.aD != null && !this.aD.isRenderPause()) {
            requestRender();
        }
    }

    public void requestRender() {
        if (this.aD != null && !this.aD.isRenderPause()) {
            this.P.requestRender();
        }
    }

    public int getRenderMode() {
        return this.P.getRenderMode();
    }

    private void D() {
        if (!this.ar) {
            try {
                this.ap.setName("AuthThread");
                this.ap.start();
                this.ar = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void E() {
        if (!this.as) {
            try {
                if (this.aq == null) {
                    this.aq = new gj(this.e, this);
                }
                this.aq.setName("AuthProThread");
                this.aq.start();
                this.as = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public float t() {
        return this.aH;
    }

    public synchronized void b(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4, false, false, null);
    }

    public synchronized void a(int i, int i2, int i3, int i4, boolean z, boolean z2, StyleItem[] styleItemArr) {
        if (this.aO && this.aN && this.a) {
            r(i3);
            final int i5 = i;
            final int i6 = i2;
            final int i7 = i3;
            final int i8 = i4;
            final boolean z3 = z;
            final boolean z4 = z2;
            final StyleItem[] styleItemArr2 = styleItemArr;
            queueEvent(new Runnable(this) {
                final /* synthetic */ af h;

                public void run() {
                    try {
                        this.h.f.setMapModeAndStyle(i5, i6, i7, i8, z3, z4, styleItemArr2);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } else {
            this.bb.g = i;
            this.bb.d = i2;
            this.bb.e = i3;
            this.bb.f = i4;
            this.bb.b = true;
        }
    }

    protected void r(int i) {
        if (this.Q == null) {
            return;
        }
        if (i == 0) {
            if (this.Q.d()) {
                this.Q.g(false);
                this.Q.e();
            }
        } else if (!this.Q.d()) {
            this.Q.g(true);
            this.Q.e();
        }
    }

    public void s(final int i) {
        queueEvent(new Runnable(this) {
            final /* synthetic */ af b;

            public void run() {
                try {
                    this.b.f.clearAllMessages(i);
                    this.b.f.clearAnimations(i, true);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void a(final int i, final boolean z) {
        if (this.aN && this.aO) {
            resetRenderTime();
            queueEvent(new Runnable(this) {
                final /* synthetic */ af c;

                public void run() {
                    try {
                        this.c.f.setBuildingEnable(i, z);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return;
        }
        this.bd.c = z;
        this.bd.b = true;
        this.bd.g = i;
    }

    public void b(final int i, final boolean z) {
        if (this.aN && this.aO) {
            resetRenderTime();
            queueEvent(new Runnable(this) {
                final /* synthetic */ af c;

                public void run() {
                    if (this.c.f != null) {
                        if (z) {
                            this.c.f.setAllContentEnable(i, true);
                        } else {
                            this.c.f.setAllContentEnable(i, false);
                        }
                        this.c.f.setSimple3DEnable(i, false);
                    }
                }
            });
            return;
        }
        this.bf.c = z;
        this.bf.b = true;
        this.bf.g = i;
    }

    public void c(final int i, final boolean z) {
        if (this.aN && this.aO) {
            resetRenderTime();
            queueEvent(new Runnable(this) {
                final /* synthetic */ af c;

                public void run() {
                    try {
                        if (z) {
                            this.c.f.setBuildingTextureEnable(i, true);
                        } else {
                            this.c.f.setBuildingTextureEnable(i, false);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return;
        }
        this.bi.c = z;
        this.bi.b = true;
        this.bi.g = i;
    }

    public synchronized void a(int i, GL10 gl10, EGLConfig eGLConfig) {
        if (this.an == 3) {
            this.Q.f().a(fo.b);
        } else {
            this.Q.f().a(fo.a);
        }
        this.aO = false;
        this.g = this.P.getWidth();
        this.h = this.P.getHeight();
        this.aQ = false;
        try {
            AeUtil.loadLib(this.e);
            this.f.createAMapInstance(AeUtil.initResource(this.e));
            y(i);
            this.aW = new db();
            this.S.a(this.aW);
            this.aN = true;
            this.l = gl10.glGetString(7937);
        } catch (Throwable th) {
            gz.c(th, "AMapDElegateImp", "createSurface");
        }
        GLMapState mapState = this.f.getMapState(1);
        if (!(mapState == null || mapState.getNativeInstance() == 0)) {
            mapState.setMapGeoCenter(this.c.getSX(), this.c.getSY());
            mapState.setMapAngle(this.c.getSR());
            mapState.setMapZoomer(this.c.getSZ());
            mapState.setCameraDegree(this.c.getSC());
        }
        this.aX.a(this.e);
        D();
        if (this.av != null) {
            this.av.onSurfaceCreated(gl10, eGLConfig);
        }
        u();
    }

    protected void u() {
        AMapNativeRenderer.nativeDrawLineInit();
    }

    public void a(int i, GL10 gl10, int i2, int i3) {
        this.aQ = false;
        if (!this.aN) {
            a(i, gl10, null);
        }
        this.g = i2;
        this.h = i3;
        this.at = true;
        this.am = new Rect(0, 0, i2, i3);
        this.U = a(i, new Rect(0, 0, this.g, this.h), this.g, this.h);
        if (!this.aO) {
            if (this.c != null) {
                this.c.setMapZoomScale(this.aH);
                this.c.setMapWidth(i2);
                this.c.setMapHeight(i3);
            }
            this.f.setIndoorEnable(this.U, false);
            this.f.setSimple3DEnable(this.U, false);
        }
        if (this.aC != null) {
            this.aC.a(new c(TinkerReport.KEY_APPLIED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND));
        }
        synchronized (this) {
            this.aO = true;
        }
        if (this.Z) {
            this.c.setAnchorX(Math.max(1, Math.min(this.aT, i2 - 1)));
            this.c.setAnchorY(Math.max(1, Math.min(this.aU, i3 - 1)));
        } else {
            this.c.setAnchorX(i2 >> 1);
            this.c.setAnchorY(i3 >> 1);
        }
        this.f.setProjectionCenter(this.U, this.c.getAnchorX(), this.c.getAnchorY());
        this.a = true;
        if (this.bf.b) {
            this.bf.run();
        }
        if (this.bb.b) {
            this.bb.run();
        }
        if (this.bc.b) {
            this.bc.run();
        }
        if (this.aZ.b) {
            this.aZ.run();
        }
        if (this.bd.b) {
            this.bd.run();
        }
        if (this.bi.b) {
            this.bi.run();
        }
        if (this.be.b) {
            this.be.run();
        }
        if (this.bg.b) {
            this.bg.run();
        }
        if (this.ba.b) {
            this.ba.run();
        }
        if (this.bj.b) {
            this.bj.run();
        }
        if (this.av != null) {
            this.av.onSurfaceChanged(gl10, i2, i3);
        }
        if (this.i != null) {
            this.i.post(this.bh);
        }
    }

    public void destroySurface(int i) {
        this.aR.lock();
        try {
            if (this.aN) {
                this.f.destroyAMapEngine();
            }
            this.aN = false;
            this.aO = false;
            this.aQ = false;
        } catch (Throwable th) {
        } finally {
            this.aR.unlock();
        }
    }

    public Context v() {
        return this.e;
    }

    private void y(int i) {
    }

    public int a(int i, Rect rect, int i2, int i3) {
        int i4 = 0;
        if (!(this.f == null || i < 0 || rect == null)) {
            i4 = this.f.getEngineIDWithType(i);
            if (this.f.isEngineCreated(i4)) {
                a(i4, rect.left, rect.top, rect.width(), rect.height(), i2, i3);
            } else {
                int i5 = this.e.getResources().getDisplayMetrics().densityDpi;
                float f = this.e.getResources().getDisplayMetrics().density;
                this.aH = GLMapState.calMapZoomScalefactor(i2, i3, i5);
                MapViewInitParam mapViewInitParam = new MapViewInitParam();
                mapViewInitParam.engineId = i4;
                mapViewInitParam.x = rect.left;
                mapViewInitParam.y = rect.top;
                mapViewInitParam.width = rect.width();
                mapViewInitParam.height = rect.height();
                mapViewInitParam.screenWidth = i2;
                mapViewInitParam.screenHeight = i3;
                mapViewInitParam.screenScale = f;
                mapViewInitParam.textScale = f * this.aI;
                mapViewInitParam.mapZoomScale = this.aH;
                this.f.createAMapEngineWithFrame(mapViewInitParam);
                GLMapState mapState = this.f.getMapState(i4);
                mapState.setMapZoomer(this.c.getSZ());
                mapState.setCameraDegree(this.c.getSC());
                mapState.setMapAngle(this.c.getSR());
                mapState.setMapGeoCenter(this.c.getSX(), this.c.getSY());
                this.f.setMapState(i4, mapState);
                this.f.setOvelayBundle(i4, new GLOverlayBundle(i4, this));
            }
        }
        return i4;
    }

    public int a(EAMapPlatformGestureInfo eAMapPlatformGestureInfo) {
        if (this.f != null) {
            return this.f.getEngineIDWithGestureInfo(eAMapPlatformGestureInfo);
        }
        return 1;
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (this.f != null) {
            this.f.setServiceViewRect(i, i2, i3, i4, i5, i6, i7);
        }
    }

    private boolean c(int i, int i2) {
        if (this.f != null) {
            return this.f.getSrvViewStateBoolValue(i, i2);
        }
        return false;
    }

    public CameraPosition getCameraPosition() throws RemoteException {
        return f(this.Z);
    }

    public float getMaxZoomLevel() {
        if (this.c != null) {
            return this.c.getMaxZoomLevel();
        }
        return 20.0f;
    }

    public float getMinZoomLevel() {
        if (this.c != null) {
            return this.c.getMinZoomLevel();
        }
        return 3.0f;
    }

    public void moveCamera(CameraUpdate cameraUpdate) throws RemoteException {
        if (cameraUpdate != null) {
            a(cameraUpdate.getCameraUpdateFactoryDelegate());
        }
    }

    public void a(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException {
        if (this.f != null && !this.V) {
            if (this.Y && this.f.getStateMessageCount() > 0) {
                AbstractCameraUpdateMessage c = p.c();
                c.nowType = Type.changeGeoCenterZoomTiltBearing;
                c.geoPoint = new Point(this.c.getSX(), this.c.getSY());
                c.zoom = this.c.getSZ();
                c.bearing = this.c.getSR();
                c.tilt = this.c.getSC();
                this.f.addMessage(abstractCameraUpdateMessage, false);
                while (this.f.getStateMessageCount() > 0) {
                    AbstractCameraUpdateMessage stateMessage = this.f.getStateMessage();
                    if (stateMessage != null) {
                        stateMessage.mergeCameraUpdateDelegate(c);
                    }
                }
                abstractCameraUpdateMessage = c;
            }
            resetRenderTime();
            this.f.clearAnimations(1, false);
            abstractCameraUpdateMessage.isChangeFinished = true;
            c(abstractCameraUpdateMessage);
            this.f.addMessage(abstractCameraUpdateMessage, false);
        }
    }

    private void c(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        abstractCameraUpdateMessage.isUseAnchor = this.Z;
        if (this.Z) {
            abstractCameraUpdateMessage.anchorX = this.c.getAnchorX();
            abstractCameraUpdateMessage.anchorY = this.c.getAnchorY();
        }
        if (abstractCameraUpdateMessage.width == 0) {
            abstractCameraUpdateMessage.width = getMapWidth();
        }
        if (abstractCameraUpdateMessage.height == 0) {
            abstractCameraUpdateMessage.height = getMapHeight();
        }
        abstractCameraUpdateMessage.mapConfig = this.c;
    }

    public void animateCamera(CameraUpdate cameraUpdate) throws RemoteException {
        if (cameraUpdate != null) {
            b(cameraUpdate.getCameraUpdateFactoryDelegate());
        }
    }

    public void b(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException {
        a(abstractCameraUpdateMessage, 250, null);
    }

    public void animateCameraWithCallback(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) throws RemoteException {
        if (cameraUpdate != null) {
            animateCameraWithDurationAndCallback(cameraUpdate, 250, cancelableCallback);
        }
    }

    public void animateCameraWithDurationAndCallback(CameraUpdate cameraUpdate, long j, CancelableCallback cancelableCallback) {
        if (cameraUpdate != null) {
            a(cameraUpdate.getCameraUpdateFactoryDelegate(), j, cancelableCallback);
        }
    }

    public void a(AbstractCameraUpdateMessage abstractCameraUpdateMessage, long j, CancelableCallback cancelableCallback) {
        if (abstractCameraUpdateMessage != null && !this.V && this.f != null) {
            abstractCameraUpdateMessage.mCallback = cancelableCallback;
            abstractCameraUpdateMessage.mDuration = j;
            if (this.Y || getMapHeight() == 0 || getMapWidth() == 0) {
                try {
                    a(abstractCameraUpdateMessage);
                    if (abstractCameraUpdateMessage.mCallback != null) {
                        abstractCameraUpdateMessage.mCallback.onFinish();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
            try {
                this.f.interruptAnimation();
                resetRenderTime();
                c(abstractCameraUpdateMessage);
                this.f.addMessage(abstractCameraUpdateMessage, true);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public void stopAnimation() throws RemoteException {
        if (this.f != null) {
            this.f.interruptAnimation();
        }
        resetRenderTime();
    }

    public Polyline addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        resetRenderTime();
        IPolyline a = this.S.a(polylineOptions);
        if (a != null) {
            return new Polyline(a);
        }
        return null;
    }

    public BuildingOverlay addBuildingOverlay() {
        try {
            ch a = this.S.a();
            if (a != null) {
                return new BuildingOverlay(a);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GL3DModel addGLModel(GL3DModelOptions gL3DModelOptions) {
        return this.W.a(gL3DModelOptions);
    }

    public NavigateArrow addNavigateArrow(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        resetRenderTime();
        INavigateArrow a = this.S.a(navigateArrowOptions);
        if (a != null) {
            return new NavigateArrow(a);
        }
        return null;
    }

    public Polygon addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        resetRenderTime();
        IPolygon a = this.S.a(polygonOptions);
        if (a != null) {
            return new Polygon(a);
        }
        return null;
    }

    public Circle addCircle(CircleOptions circleOptions) throws RemoteException {
        resetRenderTime();
        ICircle a = this.S.a(circleOptions);
        if (a != null) {
            return new Circle(a);
        }
        return null;
    }

    public Arc addArc(ArcOptions arcOptions) throws RemoteException {
        resetRenderTime();
        IArc a = this.S.a(arcOptions);
        if (a != null) {
            return new Arc(a);
        }
        return null;
    }

    public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        resetRenderTime();
        IGroundOverlay a = this.S.a(groundOverlayOptions);
        if (a != null) {
            return new GroundOverlay(a);
        }
        return null;
    }

    public MultiPointOverlay addMultiPointOverlay(MultiPointOverlayOptions multiPointOverlayOptions) throws RemoteException {
        resetRenderTime();
        IMultiPointOverlay a = this.aY.a(multiPointOverlayOptions);
        if (a != null) {
            return new MultiPointOverlay(a);
        }
        return null;
    }

    public Marker addMarker(MarkerOptions markerOptions) throws RemoteException {
        resetRenderTime();
        return this.b.a(markerOptions);
    }

    public Text addText(TextOptions textOptions) throws RemoteException {
        resetRenderTime();
        return this.b.a(textOptions);
    }

    public ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        resetRenderTime();
        return this.b.a((ArrayList) arrayList, z);
    }

    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        return this.R.a(tileOverlayOptions);
    }

    public void clear() throws RemoteException {
        try {
            clear(false);
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "clear");
            th.printStackTrace();
        }
    }

    public void clear(boolean z) throws RemoteException {
        String str = null;
        try {
            String c;
            i();
            if (this.ab != null) {
                if (z) {
                    c = this.ab.c();
                    str = this.ab.d();
                    this.S.b(str);
                    this.R.c();
                    this.b.a(c);
                    this.W.b();
                    if (this.Q != null) {
                        this.Q.m();
                    }
                    if (this.aY != null) {
                        this.aY.c();
                    }
                    resetRenderTime();
                }
                this.ab.e();
            }
            c = null;
            this.S.b(str);
            this.R.c();
            this.b.a(c);
            this.W.b();
            if (this.Q != null) {
                this.Q.m();
            }
            if (this.aY != null) {
                this.aY.c();
            }
            resetRenderTime();
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "clear");
            th.printStackTrace();
        }
    }

    public int getMapType() throws RemoteException {
        return this.an;
    }

    public void setMapType(int i) throws RemoteException {
        if (i != this.an || (this.c != null && this.c.isCustomStyleEnable())) {
            if (this.aC != null) {
                this.aC.a(new c(1, Integer.valueOf(i)));
            }
            this.an = i;
        }
    }

    public void t(int i) {
        int i2 = 2;
        int i3 = 1;
        int i4 = 0;
        this.an = i;
        if (i == 1) {
            i3 = 0;
            i2 = 0;
        } else if (i == 2) {
            i2 = 1;
            i3 = 0;
        } else if (i == 3) {
            i2 = 0;
            i4 = 4;
        } else if (i == 4) {
            i3 = 0;
            i2 = 0;
            i4 = 4;
        } else if (i == 5) {
            i3 = 0;
            i4 = 5;
        } else {
            this.an = 1;
            i3 = 0;
            i2 = 0;
        }
        try {
            this.c.setMapStyleMode(i2);
            this.c.setMapStyleTime(i3);
            this.c.setMapStyleState(i4);
            if (this.c.isCustomStyleEnable()) {
                a(1, i2, i3, i4, true, false, null);
                this.c.setCustomStyleEnable(false);
                this.N.setLogoEnable(true);
            } else {
                if (this.c.getMapLanguage().equals(AMap.ENGLISH)) {
                    setMapLanguage("zh_cn");
                }
                b(1, i2, i3, i4);
            }
            resetRenderTime();
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "setMaptype");
            th.printStackTrace();
        }
    }

    public boolean isTrafficEnabled() throws RemoteException {
        return this.c.isTrafficEnabled();
    }

    public void setTrafficEnabled(final boolean z) throws RemoteException {
        if (!this.aN || this.V) {
            this.aZ.c = z;
            this.aZ.b = true;
            this.aZ.g = 1;
            return;
        }
        queueEvent(new Runnable(this, z) {
            final /* synthetic */ boolean a;
            final /* synthetic */ af c;

            public void run() {
                try {
                    if (this.c.c.isTrafficEnabled() != this.a) {
                        this.c.c.setTrafficEnabled(z);
                        this.c.aD.setTrafficMode(this.a);
                        if (this.a) {
                            this.c.f.setTrafficEnable(1, this.a);
                            this.c.resetRenderTime();
                        } else {
                            this.c.f.setTrafficEnable(1, this.a);
                            this.c.resetRenderTime();
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public boolean isIndoorEnabled() throws RemoteException {
        return this.c.isIndoorEnable();
    }

    public void setIndoorEnabled(final boolean z) throws RemoteException {
        if (!this.aN || this.V) {
            this.bg.c = z;
            this.bg.b = true;
            this.bg.g = 1;
            return;
        }
        this.c.setIndoorEnable(z);
        resetRenderTime();
        if (!z) {
            if (this.f != null) {
                this.f.setIndoorEnable(1, false);
            }
            this.c.maxZoomLevel = this.c.isSetLimitZoomLevel() ? this.c.getMaxZoomLevel() : 20.0f;
            if (this.N.isZoomControlsEnabled()) {
                this.X.invalidateZoomController(this.c.getSZ());
            }
        } else if (this.f != null) {
            this.f.setIndoorEnable(1, true);
        }
        if (this.N.isIndoorSwitchEnabled()) {
            this.i.post(new Runnable(this) {
                final /* synthetic */ af b;

                public void run() {
                    if (z) {
                        this.b.b(true);
                    } else if (this.b.Q != null && this.b.Q.g() != null) {
                        this.b.Q.g().a(false);
                    }
                }
            });
        }
    }

    public void set3DBuildingEnabled(boolean z) throws RemoteException {
        p(1);
        a(1, z);
        q(1);
    }

    public boolean isMyLocationEnabled() throws RemoteException {
        return this.T;
    }

    public void setMyLocationEnabled(boolean z) throws RemoteException {
        if (!this.V) {
            try {
                if (this.Q != null) {
                    fr h = this.Q.h();
                    if (this.ac == null) {
                        h.a(false);
                    } else if (z) {
                        this.ac.activate(this.F);
                        h.a(true);
                        if (this.ab == null) {
                            this.ab = new ct(this, this.e);
                        }
                    } else {
                        if (this.ab != null) {
                            this.ab.b();
                            this.ab = null;
                        }
                        this.ac.deactivate();
                    }
                }
                if (!z) {
                    this.N.setMyLocationButtonEnabled(z);
                }
                this.T = z;
                resetRenderTime();
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImp", "setMyLocationEnabled");
                th.printStackTrace();
            }
        }
    }

    public void setLoadOfflineData(final boolean z) throws RemoteException {
        queueEvent(new Runnable(this) {
            final /* synthetic */ af b;

            public void run() {
                if (this.b.f != null) {
                    this.b.f.setOfflineDataEnable(1, z);
                }
            }
        });
    }

    public void setMyLocationStyle(MyLocationStyle myLocationStyle) throws RemoteException {
        if (!this.V) {
            if (this.ab == null) {
                this.ab = new ct(this, this.e);
            }
            if (this.ab != null) {
                if (myLocationStyle.getInterval() < ((long) 1000)) {
                    myLocationStyle.interval((long) 1000);
                }
                if (this.ac != null && (this.ac instanceof ad)) {
                    ((ad) this.ac).a(myLocationStyle.getInterval());
                    ((ad) this.ac).a(myLocationStyle.getMyLocationType());
                }
                this.ab.a(myLocationStyle);
            }
        }
    }

    public void setMyLocationType(int i) throws RemoteException {
        if (this.ab != null && this.ab.a() != null) {
            this.ab.a().myLocationType(i);
            setMyLocationStyle(this.ab.a());
        }
    }

    public List<Marker> getMapScreenMarkers() throws RemoteException {
        if (en.b(getMapWidth(), getMapHeight())) {
            return this.b.f();
        }
        return new ArrayList();
    }

    public void setMapTextEnable(final boolean z) throws RemoteException {
        if (this.aN && this.aO) {
            resetRenderTime();
            queueEvent(new Runnable(this) {
                final /* synthetic */ af b;

                public void run() {
                    try {
                        this.b.f.setLabelEnable(1, z);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return;
        }
        this.be.c = z;
        this.be.b = true;
        this.be.g = 1;
    }

    public void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) throws RemoteException {
        if (!this.V) {
            this.ao = myTrafficStyle;
            if (this.aN && this.aO && myTrafficStyle != null) {
                resetRenderTime();
                queueEvent(new Runnable(this) {
                    final /* synthetic */ af a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.f.setTrafficStyle(1, this.a.ao.getSmoothColor(), this.a.ao.getSlowColor(), this.a.ao.getCongestedColor(), this.a.ao.getSeriousCongestedColor());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
                return;
            }
            this.bj.c = false;
            this.bj.b = true;
            this.bj.g = 1;
        }
    }

    public Location getMyLocation() throws RemoteException {
        if (this.ac != null) {
            return this.F.a;
        }
        return null;
    }

    public void setLocationSource(LocationSource locationSource) throws RemoteException {
        try {
            if (!this.V) {
                if (this.ac != null && (this.ac instanceof ad)) {
                    this.ac.deactivate();
                }
                this.ac = locationSource;
                if (locationSource != null) {
                    this.Q.h().a(true);
                } else {
                    this.Q.h().a(false);
                }
            }
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "setLocationSource");
            th.printStackTrace();
        }
    }

    public void setMyLocationRotateAngle(float f) throws RemoteException {
        if (this.ab != null) {
            this.ab.a(f);
        }
    }

    public UiSettings getAMapUiSettings() throws RemoteException {
        if (this.L == null) {
            this.L = new UiSettings(this.N);
        }
        return this.L;
    }

    public Projection getAMapProjection() throws RemoteException {
        return new Projection(this.M);
    }

    public void setOnMapClickListener(OnMapClickListener onMapClickListener) throws RemoteException {
        this.y = onMapClickListener;
    }

    public void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) throws RemoteException {
        this.z = onMapTouchListener;
    }

    public void setOnPOIClickListener(OnPOIClickListener onPOIClickListener) throws RemoteException {
        this.A = onPOIClickListener;
    }

    public void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        this.B = onMapLongClickListener;
    }

    public void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        this.t = onMarkerClickListener;
    }

    public void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        this.u = onPolylineClickListener;
    }

    public void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) throws RemoteException {
        this.v = onMarkerDragListener;
    }

    public void setOnMaploadedListener(OnMapLoadedListener onMapLoadedListener) throws RemoteException {
        this.w = onMapLoadedListener;
    }

    public void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        this.x = onCameraChangeListener;
    }

    public void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        this.C = onInfoWindowClickListener;
    }

    public void setOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        this.D = onIndoorBuildingActiveListener;
    }

    public void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        this.E = onMyLocationChangeListener;
    }

    public void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        if (!this.V && this.J != null) {
            this.J.a(infoWindowAdapter);
        }
    }

    public void setInfoWindowAdapter(CommonInfoWindowAdapter commonInfoWindowAdapter) throws RemoteException {
        if (!this.V && this.J != null) {
            this.J.a(commonInfoWindowAdapter);
        }
    }

    public void setOnMultiPointClickListener(OnMultiPointClickListener onMultiPointClickListener) {
        if (this.aY != null) {
            this.aY.a(onMultiPointClickListener);
        }
    }

    public String getMapContentApprovalNumber() {
        if (this.c == null || this.c.isCustomStyleEnable()) {
            return null;
        }
        Object a = ec.a(this.e, "approval_number", "mc", "");
        if (TextUtils.isEmpty(a)) {
            return "GS（2017）3426号 | GS（2017）2550号";
        }
        return a;
    }

    public String getSatelliteImageApprovalNumber() {
        Object a = ec.a(this.e, "approval_number", "si", "");
        return !TextUtils.isEmpty(a) ? a : "GS（2018）984号";
    }

    public void setMapLanguage(String str) {
        if (!TextUtils.isEmpty(str) && this.c != null && !this.c.isCustomStyleEnable() && !this.c.getMapLanguage().equals(str)) {
            if ((!str.equals(AMap.ENGLISH) ? 1 : 0) != 0) {
                this.c.setMapLanguage("zh_cn");
                this.au = 0;
            } else {
                if (this.an != 1) {
                    try {
                        setMapType(1);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                this.c.setMapLanguage(AMap.ENGLISH);
                this.au = -10000;
            }
            try {
                a(getCameraPosition());
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            this.R.a(this.c.getMapLanguage());
        }
    }

    public void getMapPrintScreen(onMapPrintScreenListener onmapprintscreenlistener) {
        this.G = onmapprintscreenlistener;
        this.ak = true;
        resetRenderTime();
    }

    public void getMapScreenShot(OnMapScreenShotListener onMapScreenShotListener) {
        this.H = onMapScreenShotListener;
        this.ak = true;
        resetRenderTime();
    }

    public float getScalePerPixel() throws RemoteException {
        try {
            return ((float) ((((Math.cos((getCameraPosition().target.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, (double) g()) * 256.0d))) * t();
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "getScalePerPixel");
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setRunLowFrame(boolean z) {
        if (!z) {
            s();
        }
    }

    public void removecache() throws RemoteException {
        removecache(null);
    }

    public void removecache(OnCacheRemoveListener onCacheRemoveListener) throws RemoteException {
        if (this.i != null && this.f != null) {
            try {
                Runnable dVar = new d(this, this.e, onCacheRemoveListener);
                this.i.removeCallbacks(dVar);
                this.i.post(dVar);
            } catch (Throwable th) {
                gz.c(th, "AMapDelegateImp", "removecache");
                th.printStackTrace();
            }
        }
    }

    public void setCustomRenderer(CustomRenderer customRenderer) throws RemoteException {
        this.av = customRenderer;
    }

    public void setCenterToPixel(int i, int i2) throws RemoteException {
        this.Z = true;
        this.aT = i;
        this.aU = i2;
        if (!this.aO || !this.aN) {
            return;
        }
        if (this.c.getAnchorX() != this.aT || this.c.getAnchorY() != this.aU) {
            this.c.setAnchorX(this.aT);
            this.c.setAnchorY(this.aU);
            queueEvent(new Runnable(this) {
                final /* synthetic */ af a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        this.a.c.setAnchorX(Math.max(0, Math.min(this.a.aT, this.a.g)));
                        this.a.c.setAnchorY(Math.max(0, Math.min(this.a.aU, this.a.h)));
                        this.a.f.setProjectionCenter(1, this.a.c.getAnchorX(), this.a.c.getAnchorY());
                        this.a.at = true;
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public void setMapTextZIndex(int i) throws RemoteException {
        this.au = i;
    }

    public int getMapTextZIndex() throws RemoteException {
        return this.au;
    }

    public void reloadMap() {
    }

    public void setRenderFps(int i) {
        try {
            this.ay = Math.max(10, Math.min(i, 40));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setIndoorBuildingInfo(IndoorBuildingInfo indoorBuildingInfo) throws RemoteException {
        if (!this.V && indoorBuildingInfo != null && indoorBuildingInfo.activeFloorName != null && indoorBuildingInfo.poiid != null) {
            this.d = (aa) indoorBuildingInfo;
            resetRenderTime();
            queueEvent(new Runnable(this) {
                final /* synthetic */ af a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.f != null) {
                        this.a.f.setIndoorBuildingToBeActive(1, this.a.d.activeFloorName, this.a.d.activeFloorIndex, this.a.d.poiid);
                    }
                }
            });
        }
    }

    public void setAMapGestureListener(AMapGestureListener aMapGestureListener) {
        if (this.aE != null) {
            this.I = aMapGestureListener;
            this.aE.a(aMapGestureListener);
        }
    }

    public float getZoomToSpanLevel(LatLng latLng, LatLng latLng2) {
        MapConfig mapConfig = getMapConfig();
        if (latLng == null || latLng2 == null || !this.aN || this.V) {
            return mapConfig.getSZ();
        }
        Builder builder = new Builder();
        builder.include(latLng);
        builder.include(latLng2);
        GLMapState gLMapState = new GLMapState(1, this.f.getNativeInstance());
        Pair a = en.a(mapConfig, 0, 0, 0, 0, builder.build(), getMapWidth(), getMapHeight());
        gLMapState.recycle();
        if (a != null) {
            return ((Float) a.first).floatValue();
        }
        return gLMapState.getMapZoomer();
    }

    public Pair<Float, LatLng> calculateZoomToSpanLevel(int i, int i2, int i3, int i4, LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null && i == i2 && i2 == i3 && i3 == i4 && latLng.latitude == latLng2.latitude && latLng.longitude == latLng2.longitude) {
            return new Pair(Float.valueOf(getMaxZoomLevel()), latLng);
        }
        MapConfig mapConfig = getMapConfig();
        if (latLng == null || latLng2 == null || !this.aN || this.V) {
            DPoint obtain = DPoint.obtain();
            GLMapState.geo2LonLat(mapConfig.getSX(), mapConfig.getSY(), obtain);
            Pair<Float, LatLng> pair = new Pair(Float.valueOf(mapConfig.getSZ()), new LatLng(obtain.y, obtain.x));
            obtain.recycle();
            return pair;
        }
        Builder builder = new Builder();
        builder.include(latLng);
        builder.include(latLng2);
        GLMapState gLMapState = new GLMapState(1, this.f.getNativeInstance());
        Pair a = en.a(mapConfig, i, i2, i3, i4, builder.build(), getMapWidth(), getMapHeight());
        gLMapState.recycle();
        if (a == null) {
            return null;
        }
        obtain = DPoint.obtain();
        GLMapState.geo2LonLat(((IPoint) a.second).x, ((IPoint) a.second).y, obtain);
        Pair<Float, LatLng> pair2 = new Pair(a.first, new LatLng(obtain.y, obtain.x));
        obtain.recycle();
        return pair2;
    }

    public InfoWindowAnimationManager getInfoWindowAnimationManager() {
        return new InfoWindowAnimationManager(this.K);
    }

    public void setMaskLayerParams(int i, int i2, int i3, int i4, final int i5, long j) {
        try {
            if (this.aw != null) {
                GLAlphaAnimation gLAlphaAnimation;
                float f = ((float) i4) / 255.0f;
                if (i5 == -1) {
                    gLAlphaAnimation = new GLAlphaAnimation(f, 0.0f);
                    gLAlphaAnimation.setAnimationListener(new AnimationListener(this) {
                        final /* synthetic */ af b;

                        public void onAnimationStart() {
                        }

                        public void onAnimationEnd() {
                            this.b.i.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass18 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.ax = i5;
                                    if (this.a.b.Q != null) {
                                        this.a.b.Q.j().setVisibility(0);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    this.ax = i5;
                    gLAlphaAnimation = new GLAlphaAnimation(0.0f, f);
                    if (f > 0.2f) {
                        if (this.Q != null) {
                            this.Q.j().setVisibility(4);
                        }
                    } else if (this.Q != null) {
                        this.Q.j().setVisibility(0);
                    }
                }
                gLAlphaAnimation.setInterpolator(new LinearInterpolator());
                gLAlphaAnimation.setDuration(j);
                this.aw.a(i, i2, i3, i4);
                this.aw.a(gLAlphaAnimation);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setMaxZoomLevel(float f) {
        this.c.setMaxZoomLevel(f);
    }

    public void setMinZoomLevel(float f) {
        this.c.setMinZoomLevel(f);
    }

    public void resetMinMaxZoomPreference() {
        this.c.resetMinMaxZoomPreference();
        try {
            if (this.N.isZoomControlsEnabled() && this.c.isNeedUpdateZoomControllerState()) {
                this.X.invalidateZoomController(this.c.getSZ());
            }
        } catch (RemoteException e) {
        }
    }

    public void setMapStatusLimits(LatLngBounds latLngBounds) {
        try {
            this.c.setLimitLatLngBounds(latLngBounds);
            F();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null) {
            return false;
        }
        return true;
    }

    private void F() {
        try {
            LatLngBounds limitLatLngBounds = this.c.getLimitLatLngBounds();
            if (this.f != null && a(limitLatLngBounds)) {
                GLMapState gLMapState = new GLMapState(1, this.f.getNativeInstance());
                GLMapState.lonlat2Geo(limitLatLngBounds.northeast.longitude, limitLatLngBounds.northeast.latitude, IPoint.obtain());
                GLMapState.lonlat2Geo(limitLatLngBounds.southwest.longitude, limitLatLngBounds.southwest.latitude, IPoint.obtain());
                this.c.setLimitIPoints(new IPoint[]{r2, r3});
                gLMapState.recycle();
                return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c.setLimitIPoints(null);
    }

    public Handler getMainHandler() {
        return this.i;
    }

    public void onChangeFinish() {
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.what = 11;
        this.i.sendMessage(obtainMessage);
    }

    protected void a(boolean z, CameraPosition cameraPosition) {
        if (this.c != null && this.c.getChangedCounter() != 0) {
            try {
                this.c.resetChangedCounter();
                if (this.I != null) {
                    this.I.onMapStable();
                }
                if (this.x != null && this.P.isEnabled()) {
                    if (cameraPosition == null) {
                        cameraPosition = getCameraPosition();
                    }
                    this.x.onCameraChangeFinish(cameraPosition);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setZoomScaleParam(float f) {
        this.aH = f;
    }

    public void onFling() {
        if (this.R != null) {
            this.R.b(true);
        }
        this.aj = true;
    }

    public int getMapWidth() {
        return this.g;
    }

    public int getMapHeight() {
        return this.h;
    }

    public float getCameraAngle() {
        return o(this.U);
    }

    public float getSkyHeight() {
        return this.c.getSkyHeight();
    }

    public boolean isMaploaded() {
        return this.aa;
    }

    public MapConfig getMapConfig() {
        return this.c;
    }

    public View getView() throws RemoteException {
        return this.Q;
    }

    public void setZOrderOnTop(boolean z) throws RemoteException {
    }

    public void onIndoorBuildingActivity(int i, byte[] bArr) {
        aa aaVar = null;
        if (bArr != null) {
            try {
                aa aaVar2 = new aa();
                byte b = bArr[0];
                aaVar2.a = new String(bArr, 1, b, "utf-8");
                int i2 = b + 1;
                int i3 = i2 + 1;
                b = bArr[i2];
                aaVar2.b = new String(bArr, i3, b, "utf-8");
                i2 = b + i3;
                i3 = i2 + 1;
                b = bArr[i2];
                aaVar2.activeFloorName = new String(bArr, i3, b, "utf-8");
                i2 = b + i3;
                aaVar2.activeFloorIndex = GLConvertUtil.getInt(bArr, i2);
                i2 += 4;
                i3 = i2 + 1;
                b = bArr[i2];
                aaVar2.poiid = new String(bArr, i3, b, "utf-8");
                i2 = b + i3;
                i3 = i2 + 1;
                b = bArr[i2];
                aaVar2.h = new String(bArr, i3, b, "utf-8");
                i2 = b + i3;
                aaVar2.c = GLConvertUtil.getInt(bArr, i2);
                i2 += 4;
                aaVar2.floor_indexs = new int[aaVar2.c];
                aaVar2.floor_names = new String[aaVar2.c];
                aaVar2.d = new String[aaVar2.c];
                for (int i4 = 0; i4 < aaVar2.c; i4++) {
                    aaVar2.floor_indexs[i4] = GLConvertUtil.getInt(bArr, i2);
                    i3 = i2 + 4;
                    i2 = i3 + 1;
                    byte b2 = bArr[i3];
                    if (b2 > (byte) 0) {
                        aaVar2.floor_names[i4] = new String(bArr, i2, b2, "utf-8");
                        i3 = i2 + b2;
                    } else {
                        i3 = i2;
                    }
                    i2 = i3 + 1;
                    b2 = bArr[i3];
                    if (b2 > (byte) 0) {
                        aaVar2.d[i4] = new String(bArr, i2, b2, "utf-8");
                        i2 += b2;
                    }
                }
                aaVar2.e = GLConvertUtil.getInt(bArr, i2);
                i2 += 4;
                if (aaVar2.e > 0) {
                    aaVar2.f = new int[aaVar2.e];
                    int i5 = i2;
                    for (i2 = 0; i2 < aaVar2.e; i2++) {
                        aaVar2.f[i2] = GLConvertUtil.getInt(bArr, i5);
                        i5 += 4;
                    }
                }
                aaVar = aaVar2;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.bm = aaVar;
        a(new Runnable(this) {
            final /* synthetic */ af a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.aV != null) {
                    this.a.aV.a(this.a.bm);
                }
            }
        });
    }

    public AMapCameraInfo getCamerInfo() {
        return null;
    }

    public void destroy() {
        this.V = true;
        if (this.aC != null) {
            this.aC.a();
        }
        if (this.aY != null) {
            this.aY.b();
        }
        if (this.ac != null) {
            this.ac.deactivate();
        }
        this.ac = null;
        this.aV = null;
        if (this.aD != null) {
            this.aD.renderPause();
        }
        if (this.aX != null) {
            this.aX.d();
        }
        if (this.aE != null) {
            this.aE.a(null);
            this.aE.b();
            this.aE = null;
        }
        if (this.S != null) {
            this.S.c();
        }
        if (this.b != null) {
            this.b.j();
        }
        if (this.R != null) {
            this.R.f();
        }
        A();
        if (this.ap != null) {
            this.ap.interrupt();
            this.ap = null;
        }
        if (this.aq != null) {
            this.aq.interrupt();
            this.aq = null;
        }
        if (this.aA != null) {
            this.aA.a();
            this.aA = null;
        }
        if (this.aB != null) {
            this.aB.a(null);
            this.aB.a();
            this.aB = null;
        }
        dv.b();
        if (this.f != null) {
            this.f.setMapListener(null);
            this.f.releaseNetworkState();
            queueEvent(new Runnable(this) {
                final /* synthetic */ af a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        if (this.a.aX != null) {
                            this.a.aX.c();
                        }
                        if (this.a.aW != null) {
                            this.a.aW.b();
                            this.a.aW = null;
                        }
                        if (this.a.f != null) {
                            this.a.f.destroyAMapEngine();
                            this.a.f = null;
                        }
                        this.a.W.d();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            int i = 0;
            while (this.f != null) {
                int i2 = i + 1;
                if (i < 20) {
                    try {
                        Thread.sleep(50);
                        i = i2;
                    } catch (InterruptedException e) {
                        i = i2;
                    }
                }
            }
        }
        try {
            if (this.W != null) {
                this.W.c();
            }
            if (this.J != null) {
                this.J.b();
            }
            if (this.P != null) {
                this.P.b();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImp", "destroy");
            th.printStackTrace();
            return;
        }
        if (this.Q != null) {
            this.Q.l();
            this.Q = null;
        }
        if (this.ab != null) {
            this.ab.b();
            this.ab = null;
        }
        this.ac = null;
        G();
        this.ao = null;
        gz.b();
    }

    private void G() {
        this.E = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.F = null;
        this.G = null;
        this.H = null;
    }

    public void beforeDrawLabel(int i, GLMapState gLMapState) {
        p();
        if (this.f != null) {
            this.f.pushRendererState();
        }
        this.S.a(true, this.au);
        if (this.f != null) {
            this.f.popRendererState();
        }
    }

    public void afterDrawLabel(int i, GLMapState gLMapState) {
        p();
        if (this.f != null) {
            this.f.pushRendererState();
        }
        this.R.b();
        this.S.a(false, this.au);
        if (this.aY != null) {
            this.aY.a(this.c, getViewMatrix(), getProjectionMatrix());
        }
        if (this.b != null) {
            this.b.b();
        }
        if (this.W != null) {
            this.W.a();
        }
        if (this.K != null) {
            this.K.b(getMapWidth(), getMapHeight());
        }
        if (this.f != null) {
            this.f.popRendererState();
        }
    }

    public void afterDrawFrame(int i, GLMapState gLMapState) {
        float mapZoomer = gLMapState.getMapZoomer();
        boolean z = this.f != null && (this.f.isInMapAction(i) || this.f.isInMapAnimation(i));
        if (!z) {
            if (this.ay != -1) {
                this.aD.setRenderFps((float) this.ay);
            } else {
                this.aD.setRenderFps(15.0f);
            }
            if (this.aM == 1) {
                this.aM = 0;
            }
            if (this.aG != mapZoomer) {
                this.aG = mapZoomer;
            }
        }
        if (!this.aQ) {
            this.aQ = true;
        }
    }

    public void afterAnimation() {
        j();
    }

    public long createGLOverlay(int i) {
        if (this.f != null) {
            return this.f.createOverlay(1, i);
        }
        return 0;
    }

    public long getGlOverlayMgrPtr() {
        if (this.f != null) {
            return this.f.getGlOverlayMgrPtr(1);
        }
        return 0;
    }

    public CrossOverlay addCrossVector(CrossOverlayOptions crossOverlayOptions) {
        if (crossOverlayOptions == null || crossOverlayOptions.getRes() == null) {
            return null;
        }
        BaseMapOverlay crossVectorOverlay = new CrossVectorOverlay(1, v(), this);
        if (crossOverlayOptions != null) {
            crossVectorOverlay.setAttribute(crossOverlayOptions.getAttribute());
        }
        if (this.f != null) {
            this.f.getOverlayBundle(1).addOverlay(crossVectorOverlay);
            crossVectorOverlay.resumeMarker(crossOverlayOptions.getRes());
        }
        return new CrossOverlay(crossOverlayOptions, crossVectorOverlay);
    }

    public RouteOverlay addNaviRouteOverlay() {
        return null;
    }

    public void addOverlayTexture(int i, GLTextureProperty gLTextureProperty) {
        if (this.f != null) {
            GLOverlayBundle overlayBundle = this.f.getOverlayBundle(i);
            if (overlayBundle != null && gLTextureProperty != null && gLTextureProperty.mBitmap != null) {
                this.f.addOverlayTexture(i, gLTextureProperty);
                overlayBundle.addOverlayTextureItem(gLTextureProperty.mId, gLTextureProperty.mAnchor, gLTextureProperty.mXRatio, gLTextureProperty.mYRatio, gLTextureProperty.mBitmap.getWidth(), gLTextureProperty.mBitmap.getHeight());
            }
        }
    }

    public void setCustomMapStylePath(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.c.getCustomStylePath())) {
            this.c.setCustomStylePath(str);
            this.O = true;
        }
    }

    public void setCustomMapStyleID(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.c.getCustomStyleID())) {
            this.c.setCustomStyleID(str);
            this.O = true;
        }
    }

    public void setCustomTextureResourcePath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c.setCustomTextureResourcePath(str);
        }
    }

    public MyLocationStyle getMyLocationStyle() throws RemoteException {
        if (this.ab != null) {
            return this.ab.a();
        }
        return null;
    }

    public void a(boolean z, boolean z2) {
        boolean z3 = false;
        if (!this.aN || this.V) {
            this.bc.b = true;
            this.bc.c = z;
            return;
        }
        if (z2) {
            z3 = z2;
        }
        if (!TextUtils.isEmpty(this.c.getCustomStylePath()) || !TextUtils.isEmpty(this.c.getCustomStyleID())) {
            if (z) {
                try {
                    if (!(!this.c.isProFunctionAuthEnable() || TextUtils.isEmpty(this.c.getCustomStyleID()) || this.aA == null)) {
                        this.aA.a(this.c.getCustomStyleID());
                        this.aA.b();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
            if (z2 || this.O || (this.c.isCustomStyleEnable() ^ z) != 0) {
                a(z, null, z3);
            }
            this.O = false;
        }
    }

    public void setMapCustomEnable(boolean z) {
        if (z) {
            E();
        }
        a(z, false);
    }

    public void a(boolean z, byte[] bArr) {
        a(z, bArr, false);
    }

    public void a(boolean z, byte[] bArr, boolean z2) {
        boolean z3 = false;
        this.c.setCustomStyleEnable(z);
        if (this.c.isHideLogoEnable()) {
            this.N.setLogoEnable(!z);
        }
        if (z) {
            dk dkVar;
            StyleItem[] styleItemArr;
            c(1, true);
            dj djVar = new dj(this.e);
            if (!(this.ao == null || this.ao.getTrafficRoadBackgroundColor() == -1)) {
                djVar.a(this.ao.getTrafficRoadBackgroundColor());
            }
            if (this.c.isProFunctionAuthEnable() && !TextUtils.isEmpty(this.c.getCustomTextureResourcePath())) {
                z3 = true;
            }
            if (bArr != null) {
                dk a = djVar.a(bArr, z3);
                if (a != null) {
                    StyleItem[] c = a.c();
                    if (c != null) {
                        this.c.setUseProFunction(true);
                    }
                    dkVar = a;
                    styleItemArr = c;
                } else {
                    dkVar = a;
                    styleItemArr = null;
                }
            } else {
                styleItemArr = null;
                dkVar = null;
            }
            if (styleItemArr == null) {
                dkVar = djVar.a(this.c.getCustomStylePath(), z3);
                if (dkVar != null) {
                    styleItemArr = dkVar.c();
                }
            }
            if (djVar.a() != 0) {
                this.c.setCustomBackgroundColor(djVar.a());
            }
            if (dkVar == null || dkVar.d() == null) {
                a(styleItemArr, z2);
                return;
            } else if (this.aB != null) {
                this.aB.a((String) dkVar.d());
                this.aB.a(dkVar);
                this.aB.b();
                return;
            } else {
                return;
            }
        }
        c(1, false);
        a(1, this.c.getMapStyleMode(), this.c.getMapStyleTime(), this.c.getMapStyleState(), true, false, null);
    }

    public void a(String str, dk dkVar) {
        setCustomTextureResourcePath(str);
        if (this.c.isCustomStyleEnable() && dkVar != null) {
            a(dkVar.c(), false);
        }
    }

    protected void a(StyleItem[] styleItemArr, boolean z) {
        int i = (z || (styleItemArr != null && styleItemArr.length > 0)) ? true : 0;
        if (i != 0) {
            a(1, 0, 0, 0, true, true, styleItemArr);
            el.a(this.e, true);
            return;
        }
        el.a(this.e, false);
    }

    public void removeGLOverlay(BaseMapOverlay baseMapOverlay) {
        if (this.f != null) {
            this.f.getOverlayBundle(1).removeOverlay(baseMapOverlay);
        }
    }

    public float[] w() {
        if (this.c != null) {
            return this.c.getMvpMatrix();
        }
        return this.m;
    }

    public String d(String str) {
        if (this.S != null) {
            return this.S.a(str);
        }
        return null;
    }

    public void h(boolean z) {
        if (!this.V) {
            this.Q.f(z);
        }
    }

    public float[] getViewMatrix() {
        if (this.c != null) {
            return this.c.getViewMatrix();
        }
        return this.n;
    }

    public float[] getProjectionMatrix() {
        if (this.c != null) {
            return this.c.getProjectionMatrix();
        }
        return this.o;
    }

    public void changeSurface(GL10 gl10, int i, int i2) {
        try {
            a(1, gl10, i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void createSurface(GL10 gl10, EGLConfig eGLConfig) {
        try {
            a(1, gl10, eGLConfig);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void renderSurface(GL10 gl10) {
        drawFrame(gl10);
    }

    public boolean canStopMapRender() {
        if (this.f != null) {
            this.f.canStopMapRender(1);
        }
        return true;
    }

    public void getLatLngRect(DPoint[] dPointArr) {
        try {
            Rectangle geoRectangle = this.c.getGeoRectangle();
            if (geoRectangle != null) {
                IPoint[] clipRect = geoRectangle.getClipRect();
                for (int i = 0; i < 4; i++) {
                    GLMapState.geo2LonLat(clipRect[i].x, clipRect[i].y, dPointArr[i]);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void checkMapState(GLMapState gLMapState) {
        if (this.c != null && !this.V) {
            LatLngBounds limitLatLngBounds = this.c.getLimitLatLngBounds();
            if (limitLatLngBounds != null) {
                try {
                    IPoint[] limitIPoints = this.c.getLimitIPoints();
                    if (limitIPoints == null) {
                        GLMapState.lonlat2Geo(limitLatLngBounds.northeast.longitude, limitLatLngBounds.northeast.latitude, IPoint.obtain());
                        GLMapState.lonlat2Geo(limitLatLngBounds.southwest.longitude, limitLatLngBounds.southwest.latitude, IPoint.obtain());
                        limitIPoints = new IPoint[]{r2, r3};
                        this.c.setLimitIPoints(limitIPoints);
                    }
                    IPoint[] iPointArr = limitIPoints;
                    float b = en.b(this.c, iPointArr[0].x, iPointArr[0].y, iPointArr[1].x, iPointArr[1].y, getMapWidth(), getMapHeight());
                    float mapZoomer = gLMapState.getMapZoomer();
                    if (this.c.isSetLimitZoomLevel()) {
                        float maxZoomLevel = this.c.getMaxZoomLevel();
                        float minZoomLevel = this.c.getMinZoomLevel();
                        if (b < maxZoomLevel && b > minZoomLevel) {
                            b = Math.max(b, Math.min(mapZoomer, maxZoomLevel));
                        }
                    } else if (b <= 0.0f || mapZoomer >= b) {
                        b = mapZoomer;
                    }
                    gLMapState.setMapZoomer(b);
                    IPoint obtain = IPoint.obtain();
                    gLMapState.getMapGeoCenter(obtain);
                    int i = obtain.x;
                    int i2 = obtain.y;
                    int[] a = en.a(iPointArr[0].x, iPointArr[0].y, iPointArr[1].x, iPointArr[1].y, this.c, gLMapState, i, i2);
                    if (a != null && a.length == 2) {
                        i = a[0];
                        i2 = a[1];
                    }
                    gLMapState.setMapGeoCenter(i, i2);
                    obtain.recycle();
                } catch (Throwable th) {
                }
            } else if (this.c.isSetLimitZoomLevel()) {
                gLMapState.setMapZoomer(Math.max(this.c.getMinZoomLevel(), Math.min(gLMapState.getMapZoomer(), this.c.getMaxZoomLevel())));
            }
        }
    }

    public void setRenderMode(int i) {
        if (this.P != null) {
            this.P.setRenderMode(i);
        }
    }

    public da u(int i) {
        if (this.aW == null) {
            return null;
        }
        return this.aW.a(i);
    }

    public db x() {
        return this.aW;
    }

    public void b(int i, int i2) {
        if (this.c != null) {
            this.g = i;
            this.h = i2;
            this.c.setMapWidth(i);
            this.c.setMapHeight(i2);
        }
    }

    public void i(boolean z) {
        if (this.c != null) {
            this.c.setHideLogoEnble(z);
            if (this.c.isCustomStyleEnable()) {
                this.N.setLogoEnable(!z);
            }
        }
    }

    public void a(String str, boolean z, int i) {
        if (this.Q != null) {
            this.Q.a(str, z, i);
        }
        if (this.N != null) {
            this.N.requestRefreshLogo();
        }
    }

    public void y() {
        if (this.Q != null) {
            this.Q.e();
        }
    }

    public float v(int i) {
        GLMapState gLMapState = new GLMapState(1, this.f.getNativeInstance());
        gLMapState.setMapZoomer((float) i);
        gLMapState.recalculate();
        float gLUnitWithWin = gLMapState.getGLUnitWithWin(1);
        gLMapState.recycle();
        return gLUnitWithWin;
    }
}
