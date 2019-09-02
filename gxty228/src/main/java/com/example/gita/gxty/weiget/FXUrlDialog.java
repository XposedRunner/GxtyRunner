package com.example.gita.gxty.weiget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.s;
import me.shaohui.bottomdialog.BaseBottomDialog;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;

public class FXUrlDialog extends BaseBottomDialog implements OnClickListener {
    private ShareListener a;
    private String b;
    private String c;
    private String d;

    public int getLayoutRes() {
        return R.layout.layout_bottom_share;
    }

    public void bindView(View view) {
        view.findViewById(R.id.share_qq).setOnClickListener(this);
        view.findViewById(R.id.share_qzone).setOnClickListener(this);
        view.findViewById(R.id.share_wx).setOnClickListener(this);
        view.findViewById(R.id.share_wx_timeline).setOnClickListener(this);
        this.a = new ShareListener(this) {
            final /* synthetic */ FXUrlDialog a;

            {
                this.a = r1;
            }

            public void shareSuccess() {
                s.a((CharSequence) "分享成功");
            }

            public void shareFailure(Exception exception) {
                s.a((CharSequence) "分享失败");
            }

            public void shareCancel() {
            }
        };
    }

    public void onClick(View view) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.mipmap.my_icon);
        switch (view.getId()) {
            case R.id.share_wx /*2131755621*/:
                ShareUtil.shareMedia(getContext(), 3, this.c, this.d, this.b, decodeResource, this.a);
                break;
            case R.id.share_wx_timeline /*2131755623*/:
                ShareUtil.shareMedia(getContext(), 4, this.c, this.d, this.b, decodeResource, this.a);
                break;
            case R.id.share_qq /*2131755627*/:
                ShareUtil.shareMedia(getContext(), 1, this.c, this.d, this.b, decodeResource, this.a);
                break;
            case R.id.share_qzone /*2131755629*/:
                ShareUtil.shareMedia(getContext(), 2, this.c, this.d, this.b, decodeResource, this.a);
                break;
        }
        dismiss();
    }
}
