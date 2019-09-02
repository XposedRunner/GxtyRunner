package com.example.gita.gxty.ram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.SportRecordAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataSportRecord;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.io.Serializable;
import java.util.ArrayList;

public class SportRecordActivity extends BaseActivity {
    ArrayList<DataSportRecord> f = new ArrayList();
    private SportRecordAdapter g;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "锻炼记录");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        b();
        j();
        a((RelativeLayout) findViewById(R.id.baiduAd));
    }

    protected void onResume() {
        super.onResume();
        this.g.setNewData(null);
        o();
    }

    private void b() {
        this.g = new SportRecordAdapter(this.f);
        this.mRecyclerView.setAdapter(this.g);
        this.g.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SportRecordActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(this.a.c(), SportTermActivity.class);
                intent.putExtra("data", (Serializable) this.a.f.get(i));
                this.a.startActivity(intent);
            }
        });
    }

    private void o() {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("School/getTermList")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<ArrayList<DataSportRecord>>>(this, this) {
            final /* synthetic */ SportRecordActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<ArrayList<DataSportRecord>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.a.isFinishing()) {
                    this.a.f = (ArrayList) ((LzyResponse) aVar.c()).data;
                    this.a.g.addData(this.a.f);
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_sport_record;
    }
}
