package cn.jpush.android.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v4.view.PointerIconCompat;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.jiguang.net.HttpUtils;
import cn.jpush.android.a.d;
import cn.jpush.android.d.e;
import cn.jpush.android.data.b;
import java.util.Locale;

public final class a extends WebViewClient {
    private final b a;
    private final Context b;
    private boolean c = false;

    public a(b bVar, Context context) {
        this.a = bVar;
        this.b = context;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Context context = webView.getContext();
        e.e("JPushWebViewClient", "Url vaule is :" + str);
        try {
            webView.getSettings().setSavePassword(false);
            cn.jpush.android.d.a.a(webView);
            String format = String.format(Locale.ENGLISH, "{\"url\":\"%s\"}", new Object[]{str});
            if (this.a.F) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                d.a(this.a.c, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, format, cn.jpush.android.a.e);
                return true;
            } else if (str.endsWith(".mp3")) {
                r1 = new Intent("android.intent.action.VIEW");
                r1.setDataAndType(Uri.parse(str), "audio/*");
                webView.getContext().startActivity(r1);
                return true;
            } else if (str.endsWith(".mp4") || str.endsWith(".3gp")) {
                r1 = new Intent("android.intent.action.VIEW");
                r1.setDataAndType(Uri.parse(str), "video/*");
                webView.getContext().startActivity(r1);
                return true;
            } else if (str.endsWith(".apk")) {
                webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else {
                if (str.startsWith("http")) {
                    d.a(this.a.c, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, format, cn.jpush.android.a.e);
                } else if (str.startsWith("mailto")) {
                    if (str.lastIndexOf("direct=") < 0 && !str.startsWith("mailto")) {
                        if (str.indexOf(HttpUtils.URL_AND_PARA_SEPARATOR) > 0) {
                            str = str + "&direct=false";
                        } else {
                            str = str + "?direct=false";
                        }
                        str.lastIndexOf("direct=");
                    }
                    int indexOf = str.indexOf(HttpUtils.URL_AND_PARA_SEPARATOR);
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf);
                    e.a("JPushWebViewClient", "Uri: " + substring);
                    e.a("JPushWebViewClient", "QueryString: " + substring2);
                    r1 = null;
                    if (substring.startsWith("mailto")) {
                        if (substring.split(":").length == 2) {
                            indexOf = substring2.indexOf("title=") + 6;
                            int indexOf2 = substring2.indexOf("&content=");
                            String substring3 = substring2.substring(indexOf, indexOf2);
                            substring2 = substring2.substring(indexOf2 + 9);
                            String[] strArr = new String[]{r4[1]};
                            r1 = new Intent("android.intent.action.SEND");
                            r1.setType("plain/text");
                            r1.putExtra("android.intent.extra.EMAIL", strArr);
                            r1.putExtra("android.intent.extra.SUBJECT", substring3);
                            r1.putExtra("android.intent.extra.TEXT", substring2);
                        }
                    }
                    if (r1 != null) {
                        context.startActivity(r1);
                    }
                    d.a(this.a.c, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, format, cn.jpush.android.a.e);
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            e.i("JPushWebViewClient", "Invalid url");
            return true;
        }
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    public final void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.c) {
            sslErrorHandler.proceed();
        } else if (this.b == null || this.b.getClass().isAssignableFrom(Activity.class)) {
            sslErrorHandler.cancel();
        } else {
            try {
                Builder builder = new Builder(this.b);
                builder.setTitle("提示");
                builder.setMessage("SSL 证书异常，是否继续加载？");
                builder.setNegativeButton("否", new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        sslErrorHandler.cancel();
                    }
                });
                builder.setPositiveButton("是", new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        this.b.c = true;
                        sslErrorHandler.proceed();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            } catch (Throwable th) {
                sslErrorHandler.cancel();
                e.g("JPushWebViewClient", "show dialog error:" + th);
            }
        }
    }
}
