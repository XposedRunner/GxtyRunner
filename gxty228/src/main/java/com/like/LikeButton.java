package com.like;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.like.view.R;

public class LikeButton extends FrameLayout implements OnClickListener {
    private static final DecelerateInterpolator a = new DecelerateInterpolator();
    private static final AccelerateDecelerateInterpolator b = new AccelerateDecelerateInterpolator();
    private static final OvershootInterpolator c = new OvershootInterpolator(4.0f);
    private ImageView d;
    private DotsView e;
    private CircleView f;
    private a g;
    private c h;
    private b i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private float o;
    private boolean p;
    private boolean q;
    private AnimatorSet r;
    private Drawable s;
    private Drawable t;

    public LikeButton(Context context) {
        this(context, null);
    }

    public LikeButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LikeButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            a(context, attributeSet, i);
        }
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater.from(getContext()).inflate(R.layout.likeview, this, true);
        this.d = (ImageView) findViewById(R.id.icon);
        this.e = (DotsView) findViewById(R.id.dots);
        this.f = (CircleView) findViewById(R.id.circle);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LikeButton, i, 0);
        this.n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LikeButton_icon_size, -1);
        if (this.n == -1) {
            this.n = 40;
        }
        String string = obtainStyledAttributes.getString(R.styleable.LikeButton_icon_type);
        this.s = a(obtainStyledAttributes, R.styleable.LikeButton_like_drawable);
        if (this.s != null) {
            setLikeDrawable(this.s);
        }
        this.t = a(obtainStyledAttributes, R.styleable.LikeButton_unlike_drawable);
        if (this.t != null) {
            setUnlikeDrawable(this.t);
        }
        if (!(string == null || string.isEmpty())) {
            this.g = a(string);
        }
        this.l = obtainStyledAttributes.getColor(R.styleable.LikeButton_circle_start_color, 0);
        if (this.l != 0) {
            this.f.setStartColor(this.l);
        }
        this.m = obtainStyledAttributes.getColor(R.styleable.LikeButton_circle_end_color, 0);
        if (this.m != 0) {
            this.f.setEndColor(this.m);
        }
        this.j = obtainStyledAttributes.getColor(R.styleable.LikeButton_dots_primary_color, 0);
        this.k = obtainStyledAttributes.getColor(R.styleable.LikeButton_dots_secondary_color, 0);
        if (!(this.j == 0 || this.k == 0)) {
            this.e.a(this.j, this.k);
        }
        if (this.s == null && this.t == null) {
            if (this.g != null) {
                a();
            } else {
                setIcon(IconType.Heart);
            }
        }
        setEnabled(obtainStyledAttributes.getBoolean(R.styleable.LikeButton_is_enabled, true));
        Boolean valueOf = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.LikeButton_liked, false));
        setAnimationScaleFactor(obtainStyledAttributes.getFloat(R.styleable.LikeButton_anim_scale_factor, 3.0f));
        setLiked(valueOf);
        setOnClickListener(this);
        obtainStyledAttributes.recycle();
    }

    private Drawable a(TypedArray typedArray, int i) {
        int resourceId = typedArray.getResourceId(i, -1);
        return -1 != resourceId ? ContextCompat.getDrawable(getContext(), resourceId) : null;
    }

    public void onClick(View view) {
        if (this.q) {
            this.p = !this.p;
            this.d.setImageDrawable(this.p ? this.s : this.t);
            if (this.h != null) {
                if (this.p) {
                    this.h.a(this);
                } else {
                    this.h.b(this);
                }
            }
            if (this.r != null) {
                this.r.cancel();
            }
            if (this.p) {
                this.d.animate().cancel();
                this.d.setScaleX(0.0f);
                this.d.setScaleY(0.0f);
                this.f.setInnerCircleRadiusProgress(0.0f);
                this.f.setOuterCircleRadiusProgress(0.0f);
                this.e.setCurrentProgress(0.0f);
                this.r = new AnimatorSet();
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f, CircleView.b, new float[]{0.1f, 1.0f});
                ofFloat.setDuration(250);
                ofFloat.setInterpolator(a);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f, CircleView.a, new float[]{0.1f, 1.0f});
                ofFloat2.setDuration(200);
                ofFloat2.setStartDelay(200);
                ofFloat2.setInterpolator(a);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.d, ImageView.SCALE_Y, new float[]{0.2f, 1.0f});
                ofFloat3.setDuration(350);
                ofFloat3.setStartDelay(250);
                ofFloat3.setInterpolator(c);
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.d, ImageView.SCALE_X, new float[]{0.2f, 1.0f});
                ofFloat4.setDuration(350);
                ofFloat4.setStartDelay(250);
                ofFloat4.setInterpolator(c);
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.e, DotsView.a, new float[]{0.0f, 1.0f});
                ofFloat5.setDuration(900);
                ofFloat5.setStartDelay(50);
                ofFloat5.setInterpolator(b);
                this.r.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
                this.r.addListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ LikeButton a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.a.f.setInnerCircleRadiusProgress(0.0f);
                        this.a.f.setOuterCircleRadiusProgress(0.0f);
                        this.a.e.setCurrentProgress(0.0f);
                        this.a.d.setScaleX(1.0f);
                        this.a.d.setScaleY(1.0f);
                    }

                    public void onAnimationEnd(Animator animator) {
                        if (this.a.i != null) {
                            this.a.i.a(this.a);
                        }
                    }
                });
                this.r.start();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.q) {
            switch (motionEvent.getAction()) {
                case 0:
                    setPressed(true);
                    break;
                case 1:
                    this.d.animate().scaleX(0.7f).scaleY(0.7f).setDuration(150).setInterpolator(a);
                    this.d.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(a);
                    if (isPressed()) {
                        performClick();
                        setPressed(false);
                        break;
                    }
                    break;
                case 2:
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (x > 0.0f && x < ((float) getWidth()) && y > 0.0f && y < ((float) getHeight())) {
                        z = true;
                    }
                    if (isPressed() != z) {
                        setPressed(z);
                        break;
                    }
                    break;
                case 3:
                    setPressed(false);
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void setLikeDrawableRes(@DrawableRes int i) {
        this.s = ContextCompat.getDrawable(getContext(), i);
        if (this.n != 0) {
            this.s = d.a(getContext(), this.s, this.n, this.n);
        }
        if (this.p) {
            this.d.setImageDrawable(this.s);
        }
    }

    public void setLikeDrawable(Drawable drawable) {
        this.s = drawable;
        if (this.n != 0) {
            this.s = d.a(getContext(), drawable, this.n, this.n);
        }
        if (this.p) {
            this.d.setImageDrawable(this.s);
        }
    }

    public void setUnlikeDrawableRes(@DrawableRes int i) {
        this.t = ContextCompat.getDrawable(getContext(), i);
        if (this.n != 0) {
            this.t = d.a(getContext(), this.t, this.n, this.n);
        }
        if (!this.p) {
            this.d.setImageDrawable(this.t);
        }
    }

    public void setUnlikeDrawable(Drawable drawable) {
        this.t = drawable;
        if (this.n != 0) {
            this.t = d.a(getContext(), drawable, this.n, this.n);
        }
        if (!this.p) {
            this.d.setImageDrawable(this.t);
        }
    }

    public void setIcon(IconType iconType) {
        this.g = a(iconType);
        setLikeDrawableRes(this.g.b());
        setUnlikeDrawableRes(this.g.a());
        this.d.setImageDrawable(this.t);
    }

    public void a() {
        setLikeDrawableRes(this.g.b());
        setUnlikeDrawableRes(this.g.a());
        this.d.setImageDrawable(this.t);
    }

    public void setIconSizeDp(int i) {
        setIconSizePx((int) d.a(getContext(), (float) i));
    }

    public void setIconSizePx(int i) {
        this.n = i;
        b();
        this.t = d.a(getContext(), this.t, i, i);
        this.s = d.a(getContext(), this.s, i, i);
    }

    private a a(String str) {
        for (a aVar : d.a()) {
            if (aVar.c().name().toLowerCase().equals(str.toLowerCase())) {
                return aVar;
            }
        }
        throw new IllegalArgumentException("Correct icon type not specified.");
    }

    private a a(IconType iconType) {
        for (a aVar : d.a()) {
            if (aVar.c().equals(iconType)) {
                return aVar;
            }
        }
        throw new IllegalArgumentException("Correct icon type not specified.");
    }

    public void setOnLikeListener(c cVar) {
        this.h = cVar;
    }

    public void setOnAnimationEndListener(b bVar) {
        this.i = bVar;
    }

    public void setCircleStartColorRes(@ColorRes int i) {
        this.l = ContextCompat.getColor(getContext(), i);
        this.f.setStartColor(this.l);
    }

    public void setCircleStartColorInt(@ColorInt int i) {
        this.l = i;
        this.f.setStartColor(i);
    }

    public void setCircleEndColorRes(@ColorRes int i) {
        this.m = ContextCompat.getColor(getContext(), i);
        this.f.setEndColor(this.m);
    }

    private void b() {
        if (this.n != 0) {
            this.e.b((int) (((float) this.n) * this.o), (int) (((float) this.n) * this.o));
            this.f.a(this.n, this.n);
        }
    }

    public void setLiked(Boolean bool) {
        if (bool.booleanValue()) {
            this.p = true;
            this.d.setImageDrawable(this.s);
            return;
        }
        this.p = false;
        this.d.setImageDrawable(this.t);
    }

    public void setEnabled(boolean z) {
        this.q = z;
    }

    public void setAnimationScaleFactor(float f) {
        this.o = f;
        b();
    }
}
