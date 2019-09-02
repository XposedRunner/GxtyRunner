package com.example.gita.gxty.ram.discover;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jiguang.net.HttpUtils;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.PinglunAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataPage;
import com.example.gita.gxty.model.DataPingLun;
import com.example.gita.gxty.model.Feed;
import com.example.gita.gxty.model.FeedData;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.d;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.ninegrid.NineGridTestLayout;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import java.util.ArrayList;

public class FeedDetailActivity extends BaseActivity implements RequestLoadMoreListener {
    @BindView(2131755235)
    protected View acitonLayout;
    @BindView(2131755237)
    protected EditText commentTxt;
    private PinglunAdapter f;
    private View g;
    private int h = 1;
    private ImageView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private NineGridTestLayout m;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    private TextView n;
    private TextView o;
    private TextView p;
    private Feed q;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "详情");
        this.titleBar.setImmersive(false);
        this.q = (Feed) getIntent().getSerializableExtra("data");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.g = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        o();
        View inflate = getLayoutInflater().inflate(R.layout.feed_detail_top, (ViewGroup) this.mRecyclerView.getParent(), false);
        a(inflate);
        this.f.addHeaderView(inflate);
        this.f.setHeaderAndEmpty(true);
        this.h = 1;
        d(this.h);
    }

    private void a(final View view) {
        this.i = (ImageView) view.findViewById(R.id.imageView);
        this.j = (TextView) view.findViewById(R.id.timeTxt);
        this.k = (TextView) view.findViewById(R.id.nickName);
        this.l = (TextView) view.findViewById(R.id.content);
        this.m = (NineGridTestLayout) view.findViewById(R.id.nineGrid);
        this.n = (TextView) view.findViewById(R.id.liulanTxt);
        this.o = (TextView) view.findViewById(R.id.pinglunTxt);
        this.p = (TextView) view.findViewById(R.id.zanTxt);
        view.findViewById(R.id.zanlayout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedDetailActivity b;

            public void onClick(View view) {
                BaseActivity.a(this.b.c(), this.b.q.feed_id, this.b.q.like_count, this.b.p, (ImageView) view.findViewById(R.id.zanImg));
            }
        });
    }

    private void a(Feed feed) {
        e.a(c()).a(feed.user_head).b((int) R.mipmap.default_avatar).a(this.i);
        this.j.setText(d.e(feed.getMyabcdTime()));
        this.k.setText(feed.user_name);
        this.l.setText(feed.content_word);
        this.n.setText(feed.view_count + "");
        this.o.setText(feed.feed_count + "");
        this.p.setText(feed.like_count + "");
        this.m.setIsShowAll(false);
        this.m.setUrlList(feed.content_pic);
    }

    protected int a() {
        return R.layout.activity_feed;
    }

    @OnClick({2131755236})
    void butterknifeOnItemClick(View view) {
        if (R.id.actionBtn == view.getId()) {
            b();
        }
    }

    private void b() {
        String obj = this.commentTxt.getText().toString();
        if (r.a(obj)) {
            a("请输入评论");
            return;
        }
        Object dataPingLun = new DataPingLun();
        dataPingLun.content = obj;
        dataPingLun.feed_id = this.q.feed_id;
        DataBean a = b.a(dataPingLun);
        ((PostRequest) ((PostRequest) ((PostRequest) a.b(com.example.gita.gxty.a.a("discovery/comment")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Void>>(this, c()) {
            final /* synthetic */ FeedDetailActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<Void>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                this.a.a(((LzyResponse) aVar.c()).msg);
                this.a.commentTxt.setText("");
                this.a.h = 1;
                this.a.d(this.a.h);
            }
        });
    }

    private void o() {
        this.f = new PinglunAdapter(new ArrayList(), c(), 0);
        this.f.setOnLoadMoreListener(this, this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.f);
        this.f.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ FeedDetailActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            }
        });
    }

    private void d(final int i) {
        Object dataPage = new DataPage();
        dataPage.userid = q.a(c()).b();
        DataBean a = b.a(dataPage);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a(String.format("discovery/feedDetail/%1$s", new Object[]{this.q.feed_id}) + HttpUtils.URL_AND_PARA_SEPARATOR + BaseActivity.a(i))).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<FeedData>>(this, this, false) {
            final /* synthetic */ FeedDetailActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<FeedData>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.b.isFinishing()) {
                    try {
                        FeedData feedData = (FeedData) ((LzyResponse) aVar.c()).data;
                        if (feedData == null) {
                            this.b.f.loadMoreFail();
                        } else if (i == 1) {
                            if (feedData.feed != null) {
                                this.b.a(feedData.feed);
                            }
                            this.b.f.setNewData(feedData.comment_list);
                        } else {
                            this.b.f.addData(feedData.comment_list);
                            if (feedData.comment_list.size() < 10) {
                                this.b.f.loadMoreEnd(true);
                                return;
                            }
                            h.a((Object) "还可以继续加载");
                            this.b.f.loadMoreComplete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<FeedData>> aVar) {
                super.b(aVar);
                h.b(aVar.d());
                this.b.f.loadMoreFail();
                if (!this.b.isFinishing()) {
                    this.b.a(aVar.d().getMessage());
                }
            }
        });
    }

    public void onLoadMoreRequested() {
        int i = this.h + 1;
        this.h = i;
        d(i);
    }
}
