package com.example.gita.gxty.ram;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.adapter.SignAdapter;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataSign;
import com.example.gita.gxty.model.DataXieHuiBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.RunID;
import com.example.gita.gxty.model.SignListData;
import com.example.gita.gxty.ram.service.SignService;
import com.example.gita.gxty.utils.k;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.TitleBar.a;
import com.example.gita.gxty.weiget.d;
import com.lzy.okgo.b.b;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import java.util.ArrayList;

public class SignListActivity extends BaseActivity {
    ArrayList<DataSign> f = new ArrayList();
    private SignAdapter g;
    private int h = 1;
    private String i;
    @BindView(2131755210)
    protected RecyclerView mRecyclerView;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = getIntent().getStringExtra("ibeancons");
        this.h = getIntent().getIntExtra("signType", 1);
        a(this.titleBar, "签到列表");
        this.titleBar.a(new a(this) {
            final /* synthetic */ SignListActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "签到记录";
            }

            public int b() {
                return 0;
            }

            public void a(View view) {
                this.a.startActivity(new Intent(this.a.c(), SignRecordListActivity.class));
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        b();
        o();
        j();
    }

    private void b() {
        this.g = new SignAdapter(this.f);
        this.mRecyclerView.setAdapter(this.g);
        this.g.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SignListActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DataSign dataSign = (DataSign) this.a.f.get(i);
                if (!"1".equals(dataSign.isValidSignStatus)) {
                    return;
                }
                if (!"2".equals(dataSign.type)) {
                    this.a.a((DataSign) this.a.f.get(i), null);
                } else if (dataSign.beacon == null || dataSign.beacon.isEmpty()) {
                    this.a.a("设备配置错误，请联系管理员!");
                } else if (n.a(this.a.c()).a(3)) {
                    this.a.a(dataSign);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", dataSign);
                    this.a.a((int) PointerIconCompat.TYPE_ALIAS, bundle);
                }
            }
        });
    }

    private void a(final DataSign dataSign, final String str) {
        k.a((Context) this);
        if (k.a()) {
            f();
            new Thread(this) {
                final /* synthetic */ SignListActivity c;

                public void run() {
                    b anonymousClass1 = new com.example.gita.gxty.a.a<LzyResponse<RunID>>(this, this.c.c(), false) {
                        final /* synthetic */ AnonymousClass3 a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<RunID>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            this.a.c.g();
                            Bundle bundle = new Bundle();
                            bundle.putString("data", ((LzyResponse) aVar.c()).msg);
                            this.a.c.a(1011, bundle);
                        }

                        public void b(com.lzy.okgo.model.a<LzyResponse<RunID>> aVar) {
                            super.b(aVar);
                            this.a.c.g();
                            if (!this.a.c.isFinishing()) {
                                s.a(aVar.d().getMessage());
                            }
                            this.a.c.finish();
                        }
                    };
                    Object dataXieHuiBean = new DataXieHuiBean();
                    dataXieHuiBean.ass_id = dataSign.ass_id;
                    dataXieHuiBean.nonce = dataSign.nonce;
                    dataXieHuiBean.extra = str;
                    DataBean a = com.example.gita.gxty.utils.b.a(dataXieHuiBean);
                    ((PostRequest) ((PostRequest) ((PostRequest) com.lzy.okgo.a.b(com.example.gita.gxty.a.a("association/doSign")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(anonymousClass1);
                }
            }.start();
            return;
        }
        a("网络已断开，请联网后重试！");
    }

    private void o() {
        Object signListData = new SignListData();
        signListData.type = this.h + "";
        signListData.ibeacons = this.i;
        DataBean a = com.example.gita.gxty.utils.b.a(signListData);
        String str = (String) q.a(c()).b("schoolId", "0");
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(String.format(com.example.gita.gxty.a.a("association/dataListByDevice/%1$s"), new Object[]{str})).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<ArrayList<DataSign>>>(this, this) {
            final /* synthetic */ SignListActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<ArrayList<DataSign>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                if (!this.a.isFinishing()) {
                    this.a.f = (ArrayList) ((LzyResponse) aVar.c()).data;
                    this.a.g.addData(this.a.f);
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_sign_list;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10010 && i2 == -1) {
            a((DataSign) intent.getSerializableExtra("data"), intent.getStringExtra("result"));
        } else {
            finish();
        }
    }

    @Nullable
    protected Dialog onCreateDialog(int i, final Bundle bundle) {
        Dialog bVar;
        if (i == 1011) {
            bVar = new com.example.gita.gxty.weiget.b(this, "提醒", bundle.getString("data"), "确定", "取消");
            bVar.a().setVisibility(8);
            bVar.setListener(new com.example.gita.gxty.weiget.b.a(this) {
                final /* synthetic */ SignListActivity a;

                {
                    this.a = r1;
                }

                public boolean a(com.example.gita.gxty.weiget.b bVar, int i) {
                    if (i == 1) {
                        this.a.finish();
                    }
                    return false;
                }
            });
            return bVar;
        } else if (i != PointerIconCompat.TYPE_ALIAS) {
            return super.onCreateDialog(i, bundle);
        } else {
            bVar = new d(this, true, 3);
            bVar.setListener(new d.a(this) {
                final /* synthetic */ SignListActivity b;

                public boolean a(d dVar) {
                    if (bundle != null) {
                        this.b.a((DataSign) bundle.getSerializable("data"));
                    }
                    return false;
                }
            });
            return bVar;
        }
    }

    private void a(DataSign dataSign) {
        try {
            SignService.a(c(), dataSign);
            Intent intent = new Intent(c(), SignChangDiActivity.class);
            intent.putExtra("data", dataSign);
            startActivityForResult(intent, 10010);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
