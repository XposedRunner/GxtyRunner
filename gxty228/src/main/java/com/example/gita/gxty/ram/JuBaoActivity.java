package com.example.gita.gxty.ram;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.BindView;
import cn.jiguang.net.HttpUtils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.JubaoData;
import com.example.gita.gxty.ram.a.b;
import com.example.gita.gxty.ram.adapt.ImageItem;
import com.example.gita.gxty.utils.j;
import com.example.gita.gxty.utils.r;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.a;
import com.lzy.okgo.request.GetRequest;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class JuBaoActivity extends BaseImgActivity {
    @BindView(2131755257)
    protected GridView camaraLayout;
    a f;
    @BindView(2131755192)
    protected TitleBar titleBar;
    @BindView(2131755255)
    protected EditText topicTxt;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "我要举报");
        this.titleBar.a(new a(this) {
            final /* synthetic */ JuBaoActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "发送";
            }

            public int b() {
                return 0;
            }

            public void a(View view) {
                this.a.t();
            }
        });
        this.f = new a(this.camaraLayout, this);
        this.f.setListener(new b(this) {
            final /* synthetic */ JuBaoActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                BaseActivity.a(this.a.topicTxt);
                this.a.d("temp");
            }
        });
        TextWatcher jVar = new j(this.topicTxt, 500);
        jVar.a(new j.a(this) {
            final /* synthetic */ JuBaoActivity a;

            {
                this.a = r1;
            }

            public void a(long j) {
                ((TextView) this.a.findViewById(R.id.showCountTxt)).setText(j + HttpUtils.PATHS_SEPARATOR + 500);
            }
        });
        this.topicTxt.addTextChangedListener(jVar);
    }

    public int r() {
        return 1;
    }

    public void a(String str, boolean z) {
        super.a(str, z);
        if (z) {
            List arrayList = new ArrayList();
            arrayList.add(c("temp"));
            this.f.a(arrayList, true);
        }
    }

    protected int a() {
        return R.layout.activity_jubao;
    }

    private void t() {
        new Thread(this) {
            final /* synthetic */ JuBaoActivity a;

            {
                this.a = r1;
            }

            public void run() {
                String obj = this.a.topicTxt.getText().toString();
                List<ImageItem> a = this.a.f.a();
                List list = null;
                if (r.a(obj) && (a == null || a.isEmpty())) {
                    this.a.a("文字和图片不能同时为空！！!");
                    return;
                }
                boolean a2;
                this.a.f();
                if (a != null && a.size() > 0) {
                    Object obj2;
                    List arrayList = new ArrayList();
                    for (ImageItem imageUrl : a) {
                        try {
                            arrayList.add(this.a.e(imageUrl.getImageUrl()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            obj2 = 1;
                        }
                    }
                    obj2 = null;
                    if (obj2 != null) {
                        this.a.a("图片上传失败");
                        this.a.g();
                        return;
                    }
                    list = arrayList;
                }
                try {
                    a2 = this.a.a(obj, list);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.a.a("请求失败");
                    a2 = false;
                }
                this.a.g();
                if (a2) {
                    this.a.finish();
                }
            }
        }.start();
    }

    private boolean a(String str, List<String> list) throws Exception {
        Object jubaoData = new JubaoData();
        jubaoData.text = str;
        jubaoData.images = list;
        DataBean a = com.example.gita.gxty.utils.b.a(jubaoData);
        GetRequest getRequest = (GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("center/doReport")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0]);
        com.example.gita.gxty.a.a.a(getRequest.getHeaders());
        JSONObject jSONObject = new JSONObject(getRequest.execute().body().string());
        a(jSONObject.getString("msg"));
        return "200".equals(jSONObject.getString("code"));
    }
}
