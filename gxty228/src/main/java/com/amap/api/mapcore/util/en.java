package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.jiguang.net.HttpUtils;
import com.amap.api.mapcore.util.gk.a;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.AeUtil;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.FPoint3;
import com.autonavi.amap.mapcore.FileUtil;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.github.mikephil.charting.utils.Utils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: Util */
public class en {
    private static FPoint[] a = new FPoint[]{FPoint.obtain(), FPoint.obtain(), FPoint.obtain(), FPoint.obtain()};
    private static List<Float> b = new ArrayList(4);
    private static List<Float> c = new ArrayList(4);

    public static Bitmap a(Context context, String str) {
        try {
            InputStream open = eg.a(context).open(str);
            Bitmap decodeStream = BitmapFactory.decodeStream(open);
            open.close();
            return decodeStream;
        } catch (Throwable th) {
            gz.c(th, "Util", "fromAsset");
            return null;
        }
    }

    public static void a(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static String a(String str, Object obj) {
        return str + HttpUtils.EQUAL_SIGN + String.valueOf(obj);
    }

    public static float a(float f, float f2) {
        if (f <= 40.0f) {
            return f;
        }
        int i;
        if (f2 <= 15.0f) {
            i = 40;
        } else if (f2 <= 16.0f) {
            i = 56;
        } else if (f2 <= 17.0f) {
            i = 66;
        } else if (f2 <= 18.0f) {
            i = 74;
        } else if (f2 <= 18.0f) {
            i = 78;
        } else {
            i = 80;
        }
        if (f > ((float) i)) {
            return (float) i;
        }
        return f;
    }

    public static float a(MapConfig mapConfig, float f) {
        if (mapConfig != null) {
            if (f > mapConfig.maxZoomLevel) {
                return mapConfig.maxZoomLevel;
            }
            if (f < mapConfig.minZoomLevel) {
                return mapConfig.minZoomLevel;
            }
            return f;
        } else if (f > 20.0f) {
            return 20.0f;
        } else {
            if (f < 3.0f) {
                return 3.0f;
            }
            return f;
        }
    }

    public static FloatBuffer a(float[] fArr) {
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
            asFloatBuffer.put(fArr);
            asFloatBuffer.position(0);
            return asFloatBuffer;
        } catch (Throwable th) {
            gz.c(th, "Util", "makeFloatBuffer1");
            th.printStackTrace();
            return null;
        }
    }

    public static FloatBuffer a(float[] fArr, FloatBuffer floatBuffer) {
        try {
            floatBuffer.clear();
            floatBuffer.put(fArr);
            floatBuffer.position(0);
            return floatBuffer;
        } catch (Throwable th) {
            gz.c(th, "Util", "makeFloatBuffer2");
            th.printStackTrace();
            return null;
        }
    }

    public static int a(int i, int i2) {
        return a(0, Bitmap.createBitmap(i, i2, Config.ARGB_8888), true);
    }

    public static int a(Bitmap bitmap) {
        return a(bitmap, false);
    }

    public static int a(Bitmap bitmap, boolean z) {
        return a(0, bitmap, z);
    }

    public static int a(int i, Bitmap bitmap, boolean z) {
        int b = b(i, bitmap, z);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return b;
    }

    public static int b(int i, Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = new int[]{0};
            GLES20.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        if (z) {
            GLES20.glTexParameterf(3553, 10242, 10497.0f);
            GLES20.glTexParameterf(3553, 10243, 10497.0f);
        } else {
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
        }
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        return i;
    }

    public static int a(int i, Bitmap bitmap, int i2, int i3) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = new int[]{0};
            GLES20.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLUtils.texSubImage2D(3553, 0, i2, i3, bitmap);
        return i;
    }

    public static String a(String... strArr) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            stringBuilder.append(strArr[i]);
            if (i2 != strArr.length - 1) {
                stringBuilder.append(",");
            }
            i2++;
            i++;
        }
        return stringBuilder.toString();
    }

    public static int a(Object[] objArr) {
        return Arrays.hashCode(objArr);
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
    }

    public static String a(Context context) {
        File file = new File(FileUtil.getMapBaseStorage(context), AeUtil.ROOT_DATA_PATH_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file.toString() + File.separator);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String b(Context context) {
        return FileUtil.getMapBaseStorage(context) + File.separator + "data" + File.separator;
    }

    public static String c(Context context) {
        String a = a(context);
        if (a == null) {
            return null;
        }
        File file = new File(a, "VMAP2");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String a(int i) {
        if (i < 1000) {
            return i + "m";
        }
        return (i / 1000) + "km";
    }

    public static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        State state = activeNetworkInfo.getState();
        if (state == null || state == State.DISCONNECTED || state == State.DISCONNECTING) {
            return false;
        }
        return true;
    }

    public static boolean a() {
        return VERSION.SDK_INT >= 8;
    }

    public static boolean b() {
        return VERSION.SDK_INT >= 9;
    }

    public static boolean c() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean d() {
        return VERSION.SDK_INT >= 12;
    }

    public static void b(int i) {
        GLES20.glDeleteTextures(1, new int[]{i}, 0);
    }

    public static String a(InputStream inputStream) {
        try {
            return new String(b(inputStream), "utf-8");
        } catch (Throwable th) {
            gz.c(th, "Util", "decodeAssetResData");
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr, 0, 2048);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static String a(File file) {
        BufferedReader bufferedReader;
        Throwable e;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader2 = null;
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream2;
        try {
            fileInputStream2 = new FileInputStream(file);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream2, "utf-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader2 = bufferedReader;
                    } catch (Throwable th) {
                        e = th;
                        bufferedReader2 = bufferedReader;
                    }
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e422) {
                        e422.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                bufferedReader = null;
                fileInputStream = fileInputStream2;
                try {
                    gz.c(e, "Util", "readFile fileNotFound");
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e42222) {
                                    e42222.printStackTrace();
                                }
                            }
                        } catch (Throwable th3) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e52) {
                                    e52.printStackTrace();
                                }
                            }
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e422222) {
                            e422222.printStackTrace();
                        }
                    }
                    return stringBuffer.toString();
                } catch (Throwable th4) {
                    e = th4;
                    fileInputStream2 = fileInputStream;
                    bufferedReader2 = bufferedReader;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e522) {
                            e522.printStackTrace();
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e5222) {
                                    e5222.printStackTrace();
                                }
                            }
                        } catch (Throwable th5) {
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e52222) {
                                    e52222.printStackTrace();
                                }
                            }
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e522222) {
                            e522222.printStackTrace();
                        }
                    }
                    throw e;
                }
            } catch (IOException e7) {
                e = e7;
                try {
                    gz.c(e, "Util", "readFile io");
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e4222222) {
                            e4222222.printStackTrace();
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e42222222) {
                                    e42222222.printStackTrace();
                                }
                            }
                        } catch (Throwable th6) {
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e5222222) {
                                    e5222222.printStackTrace();
                                }
                            }
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e422222222) {
                            e422222222.printStackTrace();
                        }
                    }
                    return stringBuffer.toString();
                } catch (Throwable th7) {
                    e = th7;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw e;
                }
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            bufferedReader = null;
            gz.c(e, "Util", "readFile fileNotFound");
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return stringBuffer.toString();
        } catch (IOException e9) {
            e = e9;
            fileInputStream2 = null;
            gz.c(e, "Util", "readFile io");
            e.printStackTrace();
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return stringBuffer.toString();
        } catch (Throwable th8) {
            e = th8;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw e;
        }
        return stringBuffer.toString();
    }

    public static boolean a(LatLng latLng, List<LatLng> list) {
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        double d3 = latLng.latitude;
        if (list.size() < 3) {
            return false;
        }
        if (!((LatLng) list.get(0)).equals(list.get(list.size() - 1))) {
            list.add(list.get(0));
        }
        int i = 0;
        int i2 = 0;
        while (i < list.size() - 1) {
            double d4 = ((LatLng) list.get(i)).longitude;
            double d5 = ((LatLng) list.get(i)).latitude;
            double d6 = ((LatLng) list.get(i + 1)).longitude;
            double d7 = ((LatLng) list.get(i + 1)).latitude;
            if (b(d, d2, d4, d5, d6, d7)) {
                return true;
            }
            int i3;
            if (Math.abs(d7 - d5) < 1.0E-9d) {
                i3 = i2;
            } else {
                if (b(d4, d5, d, d2, 180.0d, d3)) {
                    if (d5 > d7) {
                        i3 = i2 + 1;
                    }
                } else if (b(d6, d7, d, d2, 180.0d, d3)) {
                    if (d7 > d5) {
                        i3 = i2 + 1;
                    }
                } else if (a(d4, d5, d6, d7, d, d2, 180.0d, d3)) {
                    i3 = i2 + 1;
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return i2 % 2 != 0;
    }

    public static double a(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2));
    }

    public static boolean b(double d, double d2, double d3, double d4, double d5, double d6) {
        if (Math.abs(a(d, d2, d3, d4, d5, d6)) >= 1.0E-9d || (d - d3) * (d - d5) > Utils.DOUBLE_EPSILON || (d2 - d4) * (d2 - d6) > Utils.DOUBLE_EPSILON) {
            return false;
        }
        return true;
    }

    public static boolean a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = ((d3 - d) * (d8 - d6)) - ((d4 - d2) * (d7 - d5));
        if (d9 == Utils.DOUBLE_EPSILON) {
            return false;
        }
        double d10 = (((d2 - d6) * (d7 - d5)) - ((d - d5) * (d8 - d6))) / d9;
        d9 = (((d2 - d6) * (d3 - d)) - ((d - d5) * (d4 - d2))) / d9;
        if (d10 < Utils.DOUBLE_EPSILON || d10 > 1.0d || d9 < Utils.DOUBLE_EPSILON || d9 > 1.0d) {
            return false;
        }
        return true;
    }

    public static boolean a(BaseHoleOptions baseHoleOptions, LatLng latLng) {
        if (baseHoleOptions instanceof CircleHoleOptions) {
            CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
            LatLng center = circleHoleOptions.getCenter();
            double radius = circleHoleOptions.getRadius();
            if (center != null && ((double) AMapUtils.calculateLineDistance(center, latLng)) <= radius) {
                return true;
            }
            return false;
        }
        List points = ((PolygonHoleOptions) baseHoleOptions).getPoints();
        if (points == null || points.size() == 0) {
            return false;
        }
        return a(latLng, points);
    }

    public static List<FPoint> a(FPoint[] fPointArr, List<FPoint> list, boolean z) {
        List arrayList = new ArrayList();
        List<FPoint> arrayList2 = new ArrayList(list);
        int i = 0;
        while (i < 4) {
            arrayList.clear();
            int size = arrayList2.size();
            int i2 = 0;
            while (true) {
                int i3;
                if (z) {
                    i3 = size;
                } else {
                    i3 = size - 1;
                }
                if (i2 >= i3) {
                    break;
                }
                FPoint fPoint = (FPoint) arrayList2.get(i2 % size);
                FPoint fPoint2 = (FPoint) arrayList2.get((i2 + 1) % size);
                if (i2 == 0 && a(fPoint, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                    arrayList.add(fPoint);
                }
                if (a(fPoint, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                    if (a(fPoint2, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                        arrayList.add(fPoint2);
                    } else {
                        arrayList.add(a(fPointArr[i], fPointArr[(i + 1) % fPointArr.length], fPoint, fPoint2));
                    }
                } else if (a(fPoint2, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                    arrayList.add(a(fPointArr[i], fPointArr[(i + 1) % fPointArr.length], fPoint, fPoint2));
                    arrayList.add(fPoint2);
                }
                i2++;
            }
            arrayList2.clear();
            for (i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList2.add(arrayList.get(i3));
            }
            byte b = (byte) (i + 1);
        }
        return arrayList2;
    }

    public static List<FPoint> b(FPoint[] fPointArr, List<FPoint> list, boolean z) {
        List arrayList = new ArrayList();
        List<FPoint> arrayList2 = new ArrayList(list);
        int i = 0;
        while (i < 4) {
            int i2;
            arrayList.clear();
            int size = arrayList2.size();
            int i3 = 0;
            while (true) {
                if (z) {
                    i2 = size;
                } else {
                    i2 = size - 1;
                }
                if (i3 >= i2) {
                    break;
                }
                FPoint fPoint = (FPoint3) arrayList2.get(i3 % size);
                FPoint fPoint2 = (FPoint3) arrayList2.get((i3 + 1) % size);
                if (i3 == 0 && a(fPoint, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                    arrayList.add(fPoint);
                }
                if (a(fPoint, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                    if (a(fPoint2, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                        arrayList.add(fPoint2);
                    } else {
                        arrayList.add(a(fPointArr[i], fPointArr[(i + 1) % fPointArr.length], fPoint, fPoint2, fPoint2.colorIndex));
                    }
                } else if (a(fPoint2, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                    arrayList.add(a(fPointArr[i], fPointArr[(i + 1) % fPointArr.length], fPoint, fPoint2, fPoint.colorIndex));
                    arrayList.add(fPoint2);
                }
                i3++;
            }
            arrayList2.clear();
            for (i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList2.add(arrayList.get(i2));
            }
            byte b = (byte) (i + 1);
        }
        return arrayList2;
    }

    public static FPoint[] a(lj ljVar, boolean z) {
        int i;
        int i2;
        float skyHeight = ljVar.getSkyHeight();
        if (z) {
            i = 100;
            i2 = 10;
        } else {
            i2 = 0;
            i = 0;
        }
        PointF obtain = FPoint.obtain();
        ljVar.a(-i, (int) (skyHeight - ((float) i2)), obtain);
        a[0].set(obtain.x, obtain.y);
        PointF obtain2 = FPoint.obtain();
        ljVar.a(ljVar.getMapWidth() + i, (int) (skyHeight - ((float) i2)), obtain2);
        a[1].set(obtain2.x, obtain2.y);
        PointF obtain3 = FPoint.obtain();
        ljVar.a(ljVar.getMapWidth() + i, ljVar.getMapHeight() + i, obtain3);
        a[2].set(obtain3.x, obtain3.y);
        PointF obtain4 = FPoint.obtain();
        ljVar.a(-i, i + ljVar.getMapHeight(), obtain4);
        a[3].set(obtain4.x, obtain4.y);
        obtain.recycle();
        obtain2.recycle();
        obtain3.recycle();
        obtain4.recycle();
        return a;
    }

    private static FPoint3 a(FPoint fPoint, FPoint fPoint2, FPoint3 fPoint3, FPoint3 fPoint32, int i) {
        FPoint3 fPoint33 = new FPoint3(0.0f, 0.0f, i);
        double d = (double) (((fPoint2.y - fPoint.y) * (fPoint.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint.y - fPoint3.y)));
        double d2 = (double) (((fPoint2.y - fPoint.y) * (fPoint32.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint32.y - fPoint3.y)));
        fPoint33.x = (float) (((double) fPoint3.x) + ((((double) (fPoint32.x - fPoint3.x)) * d) / d2));
        fPoint33.y = (float) (((d * ((double) (fPoint32.y - fPoint3.y))) / d2) + ((double) fPoint3.y));
        return fPoint33;
    }

    private static FPoint a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3, FPoint fPoint4) {
        FPoint obtain = FPoint.obtain(0.0f, 0.0f);
        double d = (double) (((fPoint2.y - fPoint.y) * (fPoint.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint.y - fPoint3.y)));
        double d2 = (double) (((fPoint2.y - fPoint.y) * (fPoint4.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint4.y - fPoint3.y)));
        obtain.x = (float) (((double) fPoint3.x) + ((((double) (fPoint4.x - fPoint3.x)) * d) / d2));
        obtain.y = (float) (((d * ((double) (fPoint4.y - fPoint3.y))) / d2) + ((double) fPoint3.y));
        return obtain;
    }

    public static boolean a(FPoint fPoint, FPoint[] fPointArr) {
        if (fPointArr == null) {
            return false;
        }
        for (int i = 0; i < fPointArr.length; i = (byte) (i + 1)) {
            if (!a(fPoint, fPointArr[i], fPointArr[(i + 1) % fPointArr.length])) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        if (((double) (((fPoint3.x - fPoint2.x) * (fPoint.y - fPoint2.y)) - ((fPoint.x - fPoint2.x) * (fPoint3.y - fPoint2.y)))) >= Utils.DOUBLE_EPSILON) {
            return true;
        }
        return false;
    }

    public static List<IPoint> a(IPoint[] iPointArr, List<IPoint> list, boolean z) {
        List arrayList = new ArrayList();
        List<IPoint> arrayList2 = new ArrayList(list);
        int i = 0;
        while (i < 4) {
            arrayList.clear();
            int size = arrayList2.size();
            int i2 = 0;
            while (true) {
                int i3;
                if (z) {
                    i3 = size;
                } else {
                    i3 = size - 1;
                }
                if (i2 >= i3) {
                    break;
                }
                IPoint iPoint = (IPoint) arrayList2.get(i2 % size);
                IPoint iPoint2 = (IPoint) arrayList2.get((i2 + 1) % size);
                if (i2 == 0 && a(iPoint, iPointArr[i], iPointArr[(i + 1) % iPointArr.length])) {
                    arrayList.add(iPoint);
                }
                if (a(iPoint, iPointArr[i], iPointArr[(i + 1) % iPointArr.length])) {
                    if (a(iPoint2, iPointArr[i], iPointArr[(i + 1) % iPointArr.length])) {
                        arrayList.add(iPoint2);
                    } else {
                        arrayList.add(a(iPointArr[i], iPointArr[(i + 1) % iPointArr.length], iPoint, iPoint2));
                    }
                } else if (a(iPoint2, iPointArr[i], iPointArr[(i + 1) % iPointArr.length])) {
                    arrayList.add(a(iPointArr[i], iPointArr[(i + 1) % iPointArr.length], iPoint, iPoint2));
                    arrayList.add(iPoint2);
                }
                i2++;
            }
            arrayList2.clear();
            for (i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList2.add(arrayList.get(i3));
            }
            byte b = (byte) (i + 1);
        }
        return arrayList2;
    }

    private static IPoint a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, IPoint iPoint4) {
        IPoint obtain = IPoint.obtain(0, 0);
        double d = (((double) (iPoint2.y - iPoint.y)) * ((double) (iPoint.x - iPoint3.x))) - (((double) (iPoint2.x - iPoint.x)) * ((double) (iPoint.y - iPoint3.y)));
        double d2 = (((double) (iPoint2.y - iPoint.y)) * ((double) (iPoint4.x - iPoint3.x))) - (((double) (iPoint2.x - iPoint.x)) * ((double) (iPoint4.y - iPoint3.y)));
        obtain.x = (int) (((double) iPoint3.x) + ((((double) (iPoint4.x - iPoint3.x)) * d) / d2));
        obtain.y = (int) (((d * ((double) (iPoint4.y - iPoint3.y))) / d2) + ((double) iPoint3.y));
        return obtain;
    }

    public static boolean a(List<IPoint> list, int i, int i2) {
        if (i2 < 3) {
            return false;
        }
        double d = Utils.DOUBLE_EPSILON;
        int i3 = i2 - 1;
        for (int i4 = 0; i4 < i2; i4++) {
            IPoint iPoint = (IPoint) list.get(i3);
            IPoint iPoint2 = (IPoint) list.get(i4);
            d += ((((double) iPoint.x) / 1000000.0d) * (((double) iPoint2.y) / 1000000.0d)) - ((((double) iPoint.y) / 1000000.0d) * (((double) iPoint2.x) / 1000000.0d));
            i3 = i4;
        }
        return d < Utils.DOUBLE_EPSILON;
    }

    private static boolean a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3) {
        return a(iPoint.x, iPoint.y, iPoint2, iPoint3);
    }

    private static boolean a(int i, int i2, IPoint iPoint, IPoint iPoint2) {
        if ((((double) (iPoint2.x - iPoint.x)) * ((double) (i2 - iPoint.y))) - (((double) (i - iPoint.x)) * ((double) (iPoint2.y - iPoint.y))) >= Utils.DOUBLE_EPSILON) {
            return true;
        }
        return false;
    }

    public static Bitmap a(int i, int i2, int i3, int i4) {
        try {
            int[] iArr = new int[(i3 * i4)];
            int[] iArr2 = new int[(i3 * i4)];
            Buffer wrap = IntBuffer.wrap(iArr);
            wrap.position(0);
            GLES20.glReadPixels(i, i2, i3, i4, 6408, 5121, wrap);
            for (int i5 = 0; i5 < i4; i5++) {
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = iArr[(i5 * i3) + i6];
                    iArr2[(((i4 - i5) - 1) * i3) + i6] = ((i7 & -16711936) | ((i7 << 16) & 16711680)) | ((i7 >> 16) & 255);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
            createBitmap.setPixels(iArr2, 0, i3, 0, 0, i3, i4);
            return createBitmap;
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImpGLSurfaceView", "SavePixels");
            th.printStackTrace();
            return null;
        }
    }

    public static gk e() {
        try {
            if (le.e != null) {
                return le.e;
            }
            return new a("3dmap", "6.4.1", le.c).a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore", "com.amap.api.3dmap.admic", "com.amap.api.trace", "com.amap.api.trace.core"}).a("6.4.1").a();
        } catch (Throwable th) {
            return null;
        }
    }

    private static void b(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                b(((ViewGroup) view).getChildAt(i));
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setHorizontallyScrolling(false);
        }
    }

    public static Bitmap a(View view) {
        Bitmap bitmap = null;
        try {
            b(view);
            view.destroyDrawingCache();
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                bitmap = drawingCache.copy(Config.ARGB_8888, false);
            }
        } catch (Throwable th) {
            gz.c(th, "Utils", "getBitmapFromView");
            th.printStackTrace();
        }
        return bitmap;
    }

    public static DPoint a(LatLng latLng) {
        double d = (latLng.longitude / 360.0d) + 0.5d;
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        return DPoint.obtain(d * 1.0d, (((Math.log((1.0d + sin) / (1.0d - sin)) * 0.5d) / -6.283185307179586d) + 0.5d) * 1.0d);
    }

    public static boolean a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }

    public static Pair<Float, IPoint> a(AbstractCameraUpdateMessage abstractCameraUpdateMessage, GLMapState gLMapState, MapConfig mapConfig) {
        LatLngBounds latLngBounds = abstractCameraUpdateMessage.bounds;
        int i = abstractCameraUpdateMessage.width;
        int i2 = abstractCameraUpdateMessage.height;
        return a(mapConfig, Math.max(abstractCameraUpdateMessage.paddingLeft, 1), Math.max(abstractCameraUpdateMessage.paddingRight, 1), Math.max(abstractCameraUpdateMessage.paddingTop, 1), Math.max(abstractCameraUpdateMessage.paddingBottom, 1), latLngBounds, i, i2);
    }

    public static Pair<Float, IPoint> a(MapConfig mapConfig, int i, int i2, int i3, int i4, LatLngBounds latLngBounds, int i5, int i6) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null) {
            return null;
        }
        if (mapConfig == null) {
            return null;
        }
        Point latLongToPixels = VirtualEarthProjection.latLongToPixels(latLngBounds.northeast.latitude, latLngBounds.northeast.longitude, 20);
        Point latLongToPixels2 = VirtualEarthProjection.latLongToPixels(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, 20);
        int i7 = latLongToPixels.x - latLongToPixels2.x;
        int i8 = latLongToPixels2.y - latLongToPixels.y;
        int i9 = i5 - (i + i2);
        int i10 = i6 - (i3 + i4);
        if (i7 < 0 && i8 < 0) {
            return null;
        }
        int i11;
        int i12 = i7 <= 0 ? 1 : i7;
        if (i8 <= 0) {
            i11 = 1;
        } else {
            i11 = i8;
        }
        if (i9 <= 0) {
            i9 = 1;
        }
        if (i10 <= 0) {
            i10 = 1;
        }
        Pair a = a(mapConfig, latLongToPixels.x, latLongToPixels.y, latLongToPixels2.x, latLongToPixels2.y, i9, i10);
        float floatValue = ((Float) a.first).floatValue();
        boolean booleanValue = ((Boolean) a.second).booleanValue();
        float a2 = a(mapConfig.getMapZoomScale(), floatValue, (double) i12);
        float a3 = a(mapConfig.getMapZoomScale(), floatValue, (double) i11);
        if (floatValue >= mapConfig.maxZoomLevel) {
            i7 = (int) (((float) latLongToPixels2.x) + (((((float) (i2 - i)) + a2) * ((float) i12)) / (a2 * 2.0f)));
            i8 = (int) (((float) latLongToPixels.y) + (((((float) (i4 - i3)) + a3) * ((float) i11)) / (a3 * 2.0f)));
        } else if (booleanValue) {
            i7 = (int) (((float) latLongToPixels2.x) + ((((float) ((i5 / 2) - i)) / a2) * ((float) i12)));
            i8 = (int) (((float) latLongToPixels.y) + (((((float) (i4 - i3)) + a3) * ((float) i11)) / (a3 * 2.0f)));
        } else {
            i7 = (int) (((float) latLongToPixels2.x) + (((((float) (i2 - i)) + a2) * ((float) i12)) / (a2 * 2.0f)));
            i8 = (int) (((float) latLongToPixels.y) + ((((float) ((i6 / 2) - i3)) / a3) * ((float) i11)));
        }
        return new Pair(Float.valueOf(floatValue), IPoint.obtain((int) (((float) i7) + a(mapConfig.getMapZoomScale(), floatValue, (float) (mapConfig.getAnchorX() - (mapConfig.getMapWidth() >> 1)))), (int) (((float) i8) + a(mapConfig.getMapZoomScale(), floatValue, (float) (mapConfig.getAnchorY() - (mapConfig.getMapHeight() >> 1))))));
    }

    public static double a(float f, double d, double d2) {
        return 20.0d - (Math.log(d2 / (((double) f) * d)) / Math.log(2.0d));
    }

    private static float a(float f, float f2, double d) {
        return (float) (d / (Math.pow(2.0d, (double) (20.0f - f2)) * ((double) f)));
    }

    private static float a(float f, float f2, float f3) {
        return (float) ((((double) f3) * Math.pow(2.0d, (double) (20.0f - f2))) * ((double) f));
    }

    public static Pair<Float, Boolean> a(MapConfig mapConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        float maxZoomLevel;
        boolean z = true;
        mapConfig.getSZ();
        if (i == i3 && i2 == i4) {
            maxZoomLevel = mapConfig.getMaxZoomLevel();
        } else {
            float a = (float) a(mapConfig.getMapZoomScale(), (double) i5, (double) Math.abs(i3 - i));
            maxZoomLevel = Math.min(a, (float) a(mapConfig.getMapZoomScale(), (double) i6, (double) Math.abs(i4 - i2)));
            if (maxZoomLevel != a) {
                z = false;
            }
            maxZoomLevel = Math.min(mapConfig.getMaxZoomLevel(), Math.max(mapConfig.getMinZoomLevel(), maxZoomLevel));
        }
        return new Pair(Float.valueOf(maxZoomLevel), Boolean.valueOf(z));
    }

    public static float b(MapConfig mapConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        float sz = mapConfig.getSZ();
        if (i == i3 || i2 == i4) {
            return sz;
        }
        return Math.max((float) a(mapConfig.getMapZoomScale(), (double) i5, (double) Math.abs(i3 - i)), (float) a(mapConfig.getMapZoomScale(), (double) i6, (double) Math.abs(i4 - i2)));
    }

    public static boolean b(int i, int i2) {
        if (i > 0 && i2 > 0) {
            return true;
        }
        Log.w("3dmap", "the map must have a size");
        return false;
    }

    public static synchronized int[] a(int i, int i2, int i3, int i4, MapConfig mapConfig, GLMapState gLMapState, int i5, int i6) {
        int[] iArr;
        synchronized (en.class) {
            int mapWidth = mapConfig.getMapWidth();
            int mapHeight = mapConfig.getMapHeight();
            int anchorX = mapConfig.getAnchorX();
            int anchorY = mapConfig.getAnchorY();
            float a = a(mapConfig.getMapZoomScale(), gLMapState.getMapZoomer(), (float) anchorX);
            float a2 = a(mapConfig.getMapZoomScale(), gLMapState.getMapZoomer(), (float) (mapWidth - anchorX));
            float a3 = a(mapConfig.getMapZoomScale(), gLMapState.getMapZoomer(), (float) anchorY);
            float f = ((float) i3) + a;
            a2 = ((float) i) - a2;
            a3 += (float) i2;
            float a4 = ((float) i4) - a(mapConfig.getMapZoomScale(), gLMapState.getMapZoomer(), (float) (mapHeight - anchorY));
            iArr = new int[]{(int) Math.max(f, Math.min((float) i5, a2)), (int) Math.max(a3, Math.min((float) i6, a4))};
        }
        return iArr;
    }

    public static void a(Rect rect) {
        if (rect != null) {
            rect.set(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    public static void b(Rect rect, int i, int i2) {
        if (rect != null) {
            if (i < rect.left) {
                rect.left = i;
            }
            if (i > rect.right) {
                rect.right = i;
            }
            if (i2 > rect.top) {
                rect.top = i2;
            }
            if (i2 < rect.bottom) {
                rect.bottom = i2;
            }
        }
    }

    public static byte[] a(byte[] bArr, int[] iArr) {
        try {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            Bitmap copy = decodeByteArray.copy(decodeByteArray.getConfig(), true);
            decodeByteArray.getWidth();
            decodeByteArray.getHeight();
            int i = 6;
            int i2 = 6;
            int i3 = 0;
            for (int i4 = 1; i4 < 72; i4++) {
                for (int i5 = 8; i5 < 12; i5++) {
                    decodeByteArray.getPixel(i5, i4);
                    if (i4 < 4 * i) {
                        copy.setPixel(i5, i4, iArr[i3]);
                    } else {
                        i3++;
                        i2--;
                        i += i2;
                    }
                }
            }
            byte[] b = b(copy);
            if (b == null) {
                b = bArr;
            }
            copy.recycle();
            decodeByteArray.recycle();
            return b;
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        try {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            Bitmap copy = decodeByteArray.copy(decodeByteArray.getConfig(), true);
            int width = decodeByteArray.getWidth();
            int height = decodeByteArray.getHeight();
            for (int i2 = 1; i2 < width; i2++) {
                for (int i3 = 1; i3 < height; i3++) {
                    copy.setPixel(i2, i3, i);
                }
            }
            byte[] b = b(copy);
            if (b == null) {
                b = bArr;
            }
            copy.recycle();
            decodeByteArray.recycle();
            return b;
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    private static byte[] b(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th22) {
                        th22.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th222) {
            Throwable th4 = th222;
            byteArrayOutputStream = bArr;
            th = th4;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
        return bArr;
    }

    public static boolean a(List<BaseHoleOptions> list, PolygonHoleOptions polygonHoleOptions) {
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            BaseHoleOptions baseHoleOptions = (BaseHoleOptions) list.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                z = a(((PolygonHoleOptions) baseHoleOptions).getPoints(), polygonHoleOptions.getPoints());
                if (z) {
                    return true;
                }
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                z = b(polygonHoleOptions.getPoints(), (CircleHoleOptions) baseHoleOptions);
                if (z) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return z;
    }

    public static boolean a(List<BaseHoleOptions> list, CircleHoleOptions circleHoleOptions) {
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            BaseHoleOptions baseHoleOptions = (BaseHoleOptions) list.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                z = b(((PolygonHoleOptions) baseHoleOptions).getPoints(), circleHoleOptions);
                if (z) {
                    return true;
                }
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                z = a(circleHoleOptions, (CircleHoleOptions) baseHoleOptions);
                if (z) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return z;
    }

    public static boolean a(CircleHoleOptions circleHoleOptions, CircleHoleOptions circleHoleOptions2) {
        try {
            if (((double) AMapUtils.calculateLineDistance(circleHoleOptions2.getCenter(), circleHoleOptions.getCenter())) < circleHoleOptions.getRadius() + circleHoleOptions2.getRadius()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            gz.c(th, "Util", "isPolygon2CircleIntersect");
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(List<LatLng> list, CircleHoleOptions circleHoleOptions) {
        try {
            List arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(list.get(i));
            }
            arrayList.add(list.get(0));
            List arrayList2 = new ArrayList();
            int i2 = 0;
            while (i2 < arrayList.size() && i2 + 1 < arrayList.size()) {
                if (circleHoleOptions.getRadius() >= ((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) arrayList.get(i2))) || circleHoleOptions.getRadius() >= ((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) arrayList.get(i2 + 1)))) {
                    return true;
                }
                Object obj;
                arrayList2.clear();
                arrayList2.add(arrayList.get(i2));
                arrayList2.add(arrayList.get(i2 + 1));
                if (circleHoleOptions.getRadius() >= ((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) SpatialRelationUtil.calShortestDistancePoint(arrayList2, circleHoleOptions.getCenter()).second))) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    return true;
                }
                i2++;
            }
        } catch (Throwable th) {
            gz.c(th, "Util", "isPolygon2CircleIntersect");
            th.printStackTrace();
        }
        return false;
    }

    public static boolean a(List<LatLng> list, List<LatLng> list2) {
        int i = 0;
        while (i < list2.size()) {
            try {
                if (a((LatLng) list2.get(i), (List) list)) {
                    return true;
                }
                i++;
            } catch (Throwable th) {
                gz.c(th, "Util", "isPolygon2PolygonIntersect");
                th.printStackTrace();
                return false;
            }
        }
        for (i = 0; i < list.size(); i++) {
            if (a((LatLng) list.get(i), (List) list2)) {
                return true;
            }
        }
        return b((List) list, (List) list2);
    }

    private static boolean b(List<LatLng> list, List<LatLng> list2) {
        int i = 0;
        while (i < list.size() && i + 1 < list.size()) {
            try {
                int i2 = 0;
                while (i2 < list2.size() && i2 + 1 < list2.size()) {
                    boolean a = ei.a((LatLng) list.get(i), (LatLng) list.get(i + 1), (LatLng) list2.get(i2), (LatLng) list2.get(i2 + 1));
                    if (a) {
                        return a;
                    }
                    i2++;
                }
                i++;
            } catch (Throwable th) {
                gz.c(th, "Util", "isSegmentsIntersect");
                th.printStackTrace();
            }
        }
        return false;
    }

    public static boolean e(Context context) {
        File file = new File(b(context));
        if (file.exists()) {
            return FileUtil.deleteFile(file);
        }
        return true;
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        if (dPoint == null || dPoint2 == null) {
            return 0.0f;
        }
        double d = dPoint.x;
        double d2 = dPoint2.x;
        return (float) ((Math.atan2(dPoint2.y - dPoint.y, d2 - d) / 3.141592653589793d) * 180.0d);
    }
}
