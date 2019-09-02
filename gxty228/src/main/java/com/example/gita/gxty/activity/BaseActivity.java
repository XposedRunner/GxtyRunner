package com.example.gita.gxty.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AppActivity;
import com.baidu.mobads.AppActivity.ActionBarColorTheme;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataCommentLike;
import com.example.gita.gxty.model.DataFeedLike;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.a;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.e;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.l;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.c;
import com.lzy.okgo.request.PostRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class BaseActivity extends AppCompatActivity {
    public static String[] a = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_WIFI_STATE", "android.permission.READ_PHONE_STATE", "android.permission.BLUETOOTH", "android.permission.BLUETOOTH_ADMIN"};
    public static String b = "应用需要位置，电话，存储等权限，被您拒绝或者系统发生错误导致申请失败，请您到设置页面手动授权，否则功能无法正常使用！如果权限已开启，建议全部关闭后重试。";
    public static String c = "应用需要位置，电话,存储等权限，请您到设置页面手动授权，否则功能无法正常使用！";
    private static final Map<String, Boolean> i = new HashMap();
    private static final Map<String, Boolean> j = new HashMap();
    Handler d = new Handler(this) {
        final /* synthetic */ BaseActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1799) {
                if (!this.a.g) {
                    try {
                        s.b((String) message.obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (message.what == 1800) {
                if (!this.a.g) {
                    try {
                        s.a((String) message.obj);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (message.what == 1801) {
                try {
                    if (this.a.h != null) {
                        this.a.h.dismiss();
                        this.a.h = null;
                    }
                    this.a.h = new c(this.a.c());
                    this.a.h.show();
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            } else if (message.what == 1802) {
                try {
                    if (this.a.h != null) {
                        this.a.h.dismiss();
                        this.a.h = null;
                    }
                } catch (Exception e222) {
                    e222.printStackTrace();
                }
            }
        }
    };
    AdView e = null;
    private Typeface f;
    private boolean g = false;
    private c h;

    protected abstract int a();

    public BaseActivity c() {
        return this;
    }

    public static String a(int i) {
        return "p=" + i + "&s=" + 10;
    }

    public static void a(EditText editText) {
        editText.requestFocus();
        Selection.setSelection(editText.getText(), editText.getText().toString().trim().length());
        ((InputMethodManager) editText.getContext().getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public Typeface d() {
        if (this.f == null) {
            this.f = Typeface.createFromAsset(getAssets(), "font/DINOT-CondMedium.ttf");
        }
        return this.f;
    }

    public void a(TitleBar titleBar, String str) {
        if (titleBar != null) {
            titleBar.setLeftImageResource(R.drawable.back_bg);
            titleBar.setTitle((CharSequence) str);
            titleBar.setBackgroundColor(getResources().getColor(R.color.black_zhong));
            titleBar.setTitleSize(17.0f);
            titleBar.setTitleColor(getResources().getColor(R.color.titleColor8));
            titleBar.setLeftClickListener(new OnClickListener(this) {
                final /* synthetic */ BaseActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.finish();
                }
            });
            titleBar.setActionTextColor(Color.parseColor("#4bd9ba"));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.a((Activity) this);
        try {
            if (VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.clearFlags(67108864);
                window.getDecorView().setSystemUiVisibility(1280);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (e()) {
            try {
                getWindow().addFlags(128);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String b = l.a(c()).b();
        if (!"http://gxhttp.chinacloudapp.cn/api/".equals(b)) {
            if ("http://gxtest.wizarcan.com/api/".equals(b)) {
                a("正在使用测试版本");
            } else if ("http://pre.sportcampus.cn/api/".equals(b)) {
                a("正在使用预发布版本");
            }
        }
        try {
            setContentView(a());
            ButterKnife.bind((Activity) this);
        } catch (Exception e22) {
            e22.printStackTrace();
            a("页面加载失败！");
            finish();
        }
    }

    public boolean e() {
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
        g();
        l();
        a.b(this);
    }

    public void a(int i, Bundle bundle) {
        try {
            b(i);
            showDialog(i, bundle);
        } catch (Exception e) {
            h.a(e);
        }
    }

    public void b(int i) {
        try {
            removeDialog(i);
        } catch (Exception e) {
            h.a(e);
        }
    }

    protected void onPause() {
        this.g = true;
        super.onPause();
    }

    protected void onResume() {
        this.g = false;
        super.onResume();
    }

    public void a(String str) {
        if (!this.g) {
            this.d.sendMessage(this.d.obtainMessage(1800, str));
        }
    }

    public void b(String str) {
        if (!this.g) {
            this.d.sendMessage(this.d.obtainMessage(1799, str));
        }
    }

    public void a(String str, com.lzy.okgo.model.a aVar) {
        try {
            n.a(c()).a(str, aVar.f().header("Seq"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void f() {
        this.d.sendEmptyMessage(1801);
    }

    public void g() {
        this.d.sendEmptyMessage(1802);
    }

    public void h() {
        c(0);
    }

    public void c(final int i) {
        f();
        new Thread(this) {
            final /* synthetic */ BaseActivity b;

            public void run() {
                try {
                    File file = new File(this.b.getFilesDir(), "share_img.png");
                    h.a(file.getAbsolutePath());
                    b.a(this.b.c(), file.getAbsolutePath());
                    e.a(this.b.c(), file.getAbsolutePath(), i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.b.g();
            }
        }.start();
    }

    protected void i() {
        try {
            if (VERSION.SDK_INT > 11 && VERSION.SDK_INT < 19) {
                getWindow().getDecorView().setSystemUiVisibility(8);
            } else if (VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(4102);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(BaseActivity baseActivity, String str, int i, TextView textView, ImageView imageView) {
        if (textView != null && imageView != null) {
            boolean z;
            String str2;
            String str3 = "discovery/doLike";
            if (i.containsKey(str) && ((Boolean) i.get(str)).booleanValue()) {
                z = true;
                str2 = "discovery/unLike";
            } else {
                z = false;
                str2 = "discovery/doLike";
            }
            Object dataFeedLike = new DataFeedLike();
            dataFeedLike.feed_id = str;
            DataBean a = b.a(dataFeedLike);
            final TextView textView2 = textView;
            final int i2 = i;
            final ImageView imageView2 = imageView;
            final String str4 = str;
            ((PostRequest) ((PostRequest) ((PostRequest) com.lzy.okgo.a.b(com.example.gita.gxty.a.a(str2)).tag(baseActivity)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Void>>(baseActivity) {
                public void a(com.lzy.okgo.model.a<LzyResponse<Void>> aVar) {
                    super.a((com.lzy.okgo.model.a) aVar);
                    if (z) {
                        textView2.setText("" + i2);
                        imageView2.setImageResource(R.drawable.dis8);
                        BaseActivity.i.put(str4, Boolean.valueOf(false));
                        return;
                    }
                    textView2.setText("" + (i2 + 1));
                    imageView2.setImageResource(R.drawable.dis82);
                    BaseActivity.i.put(str4, Boolean.valueOf(true));
                }
            });
        }
    }

    public static void b(BaseActivity baseActivity, String str, int i, TextView textView, ImageView imageView) {
        if (textView != null && imageView != null) {
            boolean z;
            String str2;
            String str3 = "discovery/doLike";
            if (j.containsKey(str) && ((Boolean) j.get(str)).booleanValue()) {
                z = true;
                str2 = "discovery/unLike";
            } else {
                z = false;
                str2 = "discovery/doLike";
            }
            Object dataCommentLike = new DataCommentLike();
            dataCommentLike.comment_id = str;
            DataBean a = b.a(dataCommentLike);
            final TextView textView2 = textView;
            final int i2 = i;
            final ImageView imageView2 = imageView;
            final String str4 = str;
            ((PostRequest) ((PostRequest) ((PostRequest) com.lzy.okgo.a.b(com.example.gita.gxty.a.a(str2)).tag(baseActivity)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Void>>(baseActivity) {
                public void a(com.lzy.okgo.model.a<LzyResponse<Void>> aVar) {
                    super.a((com.lzy.okgo.model.a) aVar);
                    if (z) {
                        textView2.setText("" + i2);
                        imageView2.setImageResource(R.drawable.dis8);
                        BaseActivity.j.put(str4, Boolean.valueOf(false));
                        return;
                    }
                    textView2.setText("" + (i2 + 1));
                    imageView2.setImageResource(R.drawable.dis82);
                    BaseActivity.j.put(str4, Boolean.valueOf(true));
                }
            });
        }
    }

    public void j() {
        if (n.a(c()).g()) {
            BuglyUtils.a(this, (RelativeLayout) findViewById(R.id.adsLayout), "7020936951832006");
        }
    }

    public void k() {
        try {
            if (VERSION.SDK_INT >= 21) {
                finishAndRemoveTask();
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            s.a((Context) this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            s.a(this, getPackageName());
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        try {
            System.exit(0);
        } catch (Exception e222) {
            e222.printStackTrace();
        }
    }

    public void l() {
        if (this.e != null) {
            this.e.a();
        }
    }

    public void a(RelativeLayout relativeLayout) {
        if (n.a(c()).g()) {
            relativeLayout.setVisibility(0);
            try {
                AppActivity.a(ActionBarColorTheme.ACTION_BAR_WHITE_THEME);
                this.e = new AdView(this, "5927743");
                this.e.setListener(new com.baidu.mobads.a(this) {
                    final /* synthetic */ BaseActivity a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        h.b("onAdSwitch");
                    }

                    public void a(JSONObject jSONObject) {
                        h.b("onAdShow " + jSONObject.toString());
                    }

                    public void a(AdView adView) {
                        h.b("onAdReady " + adView);
                    }

                    public void a(String str) {
                        h.b("onAdFailed " + str);
                    }

                    public void b(JSONObject jSONObject) {
                    }

                    public void c(JSONObject jSONObject) {
                        h.b("onAdClose");
                    }
                });
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                int min = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(min, (min * 3) / 20);
                layoutParams.addRule(12);
                relativeLayout.addView(this.e, layoutParams);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        relativeLayout.setVisibility(8);
    }
}
