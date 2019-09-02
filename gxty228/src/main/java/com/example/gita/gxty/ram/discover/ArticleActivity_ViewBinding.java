package com.example.gita.gxty.ram.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class ArticleActivity_ViewBinding implements Unbinder {
    private ArticleActivity a;

    @UiThread
    public ArticleActivity_ViewBinding(ArticleActivity articleActivity, View view) {
        this.a = articleActivity;
        articleActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
    }

    @CallSuper
    public void unbind() {
        ArticleActivity articleActivity = this.a;
        if (articleActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        articleActivity.titleBar = null;
    }
}
