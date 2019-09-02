package com.example.gita.gxty_runner.hook.app;

import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

public class GetInstalledPackagesMethodHook extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        List packageInfoList = (List) param.getResult();
        List<PackageInfo> resultPackageInfoList = new ArrayList<>();

        for (Object packageInfo : packageInfoList) {
            String packageName = ((PackageInfo) packageInfo).packageName;

            if (!Common.isTarget(packageName)) {
                resultPackageInfoList.add((PackageInfo) packageInfo);
            }

        }

        param.setResult(resultPackageInfoList);
    }
}