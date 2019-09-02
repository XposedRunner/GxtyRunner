package com.example.gita.gxty_runner.hook.app;

import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class AdsActivityOnCreateMethodHook extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) {
        for (Field field : param.thisObject.getClass().getDeclaredFields()) {
            Object fieldObject = XposedHelpers.getObjectField(param.thisObject, field.getName());
            if ((fieldObject instanceof Handler)) {
                Handler handler = (Handler) fieldObject;
                XposedHelpers.findAndHookMethod(fieldObject.getClass(), "handleMessage", Message.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {

                        if (((Message) param.args[0]).what == 1007) {
                            param.setResult(null);
                        }
                    }
                });
                handler.removeMessages(1008);
                handler.sendEmptyMessage(1008);
            }

        }

    }
}