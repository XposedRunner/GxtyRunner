package com.example.gita.gxty.weiget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.example.gita.gxty.R;

/* compiled from: LoadingDialog */
public class c extends Dialog {
    public c(Context context) {
        this(context, "");
    }

    public c(Context context, String str) {
        super(context, R.style.loading_dialog);
        View inflate = LayoutInflater.from(context).inflate(R.layout.progressdialog_no_deal, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.dialog_view);
        TextView textView = (TextView) inflate.findViewById(R.id.tipTextView);
        ((ImageView) inflate.findViewById(R.id.img)).startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim));
        if (str.equals("")) {
            textView.setVisibility(8);
        } else {
            textView.setText(str);
        }
        setCancelable(false);
        setContentView(linearLayout, new LayoutParams(-1, -1));
        inflate.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.dismiss();
                return true;
            }
        });
    }
}
