package com.example.gita.gxty.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.Class;
import java.util.ArrayList;

public class ClassAdapter extends BaseQuickAdapter<Class, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (Class) obj);
    }

    public ClassAdapter(ArrayList arrayList) {
        super(R.layout.item_class, arrayList);
    }

    protected void a(BaseViewHolder baseViewHolder, Class classR) {
        baseViewHolder.setText((int) R.id.name, classR.name);
    }
}
