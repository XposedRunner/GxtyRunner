package com.example.gita.gxty.weiget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataCaiDan;

/* compiled from: CaiDanDialog */
public class a extends Dialog implements OnClickListener {
    a a;
    private View b = findViewById(R.id.caidan_topLayout);
    private View c = findViewById(R.id.caidan_btn);
    private TextView d = ((TextView) findViewById(R.id.caidan_txt));
    private ImageView e = ((ImageView) findViewById(R.id.caidan_img));
    private DataCaiDan f;

    /* compiled from: CaiDanDialog */
    public interface a {
        boolean a(a aVar, DataCaiDan dataCaiDan);

        boolean b(a aVar, DataCaiDan dataCaiDan);
    }

    public a(Context context, DataCaiDan dataCaiDan) {
        super(context, R.style.my_dialog);
        this.f = dataCaiDan;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.caidan_dialog);
        this.d.setText(dataCaiDan.title);
        e.b(context).a(dataCaiDan.icon).b((int) R.mipmap.popuo_img2).a(this.e);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (R.id.caidan_topLayout == view.getId()) {
            if (this.a != null) {
                this.a.a(this, this.f);
            }
            dismiss();
        } else if (R.id.caidan_btn == view.getId()) {
            if (this.a != null) {
                this.a.b(this, this.f);
            }
            dismiss();
        }
    }
}
