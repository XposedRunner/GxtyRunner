package com.example.gita.gxty.ram;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.weiget.DividerDecoration;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;

public class ChooseXieHuiActivity extends BaseActivity {
    ArrayList<TXieHui> f = new ArrayList();
    private XieHuiAdapter g;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleBar.setLeftImageResource(R.mipmap.back);
        this.titleBar.setTitle((CharSequence) "选择协会");
        this.titleBar.setTitleColor(Color.parseColor("#ffffff"));
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ ChooseXieHuiActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.titleBar.setActionTextColor(Color.parseColor("#ffffff"));
        this.titleBar.a(new a(this) {
            final /* synthetic */ ChooseXieHuiActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "添加";
            }

            public int b() {
                return 0;
            }

            public void a(View view) {
                this.a.startActivity(new Intent(this.a.c(), MyXieHuiActivity.class));
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        b();
    }

    protected void onResume() {
        super.onResume();
        this.g.setNewData(null);
        o();
    }

    private void b() {
        this.g = new XieHuiAdapter(this.f);
        this.mRecyclerView.setAdapter(this.g);
        this.g.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ ChooseXieHuiActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TXieHui tXieHui = (TXieHui) this.a.f.get(i);
                Intent intent = new Intent();
                intent.putExtra("xiehui", tXieHui);
                h.b(tXieHui);
                this.a.setResult(-1, intent);
                this.a.finish();
            }
        });
    }

    private void o() {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("association/dataList")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<XieHuiData>>(this, this) {
            final /* synthetic */ ChooseXieHuiActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<XieHuiData>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.a.isFinishing()) {
                    this.a.f = ((XieHuiData) ((LzyResponse) aVar.c()).data).my;
                    this.a.mRecyclerView.addItemDecoration(new DividerDecoration(this.a, this.a.f.size()));
                    this.a.g.addData(this.a.f);
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_choose_xiehui;
    }
}
