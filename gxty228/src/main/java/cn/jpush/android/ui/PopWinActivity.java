package cn.jpush.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.jpush.android.a.d;
import cn.jpush.android.c.h;
import cn.jpush.android.d.a;
import cn.jpush.android.d.e;
import cn.jpush.android.d.i;
import cn.jpush.android.data.b;
import cn.jpush.android.data.g;
import cn.jpush.android.e.a.f;
import java.io.File;

public class PopWinActivity extends Activity {
    public static f a = null;
    private String b;
    private WebView c;
    private b d = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            try {
                if (getIntent().getBooleanExtra("isNotification", false)) {
                    cn.jpush.android.service.b.a();
                    cn.jpush.android.service.b.a(getApplicationContext(), getIntent());
                    finish();
                    return;
                }
                String uri;
                Intent intent = getIntent();
                b bVar = (b) intent.getSerializableExtra("body");
                if (bVar == null) {
                    e.d("PopWinActivity", "parse entity form plugin plateform");
                    if (intent.getData() != null) {
                        uri = intent.getData().toString();
                    } else {
                        uri = null;
                    }
                    if (TextUtils.isEmpty(uri) && intent.getExtras() != null) {
                        uri = intent.getExtras().getString("JMessageExtra");
                    }
                    bVar = h.a(this, uri, "");
                }
                this.d = bVar;
                if (this.d != null) {
                    this.b = this.d.c;
                    int identifier = getResources().getIdentifier("jpush_popwin_layout", "layout", getPackageName());
                    if (identifier == 0) {
                        e.j("PopWinActivity", "Please add layout resource jpush_popwin_layout.xml to res/layout !");
                        finish();
                    } else {
                        setContentView(identifier);
                        identifier = getResources().getIdentifier("wvPopwin", "id", getPackageName());
                        if (identifier == 0) {
                            e.j("PopWinActivity", "Please use default code in jpush_popwin_layout.xml!");
                            finish();
                        } else {
                            this.c = (WebView) findViewById(identifier);
                            if (this.c == null) {
                                e.j("PopWinActivity", "Can not get webView in layout file!");
                                finish();
                            } else {
                                this.c.setScrollbarFadingEnabled(true);
                                this.c.setScrollBarStyle(33554432);
                                WebSettings settings = this.c.getSettings();
                                settings.setDomStorageEnabled(true);
                                a.a(settings);
                                a.a(this.c);
                                settings.setSavePassword(false);
                                this.c.setBackgroundColor(0);
                                a = new f(this, this.d);
                                if (VERSION.SDK_INT >= 17) {
                                    e.d("PopWinActivity", "Android sdk version greater than or equal to 17, Java—Js interact by annotation!");
                                    a();
                                }
                                this.c.setWebChromeClient(new cn.jpush.android.e.a.a("JPushWeb", cn.jpush.android.e.a.b.class, null, null));
                                this.c.setWebViewClient(new a(this.d, this));
                                cn.jpush.android.e.a.b.setWebViewHelper(a);
                            }
                        }
                    }
                    g gVar = (g) this.d;
                    String str = gVar.Q;
                    uri = gVar.a;
                    e.c("PopWinActivity", "showUrl = " + uri);
                    if (TextUtils.isEmpty(str) || !new File(str.replace("file://", "")).exists()) {
                        this.c.loadUrl(uri);
                    } else {
                        this.c.loadUrl(str);
                    }
                    d.a(this.b, 1000, null, this);
                    return;
                }
                e.h("PopWinActivity", "Warning，null message entity! Close PopWinActivity!");
                finish();
                return;
            } catch (Exception e) {
                e.j("PopWinActivity", "Extra data is not serializable!");
                e.printStackTrace();
                finish();
                return;
            }
        }
        e.h("PopWinActivity", "PopWinActivity get NULL intent!");
        finish();
    }

    private void a() {
        r0 = new Object[2];
        Class[] clsArr = new Class[]{a, "JPushWeb"};
        clsArr[0] = Object.class;
        clsArr[1] = String.class;
        try {
            i.a(this.c, "addJavascriptInterface", clsArr, r0);
        } catch (Exception e) {
            e.printStackTrace();
            e.i("PopWinActivity", "addJavascriptInterface failed:" + e.toString());
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        d.a(this.b, PointerIconCompat.TYPE_CELL, null, this);
    }

    protected void onResume() {
        super.onResume();
        if (this.c != null) {
            if (VERSION.SDK_INT >= 11) {
                this.c.onResume();
            }
            cn.jpush.android.e.a.b.setWebViewHelper(a);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.c != null && VERSION.SDK_INT >= 11) {
            this.c.onPause();
        }
    }

    protected void onDestroy() {
        if (this.c != null) {
            this.c.removeAllViews();
            this.c.destroy();
            this.c = null;
        }
        super.onDestroy();
    }

    public final void a(String str) {
        if (this.d != null && this.c != null && (this.d instanceof g)) {
            if (!TextUtils.isEmpty(str)) {
                ((g) this.d).a = str;
                Intent intent = new Intent(this, PushActivity.class);
                intent.putExtra("body", this.d);
                intent.putExtra("from_way", true);
                intent.setFlags(335544320);
                startActivity(intent);
            }
            finish();
        }
    }
}
