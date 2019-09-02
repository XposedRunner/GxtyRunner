package com.loc;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
/* compiled from: GeoFenceManager */
public class a implements GeoFenceManagerBase {
    private Object A = new Object();
    cq a = null;
    Context b = null;
    PendingIntent c = null;
    String d = null;
    GeoFenceListener e = null;
    volatile int f = 1;
    ArrayList<GeoFence> g = new ArrayList();
    c h = null;
    Object i = new Object();
    Object j = new Object();
    a k = null;
    b l = null;
    volatile boolean m = false;
    volatile boolean n = false;
    volatile boolean o = false;
    ac p = null;
    bd q = null;
    AMapLocationClient r = null;
    volatile AMapLocation s = null;
    long t = 0;
    AMapLocationClientOption u = null;
    int v = 0;
    AMapLocationListener w = new AMapLocationListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void onLocationChanged(AMapLocation aMapLocation) {
            int i = 8;
            Object obj = 1;
            try {
                if (!this.a.z && this.a.o) {
                    Bundle bundle;
                    a aVar;
                    this.a.s = aMapLocation;
                    if (aMapLocation != null) {
                        i = aMapLocation.getErrorCode();
                        if (aMapLocation.getErrorCode() == 0) {
                            this.a.t = ct.b();
                            this.a.a(5, null, 0);
                            if (obj == null) {
                                this.a.v = 0;
                                this.a.a(6, null, 0);
                            }
                            bundle = new Bundle();
                            if (!this.a.m) {
                                this.a.a(7);
                                bundle.putLong("interval", 2000);
                                this.a.a(8, bundle, 2000);
                            }
                            aVar = this.a;
                            aVar.v++;
                            if (this.a.v >= 3) {
                                bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i);
                                this.a.a(1002, bundle);
                                return;
                            }
                            return;
                        }
                        a aVar2 = this.a;
                        a.a("定位失败", aMapLocation.getErrorCode(), aMapLocation.getErrorInfo(), "locationDetail:" + aMapLocation.getLocationDetail());
                    }
                    obj = null;
                    if (obj == null) {
                        bundle = new Bundle();
                        if (this.a.m) {
                            this.a.a(7);
                            bundle.putLong("interval", 2000);
                            this.a.a(8, bundle, 2000);
                        }
                        aVar = this.a;
                        aVar.v++;
                        if (this.a.v >= 3) {
                            bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i);
                            this.a.a(1002, bundle);
                            return;
                        }
                        return;
                    }
                    this.a.v = 0;
                    this.a.a(6, null, 0);
                }
            } catch (Throwable th) {
            }
        }
    };
    final int x = 3;
    final int y = 2;
    volatile boolean z = false;

    /* compiled from: GeoFenceManager */
    class a extends Handler {
        final /* synthetic */ a a;

        public a(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        this.a.b(message.getData());
                        return;
                    case 1:
                        this.a.c(message.getData());
                        return;
                    case 2:
                        this.a.e(message.getData());
                        return;
                    case 3:
                        this.a.d(message.getData());
                        return;
                    case 4:
                        this.a.f(message.getData());
                        return;
                    case 5:
                        this.a.c();
                        return;
                    case 6:
                        this.a.a(this.a.s);
                        return;
                    case 7:
                        this.a.b();
                        return;
                    case 8:
                        this.a.j(message.getData());
                        return;
                    case 9:
                        this.a.a(message.getData());
                        return;
                    case 10:
                        this.a.a();
                        return;
                    case 11:
                        this.a.h(message.getData());
                        return;
                    case 12:
                        this.a.g(message.getData());
                        return;
                    case 13:
                        this.a.d();
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
            }
        }
    }

    /* compiled from: GeoFenceManager */
    static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
            }
        }
    }

    /* compiled from: GeoFenceManager */
    class c extends Handler {
        final /* synthetic */ a a;

        public c(a aVar) {
            this.a = aVar;
        }

        public c(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                Bundle data = message.getData();
                switch (message.what) {
                    case 1000:
                        this.a.i(data);
                        return;
                    case 1001:
                        this.a.a((GeoFence) data.getParcelable("geoFence"));
                        return;
                    case 1002:
                        try {
                            this.a.b(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                            return;
                        } catch (Throwable th) {
                            th.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
            } catch (Throwable th2) {
            }
        }
    }

    public a(Context context) {
        try {
            this.b = context.getApplicationContext();
            e();
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManger", "<init>");
        }
    }

    static float a(DPoint dPoint, List<DPoint> list) {
        if (dPoint == null || list == null || list.isEmpty()) {
            return Float.MAX_VALUE;
        }
        float f = Float.MAX_VALUE;
        for (DPoint a : list) {
            f = Math.min(f, ct.a(dPoint, a));
        }
        return f;
    }

    private int a(List<GeoFence> list) {
        try {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            for (GeoFence b : list) {
                b(b);
            }
            return 0;
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "addGeoFenceList");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static Bundle a(GeoFence geoFence, String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        String str3 = GeoFence.BUNDLE_KEY_FENCEID;
        if (str == null) {
            str = "";
        }
        bundle.putString(str3, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    private GeoFence a(Bundle bundle, boolean z) {
        Object parcelableArrayList;
        float f = 1000.0f;
        GeoFence geoFence = new GeoFence();
        List arrayList = new ArrayList();
        DPoint dPoint = new DPoint();
        if (z) {
            geoFence.setType(1);
            parcelableArrayList = bundle.getParcelableArrayList("pointList");
            if (parcelableArrayList != null) {
                dPoint = b((List) parcelableArrayList);
            }
            geoFence.setMaxDis2Center(b(dPoint, (List) parcelableArrayList));
            geoFence.setMinDis2Center(a(dPoint, (List) parcelableArrayList));
        } else {
            geoFence.setType(0);
            dPoint = (DPoint) bundle.getParcelable("centerPoint");
            if (dPoint != null) {
                arrayList.add(dPoint);
            }
            float f2 = bundle.getFloat("fenceRadius", 1000.0f);
            if (f2 > 0.0f) {
                f = f2;
            }
            geoFence.setRadius(f);
            geoFence.setMinDis2Center(f);
            geoFence.setMaxDis2Center(f);
            List list = arrayList;
        }
        geoFence.setActivatesAction(this.f);
        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
        List arrayList2 = new ArrayList();
        arrayList2.add(parcelableArrayList);
        geoFence.setPointList(arrayList2);
        geoFence.setCenter(dPoint);
        geoFence.setPendingIntentAction(this.d);
        geoFence.setExpiration(-1);
        geoFence.setPendingIntent(this.c);
        geoFence.setFenceId(bd.a());
        if (this.a != null) {
            this.a.a(this.b, 2);
        }
        return geoFence;
    }

    static void a(String str, int i, String str2, String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("===========================================\n");
        stringBuffer.append("              " + str + "                ").append("\n");
        stringBuffer.append("-------------------------------------------\n");
        stringBuffer.append("errorCode:" + i).append("\n");
        stringBuffer.append("错误信息:" + str2).append("\n");
        if (strArr != null && strArr.length > 0) {
            for (String append : strArr) {
                stringBuffer.append(append).append("\n");
            }
        }
        stringBuffer.append("===========================================\n");
        Log.i("fenceErrLog", stringBuffer.toString());
    }

    private static boolean a(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.abs(((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2))) < 1.0E-9d && (d - d3) * (d - d5) <= Utils.DOUBLE_EPSILON && (d2 - d4) * (d2 - d6) <= Utils.DOUBLE_EPSILON;
    }

    private static boolean a(GeoFence geoFence, int i) {
        boolean z = false;
        if ((i & 1) == 1) {
            try {
                if (geoFence.getStatus() == 1) {
                    z = true;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                boolean z2 = false;
                cl.a(th2, "Utils", "remindStatus");
                return z2;
            }
        }
        if ((i & 2) == 2 && geoFence.getStatus() == 2) {
            z = true;
        }
        return ((i & 4) == 4 && geoFence.getStatus() == 3) ? true : z;
    }

    private static boolean a(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z = false;
        try {
            if (!ct.a(aMapLocation) || geoFence == null || geoFence.getPointList() == null || geoFence.getPointList().isEmpty()) {
                return false;
            }
            Object obj;
            switch (geoFence.getType()) {
                case 0:
                case 2:
                    DPoint center = geoFence.getCenter();
                    obj = null;
                    if (ct.a(new double[]{center.getLatitude(), center.getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude()}) <= geoFence.getRadius()) {
                        obj = 1;
                    }
                    return obj != null;
                case 1:
                case 3:
                    for (List list : geoFence.getPointList()) {
                        int i = 0;
                        double longitude = aMapLocation.getLongitude();
                        double latitude = aMapLocation.getLatitude();
                        double latitude2 = aMapLocation.getLatitude();
                        if (list.size() < 3) {
                            obj = null;
                        } else {
                            if (!((DPoint) list.get(0)).equals(list.get(list.size() - 1))) {
                                list.add(list.get(0));
                            }
                            int i2 = 0;
                            while (i2 < list.size() - 1) {
                                double longitude2 = ((DPoint) list.get(i2)).getLongitude();
                                double latitude3 = ((DPoint) list.get(i2)).getLatitude();
                                double longitude3 = ((DPoint) list.get(i2 + 1)).getLongitude();
                                double latitude4 = ((DPoint) list.get(i2 + 1)).getLatitude();
                                if (a(longitude, latitude, longitude2, latitude3, longitude3, latitude4)) {
                                    obj = 1;
                                } else {
                                    int i3;
                                    if (Math.abs(latitude4 - latitude3) >= 1.0E-9d) {
                                        if (a(longitude2, latitude3, longitude, latitude, 180.0d, latitude2)) {
                                            if (latitude3 > latitude4) {
                                                i3 = i + 1;
                                                i = i3;
                                                i2++;
                                            }
                                        } else if (!a(longitude3, latitude4, longitude, latitude, 180.0d, latitude2)) {
                                            Object obj2;
                                            double d = ((longitude3 - longitude2) * (latitude2 - latitude)) - ((latitude4 - latitude3) * (180.0d - longitude));
                                            if (d != Utils.DOUBLE_EPSILON) {
                                                double d2 = (((latitude3 - latitude) * (180.0d - longitude)) - ((longitude2 - longitude) * (latitude2 - latitude))) / d;
                                                longitude2 = (((longitude3 - longitude2) * (latitude3 - latitude)) - ((longitude2 - longitude) * (latitude4 - latitude3))) / d;
                                                if (d2 >= Utils.DOUBLE_EPSILON && d2 <= 1.0d && longitude2 >= Utils.DOUBLE_EPSILON && longitude2 <= 1.0d) {
                                                    obj2 = 1;
                                                    if (obj2 != null) {
                                                        i3 = i + 1;
                                                        i = i3;
                                                        i2++;
                                                    }
                                                }
                                            }
                                            obj2 = null;
                                            if (obj2 != null) {
                                                i3 = i + 1;
                                                i = i3;
                                                i2++;
                                            }
                                        } else if (latitude4 > latitude3) {
                                            i3 = i + 1;
                                            i = i3;
                                            i2++;
                                        }
                                    }
                                    i3 = i;
                                    i = i3;
                                    i2++;
                                }
                            }
                            obj = i % 2 != 0 ? 1 : null;
                        }
                        z = obj != null ? true : z;
                    }
                    return z;
                default:
                    return false;
            }
        } catch (Throwable th) {
            cl.a(th, "Utils", "isInGeoFence");
            return false;
        }
    }

    static float b(DPoint dPoint, List<DPoint> list) {
        if (dPoint == null || list == null || list.isEmpty()) {
            return Float.MIN_VALUE;
        }
        float f = Float.MIN_VALUE;
        for (DPoint a : list) {
            f = Math.max(f, ct.a(dPoint, a));
        }
        return f;
    }

    private int b(GeoFence geoFence) {
        try {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            if (this.g.contains(geoFence)) {
                return 17;
            }
            this.g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "addGeoFence2List");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static DPoint b(List<DPoint> list) {
        DPoint dPoint;
        double d = Utils.DOUBLE_EPSILON;
        DPoint dPoint2 = new DPoint();
        if (list != null) {
            try {
                double d2 = Utils.DOUBLE_EPSILON;
                for (DPoint dPoint3 : list) {
                    d2 += dPoint3.getLatitude();
                    d += dPoint3.getLongitude();
                }
                dPoint3 = new DPoint(ct.c(d2 / ((double) list.size())), ct.c(d / ((double) list.size())));
            } catch (Throwable th) {
                cl.a(th, "GeoFenceUtil", "getPolygonCenter");
                return dPoint2;
            }
        }
        dPoint3 = dPoint2;
        return dPoint3;
    }

    private void b(int i, Bundle bundle) {
        int i2;
        Throwable th;
        Bundle bundle2 = new Bundle();
        int i3 = 0;
        try {
            int i4;
            ArrayList arrayList = new ArrayList();
            if (bundle == null || bundle.isEmpty()) {
                i4 = 1;
            } else {
                Collection arrayList2 = new ArrayList();
                String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                String string2 = bundle.getString("keyWords");
                String string3 = bundle.getString("city");
                CharSequence string4 = bundle.getString("poiType");
                DPoint dPoint = (DPoint) bundle.getParcelable("centerPoint");
                int i5 = bundle.getInt("searchSize", 10);
                float f = bundle.getFloat("aroundRadius", 3000.0f);
                Object obj = 1;
                if (!TextUtils.isEmpty(string2)) {
                    switch (i) {
                        case 1:
                            if (TextUtils.isEmpty(string4)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 2:
                            if (dPoint != null) {
                                if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                                    a("添加围栏失败", 0, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                                    obj = null;
                                    break;
                                }
                            }
                            obj = null;
                            break;
                        default:
                            break;
                    }
                }
                obj = null;
                if (obj != null) {
                    String a;
                    Bundle bundle3 = new Bundle();
                    bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                    Bundle bundle4 = bundle3;
                    bundle4.putString("pendingIntentAction", this.d);
                    bundle3.putLong("expiration", -1);
                    bundle4 = bundle3;
                    bundle4.putInt("activatesAction", this.f);
                    switch (i) {
                        case 1:
                            bundle3.putFloat("fenceRadius", 1000.0f);
                            a = this.p.a(this.b, "http://restapi.amap.com/v3/place/text?", string2, string4, string3, String.valueOf(i5));
                            break;
                        case 2:
                            double c = ct.c(dPoint.getLatitude());
                            double c2 = ct.c(dPoint.getLongitude());
                            int intValue = Float.valueOf(f).intValue();
                            bundle3.putFloat("fenceRadius", 200.0f);
                            a = this.p.a(this.b, "http://restapi.amap.com/v3/place/around?", string2, string4, String.valueOf(i5), String.valueOf(c), String.valueOf(c2), String.valueOf(intValue));
                            break;
                        case 3:
                            a = this.p.a(this.b, "http://restapi.amap.com/v3/config/district?", string2);
                            break;
                        default:
                            a = null;
                            break;
                    }
                    if (a != null) {
                        bd bdVar;
                        if (1 == i) {
                            bdVar = this.q;
                            i4 = bd.a(a, arrayList2, bundle3);
                        } else {
                            i4 = 0;
                        }
                        if (2 == i) {
                            bdVar = this.q;
                            i4 = bd.b(a, arrayList2, bundle3);
                        }
                        if (3 == i) {
                            i4 = this.q.c(a, arrayList2, bundle3);
                        }
                        if (i4 != ByteBufferUtils.ERROR_CODE) {
                            switch (i4) {
                                case 1:
                                case 4:
                                case 5:
                                case 7:
                                case 16:
                                case 17:
                                    break;
                                case ByteBufferUtils.ERROR_CODE /*10000*/:
                                    i4 = 0;
                                    break;
                                case 10001:
                                case GLMapStaticValue.AM_CALLBACK_NEED_NEXTFRAME /*10002*/:
                                case 10007:
                                case 10008:
                                case 10009:
                                case 10012:
                                case 10013:
                                    i4 = 7;
                                    break;
                                case GLMapStaticValue.AM_CALLBACK_INDOOR_NETWORK_ERR /*10003*/:
                                case 10004:
                                case 10005:
                                case 10006:
                                case 10010:
                                case 10011:
                                case 10014:
                                case 10015:
                                case 10016:
                                case 10017:
                                    i4 = 4;
                                    break;
                                case 20000:
                                case 20001:
                                case 20002:
                                    i4 = 1;
                                    break;
                                case 20003:
                                    i4 = 8;
                                    break;
                                default:
                                    i4 = 8;
                                    break;
                            }
                            if (i4 != 0) {
                                a("添加围栏失败", i4, "searchErrCode is " + i4, new String[0]);
                            }
                        } else if (arrayList2.isEmpty()) {
                            i4 = 16;
                        } else {
                            i4 = a((List) arrayList2);
                            if (i4 == 0) {
                                arrayList.addAll(arrayList2);
                            }
                        }
                    } else {
                        i4 = 4;
                    }
                } else {
                    i4 = 1;
                }
                try {
                    bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                    bundle2.putParcelableArrayList("resultList", arrayList);
                } catch (Throwable th2) {
                    i3 = i4;
                    th = th2;
                    bundle2.putInt(MyLocationStyle.ERROR_CODE, i3);
                    a(1000, bundle2);
                    throw th;
                }
            }
            bundle2.putInt(MyLocationStyle.ERROR_CODE, i4);
            a(1000, bundle2);
        } catch (Throwable th3) {
            th = th3;
            bundle2.putInt(MyLocationStyle.ERROR_CODE, i3);
            a(1000, bundle2);
            throw th;
        }
    }

    private static boolean b(AMapLocation aMapLocation, GeoFence geoFence) {
        Throwable th;
        boolean z = true;
        boolean z2 = false;
        try {
            if (a(aMapLocation, geoFence)) {
                if (geoFence.getEnterTime() == -1) {
                    if (geoFence.getStatus() != 1) {
                        geoFence.setEnterTime(ct.b());
                        geoFence.setStatus(1);
                        return true;
                    }
                } else if (geoFence.getStatus() != 3 && ct.b() - geoFence.getEnterTime() > 600000) {
                    geoFence.setStatus(3);
                    return true;
                }
            } else if (geoFence.getStatus() != 2) {
                try {
                    geoFence.setStatus(2);
                    geoFence.setEnterTime(-1);
                    z2 = true;
                } catch (Throwable th2) {
                    th = th2;
                    cl.a(th, "Utils", "isFenceStatusChanged");
                    return z;
                }
            }
            return z2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            z = false;
            th = th4;
            cl.a(th, "Utils", "isFenceStatusChanged");
            return z;
        }
    }

    private void e() {
        if (!this.o) {
            this.o = true;
        }
        if (!this.n) {
            try {
                if (Looper.myLooper() == null) {
                    this.h = new c(this, this.b.getMainLooper());
                } else {
                    this.h = new c(this);
                }
            } catch (Throwable th) {
                cl.a(th, "GeoFenceManger", "init 1");
            }
            try {
                this.l = new b("fenceActionThread");
                this.l.setPriority(5);
                this.l.start();
                this.k = new a(this, this.l.getLooper());
            } catch (Throwable th2) {
                cl.a(th2, "GeoFenceManger", "init 2");
            }
            try {
                Context context = this.b;
                this.p = new ac();
                this.q = new bd();
                this.u = new AMapLocationClientOption();
                this.r = new AMapLocationClient(this.b);
                this.u.setLocationCacheEnable(true);
                this.u.setNeedAddress(false);
                this.r.setLocationListener(this.w);
                if (this.a == null) {
                    this.a = new cq();
                }
            } catch (Throwable th22) {
                cl.a(th22, "GeoFenceManger", "initBase");
            }
            this.n = true;
            try {
                if (this.d != null && this.c == null) {
                    createPendingIntent(this.d);
                }
            } catch (Throwable th222) {
                cl.a(th222, "GeoFenceManger", "init 4");
            }
        }
    }

    private void f() {
        if (!this.z && this.k != null) {
            Object obj = null;
            if (this.s != null && ct.a(this.s) && ct.b() - this.t < 10000) {
                obj = 1;
            }
            if (obj != null) {
                a(6, null, 0);
                a(5, null, 0);
                return;
            }
            a(7);
            a(7, null, 0);
        }
    }

    private void g() {
        try {
            if (this.m) {
                a(8);
            }
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable th) {
        }
    }

    final void a() {
        if (this.n) {
            if (this.g != null) {
                this.g.clear();
                this.g = null;
            }
            if (!this.o) {
                try {
                    synchronized (this.i) {
                        if (this.k != null) {
                            this.k.removeCallbacksAndMessages(null);
                        }
                        this.k = null;
                    }
                } catch (Throwable th) {
                    cl.a(th, "GeoFenceManager", "destroyActionHandler");
                }
                try {
                    if (this.r != null) {
                        this.r.stopLocation();
                        this.r.onDestroy();
                    }
                    this.r = null;
                    if (this.l != null) {
                        if (VERSION.SDK_INT >= 18) {
                            this.l.quitSafely();
                        } else {
                            this.l.quit();
                        }
                    }
                    this.l = null;
                    this.p = null;
                    synchronized (this.A) {
                        if (this.c != null) {
                            this.c.cancel();
                        }
                        this.c = null;
                    }
                    try {
                        synchronized (this.j) {
                            if (this.h != null) {
                                this.h.removeCallbacksAndMessages(null);
                            }
                            this.h = null;
                        }
                    } catch (Throwable th2) {
                        cl.a(th2, "GeoFenceManager", "destroyResultHandler");
                    }
                    if (this.a != null) {
                        this.a.b(this.b);
                    }
                } catch (Throwable th3) {
                }
                this.m = false;
                this.n = false;
            }
        }
    }

    final void a(int i) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    this.k.removeMessages(i);
                }
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    final void a(int i, Bundle bundle) {
        try {
            synchronized (this.j) {
                if (this.h != null) {
                    Message obtainMessage = this.h.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.setData(bundle);
                    this.h.sendMessage(obtainMessage);
                }
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    final void a(int i, Bundle bundle, long j) {
        try {
            synchronized (this.i) {
                if (this.k != null) {
                    Message obtainMessage = this.k.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.setData(bundle);
                    this.k.sendMessageDelayed(obtainMessage, j);
                }
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    final void a(Bundle bundle) {
        int i;
        if (bundle != null) {
            try {
                i = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                cl.a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        i = 1;
        if (this.f != i) {
            if (!(this.g == null || this.g.isEmpty())) {
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence geoFence = (GeoFence) it.next();
                    geoFence.setStatus(0);
                    geoFence.setEnterTime(-1);
                }
            }
            f();
        }
        this.f = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void a(com.amap.api.fence.GeoFence r7) {
        /*
        r6 = this;
        r1 = r6.A;	 Catch:{ Throwable -> 0x0053 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0053 }
        r0 = r6.b;	 Catch:{ all -> 0x0050 }
        if (r0 == 0) goto L_0x004e;
    L_0x0007:
        r0 = r6.c;	 Catch:{ all -> 0x0050 }
        if (r0 != 0) goto L_0x0013;
    L_0x000b:
        r0 = r7.getPendingIntent();	 Catch:{ all -> 0x0050 }
        if (r0 != 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x0050 }
    L_0x0012:
        return;
    L_0x0013:
        r0 = new android.content.Intent;	 Catch:{ all -> 0x0050 }
        r0.<init>();	 Catch:{ all -> 0x0050 }
        r2 = r7.getFenceId();	 Catch:{ all -> 0x0050 }
        r3 = r7.getCustomId();	 Catch:{ all -> 0x0050 }
        r4 = r7.getStatus();	 Catch:{ all -> 0x0050 }
        r5 = 0;
        r2 = a(r7, r2, r3, r4, r5);	 Catch:{ all -> 0x0050 }
        r0.putExtras(r2);	 Catch:{ all -> 0x0050 }
        r2 = r6.d;	 Catch:{ all -> 0x0050 }
        if (r2 == 0) goto L_0x0035;
    L_0x0030:
        r2 = r6.d;	 Catch:{ all -> 0x0050 }
        r0.setAction(r2);	 Catch:{ all -> 0x0050 }
    L_0x0035:
        r2 = r6.b;	 Catch:{ all -> 0x0050 }
        r2 = com.loc.db.c(r2);	 Catch:{ all -> 0x0050 }
        r0.setPackage(r2);	 Catch:{ all -> 0x0050 }
        r2 = r7.getPendingIntent();	 Catch:{ all -> 0x0050 }
        if (r2 == 0) goto L_0x005c;
    L_0x0044:
        r2 = r7.getPendingIntent();	 Catch:{ all -> 0x0050 }
        r3 = r6.b;	 Catch:{ all -> 0x0050 }
        r4 = 0;
        r2.send(r3, r4, r0);	 Catch:{ all -> 0x0050 }
    L_0x004e:
        monitor-exit(r1);	 Catch:{ all -> 0x0050 }
        goto L_0x0012;
    L_0x0050:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ Throwable -> 0x0053 }
        throw r0;	 Catch:{ Throwable -> 0x0053 }
    L_0x0053:
        r0 = move-exception;
        r1 = "GeoFenceManager";
        r2 = "resultTriggerGeoFence";
        com.loc.cl.a(r0, r1, r2);
        goto L_0x0012;
    L_0x005c:
        r2 = r6.c;	 Catch:{ all -> 0x0050 }
        r3 = r6.b;	 Catch:{ all -> 0x0050 }
        r4 = 0;
        r2.send(r3, r4, r0);	 Catch:{ all -> 0x0050 }
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.a(com.amap.api.fence.GeoFence):void");
    }

    final void a(AMapLocation aMapLocation) {
        try {
            if (!this.z && this.g != null && !this.g.isEmpty() && aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence geoFence = (GeoFence) it.next();
                    if (geoFence.isAble() && b(aMapLocation, geoFence) && a(geoFence, this.f)) {
                        geoFence.setCurrentLocation(aMapLocation);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("geoFence", geoFence);
                        a(1001, bundle);
                    }
                }
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    public void addDistrictGeoFence(String str, String str2) {
        try {
            e();
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            a(4, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    public void addKeywordGeoFence(String str, String str2, String str3, int i, String str4) {
        int i2 = 25;
        try {
            e();
            int i3 = i <= 0 ? 10 : i;
            if (i3 <= 25) {
                i2 = i3;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putString("city", str3);
            bundle.putInt("searchSize", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            a(2, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    public void addNearbyGeoFence(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        int i2 = 25;
        try {
            e();
            if (f <= 0.0f || f > 50000.0f) {
                f = 3000.0f;
            }
            int i3 = i <= 0 ? 10 : i;
            if (i3 <= 25) {
                i2 = i3;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f);
            bundle.putInt("searchSize", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            a(3, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    public void addPolygonGeoFence(List<DPoint> list, String str) {
        try {
            e();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("pointList", new ArrayList(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    public void addRoundGeoFence(DPoint dPoint, float f, String str) {
        try {
            e();
            Bundle bundle = new Bundle();
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("fenceRadius", f);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(0, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    final void b() {
        try {
            if (this.r != null) {
                g();
                this.u.setOnceLocation(true);
                this.r.setLocationOption(this.u);
                this.r.startLocation();
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "doStartOnceLocation");
        }
    }

    final void b(int i) {
        try {
            if (this.b != null) {
                synchronized (this.A) {
                    if (this.c == null) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtras(a(null, null, null, 4, i));
                    this.c.send(this.b, 0, intent);
                }
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    final void b(Bundle bundle) {
        try {
            String string;
            int i;
            Bundle bundle2;
            ArrayList arrayList = new ArrayList();
            String str = "";
            if (!(bundle == null || bundle.isEmpty())) {
                DPoint dPoint = (DPoint) bundle.getParcelable("centerPoint");
                string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint == null) {
                    str = string;
                } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                    a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                    i = 1;
                    bundle2 = new Bundle();
                    bundle2.putInt(MyLocationStyle.ERROR_CODE, i);
                    bundle2.putParcelableArrayList("resultList", arrayList);
                    bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                    a(1000, bundle2);
                } else {
                    GeoFence a = a(bundle, false);
                    i = b(a);
                    if (i == 0) {
                        arrayList.add(a);
                    }
                    bundle2 = new Bundle();
                    bundle2.putInt(MyLocationStyle.ERROR_CODE, i);
                    bundle2.putParcelableArrayList("resultList", arrayList);
                    bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                    a(1000, bundle2);
                }
            }
            string = str;
            i = 1;
            bundle2 = new Bundle();
            bundle2.putInt(MyLocationStyle.ERROR_CODE, i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
            a(1000, bundle2);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "doAddGeoFenceRound");
        }
    }

    final void c() {
        try {
            if (!this.z && ct.a(this.s)) {
                float f;
                AMapLocation aMapLocation = this.s;
                List<GeoFence> list = this.g;
                if (aMapLocation == null || aMapLocation.getErrorCode() != 0 || list == null || list.isEmpty()) {
                    f = Float.MAX_VALUE;
                } else {
                    DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    float f2 = Float.MAX_VALUE;
                    for (GeoFence geoFence : list) {
                        if (geoFence.isAble()) {
                            float a = ct.a(dPoint, geoFence.getCenter());
                            if (a > geoFence.getMinDis2Center() && a < geoFence.getMaxDis2Center()) {
                                f = 0.0f;
                                break;
                            }
                            if (a > geoFence.getMaxDis2Center()) {
                                f2 = Math.min(f2, a - geoFence.getMaxDis2Center());
                            }
                            f2 = a < geoFence.getMinDis2Center() ? Math.min(f2, geoFence.getMinDis2Center() - a) : f2;
                        }
                    }
                    f = f2;
                }
                if (f == Float.MAX_VALUE) {
                    return;
                }
                if (f < 1000.0f) {
                    a(7);
                    Bundle bundle = new Bundle();
                    bundle.putLong("interval", 2000);
                    a(8, bundle, 500);
                } else if (f < 5000.0f) {
                    g();
                    a(7);
                    a(7, null, 10000);
                } else {
                    g();
                    a(7);
                    a(7, null, (long) (((f - 4000.0f) / 100.0f) * 1000.0f));
                }
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    final void c(Bundle bundle) {
        int i = 1;
        try {
            ArrayList arrayList = new ArrayList();
            String str = "";
            if (!(bundle == null || bundle.isEmpty())) {
                List parcelableArrayList = bundle.getParcelableArrayList("pointList");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (parcelableArrayList != null && parcelableArrayList.size() > 2) {
                    GeoFence a = a(bundle, true);
                    i = b(a);
                    if (i == 0) {
                        arrayList.add(a);
                    }
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle2.putInt(MyLocationStyle.ERROR_CODE, i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle2);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "doAddGeoFencePolygon");
        }
    }

    public PendingIntent createPendingIntent(String str) {
        synchronized (this.A) {
            try {
                Intent intent = new Intent(str);
                intent.setPackage(db.c(this.b));
                this.c = PendingIntent.getBroadcast(this.b, 0, intent, 0);
                this.d = str;
                if (!(this.g == null || this.g.isEmpty())) {
                    Iterator it = this.g.iterator();
                    while (it.hasNext()) {
                        GeoFence geoFence = (GeoFence) it.next();
                        geoFence.setPendingIntent(this.c);
                        geoFence.setPendingIntentAction(this.d);
                    }
                }
            } catch (Throwable th) {
                cl.a(th, "GeoFenceManager", "createPendingIntent");
            }
        }
        return this.c;
    }

    final void d() {
        try {
            a(7);
            a(8);
            if (this.r != null) {
                this.r.stopLocation();
            }
            this.m = false;
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "doPauseGeoFence");
        }
    }

    final void d(Bundle bundle) {
        b(2, bundle);
    }

    final void e(Bundle bundle) {
        b(1, bundle);
    }

    final void f(Bundle bundle) {
        b(3, bundle);
    }

    final void g(Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    CharSequence string = bundle.getString("fid");
                    if (!TextUtils.isEmpty(string)) {
                        boolean z = bundle.getBoolean("ab", true);
                        if (this.g != null && !this.g.isEmpty()) {
                            Iterator it = this.g.iterator();
                            while (it.hasNext()) {
                                GeoFence geoFence = (GeoFence) it.next();
                                if (geoFence.getFenceId().equals(string)) {
                                    geoFence.setAble(z);
                                    break;
                                }
                            }
                        }
                        if (z) {
                            f();
                            return;
                        }
                        Object obj;
                        if (this.g == null || this.g.isEmpty()) {
                            obj = 1;
                        } else {
                            Iterator it2 = this.g.iterator();
                            while (it2.hasNext()) {
                                if (((GeoFence) it2.next()).isAble()) {
                                    obj = null;
                                    break;
                                }
                            }
                            int i = 1;
                        }
                        if (obj != null) {
                            d();
                        }
                    }
                }
            } catch (Throwable th) {
                cl.a(th, "GeoFenceManager", "doSetGeoFenceAble");
            }
        }
    }

    public List<GeoFence> getAllGeoFence() {
        try {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            return (ArrayList) this.g.clone();
        } catch (Throwable th) {
            return new ArrayList();
        }
    }

    final void h(Bundle bundle) {
        try {
            if (this.g != null) {
                GeoFence geoFence = (GeoFence) bundle.getParcelable("fc");
                if (this.g.contains(geoFence)) {
                    this.g.remove(geoFence);
                }
                if (this.g.size() <= 0) {
                    a();
                } else {
                    f();
                }
            }
        } catch (Throwable th) {
        }
    }

    final void i(Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    int i = bundle.getInt(MyLocationStyle.ERROR_CODE);
                    ArrayList parcelableArrayList = bundle.getParcelableArrayList("resultList");
                    ArrayList arrayList = parcelableArrayList == null ? new ArrayList() : parcelableArrayList;
                    String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                    String str = string == null ? "" : string;
                    if (this.e != null) {
                        this.e.onGeoFenceCreateFinished((ArrayList) arrayList.clone(), i, str);
                    }
                    if (i == 0) {
                        f();
                    }
                }
            } catch (Throwable th) {
                cl.a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
            }
        }
    }

    public boolean isPause() {
        return this.z;
    }

    final void j(Bundle bundle) {
        long j = 2000;
        try {
            if (this.r != null) {
                if (!(bundle == null || bundle.isEmpty())) {
                    j = bundle.getLong("interval", 2000);
                }
                this.u.setOnceLocation(false);
                this.u.setInterval(j);
                this.r.setLocationOption(this.u);
                if (!this.m) {
                    this.r.stopLocation();
                    this.r.startLocation();
                    this.m = true;
                }
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "doStartContinueLocation");
        }
    }

    public void pauseGeoFence() {
        try {
            e();
            this.z = true;
            a(13, null, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "pauseGeoFence");
        }
    }

    public void removeGeoFence() {
        try {
            this.o = false;
            a(10, null, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "removeGeoFence");
        }
    }

    public boolean removeGeoFence(GeoFence geoFence) {
        try {
            if (this.g == null || this.g.isEmpty()) {
                this.o = false;
                a(10, null, 0);
                return true;
            } else if (!this.g.contains(geoFence)) {
                return false;
            } else {
                if (this.g.size() == 1) {
                    this.o = false;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("fc", geoFence);
                a(11, bundle, 0);
                return true;
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
            return false;
        }
    }

    public void resumeGeoFence() {
        try {
            e();
            if (this.z) {
                this.z = false;
                f();
            }
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "resumeGeoFence");
        }
    }

    public void setActivateAction(int i) {
        try {
            e();
            if (i > 7 || i <= 0) {
                i = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i);
            a(9, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    public void setGeoFenceAble(String str, boolean z) {
        try {
            e();
            Bundle bundle = new Bundle();
            bundle.putString("fid", str);
            bundle.putBoolean("ab", z);
            a(12, bundle, 0);
        } catch (Throwable th) {
            cl.a(th, "GeoFenceManager", "setGeoFenceAble");
        }
    }

    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        try {
            this.e = geoFenceListener;
        } catch (Throwable th) {
        }
    }
}
