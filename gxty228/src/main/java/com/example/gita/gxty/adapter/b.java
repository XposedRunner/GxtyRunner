package com.example.gita.gxty.adapter;

import android.content.Context;
import android.view.View;
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
import java.util.List;

/* compiled from: OfflineListAdapter */
public class b extends BaseExpandableListAdapter implements OnGroupCollapseListener, OnGroupExpandListener {
    private boolean[] a;
    private List<OfflineMapProvince> b = null;
    private OfflineMapManager c;
    private Context d;

    /* compiled from: OfflineListAdapter */
    public final class a {
        public a a;
        final /* synthetic */ b b;

        public a(b bVar) {
            this.b = bVar;
        }
    }

    public b(List<OfflineMapProvince> list, OfflineMapManager offlineMapManager, Context context) {
        this.b = list;
        this.c = offlineMapManager;
        this.d = context;
        this.a = new boolean[list.size()];
    }

    public int getGroupCount() {
        return this.b.size();
    }

    public Object getGroup(int i) {
        return ((OfflineMapProvince) this.b.get(i)).getProvinceName();
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public int getChildrenCount(int i) {
        if (a(i)) {
            return ((OfflineMapProvince) this.b.get(i)).getCityList().size() + 1;
        }
        return ((OfflineMapProvince) this.b.get(i)).getCityList().size();
    }

    public Object getChild(int i, int i2) {
        return null;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = (RelativeLayout) RelativeLayout.inflate(this.d, R.layout.offlinemap_group, null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.group_image);
        ((TextView) view.findViewById(R.id.group_text)).setText(((OfflineMapProvince) this.b.get(i)).getProvinceName());
        if (this.a[i]) {
            imageView.setImageResource(R.mipmap.open);
        } else {
            imageView.setImageResource(R.mipmap.next);
        }
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        OfflineMapCity offlineMapCity;
        if (view != null) {
            aVar = (a) view.getTag();
        } else {
            a aVar2 = new a(this);
            a aVar3 = new a(this.d, this.c);
            view = aVar3.a();
            aVar2.a = aVar3;
            view.setTag(aVar2);
            aVar = aVar2;
        }
        aVar.a.a(false);
        if (!a(i)) {
            offlineMapCity = (OfflineMapCity) ((OfflineMapProvince) this.b.get(i)).getCityList().get(i2);
        } else if (a(i, i2)) {
            offlineMapCity = a((OfflineMapProvince) this.b.get(i));
            aVar.a.a(true);
        } else {
            offlineMapCity = (OfflineMapCity) ((OfflineMapProvince) this.b.get(i)).getCityList().get(i2 - 1);
        }
        aVar.a.a(offlineMapCity);
        return view;
    }

    private boolean a(int i, int i2) {
        return a(i) && i2 == 0;
    }

    private boolean a(int i) {
        return i > 2;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public OfflineMapCity a(OfflineMapProvince offlineMapProvince) {
        OfflineMapCity offlineMapCity = new OfflineMapCity();
        offlineMapCity.setCity(offlineMapProvince.getProvinceName());
        offlineMapCity.setSize(offlineMapProvince.getSize());
        offlineMapCity.setCompleteCode(offlineMapProvince.getcompleteCode());
        offlineMapCity.setState(offlineMapProvince.getState());
        offlineMapCity.setUrl(offlineMapProvince.getUrl());
        return offlineMapCity;
    }

    public void onGroupCollapse(int i) {
        this.a[i] = false;
    }

    public void onGroupExpand(int i) {
        this.a[i] = true;
    }
}
