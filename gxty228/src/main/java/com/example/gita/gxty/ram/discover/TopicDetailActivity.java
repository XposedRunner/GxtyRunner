package com.example.gita.gxty.ram.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import cn.jiguang.net.HttpUtils;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.FeedAdAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataPage;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Topic;
import com.example.gita.gxty.model.TopicData;
import com.example.gita.gxty.model.TopicData.Detail;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.io.Serializable;
import java.util.ArrayList;

public class TopicDetailActivity extends BaseActivity implements RequestLoadMoreListener {
    private ImageView f;
    private TextView g;
    private TextView h;
    private FeedAdAdapter i;
    private View j;
    private int k = 1;
    private Topic l;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.l = (Topic) getIntent().getSerializableExtra("data");
        a(this.titleBar, "话题");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.j = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        b();
        View inflate = getLayoutInflater().inflate(R.layout.topic_top_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        a(inflate);
        this.i.addHeaderView(inflate);
        this.i.setHeaderAndEmpty(true);
        this.k = 1;
        d(this.k);
    }

    protected int a() {
        return R.layout.activity_topic;
    }

    private void b() {
        this.i = new FeedAdAdapter(new ArrayList(), this);
        this.i.setOnLoadMoreListener(this, this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.i);
        this.i.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ TopicDetailActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(this.a.c(), FeedDetailActivity.class);
                intent.putExtra("data", (Serializable) baseQuickAdapter.getItem(i));
                this.a.startActivity(intent);
            }
        });
    }

    private void a(View view) {
        this.f = (ImageView) view.findViewById(R.id.topicTopImg);
        this.g = (TextView) view.findViewById(R.id.topicTopNameTxt);
        this.h = (TextView) view.findViewById(R.id.topicTopCountTxt);
    }

    private void a(Detail detail) {
        if (detail != null) {
            this.g.setText(detail.title);
            this.h.setText(s.a(detail.desc));
            e.a(c()).a(detail.icon).b((int) R.mipmap.default_huati).a(this.f);
        }
    }

    private void d(final int i) {
        Object dataPage = new DataPage();
        dataPage.userid = q.a(c()).b();
        DataBean a = b.a(dataPage);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a(String.format("discovery/topicDetail/%1$s", new Object[]{this.l.id}) + HttpUtils.URL_AND_PARA_SEPARATOR + BaseActivity.a(i))).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<TopicData>>(this, c()) {
            final /* synthetic */ TopicDetailActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<TopicData>> aVar) {
                boolean z = false;
                super.a((com.lzy.okgo.model.a) aVar);
                int itemCount = this.b.i.getItemCount();
                if (!this.b.isFinishing()) {
                    try {
                        TopicData topicData = (TopicData) ((LzyResponse) aVar.c()).data;
                        if (i == 1) {
                            this.b.a(topicData.detail);
                            this.b.i.setNewData(topicData.feed);
                            if (topicData.feed != null && topicData.feed.size() > 0) {
                                DiscoverActivity.a(this.b.c(), this.b.i, 0, true);
                            }
                        } else if (topicData.feed != null) {
                            if (topicData.feed.size() <= 3) {
                                z = true;
                            }
                            this.b.i.addData(topicData.feed);
                            if (topicData.feed.size() < 10) {
                                this.b.i.loadMoreEnd(true);
                            } else {
                                h.a((Object) "还可以继续加载");
                                this.b.i.loadMoreComplete();
                            }
                            if (topicData.feed != null && topicData.feed.size() > 0) {
                                DiscoverActivity.a(this.b.c(), this.b.i, itemCount, z);
                            }
                        } else {
                            this.b.i.loadMoreFail();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<TopicData>> aVar) {
                super.b(aVar);
                h.b(aVar.d());
                this.b.i.loadMoreFail();
                if (!this.b.isFinishing()) {
                    this.b.a(aVar.d().getMessage());
                }
            }
        });
    }

    public void onLoadMoreRequested() {
        int i = this.k + 1;
        this.k = i;
        d(i);
    }
}
