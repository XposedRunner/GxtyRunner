package com.example.gita.gxty.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataSportTerm;
import java.util.ArrayList;

public class SportTermAdapter extends BaseQuickAdapter<DataSportTerm, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (DataSportTerm) obj);
    }

    public SportTermAdapter(ArrayList arrayList) {
        super(R.layout.item_sport_term, arrayList);
    }

    protected void a(BaseViewHolder baseViewHolder, DataSportTerm dataSportTerm) {
        baseViewHolder.setText((int) R.id.myinfo_titleTxt, dataSportTerm.title);
        baseViewHolder.setText((int) R.id.myinfo_countTxt, dataSportTerm.count + "次");
    }
}
