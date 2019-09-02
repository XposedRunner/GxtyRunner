package cn.jpush.android.e.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.a.d;
import cn.jpush.android.a.g;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.d.a;
import cn.jpush.android.d.e;
import cn.jpush.android.data.b;
import cn.jpush.android.ui.PopWinActivity;
import cn.jpush.android.ui.PushActivity;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import java.lang.ref.WeakReference;

public final class f {
    private final WeakReference<Activity> a;
    private final b b;

    public f(Context context, b bVar) {
        this.a = new WeakReference((Activity) context);
        this.b = bVar;
    }

    @JavascriptInterface
    public final void createShortcut(String str, String str2, String str3) {
        int parseInt;
        int i = 0;
        try {
            parseInt = Integer.parseInt(str3);
        } catch (Exception e) {
            e.c("WebViewHelper", "iconId should be int - " + str3);
            parseInt = i;
        }
        if (this.a.get() != null) {
            e.c("WebViewHelper", "Web callback:createShortcut - name:" + str + ", url:" + str2);
            Context context = (Context) this.a.get();
            parseInt = cn.jpush.android.api.b.a(parseInt);
            Uri parse = Uri.parse(str2);
            if (parse == null) {
                e.c("AndroidUtil", "Unexpected: invalid url - " + str2);
                return;
            }
            Parcelable intent = new Intent("android.intent.action.VIEW", parse);
            intent.setFlags(335544320);
            try {
                Parcelable fromContext = ShortcutIconResource.fromContext(context, parseInt);
                Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                intent2.putExtra("duplicate", false);
                intent2.putExtra("android.intent.extra.shortcut.NAME", str);
                intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
                intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", fromContext);
                intent2.setPackage(context.getPackageName());
                context.sendBroadcast(intent2);
            } catch (Throwable th) {
                e.h("AndroidUtil", "createShortCut error:" + th.getMessage());
            }
        }
    }

    @JavascriptInterface
    public final void click(String str, String str2, String str3) {
        boolean z;
        if (this.a.get() != null) {
            boolean parseBoolean;
            e.c("WebViewHelper", "Web callback:click - actionId:" + str + ", shouldClose:" + str2 + ", shouldCancelNotification:" + str3);
            userClick(str);
            try {
                parseBoolean = Boolean.parseBoolean(str2);
                try {
                    z = parseBoolean;
                    parseBoolean = Boolean.parseBoolean(str3);
                } catch (Exception e) {
                    z = parseBoolean;
                    parseBoolean = false;
                    if (parseBoolean) {
                        cn.jpush.android.api.b.a((Context) this.a.get(), this.b, 0);
                    }
                    if (!z) {
                        ((Activity) this.a.get()).finish();
                    }
                }
            } catch (Exception e2) {
                parseBoolean = false;
                z = parseBoolean;
                parseBoolean = false;
                if (parseBoolean) {
                    cn.jpush.android.api.b.a((Context) this.a.get(), this.b, 0);
                }
                if (!z) {
                    ((Activity) this.a.get()).finish();
                }
            }
            if (parseBoolean) {
                cn.jpush.android.api.b.a((Context) this.a.get(), this.b, 0);
            }
            if (!z) {
                ((Activity) this.a.get()).finish();
            }
        }
    }

    @JavascriptInterface
    private void userClick(String str) {
        int parseInt;
        int i = 1100;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Exception e) {
            e.i("WebViewHelper", "Invalid actionId from Web - " + str);
            parseInt = i;
        }
        d.a(this.b.c, parseInt, null, (Context) this.a.get());
    }

    @JavascriptInterface
    public final void download(String str, String str2, String str3) {
        e.a("WebViewHelper", "msgType from web: " + str3);
        download(str, str2);
    }

    @JavascriptInterface
    public final void startActivityByName(String str, String str2) {
        e.c("WebViewHelper", "activityName = " + str);
        if (TextUtils.isEmpty(str)) {
            e.j("WebViewHelper", "The activity name is null or empty, Give up..");
        }
        Context context = (Context) this.a.get();
        if (context != null) {
            try {
                Class cls = Class.forName(str);
                if (cls != null) {
                    Intent intent = new Intent(context, cls);
                    intent.putExtra(JPushInterface.EXTRA_ACTIVITY_PARAM, str2);
                    intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                e.j("WebViewHelper", "The activity name is invalid, Give up..");
            }
        }
    }

    @JavascriptInterface
    public final void startActivityByIntent(String str, String str2) {
        Context context = (Context) this.a.get();
        if (context != null) {
            try {
                Intent intent = new Intent(str);
                intent.addCategory(context.getPackageName());
                intent.putExtra(JPushInterface.EXTRA_EXTRA, str2);
                intent.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
                context.startActivity(intent);
            } catch (Exception e) {
                e.j("WebViewHelper", "Unhandle intent : " + str);
            }
        }
    }

    @JavascriptInterface
    public final void triggerNativeAction(String str) {
        Context context = (Context) this.a.get();
        if (context != null) {
            String str2 = JPushInterface.ACTION_RICHPUSH_CALLBACK;
            String str3 = JPushInterface.EXTRA_EXTRA;
            Bundle bundle = new Bundle();
            if (str3 != null) {
                bundle.putString(str3, str);
            }
            a.a(context, str2, bundle);
        }
    }

    @JavascriptInterface
    public final void startMainActivity(String str) {
        Context context = (Context) this.a.get();
        if (context != null) {
            try {
                ((Activity) context).finish();
                a.b(context);
            } catch (Exception e) {
                e.j("WebViewHelper", "startMainActivity failed");
            }
        }
    }

    @JavascriptInterface
    public final void download(String str, String str2) {
        if (this.a.get() != null) {
            userClick(str);
            download(str2);
            cn.jpush.android.api.b.a((Context) this.a.get(), this.b, 0);
            ((Activity) this.a.get()).finish();
        }
    }

    @JavascriptInterface
    public final void download(String str) {
        if (this.a.get() != null) {
            e.c("WebViewHelper", "Web callback:download - " + str);
        }
    }

    @JavascriptInterface
    public final void close() {
        if (this.a.get() != null) {
            e.c("WebViewHelper", "Web callback:close");
            ((Activity) this.a.get()).finish();
        }
    }

    @JavascriptInterface
    public final void showToast(String str) {
        if (this.a.get() != null) {
            e.c("WebViewHelper", "Web callback:showToast - " + str);
            Toast.makeText((Context) this.a.get(), str, 0).show();
        }
    }

    @JavascriptInterface
    public final void executeMsgMessage(String str) {
        if (JCoreInterface.getDebugMode()) {
            e.c("WebViewHelper", "Web callback:executeMsgMessage - " + str);
            if (this.a.get() != null) {
                g.a((Context) this.a.get(), str);
            }
        }
    }

    @JavascriptInterface
    public final void showTitleBar() {
        if (this.a.get() != null && (this.a.get() instanceof PushActivity)) {
            ((PushActivity) this.a.get()).a();
        }
    }

    @JavascriptInterface
    public final void startPushActivity(String str) {
        if (this.a.get() != null && (this.a.get() instanceof PopWinActivity)) {
            ((PopWinActivity) this.a.get()).a(str);
        }
    }
}
