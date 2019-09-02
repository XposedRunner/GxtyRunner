package com.example.gita.gxty.ram.medal;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.EasyRefreshLayout.EasyEvent;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.MedalData;
import com.example.gita.gxty.model.MedalGroup;
import com.example.gita.gxty.model.MedalTemp;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.request.GetRequest;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class MedalActivity extends BaseActivity {
    @BindView(2131755234)
    protected EasyRefreshLayout easylayout;
    OnItemClickListener f = new OnItemClickListener(this) {
        final /* synthetic */ MedalActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                MedalDetailActivity.a(this.a.c(), (MedalData) adapterView.getAdapter().getItem(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @BindView(2131755268)
    protected MyGridView gv1;
    @BindView(2131755269)
    protected MyGridView gv2;
    @BindView(2131755270)
    protected MyGridView gv3;
    @BindView(2131755272)
    protected MyGridView gv4;
    @BindView(2131755274)
    protected MyGridView gv5;
    @BindView(2131755195)
    protected TextView text1;
    @BindView(2131755032)
    protected TextView text2;
    @BindView(2131755265)
    protected TextView text3;
    @BindView(2131755271)
    protected TextView text4;
    @BindView(2131755273)
    protected TextView text5;
    @BindView(2131755192)
    protected TitleBar titleBar;
    @BindView(2131755266)
    protected ImageView topImg;
    @BindView(2131755267)
    protected TextView topText;

    class a extends BaseAdapter {
        List<MedalData> a;
        final /* synthetic */ MedalActivity b;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(MedalActivity medalActivity, List<MedalData> list) {
            this.b = medalActivity;
            if (list == null) {
                Log.e("889977", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
                this.a = new ArrayList();
                return;
            }
            Log.e("889977", "9-" + list.size());
            this.a = list;
        }

        public int getCount() {
            return this.a.size();
        }

        public MedalData a(int i) {
            return (MedalData) this.a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.b.c()).inflate(R.layout.item_metal, null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.disImg);
            TextView textView = (TextView) view.findViewById(R.id.disTxt);
            MedalData a = a(i);
            if ("1".equals(a.is_have)) {
                e.a(this.b.c()).a(a.medal_icon).b((int) R.mipmap.default_huati).a(imageView);
            } else {
                e.a(this.b.c()).a(a.medal_icon_n).b((int) R.mipmap.default_huati).a(imageView);
            }
            textView.setText(a.medal_name);
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "勋章");
        this.titleBar.a(new com.example.gita.gxty.weiget.TitleBar.a(this) {
            final /* synthetic */ MedalActivity a;

            {
                this.a = r1;
            }

            public String a() {
                return "";
            }

            public int b() {
                return R.mipmap.share;
            }

            public void a(View view) {
                this.a.c(1);
            }
        });
        this.gv1.setVisibility(8);
        this.gv2.setVisibility(8);
        this.gv3.setVisibility(8);
        this.gv4.setVisibility(8);
        this.gv5.setVisibility(8);
        this.gv1.setOnItemClickListener(this.f);
        this.gv2.setOnItemClickListener(this.f);
        this.gv3.setOnItemClickListener(this.f);
        this.gv4.setOnItemClickListener(this.f);
        this.gv5.setOnItemClickListener(this.f);
        this.text1.setVisibility(8);
        this.text2.setVisibility(8);
        this.text3.setVisibility(8);
        this.text4.setVisibility(8);
        this.text5.setVisibility(8);
        this.easylayout.addEasyEvent(new EasyEvent(this) {
            final /* synthetic */ MedalActivity a;

            {
                this.a = r1;
            }

            public void onLoadMore() {
            }

            public void onRefreshing() {
                this.a.b();
            }
        });
        e.a((FragmentActivity) this).a(q.a(c()).b("photo", "")).a(this.topImg);
        b();
    }

    protected int a() {
        return R.layout.activity_metal;
    }

    private void b() {
        DataBean a = b.a(null);
        ((GetRequest) ((GetRequest) ((GetRequest) com.lzy.okgo.a.a(com.example.gita.gxty.a.a("medal/myMedalList")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<MedalTemp>>(this, this, false) {
            final /* synthetic */ MedalActivity a;

            public void a(com.lzy.okgo.model.a<LzyResponse<MedalTemp>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                try {
                    Log.e("889977", "1");
                    this.a.easylayout.refreshComplete();
                    if (this.a.isFinishing()) {
                        Log.e("889977", "3");
                        return;
                    }
                    Log.e("889977", "2");
                    MedalTemp medalTemp = (MedalTemp) ((LzyResponse) aVar.c()).data;
                    if (medalTemp != null) {
                        Log.e("889977", "4");
                        this.a.topText.setText(medalTemp.myTotal);
                        if (medalTemp.group != null) {
                            int i = 1;
                            for (MedalGroup medalGroup : medalTemp.group) {
                                if (i == 1) {
                                    Log.e("889977", Constants.VIA_SHARE_TYPE_INFO);
                                    this.a.text1.setVisibility(0);
                                    this.a.text1.setText(medalGroup.display);
                                    this.a.gv1.setVisibility(0);
                                    this.a.gv1.setAdapter(new a(this.a, medalGroup.child));
                                } else if (i == 2) {
                                    Log.e("889977", Constants.VIA_SHARE_TYPE_PUBLISHMOOD);
                                    this.a.text2.setVisibility(0);
                                    this.a.text2.setText(medalGroup.display);
                                    this.a.gv2.setVisibility(0);
                                    this.a.gv2.setAdapter(new a(this.a, medalGroup.child));
                                } else if (i == 3) {
                                    this.a.text3.setVisibility(0);
                                    this.a.text3.setText(medalGroup.display);
                                    this.a.gv3.setVisibility(0);
                                    this.a.gv3.setAdapter(new a(this.a, medalGroup.child));
                                } else if (i == 4) {
                                    this.a.text4.setVisibility(0);
                                    this.a.text4.setText(medalGroup.display);
                                    this.a.gv4.setVisibility(0);
                                    this.a.gv4.setAdapter(new a(this.a, medalGroup.child));
                                } else if (i == 5) {
                                    this.a.text5.setVisibility(0);
                                    this.a.text5.setText(medalGroup.display);
                                    this.a.gv5.setVisibility(0);
                                    this.a.gv5.setAdapter(new a(this.a, medalGroup.child));
                                }
                                i++;
                            }
                            return;
                        }
                        return;
                    }
                    Log.e("889977", "5");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("889977", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
                }
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<MedalTemp>> aVar) {
                super.b(aVar);
                this.a.easylayout.refreshComplete();
            }
        });
    }
}
