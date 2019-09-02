package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SignatureActivity_ViewBinding implements Unbinder {
    private SignatureActivity a;

    @UiThread
    public SignatureActivity_ViewBinding(SignatureActivity signatureActivity, View view) {
        this.a = signatureActivity;
        signatureActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        signatureActivity.signature = (EditText) Utils.findRequiredViewAsType(view, R.id.signature, "field 'signature'", EditText.class);
        signatureActivity.tips = (TextView) Utils.findRequiredViewAsType(view, R.id.tips, "field 'tips'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SignatureActivity signatureActivity = this.a;
        if (signatureActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        signatureActivity.titleBar = null;
        signatureActivity.signature = null;
        signatureActivity.tips = null;
    }
}
