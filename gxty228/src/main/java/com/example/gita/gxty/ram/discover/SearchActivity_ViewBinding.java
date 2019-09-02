package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class SearchActivity_ViewBinding implements Unbinder {
    private SearchActivity a;

    @UiThread
    public SearchActivity_ViewBinding(SearchActivity searchActivity, View view) {
        this.a = searchActivity;
        searchActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
    }

    @CallSuper
    public void unbind() {
        SearchActivity searchActivity = this.a;
        if (searchActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        searchActivity.titleBar = null;
    }
}
