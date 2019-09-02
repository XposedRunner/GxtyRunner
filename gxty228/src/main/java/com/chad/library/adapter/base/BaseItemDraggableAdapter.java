package com.chad.library.adapter.base;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import com.chad.library.R;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import java.util.Collections;
import java.util.List;

public abstract class BaseItemDraggableAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final String ERROR_NOT_SAME_ITEMTOUCHHELPER = "Item drag and item swipe should pass the same ItemTouchHelper";
    private static final int NO_TOGGLE_VIEW = 0;
    protected boolean itemDragEnabled = false;
    protected boolean itemSwipeEnabled = false;
    protected boolean mDragOnLongPress = true;
    protected ItemTouchHelper mItemTouchHelper;
    protected OnItemDragListener mOnItemDragListener;
    protected OnItemSwipeListener mOnItemSwipeListener;
    protected OnLongClickListener mOnToggleViewLongClickListener;
    protected OnTouchListener mOnToggleViewTouchListener;
    protected int mToggleViewId = 0;

    public BaseItemDraggableAdapter(List<T> list) {
        super((List) list);
    }

    public BaseItemDraggableAdapter(int i, List<T> list) {
        super(i, list);
    }

    public void onBindViewHolder(K k, int i) {
        super.onBindViewHolder((BaseViewHolder) k, i);
        int itemViewType = k.getItemViewType();
        if (this.mItemTouchHelper != null && this.itemDragEnabled && itemViewType != BaseQuickAdapter.LOADING_VIEW && itemViewType != BaseQuickAdapter.HEADER_VIEW && itemViewType != BaseQuickAdapter.EMPTY_VIEW && itemViewType != BaseQuickAdapter.FOOTER_VIEW) {
            if (this.mToggleViewId != 0) {
                View view = k.getView(this.mToggleViewId);
                if (view != null) {
                    view.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
                    if (this.mDragOnLongPress) {
                        view.setOnLongClickListener(this.mOnToggleViewLongClickListener);
                        return;
                    } else {
                        view.setOnTouchListener(this.mOnToggleViewTouchListener);
                        return;
                    }
                }
                return;
            }
            k.itemView.setTag(R.id.BaseQuickAdapter_viewholder_support, k);
            k.itemView.setOnLongClickListener(this.mOnToggleViewLongClickListener);
        }
    }

    public void setToggleViewId(int i) {
        this.mToggleViewId = i;
    }

    public void setToggleDragOnLongPress(boolean z) {
        this.mDragOnLongPress = z;
        if (this.mDragOnLongPress) {
            this.mOnToggleViewTouchListener = null;
            this.mOnToggleViewLongClickListener = new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (BaseItemDraggableAdapter.this.mItemTouchHelper != null && BaseItemDraggableAdapter.this.itemDragEnabled) {
                        BaseItemDraggableAdapter.this.mItemTouchHelper.startDrag((ViewHolder) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                    }
                    return true;
                }
            };
            return;
        }
        this.mOnToggleViewTouchListener = new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) != 0 || BaseItemDraggableAdapter.this.mDragOnLongPress) {
                    return false;
                }
                if (BaseItemDraggableAdapter.this.mItemTouchHelper != null && BaseItemDraggableAdapter.this.itemDragEnabled) {
                    BaseItemDraggableAdapter.this.mItemTouchHelper.startDrag((ViewHolder) view.getTag(R.id.BaseQuickAdapter_viewholder_support));
                }
                return true;
            }
        };
        this.mOnToggleViewLongClickListener = null;
    }

    public void enableDragItem(@NonNull ItemTouchHelper itemTouchHelper) {
        enableDragItem(itemTouchHelper, 0, true);
    }

    public void enableDragItem(@NonNull ItemTouchHelper itemTouchHelper, int i, boolean z) {
        this.itemDragEnabled = true;
        this.mItemTouchHelper = itemTouchHelper;
        setToggleViewId(i);
        setToggleDragOnLongPress(z);
    }

    public void disableDragItem() {
        this.itemDragEnabled = false;
        this.mItemTouchHelper = null;
    }

    public boolean isItemDraggable() {
        return this.itemDragEnabled;
    }

    public void enableSwipeItem() {
        this.itemSwipeEnabled = true;
    }

    public void disableSwipeItem() {
        this.itemSwipeEnabled = false;
    }

    public boolean isItemSwipeEnable() {
        return this.itemSwipeEnabled;
    }

    public void setOnItemDragListener(OnItemDragListener onItemDragListener) {
        this.mOnItemDragListener = onItemDragListener;
    }

    public int getViewHolderPosition(ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() - getHeaderLayoutCount();
    }

    public void onItemDragStart(ViewHolder viewHolder) {
        if (this.mOnItemDragListener != null && this.itemDragEnabled) {
            this.mOnItemDragListener.onItemDragStart(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void onItemDragMoving(ViewHolder viewHolder, ViewHolder viewHolder2) {
        int viewHolderPosition = getViewHolderPosition(viewHolder);
        int viewHolderPosition2 = getViewHolderPosition(viewHolder2);
        if (inRange(viewHolderPosition) && inRange(viewHolderPosition2)) {
            int i;
            if (viewHolderPosition < viewHolderPosition2) {
                for (i = viewHolderPosition; i < viewHolderPosition2; i++) {
                    Collections.swap(this.mData, i, i + 1);
                }
            } else {
                for (i = viewHolderPosition; i > viewHolderPosition2; i--) {
                    Collections.swap(this.mData, i, i - 1);
                }
            }
            notifyItemMoved(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        }
        if (this.mOnItemDragListener != null && this.itemDragEnabled) {
            this.mOnItemDragListener.onItemDragMoving(viewHolder, viewHolderPosition, viewHolder2, viewHolderPosition2);
        }
    }

    public void onItemDragEnd(ViewHolder viewHolder) {
        if (this.mOnItemDragListener != null && this.itemDragEnabled) {
            this.mOnItemDragListener.onItemDragEnd(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void setOnItemSwipeListener(OnItemSwipeListener onItemSwipeListener) {
        this.mOnItemSwipeListener = onItemSwipeListener;
    }

    public void onItemSwipeStart(ViewHolder viewHolder) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.onItemSwipeStart(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void onItemSwipeClear(ViewHolder viewHolder) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.clearView(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void onItemSwiped(ViewHolder viewHolder) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.onItemSwiped(viewHolder, getViewHolderPosition(viewHolder));
        }
        int viewHolderPosition = getViewHolderPosition(viewHolder);
        if (inRange(viewHolderPosition)) {
            this.mData.remove(viewHolderPosition);
            notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }

    public void onItemSwiping(Canvas canvas, ViewHolder viewHolder, float f, float f2, boolean z) {
        if (this.mOnItemSwipeListener != null && this.itemSwipeEnabled) {
            this.mOnItemSwipeListener.onItemSwipeMoving(canvas, viewHolder, f, f2, z);
        }
    }

    private boolean inRange(int i) {
        return i >= 0 && i < this.mData.size();
    }
}
