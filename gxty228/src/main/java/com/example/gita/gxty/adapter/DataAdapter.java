package com.example.gita.gxty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.ram.adapt.IAdapter;
import com.example.gita.gxty.ram.adapt.c;
import com.example.gita.gxty.ram.db.a.a;
import java.util.List;

public class DataAdapter extends IAdapter<List<a>> {
    boolean a = false;

    public DataAdapter(Context context, c cVar, List<a> list, boolean z) {
        super(context, cVar, list);
        this.a = z;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.inflate(R.layout.item_data, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.data2);
        TextView textView2 = (TextView) view.findViewById(R.id.data3);
        TextView textView3 = (TextView) view.findViewById(R.id.data4);
        TextView textView4 = (TextView) view.findViewById(R.id.data5);
        a aVar = (a) this.b.get(i);
        ((TextView) view.findViewById(R.id.data1)).setText(aVar.b);
        textView.setText(aVar.c);
        textView2.setText(aVar.d);
        textView3.setText(aVar.e);
        textView4.setText(aVar.f);
        return view;
    }
}
