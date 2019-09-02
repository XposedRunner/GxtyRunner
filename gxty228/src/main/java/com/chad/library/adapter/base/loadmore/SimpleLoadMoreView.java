package com.chad.library.adapter.base.loadmore;

import com.chad.library.R;

public final class SimpleLoadMoreView extends LoadMoreView {
    public int getLayoutId() {
        return R.layout.quick_view_load_more;
    }

    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
