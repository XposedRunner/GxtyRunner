package com.example.gita.gxty.ram.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.EasyRefreshLayout.EasyEvent;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.a.a;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.BaseFragment;
import com.example.gita.gxty.adapter.NewRankAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataPage;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.NewRank;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.o;
import com.example.gita.gxty.utils.q;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RankFragment extends BaseFragment {
    protected EasyRefreshLayout b;
    protected RecyclerView c;
    private View d;
    private NewRankAdapter e;
    private View f;
    private int g = 1;
    private View h;
    private String i = "1";

    static /* synthetic */ int a(RankFragment rankFragment) {
        int i = rankFragment.g + 1;
        rankFragment.g = i;
        return i;
    }

    public static RankFragment b(String str) {
        RankFragment rankFragment = new RankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cycle", str);
        rankFragment.setArguments(bundle);
        return rankFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.frag_rank, viewGroup, false);
        this.i = getArguments().getString("cycle");
        return this.d;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = (EasyRefreshLayout) view.findViewById(R.id.easylayout);
        this.c = (RecyclerView) view.findViewById(R.id.rv_list);
        this.c.setLayoutManager(new LinearLayoutManager(a()));
        this.f = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.c.getParent(), false);
        c();
        this.h = getLayoutInflater().inflate(R.layout.rank_top_view, (ViewGroup) this.c.getParent(), false);
        this.e.addHeaderView(this.h);
        this.e.setHeaderAndEmpty(true);
        this.b.addEasyEvent(new EasyEvent(this) {
            final /* synthetic */ RankFragment a;

            {
                this.a = r1;
            }

            public void onLoadMore() {
                this.a.b(RankFragment.a(this.a));
            }

            public void onRefreshing() {
                this.a.b();
            }
        });
        b();
    }

    private void a(NewRank newRank, int i) {
        try {
            if (this.h != null) {
                ImageView imageView = (ImageView) this.h.findViewById(o.a(a()).a("noImg" + i));
                TextView textView = (TextView) this.h.findViewById(o.a(a()).a("noTxt" + i + "1"));
                TextView textView2 = (TextView) this.h.findViewById(o.a(a()).a("noTxt" + i + "2"));
                TextView textView3 = (TextView) this.h.findViewById(o.a(a()).a("noTxt" + i + "3"));
                if (imageView != null) {
                    e.a(a()).a(newRank.user_head).b((int) R.mipmap.default_avatar).a(imageView);
                }
                textView.setText(newRank.user_name);
                textView2.setText(newRank.km);
                textView3.setText(newRank.desc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c() {
        this.e = new NewRankAdapter(a(), new ArrayList(), ((RankActivity) getActivity()).b() + this.i);
        this.c.setAdapter(this.e);
    }

    public void b() {
        this.g = 1;
        b(this.g);
    }

    private void b(final int i) {
        h.b("------------abcdefg-----------------");
        Object dataPage = new DataPage();
        dataPage.userid = q.a(a()).b();
        dataPage.type = ((RankActivity) getActivity()).b();
        dataPage.cycle = this.i;
        DataBean a = b.a(dataPage);
        com.lzy.okgo.b.b anonymousClass2 = new a<LzyResponse<List<NewRank>>>(this, a()) {
            final /* synthetic */ RankFragment b;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<NewRank>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (this.b.a() != null && !this.b.a().isFinishing()) {
                    try {
                        if (i == 1) {
                            this.b.b.refreshComplete();
                        } else {
                            this.b.b.loadMoreComplete();
                        }
                        List list = (List) ((LzyResponse) aVar.c()).data;
                        if (i == 1) {
                            if (list == null || list.size() <= 0) {
                                this.b.e.replaceData(new ArrayList());
                                return;
                            }
                            int size = list.size();
                            if (size == 1) {
                                this.b.a((NewRank) list.get(0), 1);
                            } else if (size == 2) {
                                this.b.a((NewRank) list.get(0), 1);
                                this.b.a((NewRank) list.get(1), 2);
                            } else if (size == 3) {
                                this.b.a((NewRank) list.get(0), 1);
                                this.b.a((NewRank) list.get(1), 2);
                                this.b.a((NewRank) list.get(2), 3);
                            } else {
                                this.b.a((NewRank) list.get(0), 1);
                                this.b.a((NewRank) list.get(1), 2);
                                this.b.a((NewRank) list.get(2), 3);
                            }
                            this.b.e.replaceData(list);
                        } else if (list != null) {
                            this.b.e.addData((Collection) list);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<List<NewRank>>> aVar) {
                super.b(aVar);
                h.b(aVar.d());
                if (i == 1) {
                    this.b.b.refreshComplete();
                } else {
                    this.b.b.loadMoreComplete();
                }
                if (this.b.a() != null && !this.b.a().isFinishing()) {
                    this.b.a().a(aVar.d().getMessage());
                }
            }
        };
        StringBuilder append = new StringBuilder().append("discovery/rankList?");
        a();
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a(append.append(BaseActivity.a(i)).toString())).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(anonymousClass2);
    }
}
