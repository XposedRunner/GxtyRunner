package com.amap.api.mapcore.util;

import com.amap.api.maps.model.MultiPointItem;
import com.autonavi.amap.mapcore.IPoint;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: QuadTree2 */
public class ak {
    private final ah a;
    private final int b;
    private int c;
    private List<MultiPointItem> d;
    private List<ak> e;

    protected ak(ah ahVar) {
        this(ahVar, 0);
    }

    private ak(int i, int i2, int i3, int i4, int i5) {
        this(new ah(i, i2, i3, i4), i5);
    }

    private ak(ah ahVar, int i) {
        this.c = 30;
        this.e = null;
        this.a = ahVar;
        this.b = i;
        this.c = a(this.b);
    }

    private int a(int i) {
        switch (i) {
            case 0:
                return 50;
            case 1:
                return 30;
            case 2:
            case 3:
                return 20;
            case 4:
                return 10;
            case 5:
                return 10;
            case 6:
                return 5;
            default:
                return 5;
        }
    }

    protected void a(MultiPointItem multiPointItem) {
        IPoint iPoint = multiPointItem.getIPoint();
        if (this.a.a(iPoint.x, iPoint.y)) {
            a(iPoint.x, iPoint.y, multiPointItem);
        }
    }

    private void a(int i, int i2, MultiPointItem multiPointItem) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        if (this.d.size() <= this.c || this.b >= 40) {
            this.d.add(multiPointItem);
            return;
        }
        if (this.e == null) {
            b();
        }
        if (this.e == null) {
            return;
        }
        if (i2 < this.a.f) {
            if (i < this.a.e) {
                ((ak) this.e.get(0)).a(i, i2, multiPointItem);
            } else {
                ((ak) this.e.get(1)).a(i, i2, multiPointItem);
            }
        } else if (i < this.a.e) {
            ((ak) this.e.get(2)).a(i, i2, multiPointItem);
        } else {
            ((ak) this.e.get(3)).a(i, i2, multiPointItem);
        }
    }

    private void b() {
        this.e = new ArrayList(4);
        this.e.add(new ak(this.a.a, this.a.e, this.a.b, this.a.f, this.b + 1));
        this.e.add(new ak(this.a.e, this.a.c, this.a.b, this.a.f, this.b + 1));
        this.e.add(new ak(this.a.a, this.a.e, this.a.f, this.a.d, this.b + 1));
        this.e.add(new ak(this.a.e, this.a.c, this.a.f, this.a.d, this.b + 1));
    }

    protected void a() {
        this.e = null;
        if (this.d != null) {
            this.d.clear();
        }
    }

    protected void a(ah ahVar, Collection<MultiPointItem> collection, double d) {
        a(ahVar, collection, 1.0f, d);
    }

    private void a(ah ahVar, Collection<MultiPointItem> collection, float f, double d) {
        if (this.a.a(ahVar)) {
            float f2;
            if (this.d != null) {
                int size = (int) (((float) this.d.size()) * f);
                for (int i = 0; i < size; i++) {
                    MultiPointItem multiPointItem = (MultiPointItem) this.d.get(i);
                    if (ahVar.a(multiPointItem.getIPoint())) {
                        collection.add(multiPointItem);
                    }
                }
            }
            if (d > Utils.DOUBLE_EPSILON) {
                double d2 = ((((double) this.a.d) - ((double) this.a.b)) * (((double) this.a.c) - ((double) this.a.a))) / d;
                if (d2 >= ((double) 0.7f)) {
                    f2 = d2 > 1.0d ? 1.0f : (float) ((((4.8188d * d2) * d2) - (d2 * 4.9339d)) + 1.1093d);
                } else {
                    return;
                }
            }
            f2 = f;
            if (this.e != null) {
                for (ak a : this.e) {
                    a.a(ahVar, collection, f2, d);
                }
            }
        }
    }
}
