package com.example.gita.gxty_runner.hook.autorun;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import de.robv.android.xposed.XC_MethodHook;

import static java.lang.Thread.sleep;

public class ResultActivityOnCreateMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(final MethodHookParam param) {

            if (!Common.autoRun) return;

            final Handler handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    switch (msg.what) {
                        case 0:
                            Toast.makeText((Context) param.thisObject, "跑步结束，10秒后程序结束", Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            ((Activity) param.thisObject).finish();
                            Common.exit = true;
                            break;
                    }

                    return true;
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.sendMessage(handler.obtainMessage(0));
                    try {
                        sleep(10 * 1000);
                    } catch (InterruptedException ignored) {
                    }
                    handler.sendMessage(handler.obtainMessage(1));
                }
            }).start();
        }
    }
