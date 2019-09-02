package com.chad.library.adapter.base.listener;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView.ViewHolder;

public interface OnItemSwipeListener {
    void clearView(ViewHolder viewHolder, int i);

    void onItemSwipeMoving(Canvas canvas, ViewHolder viewHolder, float f, float f2, boolean z);

    void onItemSwipeStart(ViewHolder viewHolder, int i);

    void onItemSwiped(ViewHolder viewHolder, int i);
}
