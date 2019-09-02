package cn.jiguang.g;

import cn.jiguang.e.d;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class f {
    public static ArrayList<String> a(InputStream inputStream) {
        InputStreamReader inputStreamReader;
        Exception e;
        Throwable th;
        ArrayList<String> arrayList = new ArrayList();
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 2048);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    readLine = readLine.trim();
                    if (!"".equals(readLine)) {
                        arrayList.add(readLine);
                    }
                }
                bufferedReader.close();
                try {
                    inputStreamReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    d.i("FileUtil", e.getMessage());
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            inputStreamReader = null;
            d.i("FileUtil", e.getMessage());
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        return arrayList;
    }

    private static void a(File file, ZipOutputStream zipOutputStream, String str) {
        String str2 = new String((str + (str.trim().length() == 0 ? "" : File.separator) + file.getName()).getBytes("8859_1"), "GB2312");
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    a(a, zipOutputStream, str2);
                }
                return;
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }

    public static void a(Collection<File> collection, File file) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File a : collection) {
            a(a, zipOutputStream, "");
        }
        zipOutputStream.close();
    }
}
