package com.example.gita.gxty.ram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.MyMine;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.a;
import com.lzy.okgo.request.GetRequest;
import com.yuyh.library.imgsel.common.ImageLoader;

public class MyInfoActivity extends BaseActivity {
    @BindView(2131755276)
    protected ImageView avatar;
    private Bitmap f = null;
    private int g;
    @BindView(2131755307)
    protected TextView myinfo_nameText;
    @BindView(2131755305)
    protected TextView myinfo_nicknameText;
    @BindView(2131755303)
    protected TextView myinfo_renzhengText;
    @BindView(2131755319)
    protected TextView myinfo_shengaoText;
    @BindView(2131755317)
    protected TextView myinfo_tizhongText;
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
        a(this.titleBar, "编辑资料");
        this.titleBar.a(new a(this) {
            final /* synthetic */ MyInfoActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "编辑";
            }

            public int b() {
                return 0;
            }

            public void a(View view) {
                this.a.startActivity(new Intent(this.a.c(), MyInfoEditActivity.class));
            }
        });
        this.myinfo_renzhengText.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyInfoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.g == 0 || 2 == this.a.g) {
                    this.a.startActivityForResult(new Intent(this.a.c(), RenZhengActivity.class), PointerIconCompat.TYPE_CROSSHAIR);
                }
            }
        });
        com.yuyh.library.imgsel.a.a().a(new ImageLoader() {
            public void displayImage(Context context, String str, ImageView imageView) {
                e.b(context).a(str).a(imageView);
            }
        });
        p();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == PointerIconCompat.TYPE_CROSSHAIR && i2 == -1) {
            p();
        }
    }

    protected void onStart() {
        super.onStart();
        b();
    }

    private void b() {
        try {
            this.myinfo_nameText.setText((String) q.a(c()).b("username", ""));
            this.myinfo_xuexiaoText.setText((String) q.a(c()).b("school", ""));
            this.myinfo_yuanxiText.setText((String) q.a(c()).b("departName8", ""));
            this.myinfo_xuehaoText.setText((String) q.a(c()).b("stStuNumber", ""));
            this.myinfo_shengaoText.setText(((String) q.a(c()).b("height", "")) + IXAdRequestInfo.MAX_CONTENT_LENGTH);
            this.myinfo_tizhongText.setText(((String) q.a(c()).b("weight", "")) + "kg");
            this.myinfo_nicknameText.setText((String) q.a(c()).b("nickname", ""));
            this.g = ((Integer) q.a(c()).b("certificationStatus", Integer.valueOf(0))).intValue();
            if (1 == this.g) {
                this.myinfo_renzhengText.setText("实名认证中");
            } else if (2 == this.g) {
                this.myinfo_renzhengText.setText("实名认证失败");
            } else if (3 == this.g) {
                this.myinfo_renzhengText.setText("实名认证成功");
            } else {
                this.myinfo_renzhengText.setText("去实名认证");
            }
            if ("1".equals((String) q.a(c()).b("sex", ""))) {
                this.myinfo_xingbieText.setText("男");
            } else {
                this.myinfo_xingbieText.setText("女");
            }
            o();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void o() {
        try {
            e.a((FragmentActivity) this).a(q.a(c()).b("photo", "")).a(this.avatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_myinfo;
    }

    private void p() {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("student/getUserDetail")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<MyMine>>(this, this) {
            final /* synthetic */ MyInfoActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<MyMine>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    MyMine myMine = (MyMine) ((LzyResponse) aVar.c()).data;
                    q.a(this.a.c()).a("userid", myMine.base.user_id);
                    q.a(this.a.c()).a("sex", myMine.base.sex);
                    q.a(this.a.c()).a("username", myMine.base.userName);
                    q.a(this.a.c()).a("nickname", myMine.base.nickname);
                    q.a(this.a.c()).a("photo", myMine.base.stPhoto);
                    q.a(this.a.c()).a("stStuNumber", myMine.base.stStuNumber);
                    q.a(this.a.c()).a("certificationStatus", Integer.valueOf(myMine.base.certificationStatus));
                    q.a(this.a.c()).a("school", myMine.school.scName);
                    q.a(this.a.c()).a("schoolId", myMine.school.scId);
                    q.a(this.a.c()).a("departId8", myMine.department.id);
                    q.a(this.a.c()).a("departName8", myMine.department.name);
                    q.a(this.a.c()).a("height", myMine.base.height);
                    q.a(this.a.c()).a("weight", myMine.base.weight);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.a.b();
            }
        });
    }
}
