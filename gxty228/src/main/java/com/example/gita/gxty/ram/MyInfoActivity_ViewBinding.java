package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class MyInfoActivity_ViewBinding implements Unbinder {
    private MyInfoActivity a;

    @UiThread
    public MyInfoActivity_ViewBinding(MyInfoActivity myInfoActivity, View view) {
        this.a = myInfoActivity;
        myInfoActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        myInfoActivity.avatar = (ImageView) Utils.findRequiredViewAsType(view, R.id.avatar, "field 'avatar'", ImageView.class);
        myInfoActivity.myinfo_renzhengText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_renzhengText, "field 'myinfo_renzhengText'", TextView.class);
        myInfoActivity.myinfo_nameText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_nameText, "field 'myinfo_nameText'", TextView.class);
        myInfoActivity.myinfo_xuehaoText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_xuehaoText, "field 'myinfo_xuehaoText'", TextView.class);
        myInfoActivity.myinfo_xuexiaoText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_xuexiaoText, "field 'myinfo_xuexiaoText'", TextView.class);
        myInfoActivity.myinfo_yuanxiText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_yuanxiText, "field 'myinfo_yuanxiText'", TextView.class);
        myInfoActivity.myinfo_xingbieText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_xingbieText, "field 'myinfo_xingbieText'", TextView.class);
        myInfoActivity.myinfo_tizhongText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_tizhongText, "field 'myinfo_tizhongText'", TextView.class);
        myInfoActivity.myinfo_shengaoText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_shengaoText, "field 'myinfo_shengaoText'", TextView.class);
        myInfoActivity.myinfo_nicknameText = (TextView) Utils.findRequiredViewAsType(view, R.id.myinfo_nicknameText, "field 'myinfo_nicknameText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        MyInfoActivity myInfoActivity = this.a;
        if (myInfoActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        myInfoActivity.titleBar = null;
        myInfoActivity.avatar = null;
        myInfoActivity.myinfo_renzhengText = null;
        myInfoActivity.myinfo_nameText = null;
        myInfoActivity.myinfo_xuehaoText = null;
        myInfoActivity.myinfo_xuexiaoText = null;
        myInfoActivity.myinfo_yuanxiText = null;
        myInfoActivity.myinfo_xingbieText = null;
        myInfoActivity.myinfo_tizhongText = null;
        myInfoActivity.myinfo_shengaoText = null;
        myInfoActivity.myinfo_nicknameText = null;
    }
}
