package com.example.gita.gxty.weiget;

import android.view.View;
import android.view.View.OnClickListener;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.Feed;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.s;
import com.lzy.okgo.a;
import com.lzy.okgo.request.PostRequest;
import java.util.List;
import me.shaohui.bottomdialog.BaseBottomDialog;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;

public class FXDialog extends BaseBottomDialog implements OnClickListener {
    private ShareListener a;
    private String b;
    private int c;
    private BaseActivity d;

    public void a(String str) {
        this.b = str;
    }

    public void a(BaseActivity baseActivity, int i) {
        this.d = baseActivity;
        this.c = i;
    }

    public int getLayoutRes() {
        return R.layout.layout_bottom_share;
    }

    public void bindView(View view) {
        view.findViewById(R.id.share_qq).setOnClickListener(this);
        view.findViewById(R.id.share_qzone).setOnClickListener(this);
        view.findViewById(R.id.share_wx).setOnClickListener(this);
        view.findViewById(R.id.share_wx_timeline).setOnClickListener(this);
        this.a = new ShareListener(this) {
            final /* synthetic */ FXDialog a;

            {
                this.a = r1;
            }

            public void shareSuccess() {
                s.a((CharSequence) "分享成功");
                if (this.a.c == 2) {
                    this.a.a();
                }
            }

            public void shareFailure(Exception exception) {
                s.a((CharSequence) "分享失败");
            }

            public void shareCancel() {
                h.b("------shareCancel------------");
            }
        };
    }

    private void a() {
        DataBean a = b.a(null);
        ((PostRequest) ((PostRequest) ((PostRequest) a.b(com.example.gita.gxty.a.a("art/sharedCallBack")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<List<Feed>>>(this, this.d, false) {
            final /* synthetic */ FXDialog a;

            public void a(com.lzy.okgo.model.a<LzyResponse<List<Feed>>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                h.b("------成功------------");
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<List<Feed>>> aVar) {
                h.b("------失败------------");
                super.b(aVar);
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_wx /*2131755621*/:
                ShareUtil.shareImage(getContext(), 3, this.b, this.a);
                break;
            case R.id.share_wx_timeline /*2131755623*/:
                ShareUtil.shareImage(getContext(), 4, this.b, this.a);
                break;
            case R.id.share_qq /*2131755627*/:
                ShareUtil.shareImage(getContext(), 1, this.b, this.a);
                break;
            case R.id.share_qzone /*2131755629*/:
                ShareUtil.shareImage(getContext(), 2, this.b, this.a);
                break;
        }
        dismiss();
    }
}
