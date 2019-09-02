package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public final class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
        Point point = new Point();
        if (VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
        Point point = new Point();
        if (VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static int getScreenDensityDpi() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    public static void setFullScreen(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        activity.getWindow().addFlags(1024);
    }

    public static void setNonFullScreen(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        activity.getWindow().clearFlags(1024);
    }

    public static void toggleFullScreen(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Window window = activity.getWindow();
        if ((window.getAttributes().flags & 1024) == 1024) {
            window.clearFlags(1536);
        } else {
            window.addFlags(1536);
        }
    }

    public static boolean isFullScreen(@NonNull Activity activity) {
        if (activity != null) {
            return (activity.getWindow().getAttributes().flags & 1024) == 1024;
        } else {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public static void setLandscape(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        activity.setRequestedOrientation(0);
    }

    public static void setPortrait(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        activity.setRequestedOrientation(1);
    }

    public static boolean isLandscape() {
        return Utils.getApp().getResources().getConfiguration().orientation == 2;
    }

    public static boolean isPortrait() {
        return Utils.getApp().getResources().getConfiguration().orientation == 1;
    }

    public static int getScreenRotation(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    public static Bitmap screenShot(@NonNull Activity activity) {
        if (activity != null) {
            return screenShot(activity, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap screenShot(@NonNull Activity activity, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.setWillNotCacheDrawing(false);
        Bitmap drawingCache = decorView.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (z) {
            Resources resources = activity.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
            drawingCache = Bitmap.createBitmap(drawingCache, 0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels - dimensionPixelSize);
        } else {
            drawingCache = Bitmap.createBitmap(drawingCache, 0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        decorView.destroyDrawingCache();
        return drawingCache;
    }

    public static boolean isScreenLock() {
        return ((KeyguardManager) Utils.getApp().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    @RequiresPermission("android.permission.WRITE_SETTINGS")
    public static void setSleepDuration(int i) {
        System.putInt(Utils.getApp().getContentResolver(), "screen_off_timeout", i);
    }

    public static int getSleepDuration() {
        try {
            return System.getInt(Utils.getApp().getContentResolver(), "screen_off_timeout");
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
            return -123;
        }
    }

    public static boolean isTablet() {
        return (Utils.getApp().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static void adaptScreen4VerticalSlide(Activity activity, int i) {
        adaptScreen(activity, i, true);
    }

    public static void adaptScreen4HorizontalSlide(Activity activity, int i) {
        adaptScreen(activity, i, false);
    }

    private static void adaptScreen(Activity activity, int i, boolean z) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics displayMetrics2 = Utils.getApp().getResources().getDisplayMetrics();
        DisplayMetrics displayMetrics3 = activity.getResources().getDisplayMetrics();
        if (z) {
            displayMetrics3.density = ((float) displayMetrics3.widthPixels) / ((float) i);
        } else {
            displayMetrics3.density = ((float) displayMetrics3.heightPixels) / ((float) i);
        }
        displayMetrics3.scaledDensity = (displayMetrics.scaledDensity / displayMetrics.density) * displayMetrics3.density;
        displayMetrics3.densityDpi = (int) (160.0f * displayMetrics3.density);
        displayMetrics2.density = displayMetrics3.density;
        displayMetrics2.scaledDensity = displayMetrics3.scaledDensity;
        displayMetrics2.densityDpi = displayMetrics3.densityDpi;
        Utils.ADAPT_SCREEN_ARGS.sizeInPx = i;
        Utils.ADAPT_SCREEN_ARGS.isVerticalSlide = z;
    }

    public static void cancelAdaptScreen(Activity activity) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        DisplayMetrics displayMetrics2 = Utils.getApp().getResources().getDisplayMetrics();
        DisplayMetrics displayMetrics3 = activity.getResources().getDisplayMetrics();
        displayMetrics3.density = displayMetrics.density;
        displayMetrics3.scaledDensity = displayMetrics.scaledDensity;
        displayMetrics3.densityDpi = displayMetrics.densityDpi;
        displayMetrics2.density = displayMetrics.density;
        displayMetrics2.scaledDensity = displayMetrics.scaledDensity;
        displayMetrics2.densityDpi = displayMetrics.densityDpi;
    }

    public static void cancelAdaptScreen() {
        Utils.cancelAdaptScreen();
    }

    public static void restoreAdaptScreen() {
        Utils.restoreAdaptScreen();
    }

    public static boolean isAdaptScreen() {
        return Resources.getSystem().getDisplayMetrics().density != Utils.getApp().getResources().getDisplayMetrics().density;
    }
}
