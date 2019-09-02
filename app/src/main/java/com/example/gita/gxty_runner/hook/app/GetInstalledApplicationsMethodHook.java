package com.example.gita.gxty_runner.hook.app;

import android.content.pm.ApplicationInfo;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

public class GetInstalledApplicationsMethodHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        List applicationList = (List) param.getResult();
        List<ApplicationInfo> resultApplicationList = new ArrayList<>();

        for (Object applicationInfo : applicationList) {
            String packageName = ((ApplicationInfo) applicationInfo).packageName;

            if (!Common.isTarget(packageName)) {
                resultApplicationList.add((ApplicationInfo) applicationInfo);
            }

        }
        param.setResult(resultApplicationList);
    }
}
