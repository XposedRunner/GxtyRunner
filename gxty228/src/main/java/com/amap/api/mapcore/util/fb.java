package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.example.gita.gxty.R;

/* compiled from: BottomDialog */
public class fb extends fc implements OnClickListener {
    private OfflineMapManager a;
    private View b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private int g;
    private String h;

    public fb(Context context, OfflineMapManager offlineMapManager) {
        super(context);
        this.a = offlineMapManager;
    }

    protected void a() {
        this.b = fh.a(getContext(), R.mipmap.ads_txt_img, null);
        setContentView(this.b);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ fb a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
        this.c = (TextView) this.b.findViewById(2131165191);
        this.d = (TextView) this.b.findViewById(2131165192);
        this.d.setText("暂停下载");
        this.e = (TextView) this.b.findViewById(2131165193);
        this.f = (TextView) this.b.findViewById(2131165194);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    public void a(int i, String str) {
        this.c.setText(str);
        if (i == 0) {
            this.d.setText("暂停下载");
            this.d.setVisibility(0);
            this.e.setText("取消下载");
        }
        if (i == 2) {
            this.d.setVisibility(8);
            this.e.setText("取消下载");
        } else if (i == -1 || i == 101 || i == 102 || i == 103) {
            this.d.setText("继续下载");
            this.d.setVisibility(0);
        } else if (i == 3) {
            this.d.setVisibility(0);
            this.d.setText("继续下载");
            this.e.setText("取消下载");
        } else if (i == 4) {
            this.e.setText("删除");
            this.d.setVisibility(8);
        }
        this.g = i;
        this.h = str;
    }

    public void onClick(View view) {
        try {
            int id = view.getId();
            if (id == 2131165192) {
                if (this.g == 0) {
                    this.d.setText("继续下载");
                    this.a.pause();
                } else if (this.g == 3 || this.g == -1 || this.g == 101 || this.g == 102 || this.g == 103) {
                    this.d.setText("暂停下载");
                    this.a.downloadByCityName(this.h);
                }
                dismiss();
            } else if (id == 2131165193) {
                if (!TextUtils.isEmpty(this.h)) {
                    this.a.remove(this.h);
                    dismiss();
                }
            } else if (id == 2131165194) {
                dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
