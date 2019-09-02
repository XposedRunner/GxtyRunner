package com.example.gita.gxty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.School;
import com.example.gita.gxty.ram.adapt.IAdapter;
import com.example.gita.gxty.ram.adapt.c;
import java.util.List;

public class SchoolAdapter extends IAdapter<List<School>> {
    boolean a = false;

    public SchoolAdapter(Context context, c cVar, List<School> list, boolean z) {
        super(context, cVar, list);
        this.a = z;
    }

    public int a(int i) {
        return ((School) this.b.get(i)).getSortLetters().charAt(0);
    }

    public int b(int i) {
        for (int i2 = 0; i2 < getCount(); i2++) {
            if (((School) this.b.get(i2)).getSortLetters().toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.inflate(R.layout.item_school, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.sortTxt);
        School school = (School) this.b.get(i);
        ((TextView) view.findViewById(R.id.name)).setText(school.name);
        if (!this.a) {
            textView.setVisibility(8);
        } else if (i == b(a(i))) {
            textView.setVisibility(0);
            textView.setText(school.getSortLetters());
        } else {
            textView.setVisibility(8);
        }
        return view;
    }
}
