package com.example.gita.gxty.ram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.XieHuiAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.TXieHui;
import com.example.gita.gxty.model.XieHuiData;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.weiget.DividerDecoration;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;

public class MyXieHuiActivity extends BaseActivity {
    ArrayList<TXieHui> f = new ArrayList();
    ArrayList<TXieHui> g = new ArrayList();
    private XieHuiAdapter h;
    private XieHuiAdapter i;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755226)
    protected RecyclerView my_course;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "我的协会");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.my_course.setLayoutManager(new LinearLayoutManager(this));
        b();
    }

    protected void onStart() {
        super.onStart();
        this.i.setNewData(null);
        this.h.setNewData(null);
        o();
    }

    private void b() {
        this.h = new XieHuiAdapter(this.f);
        this.mRecyclerView.setAdapter(this.h);
        this.h.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ MyXieHuiActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                try {
                    TXieHui tXieHui = (TXieHui) this.a.f.get(i);
                    Intent intent = new Intent(this.a, AddXieHuiActivity.class);
                    intent.putExtra("xiehui", tXieHui);
                    this.a.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.i = new XieHuiAdapter(this.g);
        this.my_course.setAdapter(this.i);
        this.i.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ MyXieHuiActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                try {
                    TXieHui tXieHui = (TXieHui) this.a.g.get(i);
                    Intent intent = new Intent(this.a, DeleteXieHuiActivity.class);
                    intent.putExtra("xiehui", tXieHui);
                    this.a.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void o() {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("association/dataList")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<XieHuiData>>(this, this) {
            final /* synthetic */ MyXieHuiActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<XieHuiData>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.a.isFinishing()) {
                    XieHuiData xieHuiData = (XieHuiData) ((LzyResponse) aVar.c()).data;
                    this.a.f = xieHuiData.all;
                    this.a.mRecyclerView.addItemDecoration(new DividerDecoration(this.a, this.a.f.size()));
                    this.a.h.addData(this.a.f);
                    this.a.g = xieHuiData.my;
                    this.a.my_course.addItemDecoration(new DividerDecoration(this.a, this.a.g.size()));
                    this.a.i.addData(this.a.g);
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_myxiehui;
    }
}
