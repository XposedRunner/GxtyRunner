package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/* compiled from: ApsManager */
public final class cv {
    static boolean g = false;
    private cz A = null;
    private boolean B = true;
    private String C = "";
    private final int D = GLMapStaticValue.TMC_REFRESH_TIMELIMIT;
    private String E = "jsonp1";
    String a = null;
    b b = null;
    AMapLocation c = null;
    a d = null;
    Context e = null;
    bq f = null;
    HashMap<Messenger, Long> h = new HashMap();
    cq i = null;
    long j = 0;
    long k = 0;
    String l = null;
    AMapLocationClientOption m = null;
    AMapLocationClientOption n = new AMapLocationClientOption();
    ServerSocket o = null;
    boolean p = false;
    Socket q = null;
    boolean r = false;
    c s = null;
    private boolean t = false;
    private boolean u = false;
    private long v = 0;
    private long w = 0;
    private AMapLocationServer x = null;
    private long y = 0;
    private int z = 0;

    /* compiled from: ApsManager */
    public class a extends Handler {
        final /* synthetic */ cv a;

        public a(cv cvVar, Looper looper) {
            this.a = cvVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            Bundle data;
            Throwable th;
            Messenger messenger = null;
            try {
                data = message.getData();
                try {
                    messenger = message.replyTo;
                    if (!(data == null || data.isEmpty())) {
                        if (!this.a.a(data.getString("c"))) {
                            if (message.what == 1) {
                                cq.a(null, 2102);
                                cv cvVar = this.a;
                                AMapLocation b = cv.a(10, "invalid handlder scode!!!#1002");
                                this.a.a(messenger, b, b.l(), 0);
                                return;
                            }
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        cl.a(th, "ApsServiceCore", "ActionHandler handlerMessage");
                        switch (message.what) {
                            case 0:
                                this.a.a(data);
                                cv.a(this.a, messenger, data);
                                break;
                            case 1:
                                this.a.a(data);
                                cv.b(this.a, messenger, data);
                                break;
                            case 2:
                                if (data != null) {
                                    return;
                                }
                                return;
                            case 3:
                                if (data != null) {
                                    return;
                                }
                                return;
                            case 4:
                                this.a.a(data);
                                cv.a(this.a);
                                break;
                            case 5:
                                this.a.a(data);
                                cv.b(this.a);
                                break;
                            case 7:
                                this.a.a(data);
                                cv.c(this.a);
                                break;
                            case 9:
                                this.a.a(data);
                                cv.a(this.a, messenger);
                                break;
                            case 10:
                                this.a.a(data);
                                this.a.a(messenger, data);
                                break;
                            case 11:
                                this.a.e();
                                break;
                            case 12:
                                this.a.h.remove(messenger);
                                break;
                        }
                        super.handleMessage(message);
                    } catch (Throwable th3) {
                        cl.a(th3, "actionHandler", "handleMessage");
                        return;
                    }
                }
            } catch (Throwable th4) {
                th3 = th4;
                data = null;
                cl.a(th3, "ApsServiceCore", "ActionHandler handlerMessage");
                switch (message.what) {
                    case 0:
                        this.a.a(data);
                        cv.a(this.a, messenger, data);
                        break;
                    case 1:
                        this.a.a(data);
                        cv.b(this.a, messenger, data);
                        break;
                    case 2:
                        if (data != null) {
                            return;
                        }
                        return;
                    case 3:
                        if (data != null) {
                            return;
                        }
                        return;
                    case 4:
                        this.a.a(data);
                        cv.a(this.a);
                        break;
                    case 5:
                        this.a.a(data);
                        cv.b(this.a);
                        break;
                    case 7:
                        this.a.a(data);
                        cv.c(this.a);
                        break;
                    case 9:
                        this.a.a(data);
                        cv.a(this.a, messenger);
                        break;
                    case 10:
                        this.a.a(data);
                        this.a.a(messenger, data);
                        break;
                    case 11:
                        this.a.e();
                        break;
                    case 12:
                        this.a.h.remove(messenger);
                        break;
                }
                super.handleMessage(message);
            }
            switch (message.what) {
                case 0:
                    this.a.a(data);
                    cv.a(this.a, messenger, data);
                    break;
                case 1:
                    this.a.a(data);
                    cv.b(this.a, messenger, data);
                    break;
                case 2:
                    if (data != null && !data.isEmpty()) {
                        this.a.a(null);
                        this.a.c();
                        break;
                    }
                    return;
                case 3:
                    if (data != null && !data.isEmpty()) {
                        this.a.a(null);
                        this.a.d();
                        break;
                    }
                    return;
                case 4:
                    this.a.a(data);
                    cv.a(this.a);
                    break;
                case 5:
                    this.a.a(data);
                    cv.b(this.a);
                    break;
                case 7:
                    this.a.a(data);
                    cv.c(this.a);
                    break;
                case 9:
                    this.a.a(data);
                    cv.a(this.a, messenger);
                    break;
                case 10:
                    this.a.a(data);
                    this.a.a(messenger, data);
                    break;
                case 11:
                    this.a.e();
                    break;
                case 12:
                    this.a.h.remove(messenger);
                    break;
            }
            super.handleMessage(message);
        }
    }

    /* compiled from: ApsManager */
    class b extends HandlerThread {
        final /* synthetic */ cv a;

        public b(cv cvVar, String str) {
            this.a = cvVar;
            super(str);
        }

        protected final void onLooperPrepared() {
            try {
                this.a.A = new cz(this.a.e);
            } catch (Throwable th) {
                cl.a(th, "APSManager$ActionThread", "onLooperPrepared");
                return;
            }
            this.a.f = new bq();
            super.onLooperPrepared();
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
                cl.a(th, "APSManager$ActionThread", "run");
            }
        }
    }

    /* compiled from: ApsManager */
    class c extends Thread {
        final /* synthetic */ cv a;

        c(cv cvVar) {
            this.a = cvVar;
        }

        public final void run() {
            try {
                if (!this.a.p) {
                    this.a.p = true;
                    this.a.o = new ServerSocket(43689);
                }
                while (this.a.p && this.a.o != null) {
                    this.a.q = this.a.o.accept();
                    cv.a(this.a, this.a.q);
                }
            } catch (Throwable th) {
                cl.a(th, "ApsServiceCore", "run");
            }
            super.run();
        }
    }

    public cv(Context context) {
        this.e = context;
    }

    private static AMapLocationServer a(int i, String str) {
        try {
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            aMapLocationServer.setErrorCode(i);
            aMapLocationServer.setLocationDetail(str);
            return aMapLocationServer;
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    private void a(Bundle bundle) {
        try {
            if (!this.t) {
                cl.a(this.e);
                if (bundle != null) {
                    this.n = cl.a(bundle.getBundle("optBundle"));
                }
                this.t = true;
                this.f.a(this.e);
                this.f.a();
                a(this.n);
                this.f.b();
            }
        } catch (Throwable th) {
            this.B = false;
            this.C = th.getMessage();
            cl.a(th, "ApsServiceCore", "init");
        }
    }

    private void a(Messenger messenger) {
        try {
            if (ck.d(this.e)) {
                a(messenger, 100, null);
            }
            this.d.removeMessages(4);
            if (ck.a()) {
                this.d.sendEmptyMessage(4);
            }
            this.d.removeMessages(5);
            if (ck.c() && ck.d() > 2) {
                this.d.sendEmptyMessage(5);
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "checkConfig");
        }
    }

    private static void a(Messenger messenger, int i, Bundle bundle) {
        if (messenger != null) {
            try {
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = i;
                messenger.send(obtain);
            } catch (Throwable th) {
                cl.a(th, "ApsServiceCore", "sendMessage");
            }
        }
    }

    private void a(Messenger messenger, AMapLocation aMapLocation, String str, long j) {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(AMapLocation.class.getClassLoader());
        bundle.putParcelable("loc", aMapLocation);
        bundle.putString("nb", str);
        bundle.putLong("netUseTime", j);
        this.h.put(messenger, Long.valueOf(ct.b()));
        a(messenger, 1, bundle);
    }

    private void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (this.f != null) {
                this.f.a(aMapLocationClientOption);
            }
            if (aMapLocationClientOption != null) {
                g = aMapLocationClientOption.isKillProcess();
                if (!(this.m == null || (aMapLocationClientOption.isOffset() == this.m.isOffset() && aMapLocationClientOption.isNeedAddress() == this.m.isNeedAddress() && aMapLocationClientOption.isLocationCacheEnable() == this.m.isLocationCacheEnable() && this.m.getGeoLanguage() == aMapLocationClientOption.getGeoLanguage()))) {
                    this.w = 0;
                    this.c = null;
                }
                this.m = aMapLocationClientOption;
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "setExtra");
        }
    }

    static /* synthetic */ void a(cv cvVar) {
        try {
            if (cvVar.z < ck.b()) {
                cvVar.z++;
                cvVar.f.e();
                cvVar.d.sendEmptyMessageDelayed(4, 2000);
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "doGpsFusion");
        }
    }

    static /* synthetic */ void a(cv cvVar, Messenger messenger) {
        try {
            cvVar.b(messenger);
            ck.f(cvVar.e);
            try {
                bq bqVar = cvVar.f;
                Context context = cvVar.e;
                bqVar.h();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            cl.a(th2, "ApsServiceCore", "doCallOtherSer");
        }
    }

    static /* synthetic */ void a(cv cvVar, Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && !cvVar.u) {
                    cvVar.u = true;
                    cvVar.b(messenger);
                    ck.f(cvVar.e);
                    try {
                        bq bqVar = cvVar.f;
                        Context context = cvVar.e;
                        bqVar.g();
                    } catch (Throwable th) {
                    }
                    cvVar.a(messenger);
                    if (ck.a(cvVar.y) && "1".equals(bundle.getString("isCacheLoc"))) {
                        cvVar.y = ct.b();
                        cvVar.f.e();
                    }
                    cvVar.h();
                }
            } catch (Throwable th2) {
                cl.a(th2, "ApsServiceCore", "doInitAuth");
            }
        }
    }

    static /* synthetic */ void a(cv cvVar, Socket socket) {
        BufferedReader bufferedReader;
        Throwable th;
        String str = null;
        if (socket != null) {
            try {
                int i = cl.f;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    try {
                        cvVar.a(bufferedReader);
                        String g = cvVar.g();
                        cl.f = i;
                        try {
                            cvVar.c(g);
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th2) {
                            cl.a(th2, "ApsServiceCore", "invokeSocketLocation part3");
                        }
                    } catch (Throwable th3) {
                        th2 = th3;
                        try {
                            str = cvVar.E + "&&" + cvVar.E + "({'package':'" + cvVar.a + "','error_code':1,'error':'params error'})";
                            cl.a(th2, "ApsServiceCore", "invokeSocketLocation");
                            cl.f = i;
                            try {
                                cvVar.c(str);
                                try {
                                    bufferedReader.close();
                                    socket.close();
                                } catch (Throwable th22) {
                                    cl.a(th22, "ApsServiceCore", "invokeSocketLocation part3");
                                }
                            } catch (Throwable th222) {
                                cl.a(th222, "ApsServiceCore", "invokeSocketLocation part3");
                            }
                        } catch (Throwable th4) {
                            th222 = th4;
                            cl.f = i;
                            try {
                                cvVar.c(str);
                                try {
                                    bufferedReader.close();
                                    socket.close();
                                } catch (Throwable th5) {
                                    cl.a(th5, "ApsServiceCore", "invokeSocketLocation part3");
                                }
                            } catch (Throwable th52) {
                                cl.a(th52, "ApsServiceCore", "invokeSocketLocation part3");
                            }
                            throw th222;
                        }
                    }
                } catch (Throwable th6) {
                    th222 = th6;
                    bufferedReader = null;
                    cl.f = i;
                    cvVar.c(str);
                    bufferedReader.close();
                    socket.close();
                    throw th222;
                }
            } catch (Throwable th2222) {
                cl.a(th2222, "ApsServiceCore", "invokeSocketLocation part4");
            }
        }
    }

    private void a(BufferedReader bufferedReader) throws Exception {
        String readLine = bufferedReader.readLine();
        int i = com.tencent.bugly.BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
        if (readLine != null && readLine.length() > 0) {
            String[] split = readLine.split(" ");
            if (split != null && split.length > 1) {
                split = split[1].split("\\?");
                if (split != null && split.length > 1) {
                    String[] split2 = split[1].split(HttpUtils.PARAMETERS_SEPARATOR);
                    if (split2 != null && split2.length > 0) {
                        int i2 = com.tencent.bugly.BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
                        for (String split3 : split2) {
                            String[] split4 = split3.split(HttpUtils.EQUAL_SIGN);
                            if (split4 != null && split4.length > 1) {
                                if ("to".equals(split4[0])) {
                                    i2 = ct.h(split4[1]);
                                }
                                if ("callback".equals(split4[0])) {
                                    this.E = split4[1];
                                }
                            }
                        }
                        i = i2;
                    }
                }
            }
        }
        cl.f = i;
    }

    private AMapLocationClientOption b(Bundle bundle) {
        AMapLocationClientOption a = cl.a(bundle.getBundle("optBundle"));
        a(a);
        try {
            String string = bundle.getString("d");
            if (!TextUtils.isEmpty(string)) {
                df.a(string);
            }
        } catch (Throwable th) {
            cl.a(th, "APSManager", "parseBundle");
        }
        return a;
    }

    private void b(Messenger messenger) {
        try {
            bq bqVar = this.f;
            bq.b(this.e);
            if (ck.q()) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("ngpsAble", ck.s());
                a(messenger, 7, bundle);
                ck.r();
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "initAuth");
        }
    }

    static /* synthetic */ void b(cv cvVar) {
        try {
            if (ck.e()) {
                cvVar.f.e();
            } else if (!ct.e(cvVar.e)) {
                cvVar.f.e();
            }
            cvVar.d.sendEmptyMessageDelayed(5, (long) (ck.d() * 1000));
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "doOffFusion");
        }
    }

    static /* synthetic */ void b(cv cvVar, Messenger messenger, Bundle bundle) {
        long j = 0;
        if (bundle != null) {
            long b;
            try {
                if (!bundle.isEmpty()) {
                    AMapLocationClientOption b2 = cvVar.b(bundle);
                    if (cvVar.h.containsKey(messenger) && !b2.isOnceLocation()) {
                        if (ct.b() - ((Long) cvVar.h.get(messenger)).longValue() < 800) {
                            return;
                        }
                    }
                    if (cvVar.B) {
                        b = ct.b();
                        if (!ct.a(cvVar.x) || b - cvVar.w >= 600) {
                            AMapLocation aMapLocation;
                            cp cpVar = new cp();
                            cpVar.a(ct.b());
                            cvVar.x = cvVar.f.d();
                            if (cvVar.x.getLocationType() == 6 || cvVar.x.getLocationType() == 5) {
                                j = cvVar.x.k();
                            }
                            cpVar.a(cvVar.x);
                            cvVar.x = cvVar.f.a(cvVar.x, new String[0]);
                            b = j;
                            cpVar.b(ct.b());
                            if (ct.a(cvVar.x)) {
                                cvVar.w = ct.b();
                            }
                            if (cvVar.x == null) {
                                cvVar.x = a(8, "loc is null#0801");
                            }
                            String l = cvVar.x != null ? cvVar.x.l() : null;
                            AMapLocation aMapLocation2 = cvVar.x;
                            try {
                                if (b2.isLocationCacheEnable() && cvVar.A != null) {
                                    aMapLocation2 = cvVar.A.a(cvVar.x, l, b2.getLastLocationLifeCycle());
                                }
                                aMapLocation = aMapLocation2;
                            } catch (Throwable th) {
                                cl.a(th, "ApsServiceCore", "fixLastLocation");
                                aMapLocation = aMapLocation2;
                            }
                            cvVar.a(messenger, aMapLocation, l, b);
                            cq.a(cvVar.e, cpVar);
                        } else {
                            cvVar.a(messenger, cvVar.x, cvVar.x.l(), 0);
                        }
                        cvVar.b(messenger);
                        if (ck.A()) {
                            cvVar.a(messenger);
                        }
                        if (ck.a(cvVar.y) && cvVar.x != null && (cvVar.x.getLocationType() == 2 || cvVar.x.getLocationType() == 4 || cvVar.x.getLocationType() == 9)) {
                            cvVar.y = ct.b();
                            cvVar.f.e();
                        }
                        cvVar.h();
                        return;
                    }
                    cvVar.x = a(9, "init error : " + cvVar.C + "#0901");
                    cvVar.a(messenger, cvVar.x, cvVar.x.l(), 0);
                    cq.a(null, 2091);
                }
            } catch (Throwable th2) {
                cl.a(th2, "ApsServiceCore", "doLocation");
            }
        }
    }

    static /* synthetic */ void c(cv cvVar) {
        try {
            if (ck.a(cvVar.e, cvVar.v)) {
                cvVar.v = ct.a();
                cvVar.f.e();
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "doNGps");
        }
    }

    private void c(String str) throws UnsupportedEncodingException, IOException {
        PrintStream printStream = new PrintStream(this.q.getOutputStream(), true, "UTF-8");
        printStream.println("HTTP/1.0 200 OK");
        printStream.println("Content-Length:" + str.getBytes("UTF-8").length);
        printStream.println();
        printStream.println(str);
        printStream.close();
    }

    public static void f() {
        g = false;
    }

    private String g() {
        long currentTimeMillis = System.currentTimeMillis();
        if (ct.e(this.e)) {
            return this.E + "&&" + this.E + "({'package':'" + this.a + "','error_code':36,'error':'app is background'})";
        }
        if (this.x == null || currentTimeMillis - this.x.getTime() > 5000) {
            try {
                this.x = this.f.d();
            } catch (Throwable th) {
                cl.a(th, "ApsServiceCore", "getSocketLocResult");
            }
        }
        return this.x == null ? this.E + "&&" + this.E + "({'package':'" + this.a + "','error_code':8,'error':'unknown error'})" : this.x.getErrorCode() != 0 ? this.E + "&&" + this.E + "({'package':'" + this.a + "','error_code':" + this.x.getErrorCode() + ",'error':'" + this.x.getErrorInfo() + "'})" : this.E + "&&" + this.E + "({'package':'" + this.a + "','error_code':0,'error':'','location':{'y':" + this.x.getLatitude() + ",'precision':" + this.x.getAccuracy() + ",'x':" + this.x.getLongitude() + "},'version_code':'4.2.0','version':'4.2.0'})";
    }

    private void h() {
        try {
            if (this.f != null) {
                this.f.k();
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "startColl");
        }
    }

    public final void a() {
        try {
            this.i = new cq();
            this.b = new b(this, "amapLocCoreThread");
            this.b.setPriority(5);
            this.b.start();
            this.d = new a(this, this.b.getLooper());
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public final void a(Intent intent) {
        if ("true".equals(intent.getStringExtra("as")) && this.d != null) {
            this.d.sendEmptyMessageDelayed(9, 100);
        }
    }

    final void a(Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && ck.x()) {
                    float a;
                    double d = bundle.getDouble("lat");
                    double d2 = bundle.getDouble("lon");
                    b(bundle);
                    if (this.c != null) {
                        a = ct.a(new double[]{d, d2, this.c.getLatitude(), this.c.getLongitude()});
                        if (a < ((float) (ck.y() * 3))) {
                            Bundle bundle2 = new Bundle();
                            bundle2.setClassLoader(AMapLocation.class.getClassLoader());
                            bundle2.putInt("I_MAX_GEO_DIS", ck.y() * 3);
                            bundle2.putInt("I_MIN_GEO_DIS", ck.y());
                            bundle2.putParcelable("loc", this.c);
                            a(messenger, 6, bundle2);
                        }
                    } else {
                        a = -1.0f;
                    }
                    if (a == -1.0f || a > ((float) ck.y())) {
                        a(bundle);
                        this.c = this.f.a(d, d2);
                    }
                }
            } catch (Throwable th) {
                cl.a(th, "ApsServiceCore", "doLocationGeo");
            }
        }
    }

    public final boolean a(String str) {
        if (TextUtils.isEmpty(this.l)) {
            this.l = cl.b(this.e);
        }
        return !TextUtils.isEmpty(str) && str.equals(this.l);
    }

    public final Handler b() {
        return this.d;
    }

    public final void b(Intent intent) {
        String stringExtra = intent.getStringExtra("a");
        if (!TextUtils.isEmpty(stringExtra)) {
            dc.a(this.e, stringExtra);
        }
        this.a = intent.getStringExtra("b");
        db.a(this.a);
        stringExtra = intent.getStringExtra("d");
        if (!TextUtils.isEmpty(stringExtra)) {
            df.a(stringExtra);
        }
        ck.a = intent.getBooleanExtra("f", true);
    }

    public final void c() {
        try {
            if (!this.r) {
                this.s = new c(this);
                this.s.start();
                this.r = true;
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "startSocket");
        }
    }

    public final void d() {
        try {
            if (this.q != null) {
                this.q.close();
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "doStopScocket 1");
        }
        try {
            if (this.o != null) {
                this.o.close();
            }
        } catch (Throwable th2) {
            cl.a(th2, "ApsServiceCore", "doStopScocket 2");
        }
        try {
            if (this.s != null) {
                this.s.interrupt();
            }
        } catch (Throwable th3) {
        }
        this.s = null;
        this.o = null;
        this.p = false;
        this.r = false;
    }

    public final void e() {
        try {
            this.h.clear();
            this.h = null;
            if (this.f != null) {
                bq bqVar = this.f;
                bq.b(this.e);
            }
            if (this.d != null) {
                this.d.removeCallbacksAndMessages(null);
            }
            if (this.b != null) {
                if (VERSION.SDK_INT >= 18) {
                    co.a(this.b, HandlerThread.class, "quitSafely", new Object[0]);
                } else {
                    this.b.quit();
                }
            }
        } catch (Throwable th) {
            cl.a(th, "ApsServiceCore", "threadDestroy");
            return;
        }
        this.b = null;
        this.d = null;
        if (this.A != null) {
            this.A.c();
            this.A = null;
        }
        d();
        this.t = false;
        this.u = false;
        this.f.f();
        cq.a(this.e);
        if (!(this.i == null || this.j == 0 || this.k == 0)) {
            long b = ct.b() - this.j;
            cq.a(this.e, this.i.c(this.e), this.i.d(this.e), this.k, b);
            this.i.e(this.e);
        }
        j.b();
        if (g) {
            Process.killProcess(Process.myPid());
        }
    }
}
