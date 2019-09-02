package com.example.gita.gxty.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.OfflinemapActivity;
import com.example.gita.gxty.adapter.b;
import java.util.ArrayList;
import java.util.List;

public class AllcityFragment extends Fragment {
    private OfflineMapManager a = null;
    private List<OfflineMapProvince> b = new ArrayList();
    private b c;
    @BindView(2131755543)
    protected ExpandableListView mAllOfflineMapList;

    public static AllcityFragment a() {
        return new AllcityFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_allcity, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.a = OfflinemapActivity.f;
        return inflate;
    }

    public void b() {
        d();
        this.c = new b(this.b, this.a, getActivity());
        this.mAllOfflineMapList.setAdapter(this.c);
        this.mAllOfflineMapList.setOnGroupCollapseListener(this.c);
        this.mAllOfflineMapList.setOnGroupExpandListener(this.c);
        this.mAllOfflineMapList.setGroupIndicator(null);
    }

    private void d() {
        OfflineMapProvince offlineMapProvince;
        List offlineMapProvinceList = this.a.getOfflineMapProvinceList();
        this.b.add(new OfflineMapProvince());
        this.b.add(new OfflineMapProvince());
        this.b.add(new OfflineMapProvince());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < offlineMapProvinceList.size(); i++) {
            offlineMapProvince = (OfflineMapProvince) offlineMapProvinceList.get(i);
            if (offlineMapProvince.getCityList().size() != 1) {
                this.b.add(i + 3, offlineMapProvince);
            } else {
                String provinceName = offlineMapProvince.getProvinceName();
                if (provinceName.contains("香港")) {
                    arrayList2.addAll(offlineMapProvince.getCityList());
                } else if (provinceName.contains("澳门")) {
                    arrayList2.addAll(offlineMapProvince.getCityList());
                } else if (provinceName.contains("全国概要图")) {
                    arrayList3.addAll(offlineMapProvince.getCityList());
                } else {
                    arrayList.addAll(offlineMapProvince.getCityList());
                }
            }
        }
        offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setProvinceName("概要图");
        offlineMapProvince.setCityList(arrayList3);
        this.b.set(0, offlineMapProvince);
        offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setProvinceName("直辖市");
        offlineMapProvince.setCityList(arrayList);
        this.b.set(1, offlineMapProvince);
        offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setProvinceName("港澳");
        offlineMapProvince.setCityList(arrayList2);
        this.b.set(2, offlineMapProvince);
    }

    public void c() {
        try {
            if (this.c != null) {
                this.c.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
