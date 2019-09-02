package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ExpandableListView;
import android.widget.LinearLayout.LayoutParams;

public class DownLoadExpandListView extends ExpandableListView {
    public DownLoadExpandListView(Context context) {
        super(context);
    }

    public DownLoadExpandListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutParams(new LayoutParams(-1, -1));
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
