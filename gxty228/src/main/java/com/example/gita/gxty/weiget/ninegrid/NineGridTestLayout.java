package com.example.gita.gxty.weiget.ninegrid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.duudu.image.ImageViewPagerActivity;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.s;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;
import java.util.List;

public class NineGridTestLayout extends NineGridLayout {
    private boolean b = true;

    public NineGridTestLayout(Context context) {
        super(context);
    }

    public NineGridTestLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(boolean z) {
        this.b = z;
    }

    protected boolean a(final RatioImageView ratioImageView, String str, final int i) {
        a.a(this.a, ratioImageView, a(str), a.a(), new a(this) {
            final /* synthetic */ NineGridTestLayout c;

            public void a(String str, View view) {
            }

            public void a(String str, View view, FailReason failReason) {
            }

            public void a(String str, View view, Bitmap bitmap) {
                bitmap.getWidth();
                bitmap.getHeight();
                int i = i / 3;
                this.c.a(ratioImageView, i, i);
            }

            public void b(String str, View view) {
            }
        });
        return false;
    }

    protected void a(RatioImageView ratioImageView, String str) {
        a.a(this.a).a(a(str), (ImageView) ratioImageView, a.a());
    }

    protected void a(int i, String str, List<String> list) {
        h.b(str);
        ImageViewPagerActivity.a((Activity) this.a, list, i, true);
    }

    public String a(String str) {
        if (this.b) {
            return s.b(str);
        }
        return str;
    }
}
