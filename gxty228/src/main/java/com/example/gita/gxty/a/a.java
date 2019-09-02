package com.example.gita.gxty.a;

import android.app.Activity;
import android.content.Context;
import android.support.graphics.drawable.PathInterpolatorCompat;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.model.MedalData;
import com.example.gita.gxty.ram.medal.MedalDetailActivity;
import com.example.gita.gxty.utils.NoAuthException;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.i;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.c;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Response;

/* compiled from: DialogCallback */
public abstract class a<T> extends com.lzy.okgo.b.a<T> {
    private c a;
    private boolean b;
    private Context c;
    private int d;
    private String e;

    public a(Context context, boolean z) {
        this.b = z;
        this.c = context;
    }

    public a(Context context) {
        this.b = true;
        this.c = context;
    }

    public void a(Request<T, ? extends Request> request) {
        super.a((Request) request);
        a(request.getHeaders());
        try {
            if (this.b) {
                if (this.a != null) {
                    if (this.a.isShowing()) {
                        this.a.dismiss();
                    }
                    this.a = null;
                }
                if (!(this.c instanceof Activity)) {
                    this.a = new c(this.c);
                    this.a.show();
                } else if (!((Activity) this.c).isFinishing()) {
                    this.a = new c(this.c);
                    this.a.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(com.lzy.okgo.model.a<T> aVar) {
        super.b(aVar);
        Throwable d = aVar.d();
        h.b(d);
        if (this.c instanceof Activity) {
            Object obj = !((Activity) this.c).isFinishing() ? 1 : null;
        } else {
            int i = 1;
        }
        if (obj == null) {
            return;
        }
        if (d instanceof IllegalStateException) {
            s.a(aVar.d().getMessage());
        } else if (d instanceof NoAuthException) {
            s.a(aVar.d().getMessage());
            MyApplication.e().a(this.c);
        } else {
            s.a((CharSequence) "网络连接失败，请检查网络！");
        }
    }

    public void a(com.lzy.okgo.model.a<T> aVar) {
        if (this.d == PathInterpolatorCompat.MAX_NUM_POINTS && r.b(this.e)) {
            try {
                MedalData medalData = new MedalData();
                medalData.medal_name = "";
                medalData.is_have = "1";
                medalData.medal_id = this.e;
                MedalDetailActivity.a(this.c, medalData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a() {
        super.a();
        try {
            if (this.b && this.a != null) {
                if (this.a.isShowing()) {
                    this.a.dismiss();
                }
                this.a = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(HttpHeaders httpHeaders) {
        httpHeaders.put("uuid", b.b());
        httpHeaders.put("utoken", q.a(MyApplication.e()).c());
        httpHeaders.put(i.a(), i.a(MyApplication.e()));
        h.b("MuGua:" + i.a() + "---" + i.a(MyApplication.e()));
    }

    public T a(Response response) throws Throwable {
        Type[] actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        b bVar = new b();
        bVar.a(actualTypeArguments[0]);
        T a = bVar.a(response);
        this.d = bVar.a();
        this.e = bVar.b();
        response.close();
        return a;
    }
}
