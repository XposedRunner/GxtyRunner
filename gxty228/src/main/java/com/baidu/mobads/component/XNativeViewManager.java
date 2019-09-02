package com.baidu.mobads.component;

import java.util.ArrayList;
import java.util.List;

public class XNativeViewManager {
    private static XNativeViewManager sInstance;
    private List<XNativeView> mViewList;

    private XNativeViewManager() {
    }

    public static XNativeViewManager getInstance() {
        if (sInstance == null) {
            synchronized (XNativeViewManager.class) {
                if (sInstance == null) {
                    sInstance = new XNativeViewManager();
                    sInstance.mViewList = new ArrayList();
                }
            }
        }
        return sInstance;
    }

    public void addItem(XNativeView xNativeView) {
        this.mViewList.add(xNativeView);
    }

    public void resetAllPlayer() {
        for (XNativeView resetPlayer : this.mViewList) {
            resetPlayer.resetPlayer();
        }
    }
}
