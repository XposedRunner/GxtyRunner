package com.example.gita.gxty_runner.hook.autorun;

import android.content.res.Resources;
import android.view.View;

import java.lang.ref.WeakReference;

import de.robv.android.xposed.XC_MethodHook;

public class ViewFindViewByIdMethodHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) {

        if (!Common.autoRun) return;

        int viewID = (int) param.args[0];
        Resources resources = Common.context.get().getResources();

        if (viewID == resources.getIdentifier("sport_actionBtnleft", "id", Common.context.get().getPackageName())) {
            Common.sport_actionBtnLeft = new WeakReference<>((View) param.getResult());
        } else if (viewID == resources.getIdentifier("sport_pauseBtn", "id", Common.context.get().getPackageName())) {
            Common.sport_pauseBtn = new WeakReference<>((View) param.getResult());
        } else if (viewID == resources.getIdentifier("sport_stopBtn", "id", Common.context.get().getPackageName())) {
            Common.sport_stopBtn = new WeakReference<>((View) param.getResult());
        } else if (viewID == resources.getIdentifier("dOk", "id", Common.context.get().getPackageName())) {
            Common.dOk = new WeakReference<>((View) param.getResult());
        }

    }
}
