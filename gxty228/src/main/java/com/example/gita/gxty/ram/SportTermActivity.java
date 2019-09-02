package com.example.gita.gxty.ram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.SportTermAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataSportRecord;
import com.example.gita.gxty.model.DataSportTerm;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.TermData;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;

public class SportTermActivity extends BaseActivity {
    ArrayList<DataSportTerm> f = new ArrayList();
    private DataSportRecord g;
    private SportTermAdapter h;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = (DataSportRecord) getIntent().getSerializableExtra("data");
        if (this.g == null) {
            h.a((Object) "00000000000000000");
            finish();
            return;
        }
        a(this.titleBar, this.g.title);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        b();
        j();
    }

    protected void onResume() {
        super.onResume();
        this.h.setNewData(null);
        o();
    }

    private void b() {
        this.h = new SportTermAdapter(this.f);
        this.mRecyclerView.setAdapter(this.h);
        this.h.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SportTermActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if ("1".equals(((DataSportTerm) this.a.f.get(i)).type)) {
                    Intent intent = new Intent(this.a.c(), RunRecordActivity.class);
                    intent.putExtra("term_id", this.a.g.term_id);
                    this.a.startActivity(intent);
                    return;
                }
                intent = new Intent(this.a.c(), SignRecordListActivity.class);
                intent.putExtra("term_id", this.a.g.term_id);
                this.a.startActivity(intent);
            }
        });
    }

    private void o() {
        Object termData = new TermData();
        termData.term_id = this.g.term_id;
        DataBean a = b.a(termData);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("School/getRecordCount")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<ArrayList<DataSportTerm>>>(this, this) {
            final /* synthetic */ SportTermActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<ArrayList<DataSportTerm>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.a.isFinishing()) {
                    this.a.f = (ArrayList) ((LzyResponse) aVar.c()).data;
                    this.a.h.addData(this.a.f);
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_sport_term;
    }
}
