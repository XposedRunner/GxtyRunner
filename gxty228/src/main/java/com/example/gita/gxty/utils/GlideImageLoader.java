package com.example.gita.gxty.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DiscoverData.Banner;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    public void displayImage(Context context, Object obj, ImageView imageView) {
        try {
            e.b(context).a(((Banner) obj).pic).b((int) R.mipmap.default_banner).a(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
