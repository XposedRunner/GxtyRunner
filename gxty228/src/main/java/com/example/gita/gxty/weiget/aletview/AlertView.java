package com.example.gita.gxty.weiget.aletview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.gita.gxty.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlertView {
    private final LayoutParams a = new LayoutParams(-1, -2, 80);
    private String b;
    private String c;
    private List<String> d;
    private List<String> e;
    private String f;
    private ArrayList<String> g = new ArrayList();
    private WeakReference<Context> h;
    private ViewGroup i;
    private ViewGroup j;
    private ViewGroup k;
    private ViewGroup l;
    private Style m = Style.Alert;
    private b n;
    private c o;
    private boolean p;
    private Animation q;
    private Animation r;
    private int s = 17;
    private AnimationListener t = new AnimationListener(this) {
        final /* synthetic */ AlertView a;

        {
            this.a = r1;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.a.h();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    };
    private final OnTouchListener u = new OnTouchListener(this) {
        final /* synthetic */ AlertView a;

        {
            this.a = r1;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                this.a.g();
            }
            return false;
        }
    };

    public enum Style {
        ActionSheet,
        Alert
    }

    class a implements OnClickListener {
        final /* synthetic */ AlertView a;
        private int b;

        public a(AlertView alertView, int i) {
            this.a = alertView;
            this.b = i;
        }

        public void onClick(View view) {
            if (this.a.o != null) {
                this.a.o.a(this.a, this.b);
            }
            this.a.g();
        }
    }

    public AlertView(String str, String str2, String str3, String[] strArr, String[] strArr2, Context context, Style style, c cVar) {
        this.h = new WeakReference(context);
        if (style != null) {
            this.m = style;
        }
        this.o = cVar;
        a(str, str2, str3, strArr, strArr2);
        a();
        c();
        d();
    }

    protected void a(String str, String str2, String str3, String[] strArr, String[] strArr2) {
        this.b = str;
        this.c = str2;
        if (strArr != null) {
            this.d = Arrays.asList(strArr);
            this.g.addAll(this.d);
        }
        if (strArr2 != null) {
            this.e = Arrays.asList(strArr2);
            this.g.addAll(this.e);
        }
        if (str3 != null) {
            this.f = str3;
            if (this.m == Style.Alert && this.g.size() < 2) {
                this.g.add(0, str3);
            }
        }
    }

    protected void a() {
        Context context = (Context) this.h.get();
        if (context != null) {
            LayoutInflater from = LayoutInflater.from(context);
            this.j = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(16908290);
            this.k = (ViewGroup) from.inflate(R.layout.layout_alertview, this.j, false);
            this.k.setLayoutParams(new LayoutParams(-1, -1));
            this.i = (ViewGroup) this.k.findViewById(2131755614);
            int dimensionPixelSize;
            switch (this.m) {
                case ActionSheet:
                    this.a.gravity = 80;
                    dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.margin_actionsheet_left_right);
                    this.a.setMargins(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize);
                    this.i.setLayoutParams(this.a);
                    this.s = 80;
                    a(from);
                    return;
                case Alert:
                    this.a.gravity = 17;
                    dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);
                    this.a.setMargins(dimensionPixelSize, 0, dimensionPixelSize, 0);
                    this.i.setLayoutParams(this.a);
                    this.s = 17;
                    b(from);
                    return;
                default:
                    return;
            }
        }
    }

    protected void a(ViewGroup viewGroup) {
        this.l = (ViewGroup) viewGroup.findViewById(R.id.loAlertHeader);
        TextView textView = (TextView) viewGroup.findViewById(R.id.tvAlertTitle);
        TextView textView2 = (TextView) viewGroup.findViewById(R.id.tvAlertMsg);
        if (this.b != null) {
            try {
                textView.setText(Html.fromHtml(this.b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            textView.setVisibility(8);
        }
        if (this.c != null) {
            try {
                textView2.setText(Html.fromHtml(this.c));
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        textView2.setVisibility(8);
    }

    protected void b() {
        Context context = (Context) this.h.get();
        if (context != null) {
            ListView listView = (ListView) this.i.findViewById(R.id.alertButtonListView);
            if (this.f != null && this.m == Style.Alert) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.item_alertbutton, null);
                TextView textView = (TextView) inflate.findViewById(R.id.tvAlert);
                textView.setText(this.f);
                textView.setClickable(true);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_cancel));
                textView.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
                textView.setOnClickListener(new a(this, -1));
                listView.addFooterView(inflate);
            }
            listView.setAdapter(new AlertViewAdapter(this.g, this.d));
            listView.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ AlertView a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (this.a.o != null) {
                        this.a.o.a(this.a, i);
                    }
                    this.a.g();
                }
            });
        }
    }

    protected void a(LayoutInflater layoutInflater) {
        a((ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_actionsheet, this.i));
        b();
        TextView textView = (TextView) this.i.findViewById(R.id.tvAlertCancel);
        if (this.f != null) {
            textView.setVisibility(0);
            textView.setText(this.f);
        }
        textView.setOnClickListener(new a(this, -1));
    }

    protected void b(LayoutInflater layoutInflater) {
        Context context = (Context) this.h.get();
        if (context != null) {
            a((ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_alert, this.i));
            if (this.g.size() <= 2) {
                ((ViewStub) this.i.findViewById(R.id.viewStubHorizontal)).inflate();
                LinearLayout linearLayout = (LinearLayout) this.i.findViewById(R.id.loAlertButtons);
                int i = 0;
                int i2 = 0;
                while (i < this.g.size()) {
                    if (i != 0) {
                        View view = new View(context);
                        view.setBackgroundColor(context.getResources().getColor(R.color.bgColor_divier));
                        linearLayout.addView(view, new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen.size_divier), -1));
                    }
                    View inflate = LayoutInflater.from(context).inflate(R.layout.item_alertbutton, null);
                    TextView textView = (TextView) inflate.findViewById(R.id.tvAlert);
                    textView.setClickable(true);
                    if (this.g.size() == 1) {
                        textView.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
                    } else if (i == 0) {
                        textView.setBackgroundResource(R.drawable.bg_alertbutton_left);
                    } else if (i == this.g.size() - 1) {
                        textView.setBackgroundResource(R.drawable.bg_alertbutton_right);
                    }
                    String str = (String) this.g.get(i);
                    textView.setText(str);
                    if (str == this.f) {
                        textView.setTypeface(Typeface.DEFAULT);
                        textView.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_cancel));
                        textView.setOnClickListener(new a(this, -1));
                        i2--;
                    } else if (this.d != null && this.d.contains(str)) {
                        textView.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_destructive));
                    }
                    textView.setOnClickListener(new a(this, i2));
                    int i3 = i2 + 1;
                    linearLayout.addView(inflate, new LinearLayout.LayoutParams(-1, -2, 1.0f));
                    i++;
                    i2 = i3;
                }
                return;
            }
            ((ViewStub) this.i.findViewById(R.id.viewStubVertical)).inflate();
            b();
        }
    }

    protected void c() {
        this.r = i();
        this.q = j();
    }

    protected void d() {
    }

    private void a(View view) {
        this.p = true;
        this.j.addView(view);
        this.i.startAnimation(this.r);
    }

    public void e() {
        if (!f()) {
            a(this.k);
        }
    }

    public boolean f() {
        return this.k.getParent() != null && this.p;
    }

    public void g() {
        this.q.setAnimationListener(this.t);
        this.i.startAnimation(this.q);
    }

    public void h() {
        this.j.removeView(this.k);
        this.p = false;
        if (this.n != null) {
            this.n.a(this);
        }
    }

    public Animation i() {
        Context context = (Context) this.h.get();
        if (context == null) {
            return null;
        }
        return AnimationUtils.loadAnimation(context, a.a(this.s, true));
    }

    public Animation j() {
        Context context = (Context) this.h.get();
        if (context == null) {
            return null;
        }
        return AnimationUtils.loadAnimation(context, a.a(this.s, false));
    }
}
