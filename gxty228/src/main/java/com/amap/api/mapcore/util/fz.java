package com.amap.api.mapcore.util;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapActivity;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.example.gita.gxty.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SearchListAdapter */
public class fz extends BaseAdapter {
    private List<OfflineMapCity> a = new ArrayList();
    private OfflineMapManager b;
    private Activity c;

    /* compiled from: SearchListAdapter */
    public final class a {
        public TextView a;
        public TextView b;
        public TextView c;
        public ImageView d;
        final /* synthetic */ fz e;

        public a(fz fzVar) {
            this.e = fzVar;
        }
    }

    public fz(List<OfflineMapProvince> list, OfflineMapManager offlineMapManager, OfflineMapActivity offlineMapActivity) {
        this.b = offlineMapManager;
        this.c = offlineMapActivity;
    }

    public void a(List<OfflineMapCity> list) {
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        View view3;
        Exception exception;
        try {
            a aVar;
            final OfflineMapCity offlineMapCity = (OfflineMapCity) this.a.get(i);
            if (view == null) {
                aVar = new a(this);
                view = fh.a(this.c, R.mipmap.ads_user, null);
                aVar.a = (TextView) view.findViewById(2131165195);
                aVar.b = (TextView) view.findViewById(2131165199);
                aVar.c = (TextView) view.findViewById(2131165197);
                aVar.d = (ImageView) view.findViewById(2131165198);
                view.setTag(aVar);
                view2 = view;
            } else {
                aVar = (a) view.getTag();
                view2 = view;
            }
            try {
                aVar.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ fz c;

                    public void onClick(View view) {
                        aVar.d.setVisibility(8);
                        aVar.c.setVisibility(0);
                        aVar.c.setText("下载中");
                        try {
                            this.c.b.downloadByCityName(offlineMapCity.getCity());
                        } catch (AMapException e) {
                            e.printStackTrace();
                        }
                    }
                });
                aVar.c.setVisibility(0);
                aVar.a.setText(offlineMapCity.getCity());
                aVar.b.setText(String.valueOf(((double) ((int) (((((double) offlineMapCity.getSize()) / 1024.0d) / 1024.0d) * 100.0d))) / 100.0d) + " M");
                switch (offlineMapCity.getState()) {
                    case -1:
                    case 101:
                    case 102:
                    case 103:
                        aVar.d.setVisibility(8);
                        aVar.c.setText("下载失败");
                        break;
                    case 0:
                    case 1:
                        aVar.d.setVisibility(8);
                        aVar.c.setText("下载中");
                        break;
                    case 2:
                        aVar.d.setVisibility(8);
                        aVar.c.setText("等待下载");
                        break;
                    case 3:
                        aVar.d.setVisibility(8);
                        aVar.c.setText("暂停中");
                        break;
                    case 4:
                        aVar.d.setVisibility(8);
                        aVar.c.setText("已下载");
                        break;
                    case 6:
                        aVar.d.setVisibility(0);
                        aVar.c.setVisibility(8);
                        break;
                }
                return view2;
            } catch (Exception e) {
                Exception exception2 = e;
                view3 = view2;
                exception = exception2;
                exception.printStackTrace();
                return view3;
            }
        } catch (Exception e2) {
            exception = e2;
            view3 = view;
            exception.printStackTrace();
            return view3;
        }
    }
}
