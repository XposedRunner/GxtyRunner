package com.amap.api.mapcore.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

/* compiled from: BottomDialogBase */
abstract class fc extends Dialog {
    protected abstract void a();

    public fc(Context context) {
        super(context);
        b();
    }

    protected void b() {
        Window window = getWindow();
        if (window != null) {
            window.requestFeature(1);
            a();
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            if (attributes != null) {
                attributes.width = -1;
                attributes.height = -2;
                attributes.gravity = 80;
            }
            window.setAttributes(attributes);
            window.setBackgroundDrawableResource(17170445);
        }
    }
}
