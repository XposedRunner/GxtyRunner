package com.bigkoo.pickerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.bigkoo.pickerview.d.b;
import java.util.ArrayList;

/* compiled from: OptionsPickerView */
public class a<T> extends com.bigkoo.pickerview.d.a implements OnClickListener {
    b a;
    private View c = a(R.id.btnSubmit);
    private View d;
    private TextView e;
    private a f;

    /* compiled from: OptionsPickerView */
    public interface a {
        void a(int i, int i2, int i3);
    }

    public a(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pickerview_options, this.b);
        this.c.setTag("submit");
        this.d = a(R.id.btnCancel);
        this.d.setTag("cancel");
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e = (TextView) a(R.id.tvTitle);
        this.a = new b(a(R.id.optionspicker));
    }

    public void a(ArrayList<T> arrayList) {
        this.a.a(arrayList, null, null, false);
    }

    public void a(boolean z) {
        this.a.a(z);
    }

    public void onClick(View view) {
        if (((String) view.getTag()).equals("cancel")) {
            f();
            return;
        }
        if (this.f != null) {
            int[] a = this.a.a();
            this.f.a(a[0], a[1], a[2]);
        }
        f();
    }

    public void setOnoptionsSelectListener(a aVar) {
        this.f = aVar;
    }

    public void a(String str) {
        this.e.setText(str);
    }
}
