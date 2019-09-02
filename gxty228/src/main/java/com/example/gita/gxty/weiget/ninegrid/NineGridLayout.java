package com.example.gita.gxty.weiget.ninegrid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.example.gita.gxty.R;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public abstract class NineGridLayout extends ViewGroup {
    protected Context a;
    private float b = 3.0f;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g = false;
    private boolean h = true;
    private List<String> i = new ArrayList();
    private boolean j = true;

    protected abstract void a(int i, String str, List<String> list);

    protected abstract void a(RatioImageView ratioImageView, String str);

    protected abstract boolean a(RatioImageView ratioImageView, String str, int i);

    public NineGridLayout(Context context) {
        super(context);
        a(context);
    }

    public NineGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NineGridLayout);
        this.b = obtainStyledAttributes.getDimension(0, 3.0f);
        obtainStyledAttributes.recycle();
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        if (a(this.i) == 0) {
            setVisibility(8);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.e = i3 - i;
        this.f = (int) ((((float) this.e) - (this.b * 2.0f)) / 3.0f);
        if (this.h) {
            a();
            this.h = false;
        }
    }

    public void setSpacing(float f) {
        this.b = f;
    }

    public void setIsShowAll(boolean z) {
        this.g = z;
    }

    public void setUrlList(List<String> list) {
        if (a((List) list) == 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.i.clear();
        this.i.addAll(list);
        if (!this.h) {
            a();
        }
    }

    public void a() {
        post(new TimerTask(this) {
            final /* synthetic */ NineGridLayout a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        });
    }

    private void b() {
        removeAllViews();
        int a = a(this.i);
        if (a > 0) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        String str;
        if (a == 1) {
            str = (String) this.i.get(0);
            RatioImageView a2 = a(0, str);
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = this.f;
            setLayoutParams(layoutParams);
            a2.layout(0, 0, this.f, this.f);
            if (a(a2, str, this.e)) {
                a(a2, 0, str, false);
                return;
            } else {
                addView(a2);
                return;
            }
        }
        b(a);
        c();
        for (int i = 0; i < a; i++) {
            str = (String) this.i.get(i);
            if (this.g) {
                a(a(i, str), i, str, false);
            } else if (i < 8) {
                a(a(i, str), i, str, false);
            } else if (a <= 9) {
                a(a(i, str), i, str, false);
            } else {
                a(a(i, str), i, str, true);
                return;
            }
        }
    }

    private void c() {
        int i = this.f;
        LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = (int) (((float) (i * this.d)) + (this.b * ((float) (this.d - 1))));
        setLayoutParams(layoutParams);
    }

    private RatioImageView a(final int i, final String str) {
        RatioImageView ratioImageView = new RatioImageView(this.a);
        ratioImageView.setScaleType(ScaleType.CENTER_CROP);
        if (this.j) {
            ratioImageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NineGridLayout c;

                public void onClick(View view) {
                    this.c.a(i, str, this.c.i);
                }
            });
        }
        return ratioImageView;
    }

    public void setCanClick(boolean z) {
        this.j = z;
    }

    private void a(RatioImageView ratioImageView, int i, String str, boolean z) {
        int i2 = (int) ((((float) this.e) - (this.b * 2.0f)) / 3.0f);
        int[] a = a(i);
        int i3 = (int) ((((float) i2) + this.b) * ((float) a[1]));
        int i4 = (int) (((float) a[0]) * (((float) i2) + this.b));
        int i5 = i3 + i2;
        int i6 = i4 + i2;
        ratioImageView.layout(i3, i4, i5, i6);
        addView(ratioImageView);
        if (z) {
            int a2 = a(this.i) - 9;
            if (a2 > 0) {
                View textView = new TextView(this.a);
                textView.setText("+" + String.valueOf(a2));
                textView.setTextColor(-1);
                textView.setPadding(0, (i2 / 2) - a(30.0f), 0, 0);
                textView.setTextSize(30.0f);
                textView.setGravity(17);
                textView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                textView.getBackground().setAlpha(120);
                textView.layout(i3, i4, i5, i6);
                addView(textView);
            }
        }
        a(ratioImageView, str);
    }

    private int[] a(int i) {
        int[] iArr = new int[2];
        for (int i2 = 0; i2 < this.d; i2++) {
            for (int i3 = 0; i3 < this.c; i3++) {
                if ((this.c * i2) + i3 == i) {
                    iArr[0] = i2;
                    iArr[1] = i3;
                    break;
                }
            }
        }
        return iArr;
    }

    private void b(int i) {
        if (i <= 3) {
            this.d = 1;
            this.c = i;
        } else if (i <= 6) {
            this.d = 2;
            this.c = 3;
            if (i == 4) {
                this.c = 2;
            }
        } else {
            this.c = 3;
            if (this.g) {
                this.d = i / 3;
                if (i % 3 > 0) {
                    this.d++;
                    return;
                }
                return;
            }
            this.d = 3;
        }
    }

    protected void a(RatioImageView ratioImageView, int i, int i2) {
        ratioImageView.setLayoutParams(new LayoutParams(i, i2));
        ratioImageView.layout(0, 0, i, i2);
        LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = i2;
        setLayoutParams(layoutParams);
    }

    private int a(List<String> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    private int a(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
    }
}
