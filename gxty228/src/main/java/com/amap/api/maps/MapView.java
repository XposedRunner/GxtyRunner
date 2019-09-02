package com.amap.api.maps;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.amap.api.mapcore.util.en;
import com.amap.api.mapcore.util.hn;
import com.amap.api.mapcore.util.lo;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;

public class MapView extends FrameLayout {
    private IMapFragmentDelegate a;
    private AMap b;
    private int c = 0;

    public MapView(Context context) {
        super(context);
        getMapFragmentDelegate().setContext(context);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.c);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.c);
    }

    public MapView(Context context, AMapOptions aMapOptions) {
        super(context);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setOptions(aMapOptions);
    }

    protected IMapFragmentDelegate getMapFragmentDelegate() {
        if (this.a == null) {
            try {
                this.a = (IMapFragmentDelegate) hn.a(getContext(), en.e(), "com.amap.api.wrapper.MapFragmentDelegateWrapper", lo.class, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(0)});
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (this.a == null) {
                this.a = new lo(0);
            }
        }
        return this.a;
    }

    public AMap getMap() {
        try {
            IAMap map = getMapFragmentDelegate().getMap();
            if (map == null) {
                return null;
            }
            if (this.b == null) {
                this.b = new AMap(map);
            }
            return this.b;
        } catch (Throwable th) {
            return null;
        }
    }

    public final void onCreate(Bundle bundle) {
        try {
            addView(getMapFragmentDelegate().onCreateView(null, null, bundle), new LayoutParams(-1, -1));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onResume() {
        try {
            getMapFragmentDelegate().onResume();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onPause() {
        try {
            getMapFragmentDelegate().onPause();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onDestroy() {
        try {
            getMapFragmentDelegate().onDestroy();
            this.b = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onLowMemory() {
        try {
            getMapFragmentDelegate().onLowMemory();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        try {
            getMapFragmentDelegate().onSaveInstanceState(bundle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLayerType(int i, Paint paint) {
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        getMapFragmentDelegate().setVisibility(i);
    }
}
