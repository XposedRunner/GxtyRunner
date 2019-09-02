package com.example.gita.gxty_runner.hook.autorun;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import java.lang.ref.WeakReference;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XCallback;

class Common {
    public static WeakReference<Context> context;
    public static Object run;
    public static float target;

    public static int runType = -1;


    public static WeakReference<View> sport_actionBtnLeft, sport_pauseBtn, sport_stopBtn, dOk;
    public static Handler uiHandler;

    public static boolean task = false;
    public static boolean autoRun = false;

    public static boolean exit = false;


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

}
