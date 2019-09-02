package com.example.gita.gxty.weiget.vtext;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import java.util.ArrayList;
import java.util.List;

public class VerticalTextview extends TextSwitcher implements ViewFactory {
    private float a;
    private int b;
    private int c;
    private a d;
    private Context e;
    private int f;
    private ArrayList<Notice> g;
    private Handler h;

    public interface a {
        void a(int i, Notice notice);
    }

    public void a(float f, int i, int i2) {
        this.a = f;
        this.b = i;
        this.c = i2;
    }

    public VerticalTextview(Context context) {
        this(context, null);
        this.e = context;
    }

    public VerticalTextview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 16.0f;
        this.b = 5;
        this.c = ViewCompat.MEASURED_STATE_MASK;
        this.f = -1;
        this.e = context;
        this.g = new ArrayList();
    }

    public void setAnimTime(long j) {
        setFactory(this);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) j, 0.0f);
        translateAnimation.setDuration(j);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        Animation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-j));
        translateAnimation2.setDuration(j);
        translateAnimation2.setInterpolator(new AccelerateInterpolator());
        setInAnimation(translateAnimation);
        setOutAnimation(translateAnimation2);
    }

    public void setTextStillTime(final long j) {
        this.h = new Handler(this) {
            final /* synthetic */ VerticalTextview b;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        if (this.b.g.size() > 0) {
                            this.b.f = this.b.f + 1;
                            this.b.setText(((Notice) this.b.g.get(this.b.f % this.b.g.size())).title);
                        }
                        this.b.h.sendEmptyMessageDelayed(0, j);
                        return;
                    case 1:
                        this.b.h.removeMessages(0);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void setTextList(List<Notice> list) {
        this.g.clear();
        this.g.addAll(list);
        this.f = -1;
    }

    public void a() {
        this.h.sendEmptyMessage(0);
    }

    public void b() {
        this.h.sendEmptyMessage(1);
    }

    public View makeView() {
        View textView = new TextView(this.e);
        textView.setGravity(19);
        textView.setMaxLines(1);
        textView.setPadding(this.b, this.b, this.b, this.b);
        textView.setTextColor(this.c);
        textView.setTextSize(this.a);
        textView.setClickable(true);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VerticalTextview a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.d != null && this.a.g.size() > 0 && this.a.f != -1) {
                    this.a.d.a(this.a.f % this.a.g.size(), (Notice) this.a.g.get(this.a.f % this.a.g.size()));
                }
            }
        });
        return textView;
    }

    public void setOnItemClickListener(a aVar) {
        this.d = aVar;
    }
}
