package com.amap.api.mapcore.util;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.amap.api.maps.offlinemap.DownLoadExpandListView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapManager.OfflineLoadedListener;
import com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.amap.api.offlineservice.a;
import com.example.gita.gxty.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: OfflineMapPage */
public class ff extends a implements TextWatcher, OnTouchListener, OnScrollListener, OfflineLoadedListener, OfflineMapDownloadListener {
    private ImageView b;
    private RelativeLayout c;
    private DownLoadExpandListView d;
    private ListView e;
    private ExpandableListView f;
    private ImageView g;
    private ImageView h;
    private AutoCompleteTextView i;
    private RelativeLayout j;
    private RelativeLayout k;
    private ImageView l;
    private ImageView m;
    private RelativeLayout n;
    private List<OfflineMapProvince> o = new ArrayList();
    private ez p;
    private OfflineMapManager q = null;
    private ey r;
    private fz s;
    private boolean t = true;
    private boolean u = true;
    private int v = -1;
    private long w = 0;
    private fb x;
    private boolean y = true;

    public void a() {
        View a = fh.a(this.a, R.mipmap.addimg_8, null);
        this.d = (DownLoadExpandListView) a.findViewById(2131165187);
        this.d.setOnTouchListener(this);
        this.j = (RelativeLayout) a.findViewById(R.xml.bd_file_paths);
        this.g = (ImageView) a.findViewById(2131165186);
        this.j.setOnClickListener(this.a);
        this.k = (RelativeLayout) a.findViewById(2131165189);
        this.h = (ImageView) a.findViewById(2131165190);
        this.k.setOnClickListener(this.a);
        this.n = (RelativeLayout) a.findViewById(R.xml.v5_file_paths);
        this.b = (ImageView) this.c.findViewById(2131165205);
        this.b.setOnClickListener(this.a);
        this.m = (ImageView) this.c.findViewById(2131165207);
        this.l = (ImageView) this.c.findViewById(2131165209);
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ff a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    this.a.i.setText("");
                    this.a.l.setVisibility(8);
                    this.a.a(false);
                    LayoutParams layoutParams = (LayoutParams) this.a.m.getLayoutParams();
                    layoutParams.leftMargin = this.a.a(95.0f);
                    this.a.m.setLayoutParams(layoutParams);
                    this.a.i.setPadding(this.a.a(105.0f), 0, 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.c.findViewById(2131165210).setOnTouchListener(this);
        this.i = (AutoCompleteTextView) this.c.findViewById(2131165208);
        this.i.addTextChangedListener(this);
        this.i.setOnTouchListener(this);
        this.e = (ListView) this.c.findViewById(2131165212);
        this.f = (ExpandableListView) this.c.findViewById(2131165211);
        this.f.addHeaderView(a);
        this.f.setOnTouchListener(this);
        this.f.setOnScrollListener(this);
        this.q = new OfflineMapManager(this.a, this);
        this.q.setOnOfflineLoadedListener(this);
        l();
        this.p = new ez(this.o, this.q, this.a);
        this.f.setAdapter(this.p);
        this.f.setOnGroupCollapseListener(this.p);
        this.f.setOnGroupExpandListener(this.p);
        this.f.setGroupIndicator(null);
        if (this.t) {
            this.h.setBackgroundResource(2130837504);
            this.f.setVisibility(0);
        } else {
            this.h.setBackgroundResource(2130837508);
            this.f.setVisibility(8);
        }
        if (this.u) {
            this.g.setBackgroundResource(2130837504);
            this.d.setVisibility(0);
            return;
        }
        this.g.setBackgroundResource(2130837508);
        this.d.setVisibility(8);
    }

    private void j() {
        try {
            LayoutParams layoutParams = (LayoutParams) this.m.getLayoutParams();
            layoutParams.leftMargin = a(18.0f);
            this.m.setLayoutParams(layoutParams);
            this.i.setPadding(a(30.0f), 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(View view) {
        try {
            int id = view.getId();
            if (id == 2131165205) {
                this.a.closeScr();
            } else if (id == R.xml.bd_file_paths) {
                if (this.u) {
                    this.d.setVisibility(8);
                    this.g.setBackgroundResource(2130837508);
                    this.u = false;
                    return;
                }
                this.d.setVisibility(0);
                this.g.setBackgroundResource(2130837504);
                this.u = true;
            } else if (id != 2131165189) {
            } else {
                if (this.t) {
                    this.p.b();
                    this.h.setBackgroundResource(2130837508);
                    this.t = false;
                    return;
                }
                this.p.a();
                this.h.setBackgroundResource(2130837504);
                this.t = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RelativeLayout b() {
        if (this.c == null) {
            this.c = (RelativeLayout) fh.a(this.a, R.mipmap.arrowdown, null);
        }
        return this.c;
    }

    public void c() {
        this.q.destroy();
    }

    private void k() {
        l();
        this.s = new fz(this.o, this.q, this.a);
        this.e.setAdapter(this.s);
    }

    public void d() {
        this.r = new ey(this.a, this, this.q, this.o);
        this.d.setAdapter(this.r);
        this.r.notifyDataSetChanged();
    }

    public void a(OfflineMapCity offlineMapCity) {
        try {
            if (this.x == null) {
                this.x = new fb(this.a, this.q);
            }
            this.x.a(offlineMapCity.getState(), offlineMapCity.getCity());
            this.x.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void l() {
        OfflineMapProvince offlineMapProvince;
        List offlineMapProvinceList = this.q.getOfflineMapProvinceList();
        this.o.clear();
        this.o.add(null);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < offlineMapProvinceList.size(); i++) {
            offlineMapProvince = (OfflineMapProvince) offlineMapProvinceList.get(i);
            if (offlineMapProvince.getCityList().size() != 1) {
                this.o.add(i + 1, offlineMapProvince);
            } else {
                String provinceName = offlineMapProvince.getProvinceName();
                if (provinceName.contains("香港")) {
                    arrayList2.addAll(offlineMapProvince.getCityList());
                } else if (provinceName.contains("澳门")) {
                    arrayList2.addAll(offlineMapProvince.getCityList());
                } else if (provinceName.contains("全国概要图")) {
                    arrayList3.addAll(0, offlineMapProvince.getCityList());
                } else {
                    arrayList3.addAll(offlineMapProvince.getCityList());
                }
            }
        }
        offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setProvinceName("基本功能包+直辖市");
        offlineMapProvince.setCityList(arrayList3);
        this.o.set(0, offlineMapProvince);
        offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setProvinceName("直辖市");
        offlineMapProvince.setCityList(arrayList);
        offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setProvinceName("港澳");
        offlineMapProvince.setCityList(arrayList2);
        this.o.add(offlineMapProvince);
    }

    public void onDownload(int i, int i2, String str) {
        switch (i) {
            case 101:
                Toast.makeText(this.a, "网络异常", 0).show();
                this.q.pause();
                break;
        }
        if (i == 2) {
            try {
                this.r.a();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (this.v != i) {
            if (this.p != null) {
                this.p.notifyDataSetChanged();
            }
            if (this.r != null) {
                this.r.notifyDataSetChanged();
            }
            if (this.s != null) {
                this.s.notifyDataSetChanged();
            }
            this.v = i;
        } else if (System.currentTimeMillis() - this.w > ((long) 1200)) {
            Log.i("SHIXIN", "UPDATE_DOWNLOAD_LIST");
            if (this.y) {
                this.r.notifyDataSetChanged();
            }
            this.w = System.currentTimeMillis();
        }
    }

    public void onCheckUpdate(boolean z, String str) {
    }

    public void onRemove(boolean z, String str, String str2) {
        if (this.r != null) {
            this.r.b();
        }
    }

    public void onVerifyComplete() {
        k();
        d();
    }

    public boolean e() {
        try {
            if (this.e.getVisibility() == 0) {
                this.i.setText("");
                this.l.setVisibility(8);
                a(false);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.e();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        m();
        if (view.getId() == 2131165208) {
            j();
        }
        return false;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            a(false);
            this.l.setVisibility(8);
            return;
        }
        this.l.setVisibility(0);
        List arrayList = new ArrayList();
        if (this.o != null && this.o.size() > 0) {
            List<OfflineMapCity> arrayList2 = new ArrayList();
            for (OfflineMapProvince cityList : this.o) {
                arrayList2.addAll(cityList.getCityList());
            }
            for (OfflineMapCity offlineMapCity : arrayList2) {
                String city = offlineMapCity.getCity();
                String pinyin = offlineMapCity.getPinyin();
                String jianpin = offlineMapCity.getJianpin();
                if (charSequence.length() == 1) {
                    if (jianpin.startsWith(String.valueOf(charSequence))) {
                        arrayList.add(offlineMapCity);
                    }
                } else if (jianpin.startsWith(String.valueOf(charSequence)) || pinyin.startsWith(String.valueOf(charSequence)) || city.startsWith(String.valueOf(charSequence))) {
                    arrayList.add(offlineMapCity);
                }
            }
        }
        if (arrayList.size() > 0) {
            a(true);
            Collections.sort(arrayList, new Comparator<OfflineMapCity>(this) {
                final /* synthetic */ ff a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((OfflineMapCity) obj, (OfflineMapCity) obj2);
                }

                public int a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
                    char[] toCharArray = offlineMapCity.getJianpin().toCharArray();
                    char[] toCharArray2 = offlineMapCity2.getJianpin().toCharArray();
                    if (toCharArray[0] >= toCharArray2[0] && toCharArray[1] >= toCharArray2[1]) {
                        return 0;
                    }
                    return 1;
                }
            });
            this.s.a(arrayList);
            this.s.notifyDataSetChanged();
            return;
        }
        Toast.makeText(this.a, "未找到相关城市", 0).show();
    }

    public void afterTextChanged(Editable editable) {
    }

    public void a(boolean z) {
        int i = 0;
        if (z) {
            this.j.setVisibility(8);
            this.k.setVisibility(8);
            this.d.setVisibility(8);
            this.f.setVisibility(8);
            this.n.setVisibility(8);
            this.e.setVisibility(0);
            return;
        }
        int i2;
        this.j.setVisibility(0);
        this.k.setVisibility(0);
        this.n.setVisibility(0);
        DownLoadExpandListView downLoadExpandListView = this.d;
        if (this.u) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        downLoadExpandListView.setVisibility(i2);
        ExpandableListView expandableListView = this.f;
        if (!this.t) {
            i = 8;
        }
        expandableListView.setVisibility(i);
        this.e.setVisibility(8);
    }

    private void m() {
        if (this.i != null && this.i.isFocused()) {
            this.i.clearFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) this.a.getSystemService("input_method");
            boolean z = false;
            if (inputMethodManager != null) {
                z = inputMethodManager.isActive();
            }
            if (z) {
                inputMethodManager.hideSoftInputFromWindow(this.i.getWindowToken(), 2);
            }
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 2) {
            this.y = false;
        } else {
            this.y = true;
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
