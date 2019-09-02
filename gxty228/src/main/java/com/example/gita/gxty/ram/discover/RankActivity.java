package com.example.gita.gxty.ram.discover;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.design.widget.TabLayout.ViewPagerOnTabSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.weiget.TitleBar;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import java.util.HashMap;
import java.util.Map;

public class RankActivity extends BaseActivity {
    public static final Map<String, String> k = new HashMap();
    ViewPager f;
    RankFragment g;
    RankFragment h;
    RankFragment i;
    PagerAdapter j;
    private TabLayout l;
    private View m;
    private String n = "2";
    private boolean o = false;
    @BindView(2131755192)
    protected TitleBar titleBar;

    public class PagerAdapter extends FragmentPagerAdapter {
        final /* synthetic */ RankActivity a;

        public PagerAdapter(RankActivity rankActivity, FragmentManager fragmentManager) {
            this.a = rankActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            if (i == 0) {
                return this.a.g;
            }
            if (i == 1) {
                return this.a.h;
            }
            if (i == 2) {
                return this.a.i;
            }
            return null;
        }

        public int getCount() {
            return 3;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(bundle);
        a("本校榜", false);
    }

    public void a(String str, boolean z) {
        if (this.titleBar != null) {
            if (this.m == null) {
                this.m = LayoutInflater.from(this).inflate(R.layout.rank_tbar, null);
                this.titleBar.setLeftImageResource(R.drawable.back_bg);
                this.titleBar.setCustomTitle(this.m);
                this.titleBar.setBackgroundColor(getResources().getColor(R.color.black_zhong));
                this.titleBar.setLeftClickListener(new OnClickListener(this) {
                    final /* synthetic */ RankActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.finish();
                    }
                });
                this.titleBar.setActionTextColor(Color.parseColor("#4bd9ba"));
                this.m.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ RankActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.o();
                    }
                });
            }
            TextView textView = (TextView) this.m.findViewById(R.id.rank_title_txt);
            ImageView imageView = (ImageView) this.m.findViewById(R.id.rank_title_img);
            if (str != null) {
                textView.setText(str);
            }
            if (z) {
                imageView.setImageResource(R.mipmap.rank_up);
            } else {
                imageView.setImageResource(R.mipmap.rank_down);
            }
        }
    }

    private void o() {
        if (!this.o) {
            this.o = true;
            a(null, true);
            new AlertView("排行榜", null, "取消", null, new String[]{"本校榜", "本院榜", "全国榜"}, this, Style.ActionSheet, new c(this) {
                final /* synthetic */ RankActivity a;

                {
                    this.a = r1;
                }

                public void a(Object obj, int i) {
                    try {
                        ((AlertView) obj).h();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.a.o = false;
                    if (i == 0) {
                        this.a.n = "2";
                        this.a.a("本校榜", false);
                        this.a.p();
                    } else if (i == 1) {
                        this.a.n = "3";
                        this.a.a("本院榜", false);
                        this.a.p();
                    } else if (i == 2) {
                        this.a.n = "1";
                        this.a.a("全国榜", false);
                        this.a.p();
                    } else {
                        this.a.a(null, false);
                    }
                }
            }).e();
        }
    }

    private void p() {
        try {
            this.g.b();
            this.h.b();
            this.i.b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_rank;
    }

    private void a(Bundle bundle) {
        this.f = (ViewPager) findViewById(R.id.view_pager);
        this.f.setOffscreenPageLimit(2);
        this.l = (TabLayout) findViewById(R.id.toolbar_tab);
        this.g = RankFragment.b("day");
        this.h = RankFragment.b("week");
        this.i = RankFragment.b("month");
        this.j = new PagerAdapter(this, getSupportFragmentManager());
        this.f.setAdapter(this.j);
        this.f.addOnPageChangeListener(new TabLayoutOnPageChangeListener(this.l));
        this.l.addOnTabSelectedListener(new ViewPagerOnTabSelectedListener(this.f));
    }

    public String b() {
        return this.n;
    }
}
