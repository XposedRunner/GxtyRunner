package com.adam.gpsstatus;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.example.gita.gxty.R;

public class GpsStatusTextView extends TextView {
    private b a;
    private Context b;
    private int c;
    private int d;
    private int e;
    private a f = new a(this) {
        final /* synthetic */ GpsStatusTextView a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.setTextColor(this.a.e);
        }

        public void b() {
            this.a.setTextColor(this.a.c);
        }

        public void c() {
            this.a.setTextColor(this.a.d);
        }

        public void d() {
            this.a.setTextColor(this.a.e);
        }

        public void a(int i, int i2) {
        }
    };

    public GpsStatusTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public GpsStatusTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.b = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GpsStatusTextView, 0, 0);
        this.c = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.gps_icon_red));
        this.d = obtainStyledAttributes.getColor(1, getResources().getColor(R.color.gps_icon_green));
        this.e = obtainStyledAttributes.getColor(2, getResources().getColor(R.color.gps_icon_yellow));
        obtainStyledAttributes.recycle();
        this.a = b.a(context.getApplicationContext());
        this.a.a(this.f);
        this.a.c();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.b(this.f);
    }
}
