package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class NewMineActivity_ViewBinding implements Unbinder {
    private NewMineActivity a;
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
    public NewMineActivity_ViewBinding(final NewMineActivity newMineActivity, View view) {
        this.a = newMineActivity;
        newMineActivity.avatar = (ImageView) Utils.findRequiredViewAsType(view, R.id.avatar, "field 'avatar'", ImageView.class);
        newMineActivity.xuexiaoText = (TextView) Utils.findRequiredViewAsType(view, R.id.xuexiaoText, "field 'xuexiaoText'", TextView.class);
        newMineActivity.nameTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.nameTxt, "field 'nameTxt'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.shuoshuoText, "field 'shuoshuoText' and method 'butterknifeOnItemClick'");
        newMineActivity.shuoshuoText = (TextView) Utils.castView(findRequiredView, R.id.shuoshuoText, "field 'shuoshuoText'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.dataBtn, "field 'dataBtn' and method 'butterknifeOnItemClick'");
        newMineActivity.dataBtn = (TextView) Utils.castView(findRequiredView, R.id.dataBtn, "field 'dataBtn'", TextView.class);
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.chengjiLayout, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.kechengLayout, "method 'butterknifeOnItemClick'");
        this.e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.shetuanLayout, "method 'butterknifeOnItemClick'");
        this.f = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.duanlianjiluBtn, "method 'butterknifeOnItemClick'");
        this.g = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.wodexiaoxiBtn, "method 'butterknifeOnItemClick'");
        this.h = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.yundongshebeiBtn, "method 'butterknifeOnItemClick'");
        this.i = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.wodejiangliBtn, "method 'butterknifeOnItemClick'");
        this.j = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.shezhiBtn, "method 'butterknifeOnItemClick'");
        this.k = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.topLayout, "method 'butterknifeOnItemClick'");
        this.l = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ NewMineActivity_ViewBinding b;

            public void doClick(View view) {
                newMineActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        NewMineActivity newMineActivity = this.a;
        if (newMineActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        newMineActivity.avatar = null;
        newMineActivity.xuexiaoText = null;
        newMineActivity.nameTxt = null;
        newMineActivity.shuoshuoText = null;
        newMineActivity.dataBtn = null;
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
