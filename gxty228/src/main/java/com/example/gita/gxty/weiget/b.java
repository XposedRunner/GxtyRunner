package com.example.gita.gxty.weiget;

import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.s;

/* compiled from: ChooseDialog */
public class b extends Dialog implements OnClickListener {
    a a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e = ((TextView) findViewById(R.id.dContent));

    /* compiled from: ChooseDialog */
    public interface a {
        boolean a(b bVar, int i);
    }

    public b(Context context, String str, String str2, String str3, String str4) {
        super(context, R.style.my_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.ba_choose_dialog);
        this.e.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.b = (TextView) findViewById(R.id.dTitle);
        this.d = (TextView) findViewById(R.id.dOk);
        this.c = (TextView) findViewById(R.id.dCancel);
        this.d.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.e.setText(s.a(str2));
        this.b.setText(str);
        this.d.setText(str3);
        this.c.setText(str4);
    }

    public TextView a() {
        return this.c;
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (R.id.dOk == view.getId()) {
            if (this.a == null) {
                dismiss();
            } else if (!this.a.a(this, 1)) {
                dismiss();
            }
        } else if (R.id.dCancel != view.getId()) {
        } else {
            if (this.a == null) {
                dismiss();
            } else if (!this.a.a(this, 0)) {
                dismiss();
            }
        }
    }
}
