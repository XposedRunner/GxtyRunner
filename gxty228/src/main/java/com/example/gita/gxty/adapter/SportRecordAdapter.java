package com.example.gita.gxty.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataSportRecord;
import java.util.ArrayList;

public class SportRecordAdapter extends BaseQuickAdapter<DataSportRecord, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (DataSportRecord) obj);
    }

    public SportRecordAdapter(ArrayList arrayList) {
        super(R.layout.item_sport_record, arrayList);
    }

    protected void a(BaseViewHolder baseViewHolder, DataSportRecord dataSportRecord) {
        baseViewHolder.setText((int) R.id.myinfo_titleTxt, dataSportRecord.title);
        baseViewHolder.setText((int) R.id.myinfo_countTxt, dataSportRecord.count + "次");
    }
}
