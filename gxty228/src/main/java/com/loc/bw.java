package com.loc;

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
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
/* compiled from: CgiManager */
public final class bw {
    int a = 0;
    ArrayList<bv> b = new ArrayList();
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
    private ArrayList<bv> n = new ArrayList();
    private int o = -113;
    private bu p = null;
    private Object q;
    private int r = 0;
    private long s = 0;
    private boolean t = false;
    private Object u = new Object();

    /* compiled from: CgiManager */
    class a extends HandlerThread {
        final /* synthetic */ bw a;

        public a(bw bwVar, String str) {
            this.a = bwVar;
            super(str);
        }

        protected final void onLooperPrepared() {
            try {
                super.onLooperPrepared();
                synchronized (this.a.u) {
                    if (!this.a.t) {
                        bw bwVar = this.a;
                        bwVar.g = new PhoneStateListener(bwVar) {
                            final /* synthetic */ bw a;

                            {
                                this.a = r1;
                            }

                            public final void onCellLocationChanged(CellLocation cellLocation) {
                                try {
                                    if (this.a.a(cellLocation)) {
                                        this.a.e = cellLocation;
                                        this.a.f = true;
                                        this.a.s = ct.b();
                                    }
                                } catch (Throwable th) {
                                }
                            }

                            public final void onServiceStateChanged(ServiceState serviceState) {
                                try {
                                    switch (serviceState.getState()) {
                                        case 0:
                                            this.a.a(false, false);
                                            return;
                                        case 1:
                                            this.a.i();
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
                                            i2 = ct.a(i);
                                            break;
                                        case 2:
                                            i2 = ct.a(i);
                                            break;
                                    }
                                    bw.a(this.a, i2);
                                } catch (Throwable th) {
                                }
                            }

                            public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
                                if (signalStrength != null) {
                                    int i = -113;
                                    try {
                                        switch (this.a.a) {
                                            case 1:
                                                i = ct.a(signalStrength.getGsmSignalStrength());
                                                break;
                                            case 2:
                                                i = signalStrength.getCdmaDbm();
                                                break;
                                        }
                                        bw.a(this.a, i);
                                    } catch (Throwable th) {
                                    }
                                }
                            }
                        };
                        String str = "android.telephony.PhoneStateListener";
                        int i = 0;
                        if (ct.c() < 7) {
                            try {
                                i = co.b(str, "LISTEN_SIGNAL_STRENGTH");
                            } catch (Throwable th) {
                            }
                        } else {
                            try {
                                i = co.b(str, "LISTEN_SIGNAL_STRENGTHS");
                            } catch (Throwable th2) {
                            }
                        }
                        if (i == 0) {
                            try {
                                bwVar.c.listen(bwVar.g, 16);
                            } catch (Throwable th3) {
                            }
                        } else {
                            try {
                                bwVar.c.listen(bwVar.g, i | 16);
                            } catch (Throwable th4) {
                            }
                        }
                    }
                }
            } catch (Throwable th5) {
            }
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public bw(android.content.Context r7) {
        /*
        r6 = this;
        r4 = 0;
        r3 = 0;
        r2 = 0;
        r6.<init>();
        r6.a = r3;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r6.b = r0;
        r6.m = r2;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r6.n = r0;
        r0 = -113; // 0xffffffffffffff8f float:NaN double:NaN;
        r6.o = r0;
        r6.c = r2;
        r6.p = r2;
        r6.d = r4;
        r6.r = r3;
        r6.s = r4;
        r6.f = r3;
        r6.g = r2;
        r6.h = r2;
        r6.i = r3;
        r6.j = r2;
        r6.k = r2;
        r6.t = r3;
        r0 = new java.lang.Object;
        r0.<init>();
        r6.u = r0;
        r6.l = r7;
        r0 = r6.c;
        if (r0 != 0) goto L_0x004e;
    L_0x0042:
        r0 = r6.l;
        r1 = "phone";
        r0 = com.loc.ct.a(r0, r1);
        r0 = (android.telephony.TelephonyManager) r0;
        r6.c = r0;
    L_0x004e:
        r0 = r6.c;
        if (r0 == 0) goto L_0x0087;
    L_0x0052:
        r0 = r6.c;	 Catch:{ SecurityException -> 0x008f, Throwable -> 0x0097 }
        r0 = r0.getCellLocation();	 Catch:{ SecurityException -> 0x008f, Throwable -> 0x0097 }
        r1 = r6.l;	 Catch:{ SecurityException -> 0x008f, Throwable -> 0x0097 }
        r0 = r6.c(r0);	 Catch:{ SecurityException -> 0x008f, Throwable -> 0x0097 }
        r6.a = r0;	 Catch:{ SecurityException -> 0x008f, Throwable -> 0x0097 }
    L_0x0060:
        r0 = r6.r();	 Catch:{ Throwable -> 0x00af }
        r6.r = r0;	 Catch:{ Throwable -> 0x00af }
        r0 = r6.r;	 Catch:{ Throwable -> 0x00af }
        switch(r0) {
            case 1: goto L_0x00a4;
            case 2: goto L_0x00b1;
            default: goto L_0x006b;
        };	 Catch:{ Throwable -> 0x00af }
    L_0x006b:
        r0 = r6.l;	 Catch:{ Throwable -> 0x00af }
        r1 = "phone2";
        r0 = com.loc.ct.a(r0, r1);	 Catch:{ Throwable -> 0x00af }
        r6.q = r0;	 Catch:{ Throwable -> 0x00af }
    L_0x0075:
        r0 = r6.k;
        if (r0 != 0) goto L_0x0087;
    L_0x0079:
        r0 = new com.loc.bw$a;
        r1 = "listenerPhoneStateThread";
        r0.<init>(r6, r1);
        r6.k = r0;
        r0 = r6.k;
        r0.start();
    L_0x0087:
        r0 = new com.loc.bu;
        r0.<init>();
        r6.p = r0;
        return;
    L_0x008f:
        r0 = move-exception;
        r0 = r0.getMessage();
        r6.h = r0;
        goto L_0x0060;
    L_0x0097:
        r0 = move-exception;
        r6.h = r2;
        r1 = "CgiManager";
        r2 = "CgiManager";
        com.loc.cl.a(r0, r1, r2);
        r6.a = r3;
        goto L_0x0060;
    L_0x00a4:
        r0 = r6.l;	 Catch:{ Throwable -> 0x00af }
        r1 = "phone_msim";
        r0 = com.loc.ct.a(r0, r1);	 Catch:{ Throwable -> 0x00af }
        r6.q = r0;	 Catch:{ Throwable -> 0x00af }
        goto L_0x0075;
    L_0x00af:
        r0 = move-exception;
        goto L_0x0075;
    L_0x00b1:
        r0 = r6.l;	 Catch:{ Throwable -> 0x00af }
        r1 = "phone2";
        r0 = com.loc.ct.a(r0, r1);	 Catch:{ Throwable -> 0x00af }
        r6.q = r0;	 Catch:{ Throwable -> 0x00af }
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bw.<init>(android.content.Context):void");
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object a = co.a(obj, str, objArr);
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
        CellLocation cdmaCellLocation;
        CellLocation cellLocation;
        CellLocation cellLocation2 = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        bv bvVar;
        for (int i = 0; i < list.size(); i++) {
            CellInfo cellInfo = (CellInfo) list.get(i);
            if (cellInfo != null) {
                try {
                    boolean isRegistered = cellInfo.isRegistered();
                    if (!(cellInfo instanceof CellInfoCdma)) {
                        if (!(cellInfo instanceof CellInfoGsm)) {
                            if (!(cellInfo instanceof CellInfoWcdma)) {
                                bv a;
                                if (cellInfo instanceof CellInfoLte) {
                                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                                    if (a(cellInfoLte.getCellIdentity())) {
                                        a = a(cellInfoLte, isRegistered);
                                    }
                                } else {
                                    a = null;
                                }
                                bvVar = a;
                                break;
                            }
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                            if (a(cellInfoWcdma.getCellIdentity())) {
                                bvVar = a(cellInfoWcdma, isRegistered);
                                break;
                            }
                        } else {
                            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                            if (a(cellInfoGsm.getCellIdentity())) {
                                bvVar = a(cellInfoGsm, isRegistered);
                                break;
                            }
                        }
                    } else {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                        if (a(cellInfoCdma.getCellIdentity())) {
                            bvVar = a(cellInfoCdma, isRegistered);
                            break;
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
        bvVar = null;
        if (bvVar != null) {
            try {
                if (bvVar.k == 2) {
                    cdmaCellLocation = new CdmaCellLocation();
                    try {
                        cdmaCellLocation.setCellLocationData(bvVar.i, bvVar.e, bvVar.f, bvVar.g, bvVar.h);
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
                        cdmaCellLocation.setLacAndCid(bvVar.c, bvVar.d);
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

    private static bv a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        bv bvVar = new bv(i, z);
        bvVar.a = i2;
        bvVar.b = i3;
        bvVar.c = i4;
        bvVar.d = i5;
        bvVar.j = i6;
        return bvVar;
    }

    @SuppressLint({"NewApi"})
    private bv a(CellInfoCdma cellInfoCdma, boolean z) {
        int parseInt;
        int parseInt2;
        bv a;
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        String[] a2 = ct.a(this.c);
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
    private static bv a(CellInfoGsm cellInfoGsm, boolean z) {
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        return a(1, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
    }

    @SuppressLint({"NewApi"})
    private static bv a(CellInfoLte cellInfoLte, boolean z) {
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        return a(3, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getTac(), cellIdentity.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
    }

    @SuppressLint({"NewApi"})
    private static bv a(CellInfoWcdma cellInfoWcdma, boolean z) {
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        return a(4, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
    }

    private static bv a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            bv bvVar = new bv(1, false);
            bvVar.a = Integer.parseInt(strArr[0]);
            bvVar.b = Integer.parseInt(strArr[1]);
            bvVar.c = co.b(neighboringCellInfo, "getLac", new Object[0]);
            bvVar.d = neighboringCellInfo.getCid();
            bvVar.j = ct.a(neighboringCellInfo.getRssi());
            return bvVar;
        } catch (Throwable th) {
            cl.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private void a(CellLocation cellLocation, String[] strArr, boolean z) {
        if (cellLocation != null && this.c != null) {
            this.b.clear();
            if (b(cellLocation)) {
                this.a = 1;
                ArrayList arrayList = this.b;
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                bv bvVar = new bv(1, true);
                bvVar.a = ct.h(strArr[0]);
                bvVar.b = ct.h(strArr[1]);
                bvVar.c = gsmCellLocation.getLac();
                bvVar.d = gsmCellLocation.getCid();
                bvVar.j = this.o;
                arrayList.add(bvVar);
                if (!z) {
                    List<NeighboringCellInfo> neighboringCellInfo = this.c.getNeighboringCellInfo();
                    if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                        for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                            if (neighboringCellInfo2 != null && a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid())) {
                                bv a = a(neighboringCellInfo2, strArr);
                                if (!(a == null || this.b.contains(a))) {
                                    this.b.add(a);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static /* synthetic */ void a(bw bwVar, int i) {
        if (i == -113) {
            bwVar.o = -113;
            return;
        }
        bwVar.o = i;
        switch (bwVar.a) {
            case 1:
            case 2:
                if (bwVar.b != null && !bwVar.b.isEmpty()) {
                    try {
                        ((bv) bwVar.b.get(0)).j = bwVar.o;
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

    private static boolean a(int i) {
        return (i == -1 || i == 0 || i > SupportMenu.USER_MASK) ? false : true;
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
        return cellIdentityGsm != null && a(cellIdentityGsm.getLac()) && b(cellIdentityGsm.getCid());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityLte cellIdentityLte) {
        return cellIdentityLte != null && a(cellIdentityLte.getTac()) && b(cellIdentityLte.getCi());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityWcdma cellIdentityWcdma) {
        return cellIdentityWcdma != null && a(cellIdentityWcdma.getLac()) && b(cellIdentityWcdma.getCid());
    }

    private static boolean b(int i) {
        return (i == -1 || i == 0 || i == SupportMenu.USER_MASK || i >= 268435455) ? false : true;
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
            cl.a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    private CellLocation n() {
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
                cl.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private CellLocation o() {
        CellLocation cellLocation = null;
        Object obj = this.c;
        if (obj == null) {
            return cellLocation;
        }
        CellLocation n = n();
        if (b(n)) {
            return n;
        }
        if (ct.c() >= 18) {
            try {
                cellLocation = a(obj.getAllCellInfo());
            } catch (SecurityException e) {
                this.h = e.getMessage();
            }
        }
        if (cellLocation != null) {
            return cellLocation;
        }
        cellLocation = a(obj, "getCellLocationExt", Integer.valueOf(1));
        if (cellLocation != null) {
            return cellLocation;
        }
        cellLocation = a(obj, "getCellLocationGemini", Integer.valueOf(1));
        return cellLocation != null ? cellLocation : cellLocation;
    }

    private CellLocation p() {
        CellLocation cellLocation = null;
        Object obj = this.q;
        if (obj != null) {
            try {
                Class q = q();
                if (q.isInstance(obj)) {
                    obj = q.cast(obj);
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
                cl.a(th, "CgiManager", "getSim2Cgi");
            }
        }
        return cellLocation;
    }

    private Class<?> q() {
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
            cl.a(th, "CgiManager", "getSim2TmClass");
        }
        return cls;
    }

    private int r() {
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

    public final ArrayList<bv> a() {
        return this.b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r11, boolean r12) {
        /*
        r10 = this;
        r3 = 0;
        r1 = 1;
        r2 = 0;
        r0 = r10.l;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = com.loc.ct.a(r0);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r10.i = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.i;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x00bb;
    L_0x000f:
        r0 = r2;
    L_0x0010:
        if (r0 != 0) goto L_0x001a;
    L_0x0012:
        r0 = r10.b;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r0.isEmpty();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x0211;
    L_0x001a:
        r0 = r10.i;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 != 0) goto L_0x003e;
    L_0x001e:
        r0 = r10.c;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x003e;
    L_0x0022:
        r0 = r10.o();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r4 = r10.b(r0);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r4 != 0) goto L_0x0030;
    L_0x002c:
        r0 = r10.p();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
    L_0x0030:
        r4 = r10.b(r0);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r4 == 0) goto L_0x00cb;
    L_0x0036:
        r10.e = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r4 = com.loc.ct.b();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r10.s = r4;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
    L_0x003e:
        r0 = r10.f;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 != 0) goto L_0x0058;
    L_0x0042:
        r0 = r10.e;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 != 0) goto L_0x0058;
    L_0x0046:
        if (r12 == 0) goto L_0x0058;
    L_0x0048:
        r0 = r2;
    L_0x0049:
        r4 = 10;
        java.lang.Thread.sleep(r4);	 Catch:{ InterruptedException -> 0x00f0 }
    L_0x004e:
        r0 = r0 + 1;
        r4 = r10.e;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r4 != 0) goto L_0x0058;
    L_0x0054:
        r4 = 50;
        if (r0 < r4) goto L_0x0049;
    L_0x0058:
        r0 = 1;
        r10.f = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.e;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.b(r0);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x0074;
    L_0x0063:
        r0 = r10.c;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r4 = com.loc.ct.a(r0);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.e;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r5 = r10.l;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.c(r0);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        switch(r0) {
            case 1: goto L_0x00ff;
            case 2: goto L_0x0106;
            default: goto L_0x0074;
        };
    L_0x0074:
        r0 = com.loc.ct.c();	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        r1 = 18;
        if (r0 < r1) goto L_0x01f1;
    L_0x007c:
        r0 = r10.c;	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        if (r0 == 0) goto L_0x01f1;
    L_0x0080:
        r4 = r10.n;	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        r5 = r10.p;	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        r0 = r10.c;	 Catch:{ SecurityException -> 0x01e6, Throwable -> 0x01f0 }
        r1 = r0.getAllCellInfo();	 Catch:{ SecurityException -> 0x01e6, Throwable -> 0x01f0 }
        r0 = 0;
        r10.h = r0;	 Catch:{ SecurityException -> 0x02e3, Throwable -> 0x01f0 }
    L_0x008d:
        if (r1 == 0) goto L_0x02ad;
    L_0x008f:
        r3 = r1.size();	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        if (r3 == 0) goto L_0x02ad;
    L_0x0095:
        if (r4 == 0) goto L_0x009a;
    L_0x0097:
        r4.clear();	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
    L_0x009a:
        if (r2 >= r3) goto L_0x02ad;
    L_0x009c:
        r0 = r1.get(r2);	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        r0 = (android.telephony.CellInfo) r0;	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        if (r0 == 0) goto L_0x00b8;
    L_0x00a4:
        r6 = r0.isRegistered();	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = r0 instanceof android.telephony.CellInfoCdma;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 == 0) goto L_0x0235;
    L_0x00ac:
        r0 = (android.telephony.CellInfoCdma) r0;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = r0.getCellIdentity();	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = a(r7);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 != 0) goto L_0x021a;
    L_0x00b8:
        r2 = r2 + 1;
        goto L_0x009a;
    L_0x00bb:
        r4 = com.loc.ct.b();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r6 = r10.d;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r4 = r4 - r6;
        r6 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x02e6;
    L_0x00c8:
        r0 = r2;
        goto L_0x0010;
    L_0x00cb:
        r4 = com.loc.ct.b();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r6 = r10.s;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r4 = r4 - r6;
        r6 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x003e;
    L_0x00d9:
        r0 = 0;
        r10.e = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.b;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0.clear();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.n;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0.clear();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        goto L_0x003e;
    L_0x00e8:
        r0 = move-exception;
        r0 = r0.getMessage();
        r10.h = r0;
    L_0x00ef:
        return;
    L_0x00f0:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        goto L_0x004e;
    L_0x00f6:
        r0 = move-exception;
        r1 = "CgiManager";
        r2 = "refresh";
        com.loc.cl.a(r0, r1, r2);
        goto L_0x00ef;
    L_0x00ff:
        r0 = r10.e;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r10.a(r0, r4, r11);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        goto L_0x0074;
    L_0x0106:
        r5 = r10.e;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r5 == 0) goto L_0x0074;
    L_0x010a:
        r0 = r10.b;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0.clear();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = com.loc.ct.c();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r6 = 5;
        if (r0 < r6) goto L_0x0074;
    L_0x0116:
        r0 = r10.q;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        if (r0 == 0) goto L_0x0142;
    L_0x011a:
        r0 = r5.getClass();	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
        r6 = "mGsmCellLoc";
        r0 = r0.getDeclaredField(r6);	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
        r6 = r0.isAccessible();	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
        if (r6 != 0) goto L_0x012e;
    L_0x012a:
        r6 = 1;
        r0.setAccessible(r6);	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
    L_0x012e:
        r0 = r0.get(r5);	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
        r0 = (android.telephony.gsm.GsmCellLocation) r0;	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
        if (r0 == 0) goto L_0x01e1;
    L_0x0136:
        r6 = r10.b(r0);	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
        if (r6 == 0) goto L_0x01e1;
    L_0x013c:
        r10.a(r0, r4, r11);	 Catch:{ Throwable -> 0x01e0, SecurityException -> 0x00e8 }
        r0 = r1;
    L_0x0140:
        if (r0 != 0) goto L_0x0074;
    L_0x0142:
        r0 = r10.b(r5);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        if (r0 == 0) goto L_0x0074;
    L_0x0148:
        r0 = 2;
        r10.a = r0;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0 = new com.loc.bv;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r6 = 2;
        r7 = 1;
        r0.<init>(r6, r7);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r6 = 0;
        r6 = r4[r6];	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r6 = java.lang.Integer.parseInt(r6);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.a = r6;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r6 = 1;
        r4 = r4[r6];	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.b = r4;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = "getSystemId";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = com.loc.co.b(r5, r4, r6);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.g = r4;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = "getNetworkId";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = com.loc.co.b(r5, r4, r6);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.h = r4;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = "getBaseStationId";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = com.loc.co.b(r5, r4, r6);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.i = r4;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = r10.o;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.j = r4;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = "getBaseStationLatitude";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = com.loc.co.b(r5, r4, r6);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.e = r4;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = "getBaseStationLongitude";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = com.loc.co.b(r5, r4, r6);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r0.f = r4;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r4 = r0.e;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r5 = r0.f;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        if (r4 != r5) goto L_0x01e4;
    L_0x01a5:
        r4 = r0.e;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        if (r4 <= 0) goto L_0x01e4;
    L_0x01a9:
        r4 = r0.e;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        if (r4 < 0) goto L_0x01c1;
    L_0x01ad:
        r4 = r0.f;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        if (r4 < 0) goto L_0x01c1;
    L_0x01b1:
        r4 = r0.e;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r4 == r5) goto L_0x01c1;
    L_0x01b8:
        r4 = r0.f;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r4 == r5) goto L_0x01c1;
    L_0x01bf:
        if (r1 == 0) goto L_0x01c7;
    L_0x01c1:
        r1 = 0;
        r0.e = r1;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r1 = 0;
        r0.f = r1;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
    L_0x01c7:
        r1 = r10.b;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r1 = r1.contains(r0);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        if (r1 != 0) goto L_0x0074;
    L_0x01cf:
        r1 = r10.b;	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        r1.add(r0);	 Catch:{ Throwable -> 0x01d6, SecurityException -> 0x00e8 }
        goto L_0x0074;
    L_0x01d6:
        r0 = move-exception;
        r1 = "CgiManager";
        r4 = "hdlCdmaLocChange";
        com.loc.cl.a(r0, r1, r4);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        goto L_0x0074;
    L_0x01e0:
        r0 = move-exception;
    L_0x01e1:
        r0 = r2;
        goto L_0x0140;
    L_0x01e4:
        r1 = r2;
        goto L_0x01a9;
    L_0x01e6:
        r0 = move-exception;
        r1 = r3;
    L_0x01e8:
        r0 = r0.getMessage();	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        r10.h = r0;	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        goto L_0x008d;
    L_0x01f0:
        r0 = move-exception;
    L_0x01f1:
        r0 = r10.c;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x020b;
    L_0x01f5:
        r0 = r10.c;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r0.getNetworkOperator();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r10.m = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r10.m;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 != 0) goto L_0x020b;
    L_0x0205:
        r0 = r10.a;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r0 | 8;
        r10.a = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
    L_0x020b:
        r0 = com.loc.ct.b();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r10.d = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
    L_0x0211:
        r0 = r10.i;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x02c0;
    L_0x0215:
        r10.i();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        goto L_0x00ef;
    L_0x021a:
        r0 = r10.a(r0, r6);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r8 = r5.a(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = java.lang.Math.min(r6, r8);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (int) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (short) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r0.l = r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r4.add(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        goto L_0x00b8;
    L_0x0232:
        r0 = move-exception;
        goto L_0x00b8;
    L_0x0235:
        r7 = r0 instanceof android.telephony.CellInfoGsm;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 == 0) goto L_0x025d;
    L_0x0239:
        r0 = (android.telephony.CellInfoGsm) r0;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = r0.getCellIdentity();	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = a(r7);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 == 0) goto L_0x00b8;
    L_0x0245:
        r0 = a(r0, r6);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r8 = r5.a(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = java.lang.Math.min(r6, r8);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (int) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (short) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r0.l = r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r4.add(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        goto L_0x00b8;
    L_0x025d:
        r7 = r0 instanceof android.telephony.CellInfoWcdma;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 == 0) goto L_0x0285;
    L_0x0261:
        r0 = (android.telephony.CellInfoWcdma) r0;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = r0.getCellIdentity();	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = a(r7);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 == 0) goto L_0x00b8;
    L_0x026d:
        r0 = a(r0, r6);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r8 = r5.a(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = java.lang.Math.min(r6, r8);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (int) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (short) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r0.l = r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r4.add(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        goto L_0x00b8;
    L_0x0285:
        r7 = r0 instanceof android.telephony.CellInfoLte;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 == 0) goto L_0x00b8;
    L_0x0289:
        r0 = (android.telephony.CellInfoLte) r0;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = r0.getCellIdentity();	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r7 = a(r7);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        if (r7 == 0) goto L_0x00b8;
    L_0x0295:
        r0 = a(r0, r6);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r8 = r5.a(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = java.lang.Math.min(r6, r8);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (int) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r6 = (short) r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r0.l = r6;	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        r4.add(r0);	 Catch:{ Throwable -> 0x0232, SecurityException -> 0x00e8 }
        goto L_0x00b8;
    L_0x02ad:
        if (r4 == 0) goto L_0x01f1;
    L_0x02af:
        r0 = r4.size();	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        if (r0 <= 0) goto L_0x01f1;
    L_0x02b5:
        r0 = r10.a;	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        r0 = r0 | 4;
        r10.a = r0;	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        r5.a(r4);	 Catch:{ Throwable -> 0x01f0, SecurityException -> 0x00e8 }
        goto L_0x01f1;
    L_0x02c0:
        r0 = r10.a;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r0 & 3;
        switch(r0) {
            case 1: goto L_0x02c9;
            case 2: goto L_0x02d6;
            default: goto L_0x02c7;
        };	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
    L_0x02c7:
        goto L_0x00ef;
    L_0x02c9:
        r0 = r10.b;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r0.isEmpty();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x00ef;
    L_0x02d1:
        r0 = 0;
        r10.a = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        goto L_0x00ef;
    L_0x02d6:
        r0 = r10.b;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        r0 = r0.isEmpty();	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        if (r0 == 0) goto L_0x00ef;
    L_0x02de:
        r0 = 0;
        r10.a = r0;	 Catch:{ SecurityException -> 0x00e8, Throwable -> 0x00f6 }
        goto L_0x00ef;
    L_0x02e3:
        r0 = move-exception;
        goto L_0x01e8;
    L_0x02e6:
        r0 = r1;
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bw.a(boolean, boolean):void");
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
                    cl.a(th, "CgiManager", "cgiUseful Cgi.I_GSM_T");
                    break;
                }
            case 2:
                try {
                    if (co.b(cellLocation, "getSystemId", new Object[0]) <= 0 || co.b(cellLocation, "getNetworkId", new Object[0]) < 0 || co.b(cellLocation, "getBaseStationId", new Object[0]) < 0) {
                        z = false;
                        break;
                    }
                } catch (Throwable th2) {
                    cl.a(th2, "CgiManager", "cgiUseful Cgi.I_CDMA_T");
                    break;
                }
        }
        return z;
    }

    public final ArrayList<bv> b() {
        return this.n;
    }

    public final bv c() {
        if (this.i) {
            return null;
        }
        ArrayList arrayList = this.b;
        return arrayList.size() > 0 ? (bv) arrayList.get(0) : null;
    }

    public final bv d() {
        if (this.i) {
            return null;
        }
        ArrayList arrayList = this.n;
        return arrayList.size() > 0 ? (bv) arrayList.get(0) : null;
    }

    public final int e() {
        return this.a;
    }

    public final int f() {
        return this.a & 3;
    }

    public final TelephonyManager g() {
        return this.c;
    }

    public final void h() {
        this.p.a();
        this.s = 0;
        synchronized (this.u) {
            this.t = true;
        }
        if (!(this.c == null || this.g == null)) {
            try {
                this.c.listen(this.g, 0);
            } catch (Throwable th) {
                cl.a(th, "CgiManager", "destroy");
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

    final void i() {
        this.h = null;
        this.e = null;
        this.a = 0;
        this.b.clear();
        this.n.clear();
    }

    public final String j() {
        return this.h;
    }

    public final String k() {
        return this.m;
    }

    public final String l() {
        if (this.i) {
            i();
        }
        if (this.j == null) {
            this.j = new StringBuilder();
        } else {
            this.j.delete(0, this.j.length());
        }
        switch (this.a & 3) {
            case 1:
                for (int i = 1; i < this.b.size(); i++) {
                    this.j.append("#").append(((bv) this.b.get(i)).b);
                    this.j.append("|").append(((bv) this.b.get(i)).c);
                    this.j.append("|").append(((bv) this.b.get(i)).d);
                }
                break;
        }
        if (this.j.length() > 0) {
            this.j.deleteCharAt(0);
        }
        return this.j.toString();
    }

    public final boolean m() {
        try {
            if (!(this.c == null || (TextUtils.isEmpty(this.c.getSimOperator()) && TextUtils.isEmpty(this.c.getSimCountryIso())))) {
                return true;
            }
        } catch (Throwable th) {
        }
        try {
            int a = ct.a(ct.c(this.l));
            if (a == 0 || a == 4 || a == 2 || a == 5 || a == 3) {
                return true;
            }
        } catch (Throwable th2) {
        }
        return false;
    }
}
