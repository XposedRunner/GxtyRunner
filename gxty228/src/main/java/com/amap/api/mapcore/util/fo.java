package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/* compiled from: BlankView */
public class fo extends View {
    public static final int a = Color.argb(255, 235, 235, 235);
    public static final int b = Color.argb(255, 21, 21, 21);
    private Paint c = new Paint();

    public fo(Context context) {
        super(context);
        this.c.setAntiAlias(true);
        this.c.setColor(a);
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.setColor(i);
        }
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.c);
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
