package com.example.gita.gxty.weiget.aletview;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.gita.gxty.R;
import java.util.List;

public class AlertViewAdapter extends BaseAdapter {
    private List<String> a;
    private List<String> b;

    class a {
        final /* synthetic */ AlertViewAdapter a;
        private TextView b;

        public a(AlertViewAdapter alertViewAdapter, View view) {
            this.a = alertViewAdapter;
            this.b = (TextView) view.findViewById(R.id.tvAlert);
        }

        public void a(Context context, String str, int i) {
            try {
                this.b.setText(Html.fromHtml(str));
            } catch (Exception e) {
                e.printStackTrace();
                this.b.setText(str);
            }
            if (this.a.b == null || !this.a.b.contains(str)) {
                this.b.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_others));
            } else {
                this.b.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_destructive));
            }
        }
    }

    public AlertViewAdapter(List<String> list, List<String> list2) {
        this.a = list;
        this.b = list2;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a a;
        String str = (String) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_alertbutton, null);
            a = a(view);
            view.setTag(a);
        } else {
            a = (a) view.getTag();
        }
        a.a(viewGroup.getContext(), str, i);
        return view;
    }

    public a a(View view) {
        return new a(this, view);
    }
}
