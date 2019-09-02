package cn.jpush.android.e.a;

import android.os.Build.VERSION;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import cn.jpush.android.d.e;

public class c extends WebChromeClient {
    private final String a = "InjectedChromeClient";
    private d b;
    private boolean c;

    public c(String str, Class cls) {
        this.b = new d(str, cls);
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        jsResult.confirm();
        return true;
    }

    public void onProgressChanged(WebView webView, int i) {
        webView.getSettings().setSavePassword(false);
        if (VERSION.SDK_INT < 17) {
            if (i <= 25) {
                this.c = false;
            } else if (!this.c) {
                e.d("InjectedChromeClient", "Android sdk version lesser than 17, Java—Js interact by injection!");
                webView.loadUrl(this.b.a());
                this.c = true;
                e.c("InjectedChromeClient", " inject js interface completely on progress " + i);
            }
        }
        super.onProgressChanged(webView, i);
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (VERSION.SDK_INT < 17) {
            jsPromptResult.confirm(this.b.a(webView, str2));
        }
        return true;
    }
}
