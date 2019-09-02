package com.example.gita.gxty.ram;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataXHAdd;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.TXieHui;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;

public class DeleteXieHuiActivity extends BaseActivity {
    TXieHui f;
    @BindView(2131755467)
    protected TextView startTime;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = (TXieHui) getIntent().getSerializableExtra("xiehui");
        if (this.f == null) {
            h.b("--xieHui--is null--");
            finish();
            return;
        }
        a(this.titleBar, this.f.name);
        this.startTime.setText(this.f.add_time);
    }

    @OnClick({2131755236})
    void butterknifeOnItemClick(View view) {
        if (R.id.actionBtn == view.getId()) {
            Object dataXHAdd = new DataXHAdd();
            dataXHAdd.ass_id = this.f.ass_id;
            DataBean a = b.a(dataXHAdd);
            ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("association/doUnRexDataByUser")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this) {
                final /* synthetic */ DeleteXieHuiActivity a;

                public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                    super.a((com.lzy.okgo.model.a) aVar);
                    this.a.a(((LzyResponse) aVar.c()).msg);
                    this.a.finish();
                }
            });
        }
    }

    protected int a() {
        return R.layout.activity_xiehui_delete;
    }
}
