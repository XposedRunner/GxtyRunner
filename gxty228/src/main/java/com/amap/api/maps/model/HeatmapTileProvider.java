package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.v4.util.LongSparseArray;
import android.util.Log;
import com.amap.api.mapcore.util.dp;
import com.amap.api.maps.AMapException;
import com.autonavi.amap.mapcore.DPoint;
import com.github.mikephil.charting.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT = new Gradient(a, b);
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;
    private static final int[] a = new int[]{Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
    private static final float[] b = new float[]{0.2f, 1.0f};
    private a c;
    private Collection<WeightedLatLng> d;
    private dp e;
    private int f;
    private Gradient g;
    private int[] h;
    private double[] i;
    private double j;
    private double[] k;

    public static class Builder {
        private Collection<WeightedLatLng> a;
        private int b = 12;
        private Gradient c = HeatmapTileProvider.DEFAULT_GRADIENT;
        private double d = 0.6d;

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.d(collection));
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.a = collection;
            return this;
        }

        public Builder radius(int i) {
            this.b = Math.max(10, Math.min(i, 50));
            return this;
        }

        public Builder gradient(Gradient gradient) {
            this.c = gradient;
            return this;
        }

        public Builder transparency(double d) {
            this.d = Math.max(Utils.DOUBLE_EPSILON, Math.min(d, 1.0d));
            return this;
        }

        public HeatmapTileProvider build() {
            if (this.a == null || this.a.size() == 0) {
                try {
                    throw new AMapException("No input points.");
                } catch (AMapException e) {
                    Log.e("amap", e.getErrorMessage());
                    e.printStackTrace();
                    return null;
                }
            }
            try {
                return new HeatmapTileProvider();
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    private HeatmapTileProvider(Builder builder) {
        this.d = builder.a;
        this.f = builder.b;
        this.g = builder.c;
        if (this.g == null || !this.g.isAvailable()) {
            this.g = DEFAULT_GRADIENT;
        }
        this.j = builder.d;
        this.i = a(this.f, ((double) this.f) / 3.0d);
        a(this.g);
        c(this.d);
    }

    private void c(Collection<WeightedLatLng> collection) {
        try {
            Collection arrayList = new ArrayList();
            for (WeightedLatLng weightedLatLng : collection) {
                if (weightedLatLng.latLng.latitude < 85.0d && weightedLatLng.latLng.latitude > -85.0d) {
                    arrayList.add(weightedLatLng);
                }
            }
            this.d = arrayList;
            this.e = a(this.d);
            this.c = new a(this.e);
            for (WeightedLatLng weightedLatLng2 : this.d) {
                this.c.a(weightedLatLng2);
            }
            this.k = a(this.f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static Collection<WeightedLatLng> d(Collection<LatLng> collection) {
        Collection arrayList = new ArrayList();
        for (LatLng weightedLatLng : collection) {
            arrayList.add(new WeightedLatLng(weightedLatLng));
        }
        return arrayList;
    }

    public Tile getTile(int i, int i2, int i3) {
        double d;
        double pow = 1.0d / Math.pow(2.0d, (double) i3);
        double d2 = (((double) this.f) * pow) / 256.0d;
        double d3 = ((2.0d * d2) + pow) / ((double) ((this.f * 2) + 256));
        double d4 = (((double) i) * pow) - d2;
        double d5 = (((double) (i + 1)) * pow) + d2;
        double d6 = (((double) i2) * pow) - d2;
        double d7 = (pow * ((double) (i2 + 1))) + d2;
        ArrayList arrayList = new ArrayList();
        Collection a;
        if (d4 < Utils.DOUBLE_EPSILON) {
            a = this.c.a(new dp(1.0d + d4, 1.0d, d6, d7));
            d = -1.0d;
        } else if (d5 > 1.0d) {
            a = this.c.a(new dp(Utils.DOUBLE_EPSILON, d5 - 1.0d, d6, d7));
            d = 1.0d;
        } else {
            Object obj = arrayList;
            d = Utils.DOUBLE_EPSILON;
        }
        dp dpVar = new dp(d4, d5, d6, d7);
        if (!dpVar.a(new dp(this.e.a - d2, this.e.c + d2, this.e.b - d2, d2 + this.e.d))) {
            return TileProvider.NO_TILE;
        }
        Collection<WeightedLatLng> a2 = this.c.a(dpVar);
        if (a2.isEmpty()) {
            return TileProvider.NO_TILE;
        }
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{(this.f * 2) + 256, (this.f * 2) + 256});
        for (WeightedLatLng weightedLatLng : a2) {
            DPoint point = weightedLatLng.getPoint();
            int i4 = (int) ((point.x - d4) / d3);
            int i5 = (int) ((point.y - d6) / d3);
            double[] dArr2 = dArr[i4];
            dArr2[i5] = dArr2[i5] + weightedLatLng.intensity;
        }
        for (WeightedLatLng weightedLatLng2 : r20) {
            point = weightedLatLng2.getPoint();
            i4 = (int) (((point.x + d) - d4) / d3);
            i5 = (int) ((point.y - d6) / d3);
            dArr2 = dArr[i4];
            dArr2[i5] = dArr2[i5] + weightedLatLng2.intensity;
        }
        return a(a(a(dArr, this.i), this.h, this.k[i3]));
    }

    private void a(Gradient gradient) {
        this.g = gradient;
        this.h = gradient.generateColorMap(this.j);
    }

    private double[] a(int i) {
        int i2 = 11;
        double[] dArr = new double[21];
        for (int i3 = 5; i3 < 11; i3++) {
            dArr[i3] = a(this.d, this.e, i, (int) (1280.0d * Math.pow(2.0d, (double) i3)));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
        }
        while (i2 < 21) {
            dArr[i2] = dArr[10];
            i2++;
        }
        return dArr;
    }

    private static Tile a(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return Tile.obtain(256, 256, byteArrayOutputStream.toByteArray());
    }

    static dp a(Collection<WeightedLatLng> collection) {
        Iterator it = collection.iterator();
        WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
        double d = weightedLatLng.getPoint().x;
        double d2 = weightedLatLng.getPoint().x;
        double d3 = weightedLatLng.getPoint().y;
        double d4 = weightedLatLng.getPoint().y;
        while (it.hasNext()) {
            weightedLatLng = (WeightedLatLng) it.next();
            double d5 = weightedLatLng.getPoint().x;
            double d6 = weightedLatLng.getPoint().y;
            if (d5 < d) {
                d = d5;
            }
            if (d5 > d2) {
                d2 = d5;
            }
            if (d6 < d3) {
                d3 = d6;
            }
            if (d6 > d4) {
                d4 = d6;
            }
        }
        return new dp(d, d2, d3, d4);
    }

    static double[] a(int i, double d) {
        double[] dArr = new double[((i * 2) + 1)];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d) * d));
        }
        return dArr;
    }

    static double[][] a(double[][] dArr, double[] dArr2) {
        int i;
        int floor = (int) Math.floor(((double) dArr2.length) / 2.0d);
        int length = dArr.length;
        int i2 = length - (floor * 2);
        int i3 = (floor + i2) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, length});
        int i4 = 0;
        while (i4 < length) {
            int i5;
            for (i5 = 0; i5 < length; i5++) {
                double d = dArr[i4][i5];
                if (d != Utils.DOUBLE_EPSILON) {
                    i = (i3 < i4 + floor ? i3 : i4 + floor) + 1;
                    int i6 = floor > i4 - floor ? floor : i4 - floor;
                    while (i6 < i) {
                        double[] dArr4 = dArr3[i6];
                        dArr4[i5] = dArr4[i5] + (dArr2[i6 - (i4 - floor)] * d);
                        i6++;
                    }
                }
            }
            i4++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, new int[]{i2, i2});
        for (i2 = floor; i2 < i3 + 1; i2++) {
            i4 = 0;
            while (i4 < length) {
                d = dArr3[i2][i4];
                if (d != Utils.DOUBLE_EPSILON) {
                    i = (i3 < i4 + floor ? i3 : i4 + floor) + 1;
                    i5 = floor > i4 - floor ? floor : i4 - floor;
                    while (i5 < i) {
                        dArr4 = dArr5[i2 - floor];
                        int i7 = i5 - floor;
                        dArr4[i7] = dArr4[i7] + (dArr2[i5 - (i4 - floor)] * d);
                        i5++;
                    }
                }
                i4++;
            }
        }
        return dArr5;
    }

    static Bitmap a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = ((double) (iArr.length - 1)) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[(length2 * length2)];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == Utils.DOUBLE_EPSILON) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    static double a(Collection<WeightedLatLng> collection, dp dpVar, int i, int i2) {
        double d = dpVar.a;
        double d2 = dpVar.c;
        double d3 = dpVar.b;
        double d4 = dpVar.d;
        double d5 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / (d2 - d > d4 - d3 ? d2 - d : d4 - d3);
        LongSparseArray longSparseArray = new LongSparseArray();
        d4 = Utils.DOUBLE_EPSILON;
        for (WeightedLatLng weightedLatLng : collection) {
            LongSparseArray longSparseArray2;
            int i3 = (int) ((weightedLatLng.getPoint().x - d) * d5);
            int i4 = (int) ((weightedLatLng.getPoint().y - d3) * d5);
            LongSparseArray longSparseArray3 = (LongSparseArray) longSparseArray.get((long) i3);
            if (longSparseArray3 == null) {
                longSparseArray3 = new LongSparseArray();
                longSparseArray.put((long) i3, longSparseArray3);
                longSparseArray2 = longSparseArray3;
            } else {
                longSparseArray2 = longSparseArray3;
            }
            Double d6 = (Double) longSparseArray2.get((long) i4);
            if (d6 == null) {
                d6 = Double.valueOf(Utils.DOUBLE_EPSILON);
            }
            Double valueOf = Double.valueOf(weightedLatLng.intensity + d6.doubleValue());
            longSparseArray2.put((long) i4, valueOf);
            if (valueOf.doubleValue() > d4) {
                d2 = valueOf.doubleValue();
            } else {
                d2 = d4;
            }
            d4 = d2;
        }
        return d4;
    }

    public int getTileHeight() {
        return 256;
    }

    public int getTileWidth() {
        return 256;
    }
}
