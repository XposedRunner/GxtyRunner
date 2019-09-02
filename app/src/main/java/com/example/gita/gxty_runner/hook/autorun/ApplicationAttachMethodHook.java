package com.example.gita.gxty_runner.hook.autorun;

import android.content.Context;

import java.lang.ref.WeakReference;

import de.robv.android.xposed.XC_MethodHook;

public class ApplicationAttachMethodHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        Common.context = new WeakReference<>((Context) param.args[0]);
//                sharedPreferences = context.get().getSharedPreferences("module_prefs", Context.MODE_PRIVATE);

//                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.MINUTE, 1);
//                Intent intent = new Intent((Context) param.thisObject, XposedHelpers.findClass("com.example.gita.gxty.ram.StartActivity", classLoader));
//                intent.putExtra("autoRun", true);
//                PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
//                AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
}
