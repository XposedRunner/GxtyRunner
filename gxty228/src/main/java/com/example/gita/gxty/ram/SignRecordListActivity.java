package com.example.gita.gxty.ram;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import cn.jiguang.net.HttpUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.SignRecordAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataSignRecord;
import com.example.gita.gxty.model.Dict;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.SignRecordListData;
import com.example.gita.gxty.model.SignShenSuData;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.List;

public class SignRecordListActivity extends BaseActivity implements RequestLoadMoreListener {
    ArrayList<DataSignRecord> f = new ArrayList();
    private SignRecordAdapter g;
    private int h = 1;
    private int i = 1;
    private String j = "";
    private String k = "";
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = getIntent().getStringExtra("ass_id");
        this.k = getIntent().getStringExtra("term_id");
        a(this.titleBar, "签到记录");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        b();
        d(1);
        j();
    }

    private void b() {
        this.g = new SignRecordAdapter(this.f);
        this.mRecyclerView.setAdapter(this.g);
        this.g.setOnLoadMoreListener(this, this.mRecyclerView);
        this.g.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SignRecordListActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DataSignRecord dataSignRecord = (DataSignRecord) baseQuickAdapter.getData().get(i);
                if ("2".equals(dataSignRecord.status)) {
                    this.a.a(dataSignRecord);
                }
            }
        });
    }

    private void a(DataSignRecord dataSignRecord) {
        Object signShenSuData = new SignShenSuData();
        signShenSuData.userid = q.a(c()).b();
        signShenSuData.ass_id = dataSignRecord.ass_id;
        DataBean a = b.a(signShenSuData);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("association/doAppeal")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Dict>>>(this, this) {
            final /* synthetic */ SignRecordListActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<Dict>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (this.a.isFinishing()) {
                    h.b("页面已关闭");
                } else {
                    this.a.i = 1;
                    this.a.d(this.a.i);
                }
                this.a.a("发起申诉成功！");
            }
        });
    }

    private void d(int i) {
        Object signRecordListData = new SignRecordListData();
        signRecordListData.ass_id = this.j;
        signRecordListData.term_id = this.k;
        DataBean a = b.a(signRecordListData);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("association/mySignRecord") + HttpUtils.URL_AND_PARA_SEPARATOR + BaseActivity.a(i)).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<ArrayList<DataSignRecord>>>(this, this) {
            final /* synthetic */ SignRecordListActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<ArrayList<DataSignRecord>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.a.isFinishing()) {
                    try {
                        this.a.f = (ArrayList) ((LzyResponse) aVar.c()).data;
                        if (this.a.i == 1) {
                            this.a.g.replaceData(this.a.f);
                            return;
                        }
                        this.a.g.addData(this.a.f);
                        if (this.a.f.size() < 10) {
                            h.a((Object) "加载到最后了");
                            this.a.g.loadMoreEnd(true);
                            return;
                        }
                        h.a((Object) "还可以继续加载");
                        this.a.g.loadMoreComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_sign_list;
    }

    public void onLoadMoreRequested() {
        int i = this.i + 1;
        this.i = i;
        d(i);
    }
}
