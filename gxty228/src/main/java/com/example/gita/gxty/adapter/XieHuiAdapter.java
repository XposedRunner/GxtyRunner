package com.example.gita.gxty.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.TXieHui;
import java.util.ArrayList;

public class XieHuiAdapter extends BaseQuickAdapter<TXieHui, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (TXieHui) obj);
    }

    public XieHuiAdapter(ArrayList arrayList) {
        super(R.layout.item_xiehui, arrayList);
    }

    protected void a(BaseViewHolder baseViewHolder, TXieHui tXieHui) {
        baseViewHolder.setText((int) R.id.name, tXieHui.name);
    }
}
