package com.example.gita.gxty.weiget.devider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public abstract class Y_DividerItemDecoration extends ItemDecoration {
    private Paint a = new Paint(1);
    private Context b;

    @Nullable
    public abstract b a(int i);

    public Y_DividerItemDecoration(Context context) {
        this.b = context;
        this.a.setStyle(Style.FILL);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            b a = a(((LayoutParams) childAt.getLayoutParams()).getViewLayoutPosition());
            if (a.a().c()) {
                int a2 = a.a(this.b, a.a().e());
                int a3 = a.a(this.b, a.a().a());
                int a4 = a.a(this.b, a.a().b());
                c(childAt, canvas, recyclerView, a.a().d(), a2, a3, a4);
            }
            if (a.b().c()) {
                a2 = a.a(this.b, a.b().e());
                a3 = a.a(this.b, a.b().a());
                a4 = a.a(this.b, a.b().b());
                b(childAt, canvas, recyclerView, a.b.d(), a2, a3, a4);
            }
            if (a.c().c()) {
                a2 = a.a(this.b, a.c().e());
                a3 = a.a(this.b, a.c().a());
                a4 = a.a(this.b, a.c().b());
                d(childAt, canvas, recyclerView, a.c().d(), a2, a3, a4);
            }
            if (a.d().c()) {
                a2 = a.a(this.b, a.d().e());
                a3 = a.a(this.b, a.d().a());
                a4 = a.a(this.b, a.d().b());
                a(childAt, canvas, recyclerView, a.d().d(), a2, a3, a4);
            }
        }
    }

    private void a(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        int i5;
        if (i3 <= 0) {
            i3 = -i2;
        }
        if (i4 <= 0) {
            i5 = i2;
        } else {
            i5 = -i4;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int left = (view.getLeft() - layoutParams.leftMargin) + i3;
        int right = (view.getRight() + layoutParams.rightMargin) + i5;
        int bottom = layoutParams.bottomMargin + view.getBottom();
        int i6 = bottom + i2;
        this.a.setColor(i);
        canvas.drawRect((float) left, (float) bottom, (float) right, (float) i6, this.a);
    }

    private void b(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        int i5;
        if (i3 <= 0) {
            i3 = -i2;
        }
        if (i4 <= 0) {
            i5 = i2;
        } else {
            i5 = -i4;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int left = (view.getLeft() - layoutParams.leftMargin) + i3;
        int right = (view.getRight() + layoutParams.rightMargin) + i5;
        int top = view.getTop() - layoutParams.topMargin;
        int i6 = top - i2;
        this.a.setColor(i);
        canvas.drawRect((float) left, (float) i6, (float) right, (float) top, this.a);
    }

    private void c(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        int i5;
        if (i3 <= 0) {
            i3 = -i2;
        }
        if (i4 <= 0) {
            i5 = i2;
        } else {
            i5 = -i4;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int top = (view.getTop() - layoutParams.topMargin) + i3;
        int bottom = (view.getBottom() + layoutParams.bottomMargin) + i5;
        int left = view.getLeft() - layoutParams.leftMargin;
        i5 = left - i2;
        this.a.setColor(i);
        canvas.drawRect((float) i5, (float) top, (float) left, (float) bottom, this.a);
    }

    private void d(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        int i5;
        if (i3 <= 0) {
            i3 = -i2;
        }
        if (i4 <= 0) {
            i5 = i2;
        } else {
            i5 = -i4;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int top = (view.getTop() - layoutParams.topMargin) + i3;
        int bottom = (view.getBottom() + layoutParams.bottomMargin) + i5;
        int right = layoutParams.rightMargin + view.getRight();
        int i6 = right + i2;
        this.a.setColor(i);
        canvas.drawRect((float) right, (float) top, (float) i6, (float) bottom, this.a);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int a;
        int a2;
        int i = 0;
        b a3 = a(((LayoutParams) view.getLayoutParams()).getViewLayoutPosition());
        if (a3 == null) {
            a3 = new c().a();
        }
        int a4 = a3.a().c() ? a.a(this.b, a3.a().e()) : 0;
        if (a3.b().c()) {
            a = a.a(this.b, a3.b().e());
        } else {
            a = 0;
        }
        if (a3.c().c()) {
            a2 = a.a(this.b, a3.c().e());
        } else {
            a2 = 0;
        }
        if (a3.d().c()) {
            i = a.a(this.b, a3.d().e());
        }
        rect.set(a4, a, a2, i);
    }
}
