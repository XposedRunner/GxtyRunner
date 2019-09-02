package com.autonavi.amap.mapcore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.mapcore.util.dq;
import com.amap.api.mapcore.util.gp;
import com.amap.api.mapcore.util.gz;
import com.amap.api.mapcore.util.hx;
import com.amap.api.mapcore.util.kb;
import com.amap.api.mapcore.util.kc;
import com.autonavi.ae.gmap.GLMapEngine.InitParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipInputStream;

@SuppressLint({"NewApi"})
public class AeUtil {
    private static final int BUFFER = 1024;
    public static final String CONFIGNAME = "GNaviConfig.xml";
    public static final boolean IS_AE = true;
    public static final String RESZIPNAME = "res.zip";
    public static final String ROOTPATH = "/amap/";
    public static final String ROOT_DATA_PATH_NAME = "data_v6";
    public static final String ROOT_DATA_PATH_OLD_NAME = "data";
    public static final String SO_FILENAME = "AMapSDK_MAP_v6_4_1";
    public static final String SO_FILENAME_NAVI = "AMapSDK_NAVI_v6_3_0";

    public static class UnZipFileBrake {
        public boolean mIsAborted = false;
    }

    public interface ZipCompressProgressListener {
        void onFinishProgress(long j);
    }

    public static void loadLib(Context context) {
        try {
            hx.a().a(context, dq.a(), SO_FILENAME);
        } catch (Throwable th) {
            gz.c(th, "AeUtil", "loadLib");
        }
    }

    public static InitParam initResource(final Context context) {
        final String mapBaseStorage = FileUtil.getMapBaseStorage(context);
        String str = mapBaseStorage + ROOT_DATA_PATH_NAME + HttpUtils.PATHS_SEPARATOR;
        File file = new File(mapBaseStorage);
        if (!file.exists()) {
            file.mkdir();
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                kb.a(1).a(new kc() {
                    public void runTask() {
                        AeUtil.loadEngineRes(mapBaseStorage, context);
                    }
                });
            } catch (gp e) {
                e.printStackTrace();
            }
        } else {
            loadEngineRes(mapBaseStorage, context);
        }
        InitParam initParam = new InitParam();
        File file2 = new File(mapBaseStorage, CONFIGNAME);
        if (!(file2.exists() && file2.isFile() && file2.length() > 0)) {
            readAssetsFileAndSave("ae/GNaviConfig.xml", mapBaseStorage + CONFIGNAME, context);
        }
        initParam.mRootPath = mapBaseStorage;
        initParam.mConfigPath = mapBaseStorage + CONFIGNAME;
        initParam.mOfflineDataPath = str + HttpUtils.PATHS_SEPARATOR + "map/";
        initParam.mP3dCrossPath = str;
        return initParam;
    }

    private static void loadEngineRes(String str, Context context) {
        File file = new File(str, "res");
        if (!(file.exists() && file.isDirectory()) && file.mkdirs()) {
        }
        if (!checkEngineRes(file)) {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open("ae/res.zip");
                decompress(inputStream, file.getAbsolutePath());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (OutOfMemoryError e4) {
                e4.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
            }
        }
    }

    private static boolean checkEngineRes(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length < 2) {
            return false;
        }
        return true;
    }

    public static void decompress(InputStream inputStream, String str) throws Exception {
        decompress(inputStream, str, 0, null);
    }

    private static void decompress(InputStream inputStream, String str, long j, ZipCompressProgressListener zipCompressProgressListener) throws Exception {
        InputStream checkedInputStream = new CheckedInputStream(inputStream, new CRC32());
        ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
        decompress(null, new File(str), zipInputStream, j, zipCompressProgressListener, null);
        zipInputStream.close();
        checkedInputStream.close();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decompress(java.io.File r13, java.io.File r14, java.util.zip.ZipInputStream r15, long r16, com.autonavi.amap.mapcore.AeUtil.ZipCompressProgressListener r18, com.autonavi.amap.mapcore.AeUtil.UnZipFileBrake r19) throws java.lang.Exception {
        /*
        r10 = 0;
        r11 = 0;
    L_0x0002:
        r3 = r15.getNextEntry();
        if (r3 == 0) goto L_0x0071;
    L_0x0008:
        if (r19 == 0) goto L_0x0014;
    L_0x000a:
        r0 = r19;
        r2 = r0.mIsAborted;
        if (r2 == 0) goto L_0x0014;
    L_0x0010:
        r15.closeEntry();
    L_0x0013:
        return;
    L_0x0014:
        r2 = r3.getName();
        r4 = android.text.TextUtils.isEmpty(r2);
        if (r4 != 0) goto L_0x0026;
    L_0x001e:
        r4 = "../";
        r4 = r2.contains(r4);
        if (r4 == 0) goto L_0x0031;
    L_0x0026:
        r2 = 1;
    L_0x0027:
        if (r2 == 0) goto L_0x0013;
    L_0x0029:
        if (r13 == 0) goto L_0x0013;
    L_0x002b:
        r13.delete();	 Catch:{ Exception -> 0x002f }
        goto L_0x0013;
    L_0x002f:
        r2 = move-exception;
        goto L_0x0013;
    L_0x0031:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r14.getPath();
        r4 = r4.append(r5);
        r5 = java.io.File.separator;
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = r2.toString();
        r2 = new java.io.File;
        r2.<init>(r4);
        fileProber(r2);
        r3 = r3.isDirectory();
        if (r3 == 0) goto L_0x0063;
    L_0x005a:
        r2.mkdirs();
        r2 = r10;
    L_0x005e:
        r15.closeEntry();
        r10 = r2;
        goto L_0x0002;
    L_0x0063:
        r4 = (long) r10;
        r3 = r15;
        r6 = r16;
        r8 = r18;
        r9 = r19;
        r2 = decompressFile(r2, r3, r4, r6, r8, r9);
        r2 = r2 + r10;
        goto L_0x005e;
    L_0x0071:
        r2 = r11;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.AeUtil.decompress(java.io.File, java.io.File, java.util.zip.ZipInputStream, long, com.autonavi.amap.mapcore.AeUtil$ZipCompressProgressListener, com.autonavi.amap.mapcore.AeUtil$UnZipFileBrake):void");
    }

    private static void fileProber(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            fileProber(parentFile);
            parentFile.mkdir();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int decompressFile(java.io.File r8, java.util.zip.ZipInputStream r9, long r10, long r12, com.autonavi.amap.mapcore.AeUtil.ZipCompressProgressListener r14, com.autonavi.amap.mapcore.AeUtil.UnZipFileBrake r15) throws java.lang.Exception {
        /*
        r0 = 0;
        r1 = new java.io.BufferedOutputStream;
        r2 = new java.io.FileOutputStream;
        r2.<init>(r8);
        r1.<init>(r2);
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];
    L_0x000f:
        r3 = 0;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = r9.read(r2, r3, r4);
        r4 = -1;
        if (r3 == r4) goto L_0x0040;
    L_0x0019:
        if (r15 == 0) goto L_0x0023;
    L_0x001b:
        r4 = r15.mIsAborted;
        if (r4 == 0) goto L_0x0023;
    L_0x001f:
        r1.close();
    L_0x0022:
        return r0;
    L_0x0023:
        r4 = 0;
        r1.write(r2, r4, r3);
        r0 = r0 + r3;
        r4 = 0;
        r3 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r3 <= 0) goto L_0x000f;
    L_0x002e:
        if (r14 == 0) goto L_0x000f;
    L_0x0030:
        r4 = (long) r0;
        r4 = r4 + r10;
        r6 = 100;
        r4 = r4 * r6;
        r4 = r4 / r12;
        if (r15 == 0) goto L_0x003c;
    L_0x0038:
        r3 = r15.mIsAborted;
        if (r3 != 0) goto L_0x000f;
    L_0x003c:
        r14.onFinishProgress(r4);
        goto L_0x000f;
    L_0x0040:
        r1.close();
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.AeUtil.decompressFile(java.io.File, java.util.zip.ZipInputStream, long, long, com.autonavi.amap.mapcore.AeUtil$ZipCompressProgressListener, com.autonavi.amap.mapcore.AeUtil$UnZipFileBrake):int");
    }

    public static void readAssetsFileAndSave(String str, String str2, Context context) {
        InputStream open;
        Throwable th;
        InputStream inputStream;
        FileOutputStream fileOutputStream = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                open = context.getAssets().open(str);
                try {
                    File file = new File(str2);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = open.read(bArr, 0, 1024);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                        }
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        if (open != null) {
                            open.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (open != null) {
                        open.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                open = null;
                if (open != null) {
                    open.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
    }
}
