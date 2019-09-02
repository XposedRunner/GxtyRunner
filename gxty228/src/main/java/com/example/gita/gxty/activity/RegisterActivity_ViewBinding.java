package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class RegisterActivity_ViewBinding implements Unbinder {
    private RegisterActivity a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;

    @UiThread
    public RegisterActivity_ViewBinding(final RegisterActivity registerActivity, View view) {
        this.a = registerActivity;
        registerActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.show, "field 'show' and method 'butterknifeOnItemClick'");
        registerActivity.show = (ImageView) Utils.castView(findRequiredView, R.id.show, "field 'show'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RegisterActivity_ViewBinding b;

            public void doClick(View view) {
                registerActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.jiaoyanmaImg, "field 'jiaoyanmaImg' and method 'butterknifeOnItemClick'");
        registerActivity.jiaoyanmaImg = (ImageView) Utils.castView(findRequiredView, R.id.jiaoyanmaImg, "field 'jiaoyanmaImg'", ImageView.class);
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RegisterActivity_ViewBinding b;

            public void doClick(View view) {
                registerActivity.butterknifeOnItemClick(view);
            }
        });
        registerActivity.jiaoyanmaTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.jiaoyanmaTxt, "field 'jiaoyanmaTxt'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.xieyiBtn, "field 'xieyiBtn' and method 'butterknifeOnItemClick'");
        registerActivity.xieyiBtn = (TextView) Utils.castView(findRequiredView, R.id.xieyiBtn, "field 'xieyiBtn'", TextView.class);
        this.d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RegisterActivity_ViewBinding b;

            public void doClick(View view) {
                registerActivity.butterknifeOnItemClick(view);
            }
        });
        registerActivity.checkBtn = (CheckBox) Utils.findRequiredViewAsType(view, R.id.checkBtn, "field 'checkBtn'", CheckBox.class);
        findRequiredView = Utils.findRequiredView(view, R.id.send, "field 'send' and method 'butterknifeOnItemClick'");
        registerActivity.send = (TextView) Utils.castView(findRequiredView, R.id.send, "field 'send'", TextView.class);
        this.e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RegisterActivity_ViewBinding b;

            public void doClick(View view) {
                registerActivity.butterknifeOnItemClick(view);
            }
        });
        registerActivity.mobile = (EditText) Utils.findRequiredViewAsType(view, R.id.mobile, "field 'mobile'", EditText.class);
        registerActivity.password = (EditText) Utils.findRequiredViewAsType(view, R.id.password, "field 'password'", EditText.class);
        registerActivity.verification = (EditText) Utils.findRequiredViewAsType(view, R.id.verification, "field 'verification'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.next, "method 'butterknifeOnItemClick'");
        this.f = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RegisterActivity_ViewBinding b;

            public void doClick(View view) {
                registerActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        RegisterActivity registerActivity = this.a;
        if (registerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        registerActivity.titleBar = null;
        registerActivity.show = null;
        registerActivity.jiaoyanmaImg = null;
        registerActivity.jiaoyanmaTxt = null;
        registerActivity.xieyiBtn = null;
        registerActivity.checkBtn = null;
        registerActivity.send = null;
        registerActivity.mobile = null;
        registerActivity.password = null;
        registerActivity.verification = null;
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
    }
}
