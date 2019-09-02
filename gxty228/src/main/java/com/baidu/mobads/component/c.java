package com.baidu.mobads.component;

import com.baidu.mobads.component.NativeVideoView.State;

/* synthetic */ class c {
    static final /* synthetic */ int[] a = new int[State.values().length];

    static {
        try {
            a[State.STARTED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[State.PAUSED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[State.PLAYBACKCOMPLETED.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[State.PREPARED.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
