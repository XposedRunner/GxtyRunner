package com.baidu.mobads.d;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import java.util.Observable;
import java.util.Observer;

class c implements Observer {
    final /* synthetic */ String a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ a c;

    c(a aVar, String str, ImageView imageView) {
        this.c = aVar;
        this.a = str;
        this.b = imageView;
    }

    public void update(Observable observable, Object obj) {
        if (((IOAdDownloader) observable).getState() == DownloadStatus.COMPLETED) {
            Bitmap a = this.c.a(a.b(this.a), this.b);
            if (a != null) {
                this.c.a(this.a, a);
            }
        }
    }
}
