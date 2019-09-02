package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class ForgetPwd2Activity_ViewBinding implements Unbinder {
    private ForgetPwd2Activity a;
    private View b;

    @UiThread
    public ForgetPwd2Activity_ViewBinding(final ForgetPwd2Activity forgetPwd2Activity, View view) {
        this.a = forgetPwd2Activity;
        forgetPwd2Activity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        forgetPwd2Activity.pwd1 = (EditText) Utils.findRequiredViewAsType(view, R.id.pwd1, "field 'pwd1'", EditText.class);
        forgetPwd2Activity.pwd2 = (EditText) Utils.findRequiredViewAsType(view, R.id.pwd2, "field 'pwd2'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.next, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ForgetPwd2Activity_ViewBinding b;

            public void doClick(View view) {
                forgetPwd2Activity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        ForgetPwd2Activity forgetPwd2Activity = this.a;
        if (forgetPwd2Activity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        forgetPwd2Activity.titleBar = null;
        forgetPwd2Activity.pwd1 = null;
        forgetPwd2Activity.pwd2 = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
