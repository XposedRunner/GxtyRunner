package com.blankj.utilcode.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;

public final class FileIOUtils {
    private static int sBufferSize = 8192;

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream) {
        return writeFileFromIS(getFileByPath(str), inputStream, false);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream) {
        return writeFileFromIS(file, inputStream, false);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) {
        OutputStream bufferedOutputStream;
        IOException e;
        Throwable th;
        boolean z2 = false;
        if (createOrExistsFile(file) && inputStream != null) {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
                try {
                    byte[] bArr = new byte[sBufferSize];
                    while (true) {
                        int read = inputStream.read(bArr, 0, sBufferSize);
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

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, false);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, z);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr) {
        return writeFileFromBytesByStream(file, bArr, false);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z) {
        BufferedOutputStream bufferedOutputStream;
        IOException e;
        Throwable th;
        boolean z2 = false;
        if (bArr != null && createOrExistsFile(file)) {
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
                try {
                    bufferedOutputStream.write(bArr);
                    z2 = true;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return z2;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222 = e4;
                bufferedOutputStream = null;
                e222.printStackTrace();
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return z2;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                throw th;
            }
        }
        return z2;
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z, boolean z2) {
        if (bArr == null) {
            return false;
        }
        FileChannel fileChannel = null;
        try {
            fileChannel = new FileOutputStream(file, z).getChannel();
            fileChannel.position(fileChannel.size());
            fileChannel.write(ByteBuffer.wrap(bArr));
            if (z2) {
                fileChannel.force(true);
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            if (fileChannel == null) {
                return false;
            }
            try {
                fileChannel.close();
                return false;
            } catch (IOException e22) {
                e22.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
            }
        }
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(str, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByMap(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z, boolean z2) {
        IOException e;
        Throwable th;
        if (bArr == null || !createOrExistsFile(file)) {
            return false;
        }
        FileChannel fileChannel = null;
        try {
            FileChannel channel = new FileOutputStream(file, z).getChannel();
            try {
                MappedByteBuffer map = channel.map(MapMode.READ_WRITE, channel.size(), (long) bArr.length);
                map.put(bArr);
                if (z2) {
                    map.force();
                }
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            } catch (IOException e3) {
                IOException iOException = e3;
                fileChannel = channel;
                e2 = iOException;
                try {
                    e2.printStackTrace();
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileChannel = channel;
                th = th4;
                if (fileChannel != null) {
                    fileChannel.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            e22 = e4;
            e22.printStackTrace();
            if (fileChannel != null) {
                fileChannel.close();
            }
            return false;
        }
    }

    public static boolean writeFileFromString(String str, String str2) {
        return writeFileFromString(getFileByPath(str), str2, false);
    }

    public static boolean writeFileFromString(String str, String str2, boolean z) {
        return writeFileFromString(getFileByPath(str), str2, z);
    }

    public static boolean writeFileFromString(File file, String str) {
        return writeFileFromString(file, str, false);
    }

    public static boolean writeFileFromString(File file, String str, boolean z) {
        BufferedWriter bufferedWriter;
        IOException e;
        Throwable th;
        boolean z2 = false;
        if (!(file == null || str == null || !createOrExistsFile(file))) {
            BufferedWriter bufferedWriter2 = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, z));
                try {
                    bufferedWriter.write(str);
                    z2 = true;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return z2;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedWriter2 = bufferedWriter;
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222 = e4;
                bufferedWriter = null;
                e222.printStackTrace();
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                return z2;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                }
                throw th;
            }
        }
        return z2;
    }

    public static List<String> readFile2List(String str) {
        return readFile2List(getFileByPath(str), null);
    }

    public static List<String> readFile2List(String str, String str2) {
        return readFile2List(getFileByPath(str), str2);
    }

    public static List<String> readFile2List(File file) {
        return readFile2List(file, 0, Integer.MAX_VALUE, null);
    }

    public static List<String> readFile2List(File file, String str) {
        return readFile2List(file, 0, Integer.MAX_VALUE, str);
    }

    public static List<String> readFile2List(String str, int i, int i2) {
        return readFile2List(getFileByPath(str), i, i2, null);
    }

    public static List<String> readFile2List(String str, int i, int i2, String str2) {
        return readFile2List(getFileByPath(str), i, i2, str2);
    }

    public static List<String> readFile2List(File file, int i, int i2) {
        return readFile2List(file, i, i2, null);
    }

    public static List<String> readFile2List(File file, int i, int i2, String str) {
        IOException e;
        Throwable th;
        if (!isFileExists(file) || i > i2) {
            return null;
        }
        BufferedReader bufferedReader;
        try {
            int i3;
            List<String> arrayList = new ArrayList();
            if (isSpace(str)) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                i3 = 1;
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
                i3 = 1;
            }
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null && i3 <= i2) {
                        if (i <= i3 && i3 <= i2) {
                            arrayList.add(readLine);
                        }
                        i3++;
                    } else if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return arrayList;
        } catch (IOException e4) {
            e = e4;
            bufferedReader = null;
            try {
                e.printStackTrace();
                if (bufferedReader == null) {
                    return null;
                }
                try {
                    bufferedReader.close();
                    return null;
                } catch (IOException e5) {
                    e5.printStackTrace();
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e52) {
                        e52.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public static String readFile2String(String str) {
        return readFile2String(getFileByPath(str), null);
    }

    public static String readFile2String(String str, String str2) {
        return readFile2String(getFileByPath(str), str2);
    }

    public static String readFile2String(File file) {
        return readFile2String(file, null);
    }

    public static String readFile2String(File file, String str) {
        byte[] readFile2BytesByStream = readFile2BytesByStream(file);
        if (readFile2BytesByStream == null) {
            return null;
        }
        if (isSpace(str)) {
            return new String(readFile2BytesByStream);
        }
        try {
            return new String(readFile2BytesByStream, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] readFile2BytesByStream(String str) {
        return readFile2BytesByStream(getFileByPath(str));
    }

    public static byte[] readFile2BytesByStream(File file) {
        byte[] bArr = null;
        if (isFileExists(file)) {
            try {
                bArr = is2Bytes(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }

    public static byte[] readFile2BytesByChannel(String str) {
        return readFile2BytesByChannel(getFileByPath(str));
    }

    public static byte[] readFile2BytesByChannel(File file) {
        FileChannel channel;
        IOException e;
        Throwable th;
        byte[] bArr = null;
        if (isFileExists(file)) {
            try {
                channel = new RandomAccessFile(file, "r").getChannel();
                try {
                    ByteBuffer allocate = ByteBuffer.allocate((int) channel.size());
                    do {
                    } while (channel.read(allocate) > 0);
                    bArr = allocate.array();
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (channel != null) {
                            try {
                                channel.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (channel != null) {
                            try {
                                channel.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e222 = e4;
                channel = null;
                e222.printStackTrace();
                if (channel != null) {
                    channel.close();
                }
                return bArr;
            } catch (Throwable th3) {
                channel = null;
                th = th3;
                if (channel != null) {
                    channel.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    public static byte[] readFile2BytesByMap(String str) {
        return readFile2BytesByMap(getFileByPath(str));
    }

    public static byte[] readFile2BytesByMap(File file) {
        IOException e;
        FileChannel fileChannel;
        Throwable th;
        FileChannel fileChannel2 = null;
        if (!isFileExists(file)) {
            return null;
        }
        try {
            FileChannel channel = new RandomAccessFile(file, "r").getChannel();
            try {
                int size = (int) channel.size();
                byte[] bArr = new byte[size];
                channel.map(MapMode.READ_ONLY, 0, (long) size).load().get(bArr, 0, size);
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return bArr;
            } catch (IOException e3) {
                IOException iOException = e3;
                fileChannel = channel;
                e2 = iOException;
                try {
                    e2.printStackTrace();
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel2 = fileChannel;
                    if (fileChannel2 != null) {
                        try {
                            fileChannel2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                fileChannel2 = channel;
                th = th3;
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            e22 = e4;
            fileChannel = null;
            e22.printStackTrace();
            if (fileChannel != null) {
                fileChannel.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            throw th;
        }
    }

    public static void setBufferSize(int i) {
        sBufferSize = i;
    }

    private static File getFileByPath(String str) {
        return isSpace(str) ? null : new File(str);
    }

    private static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
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

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
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
                    byte[] bArr2 = new byte[sBufferSize];
                    while (true) {
                        int read = inputStream.read(bArr2, 0, sBufferSize);
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
}
