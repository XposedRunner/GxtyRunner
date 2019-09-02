package com.example.gita.gxty_runner.hook.map;

import android.content.Intent;

import de.robv.android.xposed.XC_MethodHook;

public class RuningServiceOnStartCommandMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {

            if (Common.runType != 1) return;

            Intent intent = param.args[0] != null ? (Intent) param.args[0] : null;
            if (intent != null) {
                int intExtra = intent.getIntExtra("status", 0);
//                    XposedBridge.log("status:" + intExtra);
                switch (intExtra) {

                    case 2://暂停
//                        emulator.pause();
//                            emulatorPaused = true;
                        break;
                    case 3://继续
//                        emulator.resume();
//                            emulatorPaused = false;
                        break;
                    case 4://跑步界面进入后台
                        Common.isBackground = true;
                        break;
                    case 5://跑步界面进入前台
                        Common.isBackground = false;
                        break;
                    default:

                }
            }

        }
    }
