package com.example.gita.gxty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.request.GetRequest;
import java.net.URLEncoder;

public class ForgetPwdActivity extends BaseActivity {
    private Boolean f = Boolean.valueOf(false);
    private a g = new a(this, 60000, 1000);
    private boolean h = true;
    @BindView(2131755239)
    protected ImageView jiaoyanmaImg;
    @BindView(2131755240)
    protected TextView jiaoyanmaTxt;
    @BindView(2131755238)
    protected EditText mobile;
    @BindView(2131755242)
    protected TextView send;
    @BindView(2131755192)
    protected TitleBar titleBar;
    @BindView(2131755241)
    protected EditText verification;

    class a extends CountDownTimer {
        final /* synthetic */ ForgetPwdActivity a;

        public a(ForgetPwdActivity forgetPwdActivity, long j, long j2) {
            this.a = forgetPwdActivity;
            super(j, j2);
        }

        public void onFinish() {
            this.a.send.setText("获取验证码");
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
            final /* synthetic */ ForgetPwdActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        this.mobile.setText(getIntent().getStringExtra("mobile"));
        b();
    }

    public void onBackPressed() {
        startActivity(new Intent(c(), LoginActivity.class));
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
        return R.layout.activity_forgetpwd;
    }

    @OnClick({2131755242, 2131755243, 2131755239})
    void butterknifeOnItemClick(View view) {
        final String obj = this.mobile.getText().toString();
        Object obj2 = this.verification.getText().toString();
        CharSequence charSequence = this.jiaoyanmaTxt.getText().toString();
        DataBean a;
        if (R.id.next == view.getId()) {
            if (TextUtils.isEmpty(obj)) {
                s.a((CharSequence) "手机号码不能为空");
            } else if (TextUtils.isEmpty(obj2)) {
                s.a((CharSequence) "验证码不能为空");
            } else {
                Object forgetPwdCheck = new ForgetPwdCheck();
                forgetPwdCheck.mobile = obj;
                forgetPwdCheck.code = obj2;
                forgetPwdCheck.from = "1";
                a = b.a(forgetPwdCheck);
                ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("code/checkCode")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
                    final /* synthetic */ ForgetPwdActivity a;

                    public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                        super.a((com.lzy.okgo.model.a) aVar);
                        String str = (String) ((LzyResponse) aVar.c()).data;
                        h.b(str);
                        Intent intent = new Intent(this.a.c(), ForgetPwd2Activity.class);
                        intent.putExtra("ftoken", str);
                        this.a.startActivity(intent);
                        this.a.finish();
                    }
                });
            }
        } else if (R.id.send == view.getId()) {
            if (TextUtils.isEmpty(obj)) {
                s.a((CharSequence) "手机号码不能为空");
            } else if (TextUtils.isEmpty(charSequence)) {
                s.a((CharSequence) "校验码不能为空");
            } else {
                this.send.setClickable(false);
                obj2 = new DataVerification();
                obj2.mobile = obj;
                obj2.captcha = this.jiaoyanmaTxt.getText().toString();
                obj2.from = "1";
                a = b.a(obj2);
                ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("code/getCode")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
                    final /* synthetic */ ForgetPwdActivity b;

                    public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                        super.a((com.lzy.okgo.model.a) aVar);
                        q.a(this.b.c()).a("randomCode", (String) ((LzyResponse) aVar.c()).data);
                        q.a(this.b.c()).a("mobile", obj);
                        this.b.g.start();
                    }

                    public void b(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                        super.b(aVar);
                        q.a(this.b.c()).a("randomCode", "qas");
                        this.b.send.setClickable(true);
                        this.b.b();
                    }
                });
            }
        } else if (R.id.jiaoyanmaImg != view.getId()) {
        } else {
            if (this.h) {
                this.h = false;
                h.b("再点一次切换校验码");
                return;
            }
            b();
            this.h = true;
        }
    }

    protected void onStop() {
        super.onStop();
        q.a(c()).a("mobile");
    }
}
