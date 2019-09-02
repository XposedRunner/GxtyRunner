package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class ForgetPwdActivity_ViewBinding implements Unbinder {
    private ForgetPwdActivity a;
    private View b;
    private View c;
    private View d;

    @UiThread
    public ForgetPwdActivity_ViewBinding(final ForgetPwdActivity forgetPwdActivity, View view) {
        this.a = forgetPwdActivity;
        forgetPwdActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.jiaoyanmaImg, "field 'jiaoyanmaImg' and method 'butterknifeOnItemClick'");
        forgetPwdActivity.jiaoyanmaImg = (ImageView) Utils.castView(findRequiredView, R.id.jiaoyanmaImg, "field 'jiaoyanmaImg'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ForgetPwdActivity_ViewBinding b;

            public void doClick(View view) {
                forgetPwdActivity.butterknifeOnItemClick(view);
            }
        });
        forgetPwdActivity.jiaoyanmaTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.jiaoyanmaTxt, "field 'jiaoyanmaTxt'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.send, "field 'send' and method 'butterknifeOnItemClick'");
        forgetPwdActivity.send = (TextView) Utils.castView(findRequiredView, R.id.send, "field 'send'", TextView.class);
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ForgetPwdActivity_ViewBinding b;

            public void doClick(View view) {
                forgetPwdActivity.butterknifeOnItemClick(view);
            }
        });
        forgetPwdActivity.mobile = (EditText) Utils.findRequiredViewAsType(view, R.id.mobile, "field 'mobile'", EditText.class);
        forgetPwdActivity.verification = (EditText) Utils.findRequiredViewAsType(view, R.id.verification, "field 'verification'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.next, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ForgetPwdActivity_ViewBinding b;

            public void doClick(View view) {
                forgetPwdActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        ForgetPwdActivity forgetPwdActivity = this.a;
        if (forgetPwdActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        forgetPwdActivity.titleBar = null;
        forgetPwdActivity.jiaoyanmaImg = null;
        forgetPwdActivity.jiaoyanmaTxt = null;
        forgetPwdActivity.send = null;
        forgetPwdActivity.mobile = null;
        forgetPwdActivity.verification = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
