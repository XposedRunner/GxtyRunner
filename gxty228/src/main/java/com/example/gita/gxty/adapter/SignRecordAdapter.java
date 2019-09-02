package com.example.gita.gxty.adapter;

import android.support.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataSignRecord;
import java.util.ArrayList;
import java.util.List;

public class SignRecordAdapter extends BaseQuickAdapter<DataSignRecord, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (DataSignRecord) obj);
    }

    public SignRecordAdapter(ArrayList arrayList) {
        super(R.layout.item_sign_record, arrayList);
    }

    @NonNull
    public List<DataSignRecord> getData() {
        return super.getData();
    }

    protected void a(BaseViewHolder baseViewHolder, DataSignRecord dataSignRecord) {
        baseViewHolder.setText((int) R.id.sign_nameTxt, dataSignRecord.name);
        baseViewHolder.setText((int) R.id.sign_timeTxt, dataSignRecord.checkin_time);
        baseViewHolder.setText((int) R.id.sign_descTxt, dataSignRecord.right_desc);
        baseViewHolder.setText((int) R.id.sign_statusTxt, dataSignRecord.desc);
        if ("2".equals(dataSignRecord.status)) {
            baseViewHolder.setVisible(R.id.sign_actionBtn, true);
            baseViewHolder.setTextColor(R.id.sign_statusTxt, this.mContext.getResources().getColor(R.color.run_status_fail));
            return;
        }
        baseViewHolder.setVisible(R.id.sign_actionBtn, false);
        baseViewHolder.setTextColor(R.id.sign_statusTxt, this.mContext.getResources().getColor(R.color.run_status_success));
    }
}
