package cn.jpush.android.e.a;

import android.webkit.WebView;
import cn.jpush.android.api.c;

public class b {
    private static final String TAG = "HostJsScope";
    private static f mWebViewHelper;

    public static void setWebViewHelper(f fVar) {
        if (fVar != null) {
            mWebViewHelper = fVar;
        }
    }

    public static void createShortcut(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            mWebViewHelper.createShortcut(str, str2, str3);
        }
    }

    public static void click(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            mWebViewHelper.click(str, str2, str3);
        }
    }

    public static void download(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            mWebViewHelper.download(str, str2, str3);
        }
    }

    public static void startActivityByName(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startActivityByName(str, str2);
        }
    }

    public static void startActivityByIntent(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startActivityByIntent(str, str2);
        }
    }

    public static void triggerNativeAction(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.triggerNativeAction(str);
        }
    }

    public static void startMainActivity(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startMainActivity(str);
        }
    }

    public static void download(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.download(str, str2);
        }
    }

    public static void download(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.download(str);
        }
    }

    public static void close(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.close();
        }
    }

    public static void showToast(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.showToast(str);
        }
    }

    public static void executeMsgMessage(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.executeMsgMessage(str);
        }
    }

    public static void startActivityByNameWithSystemAlert(WebView webView, String str, String str2) {
        if (c.a != null) {
            c.a.a(str, str2);
        }
    }

    public static void showTitleBar(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.showTitleBar();
        }
    }

    public static void startPushActivity(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startPushActivity(str);
        }
    }
}
