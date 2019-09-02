package butterknife.internal;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import java.lang.reflect.Array;
import java.util.List;

public final class Utils {
    private static final TypedValue VALUE = new TypedValue();

    @UiThread
    public static Drawable getTintedDrawable(Context context, @DrawableRes int i, @AttrRes int i2) {
        if (context.getTheme().resolveAttribute(i2, VALUE, true)) {
            Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(context, i).mutate());
            DrawableCompat.setTint(wrap, ContextCompat.getColor(context, VALUE.resourceId));
            return wrap;
        }
        throw new NotFoundException("Required tint color attribute with name " + context.getResources().getResourceEntryName(i2) + " and attribute ID " + i2 + " was not found.");
    }

    @UiThread
    public static float getFloat(Context context, @DimenRes int i) {
        TypedValue typedValue = VALUE;
        context.getResources().getValue(i, typedValue, true);
        if (typedValue.type == 4) {
            return typedValue.getFloat();
        }
        throw new NotFoundException("Resource ID #0x" + Integer.toHexString(i) + " type #0x" + Integer.toHexString(typedValue.type) + " is not valid");
    }

    @SafeVarargs
    public static <T> T[] arrayOf(T... tArr) {
        return filterNull(tArr);
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... tArr) {
        return new ImmutableList(filterNull(tArr));
    }

    private static <T> T[] filterNull(T[] tArr) {
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            T t = tArr[i];
            if (t != null) {
                i3 = i2 + 1;
                tArr[i2] = t;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (i2 == length) {
            return tArr;
        }
        T[] tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i2);
        System.arraycopy(tArr, 0, tArr2, 0, i2);
        return tArr2;
    }

    public static <T> T findOptionalViewAsType(View view, @IdRes int i, String str, Class<T> cls) {
        return castView(view.findViewById(i), i, str, cls);
    }

    public static View findRequiredView(View view, @IdRes int i, String str) {
        View findViewById = view.findViewById(i);
        if (findViewById != null) {
            return findViewById;
        }
        throw new IllegalStateException("Required view '" + getResourceEntryName(view, i) + "' with ID " + i + " for " + str + " was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.");
    }

    public static <T> T findRequiredViewAsType(View view, @IdRes int i, String str, Class<T> cls) {
        return castView(findRequiredView(view, i, str), i, str, cls);
    }

    public static <T> T castView(View view, @IdRes int i, String str, Class<T> cls) {
        try {
            return cls.cast(view);
        } catch (Throwable e) {
            throw new IllegalStateException("View '" + getResourceEntryName(view, i) + "' with ID " + i + " for " + str + " was of the wrong type. See cause for more info.", e);
        }
    }

    public static <T> T castParam(Object obj, String str, int i, String str2, int i2, Class<T> cls) {
        try {
            return cls.cast(obj);
        } catch (Throwable e) {
            throw new IllegalStateException("Parameter #" + (i + 1) + " of method '" + str + "' was of the wrong type for parameter #" + (i2 + 1) + " of method '" + str2 + "'. See cause for more info.", e);
        }
    }

    private static String getResourceEntryName(View view, @IdRes int i) {
        if (view.isInEditMode()) {
            return "<unavailable while editing>";
        }
        return view.getContext().getResources().getResourceEntryName(i);
    }

    private Utils() {
        throw new AssertionError("No instances.");
    }
}
