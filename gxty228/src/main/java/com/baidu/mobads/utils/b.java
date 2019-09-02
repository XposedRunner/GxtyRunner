package com.baidu.mobads.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.baidu.mobads.AppActivity;
import com.baidu.mobads.interfaces.utils.IXAdActivityUtils;
import java.util.List;

public class b implements IXAdActivityUtils {
    public Boolean isFullScreen(Activity activity) {
        if (activity == null) {
            return Boolean.valueOf(false);
        }
        try {
            boolean z;
            if ((activity.getWindow().getAttributes().flags & 1024) == 1024) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    public void showAlertDialog(Activity activity, String str, String str2, String str3, String str4, boolean z, OnClickListener onClickListener, OnClickListener onClickListener2) {
        if (activity != null) {
            try {
                new Builder(activity).setCancelable(z).setTitle(str).setMessage(str2).setPositiveButton(str3, onClickListener).setNegativeButton(str4, onClickListener2).create().show();
            } catch (Throwable e) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
            }
        }
    }

    public boolean webviewMultiProcess(Activity activity) {
        if (activity != null) {
            List queryIntentActivities = activity.getPackageManager().queryIntentActivities(new Intent(activity, AppActivity.class), 0);
            if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                String str = ((ResolveInfo) queryIntentActivities.get(0)).activityInfo.processName;
                if (!TextUtils.isEmpty(str) && (str.contains(":") || str.endsWith("webview"))) {
                    return true;
                }
            }
        }
        return false;
    }
}
