package com.github.ybq.android.spinkit.b;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.a.a;

/* compiled from: SpriteGroup */
public abstract class f extends e {
    private e[] m = r();
    private int n;

    public abstract e[] r();

    public f() {
        s();
        a(this.m);
    }

    private void s() {
        if (this.m != null) {
            for (e callback : this.m) {
                callback.setCallback(this);
            }
        }
    }

    public void a(e... eVarArr) {
    }

    public int q() {
        return this.m == null ? 0 : this.m.length;
    }

    public e h(int i) {
        return this.m == null ? null : this.m[i];
    }

    public void a(int i) {
        this.n = i;
        for (int i2 = 0; i2 < q(); i2++) {
            h(i2).a(i);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        a(canvas);
    }

    public void a(Canvas canvas) {
        if (this.m != null) {
            for (e eVar : this.m) {
                int save = canvas.save();
                eVar.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
    }

    protected void a_(Canvas canvas) {
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        for (e bounds : this.m) {
            bounds.setBounds(rect);
        }
    }

    public void start() {
        super.start();
        a.a(this.m);
    }

    public void stop() {
        super.stop();
        a.b(this.m);
    }

    public boolean isRunning() {
        return a.c(this.m) || super.isRunning();
    }

    public ValueAnimator a() {
        return null;
    }
}
