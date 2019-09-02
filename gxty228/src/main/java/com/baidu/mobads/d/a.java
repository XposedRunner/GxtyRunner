package com.baidu.mobads.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.baidu.mobads.interfaces.download.IXAdStaticImgDownloader;
import com.baidu.mobads.openad.b.d;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observer;

public class a {
    private static final Handler b = new Handler(Looper.getMainLooper());
    private static final HandlerThread c = new HandlerThread("XAdSimpleImageLoader");
    private static final Handler d = new Handler(c.getLooper());
    private static a e;
    private LruCache<String, Bitmap> a = new b(this, ((int) Runtime.getRuntime().maxMemory()) / 32);

    private static class a {
        int a;
        int b;

        private a() {
        }
    }

    static {
        c.start();
    }

    public static a a() {
        if (e == null) {
            synchronized (a.class) {
                if (e == null) {
                    e = new a();
                }
            }
        }
        return e;
    }

    private a() {
    }

    public void a(ImageView imageView, String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalThreadStateException("please invoke in main thread!");
        } else if (imageView != null && str != null) {
            Bitmap bitmap = (Bitmap) this.a.get(str);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                return;
            }
            bitmap = a(b(str), imageView);
            if (bitmap != null) {
                a(str, bitmap);
            } else {
                a(str, new c(this, str, imageView));
            }
        }
    }

    private void a(String str, Bitmap bitmap) {
        if (this.a.get(str) == null && str != null && bitmap != null) {
            this.a.put(str, bitmap);
        }
    }

    private void a(String str, Observer observer) {
        Context applicationContext = XAdSDKFoundationFacade.getInstance().getApplicationContext();
        try {
            IXAdStaticImgDownloader createImgHttpDownloader = d.a(applicationContext).createImgHttpDownloader(new URL(str), c(str), d(str) + ".temp");
            createImgHttpDownloader.addObserver(observer);
            createImgHttpDownloader.start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private Bitmap a(String str, ImageView imageView) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        a(imageView);
        options.inSampleSize = a(options, imageView);
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        imageView.setImageBitmap(decodeFile);
        return decodeFile;
    }

    private static int a(Options options, ImageView imageView) {
        a a = a(imageView);
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i > a.a || i2 > a.b) {
            return Math.max(Math.round((((float) i) * 1.0f) / ((float) a.a)), Math.round((((float) i2) * 1.0f) / ((float) a.b)));
        }
        return 1;
    }

    private static a a(ImageView imageView) {
        a aVar = new a();
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        LayoutParams layoutParams = imageView.getLayoutParams();
        int width = imageView.getWidth();
        if (width <= 0) {
            width = layoutParams.width;
        }
        if (width <= 0) {
            width = imageView.getMaxWidth();
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }
        int height = imageView.getHeight();
        if (height <= 0) {
            height = layoutParams.height;
        }
        if (height <= 0) {
            height = imageView.getMaxHeight();
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        aVar.a = width;
        aVar.b = height;
        return aVar;
    }

    private static String b(String str) {
        String storeagePath = XAdSDKFoundationFacade.getInstance().getIoUtils().getStoreagePath(XAdSDKFoundationFacade.getInstance().getApplicationContext());
        return storeagePath + XAdSDKFoundationFacade.getInstance().getCommonUtils().md5(str) + ".temp";
    }

    private static String c(String str) {
        return XAdSDKFoundationFacade.getInstance().getIoUtils().getStoreagePath(XAdSDKFoundationFacade.getInstance().getApplicationContext());
    }

    private static String d(String str) {
        return XAdSDKFoundationFacade.getInstance().getCommonUtils().md5(str);
    }
}
