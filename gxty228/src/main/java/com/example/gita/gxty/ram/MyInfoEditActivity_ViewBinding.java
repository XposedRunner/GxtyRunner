package com.example.gita.gxty.ram;

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

public class MyInfoEditActivity_ViewBinding implements Unbinder {
    private MyInfoEditActivity a;
    private View b;
    private View c;
    private View d;

    @UiThread
    public MyInfoEditActivity_ViewBinding(final MyInfoEditActivity myInfoEditActivity, View view) {
        this.a = myInfoEditActivity;
        myInfoEditActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.avatar, "field 'avatar' and method 'butterknifeOnItemClick'");
        myInfoEditActivity.avatar = (ImageView) Utils.castView(findRequiredView, R.id.avatar, "field 'avatar'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ MyInfoEditActivity_ViewBinding b;

            public void doClick(View view) {
                myInfoEditActivity.butterknifeOnItemClick(view);
            }
        });
        myInfoEditActivity.myinfo_renzhengText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_renzhengText, "field 'myinfo_renzhengText'", TextView.class);
        myInfoEditActivity.myinfo_nameText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_nameText, "field 'myinfo_nameText'", TextView.class);
        myInfoEditActivity.myinfo_xuehaoText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_xuehaoText, "field 'myinfo_xuehaoText'", TextView.class);
        myInfoEditActivity.myinfo_xuexiaoText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_xuexiaoText, "field 'myinfo_xuexiaoText'", TextView.class);
        myInfoEditActivity.myinfo_yuanxiText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_yuanxiText, "field 'myinfo_yuanxiText'", TextView.class);
        myInfoEditActivity.myinfo_xingbieText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_xingbieText, "field 'myinfo_xingbieText'", TextView.class);
        myInfoEditActivity.myinfo_tizhongText = (EditText) Utils.findRequiredViewAsType(view, R.id.myinfo_tizhongText, "field 'myinfo_tizhongText'", EditText.class);
        myInfoEditActivity.myinfo_shengaoText = (EditText) Utils.findRequiredViewAsType(view, R.id.myinfo_shengaoText, "field 'myinfo_shengaoText'", EditText.class);
        myInfoEditActivity.myinfo_nicknameText = (EditText) Utils.findRequiredViewAsType(view, R.id.myinfo_nicknameText, "field 'myinfo_nicknameText'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.myinfo_yuanxiLayout, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ MyInfoEditActivity_ViewBinding b;

            public void doClick(View view) {
                myInfoEditActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView2 = Utils.findRequiredView(view, R.id.myinfo_xingbieLayout, "method 'butterknifeOnItemClick'");
        this.d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ MyInfoEditActivity_ViewBinding b;

            public void doClick(View view) {
                myInfoEditActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        MyInfoEditActivity myInfoEditActivity = this.a;
        if (myInfoEditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        myInfoEditActivity.titleBar = null;
        myInfoEditActivity.avatar = null;
        myInfoEditActivity.myinfo_renzhengText = null;
        myInfoEditActivity.myinfo_nameText = null;
        myInfoEditActivity.myinfo_xuehaoText = null;
        myInfoEditActivity.myinfo_xuexiaoText = null;
        myInfoEditActivity.myinfo_yuanxiText = null;
        myInfoEditActivity.myinfo_xingbieText = null;
        myInfoEditActivity.myinfo_tizhongText = null;
        myInfoEditActivity.myinfo_shengaoText = null;
        myInfoEditActivity.myinfo_nicknameText = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
