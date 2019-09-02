package com.example.gita.gxty.ram;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.a;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.MainActivity;
import com.example.gita.gxty.activity.RegisterActivity;
import com.example.gita.gxty.activity.SchoollistActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.GetDepartment;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.School;
import com.example.gita.gxty.model.Sex;
import com.example.gita.gxty.model.User;
import com.example.gita.gxty.model.UserResult;
import com.example.gita.gxty.model.YuanXi;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.List;

public class Register2Activity extends BaseActivity {
    @BindView(2131755369)
    protected TextView department;
    private User f;
    private a<YuanXi> g;
    private ArrayList<YuanXi> h = new ArrayList();
    private a<Sex> i;
    private ArrayList<Sex> j = new ArrayList();
    @BindView(2131755278)
    protected EditText name;
    @BindView(2131755364)
    protected EditText nickname;
    @BindView(2131755367)
    protected TextView school;
    @BindView(2131755372)
    protected TextView sex;
    @BindView(2131755365)
    protected EditText studentid;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j.add(new Sex("1", "男"));
        this.j.add(new Sex("2", "女"));
        this.titleBar.setLeftImageResource(R.drawable.back_bg);
        this.titleBar.setTitle((CharSequence) "");
        this.titleBar.setBackgroundColor(getResources().getColor(R.color.black_shen));
        this.titleBar.setTitleSize(17.0f);
        this.titleBar.setTitleColor(getResources().getColor(R.color.titleColor8));
        this.titleBar.setActionTextColor(Color.parseColor("#999999"));
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ Register2Activity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        this.f = (User) getIntent().getSerializableExtra("user");
        if (this.f == null) {
            h.b("异常，未知，严重 错误！！！");
            finish();
            return;
        }
        this.g = new a(this);
        this.g.a("选择院系");
        this.g.setOnoptionsSelectListener(new a.a(this) {
            final /* synthetic */ Register2Activity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                if (this.a.h != null) {
                    YuanXi yuanXi = (YuanXi) this.a.h.get(i);
                    this.a.f.department_id = yuanXi.department_id;
                    this.a.department.setText(yuanXi.department_name);
                }
            }
        });
        this.i = new a(this);
        this.i.a("选择性别");
        this.i.a(this.j);
        this.i.a(false);
        this.i.setOnoptionsSelectListener(new a.a(this) {
            final /* synthetic */ Register2Activity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                if (this.a.j != null) {
                    Sex sex = (Sex) this.a.j.get(i);
                    this.a.f.sex = sex.id;
                    this.a.sex.setText(sex.name);
                }
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(c(), MainActivity.class));
        finish();
    }

    protected int a() {
        return R.layout.activity_registertwo;
    }

    @OnClick({2131755261, 2131755366, 2131755371, 2131755368})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.register /*2131755261*/:
                Object obj = this.nickname.getText().toString();
                Object obj2 = this.studentid.getText().toString();
                Object obj3 = this.name.getText().toString();
                if (TextUtils.isEmpty(this.f.school)) {
                    s.a((CharSequence) "请选择学校");
                    return;
                } else if (TextUtils.isEmpty(this.f.department_id)) {
                    s.a((CharSequence) "学院不能为空");
                    return;
                } else if (TextUtils.isEmpty(this.f.sex)) {
                    s.a((CharSequence) "请选择性别");
                    return;
                } else if (TextUtils.isEmpty(obj)) {
                    s.a((CharSequence) "昵称不能为空");
                    return;
                } else if (TextUtils.isEmpty(obj3)) {
                    s.a((CharSequence) "姓名不能为空");
                    return;
                } else if (TextUtils.isEmpty(obj2)) {
                    s.a((CharSequence) "学号不能为空");
                    return;
                } else {
                    this.f.studentid = obj2;
                    this.f.username = obj3;
                    this.f.nickname = obj;
                    this.f.info = b.b();
                    this.f.type = b.a();
                    DataBean a = b.a(this.f);
                    ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("reg")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<UserResult>>(this, this) {
                        final /* synthetic */ Register2Activity a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<UserResult>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            UserResult userResult = (UserResult) ((LzyResponse) aVar.c()).data;
                            q.a(this.a.c()).a("userid", userResult.userid);
                            q.a(this.a.c()).a("school", userResult.school);
                            q.a(this.a.c()).a("signature", userResult.signature);
                            q.a(this.a.c()).a("goal", userResult.goal);
                            q.a(this.a.c()).a("surplus", userResult.surplus);
                            q.a(this.a.c()).a("last_time", userResult.last_time);
                            q.a(this.a.c()).a("teacher", userResult.teacher);
                            q.a(this.a.c()).a("course", userResult.course);
                            q.a(this.a.c()).a("username", userResult.username);
                            q.a(this.a.c()).a("nickname", userResult.nickname);
                            q.a(this.a.c()).a("photo", userResult.photo);
                            q.a(this.a.c()).a("length", Float.toString(Float.parseFloat(userResult.goal) - Float.parseFloat(userResult.surplus)));
                            q.a(this.a.c()).a("islogin", Boolean.valueOf(true));
                            q.a(this.a.c()).a("schoolId", userResult.schoolId);
                            q.a(this.a.c()).a("utoken", userResult.utoken);
                            BuglyUtils.b(this.a.getApplication());
                            this.a.startActivity(new Intent(this.a, MainTabActivity.class));
                            com.example.gita.gxty.utils.a.a(MainActivity.class);
                            com.example.gita.gxty.utils.a.a(RegisterActivity.class);
                            this.a.finish();
                        }
                    });
                    return;
                }
            case R.id.ll_school /*2131755366*/:
                startActivityForResult(new Intent(this, SchoollistActivity.class), 1001);
                return;
            case R.id.ll_department /*2131755368*/:
                if (TextUtils.isEmpty(this.f.school)) {
                    s.a((CharSequence) "请选择学校");
                    return;
                } else if (this.h == null || this.h.isEmpty()) {
                    s.a((CharSequence) "该学校没有院系数据，请选择其他学校或联系管理员");
                    return;
                } else {
                    this.g.d();
                    return;
                }
            case R.id.ll_sex /*2131755371*/:
                this.i.d();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1 && intent != null) {
            try {
                School school = (School) intent.getSerializableExtra("school");
                if (school != null) {
                    this.f.school = school.id;
                    this.school.setText(school.name);
                    this.f.department_id = "";
                    this.department.setText("");
                    b();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void b() {
        com.lzy.okgo.b.b anonymousClass5 = new com.example.gita.gxty.a.a<LzyResponse<List<YuanXi>>>(this, c()) {
            final /* synthetic */ Register2Activity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<YuanXi>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    this.a.h.clear();
                    this.a.h.addAll((ArrayList) ((LzyResponse) aVar.c()).data);
                    if (this.a.h != null && !this.a.h.isEmpty()) {
                        this.a.g.a(this.a.h);
                        this.a.g.a(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Object getDepartment = new GetDepartment();
        getDepartment.schoolID = this.f.school;
        DataBean a = b.a(getDepartment);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("School/get_department")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(anonymousClass5);
    }
}
