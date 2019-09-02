package com.example.gita.gxty_runner.hook.bluetooth;

import com.example.gita.gxty_runner.hook.map.DPoint;
import com.example.gita.gxty_runner.hook.map.LatLng;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class RuningServiceOnCreateMethodHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) {

        if (Common.runType != 1 || !Common.dPointList.isEmpty()) return;

        Object ibeacons = XposedHelpers.getObjectField(Common.run, "ibeacon");
        for (int i = 0; i < (int) XposedHelpers.callMethod(ibeacons, "size"); i++) {
            Object ibeacon = XposedHelpers.callMethod(ibeacons, "get", new Class[]{int.class}, i);
            String uuid = (String) XposedHelpers.getObjectField(ibeacon, "uuid");
            String major = (String) XposedHelpers.getObjectField(ibeacon, "major");
            String minor = (String) XposedHelpers.getObjectField(ibeacon, "minor");
            Object position = XposedHelpers.getObjectField(ibeacon, "position");
            double latitude = XposedHelpers.getDoubleField(position, "latitude");
            double longitude = XposedHelpers.getDoubleField(position, "longitude");
            LatLng latLng = new LatLng(latitude, longitude);
            Beacon beacon = new Beacon(uuid, major, minor);
            Common.dPointList.add(new DPoint(latLng, beacon));
        }

        Common.distance = XposedHelpers.getIntField(Common.run, "distance");
    }
}