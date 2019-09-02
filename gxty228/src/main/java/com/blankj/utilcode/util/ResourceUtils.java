package com.blankj.utilcode.util;

import android.support.annotation.RawRes;
import cn.jiguang.net.HttpUtils;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class ResourceUtils {
    private static final int BUFFER_SIZE = 8192;

    private ResourceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean copyFileFromAssets(String str, String str2) {
        boolean z = true;
        try {
            String[] list = Utils.getApp().getAssets().list(str);
            if (list.length <= 0) {
                return writeFileFromIS(str2, Utils.getApp().getAssets().open(str), false);
            }
            int length = list.length;
            int i = 0;
            while (i < length) {
                String str3 = list[i];
                i++;
                z = copyFileFromAssets(str + HttpUtils.PATHS_SEPARATOR + str3, str2 + HttpUtils.PATHS_SEPARATOR + str3) & z;
            }
            return z;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readAssets2String(String str) {
        return readAssets2String(str, null);
    }

    public static String readAssets2String(String str, String str2) {
        try {
            byte[] is2Bytes = is2Bytes(Utils.getApp().getAssets().open(str));
            if (is2Bytes == null) {
                return null;
            }
            if (isSpace(str2)) {
                return new String(is2Bytes);
            }
            try {
                return new String(is2Bytes, str2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static List<String> readAssets2List(String str) {
        return readAssets2List(str, null);
    }

    public static List<String> readAssets2List(String str, String str2) {
        try {
            return is2List(Utils.getApp().getResources().getAssets().open(str), str2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean copyFileFromRaw(@RawRes int i, String str) {
        return writeFileFromIS(str, Utils.getApp().getResources().openRawResource(i), false);
    }

    public static String readRaw2String(@RawRes int i) {
        return readRaw2String(i, null);
    }

    public static String readRaw2String(@RawRes int i, String str) {
        byte[] is2Bytes = is2Bytes(Utils.getApp().getResources().openRawResource(i));
        if (is2Bytes == null) {
            return null;
        }
        if (isSpace(str)) {
            return new String(is2Bytes);
        }
        try {
            return new String(is2Bytes, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<String> readRaw2List(@RawRes int i) {
        return readRaw2List(i, null);
    }

    public static List<String> readRaw2List(@RawRes int i, String str) {
        return is2List(Utils.getApp().getResources().openRawResource(i), str);
    }

    private static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z);
    }

    private static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) {
        OutputStream bufferedOutputStream;
        IOException e;
        Throwable th;
        boolean z2 = false;
        if (createOrExistsFile(file) && inputStream != null) {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = inputStream.read(bArr, 0, 8192);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    z2 = true;
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e22 = e3;
                    try {
                        e22.printStackTrace();
                        try {
                            inputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        return z2;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            inputStream.close();
                        } catch (IOException e22222) {
                            e22222.printStackTrace();
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e222222) {
                                e222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222222 = e4;
                bufferedOutputStream = null;
                e222222.printStackTrace();
                inputStream.close();
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return z2;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
                inputStream.close();
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        return z2;
    }

    private static File getFileByPath(String str) {
        return isSpace(str) ? null : new File(str);
    }

    private static boolean createOrExistsFile(File file) {
        boolean z = false;
        if (file == null) {
            return z;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return z;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static byte[] is2Bytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e;
        Throwable th;
        byte[] bArr = null;
        if (inputStream != null) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[8192];
                    while (true) {
                        int read = inputStream.read(bArr2, 0, 8192);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e22 = e3;
                    try {
                        e22.printStackTrace();
                        try {
                            inputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            inputStream.close();
                        } catch (IOException e22222) {
                            e22222.printStackTrace();
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e222222) {
                                e222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222222 = e4;
                byteArrayOutputStream = bArr;
                e222222.printStackTrace();
                inputStream.close();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return bArr;
            } catch (Throwable th3) {
                byteArrayOutputStream = bArr;
                th = th3;
                inputStream.close();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    private static List<String> is2List(InputStream inputStream, String str) {
        IOException e;
        Throwable th;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            List<String> arrayList = new ArrayList();
            if (isSpace(str)) {
                bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            } else {
                bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, str));
            }
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (bufferedReader2 == null) {
                return arrayList;
            }
            try {
                bufferedReader2.close();
                return arrayList;
            } catch (IOException e3) {
                e3.printStackTrace();
                return arrayList;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedReader2 = null;
            try {
                e.printStackTrace();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }
}
