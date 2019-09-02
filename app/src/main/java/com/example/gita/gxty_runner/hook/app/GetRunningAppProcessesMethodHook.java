package com.example.gita.gxty_runner.hook.app;

import android.app.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

public   class GetRunningAppProcessesMethodHook extends XC_MethodHook {

        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            
            List runningAppProcessInfos = (List) param.getResult();
            List<ActivityManager.RunningAppProcessInfo> resultList = new ArrayList<>();

            for (Object runningAppProcessInfo : runningAppProcessInfos) {
                String processName = ((ActivityManager.RunningAppProcessInfo) runningAppProcessInfo).processName;
                if (!Common.isTarget(processName)) {
                    resultList.add((ActivityManager.RunningAppProcessInfo) runningAppProcessInfo);
                }
            }

            param.setResult(resultList);
        }
    }
