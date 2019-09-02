package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class CourseActivity_ViewBinding implements Unbinder {
    private CourseActivity a;

    @UiThread
    public CourseActivity_ViewBinding(CourseActivity courseActivity, View view) {
        this.a = courseActivity;
        courseActivity.my_course = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.my_course, "field 'my_course'", LinearLayout.class);
        courseActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        courseActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'mRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        CourseActivity courseActivity = this.a;
        if (courseActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        courseActivity.my_course = null;
        courseActivity.titleBar = null;
        courseActivity.mRecyclerView = null;
    }
}
