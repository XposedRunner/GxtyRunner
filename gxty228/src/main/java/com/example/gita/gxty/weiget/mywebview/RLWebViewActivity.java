package com.example.gita.gxty.weiget.mywebview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.a;
import com.example.gita.gxty.weiget.mywebview.RLWebView.b;

public class RLWebViewActivity extends BaseActivity {
    private RLWebView f;

    public static void a(Activity activity, String str, String str2) {
        if (str == null || "".equals(str)) {
            h.b("无效的 Url");
            return;
        }
        Intent intent = new Intent(activity, RLWebViewActivity.class);
        intent.putExtra("data", str);
        intent.putExtra("title", str2);
        activity.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
            a(titleBar, getIntent().getStringExtra("title"));
            titleBar.a(new a(this) {
                final /* synthetic */ RLWebViewActivity a;

                {
                    this.a = r1;
                }

                public String a() {
                    return "浏览器";
                }

                public int b() {
                    return 0;
                }

                public void a(View view) {
                    RLWebViewActivity.a(this.a.c(), this.a.f.getUrl());
                }
            });
            this.f = (RLWebView) findViewById(R.id.webview);
            this.f.setBackgroundColor(0);
            this.f.a(-1);
            this.f.setWebviewLoadProgressListener(new b(this) {
                final /* synthetic */ RLWebViewActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    h.b(Integer.valueOf(i));
                }
            });
            String stringExtra = getIntent().getStringExtra("data");
            if (stringExtra.startsWith("http://") || stringExtra.startsWith("https://")) {
                this.f.loadUrl(stringExtra);
            } else {
                this.f.loadData(stringExtra, "text/html; charset=UTF-8", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    public static void a(Activity activity, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_rlwebview;
    }
}
