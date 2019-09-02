package com.baidu.mobads.component;

class a implements Runnable {
    final /* synthetic */ NativeVideoView a;

    a(NativeVideoView nativeVideoView) {
        this.a = nativeVideoView;
    }

    public void run() {
        this.a.resize();
    }
}
