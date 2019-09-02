package com.amap.api.maps;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import com.amap.api.mapcore.util.en;
import com.amap.api.mapcore.util.hn;
import com.amap.api.mapcore.util.lo;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;
import com.blankj.utilcode.constant.MemoryConstants;

@TargetApi(20)
public class WearMapView extends ViewGroup {
    private static int f = 0;
    private static int g = 0;
    private final String a = WearMapView.class.getSimpleName();
    private IMapFragmentDelegate b;
    private AMap c;
    private View d;
    private SwipeDismissView e;
    private int h = 0;

    public interface OnDismissCallback {
        void onDismiss();

        void onNotifySwipe();
    }

    public WearMapView(Context context) {
        super(context);
        getMapFragmentDelegate().setContext(context);
        a(context);
        b(context);
    }

    public WearMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.h);
        a(context);
        b(context);
    }

    public WearMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.h);
        a(context);
        b(context);
    }

    public WearMapView(Context context, AMapOptions aMapOptions) {
        super(context);
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setOptions(aMapOptions);
        a(context);
        b(context);
    }

    protected IMapFragmentDelegate getMapFragmentDelegate() {
        if (this.b == null) {
            try {
                this.b = (IMapFragmentDelegate) hn.a(getContext(), en.e(), "com.amap.api.wrapper.MapFragmentDelegateWrapper", lo.class, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(1)});
            } catch (Throwable th) {
            }
            if (this.b == null) {
                this.b = new lo(1);
            }
        }
        return this.b;
    }

    public AMap getMap() {
        try {
            IAMap map = getMapFragmentDelegate().getMap();
            if (map == null) {
                return null;
            }
            if (this.c == null) {
                this.c = new AMap(map);
            }
            return this.c;
        } catch (Throwable th) {
            return null;
        }
    }

    public final void onCreate(Bundle bundle) {
        try {
            this.d = getMapFragmentDelegate().onCreateView(null, null, bundle);
            addView(this.d, 0, new LayoutParams(-1, -1));
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

    private void a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            if (defaultDisplay != null) {
                defaultDisplay.getSize(point);
            }
            f = point.x;
            g = point.y;
        }
    }

    private void b(Context context) {
        this.e = new SwipeDismissView(context, this);
        LayoutParams layoutParams = new LayoutParams((int) ((context.getResources().getDisplayMetrics().density * 30.0f) + 0.5f), g);
        this.e.setBackgroundColor(Color.argb(0, 0, 0, 0));
        setBackgroundColor(Color.argb(0, 0, 0, 0));
        addView(this.e, layoutParams);
    }

    public void setOnDismissCallbackListener(OnDismissCallback onDismissCallback) {
        if (this.e != null) {
            this.e.setCallback(onDismissCallback);
        }
    }

    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof SwipeDismissView) {
                SwipeDismissView swipeDismissView = (SwipeDismissView) childAt;
                childAt.measure(swipeDismissView.getLayoutParams().width, swipeDismissView.getLayoutParams().height);
            } else {
                childAt.measure(i, i2);
            }
        }
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt == this.d) {
                childAt.layout(0, 0, getWidth(), getHeight());
            } else if (childAt == this.e) {
                a(this.e);
                this.e.layout(0, 0, this.e.getMeasuredWidth(), i3);
            }
        }
    }

    private void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, MemoryConstants.GB);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        int i2 = layoutParams.height;
        if (i2 > 0) {
            i2 = MeasureSpec.makeMeasureSpec(i2, MemoryConstants.GB);
        } else {
            i2 = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(i, i2);
    }

    public void onDismiss() {
        removeAllViews();
    }

    public void onEnterAmbient(Bundle bundle) {
        onResume();
    }

    public void onExitAmbient() {
        onPause();
    }
}
