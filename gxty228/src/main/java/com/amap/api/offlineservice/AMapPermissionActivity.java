package com.amap.api.offlineservice;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.List;

public class AMapPermissionActivity extends Activity {
    private boolean a = true;
    protected String[] needPermissions = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"};

    protected void onResume() {
        try {
            super.onResume();
            if (VERSION.SDK_INT >= 23 && this.a) {
                a(this.needPermissions);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @TargetApi(23)
    private void a(String... strArr) {
        try {
            if (VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
                List b = b(strArr);
                if (b != null && b.size() > 0) {
                    try {
                        String[] strArr2 = (String[]) b.toArray(new String[b.size()]);
                        getClass().getMethod("requestPermissions", new Class[]{String[].class, Integer.TYPE}).invoke(this, new Object[]{strArr2, Integer.valueOf(0)});
                    } catch (Throwable th) {
                    }
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @TargetApi(23)
    private List<String> b(String[] strArr) {
        try {
            List<String> arrayList = new ArrayList();
            if (VERSION.SDK_INT < 23 || getApplicationInfo().targetSdkVersion < 23) {
                return arrayList;
            }
            for (String str : strArr) {
                if (a(str) != 0 || b(str)) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private int a(String str) {
        try {
            return ((Integer) getClass().getMethod("checkSelfPermission", new Class[]{String.class}).invoke(this, new Object[]{str})).intValue();
        } catch (Throwable th) {
            return -1;
        }
    }

    private boolean b(String str) {
        try {
            return ((Boolean) getClass().getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class}).invoke(this, new Object[]{str})).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    private boolean a(int[] iArr) {
        try {
            for (int i : iArr) {
                if (i != 0) {
                    return false;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        try {
            if (VERSION.SDK_INT >= 23 && i == 0 && !a(iArr)) {
                a();
                this.a = false;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() {
        try {
            Builder builder = new Builder(this);
            builder.setTitle("提示");
            builder.setMessage("当前应用缺少必要权限。\\n\\n请点击\\\"设置\\\"-\\\"权限\\\"-打开所需权限");
            builder.setNegativeButton("取消", new OnClickListener(this) {
                final /* synthetic */ AMapPermissionActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        this.a.finish();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            builder.setPositiveButton("设置", new OnClickListener(this) {
                final /* synthetic */ AMapPermissionActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        this.a.b();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b() {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
