package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import com.amap.api.mapcore.util.db.f;
import com.amap.api.mapcore.util.ef.e;
import com.amap.api.mapcore.util.er.c;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.interfaces.ITileOverlay;
import java.lang.ref.WeakReference;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TileOverlayDelegateImp */
public class cz implements cr {
    private static int h = 0;
    f a;
    private ag b;
    private TileProvider c;
    private Float d;
    private boolean e;
    private boolean f = false;
    private lj g;
    private int i = 256;
    private int j = 256;
    private int k = -1;
    private eo l;
    private List<a> m = new ArrayList();
    private boolean n = false;
    private b o = null;
    private String p = null;
    private FloatBuffer q = null;

    /* compiled from: TileOverlayDelegateImp */
    public static class a implements Cloneable {
        public int a;
        public int b;
        public int c;
        public int d;
        public IPoint e;
        public int f = 0;
        public boolean g = false;
        public FloatBuffer h = null;
        public Bitmap i = null;
        public com.amap.api.mapcore.util.er.a j = null;
        public int k = 0;
        private lj l;
        private ag m;
        private eo n;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public a(int i, int i2, int i3, int i4, lj ljVar, ag agVar, eo eoVar) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.l = ljVar;
            this.m = agVar;
            this.n = eoVar;
        }

        public a(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.e = aVar.e;
            this.h = aVar.h;
            this.k = 0;
            this.m = aVar.m;
            this.l = aVar.l;
            this.n = aVar.n;
        }

        public a a() {
            try {
                a aVar = (a) super.clone();
                aVar.a = this.a;
                aVar.b = this.b;
                aVar.c = this.c;
                aVar.d = this.d;
                aVar.e = (IPoint) this.e.clone();
                aVar.h = this.h.asReadOnlyBuffer();
                this.k = 0;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return new a(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.a == aVar.a && this.b == aVar.b && this.c == aVar.c && this.d == aVar.d) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.a * 7) + (this.b * 11)) + (this.c * 13)) + this.d;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append("-");
            stringBuilder.append(this.b);
            stringBuilder.append("-");
            stringBuilder.append(this.c);
            stringBuilder.append("-");
            stringBuilder.append(this.d);
            return stringBuilder.toString();
        }

        public synchronized void a(Bitmap bitmap) {
            if (bitmap != null) {
                if (!bitmap.isRecycled()) {
                    try {
                        this.j = null;
                        this.i = bitmap;
                        this.l.setRunLowFrame(false);
                    } catch (Throwable th) {
                        gz.c(th, "TileOverlayDelegateImp", "setBitmap");
                        th.printStackTrace();
                        if (this.k < 3) {
                            this.k++;
                            if (this.n != null) {
                                this.n.a(true, this);
                            }
                        }
                    }
                }
            }
            if (this.k < 3) {
                this.k++;
                if (this.n != null) {
                    this.n.a(true, this);
                }
            }
        }

        public void b() {
            try {
                er.a(this);
                if (this.g) {
                    this.m.a(this.f);
                }
                this.g = false;
                this.f = 0;
                if (!(this.i == null || this.i.isRecycled())) {
                    this.i.recycle();
                }
                this.i = null;
                if (this.h != null) {
                    this.h.clear();
                }
                this.h = null;
                this.j = null;
                this.k = 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* compiled from: TileOverlayDelegateImp */
    private static class b extends ef<Void, Void, List<a>> {
        private int d;
        private boolean e;
        private int f = 256;
        private int g = 256;
        private int h = 0;
        private WeakReference<lj> i;
        private List<a> j;
        private boolean k;
        private WeakReference<ag> l;
        private WeakReference<eo> m;

        public b(boolean z, lj ljVar, int i, int i2, int i3, List<a> list, boolean z2, ag agVar, eo eoVar) {
            this.e = z;
            this.i = new WeakReference(ljVar);
            this.f = i;
            this.g = i2;
            this.h = i3;
            this.j = list;
            this.k = z2;
            this.l = new WeakReference(agVar);
            this.m = new WeakReference(eoVar);
        }

        protected List<a> a(Void... voidArr) {
            try {
                lj ljVar = (lj) this.i.get();
                if (ljVar == null) {
                    return null;
                }
                int mapWidth = ljVar.getMapWidth();
                int mapHeight = ljVar.getMapHeight();
                this.d = (int) ljVar.g();
                if (mapWidth <= 0 || mapHeight <= 0) {
                    return null;
                }
                return cz.b(ljVar, this.d, this.f, this.g, this.h, (ag) this.l.get(), (eo) this.m.get());
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        protected void a(List<a> list) {
            if (list != null) {
                try {
                    if (list.size() > 0) {
                        cz.b((lj) this.i.get(), list, this.d, this.e, this.j, this.k, (ag) this.l.get(), (eo) this.m.get());
                        list.clear();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static String b(String str) {
        h++;
        return str + h;
    }

    public cz(TileOverlayOptions tileOverlayOptions, ag agVar, boolean z) {
        com.amap.api.mapcore.util.es.a aVar;
        this.b = agVar;
        this.c = tileOverlayOptions.getTileProvider();
        this.i = this.c.getTileWidth();
        this.j = this.c.getTileHeight();
        this.q = en.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
        this.d = Float.valueOf(tileOverlayOptions.getZIndex());
        this.e = tileOverlayOptions.isVisible();
        this.f = z;
        if (this.f) {
            this.p = "TileOverlay0";
        } else {
            this.p = getId();
        }
        this.g = this.b.a();
        this.k = Integer.parseInt(this.p.substring("TileOverlay".length()));
        if (z) {
            try {
                aVar = new com.amap.api.mapcore.util.es.a(this.b.e(), this.p, agVar.a().getMapConfig().getMapLanguage());
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        aVar = new com.amap.api.mapcore.util.es.a(this.b.e(), this.p);
        aVar.a(tileOverlayOptions.getMemoryCacheEnabled());
        if (this.f) {
            aVar.i = false;
        }
        aVar.b(tileOverlayOptions.getDiskCacheEnabled());
        aVar.a(tileOverlayOptions.getMemCacheSize());
        aVar.a(tileOverlayOptions.getDiskCacheSize());
        String diskCacheDir = tileOverlayOptions.getDiskCacheDir();
        if (!(diskCacheDir == null || "".equals(diskCacheDir))) {
            aVar.a(diskCacheDir);
        }
        this.l = new eo(this.b.e(), this.i, this.j);
        this.l.a(this.c);
        this.l.a(aVar);
        this.l.a(new c(this) {
            final /* synthetic */ cz a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.g.q();
            }
        });
    }

    public void remove() {
        this.b.b((cr) this);
        this.g.setRunLowFrame(false);
    }

    public void destroy(boolean z) {
        d();
        synchronized (this.m) {
            int size = this.m.size();
            for (int i = 0; i < size; i++) {
                ((a) this.m.get(i)).b();
            }
            this.m.clear();
        }
        if (this.l != null) {
            this.l.c(z);
            this.l.a(true);
            this.l.a(null);
        }
    }

    public void clearTileCache() {
        if (this.l != null) {
            this.l.f();
        }
    }

    public String getId() {
        if (this.p == null) {
            this.p = b("TileOverlay");
        }
        return this.p;
    }

    public void setZIndex(float f) {
        this.d = Float.valueOf(f);
        this.b.d();
    }

    public float getZIndex() {
        return this.d.floatValue();
    }

    public void setVisible(boolean z) {
        this.e = z;
        this.g.setRunLowFrame(false);
        if (z) {
            a(true);
        }
    }

    public boolean isVisible() {
        return this.e;
    }

    public boolean equalsRemote(ITileOverlay iTileOverlay) {
        if (equals(iTileOverlay) || iTileOverlay.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    private boolean a(a aVar) {
        float f = (float) aVar.c;
        int i = this.i;
        int i2 = this.j;
        int i3 = aVar.e.x;
        int i4 = aVar.e.y + ((1 << (20 - ((int) f))) * i2);
        MapConfig mapConfig = this.g.getMapConfig();
        float[] fArr = new float[]{(float) (i3 - mapConfig.getSX()), (float) (i4 - mapConfig.getSY()), 0.0f, (float) ((((1 << (20 - ((int) f))) * i) + i3) - mapConfig.getSX()), (float) (i4 - mapConfig.getSY()), 0.0f, (float) (((i * (1 << (20 - ((int) f)))) + i3) - mapConfig.getSX()), (float) ((i4 - ((1 << (20 - ((int) f))) * i2)) - mapConfig.getSY()), 0.0f, (float) (i3 - mapConfig.getSX()), (float) ((i4 - ((1 << (20 - ((int) f))) * i2)) - mapConfig.getSY()), 0.0f};
        if (aVar.h == null) {
            aVar.h = en.a(fArr);
        } else {
            aVar.h = en.a(fArr, aVar.h);
        }
        return true;
    }

    public void a() {
        if (this.m != null) {
            synchronized (this.m) {
                if (this.m.size() == 0) {
                    return;
                }
                int size = this.m.size();
                for (int i = 0; i < size; i++) {
                    a aVar = (a) this.m.get(i);
                    if (!aVar.g) {
                        try {
                            IPoint iPoint = aVar.e;
                            if (!(aVar.i == null || aVar.i.isRecycled() || iPoint == null)) {
                                aVar.f = en.a(aVar.i);
                                if (aVar.f != 0) {
                                    aVar.g = true;
                                }
                                aVar.i = null;
                            }
                        } catch (Throwable th) {
                            gz.c(th, "TileOverlayDelegateImp", "drawTiles");
                        }
                    }
                    if (aVar.g) {
                        a(aVar);
                        a(aVar.f, aVar.h, this.q);
                    }
                }
            }
        }
    }

    private static ArrayList<a> b(lj ljVar, int i, int i2, int i3, int i4, ag agVar, eo eoVar) {
        GLMapState c = ljVar.c();
        Rect rect = ljVar.getMapConfig().getGeoRectangle().getRect();
        IPoint obtain = IPoint.obtain();
        IPoint obtain2 = IPoint.obtain();
        obtain.x = rect.left;
        obtain.y = rect.top;
        int min = Math.min(Integer.MAX_VALUE, obtain.x);
        int max = Math.max(0, obtain.x);
        int min2 = Math.min(Integer.MAX_VALUE, obtain.y);
        int max2 = Math.max(0, obtain.y);
        obtain.x = rect.right;
        obtain.y = rect.top;
        min = Math.min(min, obtain.x);
        max = Math.max(max, obtain.x);
        min2 = Math.min(min2, obtain.y);
        max2 = Math.max(max2, obtain.y);
        obtain.x = rect.left;
        obtain.y = rect.bottom;
        min = Math.min(min, obtain.x);
        max = Math.max(max, obtain.x);
        min2 = Math.min(min2, obtain.y);
        max2 = Math.max(max2, obtain.y);
        obtain.x = rect.right;
        obtain.y = rect.bottom;
        min = Math.min(min, obtain.x);
        int max3 = Math.max(max, obtain.x);
        max = Math.min(min2, obtain.y);
        int max4 = Math.max(max2, obtain.y);
        int i5 = min - ((1 << (20 - i)) * i2);
        int i6 = max - ((1 << (20 - i)) * i3);
        c.getMapGeoCenter(obtain2);
        min = (obtain2.x >> (20 - i)) / i2;
        max = (obtain2.y >> (20 - i)) / i3;
        int i7 = (min << (20 - i)) * i2;
        int i8 = (max << (20 - i)) * i3;
        a aVar = new a(min, max, i, i4, ljVar, agVar, eoVar);
        aVar.e = IPoint.obtain(i7, i8);
        obtain.recycle();
        obtain2.recycle();
        ArrayList<a> arrayList = new ArrayList();
        arrayList.add(aVar);
        i8 = 1;
        while (true) {
            Object obj = null;
            for (max2 = min - i8; max2 <= min + i8; max2++) {
                int i9 = max + i8;
                IPoint iPoint = new IPoint((max2 << (20 - i)) * i2, (i9 << (20 - i)) * i3);
                if (iPoint.x < max3 && iPoint.x > i5 && iPoint.y < max4 && iPoint.y > i6) {
                    if (obj == null) {
                        obj = 1;
                    }
                    a aVar2 = new a(max2, i9, i, i4, ljVar, agVar, eoVar);
                    aVar2.e = iPoint;
                    arrayList.add(aVar2);
                }
                i9 = max - i8;
                iPoint = new IPoint((max2 << (20 - i)) * i2, (i9 << (20 - i)) * i3);
                if (iPoint.x < max3 && iPoint.x > i5 && iPoint.y < max4 && iPoint.y > i6) {
                    if (obj == null) {
                        obj = 1;
                    }
                    aVar2 = new a(max2, i9, i, i4, ljVar, agVar, eoVar);
                    aVar2.e = iPoint;
                    arrayList.add(aVar2);
                }
            }
            for (i9 = (max + i8) - 1; i9 > max - i8; i9--) {
                max2 = min + i8;
                iPoint = new IPoint((max2 << (20 - i)) * i2, (i9 << (20 - i)) * i3);
                if (iPoint.x < max3 && iPoint.x > i5 && iPoint.y < max4 && iPoint.y > i6) {
                    if (obj == null) {
                        obj = 1;
                    }
                    aVar2 = new a(max2, i9, i, i4, ljVar, agVar, eoVar);
                    aVar2.e = iPoint;
                    arrayList.add(aVar2);
                }
                max2 = min - i8;
                iPoint = new IPoint((max2 << (20 - i)) * i2, (i9 << (20 - i)) * i3);
                if (iPoint.x < max3 && iPoint.x > i5 && iPoint.y < max4 && iPoint.y > i6) {
                    if (obj == null) {
                        obj = 1;
                    }
                    aVar2 = new a(max2, i9, i, i4, ljVar, agVar, eoVar);
                    aVar2.e = iPoint;
                    arrayList.add(aVar2);
                }
            }
            if (obj == null) {
                return arrayList;
            }
            i8++;
        }
    }

    public void b() {
        if (this.m != null) {
            synchronized (this.m) {
                this.m.clear();
            }
        }
    }

    private static boolean b(lj ljVar, List<a> list, int i, boolean z, List<a> list2, boolean z2, ag agVar, eo eoVar) {
        int i2 = 0;
        if (list == null) {
            return false;
        }
        if (list2 == null) {
            return false;
        }
        int i3;
        synchronized (list2) {
            for (a aVar : list2) {
                a aVar2;
                for (a aVar3 : list) {
                    if (aVar2.equals(aVar3) && aVar2.g) {
                        aVar3.g = aVar2.g;
                        aVar3.f = aVar2.f;
                        i3 = 1;
                        break;
                    }
                }
                i3 = 0;
                if (i3 == 0) {
                    aVar2.b();
                }
            }
            list2.clear();
        }
        if (i > ((int) ljVar.getMaxZoomLevel()) || i < ((int) ljVar.getMinZoomLevel())) {
            return false;
        }
        i3 = list.size();
        if (i3 <= 0) {
            return false;
        }
        while (i2 < i3) {
            aVar2 = (a) list.get(i2);
            if (aVar2 != null) {
                if (z2) {
                    if (agVar.a().getMapConfig().getMapLanguage().equals("zh_cn")) {
                        if (MapsInitializer.isLoadWorldGridMap()) {
                            if (aVar2.c >= 7) {
                                if (ee.a(aVar2.a, aVar2.b, aVar2.c)) {
                                }
                            }
                        }
                    } else if (!(MapsInitializer.isLoadWorldGridMap() || aVar2.c < 7 || ee.a(aVar2.a, aVar2.b, aVar2.c))) {
                    }
                }
                list2.add(aVar2);
                if (!(aVar2.g || eoVar == null)) {
                    eoVar.a(z, aVar2);
                }
            }
            i2++;
        }
        return true;
    }

    public void a(boolean z) {
        if (!this.n) {
            d();
            c(z);
        }
    }

    private void d() {
        if (this.o != null && this.o.a() == e.RUNNING) {
            this.o.a(true);
        }
    }

    private void c(boolean z) {
        this.o = new b(z, this.g, this.i, this.j, this.k, this.m, this.f, this.b, this.l);
        this.o.c((Object[]) new Void[0]);
    }

    public void c() {
        d();
        synchronized (this.m) {
            int size = this.m.size();
            for (int i = 0; i < size; i++) {
                ((a) this.m.get(i)).b();
            }
            this.m.clear();
        }
    }

    public void b(boolean z) {
        if (this.n != z) {
            this.n = z;
            if (this.l != null) {
                this.l.a(z);
            }
        }
    }

    public void a(String str) {
        d();
        b();
        if (this.l != null) {
            this.l.a(true);
            this.l.a(str);
            this.l.a(false);
        }
        c(true);
    }

    private void e() {
        if (this.b != null && this.b.a() != null) {
            this.a = (f) this.b.a().u(0);
        }
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null && i != 0) {
            if (this.a == null || this.a.c()) {
                e();
            }
            this.a.a();
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
            GLES20.glEnableVertexAttribArray(this.a.b);
            GLES20.glVertexAttribPointer(this.a.b, 3, 5126, false, 12, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.a.c);
            GLES20.glVertexAttribPointer(this.a.c, 2, 5126, false, 8, floatBuffer2);
            GLES20.glUniformMatrix4fv(this.a.a, 1, false, this.b.g(), 0);
            GLES20.glDrawArrays(6, 0, 4);
            GLES20.glDisableVertexAttribArray(this.a.b);
            GLES20.glDisableVertexAttribArray(this.a.c);
            GLES20.glBindTexture(3553, 0);
            GLES20.glUseProgram(0);
            GLES20.glDisable(3042);
        }
    }
}
