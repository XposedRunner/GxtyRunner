package com.example.gita.gxty_runner.hook.app;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

public   class GetOurStackTraceMethodHook extends XC_MethodHook {

        @Override
        protected void afterHookedMethod(MethodHookParam param) {


            StackTraceElement[] stackTraceElements = (StackTraceElement[]) param.getResult();
            List<StackTraceElement> stackTraceElementList = new ArrayList<>();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                try {
                    if (Common.isTarget(stackTraceElement.getClassName()) || Common.isTarget(stackTraceElement.getMethodName())) {
                        continue;
                    }
                    stackTraceElementList.add(stackTraceElement);
                } catch (Exception ignored) {
                }
            }
            param.setResult(stackTraceElementList.toArray(new StackTraceElement[]{}));

        }
    }
