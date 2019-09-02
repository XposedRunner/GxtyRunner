package com.example.gita.gxty_runner.hook.autorun;

import android.os.SystemClock;
import android.view.MotionEvent;

import de.robv.android.xposed.XC_MethodHook;

public class SportActivityOnCreateMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if (!Common.autoRun) return;

            Common.sport_actionBtnLeft.get().onTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
            Common.sport_actionBtnLeft.get().onTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));

        }
    }
