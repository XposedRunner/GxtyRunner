package com.baidu.mobads.component;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.io.IOException;

public class NativeVideoView extends RelativeLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnSeekCompleteListener, Callback {
    private static final String TAG = "FSVideoView";
    protected Activity activity;
    protected Context context;
    protected LayoutParams currentLayoutParams;
    protected State currentState;
    protected int initialConfigOrientation;
    protected int initialMovieHeight;
    protected int initialMovieWidth;
    boolean isCompleted = false;
    protected boolean isFullscreen;
    protected boolean keepState;
    protected State lastState;
    protected View loadingView;
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected VideoPlayCallback mVideoPlayCallback;
    protected MediaPlayer mediaPlayer;
    protected ViewGroup parentView;
    protected boolean shouldAutoplay;
    protected SurfaceHolder surfaceHolder;
    protected boolean surfaceIsReady;
    protected SurfaceView surfaceView;
    protected boolean videoIsReady;

    public enum State {
        IDLE,
        INITIALIZED,
        PREPARED,
        PREPARING,
        STARTED,
        STOPPED,
        PAUSED,
        PLAYBACKCOMPLETED,
        ERROR,
        END
    }

    public NativeVideoView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NativeVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        init();
    }

    public NativeVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        init();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onAttach();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onDetach();
        }
        if (!this.keepState) {
            release();
        }
    }

    public synchronized void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        this.mediaPlayer.setDisplay(this.surfaceHolder);
        if (!this.surfaceIsReady) {
            this.surfaceIsReady = true;
            if (!(this.currentState == State.PREPARED || this.currentState == State.PAUSED || this.currentState == State.STARTED || this.currentState == State.PLAYBACKCOMPLETED)) {
                tryToPrepare();
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mHandler.post(new a(this));
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            pause();
            if (this.mVideoPlayCallback != null) {
                this.mVideoPlayCallback.onPause(getCurrentPosition());
            }
        }
        this.surfaceIsReady = false;
    }

    public synchronized void onPrepared(MediaPlayer mediaPlayer) {
        if (this.mVideoPlayCallback == null) {
            this.mVideoPlayCallback.onPrepared(mediaPlayer);
        }
        this.videoIsReady = true;
        tryToPrepare();
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        stopLoading();
        if (this.lastState != null) {
            switch (c.a[this.lastState.ordinal()]) {
                case 1:
                    start();
                    if (this.mVideoPlayCallback != null && isPlaying()) {
                        this.mVideoPlayCallback.onStart();
                        break;
                    }
                case 2:
                    pause();
                    if (this.mVideoPlayCallback != null) {
                        this.mVideoPlayCallback.onPause(getCurrentPosition());
                        break;
                    }
                    break;
                case 3:
                    this.currentState = State.PLAYBACKCOMPLETED;
                    break;
                case 4:
                    this.currentState = State.PREPARED;
                    break;
            }
        }
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onSeekComplete(mediaPlayer);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.isCompleted = true;
        if (this.mediaPlayer.isLooping()) {
            start();
            if (this.mVideoPlayCallback != null && isPlaying()) {
                this.mVideoPlayCallback.onStart();
                return;
            }
            return;
        }
        this.mediaPlayer.getCurrentPosition();
        this.mediaPlayer.getDuration();
        this.currentState = State.PLAYBACKCOMPLETED;
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onCompletion(mediaPlayer);
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        stopLoading();
        this.currentState = State.ERROR;
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onError(mediaPlayer, i, i2);
        }
        return false;
    }

    protected void init() {
        if (!isInEditMode()) {
            this.shouldAutoplay = true;
            this.currentState = State.IDLE;
            this.isFullscreen = false;
            this.initialConfigOrientation = -1;
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.mediaPlayer = new MediaPlayer();
            this.surfaceView = new SurfaceView(this.context);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.surfaceView.setLayoutParams(layoutParams);
            addView(this.surfaceView);
            this.surfaceHolder = this.surfaceView.getHolder();
            this.surfaceHolder.setType(3);
            this.surfaceHolder.addCallback(this);
            this.loadingView = new ProgressBar(this.context);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.loadingView.setLayoutParams(layoutParams);
            addView(this.loadingView);
            setOnClickListener(new b(this));
        }
    }

    protected void keepState(boolean z) {
        this.keepState = z;
    }

    protected void releasePlayer() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setOnPreparedListener(null);
            this.mediaPlayer.setOnErrorListener(null);
            this.mediaPlayer.setOnSeekCompleteListener(null);
            this.mediaPlayer.setOnCompletionListener(null);
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.stop();
            }
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onRelease();
        }
        this.videoIsReady = false;
        this.currentState = State.END;
    }

    protected void release() {
        if (this.mediaPlayer != null) {
            if (!(this.currentState == State.IDLE || this.mVideoPlayCallback == null)) {
                this.mVideoPlayCallback.onCloseVideo(getCurrentPosition());
            }
            this.mediaPlayer.setOnPreparedListener(null);
            this.mediaPlayer.setOnErrorListener(null);
            this.mediaPlayer.setOnSeekCompleteListener(null);
            this.mediaPlayer.setOnCompletionListener(null);
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.stop();
                if (this.mVideoPlayCallback != null) {
                    this.mVideoPlayCallback.onStop();
                }
            }
            this.mediaPlayer.release();
            if (this.mVideoPlayCallback != null) {
                this.mVideoPlayCallback.onRelease();
            }
            this.mediaPlayer = null;
        }
        this.videoIsReady = false;
        this.surfaceIsReady = false;
        this.currentState = State.END;
    }

    protected void prepare() {
        startLoading();
        this.videoIsReady = false;
        this.initialMovieHeight = -1;
        this.initialMovieWidth = -1;
        this.mediaPlayer.setOnPreparedListener(this);
        this.mediaPlayer.setOnErrorListener(this);
        this.mediaPlayer.setOnSeekCompleteListener(this);
        this.mediaPlayer.setAudioStreamType(3);
        this.currentState = State.PREPARING;
        this.mediaPlayer.prepareAsync();
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onPreparing();
        }
    }

    protected void tryToPrepare() {
        if (this.surfaceIsReady && this.videoIsReady) {
            if (this.mediaPlayer != null) {
                this.mediaPlayer.setDisplay(this.surfaceHolder);
                this.initialMovieWidth = this.mediaPlayer.getVideoWidth();
                this.initialMovieHeight = this.mediaPlayer.getVideoHeight();
            }
            resize();
            stopLoading();
            this.currentState = State.PREPARED;
            if (this.shouldAutoplay) {
                start();
                if (this.mVideoPlayCallback != null && isPlaying()) {
                    this.mVideoPlayCallback.onStart();
                }
            }
        }
    }

    protected void startLoading() {
        this.loadingView.setVisibility(0);
    }

    protected void stopLoading() {
        this.loadingView.setVisibility(8);
    }

    public synchronized State getCurrentState() {
        return this.currentState;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        this.initialConfigOrientation = activity.getRequestedOrientation();
    }

    public void resize() {
        if (this.initialMovieHeight != -1 && this.initialMovieWidth != -1) {
            View view = (View) getParent();
            if (view != null) {
                float f = ((float) this.initialMovieWidth) / ((float) this.initialMovieHeight);
                int width = view.getWidth();
                int height = view.getHeight();
                if (f > ((float) width) / ((float) height)) {
                    height = (int) (((float) width) / f);
                } else {
                    width = (int) (((float) height) * f);
                }
                LayoutParams layoutParams = this.surfaceView.getLayoutParams();
                if (layoutParams.width != width || layoutParams.height != height) {
                    layoutParams.width = width;
                    layoutParams.height = height;
                    this.surfaceView.setLayoutParams(layoutParams);
                }
            }
        }
    }

    public boolean isShouldAutoplay() {
        return this.shouldAutoplay;
    }

    public void setShouldAutoplay(boolean z) {
        this.shouldAutoplay = z;
    }

    public void fullscreen() {
        boolean z = false;
        if (this.mediaPlayer == null) {
            throw new RuntimeException("Media Player is not initialized");
        }
        boolean isPlaying = this.mediaPlayer.isPlaying();
        if (isPlaying) {
            pause();
            if (this.mVideoPlayCallback != null) {
                this.mVideoPlayCallback.onPause(getCurrentPosition());
            }
        }
        ViewParent parent;
        if (this.isFullscreen) {
            this.isFullscreen = false;
            parent = getParent();
            if (parent instanceof ViewGroup) {
                if (!(this.parentView == null || this.parentView.getParent() == null)) {
                    this.keepState = true;
                    z = true;
                }
                ((ViewGroup) parent).removeView(this);
                if (z) {
                    this.parentView.addView(this);
                    setLayoutParams(this.currentLayoutParams);
                }
            }
        } else {
            this.isFullscreen = true;
            View findViewById = getRootView().findViewById(16908290);
            parent = getParent();
            if (parent instanceof ViewGroup) {
                if (this.parentView == null) {
                    this.parentView = (ViewGroup) parent;
                }
                this.keepState = true;
                this.currentLayoutParams = getLayoutParams();
                this.parentView.removeView(this);
            } else {
                Log.e(TAG, "Parent View is not a ViewGroup");
            }
            if (findViewById instanceof ViewGroup) {
                ((ViewGroup) findViewById).addView(this);
            } else {
                Log.e(TAG, "RootView is not a ViewGroup");
            }
        }
        resize();
        if (this.mVideoPlayCallback != null) {
            this.mVideoPlayCallback.onFullScreen(getCurrentPosition());
        }
        if (isPlaying && this.mediaPlayer != null) {
            start();
            if (this.mVideoPlayCallback != null && isPlaying()) {
                this.mVideoPlayCallback.onStart();
            }
        }
    }

    public int getCurrentPosition() {
        if (this.currentState != State.IDLE && this.mediaPlayer != null) {
            return this.mediaPlayer.getCurrentPosition();
        }
        if (this.currentState == State.IDLE) {
            return 0;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public int getDuration() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.getDuration();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public int getVideoHeight() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.getVideoHeight();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public int getVideoWidth() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.getVideoWidth();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public boolean isLooping() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.isLooping();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public boolean isPlaying() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.isPlaying();
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void pause() {
        if (this.mediaPlayer != null) {
            this.currentState = State.PAUSED;
            this.mediaPlayer.pause();
            if (this.mVideoPlayCallback != null) {
                this.mVideoPlayCallback.onPause(getCurrentPosition());
                return;
            }
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void reset() {
        if (this.mediaPlayer != null) {
            this.currentState = State.IDLE;
            this.mediaPlayer.reset();
            if (this.mVideoPlayCallback != null) {
                this.mVideoPlayCallback.onRelease();
                return;
            }
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void start() {
        if (this.mediaPlayer != null) {
            this.currentState = State.STARTED;
            this.mediaPlayer.setOnCompletionListener(this);
            if (this.isCompleted) {
                this.isCompleted = false;
                this.mediaPlayer.seekTo(0);
            }
            this.mediaPlayer.start();
            if (this.mVideoPlayCallback != null && isPlaying()) {
                this.mVideoPlayCallback.onStart();
                return;
            }
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void stop() {
        if (this.mediaPlayer != null) {
            this.currentState = State.STOPPED;
            this.mediaPlayer.stop();
            if (this.mVideoPlayCallback != null) {
                this.mVideoPlayCallback.onStop();
                return;
            }
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void seekTo(int i) {
        if (this.mediaPlayer == null) {
            throw new RuntimeException("Media Player is not initialized");
        } else if (this.mediaPlayer.getDuration() > -1 && i <= this.mediaPlayer.getDuration()) {
            this.lastState = this.currentState;
            pause();
            if (this.mVideoPlayCallback != null) {
                this.mVideoPlayCallback.onPause(getCurrentPosition());
            }
            this.mediaPlayer.seekTo(i);
            startLoading();
        }
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setOnInfoListener(onInfoListener);
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void setLooping(boolean z) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setLooping(z);
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void setVolume(float f, float f2) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setVolume(f, f2);
            return;
        }
        throw new RuntimeException("Media Player is not initialized");
    }

    public void setVideoPath(String str) {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        try {
            this.mediaPlayer.setDataSource(str);
            this.currentState = State.INITIALIZED;
            prepare();
        } catch (IOException e) {
            Log.i(TAG, "set data execption.");
            e.printStackTrace();
        }
    }

    public void setVideoURI(Uri uri) {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        try {
            this.mediaPlayer.setDataSource(this.context, uri);
            this.currentState = State.INITIALIZED;
            prepare();
        } catch (IOException e) {
            Log.i(TAG, "set data execption.");
            e.printStackTrace();
        }
    }

    public void setVideoPlayCallback(VideoPlayCallback videoPlayCallback) {
        this.mVideoPlayCallback = videoPlayCallback;
    }
}
