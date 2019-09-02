package com.baidu.mobads.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.NativeResponse.MaterialType;
import com.baidu.mobads.component.NativeVideoView.State;
import com.baidu.mobads.d.a;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

public class XNativeView extends FrameLayout implements OnClickListener, VideoPlayCallback {
    private static final String PLAY_ICON_STRING = "iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAMAAABg3Am1AAAA7VBMVEX////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////tpuCXAAAAT3RSTlMAoRzm3GoEvTPkiQ7RUakh3nIFwjrlkRLVWAGvJ+F4CMdBmBfYYQK2K+KAC8xIoBvbZKzPUH6rJs5PfKolTnuoJMtMeaciSgmlIMpJ4HajngnYGgAAAM5JREFUSMfl1cdaQlEMhdELmyJNiiBFDl0UzkW6SpMqRcD3fxye4R+T+RrkS7LjOPdcHi8E8vkDDEjBBwgUCkcYkKIxCKTHOARKJFMMSE9pCJR5zjIg5fIFBqSXIgQypTIDUqUKgVSrQ6DGa5MB6e0dArXalgHJ7UAgfXQhUK8PwaCIwHCEejBji5r+/EJz+J6gSZupRbs0m6NtdRfoHsyPRRe3XKGbXm9QapitRbm0+0XJt96jbDWlA0rv4wn9h78z+kCXaxn9uP+Tczd1AyMAFRYpWtU+AAAAAElFTkSuQmCC";
    private static final String TAG = "XNativeView";
    private static final int VISIBAL_AREA_PERSENT = 50;
    private Handler handler = new Handler();
    private Button mActionButton;
    private ImageView mActionIcon;
    private ImageView mBigPic;
    private NativeResponse mCurrentNativeItem;
    private View mFloatView;
    private NativeVideoView mVideoView;

    public XNativeView(Context context) {
        super(context);
        initViewIfNotInit();
        XNativeViewManager.getInstance().addItem(this);
    }

    public XNativeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViewIfNotInit();
        XNativeViewManager.getInstance().addItem(this);
    }

    public XNativeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViewIfNotInit();
        XNativeViewManager.getInstance().addItem(this);
    }

    private void initViewIfNotInit() {
        if (this.mBigPic == null) {
            this.mBigPic = new ImageView(getContext());
            this.mBigPic.setScaleType(ScaleType.FIT_XY);
            addView(this.mBigPic, new LayoutParams(-1, -1));
            this.mBigPic.setVisibility(8);
        }
        if (this.mVideoView == null) {
            this.mVideoView = new NativeVideoView(getContext());
            addView(this.mVideoView, new LayoutParams(-1, -1));
            this.mVideoView.keepState(true);
            this.mVideoView.setVideoPlayCallback(this);
            this.mVideoView.setVisibility(8);
        }
        if (this.mActionIcon == null) {
            this.mActionIcon = new ImageView(getContext());
            this.mActionIcon.setImageBitmap(XAdSDKFoundationFacade.getInstance().getBitmapUtils().string2bitmap(PLAY_ICON_STRING));
            this.mActionIcon.setScaleType(ScaleType.FIT_XY);
            this.mActionIcon.setOnClickListener(this);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(80, 80);
            layoutParams.gravity = 17;
            addView(this.mActionIcon, layoutParams);
        }
    }

    public void bindActivity(Activity activity) {
        this.mVideoView.setActivity(activity);
    }

    public void setNativeItem(NativeResponse nativeResponse) {
        this.mCurrentNativeItem = nativeResponse;
        XNativeViewManager.getInstance().resetAllPlayer();
        renderView();
    }

    public void pause() {
        if (this.mVideoView != null && this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
            this.mActionIcon.setVisibility(0);
        }
    }

    public void resume() {
        if (this.mVideoView != null && this.mVideoView.getCurrentState() == State.PAUSED) {
            this.mVideoView.start();
            this.mActionIcon.setVisibility(8);
        }
    }

    public void onAttach() {
        Log.i(TAG, "onAttach");
        initViewIfNotInit();
        if (this.mFloatView != null) {
            this.mFloatView.setVisibility(8);
        }
        if (this.mActionButton != null) {
            this.mActionButton.setVisibility(8);
        }
    }

    public void onDetach() {
        Log.i(TAG, "onDetach");
        if (this.mFloatView != null) {
            this.mFloatView.setVisibility(8);
        }
        if (this.mActionButton != null) {
            this.mActionButton.setVisibility(8);
        }
    }

    private void renderView() {
        Log.i(TAG, "renderView");
        hideAllView();
        MaterialType e = this.mCurrentNativeItem.e();
        if (e == MaterialType.NORMAL) {
            this.mBigPic.setVisibility(0);
            this.mVideoView.setVisibility(8);
            this.mActionIcon.setVisibility(8);
            a.a().a(this.mBigPic, this.mCurrentNativeItem.a());
        } else if (e == MaterialType.VIDEO) {
            this.mActionIcon.setImageBitmap(XAdSDKFoundationFacade.getInstance().getBitmapUtils().string2bitmap(PLAY_ICON_STRING));
            this.mActionIcon.setVisibility(0);
            this.mVideoView.setVisibility(8);
            this.mBigPic.setVisibility(0);
            a.a().a(this.mBigPic, this.mCurrentNativeItem.a());
        }
    }

    public void onCloseVideo(int i) {
        Log.i(TAG, "onCloseVideo");
        if (this.mCurrentNativeItem != null) {
            this.mCurrentNativeItem.a(getContext(), i);
            hideAllView();
        }
    }

    public void onClickAd() {
        if (this.mCurrentNativeItem != null) {
            this.mCurrentNativeItem.c(getContext());
            if (this.mVideoView != null) {
                this.mCurrentNativeItem.a((View) this, this.mVideoView.getCurrentPosition());
            } else {
                this.mCurrentNativeItem.a((View) this);
            }
        }
    }

    public void onStart() {
        Log.i(TAG, "onStart");
        if (this.mCurrentNativeItem != null) {
            this.mCurrentNativeItem.a(getContext());
        }
        this.mActionIcon.setVisibility(8);
    }

    public void onPause(int i) {
        Log.i(TAG, "onPause");
        this.mActionIcon.setVisibility(0);
    }

    public void onStop() {
        Log.i(TAG, "onStop");
        this.mActionIcon.setVisibility(0);
    }

    public void onRelease() {
        Log.i(TAG, "onRelease");
        this.mVideoView.setVisibility(8);
        this.mActionIcon.setVisibility(0);
        this.mBigPic.setVisibility(0);
        if (this.mFloatView != null) {
            this.mFloatView.setVisibility(8);
        }
        if (this.mActionButton != null) {
            this.mActionButton.setVisibility(8);
        }
    }

    public void onFullScreen(int i) {
        Log.i(TAG, "onFullScreen");
        if (this.mCurrentNativeItem != null) {
            this.mCurrentNativeItem.b(getContext(), i);
        }
    }

    public void onPreparing() {
        Log.i(TAG, "onPreparing");
        this.mVideoView.setVisibility(0);
        this.mActionIcon.setVisibility(8);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i(TAG, "onCompletion");
        if (this.mCurrentNativeItem != null) {
            this.mCurrentNativeItem.b(getContext());
        }
        if (this.mVideoView != null && this.mVideoView.getCurrentState() == State.PLAYBACKCOMPLETED && isVisible(this, 50)) {
            showActionButton();
        }
    }

    public void onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.i(TAG, "onError");
        if (this.mCurrentNativeItem != null) {
            this.mCurrentNativeItem.a(getContext(), i, i2);
        }
        hideAllView();
        if (this.mBigPic != null) {
            this.mBigPic.setVisibility(0);
        }
        if (this.mCurrentNativeItem.e() == MaterialType.VIDEO) {
            this.mActionIcon.setVisibility(0);
        }
    }

    public void onClick(View view) {
        if (view instanceof ImageView) {
            play();
        } else if (view instanceof Button) {
            this.mCurrentNativeItem.a(view);
        }
    }

    private void showActionButton() {
        if (this.mActionButton == null) {
            this.mActionButton = new Button(getContext());
            int screenDensity = (int) (XAdSDKFoundationFacade.getInstance().getCommonUtils().getScreenDensity(getContext()) + 1.0f);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(screenDensity * 80, screenDensity * 28);
            layoutParams.gravity = 17;
            this.mActionButton.setLayoutParams(layoutParams);
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor("#3797F2"));
            gradientDrawable.setCornerRadius(10.0f);
            if (VERSION.SDK_INT < 16) {
                this.mActionButton.setBackgroundDrawable(gradientDrawable);
            } else {
                this.mActionButton.setBackground(gradientDrawable);
            }
            if (this.mCurrentNativeItem.b()) {
                this.mActionButton.setText("立即下载");
            } else {
                this.mActionButton.setText("查看详情");
            }
            this.mActionButton.setTextColor(-1);
            this.mActionButton.setTextSize(12.0f);
            this.mActionButton.setOnClickListener(this);
            addView(this.mActionButton);
        } else {
            this.mActionButton.setVisibility(0);
        }
        if (this.mFloatView == null) {
            this.mFloatView = new View(getContext());
            this.mFloatView.setBackgroundColor(Color.parseColor("#88000000"));
            addView(this.mFloatView, new LayoutParams(-1, -1));
            return;
        }
        this.mFloatView.setVisibility(0);
    }

    private void hideAllView() {
        this.mVideoView.setVisibility(8);
        this.mActionIcon.setVisibility(8);
        this.mBigPic.setVisibility(8);
        if (this.mActionButton != null) {
            this.mActionButton.setVisibility(8);
        }
        if (this.mFloatView != null) {
            this.mFloatView.setVisibility(8);
        }
    }

    private void play() {
        this.mVideoView.setVisibility(0);
        this.mActionIcon.setVisibility(8);
        this.mBigPic.setVisibility(8);
        State currentState = this.mVideoView.getCurrentState();
        Log.i(TAG, "准备播放：" + currentState);
        if (currentState == State.PAUSED) {
            this.mVideoView.start();
            return;
        }
        if (currentState != State.IDLE) {
            this.mVideoView.reset();
        }
        this.mVideoView.setVideoURI(Uri.parse(this.mCurrentNativeItem.d()));
    }

    private boolean shouldAutoPlay() {
        return this.mCurrentNativeItem.c() && XAdSDKFoundationFacade.getInstance().getSystemUtils().isWifiConnected(getContext().getApplicationContext()).booleanValue();
    }

    public void render() {
        if (isVisible(this, 50) && shouldAutoPlay() && !this.mVideoView.isPlaying()) {
            XNativeViewManager.getInstance().resetAllPlayer();
            Log.i(TAG, "开始自动播放");
            this.mVideoView.setVisibility(0);
            this.mActionIcon.setVisibility(8);
            this.mBigPic.setVisibility(8);
            this.mVideoView.releasePlayer();
            this.mVideoView.setVideoURI(Uri.parse(this.mCurrentNativeItem.d()));
        }
    }

    public void onScrollStateChanged(int i) {
        if (i == 0) {
            render();
        }
    }

    public void onScroll() {
        if (!isVisible(this, 50)) {
            pause();
        }
    }

    void resetPlayer() {
        if (this.mVideoView != null) {
            this.mVideoView.reset();
        }
    }

    private static boolean isVisible(View view, int i) {
        if (view == null || view.getVisibility() != 0 || view.getParent() == null) {
            return false;
        }
        Rect rect = new Rect();
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        long height = ((long) rect.height()) * ((long) rect.width());
        long height2 = ((long) view.getHeight()) * ((long) view.getWidth());
        if (height2 <= 0 || height * 100 < height2 * ((long) i)) {
            return false;
        }
        return true;
    }
}
