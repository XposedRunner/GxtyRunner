package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class Register2Activity_ViewBinding implements Unbinder {
    private Register2Activity a;
    private View b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public Register2Activity_ViewBinding(final Register2Activity register2Activity, View view) {
        this.a = register2Activity;
        register2Activity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        register2Activity.department = (TextView) Utils.findRequiredViewAsType(view, R.id.department, "field 'department'", TextView.class);
        register2Activity.name = (EditText) Utils.findRequiredViewAsType(view, R.id.name, "field 'name'", EditText.class);
        register2Activity.studentid = (EditText) Utils.findRequiredViewAsType(view, R.id.studentid, "field 'studentid'", EditText.class);
        register2Activity.nickname = (EditText) Utils.findRequiredViewAsType(view, R.id.nickname, "field 'nickname'", EditText.class);
        register2Activity.school = (TextView) Utils.findRequiredViewAsType(view, R.id.school, "field 'school'", TextView.class);
        register2Activity.sex = (TextView) Utils.findRequiredViewAsType(view, R.id.sex, "field 'sex'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.register, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ Register2Activity_ViewBinding b;

            public void doClick(View view) {
                register2Activity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.ll_school, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ Register2Activity_ViewBinding b;

            public void doClick(View view) {
                register2Activity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.ll_sex, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ Register2Activity_ViewBinding b;

            public void doClick(View view) {
                register2Activity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.ll_department, "method 'butterknifeOnItemClick'");
        this.e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ Register2Activity_ViewBinding b;

            public void doClick(View view) {
                register2Activity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        Register2Activity register2Activity = this.a;
        if (register2Activity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        register2Activity.titleBar = null;
        register2Activity.department = null;
        register2Activity.name = null;
        register2Activity.studentid = null;
        register2Activity.nickname = null;
        register2Activity.school = null;
        register2Activity.sex = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
