package com.example.gita.gxty_runner.hook.app;

import android.app.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

public  class GetRunningTasksMethodHook extends XC_MethodHook {

        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            
            List serviceInfoList = (List) param.getResult();
            List<ActivityManager.RunningTaskInfo> resultList = new ArrayList<>();

            for (Object runningTaskInfo : serviceInfoList) {
                String taskName = ((ActivityManager.RunningTaskInfo) runningTaskInfo).baseActivity.flattenToString();
                if (!Common.isTarget(taskName)) {
                    resultList.add((ActivityManager.RunningTaskInfo) runningTaskInfo);
                }
            }

            param.setResult(resultList);
        }

    }