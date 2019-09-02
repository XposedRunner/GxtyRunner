package com.example.gita.gxty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.ForgetPwdModify;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;

public class ForgetPwd2Activity extends BaseActivity {
    private String f = null;
    @BindView(2131755244)
    protected EditText pwd1;
    @BindView(2131755245)
    protected EditText pwd2;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = getIntent().getStringExtra("ftoken");
        this.titleBar.setLeftImageResource(R.drawable.back_bg);
        this.titleBar.setTitle((CharSequence) "");
        this.titleBar.setBackgroundColor(getResources().getColor(R.color.black_shen));
        this.titleBar.setTitleSize(17.0f);
        this.titleBar.setTitleColor(getResources().getColor(R.color.titleColor8));
        this.titleBar.setActionTextColor(Color.parseColor("#999999"));
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ ForgetPwd2Activity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(c(), LoginActivity.class));
        finish();
    }

    protected int a() {
        return R.layout.activity_forgetpwd2;
    }

    @OnClick({2131755243})
    void butterknifeOnItemClick(View view) {
        String obj = this.pwd1.getText().toString();
        CharSequence obj2 = this.pwd2.getText().toString();
        switch (view.getId()) {
            case R.id.next /*2131755243*/:
                if (TextUtils.isEmpty(obj)) {
                    s.a((CharSequence) "新密码不能为空");
                    return;
                } else if (TextUtils.isEmpty(obj2)) {
                    s.a((CharSequence) "确认密码不能为空");
                    return;
                } else if (obj.equals(obj2)) {
                    Object forgetPwdModify = new ForgetPwdModify();
                    forgetPwdModify.pwd = obj;
                    forgetPwdModify.rtoken = this.f;
                    DataBean a = b.a(forgetPwdModify);
                    ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("reg/reset_pwd")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
                        final /* synthetic */ ForgetPwd2Activity a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            if (!this.a.isFinishing()) {
                                s.a(((LzyResponse) aVar.c()).msg);
                            }
                            this.a.startActivity(new Intent(this.a.c(), LoginActivity.class));
                            this.a.finish();
                        }
                    });
                    return;
                } else {
                    s.a((CharSequence) "密码不一致");
                    return;
                }
            default:
                return;
        }
    }
}
