package com.example.gita.gxty.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;

public class TodayrankActivity extends BaseActivity implements RequestLoadMoreListener {
    ArrayList f = new ArrayList();
    private View g;
    private int h = 1;
    private RankAdapter i;
    private TextView j;
    private TextView k;
    private TextView l;
    private ImageView m;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    private Rank n;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.g = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        b();
        this.i.addHeaderView(a(0, new OnClickListener(this) {
            final /* synthetic */ TodayrankActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        }));
        this.i.setHeaderAndEmpty(true);
        d(1);
    }

    private void b() {
        this.i = new RankAdapter(this.f);
        this.i.setOnLoadMoreListener(this, this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.i);
    }

    private View a(int i, OnClickListener onClickListener) {
        View inflate = getLayoutInflater().inflate(R.layout.today_rank_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        TitleBar titleBar = (TitleBar) inflate.findViewById(R.id.title_bar);
        titleBar.setLeftImageResource(R.mipmap.back);
        titleBar.setTitle((CharSequence) "今日排行榜");
        titleBar.setTitleColor(Color.parseColor("#ffffff"));
        titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ TodayrankActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        titleBar.a(new b(this, R.mipmap.share) {
            final /* synthetic */ TodayrankActivity a;

            public void a(View view) {
                this.a.h();
            }
        });
        this.j = (TextView) inflate.findViewById(R.id.my_name);
        this.k = (TextView) inflate.findViewById(R.id.my_length);
        this.l = (TextView) inflate.findViewById(R.id.no);
        this.m = (ImageView) inflate.findViewById(R.id.my_avatar);
        return inflate;
    }

    private void d(final int i) {
        Object dataRank = new DataRank();
        dataRank.page = "" + i;
        dataRank.limit = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        dataRank.userid = q.a(c()).b();
        dataRank.type = "1";
        DataBean a = com.example.gita.gxty.utils.b.a(dataRank);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/rankList")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<RankAll>>(this, this) {
            final /* synthetic */ TodayrankActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<RankAll>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    if (!this.b.isFinishing()) {
                        RankAll rankAll = (RankAll) ((LzyResponse) aVar.c()).data;
                        this.b.f = rankAll.all;
                        if (i == 1) {
                            this.b.n = rankAll.my;
                            this.b.k.setText(this.b.n.length);
                            this.b.j.setText(this.b.n.name);
                            this.b.l.setText(this.b.n.no);
                            e.a(this.b).a(this.b.n.photo).a(this.b.m);
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
        d(i);
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
