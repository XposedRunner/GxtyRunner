package com.example.gita.gxty_runner.hook.autorun;

import android.app.Activity;
import android.view.WindowManager;

import de.robv.android.xposed.XC_MethodHook;

public class StartActivityOnCreateMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {

//            if (!sharedPreferences.getBoolean("module_on", true)) return;
//
//            Uri uri = ((Activity) param.thisObject).getIntent().getData();
//            if (sharedPreferences.getBoolean("autorun_on", false) && uri != null && "autorun".equalsIgnoreCase(uri.getScheme())) {
//                autoRun = true;
//            }
//
//            //XposedBridge.log("before getStringSet: " + sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()));
//            String[] strings = sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{});
//            Set<String> stringSet = new HashSet<>();
//            for (String string : strings) {
//                long l = Long.parseLong(string);
//                if (Math.abs(l) < System.currentTimeMillis()) {
//                    if (l < 0) {
//                        do {
//                            l -= AlarmManager.INTERVAL_DAY;
//                        } while (Math.abs(l) < System.currentTimeMillis());
//                    } else {
//                        continue;
//                    }
//                }
//
//                stringSet.add(String.valueOf(l));
//
//                Intent intent = new Intent((Context) param.thisObject, param.thisObject.getClass());
//                intent.setAction(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//                intent.setData(Uri.parse("autorun://scheduled?time=" + l));
//                PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
//                AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
//                if (sharedPreferences.getBoolean("autorun_on", false)) {
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, Math.abs(l), pendingIntent);
//                } else {
//                    alarmManager.cancel(pendingIntent);
//                }
//
//            }
//            sharedPreferences.edit().putStringSet("autorun_schedules", stringSet).apply();
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if (!Common.autoRun) return;

            ((Activity) param.thisObject).getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        }
    }
