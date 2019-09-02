package com.baidu.mobads.d;

import android.content.Context;
import android.graphics.Bitmap;
import com.baidu.mobads.interfaces.download.IXAdStaticImgDownloader;
import com.baidu.mobads.openad.b.f;
import java.net.URL;

public class d extends f implements IXAdStaticImgDownloader {
    public d(Context context, URL url, String str, String str2) {
        super(context, url, str, str2, true);
    }

    protected void a() {
        super.a();
    }

    public Bitmap getBitmap() {
        return null;
    }
}
