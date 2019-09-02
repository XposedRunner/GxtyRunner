package com.example.gita.gxty.adapter;

import android.widget.ImageView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.Rank;
import java.util.ArrayList;

public class RankAdapter extends BaseQuickAdapter<Rank, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (Rank) obj);
    }

    public RankAdapter(ArrayList arrayList) {
        super(R.layout.item_rank, arrayList);
    }

    protected void a(BaseViewHolder baseViewHolder, Rank rank) {
        baseViewHolder.setText((int) R.id.no, rank.noV2);
        baseViewHolder.setText((int) R.id.name, rank.name);
        baseViewHolder.setText((int) R.id.signature, rank.signature);
        baseViewHolder.setText((int) R.id.length, rank.length);
        e.b(this.mContext).a(rank.photo).a((ImageView) baseViewHolder.getView(R.id.avatar));
    }
}
