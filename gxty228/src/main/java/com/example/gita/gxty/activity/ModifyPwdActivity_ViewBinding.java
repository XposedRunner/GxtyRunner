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

public class ModifyPwdActivity_ViewBinding implements Unbinder {
    private ModifyPwdActivity a;
    private View b;

    @UiThread
    public ModifyPwdActivity_ViewBinding(final ModifyPwdActivity modifyPwdActivity, View view) {
        this.a = modifyPwdActivity;
        modifyPwdActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        modifyPwdActivity.oldPwdEdt = (EditText) Utils.findRequiredViewAsType(view, R.id.oldPwdEdt, "field 'oldPwdEdt'", EditText.class);
        modifyPwdActivity.newPwd = (EditText) Utils.findRequiredViewAsType(view, R.id.newPwd, "field 'newPwd'", EditText.class);
        modifyPwdActivity.newPwd2 = (EditText) Utils.findRequiredViewAsType(view, R.id.newPwd2, "field 'newPwd2'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.modifyBtn, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ModifyPwdActivity_ViewBinding b;

            public void doClick(View view) {
                modifyPwdActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        ModifyPwdActivity modifyPwdActivity = this.a;
        if (modifyPwdActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        modifyPwdActivity.titleBar = null;
        modifyPwdActivity.oldPwdEdt = null;
        modifyPwdActivity.newPwd = null;
        modifyPwdActivity.newPwd2 = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
