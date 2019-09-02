package com.blankj.utilcode.util;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FragmentUtils {
    private static final String ARGS_ID = "args_id";
    private static final String ARGS_IS_ADD_STACK = "args_is_add_stack";
    private static final String ARGS_IS_HIDE = "args_is_hide";
    private static final String ARGS_TAG = "args_tag";
    private static final int TYPE_ADD_FRAGMENT = 1;
    private static final int TYPE_HIDE_FRAGMENT = 4;
    private static final int TYPE_REMOVE_FRAGMENT = 32;
    private static final int TYPE_REMOVE_TO_FRAGMENT = 64;
    private static final int TYPE_REPLACE_FRAGMENT = 16;
    private static final int TYPE_SHOW_FRAGMENT = 2;
    private static final int TYPE_SHOW_HIDE_FRAGMENT = 8;

    private static class Args {
        final int id;
        final boolean isAddStack;
        final boolean isHide;
        final String tag;

        Args(int i, boolean z, boolean z2) {
            this(i, null, z, z2);
        }

        Args(int i, String str, boolean z, boolean z2) {
            this.id = i;
            this.tag = str;
            this.isHide = z;
            this.isAddStack = z2;
        }
    }

    public static class FragmentNode {
        final Fragment fragment;
        final List<FragmentNode> next;

        public FragmentNode(Fragment fragment, List<FragmentNode> list) {
            this.fragment = fragment;
            this.next = list;
        }

        public Fragment getFragment() {
            return this.fragment;
        }

        public List<FragmentNode> getNext() {
            return this.next;
        }

        public String toString() {
            StringBuilder append = new StringBuilder().append(this.fragment.getClass().getSimpleName()).append("->");
            String obj = (this.next == null || this.next.isEmpty()) ? "no child" : this.next.toString();
            return append.append(obj).toString();
        }
    }

    public interface OnBackClickListener {
        boolean onBackClick();
    }

    private FragmentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, false, false);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, z, false);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, z, z2);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, false, i2, i3, 0, 0);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, z, i2, i3, 0, 0);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, false, i2, i3, i4, i5);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, z, i2, i3, i4, i5);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, false, viewArr);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, null, z, viewArr);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> list, @IdRes int i, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (list == null) {
            throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, (Fragment[]) list.toArray(new Fragment[list.size()]), i, null, i2);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment[] fragmentArr, @IdRes int i, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragmentArr == null) {
            throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragmentArr, i, null, i2);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, str, false, false);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, str, z, false);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            putArgs(fragment, new Args(i, str, z, z2));
            operateNoAnim(fragmentManager, 1, null, fragment);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, str, false, i2, i3, 0, 0);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, str, z, i2, i3, 0, 0);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, str, false, i2, i3, i4, i5);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            putArgs(fragment, new Args(i, str, false, z));
            addAnim(beginTransaction, i2, i3, i4, i5);
            operate(1, fragmentManager, beginTransaction, null, fragment);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, fragment, i, str, false, viewArr);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#5 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            putArgs(fragment, new Args(i, str, false, z));
            addSharedElement(beginTransaction, viewArr);
            operate(1, fragmentManager, beginTransaction, null, fragment);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> list, @IdRes int i, String[] strArr, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (list == null) {
            throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            add(fragmentManager, (Fragment[]) list.toArray(new Fragment[list.size()]), i, strArr, i2);
        }
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment[] fragmentArr, @IdRes int i, String[] strArr, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragmentArr == null) {
            throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            int length;
            int i3;
            Fragment fragment;
            boolean z;
            if (strArr == null) {
                length = fragmentArr.length;
                for (i3 = 0; i3 < length; i3++) {
                    fragment = fragmentArr[i3];
                    if (i2 != i3) {
                        z = true;
                    } else {
                        z = false;
                    }
                    putArgs(fragment, new Args(i, null, z, false));
                }
            } else {
                length = fragmentArr.length;
                for (i3 = 0; i3 < length; i3++) {
                    fragment = fragmentArr[i3];
                    String str = strArr[i3];
                    if (i2 != i3) {
                        z = true;
                    } else {
                        z = false;
                    }
                    putArgs(fragment, new Args(i, str, z, false));
                }
            }
            operateNoAnim(fragmentManager, 1, null, fragmentArr);
        }
    }

    public static void show(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        putArgs(fragment, false);
        operateNoAnim(fragment.getFragmentManager(), 2, null, fragment);
    }

    public static void show(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (Fragment putArgs : fragments) {
            putArgs(putArgs, false);
        }
        operateNoAnim(fragmentManager, 2, null, (Fragment[]) fragments.toArray(new Fragment[fragments.size()]));
    }

    public static void hide(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        putArgs(fragment, true);
        operateNoAnim(fragment.getFragmentManager(), 4, null, fragment);
    }

    public static void hide(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (Fragment putArgs : fragments) {
            putArgs(putArgs, true);
        }
        operateNoAnim(fragmentManager, 4, null, (Fragment[]) fragments.toArray(new Fragment[fragments.size()]));
    }

    public static void showHide(int i, @NonNull List<Fragment> list) {
        if (list == null) {
            throw new NullPointerException("Argument 'fragments' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide((Fragment) list.get(i), (List) list);
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull List<Fragment> list) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (list == null) {
            throw new NullPointerException("Argument 'hide' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            for (Fragment fragment2 : list) {
                putArgs(fragment2, fragment2 != fragment);
            }
            operateNoAnim(fragment.getFragmentManager(), 8, fragment, (Fragment[]) list.toArray(new Fragment[list.size()]));
        }
    }

    public static void showHide(int i, @NonNull Fragment... fragmentArr) {
        if (fragmentArr == null) {
            throw new NullPointerException("Argument 'fragments' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(fragmentArr[i], fragmentArr);
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull Fragment... fragmentArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragmentArr == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            for (Fragment fragment2 : fragmentArr) {
                boolean z;
                if (fragment2 != fragment) {
                    z = true;
                } else {
                    z = false;
                }
                putArgs(fragment2, z);
            }
            operateNoAnim(fragment.getFragmentManager(), 8, fragment, fragmentArr);
        }
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            putArgs(fragment, false);
            putArgs(fragment2, true);
            operateNoAnim(fragment.getFragmentManager(), 8, fragment, fragment2);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, false);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, z);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, @AnimRes int i, @AnimRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, false, i, i2, 0, 0);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, @AnimRes int i, @AnimRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, z, i, i2, 0, 0);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, @AnimRes int i, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, false, i, i2, i3, i4);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, @AnimRes int i, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, z, i, i2, i3, i4);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, false, viewArr);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, null, z, viewArr);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, false);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, z);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, false, i2, i3, 0, 0);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, z, i2, i3, 0, 0);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, false, i2, i3, i4, i5);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, z, i2, i3, i4, i5);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, false, viewArr);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, null, z, viewArr);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, str, false);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            if (fragmentManager != null) {
                replace(fragmentManager, fragment2, getArgs(fragment).id, str, z);
            }
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, @AnimRes int i, @AnimRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, str, false, i, i2, 0, 0);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, @AnimRes int i, @AnimRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, str, z, i, i2, 0, 0);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, @AnimRes int i, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, str, false, i, i2, i3, i4);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, @AnimRes int i, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            if (fragmentManager != null) {
                replace(fragmentManager, fragment2, getArgs(fragment).id, str, z, i, i2, i3, i4);
            }
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragment, fragment2, str, false, viewArr);
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            if (fragmentManager != null) {
                replace(fragmentManager, fragment2, getArgs(fragment).id, str, z, viewArr);
            }
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, str, false);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            putArgs(fragment, new Args(i, str, false, z));
            operate(16, fragmentManager, beginTransaction, null, fragment);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, str, false, i2, i3, 0, 0);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes int i2, @AnimRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, str, z, i2, i3, 0, 0);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, str, false, i2, i3, i4, i5);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes int i2, @AnimRes int i3, @AnimRes int i4, @AnimRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            putArgs(fragment, new Args(i, str, false, z));
            addAnim(beginTransaction, i2, i3, i4, i5);
            operate(16, fragmentManager, beginTransaction, null, fragment);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            replace(fragmentManager, fragment, i, str, false, viewArr);
        }
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            putArgs(fragment, new Args(i, str, false, z));
            addSharedElement(beginTransaction, viewArr);
            operate(16, fragmentManager, beginTransaction, null, fragment);
        }
    }

    public static void pop(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        pop(fragmentManager, true);
    }

    public static void pop(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            fragmentManager.popBackStackImmediate();
        } else {
            fragmentManager.popBackStack();
        }
    }

    public static void popTo(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        popTo(fragmentManager, cls, z, true);
    }

    public static void popTo(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls, boolean z, boolean z2) {
        int i = 1;
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z2) {
            r2 = cls.getName();
            if (!z) {
                i = 0;
            }
            fragmentManager.popBackStackImmediate(r2, i);
        } else {
            r2 = cls.getName();
            if (!z) {
                i = 0;
            }
            fragmentManager.popBackStack(r2, i);
        }
    }

    public static void popAll(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        popAll(fragmentManager, true);
    }

    public static void popAll(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        while (fragmentManager.getBackStackEntryCount() > 0) {
            if (z) {
                fragmentManager.popBackStackImmediate();
            } else {
                fragmentManager.popBackStack();
            }
        }
    }

    public static void remove(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'remove' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(fragment.getFragmentManager(), 32, null, fragment);
    }

    public static void removeTo(@NonNull Fragment fragment, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'removeTo' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(fragment.getFragmentManager(), 64, z ? fragment : null, fragment);
    }

    public static void removeAll(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List fragments = getFragments(fragmentManager);
        operateNoAnim(fragmentManager, 32, null, (Fragment[]) fragments.toArray(new Fragment[fragments.size()]));
    }

    private static void putArgs(Fragment fragment, Args args) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putInt(ARGS_ID, args.id);
        arguments.putBoolean(ARGS_IS_HIDE, args.isHide);
        arguments.putBoolean(ARGS_IS_ADD_STACK, args.isAddStack);
        arguments.putString(ARGS_TAG, args.tag);
    }

    private static void putArgs(Fragment fragment, boolean z) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putBoolean(ARGS_IS_HIDE, z);
    }

    private static Args getArgs(Fragment fragment) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = Bundle.EMPTY;
        }
        return new Args(arguments.getInt(ARGS_ID, fragment.getId()), arguments.getBoolean(ARGS_IS_HIDE), arguments.getBoolean(ARGS_IS_ADD_STACK));
    }

    private static void operateNoAnim(@Nullable FragmentManager fragmentManager, int i, Fragment fragment, Fragment... fragmentArr) {
        if (fragmentManager != null) {
            operate(i, fragmentManager, fragmentManager.beginTransaction(), fragment, fragmentArr);
        }
    }

    private static void operate(int i, @NonNull FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, Fragment fragment, Fragment... fragmentArr) {
        int i2 = 0;
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null || !fragment.isRemoving()) {
            int length;
            Fragment fragment2;
            switch (i) {
                case 1:
                    length = fragmentArr.length;
                    while (i2 < length) {
                        fragment2 = fragmentArr[i2];
                        Bundle arguments = fragment2.getArguments();
                        if (arguments != null) {
                            String string = arguments.getString(ARGS_TAG, fragment2.getClass().getName());
                            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(string);
                            if (findFragmentByTag != null && findFragmentByTag.isAdded()) {
                                fragmentTransaction.remove(findFragmentByTag);
                            }
                            fragmentTransaction.add(arguments.getInt(ARGS_ID), fragment2, string);
                            if (arguments.getBoolean(ARGS_IS_HIDE)) {
                                fragmentTransaction.hide(fragment2);
                            }
                            if (arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                                fragmentTransaction.addToBackStack(string);
                            }
                            i2++;
                        } else {
                            return;
                        }
                    }
                    break;
                case 2:
                    length = fragmentArr.length;
                    while (i2 < length) {
                        fragmentTransaction.show(fragmentArr[i2]);
                        i2++;
                    }
                    break;
                case 4:
                    length = fragmentArr.length;
                    while (i2 < length) {
                        fragmentTransaction.hide(fragmentArr[i2]);
                        i2++;
                    }
                    break;
                case 8:
                    fragmentTransaction.show(fragment);
                    length = fragmentArr.length;
                    while (i2 < length) {
                        fragment2 = fragmentArr[i2];
                        if (fragment2 != fragment) {
                            fragmentTransaction.hide(fragment2);
                        }
                        i2++;
                    }
                    break;
                case 16:
                    Bundle arguments2 = fragmentArr[0].getArguments();
                    if (arguments2 != null) {
                        String string2 = arguments2.getString(ARGS_TAG, fragmentArr[0].getClass().getName());
                        fragmentTransaction.replace(arguments2.getInt(ARGS_ID), fragmentArr[0], string2);
                        if (arguments2.getBoolean(ARGS_IS_ADD_STACK)) {
                            fragmentTransaction.addToBackStack(string2);
                            break;
                        }
                    }
                    return;
                    break;
                case 32:
                    for (Fragment fragment3 : fragmentArr) {
                        if (fragment3 != fragment) {
                            fragmentTransaction.remove(fragment3);
                        }
                    }
                    break;
                case 64:
                    for (length = fragmentArr.length - 1; length >= 0; length--) {
                        fragment2 = fragmentArr[length];
                        if (fragment2 == fragmentArr[0]) {
                            if (fragment != null) {
                                fragmentTransaction.remove(fragment2);
                                break;
                            }
                        }
                        fragmentTransaction.remove(fragment2);
                    }
                    break;
            }
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            Log.e("FragmentUtils", fragment.getClass().getName() + " is isRemoving");
        }
    }

    private static void addAnim(FragmentTransaction fragmentTransaction, int i, int i2, int i3, int i4) {
        fragmentTransaction.setCustomAnimations(i, i2, i3, i4);
    }

    private static void addSharedElement(FragmentTransaction fragmentTransaction, View... viewArr) {
        if (VERSION.SDK_INT >= 21) {
            for (View view : viewArr) {
                fragmentTransaction.addSharedElement(view, view.getTransitionName());
            }
        }
    }

    public static Fragment getTop(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return getTopIsInStack(fragmentManager, false);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Fragment getTopInStack(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return getTopIsInStack(fragmentManager, true);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static Fragment getTopIsInStack(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) fragments.get(size);
            if (fragment != null) {
                if (!z) {
                    return fragment;
                }
                Bundle arguments = fragment.getArguments();
                if (arguments != null && arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public static Fragment getTopShow(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return getTopShowIsInStack(fragmentManager, false);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Fragment getTopShowInStack(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return getTopShowIsInStack(fragmentManager, true);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static Fragment getTopShowIsInStack(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) fragments.get(size);
            if (fragment != null && fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint()) {
                if (!z) {
                    return fragment;
                }
                Bundle arguments = fragment.getArguments();
                if (arguments != null && arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public static List<Fragment> getFragments(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.isEmpty()) {
            return Collections.emptyList();
        }
        return fragments;
    }

    public static List<Fragment> getFragmentsInStack(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        List<Fragment> arrayList = new ArrayList();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                Bundle arguments = fragment.getArguments();
                if (arguments != null && arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                    arrayList.add(fragment);
                }
            }
        }
        return arrayList;
    }

    public static List<FragmentNode> getAllFragments(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return getAllFragments(fragmentManager, new ArrayList());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static List<FragmentNode> getAllFragments(@NonNull FragmentManager fragmentManager, List<FragmentNode> list) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) fragments.get(size);
            if (fragment != null) {
                list.add(new FragmentNode(fragment, getAllFragments(fragment.getChildFragmentManager(), new ArrayList())));
            }
        }
        return list;
    }

    public static List<FragmentNode> getAllFragmentsInStack(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return getAllFragmentsInStack(fragmentManager, new ArrayList());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static List<FragmentNode> getAllFragmentsInStack(@NonNull FragmentManager fragmentManager, List<FragmentNode> list) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) fragments.get(size);
            if (fragment != null) {
                Bundle arguments = fragment.getArguments();
                if (arguments != null && arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                    list.add(new FragmentNode(fragment, getAllFragmentsInStack(fragment.getChildFragmentManager(), new ArrayList())));
                }
            }
        }
        return list;
    }

    public static Fragment findFragment(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls) {
        if (fragmentManager != null) {
            return fragmentManager.findFragmentByTag(cls.getName());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Fragment findFragment(@NonNull FragmentManager fragmentManager, @NonNull String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            return fragmentManager.findFragmentByTag(str);
        } else {
            throw new NullPointerException("Argument 'tag' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public static boolean dispatchBackPress(@NonNull Fragment fragment) {
        if (fragment != null) {
            return fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick();
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public static boolean dispatchBackPress(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List fragments = getFragments(fragmentManager);
        if (fragments == null || fragments.isEmpty()) {
            return false;
        }
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) fragments.get(size);
            if (fragment != null && fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick()) {
                return true;
            }
        }
        return false;
    }

    public static void setBackgroundColor(@NonNull Fragment fragment, @ColorInt int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public static void setBackgroundResource(@NonNull Fragment fragment, @DrawableRes int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundResource(i);
        }
    }

    public static void setBackground(@NonNull Fragment fragment, Drawable drawable) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View view = fragment.getView();
        if (view != null) {
            if (VERSION.SDK_INT >= 16) {
                view.setBackground(drawable);
            } else {
                view.setBackgroundDrawable(drawable);
            }
        }
    }

    public static String getSimpleName(Fragment fragment) {
        return fragment == null ? "null" : fragment.getClass().getSimpleName();
    }
}
