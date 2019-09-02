package com.example.gita.gxty.ram.adapt;

import android.view.View;
import android.view.View.OnClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.h;

/* compiled from: AdapterItemClicker */
public class a implements OnClickListener {
    c a;

    public a(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        if (this.a != null) {
            Object tag = view.getTag(R.string.tag_tag_bean);
            if (tag != null) {
                d dVar = (d) tag;
                this.a.a(view, dVar.a, dVar.c, dVar.b);
                return;
            }
            h.b("error error error");
        }
    }
}
