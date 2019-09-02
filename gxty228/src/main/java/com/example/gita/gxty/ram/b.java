package com.example.gita.gxty.ram;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: IContentObserver */
public class b extends ContentObserver {
    private Handler a;

    public b(Context context, Handler handler) {
        super(handler);
        this.a = handler;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        this.a.sendEmptyMessage(1);
    }
}
