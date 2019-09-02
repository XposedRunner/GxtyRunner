package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.blankj.utilcode.constant.PermissionConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class PermissionUtils {
    private static final List<String> PERMISSIONS = getPermissions();
    private static PermissionUtils sInstance;
    private FullCallback mFullCallback;
    private OnRationaleListener mOnRationaleListener;
    private Set<String> mPermissions = new LinkedHashSet();
    private List<String> mPermissionsDenied;
    private List<String> mPermissionsDeniedForever;
    private List<String> mPermissionsGranted;
    private List<String> mPermissionsRequest;
    private SimpleCallback mSimpleCallback;
    private ThemeCallback mThemeCallback;

    public interface FullCallback {
        void onDenied(List<String> list, List<String> list2);

        void onGranted(List<String> list);
    }

    public interface OnRationaleListener {

        public interface ShouldRequest {
            void again(boolean z);
        }

        void rationale(ShouldRequest shouldRequest);
    }

    @RequiresApi(api = 23)
    public static class PermissionActivity extends Activity {
        public static void start(Context context) {
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
            context.startActivity(intent);
        }

        protected void onCreate(@Nullable Bundle bundle) {
            getWindow().addFlags(262160);
            if (PermissionUtils.sInstance == null) {
                super.onCreate(bundle);
                Log.e("PermissionUtils", "request permissions failed");
                finish();
                return;
            }
            if (PermissionUtils.sInstance.mThemeCallback != null) {
                PermissionUtils.sInstance.mThemeCallback.onActivityCreate(this);
            }
            super.onCreate(bundle);
            if (PermissionUtils.sInstance.rationale((Activity) this)) {
                finish();
            } else if (PermissionUtils.sInstance.mPermissionsRequest != null) {
                int size = PermissionUtils.sInstance.mPermissionsRequest.size();
                if (size <= 0) {
                    finish();
                } else {
                    requestPermissions((String[]) PermissionUtils.sInstance.mPermissionsRequest.toArray(new String[size]), 1);
                }
            }
        }

        public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
            if (strArr == null) {
                throw new NullPointerException("Argument 'permissions' of type String[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else if (iArr == null) {
                throw new NullPointerException("Argument 'grantResults' of type int[] (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else {
                PermissionUtils.sInstance.onRequestPermissionsResult(this);
                finish();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            finish();
            return true;
        }
    }

    public interface SimpleCallback {
        void onDenied();

        void onGranted();
    }

    public interface ThemeCallback {
        void onActivityCreate(Activity activity);
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    public static List<String> getPermissions(String str) {
        try {
            return Arrays.asList(Utils.getApp().getPackageManager().getPackageInfo(str, 4096).requestedPermissions);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static boolean isGranted(String... strArr) {
        for (String isGranted : strArr) {
            if (!isGranted(isGranted)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGranted(String str) {
        return VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(Utils.getApp(), str) == 0;
    }

    public static void launchAppDetailsSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        Utils.getApp().startActivity(intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH));
    }

    public static PermissionUtils permission(String... strArr) {
        return new PermissionUtils(strArr);
    }

    private PermissionUtils(String... strArr) {
        for (String permissions : strArr) {
            for (Object obj : PermissionConstants.getPermissions(permissions)) {
                if (PERMISSIONS.contains(obj)) {
                    this.mPermissions.add(obj);
                }
            }
        }
        sInstance = this;
    }

    public PermissionUtils rationale(OnRationaleListener onRationaleListener) {
        this.mOnRationaleListener = onRationaleListener;
        return this;
    }

    public PermissionUtils callback(SimpleCallback simpleCallback) {
        this.mSimpleCallback = simpleCallback;
        return this;
    }

    public PermissionUtils callback(FullCallback fullCallback) {
        this.mFullCallback = fullCallback;
        return this;
    }

    public PermissionUtils theme(ThemeCallback themeCallback) {
        this.mThemeCallback = themeCallback;
        return this;
    }

    public void request() {
        this.mPermissionsGranted = new ArrayList();
        this.mPermissionsRequest = new ArrayList();
        if (VERSION.SDK_INT < 23) {
            this.mPermissionsGranted.addAll(this.mPermissions);
            requestCallback();
            return;
        }
        for (String str : this.mPermissions) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsRequest.add(str);
            }
        }
        if (this.mPermissionsRequest.isEmpty()) {
            requestCallback();
        } else {
            startPermissionActivity();
        }
    }

    @RequiresApi(api = 23)
    private void startPermissionActivity() {
        this.mPermissionsDenied = new ArrayList();
        this.mPermissionsDeniedForever = new ArrayList();
        PermissionActivity.start(Utils.getApp());
    }

    @RequiresApi(api = 23)
    private boolean rationale(Activity activity) {
        if (this.mOnRationaleListener == null) {
            return false;
        }
        boolean z;
        for (String shouldShowRequestPermissionRationale : this.mPermissionsRequest) {
            if (activity.shouldShowRequestPermissionRationale(shouldShowRequestPermissionRationale)) {
                getPermissionsStatus(activity);
                this.mOnRationaleListener.rationale(new ShouldRequest() {
                    public void again(boolean z) {
                        if (z) {
                            PermissionUtils.this.startPermissionActivity();
                        } else {
                            PermissionUtils.this.requestCallback();
                        }
                    }
                });
                z = true;
                break;
            }
        }
        z = false;
        this.mOnRationaleListener = null;
        return z;
    }

    private void getPermissionsStatus(Activity activity) {
        for (String str : this.mPermissionsRequest) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsDenied.add(str);
                if (!activity.shouldShowRequestPermissionRationale(str)) {
                    this.mPermissionsDeniedForever.add(str);
                }
            }
        }
    }

    private void requestCallback() {
        if (this.mSimpleCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissions.size() == this.mPermissionsGranted.size()) {
                this.mSimpleCallback.onGranted();
            } else if (!this.mPermissionsDenied.isEmpty()) {
                this.mSimpleCallback.onDenied();
            }
            this.mSimpleCallback = null;
        }
        if (this.mFullCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissions.size() == this.mPermissionsGranted.size()) {
                this.mFullCallback.onGranted(this.mPermissionsGranted);
            } else if (!this.mPermissionsDenied.isEmpty()) {
                this.mFullCallback.onDenied(this.mPermissionsDeniedForever, this.mPermissionsDenied);
            }
            this.mFullCallback = null;
        }
        this.mOnRationaleListener = null;
        this.mThemeCallback = null;
    }

    private void onRequestPermissionsResult(Activity activity) {
        getPermissionsStatus(activity);
        requestCallback();
    }
}
