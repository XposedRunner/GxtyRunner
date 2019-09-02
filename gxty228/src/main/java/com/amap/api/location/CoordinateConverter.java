package com.amap.api.location;

import android.content.Context;
import com.loc.cl;
import com.loc.cm;
import com.loc.ct;

public class CoordinateConverter {
    DPoint a = null;
    private Context b;
    private CoordType c = null;
    private DPoint d = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter(Context context) {
        this.b = context;
    }

    public static float calculateLineDistance(DPoint dPoint, DPoint dPoint2) {
        try {
            return ct.a(dPoint, dPoint2);
        } catch (Throwable th) {
            return 0.0f;
        }
    }

    public static boolean isAMapDataAvailable(double d, double d2) {
        return cl.a(d, d2);
    }

    public synchronized DPoint convert() throws Exception {
        DPoint dPoint;
        if (this.c == null) {
            throw new IllegalArgumentException("转换坐标类型不能为空");
        } else if (this.d == null) {
            throw new IllegalArgumentException("转换坐标源不能为空");
        } else if (this.d.getLongitude() > 180.0d || this.d.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("请传入合理经度");
        } else if (this.d.getLatitude() > 90.0d || this.d.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("请传入合理纬度");
        } else if (cl.a(this.d.getLatitude(), this.d.getLongitude())) {
            switch (this.c) {
                case BAIDU:
                    this.a = cm.a(this.d);
                    break;
                case MAPBAR:
                    this.a = cm.b(this.b, this.d);
                    break;
                case MAPABC:
                case SOSOMAP:
                case ALIYUN:
                case GOOGLE:
                    this.a = this.d;
                    break;
                case GPS:
                    this.a = cm.a(this.b, this.d);
                    break;
            }
            dPoint = this.a;
        } else {
            dPoint = this.d;
        }
        return dPoint;
    }

    public synchronized CoordinateConverter coord(DPoint dPoint) throws Exception {
        if (dPoint == null) {
            throw new IllegalArgumentException("传入经纬度对象为空");
        } else if (dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("请传入合理经度");
        } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("请传入合理纬度");
        } else {
            this.d = dPoint;
        }
        return this;
    }

    public synchronized CoordinateConverter from(CoordType coordType) {
        this.c = coordType;
        return this;
    }
}
