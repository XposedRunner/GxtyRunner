package cn.jiguang.analytics.android.api;

import cn.jiguang.analytics.android.c.a.b;

public class PurchaseEvent extends Event {
    private static final String TAG;
    private static final long serialVersionUID = -6892274936581585775L;
    private static final String[] z;
    private boolean isSetPurchasePrice = false;
    private boolean isSetPurchaseSuccess = false;
    @EVENTFIELD("purchase_currency")
    private Currency purchaseCurrency;
    @EVENTFIELD("purchase_quantity")
    private int purchaseGoodsCount;
    @EVENTFIELD("purchase_goods_id")
    private String purchaseGoodsid;
    @EVENTFIELD("purchase_goods_name")
    private String purchaseGoodsname;
    @EVENTFIELD("purchase_goods_type")
    private String purchaseGoodstype;
    @EVENTFIELD("purchase_price")
    private double purchasePrice;
    @EVENTFIELD("purchase_success")
    private boolean purchaseSuccess;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 30;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "0<J`t\u0001:]Fj\u0005'L";
        r0 = 29;
        r4 = r3;
    L_0x000a:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0035;
    L_0x0013:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0018:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0170;
            case 1: goto L_0x0174;
            case 2: goto L_0x0178;
            case 3: goto L_0x017c;
            default: goto L_0x001f;
        };
    L_0x001f:
        r9 = 28;
    L_0x0021:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0033;
    L_0x0029:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0018;
    L_0x002d:
        TAG = r1;
        r1 = "\u0003<Kws\r\u0016Hvn\u0003!Ypy";
        r0 = -1;
        goto L_0x000a;
    L_0x0033:
        r5 = r1;
        r1 = r7;
    L_0x0035:
        if (r5 > r6) goto L_0x0013;
    L_0x0037:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x004b;
            case 1: goto L_0x0053;
            case 2: goto L_0x005b;
            case 3: goto L_0x0063;
            case 4: goto L_0x006b;
            case 5: goto L_0x0073;
            case 6: goto L_0x007b;
            case 7: goto L_0x0084;
            case 8: goto L_0x008f;
            case 9: goto L_0x009a;
            case 10: goto L_0x00a5;
            case 11: goto L_0x00b0;
            case 12: goto L_0x00bb;
            case 13: goto L_0x00c6;
            case 14: goto L_0x00d1;
            case 15: goto L_0x00dc;
            case 16: goto L_0x00e7;
            case 17: goto L_0x00f2;
            case 18: goto L_0x00fd;
            case 19: goto L_0x0108;
            case 20: goto L_0x0113;
            case 21: goto L_0x011e;
            case 22: goto L_0x0129;
            case 23: goto L_0x0134;
            case 24: goto L_0x013f;
            case 25: goto L_0x014a;
            case 26: goto L_0x0155;
            case 27: goto L_0x0160;
            case 28: goto L_0x016b;
            case 29: goto L_0x002d;
            default: goto L_0x0043;
        };
    L_0x0043:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\t'Nbp\t-]#L\u0015;[k}\u0013,}uy\u000e=\u0018.<\u0010<J`t\u0001:]Pi\u0003*]po@$Mph@:]w<\u0016(Tvy";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x004b:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\t'Nbp\t-]#L\u0015;[k}\u0013,}uy\u000e=\u0018.<\u0010<J`t\u0001:]@i\u0012;]m\u0019iObo@,Ush\u0019";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x0053:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0010<J`t\u0001:]\\m\u0015(Vwu\u00140";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x005b:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0010<J`t\u0001:]\\o\u0015*[fo\u0013";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x0063:
        r3[r2] = r1;
        r2 = 5;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016Hqu\u0003,";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x006b:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0010<J`t\u0001:]\\{\u000f&\\pC\t-";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x0073:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0010<J`t\u0001:]\\l\u0012 [f";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x007b:
        r3[r2] = r1;
        r2 = 8;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016_ls\u0004:gwe\u0010,";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x0084:
        r3[r2] = r1;
        r2 = 9;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016_ls\u0004:gm}\r,";
        r0 = 8;
        r3 = r4;
        goto L_0x000a;
    L_0x008f:
        r3[r2] = r1;
        r2 = 10;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016[vn\u0012,V`e";
        r0 = 9;
        r3 = r4;
        goto L_0x000a;
    L_0x009a:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0010<J`t\u0001:]Ds\u000f-Kwe\u0010,";
        r0 = 10;
        r3 = r4;
        goto L_0x000a;
    L_0x00a5:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0010<J`t\u0001:]Ds\u000f-Km}\r,";
        r0 = 11;
        r3 = r4;
        goto L_0x000a;
    L_0x00b0:
        r3[r2] = r1;
        r2 = 13;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016_ls\u0004:gjx";
        r0 = 12;
        r3 = r4;
        goto L_0x000a;
    L_0x00bb:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\t'Nbp\t-]#L\u0015;[k}\u0013,}uy\u000e=\u0018.<\u0010<J`t\u0001:]Sn\t*]#q\u0015:L#o\u0005=\u0018u}\f<]";
        r0 = 13;
        r3 = r4;
        goto L_0x000a;
    L_0x00c6:
        r3[r2] = r1;
        r2 = 15;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016Ljq\u0005";
        r0 = 14;
        r3 = r4;
        goto L_0x000a;
    L_0x00d1:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0010<J`t\u0001:]\\\u0015;Jfr\u00030";
        r0 = 15;
        r3 = r4;
        goto L_0x000a;
    L_0x00dc:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0010<J`t\u0001:]\\{\u000f&\\pC\u00140Hf";
        r0 = 16;
        r3 = r4;
        goto L_0x000a;
    L_0x00e7:
        r3[r2] = r1;
        r2 = 18;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016Kv\u0003,Kp";
        r0 = 17;
        r3 = r4;
        goto L_0x000a;
    L_0x00f2:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0010<J`t\u0001:]Ds\u000f-Kjx";
        r0 = 18;
        r3 = r4;
        goto L_0x000a;
    L_0x00fd:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\u0010<J`t\u0001:]\\{\u000f&\\pC\u000e(Uf";
        r0 = 19;
        r3 = r4;
        goto L_0x000a;
    L_0x0108:
        r3[r2] = r1;
        r2 = 21;
        r1 = "4!]qy@>Yp<\u0001i[lr\u0006%Q`h@+]wk\u0005,V#\u0015:Llq@\"]z3\u0016(Tvy@(Vg<\r&\\fp@\"]z3\u0016(TvyLiLky@*Wmz\f [wy\u0004i[vo\u0014&U#w\u00050\u0017u}\f<]#k\t%T#~\u0005iYa}\u000e-Wmy\u0004e\u0018br\u0004iLky@,Nfr\u0014iOjp\fiZf<\u0012,[ln\u0004,\\si\u0012*Pbo\u0005\u0016Iv}\u000e=Qwe";
        r0 = 20;
        r3 = r4;
        goto L_0x000a;
    L_0x0113:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0010<J`t\u0001:]\\h\t$]";
        r0 = 21;
        r3 = r4;
        goto L_0x000a;
    L_0x011e:
        r3[r2] = r1;
        r2 = 23;
        r1 = "LiHvn\u0003!Ypy'&Wgo\u000e(Uf!G";
        r0 = 22;
        r3 = r4;
        goto L_0x000a;
    L_0x0129:
        r3[r2] = r1;
        r2 = 24;
        r1 = "LiHvn\u0003!Ypy#<Jqy\u000e*A>";
        r0 = 23;
        r3 = r4;
        goto L_0x000a;
    L_0x0134:
        r3[r2] = r1;
        r2 = 25;
        r1 = "LiHvn\u0003!Ypy0;Q`y]";
        r0 = 24;
        r3 = r4;
        goto L_0x000a;
    L_0x013f:
        r3[r2] = r1;
        r2 = 26;
        r1 = "LiHvn\u0003!Ypy'&Wgo\u00140Hf!G";
        r0 = 25;
        r3 = r4;
        goto L_0x000a;
    L_0x014a:
        r3[r2] = r1;
        r2 = 27;
        r1 = "LiHvn\u0003!Ypy'&Wgo\t-\u0005$";
        r0 = 26;
        r3 = r4;
        goto L_0x000a;
    L_0x0155:
        r3[r2] = r1;
        r2 = 28;
        r1 = "LiHvn\u0003!Ypy3<[`y\u0013:\u0005";
        r0 = 27;
        r3 = r4;
        goto L_0x000a;
    L_0x0160:
        r3[r2] = r1;
        r2 = 29;
        r1 = "0<J`t\u0001:]Fj\u0005'Lxl\u0015;[k}\u0013,ls\u0004:{li\u000e=\u0005";
        r0 = 28;
        r3 = r4;
        goto L_0x000a;
    L_0x016b:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x0170:
        r9 = 96;
        goto L_0x0021;
    L_0x0174:
        r9 = 73;
        goto L_0x0021;
    L_0x0178:
        r9 = 56;
        goto L_0x0021;
    L_0x017c:
        r9 = 3;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.PurchaseEvent.<clinit>():void");
    }

    public PurchaseEvent() {
        super(z[0]);
    }

    public PurchaseEvent(String str, String str2, double d, boolean z, Currency currency, String str3, int i) {
        super(z[0]);
        this.purchaseGoodsid = str;
        this.purchaseGoodsname = str2;
        this.purchasePrice = d;
        this.purchaseSuccess = z;
        this.purchaseCurrency = currency;
        this.purchaseGoodstype = str3;
        this.purchaseGoodsCount = i;
        this.isSetPurchasePrice = true;
        this.isSetPurchaseSuccess = true;
    }

    public boolean checkEvent() {
        if (!super.checkEvent()) {
            return false;
        }
        if (this.extMap != null) {
            if (this.extMap.containsKey(z[16])) {
                printLog(TAG, z[10]);
                this.extMap.remove(z[16]);
            }
            if (this.extMap.containsKey(z[6])) {
                printLog(TAG, z[13]);
                this.extMap.remove(z[6]);
            }
            if (this.extMap.containsKey(z[20])) {
                printLog(TAG, z[9]);
                this.extMap.remove(z[20]);
            }
            if (this.extMap.containsKey(z[17])) {
                printLog(TAG, z[8]);
                this.extMap.remove(z[17]);
            }
            if (this.extMap.containsKey(z[4])) {
                printLog(TAG, z[18]);
                this.extMap.remove(z[4]);
            }
            if (this.extMap.containsKey(z[7])) {
                printLog(TAG, z[5]);
                this.extMap.remove(z[7]);
            }
            if (this.extMap.containsKey(z[3])) {
                printLog(TAG, z[21]);
                this.extMap.remove(z[3]);
            }
            if (this.extMap.containsKey(z[22])) {
                printLog(TAG, z[15]);
                this.extMap.remove(z[22]);
            }
        }
        if (!isValideValue(this.purchaseGoodsid, z[19], false) || !isValideValue(this.purchaseGoodsname, z[12], false) || !isValideValue(this.purchaseGoodstype, z[11], false)) {
            return false;
        }
        if (!this.isSetPurchasePrice) {
            b.g(TAG, z[14]);
            return false;
        } else if (this.purchaseCurrency == null) {
            b.g(TAG, z[2]);
            return false;
        } else if (this.isSetPurchaseSuccess) {
            return true;
        } else {
            b.g(TAG, z[1]);
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        PurchaseEvent purchaseEvent = (PurchaseEvent) obj;
        return (this.purchaseGoodsid == null ? purchaseEvent.purchaseGoodsid != null : !this.purchaseGoodsid.equals(purchaseEvent.purchaseGoodsid)) ? false : (this.purchaseGoodsname == null ? purchaseEvent.purchaseGoodsname != null : !this.purchaseGoodsname.equals(purchaseEvent.purchaseGoodsname)) ? false : this.purchasePrice != purchaseEvent.purchasePrice ? false : this.purchaseSuccess != purchaseEvent.purchaseSuccess ? false : this.purchaseGoodsCount != purchaseEvent.purchaseGoodsCount ? false : (this.purchaseCurrency == null ? purchaseEvent.purchaseCurrency != null : !this.purchaseCurrency.equals(purchaseEvent.purchaseCurrency)) ? false : this.purchaseGoodstype != null ? this.purchaseGoodstype.equals(purchaseEvent.purchaseGoodstype) : purchaseEvent.purchaseGoodstype == null;
    }

    public Currency getPurchaseCurrency() {
        return this.purchaseCurrency;
    }

    public int getPurchaseGoodsCount() {
        return this.purchaseGoodsCount;
    }

    public String getPurchaseGoodsid() {
        return this.purchaseGoodsid;
    }

    public String getPurchaseGoodsname() {
        return this.purchaseGoodsname;
    }

    public String getPurchaseGoodstype() {
        return this.purchaseGoodstype;
    }

    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    public boolean getPurchaseSuccess() {
        return this.purchaseSuccess;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.purchaseCurrency != null ? this.purchaseCurrency.hashCode() : 0) + (((this.purchaseSuccess ? 1 : 0) + (((((((this.purchaseGoodsname != null ? this.purchaseGoodsname.hashCode() : 0) + (((this.purchaseGoodsid != null ? this.purchaseGoodsid.hashCode() : 0) + (super.hashCode() * 31)) * 31)) * 31) + ((int) this.purchasePrice)) * 31) + this.purchaseGoodsCount) * 31)) * 31)) * 31;
        if (this.purchaseGoodstype != null) {
            i = this.purchaseGoodstype.hashCode();
        }
        return hashCode + i;
    }

    public PurchaseEvent setPurchaseCurrency(Currency currency) {
        this.purchaseCurrency = currency;
        return this;
    }

    public PurchaseEvent setPurchaseGoodsCount(int i) {
        this.purchaseGoodsCount = i;
        return this;
    }

    public PurchaseEvent setPurchaseGoodsid(String str) {
        this.purchaseGoodsid = str;
        return this;
    }

    public PurchaseEvent setPurchaseGoodsname(String str) {
        this.purchaseGoodsname = str;
        return this;
    }

    public PurchaseEvent setPurchaseGoodstype(String str) {
        this.purchaseGoodstype = str;
        return this;
    }

    public PurchaseEvent setPurchasePrice(double d) {
        this.purchasePrice = d;
        this.isSetPurchasePrice = true;
        return this;
    }

    public PurchaseEvent setPurchaseSuccess(boolean z) {
        this.purchaseSuccess = z;
        this.isSetPurchaseSuccess = true;
        return this;
    }

    public String toString() {
        return new StringBuilder(z[29]).append(this.purchaseGoodsCount).append(z[26]).append(this.purchaseGoodstype).append('\'').append(z[24]).append(this.purchaseCurrency).append(z[28]).append(this.purchaseSuccess).append(z[25]).append(this.purchasePrice).append(z[23]).append(this.purchaseGoodsname).append('\'').append(z[27]).append(this.purchaseGoodsid).append('\'').append(super.toString()).append('}').toString();
    }
}
