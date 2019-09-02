package com.example.gita.gxty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataUser;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.UserResult;
import com.example.gita.gxty.ram.MainTabActivity;
import com.example.gita.gxty.utils.BuglyUtils;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.g;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.c;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;

public class LoginActivity extends BaseActivity {
    private Boolean f = Boolean.valueOf(false);
    @BindView(2131755238)
    protected EditText mobile;
    @BindView(2131755258)
    protected EditText password;
    @BindView(2131755259)
    protected ImageView show;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.titleBar.setLeftImageResource(R.drawable.back_bg);
            this.titleBar.setTitle((CharSequence) "");
            this.titleBar.setBackgroundColor(getResources().getColor(R.color.black_shen));
            this.titleBar.setTitleSize(17.0f);
            this.titleBar.setTitleColor(getResources().getColor(R.color.titleColor8));
            this.titleBar.setActionTextColor(Color.parseColor("#999999"));
            this.titleBar.setLeftClickListener(new OnClickListener(this) {
                final /* synthetic */ LoginActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.onBackPressed();
                }
            });
            this.titleBar.a(new c(this, "忘记密码?") {
                final /* synthetic */ LoginActivity a;

                public void a(View view) {
                    Intent intent = new Intent(this.a.c(), ForgetPwdActivity.class);
                    intent.putExtra("mobile", this.a.mobile.getText().toString());
                    this.a.startActivity(intent);
                    this.a.finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        a((RelativeLayout) findViewById(R.id.baiduAd));
    }

    protected int a() {
        return R.layout.activity_login;
    }

    @OnClick({2131755260, 2131755259})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.show /*2131755259*/:
                if (this.f.booleanValue()) {
                    this.f = Boolean.valueOf(false);
                    this.show.setImageResource(R.mipmap.hidepsd);
                    this.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    return;
                }
                this.f = Boolean.valueOf(true);
                this.show.setImageResource(R.mipmap.showpsd);
                this.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                return;
            case R.id.login /*2131755260*/:
                final String obj = this.mobile.getText().toString();
                Object obj2 = this.password.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    s.a((CharSequence) "手机号码不能为空");
                    return;
                } else if (TextUtils.isEmpty(obj2)) {
                    s.a((CharSequence) "密码不能为空");
                    return;
                } else {
                    Object dataUser = new DataUser();
                    dataUser.mobile = obj;
                    dataUser.password = obj2;
                    dataUser.info = b.b();
                    dataUser.type = b.a();
                    DataBean a = b.a(dataUser);
                    ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("reg/login")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<UserResult>>(this, this) {
                        final /* synthetic */ LoginActivity b;

                        public void a(com.lzy.okgo.model.a<LzyResponse<UserResult>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            g.a(this.b.c(), obj);
                            q.a(this.b.c()).a("gxty_mobile", obj);
                            UserResult userResult = (UserResult) ((LzyResponse) aVar.c()).data;
                            q.a(this.b.c()).a("userid", userResult.userid);
                            q.a(this.b.c()).a("school", userResult.school);
                            q.a(this.b.c()).a("signature", userResult.signature);
                            q.a(this.b.c()).a("goal", userResult.goal);
                            q.a(this.b.c()).a("surplus", userResult.surplus);
                            q.a(this.b.c()).a("last_time", userResult.last_time);
                            q.a(this.b.c()).a("teacher", userResult.teacher);
                            q.a(this.b.c()).a("course", userResult.course);
                            q.a(this.b.c()).a("username", userResult.username);
                            q.a(this.b.c()).a("photo", userResult.photo);
                            q.a(this.b.c()).a("length", Float.toString(Float.parseFloat(userResult.goal) - Float.parseFloat(userResult.surplus)));
                            q.a(this.b.c()).a("islogin", Boolean.valueOf(true));
                            BuglyUtils.b(this.b.getApplication());
                            q.a(this.b.c()).a("schoolId", userResult.schoolId);
                            q.a(this.b.c()).a("utoken", userResult.utoken);
                            this.b.startActivity(new Intent(this.b, MainTabActivity.class));
                            com.example.gita.gxty.utils.a.a(MainActivity.class);
                            this.b.finish();
                        }
                    });
                    return;
                }
            default:
                return;
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(c(), MainActivity.class));
        finish();
    }
}
