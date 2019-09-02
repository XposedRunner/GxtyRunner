package com.github.ybq.android.spinkit;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.github.ybq.android.library.R;
import com.github.ybq.android.spinkit.b.e;
import com.github.ybq.android.spinkit.c.a;
import com.github.ybq.android.spinkit.c.b;
import com.github.ybq.android.spinkit.c.c;
import com.github.ybq.android.spinkit.c.d;
import com.github.ybq.android.spinkit.c.f;
import com.github.ybq.android.spinkit.c.g;
import com.github.ybq.android.spinkit.c.h;
import com.github.ybq.android.spinkit.c.i;
import com.github.ybq.android.spinkit.c.j;

public class SpinKitView extends ProgressBar {
    private Style a;
    private int b;
    private e c;

    public SpinKitView(Context context) {
        this(context, null);
    }

    public SpinKitView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.SpinKitViewStyle);
    }

    public SpinKitView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.SpinKitView);
    }

    @TargetApi(21)
    public SpinKitView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpinKitView, i, i2);
        this.a = Style.values()[obtainStyledAttributes.getInt(R.styleable.SpinKitView_SpinKit_Style, 0)];
        this.b = obtainStyledAttributes.getColor(R.styleable.SpinKitView_SpinKit_Color, -1);
        obtainStyledAttributes.recycle();
        a();
        setIndeterminate(true);
    }

    private void a() {
        switch (this.a) {
            case ROTATING_PLANE:
                setIndeterminateDrawable(new h());
                return;
            case DOUBLE_BOUNCE:
                setIndeterminateDrawable(new d());
                return;
            case WAVE:
                setIndeterminateDrawable(new h());
                return;
            case WANDERING_CUBES:
                setIndeterminateDrawable(new j());
                return;
            case PULSE:
                setIndeterminateDrawable(new g());
                return;
            case CHASING_DOTS:
                setIndeterminateDrawable(new a());
                return;
            case THREE_BOUNCE:
                setIndeterminateDrawable(new i());
                return;
            case CIRCLE:
                setIndeterminateDrawable(new b());
                return;
            case CUBE_GRID:
                setIndeterminateDrawable(new c());
                return;
            case FADING_CIRCLE:
                setIndeterminateDrawable(new com.github.ybq.android.spinkit.c.e());
                return;
            case FOLDING_CUBE:
                setIndeterminateDrawable(new f());
                return;
            default:
                return;
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        super.setIndeterminateDrawable(drawable);
        if (drawable instanceof e) {
            setIndeterminateDrawable((e) drawable);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setIndeterminateDrawable(e eVar) {
        super.setIndeterminateDrawable(eVar);
        this.c = eVar;
        this.c.a(this.b);
    }

    public e getIndeterminateDrawable() {
        return this.c;
    }

    public void setIndeterminateDrawableTiled(Drawable drawable) {
        super.setIndeterminateDrawableTiled(drawable);
    }
}
