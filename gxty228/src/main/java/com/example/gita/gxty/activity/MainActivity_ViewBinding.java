package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class MainActivity_ViewBinding implements Unbinder {
    private MainActivity a;
    private View b;
    private View c;
    private View d;

    @UiThread
    public MainActivity_ViewBinding(final MainActivity mainActivity, View view) {
        this.a = mainActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.login, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ MainActivity_ViewBinding b;

            public void doClick(View view) {
                mainActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.register, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ MainActivity_ViewBinding b;

            public void doClick(View view) {
                mainActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.changeBtn, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ MainActivity_ViewBinding b;

            public void doClick(View view) {
                mainActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        if (this.a == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
