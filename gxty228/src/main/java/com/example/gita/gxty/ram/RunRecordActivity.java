package com.example.gita.gxty.ram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemLongClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.ResultActivity;
import com.example.gita.gxty.adapter.RecordAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataPage;
import com.example.gita.gxty.model.DataRunRecord;
import com.example.gita.gxty.model.Dict;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Record;
import com.example.gita.gxty.model.ShenSuData;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunRecordActivity extends BaseActivity implements RequestLoadMoreListener {
    public static Map<String, String> g = new HashMap();
    List<Record> f = new ArrayList();
    private View h;
    private int i = 1;
    private RecordAdapter j;
    private String k = "";
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k = getIntent().getStringExtra("term_id");
        a(this.titleBar, "跑步记录");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.h = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        this.j = new RecordAdapter(this.f);
        this.j.setOnLoadMoreListener(this, this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.j);
        this.j.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ RunRecordActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                h.a(baseQuickAdapter.getItemCount() + "---" + i);
                Intent intent = new Intent(this.a, ResultActivity.class);
                intent.putExtra("runId", ((Record) ((ArrayList) baseQuickAdapter.getData()).get(i)).id);
                this.a.startActivity(intent);
            }
        });
        this.j.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ RunRecordActivity a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                final Record record = (Record) ((ArrayList) baseQuickAdapter.getData()).get(i);
                if (record != null) {
                    if ("1".equals(record.appealFlag) && "2".equals(record.status)) {
                        new AlertView("跑步记录:" + record.date + " " + record.time, null, "取消", null, new String[]{"发起申诉", "删除"}, this.a.c(), Style.ActionSheet, new c(this) {
                            final /* synthetic */ AnonymousClass2 b;

                            public void a(Object obj, int i) {
                                if (i == 0) {
                                    this.b.a.b(record);
                                } else if (i == 1) {
                                    this.b.a.a(record);
                                }
                            }
                        }).e();
                    } else if ("1".equals(record.appealFlag)) {
                        new AlertView("跑步记录:" + record.date + " " + record.time, null, "取消", null, new String[]{"发起申诉"}, this.a.c(), Style.ActionSheet, new c(this) {
                            final /* synthetic */ AnonymousClass2 b;

                            public void a(Object obj, int i) {
                                if (i == 0) {
                                    this.b.a.b(record);
                                }
                            }
                        }).e();
                    } else if ("2".equals(record.status)) {
                        new AlertView("跑步记录:" + record.date + " " + record.time, null, "取消", null, new String[]{"删除"}, this.a.c(), Style.ActionSheet, new c(this) {
                            final /* synthetic */ AnonymousClass2 b;

                            public void a(Object obj, int i) {
                                if (i == 0) {
                                    this.b.a.a(record);
                                }
                            }
                        }).e();
                    } else {
                        h.b("什么也不用做！！！");
                    }
                }
                return true;
            }
        });
        b();
        j();
    }

    private void a(Record record) {
        Object shenSuData = new ShenSuData();
        shenSuData.userid = q.a(c()).b();
        shenSuData.runId = record.id;
        DataBean a = b.a(shenSuData);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("run/doDelData")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Dict>>>(this, this) {
            final /* synthetic */ RunRecordActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<Dict>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (this.a.isFinishing()) {
                    h.b("页面已关闭");
                } else {
                    this.a.i = 1;
                    this.a.d(this.a.i);
                }
                this.a.a("删除成功！");
            }
        });
    }

    private void b(Record record) {
        Object shenSuData = new ShenSuData();
        shenSuData.userid = q.a(c()).b();
        shenSuData.runId = record.id;
        DataBean a = b.a(shenSuData);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("run/doAppeal")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Dict>>>(this, this) {
            final /* synthetic */ RunRecordActivity a;

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

    private void b() {
        g.clear();
        Object dataRunRecord = new DataRunRecord();
        dataRunRecord.term_id = this.k;
        DataBean a = b.a(dataRunRecord);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("run/runStateDict")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Dict>>>(this, this, false) {
            final /* synthetic */ RunRecordActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<Dict>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (this.a.isFinishing()) {
                    h.b("页面已关闭");
                    return;
                }
                List<Dict> list = (List) ((LzyResponse) aVar.c()).data;
                if (list != null && list.size() > 0) {
                    for (Dict dict : list) {
                        RunRecordActivity.g.put(dict.id, dict.name);
                    }
                }
                this.a.d(1);
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<List<Dict>>> aVar) {
                super.b(aVar);
                h.b(aVar.d());
                if (!this.a.isFinishing()) {
                    s.a(aVar.d().getMessage());
                }
            }
        });
    }

    private void d(final int i) {
        Object dataPage = new DataPage();
        dataPage.term_id = this.k;
        dataPage.page = "" + i;
        dataPage.limit = "9";
        dataPage.userid = q.a(c()).b();
        DataBean a = b.a(dataPage);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/runRecord")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Record>>>(this, this) {
            final /* synthetic */ RunRecordActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<Record>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    this.b.f = (List) ((LzyResponse) aVar.c()).data;
                    if (i == 1) {
                        this.b.j.replaceData(this.b.f);
                        return;
                    }
                    this.b.j.addData(this.b.f);
                    if (this.b.f.size() < 9) {
                        h.a((Object) "加载到最后了");
                        this.b.j.loadMoreEnd(true);
                        return;
                    }
                    h.a((Object) "还可以继续加载");
                    this.b.j.loadMoreComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_record;
    }

    public void onLoadMoreRequested() {
        int i = this.i + 1;
        this.i = i;
        d(i);
    }
}
