package com.example.gita.gxty_runner.hook.settings;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XCallback;

 class Common {

    private Common() {
    }


    @SuppressWarnings("unchecked")
    public static <T extends XCallback> T instantiate(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            return (T) XC_MethodReplacement.DO_NOTHING;
        }
    }

     public static int dip2px(Context context, float dipValue) {
         Resources r = context.getResources();
         return (int) TypedValue.applyDimension(
                 TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
     }
}
