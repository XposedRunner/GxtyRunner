package com.baidu.mobads.component;

import android.media.MediaPlayer;

public interface VideoPlayCallback {
    void onAttach();

    void onClickAd();

    void onCloseVideo(int i);

    void onCompletion(MediaPlayer mediaPlayer);

    void onDetach();

    void onError(MediaPlayer mediaPlayer, int i, int i2);

    void onFullScreen(int i);

    void onPause(int i);

    void onPrepared(MediaPlayer mediaPlayer);

    void onPreparing();

    void onRelease();

    void onSeekComplete(MediaPlayer mediaPlayer);

    void onStart();

    void onStop();
}
