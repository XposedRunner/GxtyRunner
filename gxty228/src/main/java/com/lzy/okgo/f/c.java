package com.lzy.okgo.f;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/* compiled from: IOUtils */
public class c {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                d.a(e);
            }
        }
    }

    public static byte[] a(Object obj) {
        Closeable byteArrayOutputStream;
        Closeable objectOutputStream;
        Throwable e;
        Throwable th;
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (IOException e2) {
                e = e2;
                objectOutputStream = bArr;
                try {
                    d.a(e);
                    a(objectOutputStream);
                    a(byteArrayOutputStream);
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    a(objectOutputStream);
                    a(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable e3) {
                objectOutputStream = bArr;
                th = e3;
                a(objectOutputStream);
                a(byteArrayOutputStream);
                throw th;
            }
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
                a(objectOutputStream);
                a(byteArrayOutputStream);
            } catch (IOException e4) {
                e3 = e4;
                d.a(e3);
                a(objectOutputStream);
                a(byteArrayOutputStream);
                return bArr;
            }
        } catch (IOException e5) {
            e3 = e5;
            objectOutputStream = bArr;
            byteArrayOutputStream = bArr;
            d.a(e3);
            a(objectOutputStream);
            a(byteArrayOutputStream);
            return bArr;
        } catch (Throwable e32) {
            objectOutputStream = bArr;
            byteArrayOutputStream = bArr;
            th = e32;
            a(objectOutputStream);
            a(byteArrayOutputStream);
            throw th;
        }
        return bArr;
    }

    public static Object a(byte[] bArr) {
        Closeable byteArrayInputStream;
        Closeable objectInputStream;
        Object readObject;
        Throwable e;
        Throwable th;
        Closeable closeable = null;
        if (bArr != null) {
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        readObject = objectInputStream.readObject();
                        a(objectInputStream);
                        a(byteArrayInputStream);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            d.a(e);
                            a(objectInputStream);
                            a(byteArrayInputStream);
                            return readObject;
                        } catch (Throwable th2) {
                            th = th2;
                            a(objectInputStream);
                            a(byteArrayInputStream);
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    objectInputStream = closeable;
                    d.a(e);
                    a(objectInputStream);
                    a(byteArrayInputStream);
                    return readObject;
                } catch (Throwable e4) {
                    objectInputStream = closeable;
                    th = e4;
                    a(objectInputStream);
                    a(byteArrayInputStream);
                    throw th;
                }
            } catch (Exception e5) {
                e4 = e5;
                objectInputStream = closeable;
                byteArrayInputStream = closeable;
                d.a(e4);
                a(objectInputStream);
                a(byteArrayInputStream);
                return readObject;
            } catch (Throwable e42) {
                objectInputStream = closeable;
                byteArrayInputStream = closeable;
                th = e42;
                a(objectInputStream);
                a(byteArrayInputStream);
                throw th;
            }
        }
        return readObject;
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
