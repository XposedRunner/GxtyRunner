package com.example.gita.gxty.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapManager.OfflineLoadedListener;
import com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.fragment.AllcityFragment;
import com.example.gita.gxty.fragment.DownloadcityFragment;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import com.tencent.bugly.beta.tinker.TinkerReport;

public class OfflinemapActivity extends BaseActivity implements OfflineLoadedListener, OfflineMapDownloadListener, c {
    public static OfflineMapManager f;
    @BindView(2131755356)
    protected LinearLayout choose;
    @BindView(2131755357)
    protected TextView city;
    @BindView(2131755359)
    protected FrameLayout fl_content;
    private FragmentManager g;
    private AllcityFragment h;
    private DownloadcityFragment i;
    private int j = 0;
    private Handler k = new Handler(this) {
        final /* synthetic */ OfflinemapActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case TinkerReport.KEY_APPLIED_DEXOPT_EXIST /*122*/:
                    if (this.a.j == 0) {
                        this.a.h.c();
                        return;
                    } else {
                        this.a.i.c();
                        return;
                    }
                default:
                    return;
            }
        }
    };
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleBar.setLeftImageResource(R.mipmap.back);
        this.titleBar.setTitle((CharSequence) "离线地图");
        this.titleBar.setTitleColor(Color.parseColor("#ffffff"));
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ OfflinemapActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        f = new OfflineMapManager(this, this);
        f.setOnOfflineLoadedListener(this);
        this.g = getFragmentManager();
        FragmentTransaction beginTransaction = this.g.beginTransaction();
        this.h = AllcityFragment.a();
        this.i = DownloadcityFragment.a();
        beginTransaction.add(R.id.fl_content, this.h);
        beginTransaction.add(R.id.fl_content, this.i);
        beginTransaction.hide(this.i);
        beginTransaction.show(this.h);
        beginTransaction.commit();
    }

    @OnClick({2131755356})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.choose /*2131755356*/:
                String str = null;
                new AlertView(null, str, "取消", null, new String[]{"所有城市", "下载管理"}, this, Style.ActionSheet, this).e();
                return;
            default:
                return;
        }
    }

    public void a(Object obj, int i) {
        FragmentTransaction beginTransaction = this.g.beginTransaction();
        if (i == 0) {
            this.j = 0;
            this.city.setText("所有城市");
            this.h.c();
            beginTransaction.hide(this.i);
            beginTransaction.show(this.h);
        } else if (i == 1) {
            this.j = 1;
            this.city.setText("下载管理");
            this.i.c();
            beginTransaction.hide(this.h);
            beginTransaction.show(this.i);
        }
        beginTransaction.commit();
    }

    protected int a() {
        return R.layout.activity_offlinemap;
    }

    public void onVerifyComplete() {
        try {
            this.h.b();
            this.i.b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDownload(int i, int i2, String str) {
        switch (i) {
            case -1:
                h.a("download:  ERROR " + str);
                break;
            case 0:
                h.a("download: " + i2 + "%," + str);
                break;
            case 1:
                h.a("unzip: " + i2 + "%," + str);
                break;
            case 2:
                h.a("WAITING: " + i2 + "%," + str);
                break;
            case 3:
                h.a("pause: " + i2 + "%," + str);
                break;
            case 101:
                h.a("download:  EXCEPTION_NETWORK_LOADING " + str);
                s.b((CharSequence) "网络异常");
                f.pause();
                break;
            case 102:
                h.a("download:  EXCEPTION_AMAP " + str);
                break;
            case 103:
                h.a("download:  EXCEPTION_SDCARD " + str);
                break;
        }
        this.k.sendEmptyMessage(TinkerReport.KEY_APPLIED_DEXOPT_EXIST);
    }

    public void onCheckUpdate(boolean z, String str) {
        h.a("onCheckUpdate " + str + " : " + z);
    }

    public void onRemove(boolean z, String str, String str2) {
        h.a("onRemove " + str + " : " + z + " , " + str2);
    }
}
