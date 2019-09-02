package com.amap.api.mapcore.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.HandlerThread;
import android.support.v4.internal.view.SupportMenu;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
/* compiled from: CgiManager */
public final class ks {
    int a = 0;
    ArrayList<kr> b = new ArrayList();
    TelephonyManager c = null;
    long d = 0;
    CellLocation e;
    boolean f = false;
    PhoneStateListener g = null;
    String h = null;
    boolean i = false;
    StringBuilder j = null;
    HandlerThread k = null;
    private Context l;
    private String m = null;
    private ArrayList<kr> n = new ArrayList();
    private int o = -113;
    private kq p = null;
    private Object q;
    private int r = 0;
    private long s = 0;
    private boolean t = false;
    private Object u = new Object();

    /* compiled from: CgiManager */
    class a extends HandlerThread {
        final /* synthetic */ ks a;

        public a(ks ksVar, String str) {
            this.a = ksVar;
            super(str);
        }

        protected final void onLooperPrepared() {
            try {
                super.onLooperPrepared();
                synchronized (this.a.u) {
                    if (!this.a.t) {
                        this.a.k();
                    }
                }
            } catch (Throwable th) {
            }
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
            }
        }
    }

    public ks(Context context) {
        this.l = context;
        if (this.c == null) {
            this.c = (TelephonyManager) lc.a(this.l, "phone");
        }
        j();
        this.p = new kq();
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object a = la.a(obj, str, objArr);
            CellLocation cellLocation = a != null ? (CellLocation) a : null;
            if (b(cellLocation)) {
                return cellLocation;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private CellLocation a(List<CellInfo> list) {
        CellLocation cellLocation;
        CellLocation cellLocation2 = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        kr krVar;
        for (int i = 0; i < list.size(); i++) {
            CellInfo cellInfo = (CellInfo) list.get(i);
            if (cellInfo != null) {
                try {
                    boolean isRegistered = cellInfo.isRegistered();
                    if (!(cellInfo instanceof CellInfoCdma)) {
                        if (!(cellInfo instanceof CellInfoGsm)) {
                            if (!(cellInfo instanceof CellInfoWcdma)) {
                                kr a;
                                if (cellInfo instanceof CellInfoLte) {
                                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                                    if (a(cellInfoLte.getCellIdentity())) {
                                        a = a(cellInfoLte, isRegistered);
                                    }
                                } else {
                                    a = null;
                                }
                                krVar = a;
                                break;
                            }
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                            if (a(cellInfoWcdma.getCellIdentity())) {
                                krVar = a(cellInfoWcdma, isRegistered);
                                break;
                            }
                        } else {
                            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                            if (a(cellInfoGsm.getCellIdentity())) {
                                krVar = a(cellInfoGsm, isRegistered);
                                break;
                            }
                        }
                    } else {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                        if (a(cellInfoCdma.getCellIdentity())) {
                            krVar = a(cellInfoCdma, isRegistered);
                            break;
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
        krVar = null;
        CellLocation cdmaCellLocation;
        if (krVar != null) {
            try {
                if (krVar.k == 2) {
                    cdmaCellLocation = new CdmaCellLocation();
                    try {
                        cdmaCellLocation.setCellLocationData(krVar.i, krVar.e, krVar.f, krVar.g, krVar.h);
                    } catch (Throwable th2) {
                        cellLocation = cdmaCellLocation;
                        cdmaCellLocation = null;
                        cellLocation2 = cellLocation;
                        cellLocation = cellLocation2;
                        cellLocation2 = cdmaCellLocation;
                        cdmaCellLocation = cellLocation;
                        return cdmaCellLocation == null ? cellLocation2 : cdmaCellLocation;
                    }
                } else {
                    cdmaCellLocation = new GsmCellLocation();
                    try {
                        cdmaCellLocation.setLacAndCid(krVar.c, krVar.d);
                        cellLocation2 = cdmaCellLocation;
                        cdmaCellLocation = null;
                    } catch (Throwable th3) {
                        cellLocation = cellLocation2;
                        cellLocation2 = cdmaCellLocation;
                        cdmaCellLocation = cellLocation;
                        if (cdmaCellLocation == null) {
                        }
                    }
                }
            } catch (Throwable th4) {
                cdmaCellLocation = null;
                cellLocation = cellLocation2;
                cellLocation2 = cdmaCellLocation;
                cdmaCellLocation = cellLocation;
                if (cdmaCellLocation == null) {
                }
            }
        }
        cdmaCellLocation = null;
        cellLocation2 = cdmaCellLocation;
        cdmaCellLocation = null;
        if (cdmaCellLocation == null) {
        }
    }

    private static kr a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        kr krVar = new kr(i, z);
        krVar.a = i2;
        krVar.b = i3;
        krVar.c = i4;
        krVar.d = i5;
        krVar.j = i6;
        return krVar;
    }

    @SuppressLint({"NewApi"})
    private kr a(CellInfoCdma cellInfoCdma, boolean z) {
        int parseInt;
        int parseInt2;
        kr a;
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        String[] a2 = lc.a(this.c);
        try {
            parseInt = Integer.parseInt(a2[0]);
            try {
                parseInt2 = Integer.parseInt(a2[1]);
            } catch (Throwable th) {
                parseInt2 = 0;
                a = a(2, z, parseInt, parseInt2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                a.g = cellIdentity.getSystemId();
                a.h = cellIdentity.getNetworkId();
                a.i = cellIdentity.getBasestationId();
                a.e = cellIdentity.getLatitude();
                a.f = cellIdentity.getLongitude();
                return a;
            }
        } catch (Throwable th2) {
            parseInt = 0;
            parseInt2 = 0;
            a = a(2, z, parseInt, parseInt2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
            a.g = cellIdentity.getSystemId();
            a.h = cellIdentity.getNetworkId();
            a.i = cellIdentity.getBasestationId();
            a.e = cellIdentity.getLatitude();
            a.f = cellIdentity.getLongitude();
            return a;
        }
        a = a(2, z, parseInt, parseInt2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
        a.g = cellIdentity.getSystemId();
        a.h = cellIdentity.getNetworkId();
        a.i = cellIdentity.getBasestationId();
        a.e = cellIdentity.getLatitude();
        a.f = cellIdentity.getLongitude();
        return a;
    }

    @SuppressLint({"NewApi"})
    private static kr a(CellInfoGsm cellInfoGsm, boolean z) {
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        return a(1, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
    }

    @SuppressLint({"NewApi"})
    private static kr a(CellInfoLte cellInfoLte, boolean z) {
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        return a(3, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getTac(), cellIdentity.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
    }

    @SuppressLint({"NewApi"})
    private static kr a(CellInfoWcdma cellInfoWcdma, boolean z) {
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        return a(4, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
    }

    private static kr a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            kr krVar = new kr(1, false);
            krVar.a = Integer.parseInt(strArr[0]);
            krVar.b = Integer.parseInt(strArr[1]);
            krVar.c = la.b(neighboringCellInfo, "getLac", new Object[0]);
            krVar.d = neighboringCellInfo.getCid();
            krVar.j = lc.a(neighboringCellInfo.getRssi());
            return krVar;
        } catch (Throwable th) {
            kz.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private void a(CellLocation cellLocation, String[] strArr) {
        if (cellLocation != null && this.c != null) {
            this.b.clear();
            if (b(cellLocation)) {
                this.a = 1;
                this.b.add(c(cellLocation, strArr));
                List<NeighboringCellInfo> neighboringCellInfo = this.c.getNeighboringCellInfo();
                if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                    for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                        if (neighboringCellInfo2 != null && a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid())) {
                            kr a = a(neighboringCellInfo2, strArr);
                            if (!(a == null || this.b.contains(a))) {
                                this.b.add(a);
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean a(int i) {
        return i > 0 && i <= 15;
    }

    private static boolean a(int i, int i2) {
        return (i == -1 || i == 0 || i > SupportMenu.USER_MASK || i2 == -1 || i2 == 0 || i2 == SupportMenu.USER_MASK || i2 >= 268435455) ? false : true;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityCdma cellIdentityCdma) {
        return cellIdentityCdma != null && cellIdentityCdma.getSystemId() > 0 && cellIdentityCdma.getNetworkId() >= 0 && cellIdentityCdma.getBasestationId() >= 0;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityGsm cellIdentityGsm) {
        return cellIdentityGsm != null && c(cellIdentityGsm.getLac()) && d(cellIdentityGsm.getCid());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityLte cellIdentityLte) {
        return cellIdentityLte != null && c(cellIdentityLte.getTac()) && d(cellIdentityLte.getCi());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityWcdma cellIdentityWcdma) {
        return cellIdentityWcdma != null && c(cellIdentityWcdma.getLac()) && d(cellIdentityWcdma.getCid());
    }

    private void b(int i) {
        if (i == -113) {
            this.o = -113;
            return;
        }
        this.o = i;
        switch (this.a) {
            case 1:
            case 2:
                if (this.b != null && !this.b.isEmpty()) {
                    try {
                        ((kr) this.b.get(0)).j = this.o;
                        return;
                    } catch (Throwable th) {
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    private void b(CellLocation cellLocation, String[] strArr) {
        Object obj = 1;
        if (cellLocation != null) {
            this.b.clear();
            if (lc.c() >= 5) {
                try {
                    if (this.q != null) {
                        Object obj2;
                        try {
                            Field declaredField = cellLocation.getClass().getDeclaredField("mGsmCellLoc");
                            if (!declaredField.isAccessible()) {
                                declaredField.setAccessible(true);
                            }
                            CellLocation cellLocation2 = (GsmCellLocation) declaredField.get(cellLocation);
                            if (cellLocation2 != null && b(cellLocation2)) {
                                a(cellLocation2, strArr);
                                obj2 = 1;
                                if (obj2 != null) {
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                        }
                        obj2 = null;
                        if (obj2 != null) {
                            return;
                        }
                    }
                    if (b(cellLocation)) {
                        this.a = 2;
                        kr krVar = new kr(2, true);
                        krVar.a = Integer.parseInt(strArr[0]);
                        krVar.b = Integer.parseInt(strArr[1]);
                        krVar.g = la.b(cellLocation, "getSystemId", new Object[0]);
                        krVar.h = la.b(cellLocation, "getNetworkId", new Object[0]);
                        krVar.i = la.b(cellLocation, "getBaseStationId", new Object[0]);
                        krVar.j = this.o;
                        krVar.e = la.b(cellLocation, "getBaseStationLatitude", new Object[0]);
                        krVar.f = la.b(cellLocation, "getBaseStationLongitude", new Object[0]);
                        if (krVar.e != krVar.f || krVar.e <= 0) {
                            obj = null;
                        }
                        if (krVar.e < 0 || krVar.f < 0 || krVar.e == Integer.MAX_VALUE || krVar.f == Integer.MAX_VALUE || r1 != null) {
                            krVar.e = 0;
                            krVar.f = 0;
                        }
                        if (!this.b.contains(krVar)) {
                            this.b.add(krVar);
                        }
                    }
                } catch (Throwable th2) {
                    kz.a(th2, "CgiManager", "hdlCdmaLocChange");
                }
            }
        }
    }

    private boolean b(CellLocation cellLocation) {
        boolean a = a(cellLocation);
        if (!a) {
            this.a = 0;
        }
        return a;
    }

    private int c(CellLocation cellLocation) {
        if (this.i || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            kz.a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    private kr c(CellLocation cellLocation, String[] strArr) {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        kr krVar = new kr(1, true);
        krVar.a = lc.d(strArr[0]);
        krVar.b = lc.d(strArr[1]);
        krVar.c = gsmCellLocation.getLac();
        krVar.d = gsmCellLocation.getCid();
        krVar.j = this.o;
        return krVar;
    }

    private static boolean c(int i) {
        return (i == -1 || i == 0 || i > SupportMenu.USER_MASK) ? false : true;
    }

    private static boolean d(int i) {
        return (i == -1 || i == 0 || i == SupportMenu.USER_MASK || i >= 268435455) ? false : true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void j() {
        /*
        r3 = this;
        r0 = r3.c;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r3.c;	 Catch:{ SecurityException -> 0x003b, Throwable -> 0x0043 }
        r0 = r0.getCellLocation();	 Catch:{ SecurityException -> 0x003b, Throwable -> 0x0043 }
        r1 = r3.l;	 Catch:{ SecurityException -> 0x003b, Throwable -> 0x0043 }
        r0 = r3.c(r0);	 Catch:{ SecurityException -> 0x003b, Throwable -> 0x0043 }
        r3.a = r0;	 Catch:{ SecurityException -> 0x003b, Throwable -> 0x0043 }
    L_0x0013:
        r0 = r3.u();	 Catch:{ Throwable -> 0x005d }
        r3.r = r0;	 Catch:{ Throwable -> 0x005d }
        r0 = r3.r;	 Catch:{ Throwable -> 0x005d }
        switch(r0) {
            case 1: goto L_0x0052;
            case 2: goto L_0x005f;
            default: goto L_0x001e;
        };	 Catch:{ Throwable -> 0x005d }
    L_0x001e:
        r0 = r3.l;	 Catch:{ Throwable -> 0x005d }
        r1 = "phone2";
        r0 = com.amap.api.mapcore.util.lc.a(r0, r1);	 Catch:{ Throwable -> 0x005d }
        r3.q = r0;	 Catch:{ Throwable -> 0x005d }
    L_0x0028:
        r0 = r3.k;
        if (r0 != 0) goto L_0x0004;
    L_0x002c:
        r0 = new com.amap.api.mapcore.util.ks$a;
        r1 = "listenerPhoneStateThread";
        r0.<init>(r3, r1);
        r3.k = r0;
        r0 = r3.k;
        r0.start();
        goto L_0x0004;
    L_0x003b:
        r0 = move-exception;
        r0 = r0.getMessage();
        r3.h = r0;
        goto L_0x0013;
    L_0x0043:
        r0 = move-exception;
        r1 = 0;
        r3.h = r1;
        r1 = "CgiManager";
        r2 = "CgiManager";
        com.amap.api.mapcore.util.kz.a(r0, r1, r2);
        r0 = 0;
        r3.a = r0;
        goto L_0x0013;
    L_0x0052:
        r0 = r3.l;	 Catch:{ Throwable -> 0x005d }
        r1 = "phone_msim";
        r0 = com.amap.api.mapcore.util.lc.a(r0, r1);	 Catch:{ Throwable -> 0x005d }
        r3.q = r0;	 Catch:{ Throwable -> 0x005d }
        goto L_0x0028;
    L_0x005d:
        r0 = move-exception;
        goto L_0x0028;
    L_0x005f:
        r0 = r3.l;	 Catch:{ Throwable -> 0x005d }
        r1 = "phone2";
        r0 = com.amap.api.mapcore.util.lc.a(r0, r1);	 Catch:{ Throwable -> 0x005d }
        r3.q = r0;	 Catch:{ Throwable -> 0x005d }
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ks.j():void");
    }

    private void k() {
        this.g = new PhoneStateListener(this) {
            final /* synthetic */ ks a;

            {
                this.a = r1;
            }

            public final void onCellLocationChanged(CellLocation cellLocation) {
                try {
                    if (this.a.a(cellLocation)) {
                        this.a.e = cellLocation;
                        this.a.f = true;
                        this.a.s = lc.b();
                    }
                } catch (Throwable th) {
                }
            }

            public final void onServiceStateChanged(ServiceState serviceState) {
                try {
                    switch (serviceState.getState()) {
                        case 0:
                            this.a.f();
                            return;
                        case 1:
                            this.a.h();
                            return;
                        default:
                            return;
                    }
                } catch (Throwable th) {
                }
            }

            public final void onSignalStrengthChanged(int i) {
                int i2 = -113;
                try {
                    switch (this.a.a) {
                        case 1:
                            i2 = lc.a(i);
                            break;
                        case 2:
                            i2 = lc.a(i);
                            break;
                    }
                    this.a.b(i2);
                } catch (Throwable th) {
                }
            }

            public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
                if (signalStrength != null) {
                    int i = -113;
                    try {
                        switch (this.a.a) {
                            case 1:
                                i = lc.a(signalStrength.getGsmSignalStrength());
                                break;
                            case 2:
                                i = signalStrength.getCdmaDbm();
                                break;
                        }
                        this.a.b(i);
                    } catch (Throwable th) {
                    }
                }
            }
        };
        String str = "android.telephony.PhoneStateListener";
        int i = 0;
        if (lc.c() < 7) {
            try {
                i = la.b(str, "LISTEN_SIGNAL_STRENGTH");
            } catch (Throwable th) {
            }
        } else {
            try {
                i = la.b(str, "LISTEN_SIGNAL_STRENGTHS");
            } catch (Throwable th2) {
            }
        }
        if (i == 0) {
            try {
                this.c.listen(this.g, 16);
                return;
            } catch (Throwable th3) {
                return;
            }
        }
        try {
            this.c.listen(this.g, i | 16);
        } catch (Throwable th4) {
        }
    }

    private CellLocation l() {
        if (this.c != null) {
            try {
                CellLocation cellLocation = this.c.getCellLocation();
                this.h = null;
                if (b(cellLocation)) {
                    this.e = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e) {
                this.h = e.getMessage();
            } catch (Throwable th) {
                this.h = null;
                kz.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    private boolean m() {
        return !this.i && lc.b() - this.d >= 10000;
    }

    private void n() {
        h();
    }

    private void o() {
        switch (d()) {
            case 1:
                if (this.b.isEmpty()) {
                    this.a = 0;
                    return;
                }
                return;
            case 2:
                if (this.b.isEmpty()) {
                    this.a = 0;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void p() {
        CellLocation q;
        if (!(this.i || this.c == null)) {
            q = q();
            if (!b(q)) {
                q = r();
            }
            if (b(q)) {
                this.e = q;
                this.s = lc.b();
            } else if (lc.b() - this.s > 60000) {
                this.e = null;
                this.b.clear();
                this.n.clear();
            }
        }
        if (!this.f) {
            q = this.e;
        }
        this.f = true;
        if (b(this.e)) {
            String[] a = lc.a(this.c);
            CellLocation cellLocation = this.e;
            Context context = this.l;
            switch (c(cellLocation)) {
                case 1:
                    a(this.e, a);
                    break;
                case 2:
                    b(this.e, a);
                    break;
            }
        }
        try {
            if (lc.c() >= 18) {
                t();
            }
        } catch (Throwable th) {
        }
        if (this.c != null) {
            this.m = this.c.getNetworkOperator();
            if (!TextUtils.isEmpty(this.m)) {
                this.a |= 8;
            }
        }
    }

    @SuppressLint({"NewApi"})
    private CellLocation q() {
        CellLocation cellLocation = null;
        TelephonyManager telephonyManager = this.c;
        if (telephonyManager == null) {
            return cellLocation;
        }
        CellLocation l = l();
        if (b(l)) {
            return l;
        }
        if (lc.c() >= 18) {
            try {
                cellLocation = a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e) {
                this.h = e.getMessage();
            }
        }
        if (cellLocation != null) {
            return cellLocation;
        }
        cellLocation = a(telephonyManager, "getCellLocationExt", Integer.valueOf(1));
        if (cellLocation != null) {
            return cellLocation;
        }
        cellLocation = a(telephonyManager, "getCellLocationGemini", Integer.valueOf(1));
        return cellLocation != null ? cellLocation : cellLocation;
    }

    private CellLocation r() {
        CellLocation cellLocation = null;
        Object obj = this.q;
        if (obj != null) {
            try {
                Class s = s();
                if (s.isInstance(obj)) {
                    obj = s.cast(obj);
                    String str = "getCellLocation";
                    cellLocation = a(obj, str, new Object[0]);
                    if (cellLocation == null) {
                        cellLocation = a(obj, str, Integer.valueOf(1));
                        if (cellLocation == null) {
                            cellLocation = a(obj, "getCellLocationGemini", Integer.valueOf(1));
                            if (cellLocation == null) {
                                cellLocation = a(obj, "getAllCellInfo", Integer.valueOf(1));
                                if (cellLocation != null) {
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                kz.a(th, "CgiManager", "getSim2Cgi");
            }
        }
        return cellLocation;
    }

    private Class<?> s() {
        String str;
        Class<?> cls = null;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (this.r) {
            case 0:
                str = "android.telephony.TelephonyManager";
                break;
            case 1:
                str = "android.telephony.MSimTelephonyManager";
                break;
            case 2:
                str = "android.telephony.TelephonyManager2";
                break;
            default:
                str = cls;
                break;
        }
        try {
            cls = systemClassLoader.loadClass(str);
        } catch (Throwable th) {
            kz.a(th, "CgiManager", "getSim2TmClass");
        }
        return cls;
    }

    @SuppressLint({"NewApi"})
    private void t() {
        List list = null;
        if (this.c != null) {
            List list2;
            ArrayList arrayList = this.n;
            kq kqVar = this.p;
            try {
                list = this.c.getAllCellInfo();
                this.h = null;
                list2 = list;
            } catch (SecurityException e) {
                this.h = e.getMessage();
                list2 = list;
            }
            if (list2 != null) {
                int size = list2.size();
                if (size != 0) {
                    if (arrayList != null) {
                        arrayList.clear();
                    }
                    for (int i = 0; i < size; i++) {
                        CellInfo cellInfo = (CellInfo) list2.get(i);
                        if (cellInfo != null) {
                            try {
                                boolean isRegistered = cellInfo.isRegistered();
                                kr a;
                                if (cellInfo instanceof CellInfoCdma) {
                                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                                    if (a(cellInfoCdma.getCellIdentity())) {
                                        a = a(cellInfoCdma, isRegistered);
                                        a.l = (short) ((int) Math.min(65535, kqVar.a(a)));
                                        arrayList.add(a);
                                    }
                                } else if (cellInfo instanceof CellInfoGsm) {
                                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                                    if (a(cellInfoGsm.getCellIdentity())) {
                                        a = a(cellInfoGsm, isRegistered);
                                        a.l = (short) ((int) Math.min(65535, kqVar.a(a)));
                                        arrayList.add(a);
                                    }
                                } else if (cellInfo instanceof CellInfoWcdma) {
                                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                                    if (a(cellInfoWcdma.getCellIdentity())) {
                                        a = a(cellInfoWcdma, isRegistered);
                                        a.l = (short) ((int) Math.min(65535, kqVar.a(a)));
                                        arrayList.add(a);
                                    }
                                } else if (cellInfo instanceof CellInfoLte) {
                                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                                    if (a(cellInfoLte.getCellIdentity())) {
                                        a = a(cellInfoLte, isRegistered);
                                        a.l = (short) ((int) Math.min(65535, kqVar.a(a)));
                                        arrayList.add(a);
                                    }
                                }
                            } catch (Throwable th) {
                            }
                        }
                    }
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                this.a |= 4;
                kqVar.a(arrayList);
            }
        }
    }

    private int u() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            this.r = 1;
        } catch (Throwable th) {
        }
        if (this.r == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                this.r = 2;
            } catch (Throwable th2) {
            }
        }
        return this.r;
    }

    public final ArrayList<kr> a() {
        return this.b;
    }

    final boolean a(CellLocation cellLocation) {
        if (cellLocation == null) {
            return false;
        }
        boolean z = true;
        Context context = this.l;
        switch (c(cellLocation)) {
            case 1:
                try {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    z = a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
                    break;
                } catch (Throwable th) {
                    kz.a(th, "CgiManager", "cgiUseful Cgi.I_GSM_T");
                    break;
                }
            case 2:
                try {
                    if (la.b(cellLocation, "getSystemId", new Object[0]) <= 0 || la.b(cellLocation, "getNetworkId", new Object[0]) < 0 || la.b(cellLocation, "getBaseStationId", new Object[0]) < 0) {
                        z = false;
                        break;
                    }
                } catch (Throwable th2) {
                    kz.a(th2, "CgiManager", "cgiUseful Cgi.I_CDMA_T");
                    break;
                }
        }
        return z;
    }

    public final ArrayList<kr> b() {
        return this.n;
    }

    public final int c() {
        return this.a;
    }

    public final int d() {
        return this.a & 3;
    }

    public final TelephonyManager e() {
        return this.c;
    }

    public final void f() {
        try {
            this.i = lc.a(this.l);
            if (m() || this.b.isEmpty()) {
                p();
                this.d = lc.b();
            }
            if (this.i) {
                n();
            } else {
                o();
            }
        } catch (SecurityException e) {
            this.h = e.getMessage();
        } catch (Throwable th) {
            kz.a(th, "CgiManager", "refresh");
        }
    }

    public final void g() {
        this.p.a();
        this.s = 0;
        synchronized (this.u) {
            this.t = true;
        }
        if (!(this.c == null || this.g == null)) {
            try {
                this.c.listen(this.g, 0);
            } catch (Throwable th) {
                kz.a(th, "CgiManager", "destroy");
            }
        }
        this.g = null;
        if (this.k != null) {
            this.k.quit();
            this.k = null;
        }
        this.o = -113;
        this.c = null;
        this.q = null;
    }

    final void h() {
        this.h = null;
        this.e = null;
        this.a = 0;
        this.b.clear();
        this.n.clear();
    }

    public final String i() {
        return this.m;
    }
}
