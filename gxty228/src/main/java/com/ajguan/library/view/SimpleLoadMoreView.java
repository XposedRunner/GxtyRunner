package com.ajguan.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.ajguan.library.ILoadMoreView;
import com.ajguan.library.R;
import com.github.ybq.android.spinkit.SpinKitView;

public class SimpleLoadMoreView extends FrameLayout implements ILoadMoreView {
    private SpinKitView spinKitView;
    private TextView tvHitText;
    private View view;

    public SimpleLoadMoreView(Context context) {
        this(context, null);
    }

    public SimpleLoadMoreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.view = inflate(context, R.layout.default_load_more, this);
        this.tvHitText = (TextView) this.view.findViewById(R.id.tv_hit_content);
        this.spinKitView = (SpinKitView) this.view.findViewById(R.id.spin_kit);
    }

    public void reset() {
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(4);
        this.tvHitText.setText("正在加载...");
    }

    public void loading() {
        this.spinKitView.setVisibility(0);
        this.tvHitText.setVisibility(0);
        this.tvHitText.setText("正在加载...");
    }

    public void loadComplete() {
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(0);
        this.tvHitText.setText("加载完成");
    }

    public void loadFail() {
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(0);
        this.tvHitText.setText("加载失败,点击重新加载");
    }

    public void loadNothing() {
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(0);
        this.tvHitText.setText("没有更多可以加载");
    }

    public View getCanClickFailView() {
        return this.view;
    }
}
