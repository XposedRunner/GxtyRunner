package com.example.gita.gxty.ram.discover;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import cn.jiguang.net.HttpUtils;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.EasyRefreshLayout.EasyEvent;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.PinglunAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataComment;
import com.example.gita.gxty.model.DataCommentLike;
import com.example.gita.gxty.model.DataFeedLike;
import com.example.gita.gxty.model.DataNews;
import com.example.gita.gxty.model.HuoDong;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.NewsDetail;
import com.example.gita.gxty.model.Pinglun;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.b;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HuoDongDetailActivity extends BaseActivity {
    private static final Map<String, Boolean> x = new HashMap();
    private static final Map<String, Boolean> y = new HashMap();
    @BindView(2131755236)
    protected View actionBtn;
    @BindView(2131755246)
    protected View actionLayout;
    @BindView(2131755247)
    protected View commentLayout;
    @BindView(2131755237)
    protected EditText commentTxt;
    @BindView(2131755234)
    protected EasyRefreshLayout easylayout;
    protected WebView f;
    protected TextView g;
    protected TextView h;
    protected TextView i;
    protected TextView j;
    protected TextView k;
    protected TextView l;
    protected ImageView m;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    protected View n;
    long o = 0;
    private View p;
    private PinglunAdapter q;
    private HuoDong r;
    private boolean s = true;
    private String t = "";
    @BindView(2131755192)
    protected TitleBar titleBar;
    private String u = "";
    private int v = 1;
    private boolean w = true;

    static /* synthetic */ int d(HuoDongDetailActivity huoDongDetailActivity) {
        int i = huoDongDetailActivity.v + 1;
        huoDongDetailActivity.v = i;
        return i;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.r = (HuoDong) getIntent().getSerializableExtra("data");
        this.s = getIntent().getBooleanExtra("isHuodong", true);
        if (this.s) {
            a(this.titleBar, "活动详情");
        } else {
            a(this.titleBar, "文章详情");
        }
        this.titleBar.a(new b(this, R.mipmap.share) {
            final /* synthetic */ HuoDongDetailActivity a;

            public void a(View view) {
                this.a.c(2);
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(c()));
        this.p = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        this.q = new PinglunAdapter(new ArrayList(), c(), this.s ? 1 : 2);
        this.mRecyclerView.setAdapter(this.q);
        View inflate = getLayoutInflater().inflate(R.layout.huodong_detail_header, (ViewGroup) this.mRecyclerView.getParent(), false);
        a(inflate);
        this.q.addHeaderView(inflate);
        this.q.setHeaderAndEmpty(true);
        this.q.setEmptyView(this.p);
        this.q.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ HuoDongDetailActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                try {
                    this.a.t = "回复 " + ((Pinglun) this.a.q.getData().get(i)).user_name + ":";
                    this.a.commentTxt.setHint(this.a.t);
                    this.a.commentLayout.setVisibility(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.commentTxt.setImeOptions(4);
        this.commentTxt.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ HuoDongDetailActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    this.a.p();
                }
                return false;
            }
        });
        findViewById(R.id.commentBtn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HuoDongDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.p();
            }
        });
        q();
        this.easylayout.addEasyEvent(new EasyEvent(this) {
            final /* synthetic */ HuoDongDetailActivity a;

            {
                this.a = r1;
            }

            public void onLoadMore() {
                if (System.currentTimeMillis() - this.a.o > 3000) {
                    this.a.o = System.currentTimeMillis();
                    this.a.d(HuoDongDetailActivity.d(this.a));
                    return;
                }
                h.b("000000000000000000000000000000000");
                this.a.easylayout.loadMoreComplete();
            }

            public void onRefreshing() {
                this.a.v = 1;
                this.a.d(this.a.v);
            }
        });
        this.v = 1;
        d(this.v);
    }

    private void p() {
        String obj = this.commentTxt.getText().toString();
        if (r.a(obj)) {
            a("请输入评论内容");
            return;
        }
        obj = this.t + obj;
        Object dataComment = new DataComment();
        dataComment.comment = obj;
        dataComment.userid = q.a(c()).b();
        dataComment.newsid = this.r.newsid;
        DataBean a = com.example.gita.gxty.utils.b.a(dataComment);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("art/artReview")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, c()) {
            final /* synthetic */ HuoDongDetailActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                this.a.commentLayout.setVisibility(8);
                this.a.commentTxt.setText("");
                this.a.t = "";
                this.a.a(((LzyResponse) aVar.c()).msg);
                this.a.v = 1;
                this.a.d(this.a.v);
            }
        });
    }

    private void a(View view) {
        this.f = (WebView) view.findViewById(R.id.webView);
        this.g = (TextView) view.findViewById(R.id.titleTxt);
        this.h = (TextView) view.findViewById(R.id.sourceTxt);
        this.i = (TextView) view.findViewById(R.id.timeTxt);
        this.j = (TextView) view.findViewById(R.id.typeTxt);
        this.m = (ImageView) view.findViewById(R.id.imageView);
        this.n = view.findViewById(R.id.cmtEntry);
        this.k = (TextView) view.findViewById(R.id.zanTxt);
        this.l = (TextView) view.findViewById(R.id.yueduTxt);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HuoDongDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.t = "";
                this.a.commentTxt.setHint("输入评论");
                if (this.a.commentLayout.getVisibility() == 0) {
                    this.a.commentLayout.setVisibility(8);
                } else {
                    this.a.commentLayout.setVisibility(0);
                }
            }
        });
        this.commentLayout.setVisibility(8);
        if (this.s) {
            this.actionBtn.setVisibility(8);
            this.m.setVisibility(0);
            return;
        }
        this.actionBtn.setVisibility(8);
        this.m.setVisibility(8);
    }

    private void q() {
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.getSettings().setAllowFileAccess(true);
        this.f.getSettings().setTextZoom(100);
        this.f.getSettings().setSupportZoom(false);
        this.f.getSettings().setBuiltInZoomControls(false);
        this.f.setBackgroundColor(0);
        if (VERSION.SDK_INT >= 19) {
            this.f.getSettings().setLayoutAlgorithm(LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            this.f.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        }
        this.f.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ HuoDongDetailActivity a;

            {
                this.a = r1;
            }

            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                try {
                    webView.loadUrl(webResourceRequest.getUrl().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                try {
                    webView.loadUrl(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                this.a.f.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            }
        });
    }

    private void c(String str) {
        try {
            h.b(str);
            if (this.w && r.b(str)) {
                this.w = false;
                this.f.loadUrl(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d(final int i) {
        Object dataNews = new DataNews();
        dataNews.newsid = this.r.newsid;
        dataNews.userid = q.a(c()).b();
        DataBean a = com.example.gita.gxty.utils.b.a(dataNews);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("art/artDetail") + HttpUtils.URL_AND_PARA_SEPARATOR + BaseActivity.a(this.v)).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<NewsDetail>>(this, this, false) {
            final /* synthetic */ HuoDongDetailActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<NewsDetail>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.b.isFinishing()) {
                    try {
                        NewsDetail newsDetail = (NewsDetail) ((LzyResponse) aVar.c()).data;
                        if (i == 1) {
                            this.b.easylayout.refreshComplete();
                            this.b.g.setText(newsDetail.title);
                            this.b.j.setText(newsDetail.tag);
                            this.b.h.setText(newsDetail.author);
                            this.b.i.setText(newsDetail.time);
                            this.b.k.setText(newsDetail.like_count);
                            this.b.l.setText(newsDetail.view_count);
                            e.a(this.b.c()).a(newsDetail.img).b((int) R.mipmap.default_banner).a(this.b.m);
                            this.b.u = newsDetail.href;
                            this.b.c(this.b.u);
                            if (newsDetail.comment_list != null && !newsDetail.comment_list.isEmpty()) {
                                this.b.q.replaceData(newsDetail.comment_list);
                                return;
                            }
                            return;
                        }
                        this.b.easylayout.loadMoreComplete();
                        if (newsDetail.comment_list != null && !newsDetail.comment_list.isEmpty()) {
                            this.b.q.addData(newsDetail.comment_list);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<NewsDetail>> aVar) {
                super.b(aVar);
                if (i == 1) {
                    this.b.easylayout.refreshComplete();
                    return;
                }
                this.b.easylayout.loadMoreFail();
                this.b.v = this.b.v - 1;
                if (this.b.v < 1) {
                    this.b.v = 1;
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_huodong_detail;
    }

    public static void c(BaseActivity baseActivity, String str, int i, TextView textView, ImageView imageView) {
        if (textView != null && imageView != null) {
            boolean z;
            String str2;
            String str3 = "art/doCommentLike";
            if (x.containsKey(str) && ((Boolean) x.get(str)).booleanValue()) {
                z = true;
                str2 = "art/doCommentunLike";
            } else {
                z = false;
                str2 = "art/doCommentLike";
            }
            Object dataFeedLike = new DataFeedLike();
            dataFeedLike.feed_id = str;
            DataBean a = com.example.gita.gxty.utils.b.a(dataFeedLike);
            final TextView textView2 = textView;
            final int i2 = i;
            final ImageView imageView2 = imageView;
            final String str4 = str;
            ((PostRequest) ((PostRequest) ((PostRequest) a.b(com.example.gita.gxty.a.a(str2)).tag(baseActivity)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Void>>(baseActivity) {
                public void a(com.lzy.okgo.model.a<LzyResponse<Void>> aVar) {
                    super.a((com.lzy.okgo.model.a) aVar);
                    if (z) {
                        textView2.setText("" + i2);
                        imageView2.setImageResource(R.drawable.dis8);
                        HuoDongDetailActivity.x.put(str4, Boolean.valueOf(false));
                        return;
                    }
                    textView2.setText("" + (i2 + 1));
                    imageView2.setImageResource(R.drawable.dis82);
                    HuoDongDetailActivity.x.put(str4, Boolean.valueOf(true));
                }
            });
        }
    }

    public static void d(BaseActivity baseActivity, String str, int i, TextView textView, ImageView imageView) {
        if (textView != null && imageView != null) {
            boolean z;
            String str2;
            String str3 = "art/doCommentLike";
            if (y.containsKey(str) && ((Boolean) y.get(str)).booleanValue()) {
                z = true;
                str2 = "art/doCommentunLike";
            } else {
                z = false;
                str2 = "art/doCommentLike";
            }
            Object dataCommentLike = new DataCommentLike();
            dataCommentLike.comment_id = str;
            DataBean a = com.example.gita.gxty.utils.b.a(dataCommentLike);
            final TextView textView2 = textView;
            final int i2 = i;
            final ImageView imageView2 = imageView;
            final String str4 = str;
            ((PostRequest) ((PostRequest) ((PostRequest) a.b(com.example.gita.gxty.a.a(str2)).tag(baseActivity)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Void>>(baseActivity) {
                public void a(com.lzy.okgo.model.a<LzyResponse<Void>> aVar) {
                    super.a((com.lzy.okgo.model.a) aVar);
                    if (z) {
                        textView2.setText("" + i2);
                        imageView2.setImageResource(R.drawable.dis8);
                        HuoDongDetailActivity.y.put(str4, Boolean.valueOf(false));
                        return;
                    }
                    textView2.setText("" + (i2 + 1));
                    imageView2.setImageResource(R.drawable.dis82);
                    HuoDongDetailActivity.y.put(str4, Boolean.valueOf(true));
                }
            });
        }
    }
}
