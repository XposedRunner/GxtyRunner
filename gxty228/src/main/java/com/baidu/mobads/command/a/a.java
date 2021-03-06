package com.baidu.mobads.command.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.Toast;
import com.baidu.mobads.command.b;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdResource;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.d;
import com.baidu.mobads.utils.k;
import java.io.File;
import java.net.URL;

public class a extends b {
    public a(IXNonLinearAdSlot iXNonLinearAdSlot, IXAdInstanceInfo iXAdInstanceInfo, IXAdResource iXAdResource) {
        super(iXNonLinearAdSlot, iXAdInstanceInfo, iXAdResource);
    }

    public void a() {
        boolean z = false;
        d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        XAdSDKFoundationFacade.getInstance().getIoUtils();
        IXAdURIUitls uRIUitls = XAdSDKFoundationFacade.getInstance().getURIUitls();
        IXAdSystemUtils systemUtils = XAdSDKFoundationFacade.getInstance().getSystemUtils();
        try {
            com.baidu.mobads.command.a a;
            String appPackageName = this.c.getAppPackageName();
            this.e.i("XAdDownloadAPKCommand", "download pkg = " + appPackageName);
            if ((appPackageName == null || appPackageName.equals("")) && !TextUtils.isEmpty(this.c.getOriginClickUrl())) {
                this.e.i("XAdDownloadAPKCommand", "start to download but package is empty");
                appPackageName = commonUtils.getMD5(this.c.getOriginClickUrl());
            }
            IOAdDownloader adsApkDownloader = com.baidu.mobads.openad.b.d.a(this.a).getAdsApkDownloader(appPackageName);
            com.baidu.mobads.openad.b.b a2 = com.baidu.mobads.openad.b.b.a(appPackageName);
            if (a2 == null || adsApkDownloader == null) {
                if (adsApkDownloader != null) {
                    adsApkDownloader.cancel();
                    adsApkDownloader.removeObservers();
                }
                com.baidu.mobads.openad.b.b.b(appPackageName);
                com.baidu.mobads.openad.b.d.a(this.a).removeAdsApkDownloader(appPackageName);
            } else {
                a = a2.a();
                DownloadStatus state = adsApkDownloader.getState();
                this.e.d("XAdDownloadAPKCommand", "startDownload>> downloader exist: state=" + state);
                if (state == DownloadStatus.CANCELLED || state == DownloadStatus.ERROR || state == DownloadStatus.PAUSED) {
                    adsApkDownloader.resume();
                    uRIUitls.pintHttpInNewThread(this.c.getClickThroughUrl());
                    return;
                } else if (state == DownloadStatus.COMPLETED) {
                    if (a(this.a, a)) {
                        uRIUitls.pintHttpInNewThread(this.c.getClickThroughUrl());
                        b(a);
                        return;
                    }
                    adsApkDownloader.cancel();
                    adsApkDownloader.removeObservers();
                    com.baidu.mobads.openad.b.b.b(appPackageName);
                    com.baidu.mobads.openad.b.d.a(this.a).removeAdsApkDownloader(appPackageName);
                } else if (state == DownloadStatus.DOWNLOADING || state == DownloadStatus.INITING) {
                    commonUtils.sendDownloadAdLog(this.a, 529, "downloading", this.b != null ? this.b.getProdInfo().getProdType() : "", appPackageName, commonUtils.getAppId(this.a), this.b != null ? this.b.getAdRequestInfo().getApid() : "", systemUtils.getPhoneOSBrand(), Build.MODEL, VERSION.RELEASE, VERSION.SDK_INT);
                    a(this.a, adsApkDownloader.getTitle() + adsApkDownloader.getState().getMessage(), 0, Boolean.valueOf(this.c.isPopNotif()));
                    return;
                }
            }
            a = com.baidu.mobads.command.a.a(this.a, appPackageName);
            if (a != null) {
                if (a.g == DownloadStatus.COMPLETED && a(this.a, a)) {
                    b(a);
                    return;
                }
                uRIUitls.pintHttpInNewThread(this.c.getClickThroughUrl());
            } else if (b()) {
                commonUtils.sendDownloadAdLog(this.a, 529, "alreadyinstalled1", this.b != null ? this.b.getProdInfo().getProdType() : "", appPackageName, commonUtils.getAppId(this.a), this.b != null ? this.b.getAdRequestInfo().getApid() : "", systemUtils.getPhoneOSBrand(), Build.MODEL, VERSION.RELEASE, VERSION.SDK_INT);
                XAdSDKFoundationFacade.getInstance().getPackageUtils().openApp(this.a, this.c.getAppPackageName());
                uRIUitls.pintHttpInNewThread(this.c.getClickThroughUrl());
                com.baidu.mobads.production.b.f().getXMonitorActivation(this.a, this.e).startMonitor();
                return;
            } else {
                String str;
                String appName = this.c.getAppName();
                if (appName == null || appName.equals("")) {
                    appName = this.c.getTitle();
                    if (appName == null || appName.equals("")) {
                        str = "您点击的应用";
                        a = new com.baidu.mobads.command.a(appPackageName, str);
                        a.a(this.c.getQueryKey(), this.c.getAdId(), this.c.getClickThroughUrl(), this.c.isAutoOpen());
                        a.m = this.c.isPopNotif();
                        a.a(commonUtils.getMD5(a.j) + ".apk", k.a(this.a));
                        if (this.b != null) {
                            a.b(this.b.getAdRequestInfo().getApid(), this.b.getProdInfo().getProdType());
                        }
                        a.f = com.baidu.mobads.openad.b.b.c(appPackageName);
                        if (!this.c.isActionOnlyWifi()) {
                            z = true;
                        }
                        a.s = z;
                        a.a(System.currentTimeMillis());
                        a.b(this.c.getAppSize());
                        a.a(this.c.isTooLarge());
                    }
                }
                str = appName;
                a = new com.baidu.mobads.command.a(appPackageName, str);
                a.a(this.c.getQueryKey(), this.c.getAdId(), this.c.getClickThroughUrl(), this.c.isAutoOpen());
                a.m = this.c.isPopNotif();
                a.a(commonUtils.getMD5(a.j) + ".apk", k.a(this.a));
                if (this.b != null) {
                    a.b(this.b.getAdRequestInfo().getApid(), this.b.getProdInfo().getProdType());
                }
                a.f = com.baidu.mobads.openad.b.b.c(appPackageName);
                if (this.c.isActionOnlyWifi()) {
                    z = true;
                }
                a.s = z;
                a.a(System.currentTimeMillis());
                a.b(this.c.getAppSize());
                a.a(this.c.isTooLarge());
            }
            a.t = System.currentTimeMillis();
            IOAdDownloader createAdsApkDownloader = XAdSDKFoundationFacade.getInstance().getDownloaderManager(this.a).createAdsApkDownloader(new URL(a.j), a.c, a.b, 3, a.a, a.i);
            if (!(!this.c.getAPOOpen() || this.c.getPage() == null || this.c.getPage().equals(""))) {
                a.w = true;
                a.x = this.c.getPage();
            }
            createAdsApkDownloader.addObserver(new com.baidu.mobads.openad.b.b(this.a, a));
            if (a.s || !systemUtils.is3GConnected(this.a).booleanValue()) {
                commonUtils.sendDownloadAdLog(this.a, 527, "realstart", this.b != null ? this.b.getProdInfo().getProdType() : "", appPackageName, commonUtils.getAppId(this.a), this.b != null ? this.b.getAdRequestInfo().getApid() : "", systemUtils.getPhoneOSBrand(), Build.MODEL, VERSION.RELEASE, VERSION.SDK_INT);
                createAdsApkDownloader.start();
                return;
            }
            commonUtils.sendDownloadAdLog(this.a, 529, "waitwifi", this.b != null ? this.b.getProdInfo().getProdType() : "", appPackageName, commonUtils.getAppId(this.a), this.b != null ? this.b.getAdRequestInfo().getApid() : "", systemUtils.getPhoneOSBrand(), Build.MODEL, VERSION.RELEASE, VERSION.SDK_INT);
            createAdsApkDownloader.pause();
            a(this.a, createAdsApkDownloader.getTitle() + " 将在连入Wifi后开始下载", 0, Boolean.valueOf(this.c.isPopNotif()));
        } catch (Throwable e) {
            this.e.e("XAdDownloadAPKCommand", e);
            com.baidu.mobads.c.a.a().a("ad app download failed: " + e.toString());
        }
    }

    public void a(Context context, String str, int i, Boolean bool) {
        if (bool.booleanValue()) {
            Toast.makeText(context, str, i).show();
        }
    }

    private boolean b() {
        return XAdSDKFoundationFacade.getInstance().getPackageUtils().isInstalled(this.a, this.c.getAppPackageName());
    }

    protected boolean a(Context context, com.baidu.mobads.command.a aVar) {
        boolean isInstalled = XAdSDKFoundationFacade.getInstance().getPackageUtils().isInstalled(context, aVar.i);
        d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        IXAdSystemUtils systemUtils = XAdSDKFoundationFacade.getInstance().getSystemUtils();
        if (isInstalled) {
            commonUtils.sendDownloadAdLog(this.a, 529, "alreadyinstalled", this.b != null ? this.b.getProdInfo().getProdType() : "", aVar.i, commonUtils.getAppId(this.a), this.b != null ? this.b.getAdRequestInfo().getApid() : "", systemUtils.getPhoneOSBrand(), Build.MODEL, VERSION.RELEASE, VERSION.SDK_INT);
            XAdSDKFoundationFacade.getInstance().getPackageUtils().openApp(context, aVar.i);
            return true;
        }
        commonUtils.sendDownloadAdLog(this.a, 529, "alreadydownloaded", this.b != null ? this.b.getProdInfo().getProdType() : "", aVar.i, commonUtils.getAppId(this.a), this.b != null ? this.b.getAdRequestInfo().getApid() : "", systemUtils.getPhoneOSBrand(), Build.MODEL, VERSION.RELEASE, VERSION.SDK_INT);
        String str = aVar.c + aVar.b;
        File file = new File(str);
        if (!file.exists() || file.length() <= 0) {
            return false;
        }
        XAdSDKFoundationFacade.getInstance().getPackageUtils().b(context, str);
        return true;
    }

    private void b(com.baidu.mobads.command.a aVar) {
        if (com.baidu.mobads.production.b.f() != null) {
            IXAppInfo a = a(aVar);
            if (a != null) {
                com.baidu.mobads.production.b.f().getXMonitorActivation(this.a, this.e).addAppInfoForMonitor(a);
            } else {
                this.e.e("addAppInfoForMonitor error, appInfo is null");
            }
        }
    }

    public static IXAppInfo a(com.baidu.mobads.command.a aVar) {
        if (aVar == null) {
            return null;
        }
        IXAdContainerFactory f = com.baidu.mobads.production.b.f();
        if (f == null) {
            return null;
        }
        IXAppInfo createAppInfo = f.createAppInfo();
        createAppInfo.setAdId(aVar.g());
        createAppInfo.setAppSize(aVar.e());
        createAppInfo.setClickTime(aVar.c());
        createAppInfo.setPackageName(aVar.d());
        createAppInfo.setQk(aVar.h());
        createAppInfo.setProd(aVar.i());
        createAppInfo.setTooLarge(aVar.f());
        return createAppInfo;
    }
}
