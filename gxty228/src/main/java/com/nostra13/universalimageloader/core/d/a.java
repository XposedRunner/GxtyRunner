package com.nostra13.universalimageloader.core.d;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;

/* compiled from: ImageLoadingListener */
public interface a {
    void a(String str, View view);

    void a(String str, View view, Bitmap bitmap);

    void a(String str, View view, FailReason failReason);

    void b(String str, View view);
}
