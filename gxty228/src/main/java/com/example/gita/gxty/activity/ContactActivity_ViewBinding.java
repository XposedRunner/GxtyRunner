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

public class ContactActivity_ViewBinding implements Unbinder {
    private ContactActivity a;
    private View b;
    private View c;
    private View d;

    @UiThread
    public ContactActivity_ViewBinding(final ContactActivity contactActivity, View view) {
        this.a = contactActivity;
        contactActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        contactActivity.phone1 = (TextView) Utils.findRequiredViewAsType(view, R.id.phone1, "field 'phone1'", TextView.class);
        contactActivity.phone2 = (TextView) Utils.findRequiredViewAsType(view, R.id.phone2, "field 'phone2'", TextView.class);
        contactActivity.phone3 = (TextView) Utils.findRequiredViewAsType(view, R.id.phone3, "field 'phone3'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.layout1, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ContactActivity_ViewBinding b;

            public void doClick(View view) {
                contactActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.layout2, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ContactActivity_ViewBinding b;

            public void doClick(View view) {
                contactActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.layout3, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ContactActivity_ViewBinding b;

            public void doClick(View view) {
                contactActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        ContactActivity contactActivity = this.a;
        if (contactActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        contactActivity.titleBar = null;
        contactActivity.phone1 = null;
        contactActivity.phone2 = null;
        contactActivity.phone3 = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
