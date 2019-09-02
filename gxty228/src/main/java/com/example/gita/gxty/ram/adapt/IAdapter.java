package com.example.gita.gxty.ram.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import com.example.gita.gxty.R;
import java.util.List;

public abstract class IAdapter<T extends List> extends BaseAdapter {
    private a a = null;
    protected T b = null;
    protected c c = null;
    protected Context d = null;
    protected LayoutInflater e = null;
    private b f = null;

    public IAdapter(Context context, c cVar, T t) {
        this.b = t;
        this.c = cVar;
        this.d = context;
        this.e = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.b != null ? this.b.size() : 0;
    }

    public Object getItem(int i) {
        return this.b != null ? this.b.get(i) : null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a(T t) {
        if (t == null) {
            this.b.clear();
        } else {
            this.b = t;
        }
        notifyDataSetChanged();
    }

    public void a(View view, d dVar) {
        if (this.c != null) {
            if (this.a == null) {
                this.a = new a(this.c);
            }
            view.setTag(R.string.tag_tag_bean, dVar);
            view.setOnClickListener(this.a);
        }
    }
}
