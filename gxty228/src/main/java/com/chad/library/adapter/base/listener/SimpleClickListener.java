package com.chad.library.adapter.base.listener;

import android.os.Build.VERSION;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.Set;

public abstract class SimpleClickListener implements OnItemTouchListener {
    public static String TAG = "SimpleClickListener";
    protected BaseQuickAdapter baseQuickAdapter;
    private GestureDetectorCompat mGestureDetector;
    private boolean mIsPrepressed = false;
    private boolean mIsShowPress = false;
    private View mPressedView = null;
    private RecyclerView recyclerView;

    private class ItemTouchHelperGestureListener implements OnGestureListener {
        private RecyclerView recyclerView;

        public boolean onDown(MotionEvent motionEvent) {
            SimpleClickListener.this.mIsPrepressed = true;
            SimpleClickListener.this.mPressedView = this.recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                SimpleClickListener.this.mIsShowPress = true;
            }
        }

        ItemTouchHelperGestureListener(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                if (this.recyclerView.getScrollState() != 0) {
                    return false;
                }
                View access$100 = SimpleClickListener.this.mPressedView;
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(access$100);
                if (SimpleClickListener.this.isHeaderOrFooterPosition(baseViewHolder.getLayoutPosition())) {
                    return false;
                }
                Set<Integer> childClickViewIds = baseViewHolder.getChildClickViewIds();
                Set nestViews = baseViewHolder.getNestViews();
                View findViewById;
                if (childClickViewIds == null || childClickViewIds.size() <= 0) {
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, access$100);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    if (childClickViewIds != null && childClickViewIds.size() > 0) {
                        for (Integer intValue : childClickViewIds) {
                            findViewById = access$100.findViewById(intValue.intValue());
                            if (findViewById != null) {
                                findViewById.setPressed(false);
                            }
                        }
                    }
                    SimpleClickListener.this.onItemClick(SimpleClickListener.this.baseQuickAdapter, access$100, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                } else {
                    for (Integer intValue2 : childClickViewIds) {
                        View findViewById2 = access$100.findViewById(intValue2.intValue());
                        if (findViewById2 != null) {
                            if (!SimpleClickListener.this.inRangeOfView(findViewById2, motionEvent) || !findViewById2.isEnabled()) {
                                findViewById2.setPressed(false);
                            } else if (nestViews != null && nestViews.contains(intValue2)) {
                                return false;
                            } else {
                                SimpleClickListener.this.setPressViewHotSpot(motionEvent, findViewById2);
                                findViewById2.setPressed(true);
                                SimpleClickListener.this.onItemChildClick(SimpleClickListener.this.baseQuickAdapter, findViewById2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                                resetPressedView(findViewById2);
                                return true;
                            }
                        }
                    }
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, access$100);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    for (Integer intValue22 : childClickViewIds) {
                        findViewById = access$100.findViewById(intValue22.intValue());
                        if (findViewById != null) {
                            findViewById.setPressed(false);
                        }
                    }
                    SimpleClickListener.this.onItemClick(SimpleClickListener.this.baseQuickAdapter, access$100, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                }
                resetPressedView(access$100);
            }
            return true;
        }

        private void resetPressedView(final View view) {
            if (view != null) {
                view.postDelayed(new Runnable() {
                    public void run() {
                        if (view != null) {
                            view.setPressed(false);
                        }
                    }
                }, 50);
            }
            SimpleClickListener.this.mIsPrepressed = false;
            SimpleClickListener.this.mPressedView = null;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.recyclerView.getScrollState() == 0 && SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                SimpleClickListener.this.mPressedView.performHapticFeedback(0);
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(SimpleClickListener.this.mPressedView);
                if (!SimpleClickListener.this.isHeaderOrFooterPosition(baseViewHolder.getLayoutPosition())) {
                    boolean z;
                    View findViewById;
                    Set<Integer> itemChildLongClickViewIds = baseViewHolder.getItemChildLongClickViewIds();
                    Set nestViews = baseViewHolder.getNestViews();
                    if (itemChildLongClickViewIds != null && itemChildLongClickViewIds.size() > 0) {
                        for (Integer num : itemChildLongClickViewIds) {
                            View findViewById2 = SimpleClickListener.this.mPressedView.findViewById(num.intValue());
                            if (SimpleClickListener.this.inRangeOfView(findViewById2, motionEvent) && findViewById2.isEnabled()) {
                                if (nestViews == null || !nestViews.contains(num)) {
                                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, findViewById2);
                                    SimpleClickListener.this.onItemChildLongClick(SimpleClickListener.this.baseQuickAdapter, findViewById2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                                    findViewById2.setPressed(true);
                                    SimpleClickListener.this.mIsShowPress = true;
                                    z = true;
                                } else {
                                    z = true;
                                }
                                if (!z) {
                                    SimpleClickListener.this.onItemLongClick(SimpleClickListener.this.baseQuickAdapter, SimpleClickListener.this.mPressedView, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, SimpleClickListener.this.mPressedView);
                                    SimpleClickListener.this.mPressedView.setPressed(true);
                                    if (itemChildLongClickViewIds != null) {
                                        for (Integer intValue : itemChildLongClickViewIds) {
                                            findViewById = SimpleClickListener.this.mPressedView.findViewById(intValue.intValue());
                                            if (findViewById != null) {
                                                findViewById.setPressed(false);
                                            }
                                        }
                                    }
                                    SimpleClickListener.this.mIsShowPress = true;
                                }
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                        SimpleClickListener.this.onItemLongClick(SimpleClickListener.this.baseQuickAdapter, SimpleClickListener.this.mPressedView, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                        SimpleClickListener.this.setPressViewHotSpot(motionEvent, SimpleClickListener.this.mPressedView);
                        SimpleClickListener.this.mPressedView.setPressed(true);
                        if (itemChildLongClickViewIds != null) {
                            while (r1.hasNext()) {
                                findViewById = SimpleClickListener.this.mPressedView.findViewById(intValue.intValue());
                                if (findViewById != null) {
                                    findViewById.setPressed(false);
                                }
                            }
                        }
                        SimpleClickListener.this.mIsShowPress = true;
                    }
                }
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }
    }

    public abstract void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.recyclerView == null) {
            this.recyclerView = recyclerView;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        } else if (this.recyclerView != recyclerView) {
            this.recyclerView = recyclerView;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        }
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && motionEvent.getActionMasked() == 1 && this.mIsShowPress) {
            if (this.mPressedView != null) {
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(this.mPressedView);
                if (baseViewHolder == null || !isHeaderOrFooterView(baseViewHolder.getItemViewType())) {
                    this.mPressedView.setPressed(false);
                }
            }
            this.mIsShowPress = false;
            this.mIsPrepressed = false;
        }
        return false;
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    private void setPressViewHotSpot(MotionEvent motionEvent, View view) {
        if (VERSION.SDK_INT >= 21 && view != null && view.getBackground() != null) {
            view.getBackground().setHotspot(motionEvent.getRawX(), motionEvent.getY() - view.getY());
        }
    }

    public boolean inRangeOfView(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        if (view == null || !view.isShown()) {
            return false;
        }
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        if (motionEvent.getRawX() < ((float) i) || motionEvent.getRawX() > ((float) (i + view.getWidth())) || motionEvent.getRawY() < ((float) i2) || motionEvent.getRawY() > ((float) (i2 + view.getHeight()))) {
            return false;
        }
        return true;
    }

    private boolean isHeaderOrFooterPosition(int i) {
        if (this.baseQuickAdapter == null) {
            if (this.recyclerView == null) {
                return false;
            }
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
        }
        int itemViewType = this.baseQuickAdapter.getItemViewType(i);
        boolean z = itemViewType == BaseQuickAdapter.EMPTY_VIEW || itemViewType == BaseQuickAdapter.HEADER_VIEW || itemViewType == BaseQuickAdapter.FOOTER_VIEW || itemViewType == BaseQuickAdapter.LOADING_VIEW;
        return z;
    }

    private boolean isHeaderOrFooterView(int i) {
        return i == BaseQuickAdapter.EMPTY_VIEW || i == BaseQuickAdapter.HEADER_VIEW || i == BaseQuickAdapter.FOOTER_VIEW || i == BaseQuickAdapter.LOADING_VIEW;
    }
}
