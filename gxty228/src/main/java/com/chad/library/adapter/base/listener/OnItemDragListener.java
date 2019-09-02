package com.chad.library.adapter.base.listener;

import android.support.v7.widget.RecyclerView.ViewHolder;

public interface OnItemDragListener {
    void onItemDragEnd(ViewHolder viewHolder, int i);

    void onItemDragMoving(ViewHolder viewHolder, int i, ViewHolder viewHolder2, int i2);

    void onItemDragStart(ViewHolder viewHolder, int i);
}
