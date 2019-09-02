package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public final class BarUtils {
    private static final int DEFAULT_ALPHA = 112;
    private static final int KEY_OFFSET = -123;
    private static final String TAG_ALPHA = "TAG_ALPHA";
    private static final String TAG_COLOR = "TAG_COLOR";
    private static final String TAG_OFFSET = "TAG_OFFSET";

    private BarUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getStatusBarHeight() {
        Resources system = Resources.getSystem();
        return system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static void setStatusBarVisibility(@NonNull Activity activity, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarVisibility(activity.getWindow(), z);
    }

    public static void setStatusBarVisibility(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            window.clearFlags(1024);
            showColorView(window);
            showAlphaView(window);
            addMarginTopEqualStatusBarHeight(window);
        } else {
            window.addFlags(1024);
            hideColorView(window);
            hideAlphaView(window);
            subtractMarginTopEqualStatusBarHeight(window);
        }
    }

    public static boolean isStatusBarVisible(@NonNull Activity activity) {
        if (activity != null) {
            return (activity.getWindow().getAttributes().flags & 1024) == 0;
        } else {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public static void setStatusBarLightMode(@NonNull Activity activity, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarLightMode(activity.getWindow(), z);
    }

    public static void setStatusBarLightMode(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 23) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                int systemUiVisibility = decorView.getSystemUiVisibility();
                if (z) {
                    window.addFlags(Integer.MIN_VALUE);
                    systemUiVisibility |= 8192;
                } else {
                    systemUiVisibility &= -8193;
                }
                decorView.setSystemUiVisibility(systemUiVisibility);
            }
        }
    }

    public static void addMarginTopEqualStatusBarHeight(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            view.setTag(TAG_OFFSET);
            Object tag = view.getTag(KEY_OFFSET);
            if (tag == null || !((Boolean) tag).booleanValue()) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                view.setTag(KEY_OFFSET, Boolean.valueOf(true));
            }
        }
    }

    public static void subtractMarginTopEqualStatusBarHeight(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            Object tag = view.getTag(KEY_OFFSET);
            if (tag != null && ((Boolean) tag).booleanValue()) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin - getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                view.setTag(KEY_OFFSET, Boolean.valueOf(false));
            }
        }
    }

    private static void addMarginTopEqualStatusBarHeight(Window window) {
        if (VERSION.SDK_INT >= 19) {
            View findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET);
            if (findViewWithTag != null) {
                addMarginTopEqualStatusBarHeight(findViewWithTag);
            }
        }
    }

    private static void subtractMarginTopEqualStatusBarHeight(Window window) {
        if (VERSION.SDK_INT >= 19) {
            View findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET);
            if (findViewWithTag != null) {
                subtractMarginTopEqualStatusBarHeight(findViewWithTag);
            }
        }
    }

    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarColor(activity, i, 112, false);
    }

    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarColor(activity, i, i2, false);
    }

    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int i, @IntRange(from = 0, to = 255) int i2, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            hideAlphaView(activity);
            transparentStatusBar(activity);
            addStatusBarColor(activity, i, i2, z);
        }
    }

    public static void setStatusBarColor(@NonNull View view, @ColorInt int i) {
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarColor(view, i, 112);
    }

    public static void setStatusBarColor(@NonNull View view, @ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            view.setVisibility(0);
            transparentStatusBar((Activity) view.getContext());
            LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = getStatusBarHeight();
            view.setBackgroundColor(getStatusBarColor(i, i2));
        }
    }

    public static void setStatusBarAlpha(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarAlpha(activity, 112, false);
    }

    public static void setStatusBarAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarAlpha(activity, i, false);
    }

    public static void setStatusBarAlpha(@NonNull Activity activity, @IntRange(from = 0, to = 255) int i, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            hideColorView(activity);
            transparentStatusBar(activity);
            addStatusBarAlpha(activity, i, z);
        }
    }

    public static void setStatusBarAlpha(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setStatusBarAlpha(view, 112);
    }

    public static void setStatusBarAlpha(@NonNull View view, @IntRange(from = 0, to = 255) int i) {
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            view.setVisibility(0);
            transparentStatusBar((Activity) view.getContext());
            LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = getStatusBarHeight();
            view.setBackgroundColor(Color.argb(i, 0, 0, 0));
        }
    }

    public static void setStatusBarColor4Drawer(@NonNull Activity activity, @NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            setStatusBarColor4Drawer(activity, drawerLayout, view, i, 112, z);
        }
    }

    public static void setStatusBarColor4Drawer(@NonNull Activity activity, @NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i, @IntRange(from = 0, to = 255) int i2, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            int i3;
            drawerLayout.setFitsSystemWindows(false);
            transparentStatusBar(activity);
            if (z) {
                i3 = i2;
            } else {
                i3 = 0;
            }
            setStatusBarColor(view, i, i3);
            int childCount = drawerLayout.getChildCount();
            for (i3 = 0; i3 < childCount; i3++) {
                drawerLayout.getChildAt(i3).setFitsSystemWindows(false);
            }
            if (z) {
                hideAlphaView(activity);
            } else {
                addStatusBarAlpha(activity, i2, false);
            }
        }
    }

    public static void setStatusBarAlpha4Drawer(@NonNull Activity activity, @NonNull DrawerLayout drawerLayout, @NonNull View view, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            setStatusBarAlpha4Drawer(activity, drawerLayout, view, 112, z);
        }
    }

    public static void setStatusBarAlpha4Drawer(@NonNull Activity activity, @NonNull DrawerLayout drawerLayout, @NonNull View view, @IntRange(from = 0, to = 255) int i, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT >= 19) {
            int i2;
            drawerLayout.setFitsSystemWindows(false);
            transparentStatusBar(activity);
            if (z) {
                i2 = i;
            } else {
                i2 = 0;
            }
            setStatusBarAlpha(view, i2);
            int childCount = drawerLayout.getChildCount();
            for (i2 = 0; i2 < childCount; i2++) {
                drawerLayout.getChildAt(i2).setFitsSystemWindows(false);
            }
            if (z) {
                hideAlphaView(activity);
            } else {
                addStatusBarAlpha(activity, i, false);
            }
        }
    }

    private static void addStatusBarColor(Activity activity, int i, int i2, boolean z) {
        ViewGroup viewGroup;
        if (z) {
            viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        } else {
            viewGroup = (ViewGroup) activity.findViewById(16908290);
        }
        View findViewWithTag = viewGroup.findViewWithTag(TAG_COLOR);
        if (findViewWithTag != null) {
            if (findViewWithTag.getVisibility() == 8) {
                findViewWithTag.setVisibility(0);
            }
            findViewWithTag.setBackgroundColor(getStatusBarColor(i, i2));
            return;
        }
        viewGroup.addView(createColorStatusBarView(activity, i, i2));
    }

    private static void addStatusBarAlpha(Activity activity, int i, boolean z) {
        ViewGroup viewGroup;
        if (z) {
            viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        } else {
            viewGroup = (ViewGroup) activity.findViewById(16908290);
        }
        View findViewWithTag = viewGroup.findViewWithTag(TAG_ALPHA);
        if (findViewWithTag != null) {
            if (findViewWithTag.getVisibility() == 8) {
                findViewWithTag.setVisibility(0);
            }
            findViewWithTag.setBackgroundColor(Color.argb(i, 0, 0, 0));
            return;
        }
        viewGroup.addView(createAlphaStatusBarView(activity, i));
    }

    private static void hideColorView(Activity activity) {
        hideColorView(activity.getWindow());
    }

    private static void hideAlphaView(Activity activity) {
        hideAlphaView(activity.getWindow());
    }

    private static void hideColorView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_COLOR);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(8);
        }
    }

    private static void hideAlphaView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_ALPHA);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(8);
        }
    }

    private static void showColorView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_COLOR);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(0);
        }
    }

    private static void showAlphaView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_ALPHA);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(0);
        }
    }

    private static int getStatusBarColor(int i, int i2) {
        if (i2 == 0) {
            return i;
        }
        float f = 1.0f - (((float) i2) / 255.0f);
        return Color.argb(255, (int) (((double) (((float) ((i >> 16) & 255)) * f)) + 0.5d), (int) (((double) (((float) ((i >> 8) & 255)) * f)) + 0.5d), (int) (((double) (f * ((float) (i & 255)))) + 0.5d));
    }

    private static View createColorStatusBarView(Context context, int i, int i2) {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight()));
        view.setBackgroundColor(getStatusBarColor(i, i2));
        view.setTag(TAG_COLOR);
        return view;
    }

    private static View createAlphaStatusBarView(Context context, int i) {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight()));
        view.setBackgroundColor(Color.argb(i, 0, 0, 0));
        view.setTag(TAG_ALPHA);
        return view;
    }

    private static void transparentStatusBar(Activity activity) {
        if (VERSION.SDK_INT >= 19) {
            Window window = activity.getWindow();
            if (VERSION.SDK_INT >= 21) {
                window.addFlags(Integer.MIN_VALUE);
                window.getDecorView().setSystemUiVisibility(1280);
                window.setStatusBarColor(0);
                return;
            }
            window.addFlags(67108864);
        }
    }

    public static int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (Utils.getApp().getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, Utils.getApp().getResources().getDisplayMetrics());
        }
        return 0;
    }

    @RequiresPermission("android.permission.EXPAND_STATUS_BAR")
    public static void setNotificationBarVisibility(boolean z) {
        String str = z ? VERSION.SDK_INT <= 16 ? "expand" : "expandNotificationsPanel" : VERSION.SDK_INT <= 16 ? "collapse" : "collapsePanels";
        invokePanels(str);
    }

    private static void invokePanels(String str) {
        try {
            Class.forName("android.app.StatusBarManager").getMethod(str, new Class[0]).invoke(Utils.getApp().getSystemService("statusbar"), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNavBarHeight() {
        Resources system = Resources.getSystem();
        int identifier = system.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return system.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @RequiresApi(api = 19)
    public static void setNavBarVisibility(@NonNull Activity activity, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setNavBarVisibility(activity.getWindow(), z);
    }

    @RequiresApi(19)
    public static void setNavBarVisibility(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View decorView = window.getDecorView();
        if (z) {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & -4611);
        } else {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 4610);
        }
    }

    public static boolean isNavBarVisible(@NonNull Activity activity) {
        if (activity != null) {
            return isNavBarVisible(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isNavBarVisible(@NonNull Window window) {
        if (window != null) {
            return (window.getDecorView().getSystemUiVisibility() & 2) == 0;
        } else {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Activity activity, @ColorInt int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        setNavBarColor(activity.getWindow(), i);
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Window window, @ColorInt int i) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        window.setNavigationBarColor(i);
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Activity activity) {
        if (activity != null) {
            return getNavBarColor(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Window window) {
        if (window != null) {
            return window.getNavigationBarColor();
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isSupportNavBar() {
        boolean z = true;
        if (VERSION.SDK_INT >= 17) {
            Display defaultDisplay = ((WindowManager) Utils.getApp().getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y == point.y && point2.x == point.x) {
                return false;
            }
            return true;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(Utils.getApp()).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            z = false;
        }
        return z;
    }
}
