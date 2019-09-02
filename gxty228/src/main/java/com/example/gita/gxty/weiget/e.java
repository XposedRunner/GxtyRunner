package com.example.gita.gxty.weiget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.gita.gxty.R;

/* compiled from: SignMainDialog */
public class e extends Dialog implements OnClickListener {
    a a;
    private View b = findViewById(R.id.sport_xiehuiBtn);
    private View c = findViewById(R.id.sport_changdiBtn);
    private View d = findViewById(R.id.sport_saishiBtn);
    private View e = findViewById(R.id.sport_cancelBtn);

    /* compiled from: SignMainDialog */
    public interface a {
        boolean a(e eVar, int i);
    }

    public e(Context context) {
        super(context, R.style.my_dialog2);
        setCancelable(true);
        setContentView(R.layout.sign_main);
        getWindow().setGravity(80);
        getWindow().setLayout(-1, -2);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (R.id.sport_xiehuiBtn == view.getId()) {
            if (this.a != null) {
                this.a.a(this, 1);
            }
        } else if (R.id.sport_changdiBtn == view.getId()) {
            if (this.a != null) {
                this.a.a(this, 2);
            }
        } else if (R.id.sport_saishiBtn == view.getId() && this.a != null) {
            this.a.a(this, 3);
        }
        dismiss();
    }
}
