package com.example.gita.gxty.adapter;

import android.graphics.Color;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.Record;
import com.example.gita.gxty.ram.RunRecordActivity;
import com.example.gita.gxty.utils.r;
import java.util.List;

public class RecordAdapter extends BaseQuickAdapter<Record, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (Record) obj);
    }

    public RecordAdapter(List list) {
        super(R.layout.item_record, list);
    }

    protected void a(BaseViewHolder baseViewHolder, Record record) {
        baseViewHolder.setText((int) R.id.date, record.date);
        baseViewHolder.setText(2131755339, record.time);
        baseViewHolder.setText((int) R.id.duration, record.duration);
        baseViewHolder.setText((int) R.id.length, record.length);
        baseViewHolder.setText((int) R.id.spead, record.speed);
        baseViewHolder.setText(2131755337, a(record.status));
        if (record.status.equals("1")) {
            baseViewHolder.setImageDrawable(R.id.status_circle, this.mContext.getResources().getDrawable(R.drawable.circle_green));
            baseViewHolder.setImageDrawable(R.id.status_line, this.mContext.getResources().getDrawable(R.mipmap.liststate1));
        } else if (record.status.equals("2")) {
            baseViewHolder.setImageDrawable(R.id.status_circle, this.mContext.getResources().getDrawable(R.drawable.circle_red));
            baseViewHolder.setImageDrawable(R.id.status_line, this.mContext.getResources().getDrawable(R.mipmap.liststate3));
        } else if (record.status.equals("3")) {
            baseViewHolder.setImageDrawable(R.id.status_circle, this.mContext.getResources().getDrawable(R.drawable.circle_blue));
            baseViewHolder.setImageDrawable(R.id.status_line, this.mContext.getResources().getDrawable(R.mipmap.liststate2));
        } else if (record.status.equals("4")) {
            baseViewHolder.setImageDrawable(R.id.status_circle, this.mContext.getResources().getDrawable(R.drawable.circle_blue));
            baseViewHolder.setImageDrawable(R.id.status_line, this.mContext.getResources().getDrawable(R.mipmap.liststate2));
        } else {
            baseViewHolder.setImageDrawable(R.id.status_circle, this.mContext.getResources().getDrawable(R.drawable.circle_green));
            baseViewHolder.setImageDrawable(R.id.status_line, this.mContext.getResources().getDrawable(R.mipmap.liststate1));
        }
        if (baseViewHolder.getLayoutPosition() % 2 == 0) {
            baseViewHolder.setBackgroundColor(R.id.card_view, Color.parseColor("#0fffffff"));
        } else {
            baseViewHolder.setBackgroundColor(R.id.card_view, Color.parseColor("#0f333236"));
        }
    }

    private String a(String str) {
        String str2 = (String) RunRecordActivity.g.get(str);
        if (r.a(str2)) {
            return "";
        }
        return str2;
    }
}
