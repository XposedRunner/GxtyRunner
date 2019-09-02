package com.example.gita.gxty_runner.hook.bluetooth;

import com.example.gita.gxty_runner.hook.map.DPoint;
import com.example.gita.gxty_runner.hook.map.LatLng;
import com.example.gita.gxty_runner.hook.map.MapUtils;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class AMapSetLocationListenerMethodHook extends XC_MethodHook {
    //        boolean isMethodHooked = false;
    private List<Class> hookedClasses = new ArrayList<>();

    @Override
    protected void afterHookedMethod(MethodHookParam param) {

//            if (isMethodHooked) return;
        if (hookedClasses.contains(param.args[0].getClass())) {
            return;
        }
        hookedClasses.add(param.args[0].getClass());

        if (param.args[0].getClass().getName().contains(".service.BaseService")) {
            XposedHelpers.findAndHookMethod(param.args[0].getClass(), "onLocationChanged", "com.amap.api.location.AMapLocation", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    double latitude = (double) XposedHelpers.callMethod(param.args[0], "getLatitude");
                    double longitude = (double) XposedHelpers.callMethod(param.args[0], "getLongitude");
                    Common.beaconList.clear();
                    for (DPoint dPoint : Common.dPointList) {
                        if (MapUtils.getDistance(dPoint.latLng, new LatLng(latitude, longitude)) < Common.distance) {
                            Common.beaconList.add(dPoint.beacon);
                        }
                    }
                }
            });
//                isMethodHooked = true;
        }
    }
}
