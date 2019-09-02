package com.example.gita.gxty.weiget.gallery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;

public class RvGallery extends RecyclerView {
    private a a;
    private RecyclerView b;
    private LinearSnapHelper c;
    private LinearLayoutManager d;
    private boolean e;
    private Context f;

    public interface a {
        void a(int i);
    }

    public RvGallery(Context context) {
        this(context, null);
    }

    public RvGallery(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RvGallery(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = context;
        a();
    }

    public void setOnItemSelectedListener(a aVar) {
        this.a = aVar;
    }

    private void a() {
        this.b = this;
        RecyclerView recyclerView = this.b;
        LayoutManager linearLayoutManager = new LinearLayoutManager(this.f, 0, false);
        this.d = linearLayoutManager;
        recyclerView.setLayoutManager(linearLayoutManager);
        this.c = new LinearSnapHelper();
        this.c.attachToRecyclerView(this.b);
        this.b.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ RvGallery a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                switch (i) {
                    case 0:
                        if (this.a.e) {
                            int childLayoutPosition = recyclerView.getChildLayoutPosition(this.a.c.findSnapView(this.a.d));
                            if (this.a.a != null && recyclerView.getAdapter().getItemCount() > 2) {
                                this.a.a.a(childLayoutPosition - 1);
                            }
                            this.a.e = false;
                            return;
                        }
                        return;
                    case 1:
                        this.a.e = true;
                        return;
                    default:
                        return;
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
    }
}
