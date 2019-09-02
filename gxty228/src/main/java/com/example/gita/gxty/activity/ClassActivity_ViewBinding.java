package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class ClassActivity_ViewBinding implements Unbinder {
    private ClassActivity a;
    private View b;

    @UiThread
    public ClassActivity_ViewBinding(final ClassActivity classActivity, View view) {
        this.a = classActivity;
        classActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        classActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.select, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ClassActivity_ViewBinding b;

            public void doClick(View view) {
                classActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        ClassActivity classActivity = this.a;
        if (classActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        classActivity.titleBar = null;
        classActivity.mRecyclerView = null;
        this.b.setOnClickListener(null);
        this.b = null;
    }
}
