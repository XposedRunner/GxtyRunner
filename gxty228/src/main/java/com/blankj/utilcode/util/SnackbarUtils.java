package com.blankj.utilcode.util;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public final class SnackbarUtils {
    private static final int COLOR_DEFAULT = -16777217;
    private static final int COLOR_ERROR = -65536;
    private static final int COLOR_MESSAGE = -1;
    private static final int COLOR_SUCCESS = -13912576;
    private static final int COLOR_WARNING = -16128;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    private static WeakReference<Snackbar> sReference;
    private OnClickListener actionListener;
    private CharSequence actionText;
    private int actionTextColor;
    private int bgColor;
    private int bgResource;
    private int bottomMargin;
    private int duration;
    private CharSequence message;
    private int messageColor;
    private View view;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    private SnackbarUtils(View view) {
        setDefault();
        this.view = view;
    }

    private void setDefault() {
        this.message = "";
        this.messageColor = COLOR_DEFAULT;
        this.bgColor = COLOR_DEFAULT;
        this.bgResource = -1;
        this.duration = -1;
        this.actionText = "";
        this.actionTextColor = COLOR_DEFAULT;
        this.bottomMargin = 0;
    }

    public static SnackbarUtils with(@NonNull View view) {
        if (view != null) {
            return new SnackbarUtils(view);
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public SnackbarUtils setMessage(@NonNull CharSequence charSequence) {
        if (charSequence == null) {
            throw new NullPointerException("Argument 'msg' of type CharSequence (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        this.message = charSequence;
        return this;
    }

    public SnackbarUtils setMessageColor(@ColorInt int i) {
        this.messageColor = i;
        return this;
    }

    public SnackbarUtils setBgColor(@ColorInt int i) {
        this.bgColor = i;
        return this;
    }

    public SnackbarUtils setBgResource(@DrawableRes int i) {
        this.bgResource = i;
        return this;
    }

    public SnackbarUtils setDuration(int i) {
        this.duration = i;
        return this;
    }

    public SnackbarUtils setAction(@NonNull CharSequence charSequence, @NonNull OnClickListener onClickListener) {
        if (charSequence == null) {
            throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (onClickListener != null) {
            return setAction(charSequence, COLOR_DEFAULT, onClickListener);
        } else {
            throw new NullPointerException("Argument 'listener' of type View.OnClickListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public SnackbarUtils setAction(@NonNull CharSequence charSequence, @ColorInt int i, @NonNull OnClickListener onClickListener) {
        if (charSequence == null) {
            throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (onClickListener == null) {
            throw new NullPointerException("Argument 'listener' of type View.OnClickListener (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.actionText = charSequence;
            this.actionTextColor = i;
            this.actionListener = onClickListener;
            return this;
        }
    }

    public SnackbarUtils setBottomMargin(@IntRange(from = 1) int i) {
        this.bottomMargin = i;
        return this;
    }

    public void show() {
        View view = this.view;
        if (view != null) {
            if (this.messageColor != COLOR_DEFAULT) {
                CharSequence spannableString = new SpannableString(this.message);
                spannableString.setSpan(new ForegroundColorSpan(this.messageColor), 0, spannableString.length(), 33);
                sReference = new WeakReference(Snackbar.make(view, spannableString, this.duration));
            } else {
                sReference = new WeakReference(Snackbar.make(view, this.message, this.duration));
            }
            Snackbar snackbar = (Snackbar) sReference.get();
            View view2 = snackbar.getView();
            if (this.bgResource != -1) {
                view2.setBackgroundResource(this.bgResource);
            } else if (this.bgColor != COLOR_DEFAULT) {
                view2.setBackgroundColor(this.bgColor);
            }
            if (this.bottomMargin != 0) {
                ((MarginLayoutParams) view2.getLayoutParams()).bottomMargin = this.bottomMargin;
            }
            if (this.actionText.length() > 0 && this.actionListener != null) {
                if (this.actionTextColor != COLOR_DEFAULT) {
                    snackbar.setActionTextColor(this.actionTextColor);
                }
                snackbar.setAction(this.actionText, this.actionListener);
            }
            snackbar.show();
        }
    }

    public void showSuccess() {
        this.bgColor = COLOR_SUCCESS;
        this.messageColor = -1;
        this.actionTextColor = -1;
        show();
    }

    public void showWarning() {
        this.bgColor = COLOR_WARNING;
        this.messageColor = -1;
        this.actionTextColor = -1;
        show();
    }

    public void showError() {
        this.bgColor = -65536;
        this.messageColor = -1;
        this.actionTextColor = -1;
        show();
    }

    public static void dismiss() {
        if (sReference != null && sReference.get() != null) {
            ((Snackbar) sReference.get()).dismiss();
            sReference = null;
        }
    }

    public static View getView() {
        Snackbar snackbar = (Snackbar) sReference.get();
        if (snackbar == null) {
            return null;
        }
        return snackbar.getView();
    }

    public static void addView(@LayoutRes int i, @NonNull LayoutParams layoutParams) {
        if (layoutParams == null) {
            throw new NullPointerException("Argument 'params' of type ViewGroup.LayoutParams (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View view = getView();
        if (view != null) {
            view.setPadding(0, 0, 0, 0);
            ((SnackbarLayout) view).addView(LayoutInflater.from(view.getContext()).inflate(i, null), -1, layoutParams);
        }
    }

    public static void addView(@NonNull View view, @NonNull LayoutParams layoutParams) {
        if (view == null) {
            throw new NullPointerException("Argument 'child' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (layoutParams == null) {
            throw new NullPointerException("Argument 'params' of type ViewGroup.LayoutParams (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            View view2 = getView();
            if (view2 != null) {
                view2.setPadding(0, 0, 0, 0);
                ((SnackbarLayout) view2).addView(view, layoutParams);
            }
        }
    }
}
