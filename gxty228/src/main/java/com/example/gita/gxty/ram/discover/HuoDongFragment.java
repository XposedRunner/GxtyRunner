package com.example.gita.gxty.ram.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseFragment;
import com.example.gita.gxty.adapter.HuoDongAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataPage;
import com.example.gita.gxty.model.HuoDong;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HuoDongFragment extends SearchBaseFragment implements RequestLoadMoreListener {
    protected RecyclerView b;
    private HuoDongAdapter e;
    private View f;
    private View g;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.g = layoutInflater.inflate(R.layout.frag_huodong, null);
        return this.g;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = (RecyclerView) this.g.findViewById(R.id.rv_list);
        this.b.setLayoutManager(new LinearLayoutManager(a()));
        this.f = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.b.getParent(), false);
        d();
        this.e.setEmptyView(this.f);
        b(b());
    }

    private void d() {
        this.e = new HuoDongAdapter(new ArrayList());
        this.e.setOnLoadMoreListener(this, this.b);
        this.b.setAdapter(this.e);
        this.e.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ HuoDongFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(this.a.a(), HuoDongDetailActivity.class);
                intent.putExtra("data", (Serializable) baseQuickAdapter.getItem(i));
                intent.putExtra("isHuodong", true);
                this.a.startActivity(intent);
            }
        });
    }

    public void b(final int i) {
        Object dataPage = new DataPage();
        if (r.b(this.c)) {
            dataPage.keyword = this.c;
        } else if (c()) {
            this.e.setNewData(null);
            return;
        }
        dataPage.type = "2";
        dataPage.page = "" + i;
        dataPage.limit = "" + a;
        dataPage.userid = q.a(a()).b();
        DataBean a = b.a(dataPage);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("art/schArt")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<HuoDong>>>(this, a()) {
            final /* synthetic */ HuoDongFragment b;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<HuoDong>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (this.b.a() != null && !this.b.a().isFinishing()) {
                    try {
                        if (i == 1) {
                            this.b.e.setNewData(null);
                            h.a((Object) "第一次加载");
                        }
                        List list = (List) ((LzyResponse) aVar.c()).data;
                        if (list != null) {
                            this.b.e.addData((Collection) list);
                            if (list.size() < BaseFragment.a) {
                                this.b.e.loadMoreEnd(true);
                                return;
                            }
                            h.a((Object) "还可以继续加载");
                            this.b.e.loadMoreComplete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<List<HuoDong>>> aVar) {
                super.b(aVar);
                this.b.e.loadMoreFail();
                h.b(aVar.d());
                if (this.b.a() != null && !this.b.a().isFinishing()) {
                    this.b.a(aVar.d().getMessage());
                }
            }
        });
    }

    public void onLoadMoreRequested() {
        int i = this.d + 1;
        this.d = i;
        b(i);
    }
}
