package com.baidu.mobads.component;

import android.view.View;
import android.view.View.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ NativeVideoView a;

    b(NativeVideoView nativeVideoView) {
        this.a = nativeVideoView;
    }

    public void onClick(View view) {
        if (!this.a.isPlaying()) {
            this.a.tryToPrepare();
        } else if (this.a.mVideoPlayCallback != null) {
            this.a.mVideoPlayCallback.onClickAd();
            this.a.pause();
            this.a.mVideoPlayCallback.onPause(this.a.getCurrentPosition());
        }
    }
}
