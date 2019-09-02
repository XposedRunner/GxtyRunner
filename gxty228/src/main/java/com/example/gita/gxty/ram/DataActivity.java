package com.example.gita.gxty.ram;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.DataAdapter;
import com.example.gita.gxty.ram.db.a.a;
import com.example.gita.gxty.ram.db.a.b;
import com.example.gita.gxty.weiget.TitleBar;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends BaseActivity {
    @BindView(2131755227)
    protected TextView dataSizeText;
    Handler f = new Handler(this) {
        final /* synthetic */ DataActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.h.a(this.a.g);
            this.a.dataSizeText.setText("共" + this.a.g.size() + "个点位");
        }
    };
    private List<a> g = new ArrayList();
    private DataAdapter h;
    @BindView(2131755228)
    protected ListView rv_listdown;
    @BindView(2131755192)
    protected TitleBar title_bar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.title_bar, "跑步历史数据");
        this.h = new DataAdapter(c(), null, this.g, false);
        this.rv_listdown.setAdapter(this.h);
        new Thread(this) {
            final /* synthetic */ DataActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g.addAll(new b(this.a.c()).a());
                this.a.f.sendEmptyMessage(1);
            }
        }.start();
    }

    protected int a() {
        return R.layout.activity_data;
    }
}
