package com.example.gita.gxty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.example.gita.gxty.utils.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OfflineDownloadedAdapter extends BaseAdapter {
    private OfflineMapManager a;
    private List<OfflineMapCity> b = new ArrayList();
    private Context c;
    private a d;

    public final class a {
        public a a;
        final /* synthetic */ OfflineDownloadedAdapter b;

        public a(OfflineDownloadedAdapter offlineDownloadedAdapter) {
            this.b = offlineDownloadedAdapter;
        }
    }

    public OfflineDownloadedAdapter(Context context, OfflineMapManager offlineMapManager) {
        this.c = context;
        this.a = offlineMapManager;
        b();
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        b();
        h.a("Offline Downloading notifyData cost: " + (System.currentTimeMillis() - currentTimeMillis));
    }

    private void b() {
        if (this.b != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                OfflineMapCity offlineMapCity = (OfflineMapCity) it.next();
                it.remove();
            }
            h.a("Offline Downloading notifyData cities iterator cost: " + (System.currentTimeMillis() - currentTimeMillis));
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        this.b.addAll(this.a.getDownloadOfflineMapCityList());
        this.b.addAll(this.a.getDownloadingCityList());
        h.a("Offline Downloading notifyData getDownloadingCityList cost: " + (System.currentTimeMillis() - currentTimeMillis2));
        currentTimeMillis2 = System.currentTimeMillis();
        notifyDataSetChanged();
        h.a("Offline Downloading notifyData notifyDataSetChanged cost: " + (System.currentTimeMillis() - currentTimeMillis2));
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view != null) {
            aVar = (a) view.getTag();
        } else {
            a aVar2 = new a(this);
            this.d = new a(this.c, this.a, this, 1);
            view = this.d.a();
            aVar2.a = this.d;
            view.setTag(aVar2);
            aVar = aVar2;
        }
        aVar.a.a((OfflineMapCity) getItem(i), 1);
        return view;
    }
}
