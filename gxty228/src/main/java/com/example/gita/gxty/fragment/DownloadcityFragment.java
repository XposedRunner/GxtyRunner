package com.example.gita.gxty.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.OfflinemapActivity;
import com.example.gita.gxty.adapter.OfflineDownloadedAdapter;
import com.example.gita.gxty.adapter.a;
import com.example.gita.gxty.utils.s;

public class DownloadcityFragment extends Fragment {
    private OfflineMapManager a = null;
    private OfflineDownloadedAdapter b;
    @BindView(2131755544)
    protected ListView mDownLoadedList;

    public static DownloadcityFragment a() {
        return new DownloadcityFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_downloadcity, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        d();
        return inflate;
    }

    private void d() {
        this.a = OfflinemapActivity.f;
    }

    public void b() {
        try {
            this.b = new OfflineDownloadedAdapter(getActivity(), this.a);
            this.mDownLoadedList.setAdapter(this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
        try {
            this.b.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({2131755545, 2131755546})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.update /*2131755545*/:
                try {
                    this.a.updateOfflineCityByName(a.a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                s.b((CharSequence) "已更新到最新");
                return;
            case R.id.del /*2131755546*/:
                try {
                    this.a.remove(a.a);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }
}
