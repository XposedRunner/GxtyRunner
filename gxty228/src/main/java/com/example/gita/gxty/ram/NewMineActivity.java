package com.example.gita.gxty.ram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.activity.CourseActivity;
import com.example.gita.gxty.activity.SetActivity;
import com.example.gita.gxty.activity.SignatureActivity;
import com.example.gita.gxty.ram.medal.MedalActivity;
import com.example.gita.gxty.utils.i;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.utils.s;
import com.yuyh.library.imgsel.a;
import com.yuyh.library.imgsel.common.ImageLoader;

public class NewMineActivity extends BaseActivity {
    @BindView(2131755276)
    protected ImageView avatar;
    @BindView(2131755333)
    protected TextView dataBtn;
    private long f = 0;
    @BindView(2131755322)
    protected TextView nameTxt;
    @BindView(2131755324)
    protected TextView shuoshuoText;
    @BindView(2131755323)
    protected TextView xuexiaoText;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.a().a(new ImageLoader() {
            public void displayImage(Context context, String str, ImageView imageView) {
                e.b(context).a(str).a(imageView);
            }
        });
        this.dataBtn.setVisibility(8);
        i.b(c());
        j();
        a((RelativeLayout) findViewById(R.id.baiduAd));
    }

    protected void onResume() {
        super.onResume();
        this.nameTxt.setText((String) q.a(c()).b("username", ""));
        this.xuexiaoText.setText((String) q.a(c()).b("school", ""));
        String str = (String) q.a(c()).b("signature", "");
        if (r.a(str)) {
            this.shuoshuoText.setText("这家伙很懒，什么都没留下。");
        } else {
            this.shuoshuoText.setText(str);
        }
        b();
    }

    private void b() {
        e.a((FragmentActivity) this).a(q.a(c()).b("photo", "")).a(this.avatar);
    }

    protected int a() {
        return R.layout.activity_newmine;
    }

    @OnClick({2131755325, 2131755326, 2131755327, 2131755328, 2131755329, 2131755330, 2131755331, 2131755332, 2131755320, 2131755333, 2131755324})
    void butterknifeOnItemClick(View view) {
        switch (view.getId()) {
            case R.id.topLayout /*2131755320*/:
                startActivity(new Intent(c(), MyInfoActivity.class));
                return;
            case R.id.shuoshuoText /*2131755324*/:
                startActivity(new Intent(c(), SignatureActivity.class));
                return;
            case R.id.chengjiLayout /*2131755325*/:
                startActivity(new Intent(c(), MedalActivity.class));
                return;
            case R.id.kechengLayout /*2131755326*/:
                startActivity(new Intent(c(), CourseActivity.class));
                return;
            case R.id.shetuanLayout /*2131755327*/:
                startActivity(new Intent(c(), MyXieHuiActivity.class));
                return;
            case R.id.duanlianjiluBtn /*2131755328*/:
                startActivity(new Intent(c(), SportRecordActivity.class));
                return;
            case R.id.shezhiBtn /*2131755332*/:
                startActivity(new Intent(c(), SetActivity.class));
                return;
            case R.id.dataBtn /*2131755333*/:
                startActivity(new Intent(c(), DataActivity.class));
                return;
            default:
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.f > 2000) {
            s.a((CharSequence) "再按一次退出程序");
            this.f = System.currentTimeMillis();
        } else {
            k();
        }
        return true;
    }
}
