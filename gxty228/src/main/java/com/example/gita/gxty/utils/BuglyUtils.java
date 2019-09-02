package com.example.gita.gxty.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.AppUtils;
import com.example.gita.gxty.activity.LoginActivity;
import com.example.gita.gxty.activity.MainActivity;
import com.example.gita.gxty.activity.RegisterActivity;
import com.example.gita.gxty.activity.SetActivity;
import com.example.gita.gxty.adapter.FeedAdAdapter;
import com.example.gita.gxty.ram.AdsActivity;
import com.example.gita.gxty.ram.NewMineActivity;
import com.example.gita.gxty.ram.Register2Activity;
import com.example.gita.gxty.ram.SportActivity;
import com.example.gita.gxty.ram.discover.DiscoverActivity;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressAD.NativeExpressADListener;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BuglyUtils {
    public static void a(Application application) {
        boolean z = true;
        try {
            Beta.autoCheckUpgrade = true;
            Beta.canShowApkInfo = true;
            Beta.upgradeCheckPeriod = 60000;
            Beta.initDelay = 3000;
            Beta.canShowUpgradeActs.add(SportActivity.class);
            Beta.canShowUpgradeActs.add(DiscoverActivity.class);
            Beta.canShowUpgradeActs.add(NewMineActivity.class);
            Beta.canShowUpgradeActs.add(LoginActivity.class);
            Beta.canShowUpgradeActs.add(MainActivity.class);
            Beta.canShowUpgradeActs.add(RegisterActivity.class);
            Beta.canShowUpgradeActs.add(Register2Activity.class);
            Beta.canShowUpgradeActs.add(SetActivity.class);
            String packageName = application.getPackageName();
            String a = a(Process.myPid());
            BuglyStrategy buglyStrategy = new BuglyStrategy();
            buglyStrategy.setAppVersion(AppUtils.getAppVersionName());
            if (!(a == null || a.equals(packageName))) {
                z = false;
            }
            buglyStrategy.setUploadProcess(z);
            Bugly.init(application, "7c12112763", h.a, buglyStrategy);
            b(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(Application application) {
        try {
            String b = q.a((Context) application).b();
            if (r.a(b)) {
                b = b.b();
            }
            CrashReport.setUserId(application, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a() {
        new Thread() {
            public void run() {
                try {
                    h.b("检查应用升级！");
                    Beta.checkUpgrade(false, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void b() {
        new Thread() {
            public void run() {
                try {
                    h.b("检查应用升级2！");
                    Beta.checkUpgrade(true, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static String a(int i) {
        Throwable th;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
            try {
                String readLine = bufferedReader2.readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    readLine = readLine.trim();
                }
                if (bufferedReader2 == null) {
                    return readLine;
                }
                try {
                    bufferedReader2.close();
                    return readLine;
                } catch (IOException e) {
                    e.printStackTrace();
                    return readLine;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public static void a(Activity activity, final RelativeLayout relativeLayout, String str) {
        Log.e("adsadsads", "showBanner");
        View bannerView = new BannerView(activity, ADSize.BANNER, "1107792404", str);
        bannerView.setShowClose(true);
        bannerView.setRefresh(20);
        bannerView.setADListener(new AbstractBannerADListener() {
            public void onNoAD(AdError adError) {
                h.b(adError);
            }

            public void onADReceiv() {
                relativeLayout.setVisibility(0);
            }

            public void onADClicked() {
                super.onADClicked();
            }
        });
        relativeLayout.addView(bannerView);
        bannerView.loadAD();
    }

    public static void a(final AdsActivity adsActivity, ViewGroup viewGroup, final TextView textView, final View view) {
        Log.e("adsadsads", "showSplashAD");
        SplashAD splashAD = new SplashAD(adsActivity, viewGroup, textView, "1107792404", "9010837899639538", new SplashADListener() {
            public void onADDismissed() {
                adsActivity.b();
            }

            public void onNoAD(AdError adError) {
                h.b(adError);
                adsActivity.b();
            }

            public void onADPresent() {
                textView.setText("跳过");
                view.setVisibility(8);
            }

            public void onADClicked() {
            }

            public void onADTick(long j) {
            }

            public void onADExposure() {
            }
        }, 0);
    }

    public static void a(Activity activity, final FeedAdAdapter feedAdAdapter, final int i, final boolean z) {
        Log.e("adsadsads", "initNativeExpressAD");
        new NativeExpressAD(activity, new com.qq.e.ads.nativ.ADSize(-1, -2), "1107792404", "2050630991224736", new NativeExpressADListener() {
            public void onNoAD(AdError adError) {
                h.b(adError);
            }

            public void onADLoaded(List<NativeExpressADView> list) {
                h.b("ads_1");
                h.b(list);
                feedAdAdapter.a((NativeExpressADView) list.get(0), i, z);
            }

            public void onRenderFail(NativeExpressADView nativeExpressADView) {
                h.b("ads_2");
            }

            public void onRenderSuccess(NativeExpressADView nativeExpressADView) {
                h.b("ads_3");
            }

            public void onADExposure(NativeExpressADView nativeExpressADView) {
                h.b("ads_4");
            }

            public void onADClicked(NativeExpressADView nativeExpressADView) {
                h.b("ads_5");
            }

            public void onADClosed(NativeExpressADView nativeExpressADView) {
                h.b("ads_6");
            }

            public void onADLeftApplication(NativeExpressADView nativeExpressADView) {
                h.b("ads_8");
            }

            public void onADOpenOverlay(NativeExpressADView nativeExpressADView) {
                h.b("ads_9");
            }

            public void onADCloseOverlay(NativeExpressADView nativeExpressADView) {
                h.b("ads_10");
            }
        }).loadAD(1);
    }
}
