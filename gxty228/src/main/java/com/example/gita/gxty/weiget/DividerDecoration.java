package com.example.gita.gxty.weiget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.internal.view.SupportMenu;
import com.example.gita.gxty.weiget.devider.Y_DividerItemDecoration;
import com.example.gita.gxty.weiget.devider.b;
import com.example.gita.gxty.weiget.devider.c;

public class DividerDecoration extends Y_DividerItemDecoration {
    private int a;

    public DividerDecoration(Context context, int i) {
        super(context);
        this.a = i;
    }

    public b a(int i) {
        if (i != this.a - 1) {
            return new c().a(true, Color.parseColor("#42ffffff"), 1.0f, 20.0f, 0.0f).a();
        }
        return new c().a(true, SupportMenu.CATEGORY_MASK, 0.0f, 0.0f, 0.0f).a();
    }
}
