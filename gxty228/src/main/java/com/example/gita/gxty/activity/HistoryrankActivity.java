package com.example.gita.gxty.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.adapter.RankAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataRank;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Rank;
import com.example.gita.gxty.model.RankAll;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.b;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;

public class HistoryrankActivity extends BaseActivity implements RequestLoadMoreListener, c {
    ArrayList<Rank> f = new ArrayList();
    private View g;
    private int h = 1;
    private RankAdapter i;
    private LinearLayout j;
    private TextView k;
    private TextView l;
    private TextView m;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    private TextView n;
    private ImageView o;
    private Rank p;
    private int q = 2;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.g = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        b();
        this.i.addHeaderView(a(0, new OnClickListener(this) {
            final /* synthetic */ HistoryrankActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        }));
        this.i.setHeaderAndEmpty(true);
        a(1, this.q);
    }

    private void b() {
        this.i = new RankAdapter(this.f);
        this.i.setOnLoadMoreListener(this, this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.i);
    }

    private View a(int i, OnClickListener onClickListener) {
        View inflate = getLayoutInflater().inflate(R.layout.history_rank_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        TitleBar titleBar = (TitleBar) inflate.findViewById(R.id.title_bar);
        titleBar.setLeftImageResource(R.mipmap.back);
        titleBar.setTitle((CharSequence) "历史排行榜");
        titleBar.setTitleColor(-1);
        titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ HistoryrankActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        titleBar.a(new b(this, R.mipmap.share) {
            final /* synthetic */ HistoryrankActivity a;

            public void a(View view) {
                this.a.h();
            }
        });
        this.k = (TextView) inflate.findViewById(R.id.my_name);
        this.l = (TextView) inflate.findViewById(R.id.my_length);
        this.m = (TextView) inflate.findViewById(R.id.yue);
        this.n = (TextView) inflate.findViewById(R.id.no);
        this.o = (ImageView) inflate.findViewById(R.id.my_avatar);
        this.j = (LinearLayout) inflate.findViewById(R.id.choose);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HistoryrankActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String str = null;
                String[] strArr = null;
                new AlertView(null, str, "取消", strArr, new String[]{"本月", "本学期"}, this.a, Style.ActionSheet, this.a).e();
            }
        });
        return inflate;
    }

    public void a(Object obj, int i) {
        this.f.clear();
        this.i.setNewData(new ArrayList());
        this.h = 1;
        if (i == 0) {
            this.q = 2;
            this.m.setText("本月");
        } else if (i == 1) {
            this.q = 3;
            this.m.setText("本学期");
        }
        a(1, this.q);
    }

    private void a(final int i, int i2) {
        Object dataRank = new DataRank();
        dataRank.page = "" + i;
        dataRank.limit = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        dataRank.userid = q.a(c()).b();
        dataRank.type = "" + i2;
        DataBean a = com.example.gita.gxty.utils.b.a(dataRank);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/rankList")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<RankAll>>(this, this) {
            final /* synthetic */ HistoryrankActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<RankAll>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    if (!this.b.isFinishing()) {
                        RankAll rankAll = (RankAll) ((LzyResponse) aVar.c()).data;
                        this.b.f = rankAll.all;
                        if (i == 1) {
                            this.b.p = rankAll.my;
                            this.b.l.setText(this.b.p.length);
                            this.b.k.setText(this.b.p.name);
                            this.b.n.setText(this.b.p.no);
                            e.a(this.b).a(this.b.p.photo).a(this.b.o);
                        }
                        if (i == 1 && this.b.f.size() == 0) {
                            h.a((Object) "第一次加载");
                            return;
                        }
                        this.b.i.addData(this.b.f);
                        if (this.b.f.size() < 10) {
                            h.a((Object) "加载到最后了");
                            this.b.i.loadMoreEnd(true);
                            return;
                        }
                        h.a((Object) "还可以继续加载");
                        this.b.i.loadMoreComplete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_todayrank;
    }

    public void onLoadMoreRequested() {
        int i = this.h + 1;
        this.h = i;
        a(i, this.q);
    }
}
