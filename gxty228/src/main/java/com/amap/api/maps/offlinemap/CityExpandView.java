package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ExpandableListView;
import android.widget.LinearLayout.LayoutParams;

public class CityExpandView extends ExpandableListView {
    public CityExpandView(Context context) {
        this(context, null);
    }

    public CityExpandView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, -1);
        setLayoutParams(new LayoutParams(-1, -1));
    }

    protected void onMeasure(int i, int i2) {
        System.currentTimeMillis();
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        System.currentTimeMillis();
    }
}
