package com.example.gita.gxty.ram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.ConfigData;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.ram.discover.DiscoverActivity;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.n;
import com.lzy.okgo.a;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTabActivity extends TabActivity {
    BroadcastReceiver a = new BroadcastReceiver(this) {
        final /* synthetic */ MainTabActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.example.gita.gxty.ram.action.change.tabitem".equals(intent.getAction())) {
                this.a.a(intent.getStringExtra(Progress.TAG));
            }
        }
    };
    private TabHost b;
    private b c;
    private Map<String, TextView> d = new HashMap();
    private boolean e = false;
    private List<TabBean> f = new ArrayList();
    private Handler g = new Handler(this) {
        final /* synthetic */ MainTabActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                this.a.a(1, this.a.a());
            }
        }
    };

    @SuppressLint({"NewApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ba_tab_activity);
        TabBean tabBean = new TabBean();
        tabBean = new TabBean();
        tabBean.action = DiscoverActivity.class.getName();
        tabBean.text = "发现";
        tabBean.tabImageId = R.drawable.c_main_tab_4;
        tabBean.tabId = "discover";
        this.f.add(tabBean);
        tabBean = new TabBean();
        tabBean.action = SportActivity.class.getName();
        tabBean.text = "运动";
        tabBean.tabImageId = R.drawable.c_main_tab_2;
        tabBean.tabId = "sport";
        this.f.add(tabBean);
        tabBean = new TabBean();
        tabBean.action = NewMineActivity.class.getName();
        tabBean.text = "我的";
        tabBean.tabImageId = R.drawable.c_main_tab_3;
        tabBean.tabId = "mine";
        this.f.add(tabBean);
        try {
            if (b() != null) {
                this.c = new b(this, this.g);
                getContentResolver().registerContentObserver(b(), true, this.c);
                this.c.onChange(true);
            }
        } catch (Exception e) {
            h.a(e);
        }
        findViewById(16908307).setBackgroundColor(getResources().getColor(R.color.main_tabs_bg));
        this.b = (TabHost) findViewById(16908306);
        this.b.setup();
        registerReceiver(this.a, new IntentFilter("com.example.gita.gxty.ram.action.change.tabitem"));
        c();
        a("sport");
    }

    protected void onResume() {
        super.onResume();
        c();
        a((Activity) this);
    }

    public int a() {
        return 0;
    }

    public Uri b() {
        return null;
    }

    private void a(TabBean tabBean, Intent intent) {
        this.b.addTab(this.b.newTabSpec(tabBean.tabId).setIndicator(a(this.b.getContext(), tabBean)).setContent(intent));
    }

    private View a(Context context, TabBean tabBean) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ba_tab_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab_text_2);
        if (tabBean.countType != 0) {
            this.d.put(tabBean.countType + "", textView);
        }
        ((RelativeLayout) inflate.findViewById(R.id.tab_layout)).setBackgroundResource(tabBean.tabItemBgSelectId);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.tab_img);
        imageView.setImageResource(tabBean.tabImageId);
        imageView.setVisibility(0);
        textView = (TextView) inflate.findViewById(R.id.tab_text);
        textView.setText(tabBean.text);
        textView.setVisibility(0);
        return inflate;
    }

    public void c() {
        if (!this.e) {
            try {
                if (this.b.getChildCount() > 0) {
                    this.b.clearAllTabs();
                }
                h.b("-------init main tabs-------");
                int size = this.f.size();
                h.b(Integer.valueOf(size));
                if (size > 0) {
                    for (TabBean tabBean : this.f) {
                        h.d(tabBean.action);
                        Intent intent = new Intent().setClass(this, Class.forName(tabBean.action));
                        if (tabBean.bundle != null) {
                            intent.putExtras(tabBean.bundle);
                        }
                        a(tabBean, intent);
                    }
                }
            } catch (Exception e) {
                h.a(e);
            }
            this.e = true;
        }
    }

    public void a(String str) {
        this.b.setCurrentTabByTag(str);
    }

    public void a(int i, int i2) {
        TextView textView = (TextView) this.d.get(i + "");
        if (textView != null) {
            textView.setText(i2 + "");
            if (i2 <= 0) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.a);
        } catch (Exception e) {
            h.a(e);
        }
    }

    public static void a(final Activity activity) {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("configuration/apiConfig")).tag(activity)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<ConfigData>>(false, activity) {
            public void a(com.lzy.okgo.model.a<LzyResponse<ConfigData>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    ConfigData configData = (ConfigData) ((LzyResponse) aVar.c()).data;
                    if (configData != null) {
                        Log.e("adsadsads", "getConfig");
                        n.a(activity).b(configData.OPEN_AD_FLAG);
                        n.a(activity).c(configData.OPEN_AD_XHFLAG);
                        n.a(activity).d(configData.androidBeaconScanTime);
                        n.a(activity).e(configData.androidBeaconBetweenScanTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<ConfigData>> aVar) {
                super.b(aVar);
            }
        });
    }
}
