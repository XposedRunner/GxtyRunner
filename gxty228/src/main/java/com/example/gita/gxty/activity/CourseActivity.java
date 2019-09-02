package com.example.gita.gxty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.adapter.CourseAdapter;
import com.example.gita.gxty.model.CourseAll;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataPage;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Mycourse;
import com.example.gita.gxty.model.Tcourse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.DividerDecoration;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.Iterator;

public class CourseActivity extends BaseActivity {
    ArrayList<Tcourse> f = new ArrayList();
    private CourseAdapter g;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755226)
    protected LinearLayout my_course;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "我的课程");
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        b();
    }

    protected void onStart() {
        super.onStart();
        this.my_course.removeAllViews();
        this.g.setNewData(null);
        o();
    }

    private void b() {
        this.g = new CourseAdapter(this.f);
        this.mRecyclerView.setAdapter(this.g);
        this.g.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ CourseActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                try {
                    Intent intent = new Intent(this.a, ClassActivity.class);
                    intent.putExtra("classid", ((Tcourse) this.a.f.get(i)).classid);
                    intent.putExtra("tName", ((Tcourse) this.a.f.get(i)).teacher + " " + ((Tcourse) this.a.f.get(i)).name);
                    this.a.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void o() {
        Object dataPage = new DataPage();
        dataPage.userid = q.a(c()).b();
        DataBean a = b.a(dataPage);
        ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/myClass")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<CourseAll>>(this, this) {
            final /* synthetic */ CourseActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<CourseAll>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                CourseAll courseAll = (CourseAll) ((LzyResponse) aVar.c()).data;
                ArrayList arrayList = courseAll.my.period;
                Mycourse mycourse = courseAll.my;
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, b.a(this.a, 50.0f));
                View textView = new TextView(this.a);
                textView.setTextColor(-1);
                textView.setTextSize(15.0f);
                textView.setGravity(16);
                textView.setText(mycourse.teacher + " " + mycourse.name);
                this.a.my_course.addView(textView, layoutParams);
                this.a.f = courseAll.all;
                this.a.mRecyclerView.addItemDecoration(new DividerDecoration(this.a, this.a.f.size()));
                this.a.g.addData(this.a.f);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    textView = new TextView(this.a.c());
                    textView.setTextColor(-1);
                    textView.setTextSize(15.0f);
                    textView.setGravity(16);
                    textView.setText(mycourse.teacher + " " + mycourse.name);
                    textView.setText(str);
                    this.a.my_course.addView(textView, layoutParams);
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_course;
    }
}
