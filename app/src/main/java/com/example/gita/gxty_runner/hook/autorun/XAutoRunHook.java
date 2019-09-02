package com.example.gita.gxty_runner.hook.autorun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.lang.ref.WeakReference;

import de.robv.android.xposed.XposedHelpers;

import static java.lang.Thread.sleep;


public class XAutoRunHook {
//    private static Object run;
//    private static float target;
//
//    private static int runType = -1;
//
//    private static WeakReference<Context> context;
//
//    private static WeakReference<View> sport_actionBtnLeft, sport_pauseBtn, sport_stopBtn, dOk;
//    private static Handler uiHandler;
//
//    private static boolean task = false;
//    private static boolean autoRun = false;
//
//    private static boolean exit = false;

//    private static SharedPreferences sharedPreferences;


    public static void hook(final ClassLoader classLoader) {
        XposedHelpers.findAndHookMethod("android.app.Application",
                classLoader,
                "attach",
                Context.class,
                Common.instantiate(ApplicationAttachMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.Activity",
                classLoader,
                "startActivity",
                Intent.class,
                Common.instantiate(ActivityStartActivityMethodHook.class));


        XposedHelpers.findAndHookMethod("android.view.View",
                classLoader,
                "findViewById",
                int.class,
                Common.instantiate(ViewFindViewByIdMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.MyRuningActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(MyRuningActivityOnCreateMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService",
                classLoader,
                "onCreate",
                Common.instantiate(RuningServiceOnCreateMethodHook.class));


        XposedHelpers.findAndHookConstructor("com.example.gita.gxty.model.Run",
                classLoader,
                Common.instantiate(RunConstructorMethodHook.class));


        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService",
                classLoader,
                "onDestroy",
                Common.instantiate(RuningServiceOnDestroyMethodHook.class));

        XposedHelpers.findAndHookMethod("android.content.ContextWrapper",
                classLoader,
                "sendBroadcast",
                Intent.class,
                Common.instantiate(ContextWrapperSendBroadcastMethodHook.class));


        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.SportActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(SportActivityOnCreateMethodHook.class));


        XposedHelpers.findAndHookMethod("android.app.SharedPreferencesImpl",
                classLoader,
                "getFloat",
                String.class,
                float.class,
                Common.instantiate(SharedPreferencesImplGetFloatMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.activity.ResultActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(ResultActivityOnCreateMethodHook.class));


        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.SportActivity",
                classLoader,
                "onResume",
                Common.instantiate(SportActivityOnResumeMethodHook.class));


        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.StartActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(StartActivityOnCreateMethodHook.class));


    }


}
