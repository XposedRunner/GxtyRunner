package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.lang.reflect.Field;

public final class ToastUtils {
    private static final int COLOR_DEFAULT = -16777217;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final String NULL = "null";
    private static int sBgColor = COLOR_DEFAULT;
    private static int sBgResource = -1;
    private static int sGravity = -1;
    private static int sMsgColor = COLOR_DEFAULT;
    private static int sMsgTextSize = -1;
    private static IToast sToast;
    private static int sXOffset = -1;
    private static int sYOffset = -1;

    interface IToast {
        void cancel();

        View getView();

        void setDuration(int i);

        void setGravity(int i, int i2, int i3);

        void setText(@StringRes int i);

        void setText(CharSequence charSequence);

        void setView(View view);

        void show();
    }

    static class SystemToast implements IToast {
        private static Field sField_TN_Handler;
        private static Field sField_mTN;
        Toast mToast;

        static class SafeHandler extends Handler {
            private Handler impl;

            SafeHandler(Handler handler) {
                this.impl = handler;
            }

            public void handleMessage(Message message) {
                try {
                    this.impl.handleMessage(message);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }

            public void dispatchMessage(Message message) {
                this.impl.dispatchMessage(message);
            }
        }

        SystemToast(@NonNull Toast toast) {
            if (toast == null) {
                throw new NullPointerException("Argument 'toast' of type Toast (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            this.mToast = toast;
            if (VERSION.SDK_INT == 25) {
                try {
                    sField_mTN = Toast.class.getDeclaredField("mTN");
                    sField_mTN.setAccessible(true);
                    Object obj = sField_mTN.get(toast);
                    sField_TN_Handler = sField_mTN.getType().getDeclaredField("mHandler");
                    sField_TN_Handler.setAccessible(true);
                    sField_TN_Handler.set(obj, new SafeHandler((Handler) sField_TN_Handler.get(obj)));
                } catch (Exception e) {
                }
            }
        }

        public void show() {
            this.mToast.show();
        }

        public void cancel() {
            this.mToast.cancel();
        }

        public void setView(View view) {
            this.mToast.setView(view);
        }

        public View getView() {
            return this.mToast.getView();
        }

        public void setDuration(int i) {
            this.mToast.setDuration(i);
        }

        public void setGravity(int i, int i2, int i3) {
            this.mToast.setGravity(i, i2, i3);
        }

        public void setText(int i) {
            this.mToast.setText(i);
        }

        public void setText(CharSequence charSequence) {
            this.mToast.setText(charSequence);
        }
    }

    static class ToastFactory {
        ToastFactory() {
        }

        static IToast makeToast(Context context, CharSequence charSequence, int i) {
            if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                return new SystemToast(makeNormalToast(context, charSequence, i));
            }
            if (VERSION.SDK_INT < 25) {
                return new ToastWithoutNotification(makeNormalToast(context, charSequence, i));
            }
            Log.e("ToastUtils", "Toast is GG. In fact, next step is useless.");
            return new SystemToast(makeNormalToast(context, charSequence, i));
        }

        static IToast newToast(Context context) {
            if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                return new SystemToast(new Toast(context));
            }
            if (VERSION.SDK_INT < 25) {
                return new ToastWithoutNotification(new Toast(context));
            }
            Log.e("ToastUtils", "Toast is GG. In fact, next step is useless.");
            return new SystemToast(new Toast(context));
        }

        private static Toast makeNormalToast(Context context, CharSequence charSequence, int i) {
            if (!"Xiaomi".equals(Build.MANUFACTURER)) {
                return Toast.makeText(context, charSequence, i);
            }
            Toast makeText = Toast.makeText(context, "", i);
            makeText.setText(charSequence);
            return makeText;
        }
    }

    static class ToastWithoutNotification implements IToast {
        private Handler mHandler = new Handler(Looper.myLooper());
        private LayoutParams mParams = new LayoutParams();
        private Toast mToast;
        private View mView;
        private WindowManager mWM;

        ToastWithoutNotification(@NonNull Toast toast) {
            if (toast == null) {
                throw new NullPointerException("Argument 'toast' of type Toast (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            this.mToast = toast;
            this.mParams.height = -2;
            this.mParams.width = -2;
            this.mParams.format = -3;
            this.mParams.windowAnimations = 16973828;
            this.mParams.type = 2005;
            this.mParams.setTitle("ToastWithoutNotification");
            this.mParams.flags = TinkerReport.KEY_APPLIED_PACKAGE_CHECK_LIB_META;
        }

        public void show() {
            this.mView = this.mToast.getView();
            if (this.mView != null) {
                int absoluteGravity;
                long j;
                Context context = this.mToast.getView().getContext();
                this.mWM = (WindowManager) context.getSystemService("window");
                Configuration configuration = context.getResources().getConfiguration();
                if (VERSION.SDK_INT >= 17) {
                    absoluteGravity = Gravity.getAbsoluteGravity(this.mToast.getGravity(), configuration.getLayoutDirection());
                } else {
                    absoluteGravity = this.mToast.getGravity();
                }
                this.mParams.gravity = absoluteGravity;
                if ((absoluteGravity & 7) == 7) {
                    this.mParams.horizontalWeight = 1.0f;
                }
                if ((absoluteGravity & 112) == 112) {
                    this.mParams.verticalWeight = 1.0f;
                }
                this.mParams.x = this.mToast.getXOffset();
                this.mParams.y = this.mToast.getYOffset();
                this.mParams.packageName = Utils.getApp().getPackageName();
                try {
                    this.mWM.addView(this.mView, this.mParams);
                } catch (Exception e) {
                }
                Handler handler = this.mHandler;
                Runnable anonymousClass1 = new Runnable() {
                    public void run() {
                        ToastWithoutNotification.this.cancel();
                    }
                };
                if (this.mToast.getDuration() == 0) {
                    j = 2000;
                } else {
                    j = 3500;
                }
                handler.postDelayed(anonymousClass1, j);
            }
        }

        public void cancel() {
            try {
                this.mWM.removeView(this.mView);
            } catch (IllegalArgumentException e) {
            }
            this.mView = null;
            this.mHandler = null;
            this.mToast = null;
        }

        public void setView(View view) {
            this.mToast.setView(view);
        }

        public View getView() {
            return this.mToast.getView();
        }

        public void setDuration(int i) {
            this.mToast.setDuration(i);
        }

        public void setGravity(int i, int i2, int i3) {
            this.mToast.setGravity(i, i2, i3);
        }

        public void setText(int i) {
            this.mToast.setText(i);
        }

        public void setText(CharSequence charSequence) {
            this.mToast.setText(charSequence);
        }
    }

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void setGravity(int i, int i2, int i3) {
        sGravity = i;
        sXOffset = i2;
        sYOffset = i3;
    }

    public static void setBgColor(@ColorInt int i) {
        sBgColor = i;
    }

    public static void setBgResource(@DrawableRes int i) {
        sBgResource = i;
    }

    public static void setMsgColor(@ColorInt int i) {
        sMsgColor = i;
    }

    public static void setMsgTextSize(int i) {
        sMsgTextSize = i;
    }

    public static void showShort(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = NULL;
        }
        show(charSequence, 0);
    }

    public static void showShort(@StringRes int i) {
        show(i, 0);
    }

    public static void showShort(@StringRes int i, Object... objArr) {
        show(i, 0, objArr);
    }

    public static void showShort(String str, Object... objArr) {
        show(str, 0, objArr);
    }

    public static void showLong(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = NULL;
        }
        show(charSequence, 1);
    }

    public static void showLong(@StringRes int i) {
        show(i, 1);
    }

    public static void showLong(@StringRes int i, Object... objArr) {
        show(i, 1, objArr);
    }

    public static void showLong(String str, Object... objArr) {
        show(str, 1, objArr);
    }

    public static View showCustomShort(@LayoutRes int i) {
        View view = getView(i);
        show(view, 0);
        return view;
    }

    public static View showCustomLong(@LayoutRes int i) {
        View view = getView(i);
        show(view, 1);
        return view;
    }

    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
        }
    }

    private static void show(int i, int i2) {
        try {
            show(Utils.getApp().getResources().getText(i), i2);
        } catch (Exception e) {
            show(String.valueOf(i), i2);
        }
    }

    private static void show(int i, int i2, Object... objArr) {
        try {
            show(String.format(Utils.getApp().getResources().getText(i).toString(), objArr), i2);
        } catch (Exception e) {
            show(String.valueOf(i), i2);
        }
    }

    private static void show(String str, int i, Object... objArr) {
        CharSequence charSequence;
        if (str == null) {
            charSequence = NULL;
        } else {
            charSequence = String.format(str, objArr);
            if (charSequence == null) {
                charSequence = NULL;
            }
        }
        show(charSequence, i);
    }

    private static void show(final CharSequence charSequence, final int i) {
        HANDLER.post(new Runnable() {
            @SuppressLint({"ShowToast"})
            public void run() {
                ToastUtils.cancel();
                if (Utils.isAdaptScreen()) {
                    Utils.cancelAdaptScreen();
                    ToastUtils.sToast = ToastFactory.makeToast(Utils.getApp(), charSequence, i);
                    Utils.restoreAdaptScreen();
                } else {
                    ToastUtils.sToast = ToastFactory.makeToast(Utils.getApp(), charSequence, i);
                }
                TextView textView = (TextView) ToastUtils.sToast.getView().findViewById(16908299);
                if (ToastUtils.sMsgColor != ToastUtils.COLOR_DEFAULT) {
                    textView.setTextColor(ToastUtils.sMsgColor);
                }
                if (ToastUtils.sMsgTextSize != -1) {
                    textView.setTextSize((float) ToastUtils.sMsgTextSize);
                }
                if (!(ToastUtils.sGravity == -1 && ToastUtils.sXOffset == -1 && ToastUtils.sYOffset == -1)) {
                    ToastUtils.sToast.setGravity(ToastUtils.sGravity, ToastUtils.sXOffset, ToastUtils.sYOffset);
                }
                ToastUtils.setBg(textView);
                ToastUtils.sToast.show();
            }
        });
    }

    private static void show(final View view, final int i) {
        HANDLER.post(new Runnable() {
            public void run() {
                ToastUtils.cancel();
                ToastUtils.sToast = ToastFactory.newToast(Utils.getApp());
                ToastUtils.sToast.setView(view);
                ToastUtils.sToast.setDuration(i);
                if (!(ToastUtils.sGravity == -1 && ToastUtils.sXOffset == -1 && ToastUtils.sYOffset == -1)) {
                    ToastUtils.sToast.setGravity(ToastUtils.sGravity, ToastUtils.sXOffset, ToastUtils.sYOffset);
                }
                ToastUtils.setBg();
                ToastUtils.sToast.show();
            }
        });
    }

    private static void setBg() {
        if (sBgResource != -1) {
            sToast.getView().setBackgroundResource(sBgResource);
        } else if (sBgColor != COLOR_DEFAULT) {
            View view = sToast.getView();
            Drawable background = view.getBackground();
            if (background != null) {
                background.setColorFilter(new PorterDuffColorFilter(sBgColor, Mode.SRC_IN));
            } else if (VERSION.SDK_INT >= 16) {
                view.setBackground(new ColorDrawable(sBgColor));
            } else {
                view.setBackgroundDrawable(new ColorDrawable(sBgColor));
            }
        }
    }

    private static void setBg(TextView textView) {
        if (sBgResource != -1) {
            sToast.getView().setBackgroundResource(sBgResource);
            textView.setBackgroundColor(0);
        } else if (sBgColor != COLOR_DEFAULT) {
            View view = sToast.getView();
            Drawable background = view.getBackground();
            Drawable background2 = textView.getBackground();
            if (background != null && background2 != null) {
                background.setColorFilter(new PorterDuffColorFilter(sBgColor, Mode.SRC_IN));
                textView.setBackgroundColor(0);
            } else if (background != null) {
                background.setColorFilter(new PorterDuffColorFilter(sBgColor, Mode.SRC_IN));
            } else if (background2 != null) {
                background2.setColorFilter(new PorterDuffColorFilter(sBgColor, Mode.SRC_IN));
            } else {
                view.setBackgroundColor(sBgColor);
            }
        }
    }

    private static View getView(@LayoutRes int i) {
        return ((LayoutInflater) Utils.getApp().getSystemService("layout_inflater")).inflate(i, null);
    }
}
