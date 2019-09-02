package cn.jiguang.a.a.d;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import cn.jiguang.a.a;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.d.g;
import cn.jiguang.e.d;

@TargetApi(14)
public final class b implements ActivityLifecycleCallbacks {
    private static boolean a = false;
    private static int b = 0;

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (a.a != null) {
            a.a.dispatchStatus(activity, "onCreate");
        }
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
        if (a.a != null) {
            a.a.dispatchPause(activity);
        }
        if (!a.b) {
            c.a().b(activity);
        }
    }

    public final void onActivityResumed(Activity activity) {
        if (a.a != null) {
            a.a.dispatchResume(activity);
        }
        if (!a.b) {
            c.a().a((Context) activity);
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        d.c("ActivityLifecycle", "onActivityStarted:" + activity.getClass().getCanonicalName());
        if (b == 0) {
            d.c("ActivityLifecycle", "isForeground");
            if (activity != null) {
                g.a();
                g.b(activity.getApplicationContext());
                JCoreInterface.triggerSceneCheck(activity.getApplicationContext(), 1);
            }
        }
        b++;
        if (a.a != null) {
            a.a.dispatchStatus(activity, "onStart");
        }
    }

    public final void onActivityStopped(Activity activity) {
        d.c("ActivityLifecycle", "onActivityStopped:" + activity.getClass().getCanonicalName());
        int i = b - 1;
        b = i;
        if (i == 0) {
            d.c("ActivityLifecycle", "is not Foreground");
        }
    }
}
