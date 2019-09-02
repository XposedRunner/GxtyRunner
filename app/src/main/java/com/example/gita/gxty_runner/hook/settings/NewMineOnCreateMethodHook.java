package com.example.gita.gxty_runner.hook.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class NewMineOnCreateMethodHook extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(final MethodHookParam param) {
        Activity activity = (Activity) param.thisObject;
        Resources resources = activity.getApplicationContext().getResources();
        final String packageName = activity.getApplicationContext().getPackageName();

        TextView shezhiBtn = activity.findViewById(resources.getIdentifier("shezhiBtn", "id", packageName));
        LinearLayout viewParent = (LinearLayout) shezhiBtn.getParent();

        if (viewParent.getChildAt(viewParent.getChildCount() - 1).getTag() != null)
            return;

        TextView textView = new TextView((Context) param.thisObject);
        textView.setText("模块设置");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, shezhiBtn.getTextSize());
        textView.setTextColor(shezhiBtn.getTextColors());
        textView.setGravity(shezhiBtn.getGravity());
        String base64String = "iVBORw0KGgoAAAANSUhEUgAAACwAAAAsBAMAAADsqkcyAAAAGFBMVEVHcEyurq6urq6urq6urq6urq6urq6urq7ffkDQAAAACHRSTlMA/HqZHONJvUeW1GkAAAE3SURBVHgBzdE1s8JAEADgG4jUR+agjdfEhhanffE2g5Y49/ffxvPcZavLN5lV9KcxHlBKLVORk6ZyRxyGLobwmsx3tmno8tJt/r64K1+G2ODZoeJ+ky+f5GbuxeH5ktEazeWkZGZwng5ogto4nl5xVDKHseq6EWJkwxSWvZJb3jEx5A68JLnL0GHBbc9HM9uHbDHXYZylOSzYA6bKeRguBcZwsZhzdNWPooHdvhQGyLjtAinnDRGG8/Ui4Kwhmg5b3YIZ6HasbgIEMa85/YSc6SSQRLvkubvpTPHylipjYJLkfafMxUOU8VVWUc7CcrtdCVBtykzn8bAc3iWEwC5a1LKsq1CtaikrFvTCk5CEsf1k3wHcffraicWP3FJ6Dx+eZTZ4Nne7B+PIEApJGsw4OMRZ3NA/invxVEmwXkrq1AAAAABJRU5ErkJggg==";
        byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(Resources.getSystem(), bitmap);
        bitmapDrawable.setBounds(shezhiBtn.getCompoundDrawables()[0].getBounds());
        textView.setCompoundDrawables(bitmapDrawable, null, null, null);
        textView.setCompoundDrawablePadding(shezhiBtn.getCompoundDrawablePadding());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Context) param.thisObject, XposedHelpers.findClass("com.example.gita.gxty.activity.SetActivity", ((Activity) param.thisObject).getClassLoader()));
                intent.putExtra("module", 0);
                intent.putExtra("title", "模块设置");
                ((Activity) param.thisObject).startActivity(intent);
            }
        });
        textView.setTag(0);
        viewParent.addView(textView, shezhiBtn.getLayoutParams());
    }
}
