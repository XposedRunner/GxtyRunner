package com.example.gita.gxty.a;

import android.support.graphics.drawable.PathInterpolatorCompat;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.NoAuthException;
import com.example.gita.gxty.utils.c;
import com.example.gita.gxty.utils.h;
import com.lzy.okgo.c.a;
import com.qq.e.comm.constants.ErrorCode.NetWorkError;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Response;

/* compiled from: JsonConvert */
public class b<T> implements a<T> {
    private Type a;
    private int b;
    private String c;

    public void a(Type type) {
        this.a = type;
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public T a(Response response) throws Throwable {
        String string = response.body().string();
        if (((ParameterizedType) this.a).getRawType() == LzyResponse.class) {
            T t;
            try {
                t = (LzyResponse) c.a(string, this.a);
            } catch (Exception e) {
                h.b(e.getMessage());
                LzyResponse lzyResponse = (LzyResponse) c.a(string, new com.google.gson.b.a<LzyResponse<String>>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }
                }.b());
            }
            if (t != null) {
                this.b = t.code;
                this.c = t.newMedal;
            }
            h.b("http response code：" + this.b);
            if (this.b == 200 || this.b == PathInterpolatorCompat.MAX_NUM_POINTS) {
                return t;
            }
            if (this.b == NetWorkError.RESOURCE_LOAD_FAIL_ERROR) {
                throw new NoAuthException(t.msg);
            } else if (this.b == 104) {
                throw new NoAuthException("用户授权信息无效");
            } else if (this.b == 105) {
                throw new NoAuthException("用户收取信息已过期");
            } else if (this.b == 106) {
                throw new NoAuthException("用户账户被禁用");
            } else if (this.b == 404) {
                throw new IllegalStateException(t.msg);
            } else if (this.b == 300) {
                throw new IllegalStateException("未知错误");
            } else {
                h.b("code : " + this.b + "; msg : " + t.msg);
                throw new IllegalStateException(t.msg);
            }
        }
        throw new IllegalStateException("数据无法解析!");
    }
}
