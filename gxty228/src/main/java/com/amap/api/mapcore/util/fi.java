package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMapException;
import com.tencent.bugly.BuglyStrategy.a;

/* compiled from: AbstractBasicHandler */
public abstract class fi<T, V> extends dl {
    protected T b;
    protected int c = 1;
    protected Context d;
    protected String e;

    protected abstract V c(String str) throws gh;

    public fi(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.d = context;
        this.b = t;
        this.c = 1;
        b(a.MAX_USERDATA_VALUE_LENGTH);
        a((int) a.MAX_USERDATA_VALUE_LENGTH);
    }

    protected V b(byte[] bArr) throws gh {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        if (str == null || "".equals(str)) {
            return null;
        }
        fk.a(str, this.e);
        return c(str);
    }

    public V e() throws gh {
        if (this.b != null) {
            return g();
        }
        return null;
    }

    private V g() throws gh {
        V v = null;
        int i = 0;
        while (i < this.c) {
            try {
                a(gg.a(this.d));
                v = a(d());
                i = this.c;
            } catch (gp e) {
                i++;
                if (i < this.c) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        if (AMapException.ERROR_CONNECTION.equals(e.getMessage()) || AMapException.ERROR_SOCKET.equals(e.getMessage()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e.getMessage())) {
                            throw new gh("http或socket连接失败 - ConnectionException");
                        }
                        throw new gh(e.a());
                    }
                }
                f();
                if (AMapException.ERROR_CONNECTION.equals(e.getMessage()) || AMapException.ERROR_SOCKET.equals(e.getMessage()) || AMapException.ERROR_UNKNOWN.equals(e.a()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e.getMessage())) {
                    throw new gh("http或socket连接失败 - ConnectionException");
                }
                throw new gh(e.a());
            } catch (gh e3) {
                i++;
                if (i >= this.c) {
                    throw new gh(e3.a());
                }
            } catch (Throwable th) {
                gh ghVar = new gh("未知错误");
            }
        }
        return v;
    }

    private V a(byte[] bArr) throws gh {
        return b(bArr);
    }

    protected V f() {
        return null;
    }
}
