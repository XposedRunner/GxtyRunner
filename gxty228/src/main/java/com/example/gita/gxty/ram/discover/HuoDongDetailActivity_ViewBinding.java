package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ajguan.library.EasyRefreshLayout;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class HuoDongDetailActivity_ViewBinding implements Unbinder {
    private HuoDongDetailActivity a;

    @UiThread
    public HuoDongDetailActivity_ViewBinding(HuoDongDetailActivity huoDongDetailActivity, View view) {
        this.a = huoDongDetailActivity;
        huoDongDetailActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        huoDongDetailActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
        huoDongDetailActivity.commentTxt = (EditText) Utils.findRequiredViewAsType(view, R.id.commentTxt, "field 'commentTxt'", EditText.class);
        huoDongDetailActivity.actionLayout = Utils.findRequiredView(view, R.id.actionLayout, "field 'actionLayout'");
        huoDongDetailActivity.actionBtn = Utils.findRequiredView(view, R.id.actionBtn, "field 'actionBtn'");
        huoDongDetailActivity.commentLayout = Utils.findRequiredView(view, R.id.commentLayout, "field 'commentLayout'");
        huoDongDetailActivity.easylayout = (EasyRefreshLayout) Utils.findRequiredViewAsType(view, R.id.easylayout, "field 'easylayout'", EasyRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        HuoDongDetailActivity huoDongDetailActivity = this.a;
        if (huoDongDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        huoDongDetailActivity.titleBar = null;
        huoDongDetailActivity.mRecyclerView = null;
        huoDongDetailActivity.commentTxt = null;
        huoDongDetailActivity.actionLayout = null;
        huoDongDetailActivity.actionBtn = null;
        huoDongDetailActivity.commentLayout = null;
        huoDongDetailActivity.easylayout = null;
    }
}
