package com.example.gita.gxty.weiget.ninegrid;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;
import com.example.gita.gxty.R;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;

/* compiled from: ImageLoaderUtil */
public class a {
    public static d a(Context context) {
        return d.a();
    }

    public static c a() {
        return new com.nostra13.universalimageloader.core.c.a().a(true).b(true).a((int) R.mipmap.default_feed).b((int) R.mipmap.default_feed).a(Integer.valueOf(1)).a(Config.RGB_565).a();
    }

    public static void a(Context context, ImageView imageView, String str, c cVar, com.nostra13.universalimageloader.core.d.a aVar) {
        a(context).a(str, imageView, cVar, aVar);
    }
}
