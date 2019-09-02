package com.bigkoo.pickerview.d;

import android.view.View;
import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.a.a;
import com.bigkoo.pickerview.lib.WheelView;
import java.util.ArrayList;

/* compiled from: WheelOptions */
public class b<T> {
    private View a;
    private WheelView b;
    private WheelView c;
    private WheelView d;
    private ArrayList<T> e;
    private ArrayList<ArrayList<T>> f;
    private ArrayList<ArrayList<ArrayList<T>>> g;
    private boolean h = false;
    private com.bigkoo.pickerview.b.b i;
    private com.bigkoo.pickerview.b.b j;

    public void a(View view) {
        this.a = view;
    }

    public b(View view) {
        this.a = view;
        a(view);
    }

    public void a(ArrayList<T> arrayList, ArrayList<ArrayList<T>> arrayList2, ArrayList<ArrayList<ArrayList<T>>> arrayList3, boolean z) {
        int i;
        this.h = z;
        this.e = arrayList;
        this.f = arrayList2;
        this.g = arrayList3;
        int i2 = 4;
        if (this.g == null) {
            i2 = 8;
        }
        if (this.f == null) {
            i = 12;
        } else {
            i = i2;
        }
        this.b = (WheelView) this.a.findViewById(R.id.options1);
        this.b.setAdapter(new a(this.e, i));
        this.b.setCurrentItem(0);
        this.c = (WheelView) this.a.findViewById(R.id.options2);
        if (this.f != null) {
            this.c.setAdapter(new a((ArrayList) this.f.get(0)));
        }
        this.c.setCurrentItem(this.b.getCurrentItem());
        this.d = (WheelView) this.a.findViewById(R.id.options3);
        if (this.g != null) {
            this.d.setAdapter(new a((ArrayList) ((ArrayList) this.g.get(0)).get(0)));
        }
        this.d.setCurrentItem(this.d.getCurrentItem());
        this.b.setTextSize((float) 25);
        this.c.setTextSize((float) 25);
        this.d.setTextSize((float) 25);
        if (this.f == null) {
            this.c.setVisibility(8);
        }
        if (this.g == null) {
            this.d.setVisibility(8);
        }
        this.i = new com.bigkoo.pickerview.b.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                int i2 = 0;
                if (this.a.f != null) {
                    i2 = this.a.c.getCurrentItem();
                    if (i2 >= ((ArrayList) this.a.f.get(i)).size() - 1) {
                        i2 = ((ArrayList) this.a.f.get(i)).size() - 1;
                    }
                    this.a.c.setAdapter(new a((ArrayList) this.a.f.get(i)));
                    this.a.c.setCurrentItem(i2);
                }
                if (this.a.g != null) {
                    this.a.j.a(i2);
                }
            }
        };
        this.j = new com.bigkoo.pickerview.b.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (this.a.g != null) {
                    int currentItem = this.a.b.getCurrentItem();
                    int size = currentItem >= this.a.g.size() + -1 ? this.a.g.size() - 1 : currentItem;
                    if (i >= ((ArrayList) this.a.f.get(size)).size() - 1) {
                        i = ((ArrayList) this.a.f.get(size)).size() - 1;
                    }
                    int currentItem2 = this.a.d.getCurrentItem();
                    if (currentItem2 >= ((ArrayList) ((ArrayList) this.a.g.get(size)).get(i)).size() - 1) {
                        size = ((ArrayList) ((ArrayList) this.a.g.get(size)).get(i)).size() - 1;
                    } else {
                        size = currentItem2;
                    }
                    this.a.d.setAdapter(new a((ArrayList) ((ArrayList) this.a.g.get(this.a.b.getCurrentItem())).get(i)));
                    this.a.d.setCurrentItem(size);
                }
            }
        };
        if (arrayList2 != null && z) {
            this.b.setOnItemSelectedListener(this.i);
        }
        if (arrayList3 != null && z) {
            this.c.setOnItemSelectedListener(this.j);
        }
    }

    public void a(boolean z) {
        this.b.setCyclic(z);
        this.c.setCyclic(z);
        this.d.setCyclic(z);
    }

    public int[] a() {
        return new int[]{this.b.getCurrentItem(), this.c.getCurrentItem(), this.d.getCurrentItem()};
    }
}
