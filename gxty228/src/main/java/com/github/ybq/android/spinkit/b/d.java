package com.github.ybq.android.spinkit.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;

/* compiled from: ShapeSprite */
public abstract class d extends e {
    private Paint m = new Paint();
    private int n;
    private int o;

    public abstract void a(Canvas canvas, Paint paint);

    public d() {
        this.m.setAntiAlias(true);
        this.m.setColor(-1);
    }

    public void a(int i) {
        this.o = i;
        q();
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        q();
    }

    private void q() {
        int alpha = getAlpha();
        this.n = ((((alpha + (alpha >> 7)) * (this.o >>> 24)) >> 8) << 24) | ((this.o << 8) >>> 8);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.m.setColorFilter(colorFilter);
    }

    protected final void a_(Canvas canvas) {
        this.m.setColor(this.n);
        a(canvas, this.m);
    }
}
