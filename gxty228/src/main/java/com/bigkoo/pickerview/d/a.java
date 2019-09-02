package com.bigkoo.pickerview.d;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import com.bigkoo.pickerview.R;

/* compiled from: BasePickerView */
public class a {
    private final LayoutParams a = new LayoutParams(-1, -2, 80);
    protected ViewGroup b;
    private Context c;
    private ViewGroup d;
    private ViewGroup e;
    private com.bigkoo.pickerview.b.a f;
    private boolean g;
    private Animation h;
    private Animation i;
    private int j = 80;
    private final OnTouchListener k = new OnTouchListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                this.a.f();
            }
            return false;
        }
    };

    public a(Context context) {
        this.c = context;
        a();
        b();
        c();
    }

    protected void a() {
        LayoutInflater from = LayoutInflater.from(this.c);
        this.d = (ViewGroup) ((Activity) this.c).getWindow().getDecorView().findViewById(16908290);
        this.e = (ViewGroup) from.inflate(R.layout.layout_basepickerview, this.d, false);
        this.e.setLayoutParams(new LayoutParams(-1, -1));
        this.b = (ViewGroup) this.e.findViewById(R.id.content_container);
        this.b.setLayoutParams(this.a);
    }

    protected void b() {
        this.i = g();
        this.h = h();
    }

    protected void c() {
    }

    private void a(View view) {
        this.d.addView(view);
        this.b.startAnimation(this.i);
    }

    public void d() {
        if (!e()) {
            a(this.e);
        }
    }

    public boolean e() {
        return this.d.findViewById(R.id.outmost_container) != null;
    }

    public void f() {
        if (!this.g) {
            this.h.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.d.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.d.removeView(this.a.a.e);
                            this.a.a.g = false;
                            if (this.a.a.f != null) {
                                this.a.a.f.a(this.a.a);
                            }
                        }
                    });
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            this.b.startAnimation(this.h);
            this.g = true;
        }
    }

    public Animation g() {
        return AnimationUtils.loadAnimation(this.c, com.bigkoo.pickerview.c.a.a(this.j, true));
    }

    public Animation h() {
        return AnimationUtils.loadAnimation(this.c, com.bigkoo.pickerview.c.a.a(this.j, false));
    }

    public View a(int i) {
        return this.b.findViewById(i);
    }
}
