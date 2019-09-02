package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import java.util.List;

public final class ActivityUtils {
    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isActivityExists(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Intent intent = new Intent();
            intent.setClassName(str, str2);
            if (Utils.getApp().getPackageManager().resolveActivity(intent, 0) == null || intent.resolveActivity(Utils.getApp().getPackageManager()) == null || Utils.getApp().getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
                return false;
            }
            return true;
        }
    }

    public static void startActivity(@NonNull Class<? extends Activity> cls) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Context topActivityOrApp = Utils.getTopActivityOrApp();
        startActivity(topActivityOrApp, null, topActivityOrApp.getPackageName(), cls.getName(), null);
    }

    public static void startActivity(@NonNull Class<? extends Activity> cls, Bundle bundle) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Context topActivityOrApp = Utils.getTopActivityOrApp();
        startActivity(topActivityOrApp, null, topActivityOrApp.getPackageName(), cls.getName(), bundle);
    }

    public static void startActivity(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Context topActivityOrApp = Utils.getTopActivityOrApp();
        startActivity(topActivityOrApp, null, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
        if (VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, activity.getPackageName(), cls.getName(), null);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, activity.getPackageName(), cls.getName(), bundle);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, i, i2));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), null);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls, @NonNull Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (bundle2 == null) {
            throw new NullPointerException("Argument 'options' of type Bundle (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), bundle2);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
            if (VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), null);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, @NonNull Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (bundle2 == null) {
            throw new NullPointerException("Argument 'options' of type Bundle (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), bundle2);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, i, i2));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivity(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(Utils.getTopActivityOrApp(), null, str, str2, null);
        }
    }

    public static void startActivity(@NonNull String str, @NonNull String str2, Bundle bundle) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(Utils.getTopActivityOrApp(), null, str, str2, bundle);
        }
    }

    public static void startActivity(@NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, null, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
            if (VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, str, str2, null);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, str, str2, bundle);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, str, str2, getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, null, str, str2, getOptionsBundle(activity, i, i2));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(Utils.getTopActivityOrApp(), bundle, str, str2, null);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(Utils.getTopActivityOrApp(), bundle, str, str2, bundle2);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
            if (VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, str, str2, null);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, str, str2, bundle2);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, str, str2, getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity((Context) activity, bundle, str, str2, getOptionsBundle(activity, i, i2));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivity(@NonNull Intent intent) {
        if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        startActivity(intent, Utils.getTopActivityOrApp(), null);
    }

    public static void startActivity(@NonNull Intent intent, @NonNull Bundle bundle) {
        if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (bundle == null) {
            throw new NullPointerException("Argument 'options' of type Bundle (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(intent, Utils.getTopActivityOrApp(), bundle);
        }
    }

    public static void startActivity(@NonNull Intent intent, @AnimRes int i, @AnimRes int i2) {
        if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Context topActivityOrApp = Utils.getTopActivityOrApp();
        startActivity(intent, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
        if (VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(intent, (Context) activity, null);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(intent, (Context) activity, bundle);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(intent, (Context) activity, getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivity(intent, (Context) activity, getOptionsBundle(activity, i, i2));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, null, activity.getPackageName(), cls.getName(), i, null);
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, null, activity.getPackageName(), cls.getName(), i, bundle);
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, null, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, null, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, i2, i3));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, null);
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @NonNull Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (bundle2 == null) {
            throw new NullPointerException("Argument 'options' of type Bundle (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, bundle2);
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, i2, i3));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, str, str2, i, null);
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, str, str2, i, bundle2);
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, @AnimRes int i2, @AnimRes int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#3 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle(activity, i2, i3));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(intent, activity, i, null);
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(intent, activity, i, bundle);
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(intent, activity, i, getOptionsBundle(activity, viewArr));
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i, @AnimRes int i2, @AnimRes int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intent == null) {
            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivityForResult(intent, activity, i, getOptionsBundle(activity, i2, i3));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
            }
        }
    }

    public static void startActivities(@NonNull Intent[] intentArr) {
        if (intentArr == null) {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        startActivities(intentArr, Utils.getTopActivityOrApp(), null);
    }

    public static void startActivities(@NonNull Intent[] intentArr, Bundle bundle) {
        if (intentArr == null) {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        startActivities(intentArr, Utils.getTopActivityOrApp(), bundle);
    }

    public static void startActivities(@NonNull Intent[] intentArr, @AnimRes int i, @AnimRes int i2) {
        if (intentArr == null) {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Context topActivityOrApp = Utils.getTopActivityOrApp();
        startActivities(intentArr, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
        if (VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
        }
    }

    public static void startActivities(@NonNull Activity activity, @NonNull Intent[] intentArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intentArr == null) {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivities(intentArr, (Context) activity, null);
        }
    }

    public static void startActivities(@NonNull Activity activity, @NonNull Intent[] intentArr, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intentArr == null) {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivities(intentArr, (Context) activity, bundle);
        }
    }

    public static void startActivities(@NonNull Activity activity, @NonNull Intent[] intentArr, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (intentArr == null) {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            startActivities(intentArr, (Context) activity, getOptionsBundle(activity, i, i2));
            if (VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static void startHomeActivity() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
    }

    public static List<Activity> getActivityList() {
        return Utils.getActivityList();
    }

    public static String getLauncherActivity() {
        return getLauncherActivity(Utils.getApp().getPackageName());
    }

    public static String getLauncherActivity(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
        for (ResolveInfo resolveInfo : Utils.getApp().getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.packageName.equals(str)) {
                return resolveInfo.activityInfo.name;
            }
        }
        return "no " + str;
    }

    public static Activity getTopActivity() {
        return Utils.getActivityLifecycle().getTopActivity();
    }

    public static boolean isActivityExistsInStack(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        for (Activity equals : Utils.getActivityList()) {
            if (equals.equals(activity)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isActivityExistsInStack(@NonNull Class<? extends Activity> cls) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        for (Activity activity : Utils.getActivityList()) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public static void finishActivity(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        finishActivity(activity, false);
    }

    public static void finishActivity(@NonNull Activity activity, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        activity.finish();
        if (!z) {
            activity.overridePendingTransition(0, 0);
        }
    }

    public static void finishActivity(@NonNull Activity activity, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        activity.finish();
        activity.overridePendingTransition(i, i2);
    }

    public static void finishActivity(@NonNull Class<? extends Activity> cls) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        finishActivity((Class) cls, false);
    }

    public static void finishActivity(@NonNull Class<? extends Activity> cls, boolean z) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        for (Activity activity : Utils.getActivityList()) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
                if (!z) {
                    activity.overridePendingTransition(0, 0);
                }
            }
        }
    }

    public static void finishActivity(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        for (Activity activity : Utils.getActivityList()) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static boolean finishToActivity(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            return finishToActivity(activity, z, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(@NonNull Activity activity, boolean z, boolean z2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity2 = (Activity) activityList.get(size);
            if (activity2.equals(activity)) {
                if (z) {
                    finishActivity(activity2, z2);
                }
                return true;
            }
            finishActivity(activity2, z2);
        }
        return false;
    }

    public static boolean finishToActivity(@NonNull Activity activity, boolean z, @AnimRes int i, @AnimRes int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity2 = (Activity) activityList.get(size);
            if (activity2.equals(activity)) {
                if (z) {
                    finishActivity(activity2, i, i2);
                }
                return true;
            }
            finishActivity(activity2, i, i2);
        }
        return false;
    }

    public static boolean finishToActivity(@NonNull Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            return finishToActivity((Class) cls, z, false);
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(@NonNull Class<? extends Activity> cls, boolean z, boolean z2) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) activityList.get(size);
            if (activity.getClass().equals(cls)) {
                if (z) {
                    finishActivity(activity, z2);
                }
                return true;
            }
            finishActivity(activity, z2);
        }
        return false;
    }

    public static boolean finishToActivity(@NonNull Class<? extends Activity> cls, boolean z, @AnimRes int i, @AnimRes int i2) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) activityList.get(size);
            if (activity.getClass().equals(cls)) {
                if (z) {
                    finishActivity(activity, i, i2);
                }
                return true;
            }
            finishActivity(activity, i, i2);
        }
        return false;
    }

    public static void finishOtherActivities(@NonNull Class<? extends Activity> cls) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        finishOtherActivities(cls, false);
    }

    public static void finishOtherActivities(@NonNull Class<? extends Activity> cls, boolean z) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) activityList.get(size);
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity, z);
            }
        }
    }

    public static void finishOtherActivities(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) activityList.get(size);
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity, i, i2);
            }
        }
    }

    public static void finishAllActivities() {
        finishAllActivities(false);
    }

    public static void finishAllActivities(boolean z) {
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) activityList.get(size);
            activity.finish();
            if (!z) {
                activity.overridePendingTransition(0, 0);
            }
        }
    }

    public static void finishAllActivities(@AnimRes int i, @AnimRes int i2) {
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) activityList.get(size);
            activity.finish();
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void finishAllActivitiesExceptNewest() {
        finishAllActivitiesExceptNewest(false);
    }

    public static void finishAllActivitiesExceptNewest(boolean z) {
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 2; size >= 0; size--) {
            finishActivity((Activity) activityList.get(size), z);
        }
    }

    public static void finishAllActivitiesExceptNewest(@AnimRes int i, @AnimRes int i2) {
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 2; size >= 0; size--) {
            finishActivity((Activity) activityList.get(size), i, i2);
        }
    }

    public static Drawable getActivityIcon(@NonNull Activity activity) {
        if (activity != null) {
            return getActivityIcon(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityIcon(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            return getActivityIcon(new ComponentName(Utils.getApp(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityIcon(@NonNull ComponentName componentName) {
        if (componentName == null) {
            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        try {
            return Utils.getApp().getPackageManager().getActivityIcon(componentName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable getActivityLogo(@NonNull Activity activity) {
        if (activity != null) {
            return getActivityLogo(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityLogo(@NonNull Class<? extends Activity> cls) {
        if (cls != null) {
            return getActivityLogo(new ComponentName(Utils.getApp(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityLogo(@NonNull ComponentName componentName) {
        if (componentName == null) {
            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        try {
            return Utils.getApp().getPackageManager().getActivityLogo(componentName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void startActivity(Context context, Bundle bundle, String str, String str2, Bundle bundle2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        startActivity(intent, context, bundle2);
    }

    private static void startActivity(Intent intent, Context context, Bundle bundle) {
        if (!(context instanceof Activity)) {
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
        }
        if (bundle == null || VERSION.SDK_INT < 16) {
            context.startActivity(intent);
        } else {
            context.startActivity(intent, bundle);
        }
    }

    private static void startActivityForResult(Activity activity, Bundle bundle, String str, String str2, int i, Bundle bundle2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        startActivityForResult(intent, activity, i, bundle2);
    }

    private static void startActivityForResult(Intent intent, Activity activity, int i, Bundle bundle) {
        if (bundle == null || VERSION.SDK_INT < 16) {
            activity.startActivityForResult(intent, i);
        } else {
            activity.startActivityForResult(intent, i, bundle);
        }
    }

    private static void startActivities(Intent[] intentArr, Context context, Bundle bundle) {
        if (!(context instanceof Activity)) {
            for (Intent addFlags : intentArr) {
                addFlags.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            }
        }
        if (bundle == null || VERSION.SDK_INT < 16) {
            context.startActivities(intentArr);
        } else {
            context.startActivities(intentArr, bundle);
        }
    }

    private static Bundle getOptionsBundle(Context context, int i, int i2) {
        return ActivityOptionsCompat.makeCustomAnimation(context, i, i2).toBundle();
    }

    private static Bundle getOptionsBundle(Activity activity, View[] viewArr) {
        if (VERSION.SDK_INT < 21 || viewArr == null) {
            return null;
        }
        int length = viewArr.length;
        if (length <= 0) {
            return null;
        }
        Pair[] pairArr = new Pair[length];
        for (int i = 0; i < length; i++) {
            pairArr[i] = Pair.create(viewArr[i], viewArr[i].getTransitionName());
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairArr).toBundle();
    }
}
