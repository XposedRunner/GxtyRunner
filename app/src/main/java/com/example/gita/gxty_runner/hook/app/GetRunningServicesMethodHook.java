package com.example.gita.gxty_runner.hook.app;

import android.app.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

public  class GetRunningServicesMethodHook extends XC_MethodHook {

        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            List serviceInfoList = (List) param.getResult();
            List<ActivityManager.RunningServiceInfo> resultList = new ArrayList<>();

            for (Object runningServiceInfo : serviceInfoList) {
                String serviceName = ((ActivityManager.RunningServiceInfo) runningServiceInfo).process;
                if (!Common.isTarget(serviceName)) {
                    resultList.add((ActivityManager.RunningServiceInfo) runningServiceInfo);
                }
            }

            param.setResult(resultList);
        }
    }
