package com.example.gita.gxty.adapter;

import android.widget.ImageView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.HuoDong;
import java.util.List;

public class HuoDongAdapter extends BaseQuickAdapter<HuoDong, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (HuoDong) obj);
    }

    public HuoDongAdapter(List list) {
        super(R.layout.item_huodong, list);
    }

    protected void a(BaseViewHolder baseViewHolder, HuoDong huoDong) {
        e.b(this.mContext).a(huoDong.img).b((int) R.mipmap.default_banner).a((ImageView) baseViewHolder.getView(R.id.imageView));
        baseViewHolder.setText((int) R.id.readTimesTxt, huoDong.view_count);
    }
}
