package com.ajguan.library;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Scroller;
import com.ajguan.library.exception.ERVHRuntimeException;
import com.ajguan.library.view.SimpleLoadMoreView;
import com.ajguan.library.view.SimpleRefreshHeaderView;
import com.blankj.utilcode.constant.MemoryConstants;

public class EasyRefreshLayout extends ViewGroup {
    private static final float DRAG_RATE = 1.0f;
    private static final int INVALID_POINTER = -1;
    private static long SCROLL_TO_LOADING_DURATION = 500;
    private static int SCROLL_TO_REFRESH_DURATION = 250;
    private static int SCROLL_TO_TOP_DURATION = 800;
    private static long SHOW_COMPLETED_TIME = 500;
    private static long SHOW_SCROLL_DOWN_DURATION = 300;
    private static final int START_POSITION = 0;
    private static final String TAG = "EsayRefreshLayout";
    private int activePointerId;
    private int advanceCount;
    private Runnable autoRefreshRunnable;
    private AutoScroll autoScroll;
    private View contentView;
    private int currentOffsetTop;
    private Runnable delayToScrollTopRunnable;
    private EasyEvent easyEvent;
    private boolean hasMeasureHeaderView;
    private boolean hasMeasureLoadMoreView;
    private boolean hasSendCancelEvent;
    private int headerViewHight;
    private float initDownX;
    private float initDownY;
    private boolean isAutoRefresh;
    private boolean isBeginDragged;
    boolean isCanLoad;
    private boolean isEnablePullToRefresh;
    private boolean isLoading;
    private boolean isLoadingFail;
    private boolean isNotMoreLoading;
    private boolean isRecycerView;
    private boolean isRefreshing;
    private boolean isTouch;
    private MotionEvent lastEvent;
    private float lastMotionX;
    private float lastMotionY;
    private int lastOffsetTop;
    private LoadModel loadMoreModel;
    private int loadMoreViewHeight;
    private float mDistance;
    private LayoutInflater mInflater;
    private View mLoadMoreView;
    private RecyclerView mRecyclerView;
    private float offsetY;
    private double pull_resistance;
    private View refreshHeaderView;
    private State state;
    private int totalDragDistance;
    private int touchSlop;
    private float yDiff;

    private class AutoScroll implements Runnable {
        private int lastY;
        private Scroller scroller;

        public AutoScroll() {
            this.scroller = new Scroller(EasyRefreshLayout.this.getContext());
        }

        public void run() {
            boolean z = !this.scroller.computeScrollOffset() || this.scroller.isFinished();
            if (z) {
                stop();
                EasyRefreshLayout.this.onScrollFinish(true);
                return;
            }
            int currY = this.scroller.getCurrY();
            int i = currY - this.lastY;
            this.lastY = currY;
            EasyRefreshLayout.this.moveSpinner((float) i);
            EasyRefreshLayout.this.post(this);
            EasyRefreshLayout.this.onScrollFinish(false);
        }

        public void scrollTo(int i, int i2) {
            int access$900 = i - EasyRefreshLayout.this.currentOffsetTop;
            stop();
            if (access$900 != 0) {
                this.scroller.startScroll(0, 0, 0, access$900, i2);
                EasyRefreshLayout.this.post(this);
            }
        }

        private void stop() {
            EasyRefreshLayout.this.removeCallbacks(this);
            if (!this.scroller.isFinished()) {
                this.scroller.forceFinished(true);
            }
            this.lastY = 0;
        }
    }

    public interface LoadMoreEvent {
        void onLoadMore();
    }

    public interface OnRefreshListener {
        void onRefreshing();
    }

    public interface EasyEvent extends LoadMoreEvent, OnRefreshListener {
    }

    public interface Event {
        void complete();
    }

    public EasyRefreshLayout(Context context) {
        this(context, null);
    }

    public EasyRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.pull_resistance = 2.0d;
        this.state = State.RESET;
        this.isEnablePullToRefresh = true;
        this.hasMeasureHeaderView = false;
        this.isCanLoad = false;
        this.isLoading = false;
        this.isLoadingFail = false;
        this.loadMoreModel = LoadModel.COMMON_MODEL;
        this.advanceCount = 0;
        this.delayToScrollTopRunnable = new Runnable() {
            public void run() {
                EasyRefreshLayout.this.autoScroll.scrollTo(0, EasyRefreshLayout.SCROLL_TO_TOP_DURATION);
            }
        };
        this.autoRefreshRunnable = new Runnable() {
            public void run() {
                EasyRefreshLayout.this.isAutoRefresh = true;
                EasyRefreshLayout.this.changeState(State.PULL);
                EasyRefreshLayout.this.autoScroll.scrollTo(EasyRefreshLayout.this.totalDragDistance, EasyRefreshLayout.SCROLL_TO_REFRESH_DURATION);
            }
        };
        initParameter(context, attributeSet);
    }

    private void initParameter(Context context, AttributeSet attributeSet) {
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setRefreshHeadView(getDefaultRefreshView());
        setLoadMoreView(getDefaultLoadMoreView());
        this.autoScroll = new AutoScroll();
    }

    public void setRefreshHeadView(View view) {
        if (!(view == null || view == this.refreshHeaderView)) {
            removeView(this.refreshHeaderView);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new LayoutParams(-1, -2));
        }
        this.refreshHeaderView = view;
        addView(this.refreshHeaderView);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.contentView == null) {
            initContentView();
        }
        if (this.contentView != null) {
            this.contentView.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), MemoryConstants.GB), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), MemoryConstants.GB));
            measureChild(this.refreshHeaderView, i, i2);
            if (!this.hasMeasureHeaderView) {
                this.hasMeasureHeaderView = true;
                this.headerViewHight = this.refreshHeaderView.getMeasuredHeight();
                this.totalDragDistance = this.headerViewHight;
            }
            measureChild(this.mLoadMoreView, i, i2);
            if (!this.hasMeasureLoadMoreView) {
                this.hasMeasureLoadMoreView = true;
                this.loadMoreViewHeight = this.mLoadMoreView.getMeasuredHeight();
            }
        }
    }

    private void initContentView() {
        if (this.contentView == null) {
            int i = 0;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.refreshHeaderView) || childAt.equals(this.mLoadMoreView)) {
                    i++;
                } else {
                    this.contentView = childAt;
                    if (this.contentView instanceof RecyclerView) {
                        this.isRecycerView = true;
                    } else {
                        this.isRecycerView = false;
                    }
                }
            }
        }
        if (this.isRecycerView) {
            initERVH();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            throw new RuntimeException("child view can not be empty");
        }
        if (this.contentView == null) {
            initContentView();
        }
        if (this.contentView == null) {
            throw new RuntimeException("main content of view can not be empty ");
        }
        View view = this.contentView;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop() + this.currentOffsetTop;
        measuredHeight = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, paddingTop + measuredHeight);
        int measuredWidth2 = this.refreshHeaderView.getMeasuredWidth();
        this.refreshHeaderView.layout((measuredWidth / 2) - (measuredWidth2 / 2), (-this.headerViewHight) + this.currentOffsetTop, (measuredWidth2 / 2) + (measuredWidth / 2), this.currentOffsetTop);
        measuredWidth2 = this.mLoadMoreView.getMeasuredWidth();
        this.mLoadMoreView.layout((measuredWidth / 2) - (measuredWidth2 / 2), measuredHeight, (measuredWidth / 2) + (measuredWidth2 / 2), this.loadMoreViewHeight + measuredHeight);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.isLoading || this.contentView == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float x;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.mDistance = 0.0f;
                this.activePointerId = motionEvent.getPointerId(0);
                this.isTouch = true;
                this.hasSendCancelEvent = false;
                this.isBeginDragged = false;
                this.lastOffsetTop = this.currentOffsetTop;
                this.currentOffsetTop = this.contentView.getTop();
                x = motionEvent.getX(0);
                this.lastMotionX = x;
                this.initDownX = x;
                x = motionEvent.getY(0);
                this.lastMotionY = x;
                this.initDownY = x;
                this.autoScroll.stop();
                removeCallbacks(this.delayToScrollTopRunnable);
                removeCallbacks(this.autoRefreshRunnable);
                super.dispatchTouchEvent(motionEvent);
                return true;
            case 1:
            case 3:
                if (this.currentOffsetTop > 0) {
                    finishSpinner();
                }
                this.isTouch = false;
                this.activePointerId = -1;
                break;
            case 2:
                if (this.activePointerId == -1) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                this.autoScroll.stop();
                this.lastEvent = motionEvent;
                x = motionEvent.getX(MotionEventCompat.findPointerIndex(motionEvent, this.activePointerId));
                float y = motionEvent.getY(MotionEventCompat.findPointerIndex(motionEvent, this.activePointerId));
                float f = x - this.lastMotionX;
                this.yDiff = y - this.lastMotionY;
                this.mDistance += this.yDiff;
                this.offsetY = this.yDiff * 1.0f;
                this.lastMotionX = x;
                this.lastMotionY = y;
                if (Math.abs(f) <= ((float) this.touchSlop)) {
                    if (!this.isBeginDragged && Math.abs(y - this.initDownY) > ((float) this.touchSlop)) {
                        this.isBeginDragged = true;
                    }
                    if (this.isBeginDragged) {
                        boolean z2 = this.offsetY > 0.0f;
                        boolean z3;
                        if (canChildScrollUp()) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                        boolean z4;
                        if (z2) {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        if (this.currentOffsetTop > 0) {
                            z = true;
                        }
                        if ((z2 && r4) || (r3 && r2)) {
                            moveSpinner(this.offsetY);
                            return true;
                        }
                    }
                }
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                if (actionIndex >= 0) {
                    this.lastMotionX = motionEvent.getX(actionIndex);
                    this.lastMotionY = motionEvent.getY(actionIndex);
                    this.activePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    break;
                }
                return super.dispatchTouchEvent(motionEvent);
            case 6:
                onSecondaryPointerUp(motionEvent);
                this.lastMotionY = motionEvent.getY(motionEvent.findPointerIndex(this.activePointerId));
                this.lastMotionX = motionEvent.getX(motionEvent.findPointerIndex(this.activePointerId));
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.activePointerId) {
            actionIndex = actionIndex == 0 ? 1 : 0;
            this.lastMotionY = motionEvent.getY(actionIndex);
            this.lastMotionX = motionEvent.getX(actionIndex);
            this.activePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
        }
    }

    private void moveSpinner(float f) {
        if (this.isEnablePullToRefresh) {
            int round = Math.round(f);
            if (round != 0) {
                if (!this.hasSendCancelEvent && this.isTouch && this.currentOffsetTop > 0) {
                    sendCancelEvent();
                    this.hasSendCancelEvent = true;
                }
                round = Math.max(0, round + this.currentOffsetTop);
                int i = round - this.currentOffsetTop;
                float f2 = (float) this.totalDragDistance;
                float max = Math.max(0.0f, Math.min((float) (round - this.totalDragDistance), 2.0f * f2) / f2);
                max = (float) (((double) max) - Math.pow(((double) max) / this.pull_resistance, 2.0d));
                if (i > 0) {
                    i = (int) (((float) i) * (1.0f - max));
                    round = Math.max(0, this.currentOffsetTop + i);
                }
                if (this.state == State.RESET && this.currentOffsetTop == 0 && round > 0) {
                    if (this.isNotMoreLoading || this.isLoadingFail) {
                        closeLoadView();
                    }
                    changeState(State.PULL);
                }
                if (this.currentOffsetTop > 0 && round <= 0 && (this.state == State.PULL || this.state == State.COMPLETE)) {
                    changeState(State.RESET);
                }
                if (this.state == State.PULL && !this.isTouch && this.currentOffsetTop > this.totalDragDistance && round <= this.totalDragDistance) {
                    this.autoScroll.stop();
                    changeState(State.REFRESHING);
                    if (this.easyEvent != null) {
                        this.isRefreshing = true;
                        this.easyEvent.onRefreshing();
                    }
                    i += this.totalDragDistance - round;
                }
                setTargetOffsetTopAndBottom(i);
                if (this.refreshHeaderView instanceof IRefreshHeader) {
                    ((IRefreshHeader) this.refreshHeaderView).onPositionChange((float) this.currentOffsetTop, (float) this.lastOffsetTop, (float) this.totalDragDistance, this.isTouch, this.state);
                }
            }
        }
    }

    private void finishSpinner() {
        if (this.state != State.REFRESHING) {
            this.autoScroll.scrollTo(0, SCROLL_TO_TOP_DURATION);
        } else if (this.currentOffsetTop > this.totalDragDistance) {
            this.autoScroll.scrollTo(this.totalDragDistance, SCROLL_TO_REFRESH_DURATION);
        }
    }

    private boolean canChildScrollUp() {
        boolean z = false;
        if (VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(this.contentView, -1);
        }
        if (this.contentView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.contentView;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        } else {
            if (ViewCompat.canScrollVertically(this.contentView, -1) || this.contentView.getScrollY() > 0) {
                z = true;
            }
            return z;
        }
    }

    private void setTargetOffsetTopAndBottom(int i) {
        if (i != 0) {
            this.contentView.offsetTopAndBottom(i);
            this.refreshHeaderView.offsetTopAndBottom(i);
            this.lastOffsetTop = this.currentOffsetTop;
            this.currentOffsetTop = this.contentView.getTop();
            invalidate();
        }
    }

    private void sendCancelEvent() {
        if (this.lastEvent != null) {
            MotionEvent obtain = MotionEvent.obtain(this.lastEvent);
            obtain.setAction(3);
            super.dispatchTouchEvent(obtain);
        }
    }

    private void changeState(State state) {
        this.state = state;
        IRefreshHeader iRefreshHeader = this.refreshHeaderView instanceof IRefreshHeader ? (IRefreshHeader) this.refreshHeaderView : null;
        if (iRefreshHeader != null) {
            switch (state) {
                case RESET:
                    iRefreshHeader.reset();
                    return;
                case PULL:
                    iRefreshHeader.pull();
                    return;
                case REFRESHING:
                    iRefreshHeader.refreshing();
                    return;
                case COMPLETE:
                    iRefreshHeader.complete();
                    return;
                default:
                    return;
            }
        }
    }

    public void refreshComplete() {
        this.isRefreshing = false;
        changeState(State.COMPLETE);
        if (this.currentOffsetTop == 0) {
            changeState(State.RESET);
        } else if (!this.isTouch) {
            postDelayed(this.delayToScrollTopRunnable, SHOW_COMPLETED_TIME);
        }
    }

    public void autoRefresh() {
        autoRefresh(500);
    }

    public void autoRefresh(long j) {
        if (this.state == State.RESET) {
            postDelayed(this.autoRefreshRunnable, j);
        }
    }

    public View getDefaultRefreshView() {
        return new SimpleRefreshHeaderView(getContext());
    }

    private void onScrollFinish(boolean z) {
        if (this.isAutoRefresh && !z) {
            this.isAutoRefresh = false;
            changeState(State.REFRESHING);
            if (this.easyEvent != null) {
                this.easyEvent.onRefreshing();
            }
            finishSpinner();
        }
    }

    public void addEasyEvent(EasyEvent easyEvent) {
        if (easyEvent == null) {
            throw new ERVHRuntimeException("adapter can not be null");
        }
        this.easyEvent = easyEvent;
    }

    public boolean isEnablePullToRefresh() {
        return this.isEnablePullToRefresh;
    }

    public void setEnablePullToRefresh(boolean z) {
        this.isEnablePullToRefresh = z;
    }

    public boolean isRefreshing() {
        return this.isRefreshing;
    }

    public void setRefreshing(boolean z) {
        if (z) {
            changeState(State.REFRESHING);
            if (this.isNotMoreLoading || this.isLoadingFail) {
                closeLoadView();
            }
        }
        changeState(State.RESET);
    }

    private void initERVH() {
        if (this.mLoadMoreView == null) {
            getDefaultLoadMoreView();
            setLoadMoreView(this.mLoadMoreView);
        }
        if (this.isRecycerView) {
            this.mRecyclerView = (RecyclerView) this.contentView;
            this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    if (EasyRefreshLayout.this.loadMoreModel == LoadModel.ADVANCE_MODEL && !EasyRefreshLayout.this.isLoading && !EasyRefreshLayout.this.isRefreshing && !EasyRefreshLayout.this.isLoadingFail && !EasyRefreshLayout.this.isNotMoreLoading) {
                        int access$1500 = EasyRefreshLayout.this.getLastVisiBleItem();
                        int itemCount = EasyRefreshLayout.this.mRecyclerView.getLayoutManager().getItemCount();
                        int childCount = EasyRefreshLayout.this.mRecyclerView.getLayoutManager().getChildCount();
                        if (childCount > 0 && access$1500 >= (itemCount - 1) - EasyRefreshLayout.this.advanceCount && itemCount >= childCount) {
                            EasyRefreshLayout.this.isCanLoad = true;
                        }
                        if (EasyRefreshLayout.this.isCanLoad) {
                            EasyRefreshLayout.this.isCanLoad = false;
                            EasyRefreshLayout.this.isLoading = true;
                            if (EasyRefreshLayout.this.easyEvent != null) {
                                EasyRefreshLayout.this.easyEvent.onLoadMore();
                            }
                        }
                    }
                }

                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (EasyRefreshLayout.this.loadMoreModel != LoadModel.ADVANCE_MODEL && Math.abs(EasyRefreshLayout.this.mDistance) > ((float) EasyRefreshLayout.this.touchSlop) && EasyRefreshLayout.this.mDistance < 0.0f && !EasyRefreshLayout.this.isLoading && EasyRefreshLayout.this.loadMoreModel == LoadModel.COMMON_MODEL && !EasyRefreshLayout.this.isRefreshing && !EasyRefreshLayout.this.isLoadingFail && !EasyRefreshLayout.this.isNotMoreLoading) {
                        int access$1500 = EasyRefreshLayout.this.getLastVisiBleItem();
                        int itemCount = EasyRefreshLayout.this.mRecyclerView.getLayoutManager().getItemCount();
                        int childCount = EasyRefreshLayout.this.mRecyclerView.getLayoutManager().getChildCount();
                        if (childCount > 0 && access$1500 >= itemCount - 1 && itemCount >= childCount) {
                            EasyRefreshLayout.this.isCanLoad = true;
                        }
                        if (EasyRefreshLayout.this.isCanLoad) {
                            EasyRefreshLayout.this.isCanLoad = false;
                            EasyRefreshLayout.this.isLoading = true;
                            ((ILoadMoreView) EasyRefreshLayout.this.mLoadMoreView).reset();
                            EasyRefreshLayout.this.mLoadMoreView.measure(0, 0);
                            ((ILoadMoreView) EasyRefreshLayout.this.mLoadMoreView).loading();
                            EasyRefreshLayout.this.showLoadView();
                        }
                    }
                }
            });
        }
    }

    private void showLoadView() {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, -this.mLoadMoreView.getMeasuredHeight()});
        ofInt.setTarget(this.mLoadMoreView);
        ofInt.addUpdateListener(new AnimatorUpdateListener() {
            private int lastDs;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                this.lastDs = intValue;
                EasyRefreshLayout.this.mLoadMoreView.bringToFront();
                EasyRefreshLayout.this.mLoadMoreView.setTranslationY((float) intValue);
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (EasyRefreshLayout.this.easyEvent != null) {
                    EasyRefreshLayout.this.easyEvent.onLoadMore();
                }
            }
        });
        ofInt.setDuration(SCROLL_TO_LOADING_DURATION);
        ofInt.start();
    }

    private void hideLoadView() {
        if (this.mLoadMoreView != null && this.isRecycerView) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, this.mLoadMoreView.getMeasuredHeight()});
            ofInt.setTarget(this.mLoadMoreView);
            ofInt.addUpdateListener(new AnimatorUpdateListener() {
                private int lastDs;

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    this.lastDs = intValue;
                    EasyRefreshLayout.this.mLoadMoreView.bringToFront();
                    EasyRefreshLayout.this.mLoadMoreView.setTranslationY((float) intValue);
                }
            });
            ofInt.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    EasyRefreshLayout.this.isLoading = false;
                }

                public void onAnimationCancel(Animator animator) {
                    EasyRefreshLayout.this.isLoading = false;
                }

                public void onAnimationRepeat(Animator animator) {
                    EasyRefreshLayout.this.isLoading = false;
                }
            });
            ofInt.setDuration(SHOW_SCROLL_DOWN_DURATION);
            ofInt.start();
        }
    }

    public void closeLoadView() {
        if (this.loadMoreModel == LoadModel.ADVANCE_MODEL) {
            throw new RuntimeException("enableAdance Model cant not called closeLoadView method");
        } else if (this.mLoadMoreView != null && this.isRecycerView) {
            this.mLoadMoreView.bringToFront();
            this.mLoadMoreView.setTranslationY((float) this.mLoadMoreView.getMeasuredHeight());
            resetLoadMoreState();
        }
    }

    public View getLoadMoreView() {
        return getDefaultLoadMoreView();
    }

    public void setLoadMoreView(View view) {
        if (view == null) {
            throw new ERVHRuntimeException("loadMoreView can not be null");
        }
        if (!(view == null || view == this.mLoadMoreView)) {
            removeView(this.mLoadMoreView);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new LayoutParams(-1, -2));
        }
        this.mLoadMoreView = view;
        addView(this.mLoadMoreView);
        resetLoadMoreState();
        ((ILoadMoreView) this.mLoadMoreView).reset();
        ((ILoadMoreView) this.mLoadMoreView).getCanClickFailView().setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (EasyRefreshLayout.this.isLoadingFail && EasyRefreshLayout.this.easyEvent != null) {
                    EasyRefreshLayout.this.isLoading = true;
                    ((ILoadMoreView) EasyRefreshLayout.this.mLoadMoreView).loading();
                    EasyRefreshLayout.this.easyEvent.onLoadMore();
                }
            }
        });
    }

    public void loadMoreComplete() {
        if (this.loadMoreModel == LoadModel.ADVANCE_MODEL) {
            this.isLoading = false;
        } else if (this.loadMoreModel == LoadModel.COMMON_MODEL) {
            loadMoreComplete(null);
        }
    }

    @Deprecated
    public void loadMoreComplete(Event event) {
        if (this.loadMoreModel == LoadModel.ADVANCE_MODEL) {
            throw new RuntimeException("enableAdance Model cant not called closeLoadView method");
        }
        loadMoreComplete(event, 500);
    }

    @Deprecated
    public void loadMoreComplete(final Event event, long j) {
        if (this.loadMoreModel == LoadModel.ADVANCE_MODEL) {
            throw new RuntimeException("enableAdance Model cant not called closeLoadView method");
        }
        ((ILoadMoreView) this.mLoadMoreView).loadComplete();
        if (event == null) {
            hideLoadView();
            resetLoadMoreState();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                event.complete();
                EasyRefreshLayout.this.hideLoadView();
                EasyRefreshLayout.this.resetLoadMoreState();
            }
        }, j);
    }

    private void resetLoadMoreState() {
        this.isCanLoad = false;
        this.isLoading = false;
        this.isLoadingFail = false;
        this.isNotMoreLoading = false;
    }

    public void loadMoreFail() {
        if (this.loadMoreModel == LoadModel.ADVANCE_MODEL) {
            throw new RuntimeException("enableAdance Model cant not called closeLoadView method");
        }
        ((ILoadMoreView) this.mLoadMoreView).loadFail();
        resetLoadMoreState();
        this.isLoadingFail = true;
    }

    public void loadNothing() {
        if (this.loadMoreModel == LoadModel.ADVANCE_MODEL) {
            throw new RuntimeException("enableAdance Model cant not called closeLoadView method");
        }
        ((ILoadMoreView) this.mLoadMoreView).loadNothing();
        resetLoadMoreState();
        this.isNotMoreLoading = true;
    }

    private View getDefaultLoadMoreView() {
        return new SimpleLoadMoreView(getContext());
    }

    private int getLastVisiBleItem() {
        Object obj;
        LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            obj = 1;
        } else if (layoutManager instanceof LinearLayoutManager) {
            obj = null;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            obj = 2;
        } else {
            throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
        }
        switch (obj) {
            case null:
                return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            case 1:
                return ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            case 2:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int[] iArr = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(iArr);
                return findMax(iArr);
            default:
                return -1;
        }
    }

    private int findMax(int[] iArr) {
        int i = iArr[0];
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i3 <= i) {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    public boolean isEnableLoadMore() {
        return this.loadMoreModel == LoadModel.COMMON_MODEL || this.loadMoreModel == LoadModel.ADVANCE_MODEL;
    }

    public LoadModel getLoadMoreModel() {
        return this.loadMoreModel;
    }

    public void setLoadMoreModel(LoadModel loadModel, int i) {
        this.loadMoreModel = loadModel;
        this.advanceCount = i;
    }

    public int getAdvanceCount() {
        return this.advanceCount;
    }

    public void setAdvanceCount(int i) {
        this.advanceCount = i;
    }

    public void setLoadMoreModel(LoadModel loadModel) {
        setLoadMoreModel(loadModel, 0);
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public long getShowLoadViewAnimatorDuration() {
        return SCROLL_TO_LOADING_DURATION;
    }

    public void setShowLoadViewAnimatorDuration(long j) {
        SCROLL_TO_LOADING_DURATION = j;
    }

    public int getScrollToRefreshDuration() {
        return SCROLL_TO_REFRESH_DURATION;
    }

    public void setScrollToRefreshDuration(int i) {
        SCROLL_TO_REFRESH_DURATION = i;
    }

    public int getScrollToTopDuration() {
        return SCROLL_TO_TOP_DURATION;
    }

    public void setScrollToTopDuration(int i) {
        SCROLL_TO_TOP_DURATION = i;
    }

    public long getHideLoadViewAnimatorDuration() {
        return SHOW_COMPLETED_TIME;
    }

    public void setHideLoadViewAnimatorDuration(long j) {
        SHOW_COMPLETED_TIME = j;
    }

    public double getPullResistance() {
        return this.pull_resistance;
    }

    public void setPullResistance(double d) {
        this.pull_resistance = d;
    }
}
