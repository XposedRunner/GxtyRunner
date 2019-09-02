package com.ajguan.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.ajguan.library.IRefreshHeader;
import com.ajguan.library.R;
import com.ajguan.library.State;

public class SimpleRefreshHeaderView extends FrameLayout implements IRefreshHeader {
    private View arrowIcon;
    private View loadingIcon;
    private Animation rotate_down;
    private Animation rotate_infinite;
    private Animation rotate_up;
    private View successIcon;
    private TextView textView;

    public SimpleRefreshHeaderView(Context context) {
        this(context, null);
    }

    public SimpleRefreshHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.rotate_up = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        this.rotate_down = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
        this.rotate_infinite = AnimationUtils.loadAnimation(context, R.anim.rotate_infinite);
        inflate(context, R.layout.default_refresh_header, this);
        this.textView = (TextView) findViewById(R.id.text);
        this.arrowIcon = findViewById(R.id.arrowIcon);
        this.successIcon = findViewById(R.id.successIcon);
        this.loadingIcon = findViewById(R.id.loadingIcon);
    }

    public void reset() {
        this.textView.setText(getResources().getText(R.string.header_reset));
        this.successIcon.setVisibility(4);
        this.arrowIcon.setVisibility(0);
        this.arrowIcon.clearAnimation();
        this.loadingIcon.setVisibility(4);
        this.loadingIcon.clearAnimation();
    }

    public void pull() {
    }

    public void refreshing() {
        this.arrowIcon.setVisibility(4);
        this.loadingIcon.setVisibility(0);
        this.textView.setText(getResources().getText(R.string.header_refreshing));
        this.arrowIcon.clearAnimation();
        this.loadingIcon.startAnimation(this.rotate_infinite);
    }

    public void onPositionChange(float f, float f2, float f3, boolean z, State state) {
        if (f < f3 && f2 >= f3) {
            Log.i("", ">>>>up");
            if (z && state == State.PULL) {
                this.textView.setText(getResources().getText(R.string.header_pull));
                this.arrowIcon.clearAnimation();
                this.arrowIcon.startAnimation(this.rotate_down);
            }
        } else if (f > f3 && f2 <= f3) {
            Log.i("", ">>>>down");
            if (z && state == State.PULL) {
                this.textView.setText(getResources().getText(R.string.header_pull_over));
                this.arrowIcon.clearAnimation();
                this.arrowIcon.startAnimation(this.rotate_up);
            }
        }
    }

    public void complete() {
        this.loadingIcon.setVisibility(4);
        this.loadingIcon.clearAnimation();
        this.successIcon.setVisibility(0);
        this.textView.setText(getResources().getText(R.string.header_completed));
    }
}
