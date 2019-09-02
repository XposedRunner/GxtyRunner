package com.example.gita.gxty.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.Tcourse;
import java.util.ArrayList;

public class CourseAdapter extends BaseQuickAdapter<Tcourse, BaseViewHolder> {
    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (Tcourse) obj);
    }

    public CourseAdapter(ArrayList arrayList) {
        super(R.layout.item_course, arrayList);
    }

    protected void a(BaseViewHolder baseViewHolder, Tcourse tcourse) {
        baseViewHolder.setText((int) R.id.teacher, tcourse.teacher);
        baseViewHolder.setText((int) R.id.name, tcourse.name);
    }
}
