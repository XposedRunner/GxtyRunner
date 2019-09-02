package com.example.gita.gxty_runner.hook.autorun;

import android.content.Intent;

import de.robv.android.xposed.XC_MethodHook;

import static java.lang.Thread.sleep;

public class ContextWrapperSendBroadcastMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if (Common.runType != 1 || !Common.autoRun) return;

            if ("com.example.gita.gxty.ram.service.RuningService".equalsIgnoreCase(param.thisObject.getClass().getName())) {
                Intent intent = param.args[0] != null ? (Intent) param.args[0] : null;
                if (intent == null) return;
                float runLength = intent.getFloatExtra("totalLength", 0.0f);

                if (runLength > Common.target + 11 + 29 * Math.random()) {

                    Common.uiHandler.sendMessage(Common.uiHandler.obtainMessage(0));
                    Common.uiHandler.sendMessage(Common.uiHandler.obtainMessage(1));
                    do {
                        try {
                            sleep(100);
                        } catch (InterruptedException ignored) {
                        }
                    }
                    while (Common.dOk == null);
                    Common.uiHandler.sendMessage(Common.uiHandler.obtainMessage(2));

                }
            }
        }
    }
