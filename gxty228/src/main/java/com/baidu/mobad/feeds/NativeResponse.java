package com.baidu.mobad.feeds;

import android.content.Context;
import android.view.View;

public interface NativeResponse {

    public enum MaterialType {
        NORMAL,
        VIDEO,
        HTML
    }

    String a();

    void a(Context context);

    void a(Context context, int i);

    void a(Context context, int i, int i2);

    void a(View view);

    void a(View view, int i);

    void b(Context context);

    void b(Context context, int i);

    boolean b();

    void c(Context context);

    boolean c();

    String d();

    MaterialType e();
}
