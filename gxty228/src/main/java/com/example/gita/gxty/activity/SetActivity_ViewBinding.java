package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SetActivity_ViewBinding implements Unbinder {
    private SetActivity a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;

    @UiThread
    public SetActivity_ViewBinding(final SetActivity setActivity, View view) {
        this.a = setActivity;
        setActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        setActivity.huancunSize = (TextView) Utils.findRequiredViewAsType(view, R.id.huancunSize, "field 'huancunSize'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.setsportcare, "field 'setsportcare' and method 'butterknifeOnItemClick'");
        setActivity.setsportcare = (TextView) Utils.castView(findRequiredView, R.id.setsportcare, "field 'setsportcare'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.setwentiBtn, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setxiugaimimaBtn, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setlianxikefu, "method 'butterknifeOnItemClick'");
        this.e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setlianxiQQkefu, "method 'butterknifeOnItemClick'");
        this.f = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setzhuyishixiang, "method 'butterknifeOnItemClick'");
        this.g = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setqinglihuancun, "method 'butterknifeOnItemClick'");
        this.h = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setshoujijiance, "method 'butterknifeOnItemClick'");
        this.i = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setwoyaojvbao, "method 'butterknifeOnItemClick'");
        this.j = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.setbanbengengxin, "method 'butterknifeOnItemClick'");
        this.k = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.logoutBtn, "method 'butterknifeOnItemClick'");
        this.l = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ SetActivity_ViewBinding b;

            public void doClick(View view) {
                setActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SetActivity setActivity = this.a;
        if (setActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        setActivity.titleBar = null;
        setActivity.huancunSize = null;
        setActivity.setsportcare = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
        this.h.setOnClickListener(null);
        this.h = null;
        this.i.setOnClickListener(null);
        this.i = null;
        this.j.setOnClickListener(null);
        this.j = null;
        this.k.setOnClickListener(null);
        this.k = null;
        this.l.setOnClickListener(null);
        this.l = null;
    }
}
