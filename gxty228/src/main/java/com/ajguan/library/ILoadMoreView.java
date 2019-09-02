package com.ajguan.library;

import android.view.View;

public interface ILoadMoreView {
    View getCanClickFailView();

    void loadComplete();

    void loadFail();

    void loadNothing();

    void loading();

    void reset();
}
