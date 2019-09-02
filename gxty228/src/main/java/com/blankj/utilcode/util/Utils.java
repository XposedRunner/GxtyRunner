package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import com.blankj.utilcode.util.PermissionUtils.PermissionActivity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Utils {
    private static final ActivityLifecycleImpl ACTIVITY_LIFECYCLE = new ActivityLifecycleImpl();
    static final AdaptScreenArgs ADAPT_SCREEN_ARGS = new AdaptScreenArgs();
    @SuppressLint({"StaticFieldLeak"})
    private static Application sApplication;

    static class ActivityLifecycleImpl implements ActivityLifecycleCallbacks {
        final LinkedList<Activity> mActivityList = new LinkedList();
        private int mConfigCount = 0;
        private int mForegroundCount = 0;
        final HashMap<Object, OnAppStatusChangedListener> mStatusListenerMap = new HashMap();

        ActivityLifecycleImpl() {
        }

        void addListener(Object obj, OnAppStatusChangedListener onAppStatusChangedListener) {
            this.mStatusListenerMap.put(obj, onAppStatusChangedListener);
        }

        void removeListener(Object obj) {
            this.mStatusListenerMap.remove(obj);
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            setTopActivity(activity);
        }

        public void onActivityStarted(Activity activity) {
            setTopActivity(activity);
            if (this.mForegroundCount <= 0) {
                postStatus(true);
            }
            if (this.mConfigCount < 0) {
                this.mConfigCount++;
            } else {
                this.mForegroundCount++;
            }
        }

        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.mConfigCount--;
                return;
            }
            this.mForegroundCount--;
            if (this.mForegroundCount <= 0) {
                postStatus(false);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
            this.mActivityList.remove(activity);
        }

        private void postStatus(boolean z) {
            if (!this.mStatusListenerMap.isEmpty()) {
                for (OnAppStatusChangedListener onAppStatusChangedListener : this.mStatusListenerMap.values()) {
                    if (onAppStatusChangedListener == null) {
                        return;
                    }
                    if (z) {
                        onAppStatusChangedListener.onForeground();
                    } else {
                        onAppStatusChangedListener.onBackground();
                    }
                }
            }
        }

        private void setTopActivity(Activity activity) {
            if (activity.getClass() != PermissionActivity.class) {
                if (!this.mActivityList.contains(activity)) {
                    this.mActivityList.addLast(activity);
                } else if (!((Activity) this.mActivityList.getLast()).equals(activity)) {
                    this.mActivityList.remove(activity);
                    this.mActivityList.addLast(activity);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        android.app.Activity getTopActivity() {
            /*
            r1 = this;
            r0 = r1.mActivityList;
            r0 = r0.isEmpty();
            if (r0 != 0) goto L_0x0013;
        L_0x0008:
            r0 = r1.mActivityList;
            r0 = r0.getLast();
            r0 = (android.app.Activity) r0;
            if (r0 == 0) goto L_0x0013;
        L_0x0012:
            return r0;
        L_0x0013:
            r0 = r1.getTopActivityByReflect();
            if (r0 == 0) goto L_0x0012;
        L_0x0019:
            r1.setTopActivity(r0);
            goto L_0x0012;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.Utils.ActivityLifecycleImpl.getTopActivity():android.app.Activity");
        }

        private Activity getTopActivityByReflect() {
            try {
                Class cls = Class.forName("android.app.ActivityThread");
                Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mActivityList");
                declaredField.setAccessible(true);
                Map map = (Map) declaredField.get(invoke);
                if (map == null) {
                    return null;
                }
                for (Object invoke2 : map.values()) {
                    Class cls2 = invoke2.getClass();
                    Field declaredField2 = cls2.getDeclaredField("paused");
                    declaredField2.setAccessible(true);
                    if (!declaredField2.getBoolean(invoke2)) {
                        declaredField = cls2.getDeclaredField("activity");
                        declaredField.setAccessible(true);
                        return (Activity) declaredField.get(invoke2);
                    }
                }
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (NoSuchFieldException e5) {
                e5.printStackTrace();
            }
        }
    }

    static class AdaptScreenArgs {
        boolean isVerticalSlide;
        int sizeInPx;

        AdaptScreenArgs() {
        }
    }

    public static final class FileProvider4UtilCode extends FileProvider {
        public boolean onCreate() {
            Utils.init(getContext());
            return true;
        }
    }

    public interface OnAppStatusChangedListener {
        void onBackground();

        void onForeground();
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Context context) {
        if (context == null) {
            init(getApplicationByReflect());
        } else {
            init((Application) context.getApplicationContext());
        }
    }

    public static void init(Application application) {
        if (sApplication == null) {
            if (application == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = application;
            }
            sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
        }
    }

    public static Application getApp() {
        if (sApplication != null) {
            return sApplication;
        }
        Application applicationByReflect = getApplicationByReflect();
        init(applicationByReflect);
        return applicationByReflect;
    }

    private static Application getApplicationByReflect() {
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (invoke != null) {
                return (Application) invoke;
            }
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    static ActivityLifecycleImpl getActivityLifecycle() {
        return ACTIVITY_LIFECYCLE;
    }

    static LinkedList<Activity> getActivityList() {
        return ACTIVITY_LIFECYCLE.mActivityList;
    }

    static Context getTopActivityOrApp() {
        if (!isAppForeground()) {
            return getApp();
        }
        Context topActivity = ACTIVITY_LIFECYCLE.getTopActivity();
        if (topActivity == null) {
            return getApp();
        }
        return topActivity;
    }

    static boolean isAppForeground() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getApp().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100) {
                return runningAppProcessInfo.processName.equals(getApp().getPackageName());
            }
        }
        return false;
    }

    static void restoreAdaptScreen() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics displayMetrics2 = getApp().getResources().getDisplayMetrics();
        Activity topActivity = ACTIVITY_LIFECYCLE.getTopActivity();
        if (topActivity != null) {
            DisplayMetrics displayMetrics3 = topActivity.getResources().getDisplayMetrics();
            if (ADAPT_SCREEN_ARGS.isVerticalSlide) {
                displayMetrics3.density = ((float) displayMetrics3.widthPixels) / ((float) ADAPT_SCREEN_ARGS.sizeInPx);
            } else {
                displayMetrics3.density = ((float) displayMetrics3.heightPixels) / ((float) ADAPT_SCREEN_ARGS.sizeInPx);
            }
            displayMetrics3.scaledDensity = (displayMetrics.scaledDensity / displayMetrics.density) * displayMetrics3.density;
            displayMetrics3.densityDpi = (int) (displayMetrics3.density * 160.0f);
            displayMetrics2.density = displayMetrics3.density;
            displayMetrics2.scaledDensity = displayMetrics3.scaledDensity;
            displayMetrics2.densityDpi = displayMetrics3.densityDpi;
            return;
        }
        if (ADAPT_SCREEN_ARGS.isVerticalSlide) {
            displayMetrics2.density = ((float) displayMetrics2.widthPixels) / ((float) ADAPT_SCREEN_ARGS.sizeInPx);
        } else {
            displayMetrics2.density = ((float) displayMetrics2.heightPixels) / ((float) ADAPT_SCREEN_ARGS.sizeInPx);
        }
        displayMetrics2.scaledDensity = (displayMetrics.scaledDensity / displayMetrics.density) * displayMetrics2.density;
        displayMetrics2.densityDpi = (int) (displayMetrics2.density * 160.0f);
    }

    static void cancelAdaptScreen() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics displayMetrics2 = getApp().getResources().getDisplayMetrics();
        Activity topActivity = ACTIVITY_LIFECYCLE.getTopActivity();
        if (topActivity != null) {
            DisplayMetrics displayMetrics3 = topActivity.getResources().getDisplayMetrics();
            displayMetrics3.density = displayMetrics.density;
            displayMetrics3.scaledDensity = displayMetrics.scaledDensity;
            displayMetrics3.densityDpi = displayMetrics.densityDpi;
        }
        displayMetrics2.density = displayMetrics.density;
        displayMetrics2.scaledDensity = displayMetrics.scaledDensity;
        displayMetrics2.densityDpi = displayMetrics.densityDpi;
    }

    static boolean isAdaptScreen() {
        return Resources.getSystem().getDisplayMetrics().density != getApp().getResources().getDisplayMetrics().density;
    }
}
