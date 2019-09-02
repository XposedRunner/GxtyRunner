package cn.jiguang.api.utils;

import cn.jiguang.api.JResponse;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ProtocolUtil {
    public static void fillIntData(byte[] bArr, int i, int i2) {
        System.arraycopy(int2ByteArray(i), 0, bArr, i2, 4);
    }

    public static void fillStringData(byte[] bArr, String str, int i) {
        Object bytes = str.getBytes();
        System.arraycopy(bytes, 0, bArr, i, bytes.length);
    }

    public static byte[] fixedStringToBytes(String str, int i) {
        if (str == null || "".equals(str)) {
            return new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        }
        Object bytes;
        Object obj = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bytes = obj;
        }
        if (bytes == null) {
            return new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        }
        obj = getDefaultByte(i);
        if (bytes.length <= i) {
            i = bytes.length;
        }
        System.arraycopy(bytes, 0, obj, 0, i);
        return obj;
    }

    public static byte[] getBytes(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.asReadOnlyBuffer().flip();
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException e) {
            d.g("ProtocolUtil", "[getBytes] - ByteBuffer error.");
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public static byte[] getBytesConsumed(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException e) {
            d.g("ProtocolUtil", "[getBytesConsumed] - ByteBuffer error.");
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public static byte[] getDefaultByte(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[0] = (byte) 0;
        }
        return bArr;
    }

    public static String getString(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getTlv2(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.getShort()];
            byteBuffer.get(bArr);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        } catch (Exception e2) {
        }
        return null;
    }

    public static String getTlv2(ByteBuffer byteBuffer, JResponse jResponse) {
        short s = ByteBufferUtils.getShort(byteBuffer, jResponse);
        if (s < (short) 0) {
            d.g("ProtocolUtil", "[getTlv2] - ByteBuffer error.");
            return null;
        }
        byte[] bArr = new byte[s];
        ByteBufferUtils.get(byteBuffer, bArr, jResponse);
        try {
            return new String(bArr, "UTF-8");
        } catch (Throwable th) {
            return null;
        }
    }

    public static byte[] int2ByteArray(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static byte[] long2ByteArray(long j) {
        return new byte[]{(byte) ((int) (j >>> 56)), (byte) ((int) (j >>> 48)), (byte) ((int) (j >>> 40)), (byte) ((int) (j >>> 32)), (byte) ((int) (j >>> 24)), (byte) ((int) (j >>> 16)), (byte) ((int) (j >>> 8)), (byte) ((int) j)};
    }

    public static void main(String[] strArr) {
        d.c("ProtocolUtil", k.a(fixedStringToBytes("ab", 4)));
    }

    public static byte[] short2ByteArray(short s) {
        return new byte[]{(byte) (s >>> 8), (byte) s};
    }

    public static byte[] tlv2ToByteArray(String str) {
        if (str == null || "".equals(str)) {
            return new byte[]{(byte) 0, (byte) 0};
        }
        Object bytes;
        Object obj = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bytes = obj;
        }
        if (bytes == null) {
            return new byte[]{(byte) 0, (byte) 0};
        }
        short length = (short) bytes.length;
        obj = new byte[(length + 2)];
        System.arraycopy(short2ByteArray(length), 0, obj, 0, 2);
        System.arraycopy(bytes, 0, obj, 2, length);
        return obj;
    }
}
