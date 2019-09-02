package me.shaohui.shareutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.util.Locale;
import me.shaohui.shareutil.share.ShareImageObject;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.instance.DefaultShareInstance;
import me.shaohui.shareutil.share.instance.QQShareInstance;
import me.shaohui.shareutil.share.instance.ShareInstance;
import me.shaohui.shareutil.share.instance.WeiboShareInstance;
import me.shaohui.shareutil.share.instance.WxShareInstance;

public class ShareUtil {
    public static final int TYPE = 798;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_MEDIA = 3;
    private static final int TYPE_TEXT = 2;
    private static int mPlatform;
    private static ShareImageObject mShareImageObject;
    private static ShareInstance mShareInstance;
    public static ShareListener mShareListener;
    private static String mSummary;
    private static String mTargetUrl;
    private static String mText;
    private static String mTitle;
    private static int mType;

    static void action(Activity activity) {
        mShareInstance = getShareInstance(mPlatform, activity);
        if (mShareListener == null) {
            activity.finish();
        } else if (mShareInstance.isInstall(activity)) {
            switch (mType) {
                case 1:
                    mShareInstance.shareImage(mPlatform, mShareImageObject, activity, mShareListener);
                    return;
                case 2:
                    mShareInstance.shareText(mPlatform, mText, activity, mShareListener);
                    return;
                case 3:
                    mShareInstance.shareMedia(mPlatform, mTitle, mTargetUrl, mSummary, mShareImageObject, activity, mShareListener);
                    return;
                default:
                    return;
            }
        } else {
            mShareListener.shareFailure(new Exception("The application is not install"));
            activity.finish();
        }
    }

    public static void shareText(Context context, int i, String str, ShareListener shareListener) {
        mType = 2;
        mText = str;
        mPlatform = i;
        mShareListener = buildProxyListener(shareListener);
        context.startActivity(_ShareActivity.newInstance(context, TYPE));
    }

    public static void shareImage(Context context, int i, String str, ShareListener shareListener) {
        mType = 1;
        mPlatform = i;
        mShareImageObject = new ShareImageObject(str);
        mShareListener = buildProxyListener(shareListener);
        context.startActivity(_ShareActivity.newInstance(context, TYPE));
    }

    public static void shareImage(Context context, int i, Bitmap bitmap, ShareListener shareListener) {
        mType = 1;
        mPlatform = i;
        mShareImageObject = new ShareImageObject(bitmap);
        mShareListener = buildProxyListener(shareListener);
        context.startActivity(_ShareActivity.newInstance(context, TYPE));
    }

    public static void shareMedia(Context context, int i, String str, String str2, String str3, Bitmap bitmap, ShareListener shareListener) {
        mType = 3;
        mPlatform = i;
        mShareImageObject = new ShareImageObject(bitmap);
        mSummary = str2;
        mTargetUrl = str3;
        mTitle = str;
        mShareListener = buildProxyListener(shareListener);
        context.startActivity(_ShareActivity.newInstance(context, TYPE));
    }

    public static void shareMedia(Context context, int i, String str, String str2, String str3, String str4, ShareListener shareListener) {
        mType = 3;
        mPlatform = i;
        mShareImageObject = new ShareImageObject(str4);
        mSummary = str2;
        mTargetUrl = str3;
        mTitle = str;
        mShareListener = buildProxyListener(shareListener);
        context.startActivity(_ShareActivity.newInstance(context, TYPE));
    }

    private static ShareListener buildProxyListener(ShareListener shareListener) {
        return new ShareListenerProxy(shareListener);
    }

    public static void handleResult(Intent intent) {
        if (mShareInstance != null && intent != null) {
            mShareInstance.handleResult(intent);
        } else if (intent != null) {
            ShareLogger.e("Unknown error");
        } else if (mPlatform != 5) {
            ShareLogger.e("Handle the result, but the data is null, please check you app id");
        }
    }

    private static ShareInstance getShareInstance(int i, Context context) {
        switch (i) {
            case 1:
            case 2:
                return new QQShareInstance(context, ShareManager.CONFIG.getQqId());
            case 3:
            case 4:
                return new WxShareInstance(context, ShareManager.CONFIG.getWxId());
            case 5:
                return new WeiboShareInstance(context, ShareManager.CONFIG.getWeiboId());
            default:
                return new DefaultShareInstance();
        }
    }

    public static void recycle() {
        mTitle = null;
        mSummary = null;
        mShareListener = null;
        if (!(mShareImageObject == null || mShareImageObject.getBitmap() == null || mShareImageObject.getBitmap().isRecycled())) {
            mShareImageObject.getBitmap().recycle();
        }
        mShareImageObject = null;
        if (mShareInstance != null) {
            mShareInstance.recycle();
        }
        mShareInstance = null;
    }

    public static boolean isInstalled(int i, Context context) {
        switch (i) {
            case 0:
                return true;
            case 1:
            case 2:
                return isQQInstalled(context);
            case 3:
            case 4:
                return isWeiXinInstalled(context);
            case 5:
                return isWeiBoInstalled(context);
            default:
                return false;
        }
    }

    @Deprecated
    public static boolean isQQInstalled(@NonNull Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
            if (TextUtils.equals(packageInfo.packageName.toLowerCase(Locale.getDefault()), "com.tencent.mobileqq")) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean isWeiBoInstalled(@NonNull Context context) {
        return WeiboShareSDK.createWeiboAPI(context, ShareManager.CONFIG.getWeiboId()).isWeiboAppInstalled();
    }

    @Deprecated
    public static boolean isWeiXinInstalled(Context context) {
        return WXAPIFactory.createWXAPI(context, ShareManager.CONFIG.getWxId(), true).isWXAppInstalled();
    }
}
