package com.example.gita.gxty.weiget.mywebview;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.gita.gxty.utils.h;

public class RLWebView extends WebView {
    private b a;

    class a extends WebChromeClient {
        final /* synthetic */ RLWebView a;

        a(RLWebView rLWebView) {
            this.a = rLWebView;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            return super.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            return super.onJsConfirm(webView, str, str2, jsResult);
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (this.a.a != null) {
                this.a.a.a(i);
            }
        }

        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            super.onReceivedTouchIconUrl(webView, str, z);
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            h.b("onShowCustomView");
            super.onShowCustomView(view, customViewCallback);
        }
    }

    public interface b {
        void a(int i);
    }

    public RLWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RLWebView(Context context) {
        super(context);
    }

    public void a(int i) {
        WebSettings settings = getSettings();
        settings.setCacheMode(i);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(true);
        settings.setAllowFileAccess(true);
        setWebChromeClient(new a(this));
        setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ RLWebView a;

            {
                this.a = r1;
            }

            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                try {
                    webView.loadUrl(webResourceRequest.getUrl().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                try {
                    webView.loadUrl(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    public void setWebviewLoadProgressListener(b bVar) {
        this.a = bVar;
    }
}
