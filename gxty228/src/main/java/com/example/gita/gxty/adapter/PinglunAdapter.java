package com.example.gita.gxty.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.Pinglun;
import com.example.gita.gxty.ram.discover.HuoDongDetailActivity;
import com.example.gita.gxty.utils.d;
import com.example.gita.gxty.utils.s;
import java.util.ArrayList;

public class PinglunAdapter extends BaseQuickAdapter<Pinglun, BaseViewHolder> {
    private BaseActivity a;
    private int b = 0;

    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (Pinglun) obj);
    }

    public PinglunAdapter(ArrayList arrayList, BaseActivity baseActivity, int i) {
        super(R.layout.item_pinglun, arrayList);
        this.a = baseActivity;
        this.b = i;
    }

    protected void a(final BaseViewHolder baseViewHolder, final Pinglun pinglun) {
        baseViewHolder.setText((int) R.id.nickName, pinglun.user_name + "：");
        baseViewHolder.setText((int) R.id.content, s.a(pinglun.content));
        baseViewHolder.setText((int) R.id.timeTxt, d.e(pinglun.create_time));
        baseViewHolder.setText((int) R.id.zanTxt, pinglun.like_count + "");
        e.b(this.mContext).a(pinglun.user_head).b((int) R.mipmap.default_avatar).a((ImageView) baseViewHolder.getView(R.id.imageView));
        baseViewHolder.setOnClickListener(R.id.zanlayout, new OnClickListener(this) {
            final /* synthetic */ PinglunAdapter c;

            public void onClick(View view) {
                if (this.c.b == 0) {
                    BaseActivity.b(this.c.a, pinglun.comment_id, pinglun.like_count, (TextView) baseViewHolder.getView(R.id.zanTxt), (ImageView) baseViewHolder.getView(R.id.zanImg));
                } else if (this.c.b == 1) {
                    HuoDongDetailActivity.c(this.c.a, pinglun.comment_id, pinglun.like_count, (TextView) baseViewHolder.getView(R.id.zanTxt), (ImageView) baseViewHolder.getView(R.id.zanImg));
                } else if (this.c.b == 2) {
                    HuoDongDetailActivity.d(this.c.a, pinglun.comment_id, pinglun.like_count, (TextView) baseViewHolder.getView(R.id.zanTxt), (ImageView) baseViewHolder.getView(R.id.zanImg));
                }
            }
        });
    }
}
