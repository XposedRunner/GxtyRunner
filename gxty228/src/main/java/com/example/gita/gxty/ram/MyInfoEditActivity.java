package com.example.gita.gxty.ram;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.pickerview.a;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataInfo;
import com.example.gita.gxty.model.GetDepartment;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.Sex;
import com.example.gita.gxty.model.YuanXi;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.b.b;
import com.lzy.okgo.request.GetRequest;
import com.yuyh.library.imgsel.common.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class MyInfoEditActivity extends BaseImgActivity {
    @BindView(2131755276)
    protected ImageView avatar;
    private a<YuanXi> f;
    private ArrayList<YuanXi> g = new ArrayList();
    private a<Sex> h;
    private ArrayList<Sex> i = new ArrayList();
    private String j;
    private String k;
    private String l;
    private String m;
    @BindView(2131755307)
    protected TextView myinfo_nameText;
    @BindView(2131755305)
    protected EditText myinfo_nicknameText;
    @BindView(2131755303)
    protected TextView myinfo_renzhengText;
    @BindView(2131755319)
    protected EditText myinfo_shengaoText;
    @BindView(2131755317)
    protected EditText myinfo_tizhongText;
    @BindView(2131755315)
    protected TextView myinfo_xingbieText;
    @BindView(2131755309)
    protected TextView myinfo_xuehaoText;
    @BindView(2131755311)
    protected TextView myinfo_xuexiaoText;
    @BindView(2131755313)
    protected TextView myinfo_yuanxiText;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i.add(new Sex("1", "男"));
        this.i.add(new Sex("2", "女"));
        a(this.titleBar, "编辑资料");
        this.titleBar.a(new TitleBar.a(this) {
            final /* synthetic */ MyInfoEditActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "完成";
            }

            public int b() {
                return 0;
            }

            public void a(View view) {
                this.a.a(this.a.j, this.a.k, this.a.m, this.a.myinfo_shengaoText.getText().toString(), this.a.myinfo_tizhongText.getText().toString(), this.a.myinfo_nicknameText.getText().toString());
            }
        });
        com.yuyh.library.imgsel.a.a().a(new ImageLoader() {
            public void displayImage(Context context, String str, ImageView imageView) {
                e.b(context).a(str).a(imageView);
            }
        });
        this.f = new a(this);
        this.f.a("选择院系");
        this.f.setOnoptionsSelectListener(new a.a(this) {
            final /* synthetic */ MyInfoEditActivity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                if (this.a.g != null) {
                    YuanXi yuanXi = (YuanXi) this.a.g.get(i);
                    this.a.k = yuanXi.department_id;
                    this.a.l = yuanXi.department_name;
                    this.a.myinfo_yuanxiText.setText(this.a.l);
                }
            }
        });
        this.h = new a(this);
        this.h.a("选择性别");
        this.h.a(this.i);
        this.h.a(false);
        this.h.setOnoptionsSelectListener(new a.a(this) {
            final /* synthetic */ MyInfoEditActivity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                if (this.a.i != null) {
                    this.a.j = ((Sex) this.a.i.get(i)).id;
                    if ("1".equals(this.a.j)) {
                        this.a.myinfo_xingbieText.setText("男");
                    } else {
                        this.a.myinfo_xingbieText.setText("女");
                    }
                }
            }
        });
        t();
    }

    private void t() {
        try {
            this.myinfo_nameText.setText((String) q.a(c()).b("username", ""));
            this.myinfo_nicknameText.setText((String) q.a(c()).b("nickname", ""));
            this.myinfo_xuexiaoText.setText((String) q.a(c()).b("school", ""));
            this.myinfo_xuehaoText.setText((String) q.a(c()).b("stStuNumber", ""));
            this.k = (String) q.a(c()).b("departId8", "");
            this.j = (String) q.a(c()).b("sex", "");
            this.m = (String) q.a(c()).b("photo", "");
            this.l = (String) q.a(c()).b("departName8", "");
            this.myinfo_yuanxiText.setText(this.l);
            if ("1".equals(this.j)) {
                this.myinfo_xingbieText.setText("男");
            } else {
                this.myinfo_xingbieText.setText("女");
            }
            f(this.m);
            this.myinfo_shengaoText.setText((String) q.a(c()).b("height", ""));
            this.myinfo_tizhongText.setText((String) q.a(c()).b("weight", ""));
            Integer num = (Integer) q.a(c()).b("certificationStatus", Integer.valueOf(0));
            if (1 == num.intValue()) {
                this.myinfo_renzhengText.setText("实名认证中");
            } else if (2 == num.intValue()) {
                this.myinfo_renzhengText.setText("实名认证失败");
            } else if (3 == num.intValue()) {
                this.myinfo_renzhengText.setText("实名认证成功");
            } else {
                this.myinfo_renzhengText.setText("去实名认证");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f(String str) {
        try {
            e.a((FragmentActivity) this).a(str).a(this.avatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_myinfo_edit;
    }

    @OnClick({2131755276, 2131755312, 2131755314})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.avatar /*2131755276*/:
                a("avatar", this.avatar);
                d("avatar");
                return;
            case R.id.myinfo_yuanxiLayout /*2131755312*/:
                u();
                return;
            case R.id.myinfo_xingbieLayout /*2131755314*/:
                this.h.d();
                return;
            default:
                return;
        }
    }

    public void a(String str, boolean z) {
        super.a(str, z);
        if (z) {
            String c = c("avatar");
            if (r.b(c)) {
                g(c);
            }
        }
    }

    public boolean b() {
        return true;
    }

    private void g(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ MyInfoEditActivity b;

            public void run() {
                this.b.f();
                try {
                    this.b.m = this.b.e(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.b.g();
            }
        }).start();
    }

    private void u() {
        b anonymousClass6 = new com.example.gita.gxty.a.a<LzyResponse<List<YuanXi>>>(this, c()) {
            final /* synthetic */ MyInfoEditActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<YuanXi>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    this.a.g.clear();
                    this.a.g.addAll((ArrayList) ((LzyResponse) aVar.c()).data);
                    if (this.a.g == null || this.a.g.isEmpty()) {
                        s.a((CharSequence) "该学校没有院系数据，请选择其他学校或联系管理员");
                        return;
                    }
                    this.a.f.a(this.a.g);
                    this.a.f.a(false);
                    this.a.f.d();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Object getDepartment = new GetDepartment();
        getDepartment.schoolID = (String) q.a(c()).b("schoolId", "");
        DataBean a = com.example.gita.gxty.utils.b.a(getDepartment);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("School/get_department")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(anonymousClass6);
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6) {
        Object dataInfo = new DataInfo();
        dataInfo.sex = str;
        dataInfo.department_id = str2;
        dataInfo.stPhoto = str3;
        dataInfo.height = str4;
        dataInfo.weight = str5;
        dataInfo.nickname = str6;
        dataInfo.user_id = q.a(c()).b();
        DataBean a = com.example.gita.gxty.utils.b.a(dataInfo);
        final String str7 = str3;
        final String str8 = str2;
        final String str9 = str4;
        final String str10 = str5;
        final String str11 = str;
        final String str12 = str6;
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("student/editUserDetail")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
            final /* synthetic */ MyInfoEditActivity g;

            public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.g.isFinishing()) {
                    this.g.a("修改成功");
                    q.a(this.g.c()).a("photo", str7);
                    q.a(this.g.c()).a("departId8", str8);
                    q.a(this.g.c()).a("departName8", this.g.l);
                    q.a(this.g.c()).a("height", str9 + "");
                    q.a(this.g.c()).a("weight", str10 + "");
                    q.a(this.g.c()).a("sex", str11);
                    q.a(this.g.c()).a("nickname", str12);
                    this.g.finish();
                }
            }
        });
    }
}
