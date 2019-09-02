package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.mapcore.util.cz.a;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;

/* compiled from: AbstractImageFetcher */
public class eo extends eq {
    private TileProvider e = null;

    public eo(Context context, int i, int i2) {
        super(context, i, i2);
        a(context);
    }

    private void a(Context context) {
        b(context);
    }

    public void a(TileProvider tileProvider) {
        this.e = tileProvider;
    }

    private void b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            }
        }
    }

    private Bitmap c(a aVar) {
        Bitmap bitmap = null;
        try {
            Tile tile = this.e.getTile(aVar.a, aVar.b, aVar.c);
            if (!(tile == null || tile == TileProvider.NO_TILE)) {
                bitmap = BitmapFactory.decodeByteArray(tile.data, 0, tile.data.length);
            }
        } catch (Throwable th) {
        }
        return bitmap;
    }

    protected Bitmap a(Object obj) {
        return c((a) obj);
    }
}
