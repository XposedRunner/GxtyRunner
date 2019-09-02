package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.example.gita.gxty.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: OfflineDownloadedAdapter */
public class ey extends BaseExpandableListAdapter implements OnGroupCollapseListener, OnGroupExpandListener {
    List<OfflineMapProvince> a = new ArrayList();
    private boolean[] b;
    private Context c;
    private fd d;
    private List<OfflineMapProvince> e = new ArrayList();
    private ff f;
    private OfflineMapManager g;

    /* compiled from: OfflineDownloadedAdapter */
    public final class a {
        public fd a;
        final /* synthetic */ ey b;

        public a(ey eyVar) {
            this.b = eyVar;
        }
    }

    public ey(Context context, ff ffVar, OfflineMapManager offlineMapManager, List<OfflineMapProvince> list) {
        this.c = context;
        this.f = ffVar;
        this.g = offlineMapManager;
        if (list != null && list.size() > 0) {
            this.e.clear();
            this.e.addAll(list);
            for (OfflineMapProvince offlineMapProvince : this.e) {
                if (offlineMapProvince != null && offlineMapProvince.getDownloadedCityList().size() > 0) {
                    this.a.add(offlineMapProvince);
                }
            }
        }
        this.b = new boolean[this.a.size()];
    }

    public void a() {
        for (OfflineMapProvince offlineMapProvince : this.e) {
            if (offlineMapProvince.getDownloadedCityList().size() > 0 && !this.a.contains(offlineMapProvince)) {
                this.a.add(offlineMapProvince);
            }
        }
        this.b = new boolean[this.a.size()];
        notifyDataSetChanged();
    }

    public void b() {
        try {
            for (int size = this.a.size(); size > 0; size--) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) this.a.get(size - 1);
                if (offlineMapProvince.getDownloadedCityList().size() == 0) {
                    this.a.remove(offlineMapProvince);
                }
            }
            this.b = new boolean[this.a.size()];
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getGroupCount() {
        return this.a.size();
    }

    public int getChildrenCount(int i) {
        return ((OfflineMapProvince) this.a.get(i)).getDownloadedCityList().size();
    }

    public Object getGroup(int i) {
        return ((OfflineMapProvince) this.a.get(i)).getProvinceName();
    }

    public Object getChild(int i, int i2) {
        return ((OfflineMapProvince) this.a.get(i)).getDownloadedCityList().get(i2);
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = (RelativeLayout) fh.a(this.c, R.mipmap.allbg, null);
        }
        ImageView imageView = (ImageView) view.findViewById(2131165202);
        ((TextView) view.findViewById(2131165201)).setText(((OfflineMapProvince) this.a.get(i)).getProvinceName());
        if (this.b[i]) {
            imageView.setImageDrawable(fh.a().getDrawable(2130837509));
        } else {
            imageView.setImageDrawable(fh.a().getDrawable(2130837510));
        }
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view != null) {
            aVar = (a) view.getTag();
        } else {
            a aVar2 = new a(this);
            this.d = new fd(this.c, this.g);
            this.d.a(2);
            view = this.d.a();
            aVar2.a = this.d;
            view.setTag(aVar2);
            aVar = aVar2;
        }
        OfflineMapProvince offlineMapProvince = (OfflineMapProvince) this.a.get(i);
        if (i2 < offlineMapProvince.getDownloadedCityList().size()) {
            final OfflineMapCity offlineMapCity = (OfflineMapCity) offlineMapProvince.getDownloadedCityList().get(i2);
            aVar.a.a(offlineMapCity);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ey b;

                public void onClick(View view) {
                    this.b.f.a(offlineMapCity);
                }
            });
        }
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void onGroupCollapse(int i) {
        this.b[i] = false;
    }

    public void onGroupExpand(int i) {
        this.b[i] = true;
    }
}
