package com.example.gita.gxty.adapter;

import android.graphics.Color;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataSign;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.ArrayList;

public class SignAdapter extends BaseQuickAdapter<DataSign, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (DataSign) obj);
    }

    public SignAdapter(ArrayList arrayList) {
        super(R.layout.item_sign, arrayList);
    }

    protected void a(BaseViewHolder baseViewHolder, DataSign dataSign) {
        baseViewHolder.setText((int) R.id.sign_nameTxt, dataSign.name);
        baseViewHolder.setText((int) R.id.sign_descTxt1, dataSign.add_time);
        baseViewHolder.setText((int) R.id.sign_descTxt2, dataSign.right_desc);
        if ("1".equals(dataSign.isValidSignStatus)) {
            baseViewHolder.setText((int) R.id.sign_actionBtn, (CharSequence) "签到");
            baseViewHolder.setTextColor(R.id.sign_actionBtn, Color.parseColor("#4bd9ba"));
        } else if ("0".equals(dataSign.isValidSignStatus)) {
            baseViewHolder.setText((int) R.id.sign_actionBtn, (CharSequence) "签到");
            baseViewHolder.setTextColor(R.id.sign_actionBtn, Color.parseColor("#999999"));
        } else if ("2".equals(dataSign.isValidSignStatus)) {
            baseViewHolder.setText((int) R.id.sign_actionBtn, (CharSequence) "已签到");
            baseViewHolder.setTextColor(R.id.sign_actionBtn, Color.parseColor("#999999"));
        } else if (WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(dataSign.isValidSignStatus)) {
            baseViewHolder.setText((int) R.id.sign_actionBtn, (CharSequence) "已签到");
            baseViewHolder.setTextColor(R.id.sign_actionBtn, Color.parseColor("#999999"));
        } else {
            baseViewHolder.setText((int) R.id.sign_actionBtn, (CharSequence) "签到");
            baseViewHolder.setTextColor(R.id.sign_actionBtn, Color.parseColor("#999999"));
        }
    }
}
