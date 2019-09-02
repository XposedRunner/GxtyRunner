package com.example.gita.gxty.weiget.ninegrid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import com.blankj.utilcode.constant.MemoryConstants;
import com.example.gita.gxty.R;

public class RatioImageView extends AppCompatImageView {
    private float a = 0.0f;

    public RatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioImageView);
        this.a = obtainStyledAttributes.getFloat(0, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public RatioImageView(Context context) {
        super(context);
    }

    public void setRatio(float f) {
        this.a = f;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        if (this.a != 0.0f) {
            i2 = MeasureSpec.makeMeasureSpec((int) (((float) size) / this.a), MemoryConstants.GB);
        }
        super.onMeasure(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Drawable drawable;
        switch (motionEvent.getAction()) {
            case 0:
                drawable = getDrawable();
                if (drawable != null) {
                    drawable.mutate().setColorFilter(-7829368, Mode.MULTIPLY);
                    break;
                }
                break;
            case 1:
            case 3:
                drawable = getDrawable();
                if (drawable != null) {
                    drawable.mutate().clearColorFilter();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
