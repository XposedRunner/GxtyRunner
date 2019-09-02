package com.example.gita.gxty_runner.hook.bluetooth;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class DidRangeBeaconsInRegionMethodHook extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) {
        ClassLoader classLoader = param.thisObject.getClass().getClassLoader();
        Class beaconClazz = XposedHelpers.findClass("org.altbeacon.beacon.Beacon", classLoader);
        Class identifierClazz = XposedHelpers.findClass("org.altbeacon.beacon.Identifier", classLoader);
        for (Beacon myBeacon : Common.beaconList) {
            Object beacon = XposedHelpers.newInstance(beaconClazz);
            Object identifiers = XposedHelpers.getObjectField(beacon, "mIdentifiers");
            XposedHelpers.callMethod(identifiers, "add", XposedHelpers.callStaticMethod(identifierClazz, "parse", new Class[]{String.class}, myBeacon.uuid));
            XposedHelpers.callMethod(identifiers, "add", XposedHelpers.callStaticMethod(identifierClazz, "fromInt", new Class[]{int.class}, Integer.parseInt(myBeacon.major)));
            XposedHelpers.callMethod(identifiers, "add", XposedHelpers.callStaticMethod(identifierClazz, "fromInt", new Class[]{int.class}, Integer.parseInt(myBeacon.minor)));
            XposedHelpers.callMethod(param.args[0], "add", beacon);
        }
    }
}