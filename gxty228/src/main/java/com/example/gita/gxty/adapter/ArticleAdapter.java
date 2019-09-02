package com.example.gita.gxty.adapter;

import android.widget.ImageView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.HuoDong;
import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<HuoDong, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (HuoDong) obj);
    }

    public ArticleAdapter(List list) {
        super(R.layout.item_article, list);
    }

    protected void a(BaseViewHolder baseViewHolder, HuoDong huoDong) {
        baseViewHolder.setText((int) R.id.titleTxt, huoDong.title);
        baseViewHolder.setText((int) R.id.typeTxt, huoDong.tag);
        baseViewHolder.setText((int) R.id.sourceTxt, huoDong.author);
        baseViewHolder.setText((int) R.id.timeTxt, huoDong.date);
        e.b(this.mContext).a(huoDong.img).b((int) R.mipmap.default_banner).a((ImageView) baseViewHolder.getView(R.id.imageView));
    }
}
