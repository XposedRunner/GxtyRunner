package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class LoginActivity_ViewBinding implements Unbinder {
    private LoginActivity a;
    private View b;
    private View c;

    @UiThread
    public LoginActivity_ViewBinding(final LoginActivity loginActivity, View view) {
        this.a = loginActivity;
        loginActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.show, "field 'show' and method 'butterknifeOnItemClick'");
        loginActivity.show = (ImageView) Utils.castView(findRequiredView, R.id.show, "field 'show'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ LoginActivity_ViewBinding b;

            public void doClick(View view) {
                loginActivity.butterknifeOnItemClick(view);
            }
        });
        loginActivity.mobile = (EditText) Utils.findRequiredViewAsType(view, R.id.mobile, "field 'mobile'", EditText.class);
        loginActivity.password = (EditText) Utils.findRequiredViewAsType(view, R.id.password, "field 'password'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.login, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ LoginActivity_ViewBinding b;

            public void doClick(View view) {
                loginActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        LoginActivity loginActivity = this.a;
        if (loginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        loginActivity.titleBar = null;
        loginActivity.show = null;
        loginActivity.mobile = null;
        loginActivity.password = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
