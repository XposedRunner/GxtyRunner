package com.example.gita.gxty.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.adapter.ClassAdapter;
import com.example.gita.gxty.model.Class;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataClass;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.DividerDecoration;
import com.example.gita.gxty.weiget.TitleBar;
import com.google.gson.e;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.Iterator;

public class ClassActivity extends BaseActivity {
    ArrayList<Class> f = new ArrayList();
    private String g;
    private View h;
    private int i = 1;
    private ClassAdapter j;
    private e k = new e();
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, getIntent().getStringExtra("tName"));
        this.g = getIntent().getStringExtra("classid");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.h = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) this.mRecyclerView.getParent(), false);
        b();
        o();
    }

    private void b() {
        this.j = new ClassAdapter(this.f);
        this.mRecyclerView.setAdapter(this.j);
        this.j.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ ClassActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ImageView imageView = (ImageView) ((LinearLayout) view).getChildAt(1);
                if (((Class) this.a.f.get(i)).select.booleanValue()) {
                    ((Class) this.a.f.get(i)).select = Boolean.valueOf(false);
                    imageView.setVisibility(8);
                    return;
                }
                ((Class) this.a.f.get(i)).select = Boolean.valueOf(true);
                imageView.setVisibility(0);
            }
        });
    }

    private void o() {
        Object dataClass = new DataClass();
        dataClass.classid = this.g;
        dataClass.userid = (String) q.a(c()).b("userid", "0");
        DataBean a = b.a(dataClass);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/selPeriod")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<ArrayList<Class>>>(this, this) {
            final /* synthetic */ ClassActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<ArrayList<Class>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                this.a.f = (ArrayList) ((LzyResponse) aVar.c()).data;
                this.a.mRecyclerView.addItemDecoration(new DividerDecoration(this.a, this.a.f.size()));
                this.a.j.addData(this.a.f);
            }
        });
    }

    @OnClick({2131755211})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.select /*2131755211*/:
                Object arrayList = new ArrayList();
                Iterator it = this.f.iterator();
                while (it.hasNext()) {
                    Class classR = (Class) it.next();
                    if (classR.select.booleanValue()) {
                        arrayList.add(classR.id);
                    }
                }
                String a = this.k.a(arrayList);
                Object dataClass = new DataClass();
                dataClass.classid = this.g;
                dataClass.userid = (String) q.a(c()).b("userid", "0");
                dataClass.period = a;
                DataBean a2 = b.a(dataClass);
                ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/selClass")).tag(this)).params("sign", a2.sign, new boolean[0])).params("data", a2.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
                    final /* synthetic */ ClassActivity a;

                    public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                        super.a((com.lzy.okgo.model.a) aVar);
                        this.a.a(((LzyResponse) aVar.c()).msg);
                        this.a.finish();
                    }
                });
                return;
            default:
                return;
        }
    }

    protected int a() {
        return R.layout.activity_class;
    }
}
