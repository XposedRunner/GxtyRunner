package com.example.gita.gxty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataVerification;
import com.example.gita.gxty.model.ForgetPwdCheck;
import com.example.gita.gxty.model.ImageTemp;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.User;
import com.example.gita.gxty.ram.Register2Activity;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.mywebview.RLWebViewActivity;
import com.lzy.okgo.request.GetRequest;
import java.net.URLEncoder;

public class RegisterActivity extends BaseActivity {
    @BindView(2131755362)
    protected CheckBox checkBtn;
    private boolean f = false;
    private a g = new a(this, 60000, 1000);
    private boolean h = true;
    @BindView(2131755239)
    protected ImageView jiaoyanmaImg;
    @BindView(2131755240)
    protected TextView jiaoyanmaTxt;
    @BindView(2131755238)
    protected EditText mobile;
    @BindView(2131755258)
    protected EditText password;
    @BindView(2131755242)
    protected TextView send;
    @BindView(2131755259)
    protected ImageView show;
    @BindView(2131755192)
    protected TitleBar titleBar;
    @BindView(2131755241)
    protected EditText verification;
    @BindView(2131755363)
    protected TextView xieyiBtn;

    class a extends CountDownTimer {
        final /* synthetic */ RegisterActivity a;

        public a(RegisterActivity registerActivity, long j, long j2) {
            this.a = registerActivity;
            super(j, j2);
        }

        public void onFinish() {
            this.a.send.setText("验证码");
            this.a.send.setClickable(true);
        }

        public void onTick(long j) {
            this.a.send.setClickable(false);
            this.a.send.setText((j / 1000) + "s");
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleBar.setLeftImageResource(R.drawable.back_bg);
        this.titleBar.setTitle((CharSequence) "");
        this.titleBar.setBackgroundColor(getResources().getColor(R.color.black_shen));
        this.titleBar.setTitleSize(17.0f);
        this.titleBar.setTitleColor(getResources().getColor(R.color.titleColor8));
        this.titleBar.setActionTextColor(Color.parseColor("#999999"));
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ RegisterActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        b();
        a((RelativeLayout) findViewById(R.id.baiduAd));
    }

    public void onBackPressed() {
        startActivity(new Intent(c(), MainActivity.class));
        finish();
    }

    private void b() {
        try {
            this.jiaoyanmaTxt.setText("");
            Object imageTemp = new ImageTemp();
            imageTemp.time = System.currentTimeMillis() + "";
            DataBean a = b.a(imageTemp);
            String str = com.example.gita.gxty.a.a("code/getCaptcha") + "?sign=" + a.sign + "&data=" + URLEncoder.encode(a.data, "utf-8");
            h.b(str);
            e.a(c()).a(str).a(this.jiaoyanmaImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_register;
    }

    @OnClick({2131755259, 2131755242, 2131755243, 2131755239, 2131755363})
    void butterknifeOnItemClick(View view) {
        final String obj = this.mobile.getText().toString();
        String obj2 = this.password.getText().toString();
        Object obj3 = this.verification.getText().toString();
        CharSequence charSequence = this.jiaoyanmaTxt.getText().toString();
        if (R.id.next == view.getId()) {
            if (TextUtils.isEmpty(obj)) {
                s.a((CharSequence) "手机号码不能为空");
            } else if (obj.length() != 11) {
                s.a((CharSequence) "手机号码格式错误");
            } else if (TextUtils.isEmpty(obj2)) {
                s.a((CharSequence) "密码不能为空");
            } else if (obj2.length() < 6) {
                s.a((CharSequence) "密码不能小于6位");
            } else if (TextUtils.isEmpty(obj3)) {
                s.a((CharSequence) "验证码不能为空");
            } else if (this.checkBtn.isChecked()) {
                User user = new User();
                user.verification = obj3;
                user.mobile = obj;
                user.password = obj2;
                b(user);
            } else {
                s.a((CharSequence) "请阅读并同意注册协议");
            }
        } else if (R.id.show == view.getId()) {
            if (this.f) {
                this.f = false;
                this.show.setImageResource(R.mipmap.hidepsd);
                this.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                return;
            }
            this.f = true;
            this.show.setImageResource(R.mipmap.showpsd);
            this.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else if (R.id.send == view.getId()) {
            if (TextUtils.isEmpty(obj)) {
                s.a((CharSequence) "手机号码不能为空");
            } else if (TextUtils.isEmpty(charSequence)) {
                s.a((CharSequence) "校验码不能为空");
            } else {
                this.send.setClickable(false);
                Object dataVerification = new DataVerification();
                dataVerification.mobile = obj;
                dataVerification.captcha = this.jiaoyanmaTxt.getText().toString();
                dataVerification.from = "0";
                DataBean a = b.a(dataVerification);
                ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("code/getCode")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
                    final /* synthetic */ RegisterActivity b;

                    public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                        super.a((com.lzy.okgo.model.a) aVar);
                        q.a(this.b.c()).a("mobile", obj);
                        this.b.g.start();
                    }

                    public void b(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                        super.b(aVar);
                        this.b.send.setClickable(true);
                        this.b.b();
                    }
                });
            }
        } else if (R.id.jiaoyanmaImg == view.getId()) {
            if (this.h) {
                this.h = false;
                h.b("再点一次切换校验码");
                return;
            }
            b();
            this.h = true;
        } else if (R.id.xieyiBtn == view.getId()) {
            RLWebViewActivity.a(c(), "http://gxhttp.chinacloudapp.cn/index/regXy", "注册协议");
        }
    }

    private void a(User user) {
        Intent intent = new Intent(this, Register2Activity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    private void b(final User user) {
        Object forgetPwdCheck = new ForgetPwdCheck();
        forgetPwdCheck.mobile = user.mobile;
        forgetPwdCheck.code = user.verification;
        forgetPwdCheck.from = "0";
        DataBean a = b.a(forgetPwdCheck);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("code/checkCode")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
            final /* synthetic */ RegisterActivity b;

            public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                h.b((String) ((LzyResponse) aVar.c()).data);
                this.b.a(user);
            }
        });
    }

    protected void onStop() {
        super.onStop();
        q.a(c()).a("mobile");
    }
}
