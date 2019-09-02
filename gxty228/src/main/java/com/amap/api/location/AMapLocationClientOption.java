package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.loc.cl;

public class AMapLocationClientOption implements Parcelable, Cloneable {
    public static final Creator<AMapLocationClientOption> CREATOR = new Creator<AMapLocationClientOption>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new AMapLocationClientOption(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AMapLocationClientOption[i];
        }
    };
    static String a = "";
    private static AMapLocationProtocol j = AMapLocationProtocol.HTTP;
    private static boolean t = true;
    private long b = 2000;
    private long c = ((long) cl.f);
    private boolean d = false;
    private boolean e = true;
    private boolean f = true;
    private boolean g = true;
    private boolean h = true;
    private AMapLocationMode i = AMapLocationMode.Hight_Accuracy;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;
    private long r = StatisticConfig.MIN_UPLOAD_INTERVAL;
    private GeoLanguage s = GeoLanguage.DEFAULT;
    private float u = 0.0f;
    private AMapLocationPurpose v = null;

    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    public enum AMapLocationProtocol {
        HTTP(0),
        HTTPS(1);
        
        private int a;

        private AMapLocationProtocol(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }

    public enum AMapLocationPurpose {
        SignIn,
        Transport,
        Sport
    }

    public enum GeoLanguage {
        DEFAULT,
        ZH,
        EN
    }

    protected AMapLocationClientOption(Parcel parcel) {
        boolean z = true;
        this.b = parcel.readLong();
        this.c = parcel.readLong();
        this.d = parcel.readByte() != (byte) 0;
        this.e = parcel.readByte() != (byte) 0;
        this.f = parcel.readByte() != (byte) 0;
        this.g = parcel.readByte() != (byte) 0;
        this.h = parcel.readByte() != (byte) 0;
        int readInt = parcel.readInt();
        this.i = readInt == -1 ? AMapLocationMode.Hight_Accuracy : AMapLocationMode.values()[readInt];
        this.k = parcel.readByte() != (byte) 0;
        this.l = parcel.readByte() != (byte) 0;
        this.m = parcel.readByte() != (byte) 0;
        this.n = parcel.readByte() != (byte) 0;
        this.o = parcel.readByte() != (byte) 0;
        this.p = parcel.readByte() != (byte) 0;
        this.q = parcel.readByte() != (byte) 0;
        this.r = parcel.readLong();
        readInt = parcel.readInt();
        j = readInt == -1 ? AMapLocationProtocol.HTTP : AMapLocationProtocol.values()[readInt];
        readInt = parcel.readInt();
        this.s = readInt == -1 ? GeoLanguage.DEFAULT : GeoLanguage.values()[readInt];
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        t = z;
        this.u = parcel.readFloat();
        readInt = parcel.readInt();
        this.v = readInt == -1 ? null : AMapLocationPurpose.values()[readInt];
    }

    public static String getAPIKEY() {
        return a;
    }

    public static boolean isDownloadCoordinateConvertLibrary() {
        return t;
    }

    public static void setDownloadCoordinateConvertLibrary(boolean z) {
        t = z;
    }

    public static void setLocationProtocol(AMapLocationProtocol aMapLocationProtocol) {
        j = aMapLocationProtocol;
    }

    public AMapLocationClientOption clone() {
        try {
            super.clone();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.b = this.b;
        aMapLocationClientOption.d = this.d;
        aMapLocationClientOption.i = this.i;
        aMapLocationClientOption.e = this.e;
        aMapLocationClientOption.k = this.k;
        aMapLocationClientOption.l = this.l;
        aMapLocationClientOption.f = this.f;
        aMapLocationClientOption.g = this.g;
        aMapLocationClientOption.c = this.c;
        aMapLocationClientOption.m = this.m;
        aMapLocationClientOption.n = this.n;
        aMapLocationClientOption.o = this.o;
        aMapLocationClientOption.p = isSensorEnable();
        aMapLocationClientOption.q = isWifiScan();
        aMapLocationClientOption.r = this.r;
        setLocationProtocol(getLocationProtocol());
        aMapLocationClientOption.s = this.s;
        aMapLocationClientOption.u = this.u;
        aMapLocationClientOption.v = this.v;
        return aMapLocationClientOption;
    }

    public int describeContents() {
        return 0;
    }

    public float getDeviceModeDistanceFilter() {
        return this.u;
    }

    public GeoLanguage getGeoLanguage() {
        return this.s;
    }

    public long getHttpTimeOut() {
        return this.c;
    }

    public long getInterval() {
        return this.b;
    }

    public long getLastLocationLifeCycle() {
        return this.r;
    }

    public AMapLocationMode getLocationMode() {
        return this.i;
    }

    public AMapLocationProtocol getLocationProtocol() {
        return j;
    }

    public AMapLocationPurpose getLocationPurpose() {
        return this.v;
    }

    public boolean isGpsFirst() {
        return this.l;
    }

    public boolean isKillProcess() {
        return this.k;
    }

    public boolean isLocationCacheEnable() {
        return this.n;
    }

    public boolean isMockEnable() {
        return this.e;
    }

    public boolean isNeedAddress() {
        return this.f;
    }

    public boolean isOffset() {
        return this.m;
    }

    public boolean isOnceLocation() {
        return this.d;
    }

    public boolean isOnceLocationLatest() {
        return this.o;
    }

    public boolean isSensorEnable() {
        return this.p;
    }

    public boolean isWifiActiveScan() {
        return this.g;
    }

    public boolean isWifiScan() {
        return this.q;
    }

    public AMapLocationClientOption setDeviceModeDistanceFilter(float f) {
        this.u = f;
        return this;
    }

    public AMapLocationClientOption setGeoLanguage(GeoLanguage geoLanguage) {
        this.s = geoLanguage;
        return this;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.l = z;
        return this;
    }

    public AMapLocationClientOption setHttpTimeOut(long j) {
        this.c = j;
        return this;
    }

    public AMapLocationClientOption setInterval(long j) {
        if (j <= 800) {
            j = 800;
        }
        this.b = j;
        return this;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.k = z;
        return this;
    }

    public AMapLocationClientOption setLastLocationLifeCycle(long j) {
        this.r = j;
        return this;
    }

    public AMapLocationClientOption setLocationCacheEnable(boolean z) {
        this.n = z;
        return this;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.i = aMapLocationMode;
        return this;
    }

    public AMapLocationClientOption setLocationPurpose(AMapLocationPurpose aMapLocationPurpose) {
        this.v = aMapLocationPurpose;
        if (aMapLocationPurpose != null) {
            switch (aMapLocationPurpose) {
                case SignIn:
                    this.i = AMapLocationMode.Hight_Accuracy;
                    this.d = true;
                    this.o = true;
                    this.l = false;
                    this.e = false;
                    this.q = true;
                    break;
                case Transport:
                case Sport:
                    this.i = AMapLocationMode.Hight_Accuracy;
                    this.d = false;
                    this.o = false;
                    this.l = true;
                    this.e = false;
                    this.q = true;
                    break;
            }
        }
        return this;
    }

    public AMapLocationClientOption setMockEnable(boolean z) {
        this.e = z;
        return this;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.f = z;
        return this;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.m = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.d = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocationLatest(boolean z) {
        this.o = z;
        return this;
    }

    public AMapLocationClientOption setSensorEnable(boolean z) {
        this.p = z;
        return this;
    }

    public AMapLocationClientOption setWifiActiveScan(boolean z) {
        this.g = z;
        this.h = z;
        return this;
    }

    public AMapLocationClientOption setWifiScan(boolean z) {
        this.q = z;
        if (this.q) {
            this.g = this.h;
        } else {
            this.g = false;
        }
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("interval:").append(String.valueOf(this.b)).append("#");
        stringBuilder.append("isOnceLocation:").append(String.valueOf(this.d)).append("#");
        stringBuilder.append("locationMode:").append(String.valueOf(this.i)).append("#");
        stringBuilder.append("locationProtocol:").append(String.valueOf(j)).append("#");
        stringBuilder.append("isMockEnable:").append(String.valueOf(this.e)).append("#");
        stringBuilder.append("isKillProcess:").append(String.valueOf(this.k)).append("#");
        stringBuilder.append("isGpsFirst:").append(String.valueOf(this.l)).append("#");
        stringBuilder.append("isNeedAddress:").append(String.valueOf(this.f)).append("#");
        stringBuilder.append("isWifiActiveScan:").append(String.valueOf(this.g)).append("#");
        stringBuilder.append("wifiScan:").append(String.valueOf(this.q)).append("#");
        stringBuilder.append("httpTimeOut:").append(String.valueOf(this.c)).append("#");
        stringBuilder.append("isLocationCacheEnable:").append(String.valueOf(this.n)).append("#");
        stringBuilder.append("isOnceLocationLatest:").append(String.valueOf(this.o)).append("#");
        stringBuilder.append("sensorEnable:").append(String.valueOf(this.p)).append("#");
        stringBuilder.append("geoLanguage:").append(String.valueOf(this.s)).append("#");
        stringBuilder.append("locationPurpose:").append(String.valueOf(this.v)).append("#");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = -1;
        byte b = (byte) 1;
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
        parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.i == null ? -1 : this.i.ordinal());
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.p ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.r);
        parcel.writeInt(j == null ? -1 : getLocationProtocol().ordinal());
        parcel.writeInt(this.s == null ? -1 : this.s.ordinal());
        if (!t) {
            b = (byte) 0;
        }
        parcel.writeByte(b);
        parcel.writeFloat(this.u);
        if (this.v != null) {
            i2 = this.v.ordinal();
        }
        parcel.writeInt(i2);
    }
}
