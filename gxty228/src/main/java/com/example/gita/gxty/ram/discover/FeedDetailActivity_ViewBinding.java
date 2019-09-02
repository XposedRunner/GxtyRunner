package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class FeedDetailActivity_ViewBinding implements Unbinder {
    private FeedDetailActivity a;
    private View b;

    @UiThread
    public FeedDetailActivity_ViewBinding(final FeedDetailActivity feedDetailActivity, View view) {
        this.a = feedDetailActivity;
        feedDetailActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
        feedDetailActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        feedDetailActivity.acitonLayout = Utils.findRequiredView(view, R.id.acitonLayout, "field 'acitonLayout'");
        feedDetailActivity.commentTxt = (EditText) Utils.findRequiredViewAsType(view, R.id.commentTxt, "field 'commentTxt'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.actionBtn, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ FeedDetailActivity_ViewBinding b;

            public void doClick(View view) {
                feedDetailActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        FeedDetailActivity feedDetailActivity = this.a;
        if (feedDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        feedDetailActivity.mRecyclerView = null;
        feedDetailActivity.titleBar = null;
        feedDetailActivity.acitonLayout = null;
        feedDetailActivity.commentTxt = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
