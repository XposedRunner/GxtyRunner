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
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.weiget.TitleBar;

public class SearchActivity extends BaseActivity {
    ViewPager f;
    EditText g;
    SearchFeedFragment h;
    ArticleFragment i;
    HuoDongFragment j;
    PagerAdapter k;
    private TabLayout l;
    @BindView(2131755192)
    protected TitleBar titleBar;

    public class PagerAdapter extends FragmentPagerAdapter {
        final /* synthetic */ SearchActivity a;

        public PagerAdapter(SearchActivity searchActivity, FragmentManager fragmentManager) {
            this.a = searchActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            if (i == 0) {
                return this.a.h;
            }
            if (i == 1) {
                return this.a.i;
            }
            if (i == 2) {
                return this.a.j;
            }
            return null;
        }

        public int getCount() {
            return 3;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "搜索结果");
        this.g = new EditText(this);
        this.g.setLines(1);
        this.g.setSingleLine();
        this.g.setPadding(0, 0, 0, 0);
        this.g.setBackgroundResource(R.drawable.ba_biankuang_search);
        this.g.setTextSize(10.0f);
        this.g.setHint("输入搜索关键字");
        this.g.setTextColor(Color.parseColor("#ffffff"));
        this.g.setHintTextColor(Color.parseColor("#999999"));
        this.titleBar.setCustomTitle(this.g);
        LayoutParams layoutParams = (LayoutParams) this.g.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.search_box_hight);
        this.g.setLayoutParams(layoutParams);
        this.g.setPadding(10, 0, 0, 0);
        this.g.setImeOptions(3);
        this.g.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ SearchActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 3) {
                    try {
                        ((SearchBaseFragment) this.a.k.getItem(this.a.l.getSelectedTabPosition())).b(this.a.g.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
        a(bundle);
    }

    public String b() {
        return this.g.getText().toString();
    }

    protected int a() {
        return R.layout.activity_search;
    }

    private void a(Bundle bundle) {
        this.f = (ViewPager) findViewById(R.id.view_pager);
        this.f.setOffscreenPageLimit(2);
        this.l = (TabLayout) findViewById(R.id.toolbar_tab);
        if (bundle == null) {
            this.h = new SearchFeedFragment();
            this.i = new ArticleFragment();
            this.j = new HuoDongFragment();
        }
        this.k = new PagerAdapter(this, getSupportFragmentManager());
        this.f.setAdapter(this.k);
        this.f.addOnPageChangeListener(new TabLayoutOnPageChangeListener(this.l));
        this.l.addOnTabSelectedListener(new ViewPagerOnTabSelectedListener(this.f));
    }
}
