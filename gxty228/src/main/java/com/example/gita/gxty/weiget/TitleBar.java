package com.example.gita.gxty.weiget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.constant.MemoryConstants;

public class TitleBar extends ViewGroup implements OnClickListener {
    private TextView a;
    private LinearLayout b;
    private LinearLayout c;
    private TextView d;
    private TextView e;
    private View f;
    private View g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;

    public interface a {
        String a();

        void a(View view);

        int b();
    }

    public static abstract class b implements a {
        private int a;

        public b(int i) {
            this.a = i;
        }

        public int b() {
            return this.a;
        }

        public String a() {
            return null;
        }
    }

    public static abstract class c implements a {
        private final String a;

        public c(String str) {
            this.a = str;
        }

        public int b() {
            return 0;
        }

        public String a() {
            return this.a;
        }
    }

    public TitleBar(Context context) {
        super(context);
        a(context);
    }

    public TitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @TargetApi(21)
    private void a(Context context) {
        this.i = getResources().getDisplayMetrics().widthPixels;
        if (this.h) {
            this.j = getStatusBarHeight();
        }
        this.k = a(5);
        this.l = a(8);
        this.n = a(48);
        b(context);
        setLeftTextColor(ViewCompat.MEASURED_STATE_MASK);
        setTitleColor(ViewCompat.MEASURED_STATE_MASK);
        if (a() && !b()) {
            setImmersive(true);
            ((Activity) context).getWindow().addFlags(67108864);
        } else if (b()) {
            Window window = ((Activity) context).getWindow();
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            setImmersive(true);
        } else {
            setImmersive(false);
        }
    }

    private void b(Context context) {
        this.a = new TextView(context);
        this.c = new LinearLayout(context);
        this.b = new LinearLayout(context);
        this.g = new View(context);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        this.a.setTextSize(15.0f);
        this.a.setSingleLine();
        this.a.setGravity(16);
        this.a.setPadding(this.l + this.k, 0, this.l, 0);
        this.d = new TextView(context);
        this.e = new TextView(context);
        this.c.addView(this.d);
        this.c.addView(this.e);
        this.c.setGravity(17);
        this.d.setTextSize(18.0f);
        this.d.setSingleLine();
        this.d.setGravity(17);
        this.d.setEllipsize(TruncateAt.END);
        this.e.setTextSize(12.0f);
        this.e.setSingleLine();
        this.e.setGravity(17);
        this.e.setEllipsize(TruncateAt.END);
        this.b.setPadding(this.l, 0, this.l, 0);
        addView(this.a, layoutParams);
        addView(this.c);
        addView(this.b, layoutParams);
        addView(this.g, new LayoutParams(-1, 1));
    }

    public void setImmersive(boolean z) {
        this.h = z;
        if (this.h) {
            this.j = getStatusBarHeight();
        } else {
            this.j = 0;
        }
    }

    public void setHeight(int i) {
        this.n = i;
        setMeasuredDimension(getMeasuredWidth(), this.n);
    }

    public void setLeftImageResource(int i) {
        this.a.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
    }

    public void setLeftClickListener(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void setLeftText(CharSequence charSequence) {
        this.a.setText(charSequence);
    }

    public void setLeftText(int i) {
        this.a.setText(i);
    }

    public void setLeftTextSize(float f) {
        this.a.setTextSize(f);
    }

    public void setLeftTextColor(int i) {
        this.a.setTextColor(i);
    }

    public void setLeftVisible(boolean z) {
        this.a.setVisibility(z ? 0 : 8);
    }

    public void setTitle(CharSequence charSequence) {
        int indexOf = charSequence.toString().indexOf("\n");
        if (indexOf > 0) {
            a(charSequence.subSequence(0, indexOf), charSequence.subSequence(indexOf + 1, charSequence.length()), 1);
            return;
        }
        indexOf = charSequence.toString().indexOf("\t");
        if (indexOf > 0) {
            a(charSequence.subSequence(0, indexOf), "  " + charSequence.subSequence(indexOf + 1, charSequence.length()), 0);
            return;
        }
        this.d.setText(charSequence);
        this.e.setVisibility(8);
    }

    private void a(CharSequence charSequence, CharSequence charSequence2, int i) {
        this.c.setOrientation(i);
        this.d.setText(charSequence);
        this.e.setText(charSequence2);
        this.e.setVisibility(0);
    }

    public void setCenterClickListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setTitle(int i) {
        setTitle(getResources().getString(i));
    }

    public void setTitleColor(int i) {
        this.d.setTextColor(i);
    }

    public void setTitleSize(float f) {
        this.d.setTextSize(f);
    }

    public void setTitleBackground(int i) {
        this.d.setBackgroundResource(i);
    }

    public void setSubTitleColor(int i) {
        this.e.setTextColor(i);
    }

    public void setSubTitleSize(float f) {
        this.e.setTextSize(f);
    }

    public void setCustomTitle(View view) {
        if (view == null) {
            this.d.setVisibility(0);
            if (this.f != null) {
                this.c.removeView(this.f);
                return;
            }
            return;
        }
        if (this.f != null) {
            this.c.removeView(this.f);
        }
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.f = view;
        this.c.addView(view, layoutParams);
        this.d.setVisibility(8);
    }

    public void setDivider(Drawable drawable) {
        this.g.setBackgroundDrawable(drawable);
    }

    public void setDividerColor(int i) {
        this.g.setBackgroundColor(i);
    }

    public void setDividerHeight(int i) {
        this.g.getLayoutParams().height = i;
    }

    public void setActionTextColor(int i) {
        this.m = i;
    }

    public void setOnTitleClickListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof a) {
            ((a) tag).a(view);
        }
    }

    public View a(a aVar) {
        return a(aVar, this.b.getChildCount());
    }

    public View a(a aVar, int i) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        View b = b(aVar);
        this.b.addView(b, i, layoutParams);
        return b;
    }

    public int getActionCount() {
        return this.b.getChildCount();
    }

    private View b(a aVar) {
        View imageView;
        if (TextUtils.isEmpty(aVar.a())) {
            imageView = new ImageView(getContext());
            imageView.setImageResource(aVar.b());
        } else {
            imageView = new TextView(getContext());
            imageView.setGravity(17);
            imageView.setText(aVar.a());
            imageView.setTextSize(15.0f);
            if (this.m != 0) {
                imageView.setTextColor(this.m);
            }
        }
        imageView.setPadding(this.k, 0, this.k, 0);
        imageView.setTag(aVar);
        imageView.setOnClickListener(this);
        return imageView;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        if (MeasureSpec.getMode(i2) != MemoryConstants.GB) {
            i3 = this.n + this.j;
            i2 = MeasureSpec.makeMeasureSpec(this.n, MemoryConstants.GB);
        } else {
            i3 = MeasureSpec.getSize(i2) + this.j;
        }
        measureChild(this.a, i, i2);
        measureChild(this.b, i, i2);
        if (this.a.getMeasuredWidth() > this.b.getMeasuredWidth()) {
            this.c.measure(MeasureSpec.makeMeasureSpec(this.i - (this.a.getMeasuredWidth() * 2), MemoryConstants.GB), i2);
        } else {
            this.c.measure(MeasureSpec.makeMeasureSpec(this.i - (this.b.getMeasuredWidth() * 2), MemoryConstants.GB), i2);
        }
        measureChild(this.g, i, i2);
        setMeasuredDimension(MeasureSpec.getSize(i), i3);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, this.j, this.a.getMeasuredWidth(), this.a.getMeasuredHeight() + this.j);
        this.b.layout(this.i - this.b.getMeasuredWidth(), this.j, this.i, this.b.getMeasuredHeight() + this.j);
        if (this.a.getMeasuredWidth() > this.b.getMeasuredWidth()) {
            this.c.layout(this.a.getMeasuredWidth(), this.j, this.i - this.a.getMeasuredWidth(), getMeasuredHeight());
        } else {
            this.c.layout(this.b.getMeasuredWidth(), this.j, this.i - this.b.getMeasuredWidth(), getMeasuredHeight());
        }
        this.g.layout(0, getMeasuredHeight() - this.g.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
    }

    public static int a(int i) {
        return (int) ((Resources.getSystem().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    public static int getStatusBarHeight() {
        return a(Resources.getSystem(), "status_bar_height");
    }

    private static int a(Resources resources, String str) {
        int identifier = resources.getIdentifier(str, "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean a() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean b() {
        return VERSION.SDK_INT >= 21;
    }
}
