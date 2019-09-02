package com.duudu.abcd.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.util.List;

/* compiled from: PermissionInternalUtils */
public class a {
    public static boolean a(List<Pair> list, Context context) {
        for (Pair pair : list) {
            if (a((String) pair.first, context)) {
                return true;
            }
        }
        return false;
    }

    public static void b(List<Pair> list, Context context) throws Exception {
        int i = 0;
        while (i < list.size()) {
            try {
                a((String) ((Pair) list.get(i)).first, (String) ((Pair) list.get(i)).second, context);
                return;
            } catch (Exception e) {
                if (i == list.size() - 1) {
                    Log.e("TAG", e.getLocalizedMessage());
                }
                i++;
            }
        }
    }

    private static boolean a(String str, Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            Log.e("TAG", e.getLocalizedMessage());
            packageInfo = null;
        }
        if (packageInfo != null) {
            return true;
        }
        return false;
    }

    private static void a(String str, String str2, Context context) {
        if (TextUtils.isEmpty(str2)) {
            b(str, context);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClassName(str, str2);
        if (!(context instanceof Activity)) {
            intent.setFlags(335544320);
        }
        context.startActivity(intent);
    }

    private static void b(String str, Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            if (!(context instanceof Activity)) {
                launchIntentForPackage.addFlags(335544320);
            }
            context.startActivity(launchIntentForPackage);
        }
    }
}
