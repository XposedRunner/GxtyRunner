package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class TopicDetailActivity_ViewBinding implements Unbinder {
    private TopicDetailActivity a;

    @UiThread
    public TopicDetailActivity_ViewBinding(TopicDetailActivity topicDetailActivity, View view) {
        this.a = topicDetailActivity;
        topicDetailActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
        topicDetailActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
    }

    @CallSuper
    public void unbind() {
        TopicDetailActivity topicDetailActivity = this.a;
        if (topicDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        topicDetailActivity.mRecyclerView = null;
        topicDetailActivity.titleBar = null;
    }
}
