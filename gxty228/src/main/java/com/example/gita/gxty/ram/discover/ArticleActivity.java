package com.example.gita.gxty.ram.discover;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.weiget.TitleBar;

public class ArticleActivity extends BaseActivity {
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.titleBar, "文章阅读");
        try {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.content_frame, new ArticleFragment(), "Article");
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int a() {
        return R.layout.activity_article;
    }
}
