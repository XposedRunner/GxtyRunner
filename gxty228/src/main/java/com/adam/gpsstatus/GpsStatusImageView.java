package com.adam.gpsstatus;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.example.gita.gxty.R;

public class GpsStatusImageView extends ImageView {
    public final int a = 0;
    public final int b = 1;
    public final int c = 2;
    public final int d = 3;
    private b e;
    private Drawable f;
    private Drawable g;
    private Drawable h;
    private Drawable i;
    private int j = 4;
    private int k = 7;
    private Context l;
    private int m = 0;
    private a n = new a(this) {
        final /* synthetic */ GpsStatusImageView a;

        {
            this.a = r1;
        }

        public void a() {
        }

        public void b() {
            this.a.setSignalStrength(0);
        }

        public void c() {
        }

        public void d() {
        }

        public void a(int i, int i2) {
            this.a.a(i, i2);
        }
    };

    public GpsStatusImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public GpsStatusImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    @TargetApi(21)
    public GpsStatusImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.l = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GpsStatusImageView, 0, 0);
        this.f = obtainStyledAttributes.getDrawable(0);
        this.g = obtainStyledAttributes.getDrawable(1);
        this.h = obtainStyledAttributes.getDrawable(2);
        this.i = obtainStyledAttributes.getDrawable(3);
        this.j = obtainStyledAttributes.getInt(4, this.j);
        this.k = obtainStyledAttributes.getInt(5, this.k);
        obtainStyledAttributes.recycle();
        if (this.f == null) {
            this.f = getResources().getDrawable(R.drawable.ic_gps_0_24dp);
        }
        if (this.g == null) {
            this.g = getResources().getDrawable(R.drawable.ic_gps_1_24dp);
        }
        if (this.h == null) {
            this.h = getResources().getDrawable(R.drawable.ic_gps_2_24dp);
        }
        if (this.i == null) {
            this.i = getResources().getDrawable(R.drawable.ic_gps_3_24dp);
        }
        this.e = b.a(context.getApplicationContext());
        this.e.a(this.n);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.g.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.h.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.i.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    protected void onDraw(Canvas canvas) {
        switch (this.m) {
            case 0:
                this.f.draw(canvas);
                return;
            case 1:
                this.g.draw(canvas);
                return;
            case 2:
                this.h.draw(canvas);
                return;
            case 3:
                this.i.draw(canvas);
                return;
            default:
                return;
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.e.b(this.n);
    }

    public int getSignalStrength() {
        return this.m;
    }

    private void setSignalStrength(int i) {
        this.m = i;
        invalidate();
    }

    private void a(int i, int i2) {
        if (i <= this.j) {
            setSignalStrength(1);
        } else if (i <= this.k) {
            setSignalStrength(2);
        } else {
            setSignalStrength(3);
        }
    }
}
