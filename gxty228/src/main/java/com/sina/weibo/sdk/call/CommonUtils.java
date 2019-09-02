package com.sina.weibo.sdk.call;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import cn.jiguang.net.HttpUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ExceptionMsg;
import java.util.HashMap;

class CommonUtils {
    CommonUtils() {
    }

    public static String buildUriQuery(HashMap<String, String> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : hashMap.keySet()) {
            String str2 = (String) hashMap.get(str);
            if (str2 != null) {
                stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR).append(str).append(HttpUtils.EQUAL_SIGN).append(str2);
            }
        }
        return stringBuilder.toString().replaceFirst(HttpUtils.PARAMETERS_SEPARATOR, HttpUtils.URL_AND_PARA_SEPARATOR);
    }

    public static void openWeiboActivity(Context context, String str, String str2, String str3) throws WeiboNotInstalledException {
        Intent intent;
        if (str3 != null) {
            try {
                intent = new Intent();
                intent.setAction(str);
                intent.setData(Uri.parse(str2));
                intent.setPackage(str3);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                if (str3 != null) {
                    try {
                        intent = new Intent();
                        intent.setAction(str);
                        intent.setData(Uri.parse(str2));
                        context.startActivity(intent);
                        return;
                    } catch (ActivityNotFoundException e2) {
                        throw new WeiboNotInstalledException(ExceptionMsg.WEIBO_NOT_INSTALLED);
                    }
                }
                throw new WeiboNotInstalledException(ExceptionMsg.WEIBO_NOT_INSTALLED);
            }
        }
        intent = new Intent();
        intent.setAction(str);
        intent.setData(Uri.parse(str2));
        context.startActivity(intent);
    }

    public static void openWeiboActivity(Context context, String str, String str2) throws WeiboNotInstalledException {
        try {
            Intent intent = new Intent();
            intent.setAction(str);
            intent.setData(Uri.parse(str2));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            throw new WeiboNotInstalledException(ExceptionMsg.WEIBO_NOT_INSTALLED);
        }
    }
}
