package com.example.gita.gxty.ram.discover;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.EasyRefreshLayout.EasyEvent;
import com.amap.api.location.AMapLocation;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.FeedAdAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataPage;
import com.example.gita.gxty.model.DataXHAds;
import com.example.gita.gxty.model.DiscoverData;
import com.example.gita.gxty.model.DiscoverData.Banner;
import com.example.gita.gxty.model.Feed;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Topic;
import com.example.gita.gxty.model.XingKongAds;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.GlideImageLoader;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.hlist.HorizontalListView;
import com.example.gita.gxty.weiget.mywebview.RLWebViewActivity;
import com.example.gita.gxty.weiget.vtext.Notice;
import com.example.gita.gxty.weiget.vtext.VerticalTextview;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.youth.banner.listener.OnBannerListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import org.json.JSONObject;

public class DiscoverActivity extends BaseActivity {
    @BindView(2131755234)
    protected EasyRefreshLayout easylayout;
    public List<Banner> f = new ArrayList();
    private com.youth.banner.Banner g;
    private FeedAdAdapter h;
    private View i;
    private int j = 1;
    private HorizontalListView k;
    private VerticalTextview l;
    private View m;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    private List<String> n;
    private long o = 0;

    public interface b {
        void a(XingKongAds xingKongAds);
    }

    class a extends BaseAdapter {
        List<Topic> a;
        final /* synthetic */ DiscoverActivity b;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(DiscoverActivity discoverActivity, List<Topic> list) {
            this.b = discoverActivity;
            this.a = list;
        }

        public int getCount() {
            return this.a.size();
        }

        public Topic a(int i) {
            return (Topic) this.a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.b.c()).inflate(R.layout.item_dis_img, null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.disImg);
            TextView textView = (TextView) view.findViewById(R.id.disTxt);
            Topic a = a(i);
            e.a(this.b.c()).a(a.icon).b((int) R.mipmap.default_huati).a(imageView);
            textView.setText(a.title);
            return view;
        }
    }

    static /* synthetic */ int a(DiscoverActivity discoverActivity) {
        int i = discoverActivity.j + 1;
        discoverActivity.j = i;
        return i;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.i = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        b();
        View inflate = getLayoutInflater().inflate(R.layout.banner_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        a(inflate);
        this.h.addHeaderView(inflate);
        this.h.setHeaderAndEmpty(true);
        o();
    }

    protected int a() {
        return R.layout.activity_discover;
    }

    private void b() {
        this.h = new FeedAdAdapter(new ArrayList(), this);
        this.mRecyclerView.setAdapter(this.h);
        this.h.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                try {
                    Feed feed = (Feed) baseQuickAdapter.getItem(i);
                    if (feed == null) {
                        return;
                    }
                    if (feed.isNeAd == 0) {
                        Intent intent = new Intent(this.a.c(), FeedDetailActivity.class);
                        intent.putExtra("data", feed);
                        this.a.startActivity(intent);
                    } else if (feed.isNeAd != 1 && feed.isNeAd == 2) {
                        DiscoverActivity.a(feed.xkAds.click_urls);
                        RLWebViewActivity.a(this.a.c(), feed.xkAds.ad_url);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.easylayout.addEasyEvent(new EasyEvent(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void onLoadMore() {
                this.a.d(DiscoverActivity.a(this.a));
            }

            public void onRefreshing() {
                this.a.o();
            }
        });
    }

    private void a(View view) {
        this.g = (com.youth.banner.Banner) view.findViewById(R.id.banner);
        this.g.setDelayTime(4000);
        this.g.setOnBannerListener(new OnBannerListener(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void OnBannerClick(int i) {
                try {
                    if (this.a.f.size() > i && i >= 0) {
                        Banner banner = (Banner) this.a.f.get(i);
                        if ("1".equals(banner.type)) {
                            RLWebViewActivity.a(this.a.c(), banner.href, "详情");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.k = (HorizontalListView) view.findViewById(R.id.gallery);
        this.k.setOnItemClickListener(new AdapterView.OnItemClickListener(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    Intent intent = new Intent(this.a, TopicDetailActivity.class);
                    intent.putExtra("data", (Topic) adapterView.getAdapter().getItem(i));
                    this.a.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.m = view.findViewById(R.id.noticeLayout);
        this.l = (VerticalTextview) view.findViewById(R.id.noticeView);
        this.l.a(14.0f, 5, getResources().getColor(R.color.grey_999999));
        this.l.setTextStillTime(4000);
        this.l.setAnimTime(300);
        this.l.setOnItemClickListener(new com.example.gita.gxty.weiget.vtext.VerticalTextview.a(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void a(int i, Notice notice) {
                if (notice != null) {
                    try {
                        if ("1".equals(notice.type)) {
                            RLWebViewActivity.a(this.a.c(), notice.href, notice.title);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        view.findViewById(R.id.articleLayout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(new Intent(this.a.c(), ArticleActivity.class));
            }
        });
        view.findViewById(R.id.rankLayout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(new Intent(this.a.c(), RankActivity.class));
            }
        });
        view.findViewById(R.id.huodongLayout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(new Intent(this.a.c(), HuoDongActivity.class));
            }
        });
        view.findViewById(R.id.searchLayout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoverActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(new Intent(this.a.c(), SearchActivity.class));
            }
        });
    }

    private void o() {
        DataBean a = com.example.gita.gxty.utils.b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("discovery/init")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<DiscoverData>>(this, this, false) {
            final /* synthetic */ DiscoverActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<DiscoverData>> aVar) {
                boolean z = false;
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    this.a.easylayout.refreshComplete();
                    if (!this.a.isFinishing()) {
                        DiscoverData discoverData = (DiscoverData) ((LzyResponse) aVar.c()).data;
                        if (discoverData != null) {
                            if (discoverData.banner == null || discoverData.banner.size() <= 0) {
                                this.a.g.setBackgroundResource(R.mipmap.default_banner);
                            } else {
                                this.a.g.setBackground(null);
                                this.a.f.clear();
                                this.a.g.setVisibility(0);
                                this.a.f.addAll(discoverData.banner);
                                this.a.g.setImages(discoverData.banner).setImageLoader(new GlideImageLoader()).start();
                            }
                            if (discoverData.notice == null || discoverData.notice.size() <= 0) {
                                this.a.m.setVisibility(8);
                            } else {
                                this.a.m.setVisibility(0);
                                this.a.l.setTextList(discoverData.notice);
                                this.a.l.b();
                                this.a.l.a();
                            }
                            if (discoverData.topic == null || discoverData.topic.size() <= 0) {
                                this.a.k.setVisibility(8);
                            } else {
                                this.a.k.setVisibility(0);
                                this.a.k.setAdapter(new a(this.a, discoverData.topic));
                            }
                            this.a.j = 1;
                            if (discoverData.feed == null || discoverData.feed.size() <= 0) {
                                this.a.h.setNewData(null);
                                return;
                            }
                            if (discoverData.feed.size() <= 3) {
                                z = true;
                            }
                            this.a.b(discoverData.feed);
                            this.a.h.setNewData(discoverData.feed);
                            DiscoverActivity.a(this.a.c(), this.a.h, 0, z);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<DiscoverData>> aVar) {
                super.b(aVar);
                this.a.easylayout.refreshComplete();
            }
        });
    }

    public static void a(final BaseActivity baseActivity, final FeedAdAdapter feedAdAdapter, int i, boolean z) {
        if (!n.a((Context) baseActivity).g()) {
            return;
        }
        if (n.a((Context) baseActivity).h()) {
            h.b("星河广告");
            a(baseActivity, new b() {
                public void a(final XingKongAds xingKongAds) {
                    baseActivity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 b;

                        public void run() {
                            if (xingKongAds != null) {
                                feedAdAdapter.a(xingKongAds, 0, false);
                            }
                        }
                    });
                }
            });
            return;
        }
        h.b("广点通广告");
        BuglyUtils.a((Activity) baseActivity, feedAdAdapter, i, z);
    }

    private void b(List<Feed> list) {
        if (list != null && list.size() > 0) {
            this.n = new ArrayList();
            for (Feed feed : list) {
                this.n.add(feed.feed_id);
            }
        }
    }

    private void d(int i) {
        Object dataPage = new DataPage();
        dataPage.userid = q.a(c()).b();
        DataBean a = com.example.gita.gxty.utils.b.a(dataPage);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("discovery/feedList?" + BaseActivity.a(i))).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Feed>>>(this, this, false) {
            final /* synthetic */ DiscoverActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<Feed>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                this.a.easylayout.loadMoreComplete();
                if (!this.a.isFinishing()) {
                    try {
                        this.a.p();
                        boolean z = true;
                        int itemCount = this.a.h.getItemCount();
                        List list = (List) ((LzyResponse) aVar.c()).data;
                        if (list != null && list.size() > 0) {
                            if (list.size() > 3) {
                                z = false;
                            }
                            this.a.b(list);
                            this.a.h.addData((Collection) list);
                            DiscoverActivity.a(this.a.c(), this.a.h, itemCount, z);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<List<Feed>>> aVar) {
                super.b(aVar);
                h.b(aVar.d());
                this.a.easylayout.loadMoreFail();
                if (!this.a.isFinishing()) {
                    this.a.a(aVar.d().getMessage());
                }
            }
        });
    }

    private void p() {
        if (this.n == null || this.n.isEmpty()) {
            h.b("yiduFeedIds is null");
            return;
        }
        Object dataPage = new DataPage();
        dataPage.userid = q.a(c()).b();
        dataPage.feedIds = this.n;
        DataBean a = com.example.gita.gxty.utils.b.a(dataPage);
        ((PostRequest) ((PostRequest) ((PostRequest) com.lzy.okgo.a.b(com.example.gita.gxty.a.a("discovery/feedPv")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Feed>>>(this, this, false) {
            final /* synthetic */ DiscoverActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<Feed>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                h.b("------已读成功------------");
                this.a.n = null;
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<List<Feed>>> aVar) {
                h.b("------已读失败------------");
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (this.l != null) {
            this.l.a();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.l != null) {
            this.l.b();
        }
    }

    private static void a(final BaseActivity baseActivity, final b bVar) {
        final DataXHAds dataXHAds = new DataXHAds();
        dataXHAds.appKey = "PYvL2cV3hbSWjH8GjBnp";
        dataXHAds.slotId = "800505";
        new Thread() {
            public void run() {
                try {
                    AMapLocation c = MyApplication.e().c();
                    String a = DiscoverActivity.b(baseActivity);
                    if (c != null) {
                        dataXHAds.initDevice(c.getLatitude(), c.getLongitude(), a);
                    } else {
                        dataXHAds.initDevice(121.476764d, 31.234055d, a);
                    }
                    com.google.gson.e eVar = new com.google.gson.e();
                    String a2 = eVar.a(dataXHAds);
                    h.b(a2);
                    JSONObject jSONObject = new JSONObject(new OkHttpClient().newCall(new Builder().url("http://adapi.star-galaxy.cn/apis/ssp/infomation").post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), a2)).build()).execute().body().string());
                    h.b(jSONObject.toString());
                    int i = jSONObject.getInt("code");
                    String string = jSONObject.getString("msg");
                    if (i == 1) {
                        XingKongAds xingKongAds = (XingKongAds) eVar.a(jSONObject.getJSONObject("ad").toString(), XingKongAds.class);
                        h.b(xingKongAds);
                        bVar.a(xingKongAds);
                        return;
                    }
                    h.b("星空广告 code : " + i + "   msg: " + string);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static String b(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://pv.sohu.com/cityjson?ie=utf-8").openConnection().getInputStream(), "utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    String replace = stringBuffer.toString().replace("var returnCitySN = ", "");
                    return new JSONObject(replace.substring(0, replace.length() - 1)).getString("cip");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void a(final List<String> list) {
        new Thread() {
            public void run() {
                for (String str : list) {
                    h.b(str);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openConnection().getInputStream(), "utf-8"));
                        StringBuffer stringBuffer = new StringBuffer();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuffer.append(readLine);
                        }
                        bufferedReader.close();
                        h.b(stringBuffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.o > 2000) {
            s.a((CharSequence) "再按一次退出程序");
            this.o = System.currentTimeMillis();
        } else {
            k();
        }
        return true;
    }
}
