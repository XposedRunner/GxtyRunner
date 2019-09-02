package com.amap.api.mapcore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: FileCopy */
public class bc {

    /* compiled from: FileCopy */
    public interface a {
        void a(String str, String str2);

        void a(String str, String str2, float f);

        void a(String str, String str2, int i);

        void b(String str, String str2);
    }

    public long a(File file, File file2, long j, long j2, a aVar) {
        Exception e;
        if (j == 0) {
            if (aVar != null) {
                aVar.a("", "", -1);
            }
            return 0;
        }
        long j3;
        String absolutePath = file.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        try {
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                    if (aVar != null && j <= 0) {
                        aVar.a(absolutePath, absolutePath2);
                    }
                    InputStream fileInputStream = new FileInputStream(file);
                    OutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[1024];
                    j3 = j;
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        j = j3 + ((long) read);
                        if (aVar != null) {
                            aVar.a(absolutePath, absolutePath2, a(j, j2));
                            j3 = j;
                        } else {
                            j3 = j;
                        }
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    if (aVar == null || j3 < j2 - 1) {
                        return j3;
                    }
                    aVar.b(absolutePath, absolutePath2);
                    return j3;
                }
                throw new IOException("Cannot create dir " + parentFile.getAbsolutePath());
            } else if (file2.exists() || file2.mkdirs()) {
                String[] list = file.list();
                if (list == null) {
                    return j;
                }
                int i = 0;
                j3 = j;
                while (i < list.length) {
                    try {
                        j3 = a(new File(file, list[i]), new File(new File(file2, file.getName()), list[i]), j3, j2, aVar);
                        i++;
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                return j3;
            } else {
                throw new IOException("Cannot create dir " + file2.getAbsolutePath());
            }
        } catch (Exception e3) {
            e = e3;
            j3 = j;
        }
        e.printStackTrace();
        if (aVar == null) {
            return j3;
        }
        aVar.a(absolutePath, absolutePath2, -1);
        return j3;
    }

    private float a(long j, long j2) {
        return (((float) j) / ((float) j2)) * 100.0f;
    }
}
