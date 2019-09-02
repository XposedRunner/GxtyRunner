package com.example.gita.gxty.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataModify;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.UserResult;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;

public class ModifyPwdActivity extends BaseActivity {
    @BindView(2131755300)
    protected EditText newPwd;
    @BindView(2131755301)
    protected EditText newPwd2;
    @BindView(2131755299)
    protected EditText oldPwdEdt;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "");
    }

    protected int a() {
        return R.layout.activity_modifypwd;
    }

    @OnClick({2131755302})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.modifyBtn /*2131755302*/:
                Object obj = this.oldPwdEdt.getText().toString();
                String obj2 = this.newPwd.getText().toString();
                String obj3 = this.newPwd2.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    s.a((CharSequence) "旧密码不能为空");
                    return;
                } else if (TextUtils.isEmpty(obj2)) {
                    s.a((CharSequence) "新密码不能为空");
                    return;
                } else if (obj2.equals(obj3)) {
                    Object dataModify = new DataModify();
                    dataModify.password = obj;
                    dataModify.newPassword = obj2;
                    dataModify.userid = q.a(c()).b();
                    DataBean a = b.a(dataModify);
                    ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("reg/modify_pwd")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<UserResult>>(this, this) {
                        final /* synthetic */ ModifyPwdActivity a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<UserResult>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            UserResult userResult = (UserResult) ((LzyResponse) aVar.c()).data;
                            this.a.a("修改成功");
                            this.a.finish();
                        }
                    });
                    return;
                } else {
                    s.a((CharSequence) "两次新密码不一致");
                    return;
                }
            default:
                return;
        }
    }
}
