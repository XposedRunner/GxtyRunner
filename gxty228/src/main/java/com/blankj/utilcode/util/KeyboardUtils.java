package com.blankj.utilcode.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import java.lang.reflect.Field;

public final class KeyboardUtils {
    private static OnGlobalLayoutListener onGlobalLayoutListener;
    private static OnSoftInputChangedListener onSoftInputChangedListener;
    private static int sContentViewInvisibleHeightPre5497;
    private static int sDecorViewDelta = 0;
    private static int sDecorViewInvisibleHeightPre;

    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i);
    }

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void showSoftInput(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager != null) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(activity);
                currentFocus.setFocusable(true);
                currentFocus.setFocusableInTouchMode(true);
                currentFocus.requestFocus();
            }
            inputMethodManager.showSoftInput(currentFocus, 2);
        }
    }

    public static void showSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 2);
    }

    public static void showSoftInputUsingToggle(Activity activity) {
        if (!isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
    }

    public static void hideSoftInput(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager != null) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(activity);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static void hideSoftInput(View view) {
        ((InputMethodManager) Utils.getApp().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftInputUsingToggle(Activity activity) {
        if (isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
    }

    public static void toggleSoftInput() {
        ((InputMethodManager) Utils.getApp().getSystemService("input_method")).toggleSoftInput(2, 0);
    }

    public static boolean isSoftInputVisible(Activity activity) {
        return getDecorViewInvisibleHeight(activity) > 0;
    }

    private static int getDecorViewInvisibleHeight(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView == null) {
            return sDecorViewInvisibleHeightPre;
        }
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
        int abs = Math.abs(decorView.getBottom() - rect.bottom);
        if (abs > getNavBarHeight()) {
            return abs - sDecorViewDelta;
        }
        sDecorViewDelta = abs;
        return 0;
    }

    public static void registerSoftInputChangedListener(final Activity activity, OnSoftInputChangedListener onSoftInputChangedListener) {
        if ((activity.getWindow().getAttributes().flags & 512) != 0) {
            activity.getWindow().clearFlags(512);
        }
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(16908290);
        sDecorViewInvisibleHeightPre = getDecorViewInvisibleHeight(activity);
        onSoftInputChangedListener = onSoftInputChangedListener;
        onGlobalLayoutListener = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (KeyboardUtils.onSoftInputChangedListener != null) {
                    int access$100 = KeyboardUtils.getDecorViewInvisibleHeight(activity);
                    if (KeyboardUtils.sDecorViewInvisibleHeightPre != access$100) {
                        KeyboardUtils.onSoftInputChangedListener.onSoftInputChanged(access$100);
                        KeyboardUtils.sDecorViewInvisibleHeightPre = access$100;
                    }
                }
            }
        };
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    @TargetApi(16)
    public static void unregisterSoftInputChangedListener(Activity activity) {
        activity.findViewById(16908290).getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        onSoftInputChangedListener = null;
        onGlobalLayoutListener = null;
    }

    public static void fixAndroidBug5497(final Activity activity) {
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(16908290);
        final View childAt = frameLayout.getChildAt(0);
        final int paddingBottom = childAt.getPaddingBottom();
        sContentViewInvisibleHeightPre5497 = getContentViewInvisibleHeight(activity);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int access$300 = KeyboardUtils.getContentViewInvisibleHeight(activity);
                if (KeyboardUtils.sContentViewInvisibleHeightPre5497 != access$300) {
                    childAt.setPadding(childAt.getPaddingLeft(), childAt.getPaddingTop(), childAt.getPaddingRight(), paddingBottom + KeyboardUtils.getDecorViewInvisibleHeight(activity));
                    KeyboardUtils.sContentViewInvisibleHeightPre5497 = access$300;
                }
            }
        });
    }

    private static int getContentViewInvisibleHeight(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById == null) {
            return sContentViewInvisibleHeightPre5497;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getContentViewInvisibleHeight: " + (findViewById.getBottom() - rect.bottom));
        int abs = Math.abs(findViewById.getBottom() - rect.bottom);
        if (abs <= getStatusBarHeight() + getNavBarHeight()) {
            return 0;
        }
        return abs;
    }

    public static void fixSoftInputLeaks(Context context) {
        if (context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
            String[] strArr = new String[]{"mCurRootView", "mServedView", "mNextServedView", "mLastSrvView"};
            for (int i = 0; i < 4; i++) {
                try {
                    Field declaredField = inputMethodManager.getClass().getDeclaredField(strArr[i]);
                    if (declaredField != null) {
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        Object obj = declaredField.get(inputMethodManager);
                        if (obj != null && (obj instanceof View)) {
                            if (((View) obj).getContext() == context) {
                                declaredField.set(inputMethodManager, null);
                            } else {
                                return;
                            }
                        }
                    }
                    continue;
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void clickBlankArea2HideSoftInput() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }

    private static int getStatusBarHeight() {
        Resources system = Resources.getSystem();
        return system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    private static int getNavBarHeight() {
        Resources system = Resources.getSystem();
        int identifier = system.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return system.getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
