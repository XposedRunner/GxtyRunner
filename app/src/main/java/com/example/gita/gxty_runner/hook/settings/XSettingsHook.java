package com.example.gita.gxty_runner.hook.settings;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class XSettingsHook {

    //    private static HashMap<String, WeakReference<View>> viewReferenceHashMap = new HashMap<>();
//    private static SharedPreferences sharedPreferences;


    public static void hook(final ClassLoader classLoader) {

//        XposedHelpers.findAndHookMethod("android.app.Application", classLoader, "attach", Context.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) {
//                sharedPreferences = ((Context) param.args[0]).getSharedPreferences("module_prefs", Context.MODE_PRIVATE);
//            }
//        });

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.NewMineActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(NewMineOnCreateMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.activity.SetActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(SetActivityOnCreateMethodHook.class));

    }


}
