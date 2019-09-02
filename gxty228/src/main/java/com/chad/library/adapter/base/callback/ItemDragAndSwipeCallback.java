package com.chad.library.adapter.base.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class ItemDragAndSwipeCallback extends Callback {
    private BaseItemDraggableAdapter mAdapter;
    private int mDragMoveFlags = 15;
    private float mMoveThreshold = 0.1f;
    private int mSwipeMoveFlags = 32;
    private float mSwipeThreshold = 0.7f;

    public ItemDragAndSwipeCallback(BaseItemDraggableAdapter baseItemDraggableAdapter) {
        this.mAdapter = baseItemDraggableAdapter;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public boolean isItemViewSwipeEnabled() {
        return this.mAdapter.isItemSwipeEnable();
    }

    public void onSelectedChanged(ViewHolder viewHolder, int i) {
        if (i == 2 && !isViewCreateByAdapter(viewHolder)) {
            this.mAdapter.onItemDragStart(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, Boolean.valueOf(true));
        } else if (i == 1 && !isViewCreateByAdapter(viewHolder)) {
            this.mAdapter.onItemSwipeStart(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, Boolean.valueOf(true));
        }
        super.onSelectedChanged(viewHolder, i);
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (!isViewCreateByAdapter(viewHolder)) {
            if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support) != null && ((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support)).booleanValue()) {
                this.mAdapter.onItemDragEnd(viewHolder);
                viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, Boolean.valueOf(false));
            }
            if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support) != null && ((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support)).booleanValue()) {
                this.mAdapter.onItemSwipeClear(viewHolder);
                viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, Boolean.valueOf(false));
            }
        }
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        if (isViewCreateByAdapter(viewHolder)) {
            return Callback.makeMovementFlags(0, 0);
        }
        return Callback.makeMovementFlags(this.mDragMoveFlags, this.mSwipeMoveFlags);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        return viewHolder.getItemViewType() == viewHolder2.getItemViewType();
    }

    public void onMoved(RecyclerView recyclerView, ViewHolder viewHolder, int i, ViewHolder viewHolder2, int i2, int i3, int i4) {
        super.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
        this.mAdapter.onItemDragMoving(viewHolder, viewHolder2);
    }

    public void onSwiped(ViewHolder viewHolder, int i) {
        if (!isViewCreateByAdapter(viewHolder)) {
            this.mAdapter.onItemSwiped(viewHolder);
        }
    }

    public float getMoveThreshold(ViewHolder viewHolder) {
        return this.mMoveThreshold;
    }

    public float getSwipeThreshold(ViewHolder viewHolder) {
        return this.mSwipeThreshold;
    }

    public void setSwipeThreshold(float f) {
        this.mSwipeThreshold = f;
    }

    public void setMoveThreshold(float f) {
        this.mMoveThreshold = f;
    }

    public void setDragMoveFlags(int i) {
        this.mDragMoveFlags = i;
    }

    public void setSwipeMoveFlags(int i) {
        this.mSwipeMoveFlags = i;
    }

    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        super.onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, z);
        if (i == 1 && !isViewCreateByAdapter(viewHolder)) {
            View view = viewHolder.itemView;
            canvas.save();
            if (f > 0.0f) {
                canvas.clipRect((float) view.getLeft(), (float) view.getTop(), ((float) view.getLeft()) + f, (float) view.getBottom());
                canvas.translate((float) view.getLeft(), (float) view.getTop());
            } else {
                canvas.clipRect(((float) view.getRight()) + f, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
                canvas.translate(((float) view.getRight()) + f, (float) view.getTop());
            }
            this.mAdapter.onItemSwiping(canvas, viewHolder, f, f2, z);
            canvas.restore();
        }
    }

    private boolean isViewCreateByAdapter(ViewHolder viewHolder) {
        int itemViewType = viewHolder.getItemViewType();
        return itemViewType == BaseQuickAdapter.HEADER_VIEW || itemViewType == BaseQuickAdapter.LOADING_VIEW || itemViewType == BaseQuickAdapter.FOOTER_VIEW || itemViewType == BaseQuickAdapter.EMPTY_VIEW;
    }
}
