package com.example.gita.gxty.utils;

import android.app.Activity;
import android.content.Context;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataCrash;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Run;
import com.example.gita.gxty.utils.a.a;
import com.example.gita.gxty.utils.a.c;
import com.example.gita.gxty.utils.a.d;
import com.lzy.okgo.request.GetRequest;

/* compiled from: SDUtil */
public class p {
    public static final boolean a(Activity activity) {
        if (d.a(activity)) {
            c(activity, "EEE");
            return true;
        } else if (c.a()) {
            c(activity, "VVV");
            return true;
        } else if (a.a(activity)) {
            c(activity, "HHH");
            return true;
        } else {
            h.b("正常");
            return false;
        }
    }

    private static final void b(Activity activity, String str) {
        q.a((Context) activity).a();
        throw new RuntimeException(str);
    }

    private static void c(final Activity activity, final String str) {
        Object dataCrash = new DataCrash();
        dataCrash.userid = q.a((Context) activity).b();
        dataCrash.detail = i.a(activity);
        dataCrash.type = str;
        DataBean a = b.a(dataCrash);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("webhook/crash")).tag(activity)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Run>>(true, activity) {
            public void a(com.lzy.okgo.model.a<LzyResponse<Run>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                p.b(activity, str);
                h.b("crash请求成功");
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<Run>> aVar) {
                super.b(aVar);
                p.b(activity, str);
                h.b("crash请求失败");
            }
        });
    }
}
