package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class RenZhengActivity_ViewBinding implements Unbinder {
    private RenZhengActivity a;
    private View b;
    private View c;
    private View d;

    @UiThread
    public RenZhengActivity_ViewBinding(final RenZhengActivity renZhengActivity, View view) {
        this.a = renZhengActivity;
        renZhengActivity.titleBar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", TitleBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.renzheng_img1, "field 'renzheng_img1' and method 'butterknifeOnItemClick'");
        renZhengActivity.renzheng_img1 = (ImageView) Utils.castView(findRequiredView, R.id.renzheng_img1, "field 'renzheng_img1'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RenZhengActivity_ViewBinding b;

            public void doClick(View view) {
                renZhengActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.renzheng_img2, "field 'renzheng_img2' and method 'butterknifeOnItemClick'");
        renZhengActivity.renzheng_img2 = (ImageView) Utils.castView(findRequiredView, R.id.renzheng_img2, "field 'renzheng_img2'", ImageView.class);
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RenZhengActivity_ViewBinding b;

            public void doClick(View view) {
                renZhengActivity.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.renzheng_btn, "field 'renzheng_btn' and method 'butterknifeOnItemClick'");
        renZhengActivity.renzheng_btn = (TextView) Utils.castView(findRequiredView, R.id.renzheng_btn, "field 'renzheng_btn'", TextView.class);
        this.d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ RenZhengActivity_ViewBinding b;

            public void doClick(View view) {
                renZhengActivity.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        RenZhengActivity renZhengActivity = this.a;
        if (renZhengActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        renZhengActivity.titleBar = null;
        renZhengActivity.renzheng_img1 = null;
        renZhengActivity.renzheng_img2 = null;
        renZhengActivity.renzheng_btn = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
