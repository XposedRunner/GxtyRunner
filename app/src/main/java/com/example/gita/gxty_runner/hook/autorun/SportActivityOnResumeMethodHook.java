package com.example.gita.gxty_runner.hook.autorun;

import android.app.Activity;
import android.view.KeyEvent;

import de.robv.android.xposed.XC_MethodHook;

public class SportActivityOnResumeMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if (!Common.autoRun || !Common.exit) return;

            ((Activity) param.thisObject).onKeyDown(KeyEvent.KEYCODE_BACK, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            ((Activity) param.thisObject).onKeyDown(KeyEvent.KEYCODE_BACK, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            ((Activity) param.thisObject).onKeyDown(KeyEvent.KEYCODE_BACK, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            ((Activity) param.thisObject).onKeyDown(KeyEvent.KEYCODE_BACK, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));

            Common.autoRun = false;
            Common.exit = false;
        }
    }
