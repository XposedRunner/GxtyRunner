package com.example.gita.gxty.ram.adapt;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.h;

/* compiled from: AdapterItemLongClicker */
public class b implements OnLongClickListener {
    c a;

    public boolean onLongClick(View view) {
        Object tag = view.getTag(R.string.tag_tag_bean);
        if (tag != null) {
            d dVar = (d) tag;
            this.a.b(view, dVar.a, dVar.c, dVar.b);
        } else {
            h.b("error error error");
        }
        return false;
    }
}
