package com.example.gita.gxty.utils;

import android.app.Application;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.weiget.FXDialog;
import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;

/* compiled from: FXUtils */
public class e {
    public static void a(Application application) {
        try {
            ShareManager.init(ShareConfig.instance().qqId("1106312670").wxId("wx441a0915e67468d3").wxSecret("b03d593ade436bff414f202a6509b08d"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(BaseActivity baseActivity, String str, int i) {
        FXDialog fXDialog = new FXDialog();
        fXDialog.a(baseActivity, i);
        fXDialog.a(str);
        fXDialog.show(baseActivity.getSupportFragmentManager());
    }
}
