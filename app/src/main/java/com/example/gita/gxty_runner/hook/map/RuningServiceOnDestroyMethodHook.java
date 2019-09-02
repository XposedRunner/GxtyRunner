package com.example.gita.gxty_runner.hook.map;

import de.robv.android.xposed.XC_MethodHook;

public class RuningServiceOnDestroyMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {
//            if (Common.emulator.isPaused()) Common.emulator.stop();
            Common.runType = -1;
            Common.dPointList.clear();
            Common.routeProcessor = null;
//            Common.latLng = null;
            Common.gpsCount = 0;
            Common.beaconCount = 0;
            Common.target = 0;
        }
    }
