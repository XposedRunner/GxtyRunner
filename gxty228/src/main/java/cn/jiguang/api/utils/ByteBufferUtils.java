package cn.jiguang.api.utils;

import cn.jiguang.api.JResponse;
import cn.jiguang.e.d;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class ByteBufferUtils {
    public static final int ERROR_CODE = 10000;

    private static String a(Throwable th, JResponse jResponse, ByteBuffer byteBuffer) {
        StringBuilder stringBuilder = new StringBuilder();
        if (jResponse != null) {
            stringBuilder.append(jResponse.toString());
            stringBuilder.append("|bytebuffer:" + (byteBuffer == null ? "byteBuffer is null" : byteBuffer.toString()));
        }
        d.j("ByteBufferUtils", "byteBuffer info:" + stringBuilder.toString());
        try {
            Writer stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            d.j("ByteBufferUtils", "parse data error stackTrace:" + stringWriter.toString());
        } catch (Exception e) {
        }
        return stringBuilder.toString();
    }

    public static Byte get(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return Byte.valueOf(byteBuffer.get());
        } catch (BufferUnderflowException e) {
            a(e.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return null;
        } catch (BufferOverflowException e2) {
            a(e2.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return null;
        } catch (Exception e3) {
            a(e3.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return null;
        }
    }

    public static ByteBuffer get(ByteBuffer byteBuffer, byte[] bArr, JResponse jResponse) {
        try {
            return byteBuffer.get(bArr);
        } catch (BufferUnderflowException e) {
            a(e.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return null;
        } catch (BufferOverflowException e2) {
            a(e2.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return null;
        } catch (Exception e3) {
            a(e3.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return null;
        }
    }

    public static int getInt(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getInt();
        } catch (BufferUnderflowException e) {
            a(e.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return -1;
        } catch (BufferOverflowException e2) {
            a(e2.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return -1;
        } catch (Exception e3) {
            a(e3.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return -1;
        }
    }

    public static long getLong(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getLong();
        } catch (BufferUnderflowException e) {
            a(e.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return 0;
        } catch (BufferOverflowException e2) {
            a(e2.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return 0;
        } catch (Exception e3) {
            a(e3.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return 0;
        }
    }

    public static short getShort(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getShort();
        } catch (BufferUnderflowException e) {
            a(e.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return (short) -1;
        } catch (BufferOverflowException e2) {
            a(e2.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return (short) -1;
        } catch (Exception e3) {
            a(e3.fillInStackTrace(), jResponse, byteBuffer);
            if (jResponse != null) {
                jResponse.code = ERROR_CODE;
            }
            return (short) -1;
        }
    }
}
