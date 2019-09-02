package com.qq.e.ads.nativ;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.NEADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class NativeExpressAD {
    private NEADI a;
    private boolean b;
    private boolean c;
    private List<Integer> d = new ArrayList();
    private boolean e = false;
    private BrowserType f;
    private DownAPPConfirmPolicy g;
    private VideoOption h;

    public interface NativeExpressADListener {
        void onADClicked(NativeExpressADView nativeExpressADView);

        void onADCloseOverlay(NativeExpressADView nativeExpressADView);

        void onADClosed(NativeExpressADView nativeExpressADView);

        void onADExposure(NativeExpressADView nativeExpressADView);

        void onADLeftApplication(NativeExpressADView nativeExpressADView);

        void onADLoaded(List<NativeExpressADView> list);

        void onADOpenOverlay(NativeExpressADView nativeExpressADView);

        void onNoAD(AdError adError);

        void onRenderFail(NativeExpressADView nativeExpressADView);

        void onRenderSuccess(NativeExpressADView nativeExpressADView);
    }

    public static class ADListenerAdapter implements ADListener {
        private NativeExpressADListener a;
        private NativeExpressMediaListener b;

        public ADListenerAdapter(NativeExpressADListener nativeExpressADListener) {
            this.a = nativeExpressADListener;
        }

        public ADListenerAdapter(NativeExpressMediaListener nativeExpressMediaListener) {
            this.b = nativeExpressMediaListener;
        }

        public void onADEvent(ADEvent aDEvent) {
            switch (aDEvent.getType()) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    NativeExpressAD.a(this.a, aDEvent);
                    return;
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    NativeExpressAD.a(this.b, aDEvent);
                    return;
                default:
                    return;
            }
        }
    }

    public NativeExpressAD(Context context, ADSize aDSize, String str, String str2, NativeExpressADListener nativeExpressADListener) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || aDSize == null || context == null) {
            GDTLogger.e(String.format("NativeExpressAD Constructor params error, adSize=%s, appid=%s, posId=%s, context=%s", new Object[]{aDSize, str, str2, context}));
            return;
        }
        this.b = true;
        if (a.a(context)) {
            this.c = true;
            final Context context2 = context;
            final String str3 = str;
            final ADSize aDSize2 = aDSize;
            final String str4 = str2;
            final NativeExpressADListener nativeExpressADListener2 = nativeExpressADListener;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
                final /* synthetic */ NativeExpressAD f;

                public void run() {
                    if (GDTADManager.getInstance().initWith(context2, str3)) {
                        try {
                            final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                private /* synthetic */ AnonymousClass1 b;

                                /* JADX WARNING: inconsistent code. */
                                /* Code decompiled incorrectly, please refer to instructions dump. */
                                public void run() {
                                    /*
                                    r9 = this;
                                    r8 = 1;
                                    r0 = r0;	 Catch:{ Throwable -> 0x00a2 }
                                    if (r0 == 0) goto L_0x00b0;
                                L_0x0005:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r6 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r2;	 Catch:{ Throwable -> 0x00a2 }
                                    r2 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r2 = r4;	 Catch:{ Throwable -> 0x00a2 }
                                    r3 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r3 = r3;	 Catch:{ Throwable -> 0x00a2 }
                                    r4 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r4 = r5;	 Catch:{ Throwable -> 0x00a2 }
                                    r5 = new com.qq.e.ads.nativ.NativeExpressAD$ADListenerAdapter;	 Catch:{ Throwable -> 0x00a2 }
                                    r7 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r7 = r6;	 Catch:{ Throwable -> 0x00a2 }
                                    r5.<init>(r7);	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.getNativeExpressADDelegate(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2 }
                                    r6.a = r0;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = 1;
                                    r0.e = true;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    if (r0 == 0) goto L_0x004c;
                                L_0x003d:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r1.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r1.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r0.setBrowserType(r1);	 Catch:{ Throwable -> 0x00a2 }
                                L_0x004c:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.g;	 Catch:{ Throwable -> 0x00a2 }
                                    if (r0 == 0) goto L_0x0065;
                                L_0x0056:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r1.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r1.g;	 Catch:{ Throwable -> 0x00a2 }
                                    r0.setDownAPPConfirmPolicy(r1);	 Catch:{ Throwable -> 0x00a2 }
                                L_0x0065:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.h;	 Catch:{ Throwable -> 0x00a2 }
                                    if (r0 == 0) goto L_0x007e;
                                L_0x006f:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r1.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r1.h;	 Catch:{ Throwable -> 0x00a2 }
                                    r0.setVideoOption(r1);	 Catch:{ Throwable -> 0x00a2 }
                                L_0x007e:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a2 }
                                    r1 = r0.iterator();	 Catch:{ Throwable -> 0x00a2 }
                                L_0x008a:
                                    r0 = r1.hasNext();	 Catch:{ Throwable -> 0x00a2 }
                                    if (r0 == 0) goto L_0x00b0;
                                L_0x0090:
                                    r0 = r1.next();	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = (java.lang.Integer) r0;	 Catch:{ Throwable -> 0x00a2 }
                                    r2 = r9.b;	 Catch:{ Throwable -> 0x00a2 }
                                    r2 = r2.f;	 Catch:{ Throwable -> 0x00a2 }
                                    r0 = r0.intValue();	 Catch:{ Throwable -> 0x00a2 }
                                    r2.loadAD(r0);	 Catch:{ Throwable -> 0x00a2 }
                                    goto L_0x008a;
                                L_0x00a2:
                                    r0 = move-exception;
                                    r1 = "Exception while init Native Express AD Core";
                                    com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x00b8 }
                                    r0 = r9.b;
                                    r0 = r0.f;
                                    r0.e = true;
                                L_0x00af:
                                    return;
                                L_0x00b0:
                                    r0 = r9.b;
                                    r0 = r0.f;
                                    r0.e = true;
                                    goto L_0x00af;
                                L_0x00b8:
                                    r0 = move-exception;
                                    r1 = r9.b;
                                    r1 = r1.f;
                                    r1.e = true;
                                    throw r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.nativ.NativeExpressAD.1.1.run():void");
                                }
                            });
                            return;
                        } catch (Throwable th) {
                            GDTLogger.e("Exception while init Native Express AD plugin", th);
                            return;
                        }
                    }
                    GDTLogger.e("Fail to init ADManager");
                }
            });
            return;
        }
        GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
    }

    static /* synthetic */ void a(NativeExpressADListener nativeExpressADListener, ADEvent aDEvent) {
        if (nativeExpressADListener == null) {
            GDTLogger.i("No DevADListener Bound");
            return;
        }
        switch (aDEvent.getType()) {
            case 1:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                    nativeExpressADListener.onNoAD(a.a(((Integer) aDEvent.getParas()[0]).intValue()));
                    return;
                } else {
                    GDTLogger.e("AdEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 2:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                    nativeExpressADListener.onADLoaded((List) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 3:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onRenderFail((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 4:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onRenderSuccess((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 5:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onADExposure((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 6:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onADClicked((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 7:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onADClosed((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 8:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onADLeftApplication((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 9:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onADOpenOverlay((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 10:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressADListener.onADCloseOverlay((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            default:
                return;
        }
    }

    static /* synthetic */ void a(NativeExpressMediaListener nativeExpressMediaListener, ADEvent aDEvent) {
        if (nativeExpressMediaListener == null) {
            GDTLogger.i("No media listener");
            return;
        }
        switch (aDEvent.getType()) {
            case 11:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressMediaListener.onVideoInit((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 12:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressMediaListener.onVideoLoading((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 13:
                if (aDEvent.getParas().length == 2 && (aDEvent.getParas()[0] instanceof NativeExpressADView) && (aDEvent.getParas()[1] instanceof Integer)) {
                    nativeExpressMediaListener.onVideoReady((NativeExpressADView) aDEvent.getParas()[0], (long) ((Integer) aDEvent.getParas()[1]).intValue());
                    return;
                } else {
                    GDTLogger.e("NativeMedia ADEvent Paras error!");
                    return;
                }
            case 14:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressMediaListener.onVideoStart((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 15:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressMediaListener.onVideoPause((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 16:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressMediaListener.onVideoComplete((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 17:
                if (aDEvent.getParas().length == 2 && (aDEvent.getParas()[0] instanceof NativeExpressADView) && (aDEvent.getParas()[1] instanceof Integer)) {
                    nativeExpressMediaListener.onVideoError((NativeExpressADView) aDEvent.getParas()[0], a.a(((Integer) aDEvent.getParas()[1]).intValue()));
                    return;
                } else {
                    GDTLogger.e("Native express media event paras error!");
                    return;
                }
            case 18:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressMediaListener.onVideoPageOpen((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            case 19:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                    nativeExpressMediaListener.onVideoPageClose((NativeExpressADView) aDEvent.getParas()[0]);
                    return;
                } else {
                    GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                    return;
                }
            default:
                return;
        }
    }

    public void loadAD(int i) {
        if (!this.b || !this.c) {
            GDTLogger.e("AD init Params OR Context error, details in logs produced while init NativeExpressAD");
        } else if (!this.e) {
            this.d.add(Integer.valueOf(i));
        } else if (this.a != null) {
            this.a.loadAd(i);
        } else {
            GDTLogger.e("Native Express AD Init error, see more logs");
        }
    }

    public void setBrowserType(BrowserType browserType) {
        this.f = browserType;
        if (this.a != null && browserType != null) {
            this.a.setBrowserType(browserType.value());
        }
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.g = downAPPConfirmPolicy;
        if (this.a != null && downAPPConfirmPolicy != null) {
            this.a.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }

    public void setVideoOption(VideoOption videoOption) {
        this.h = videoOption;
        if (this.a != null && videoOption != null) {
            this.a.setVideoOption(videoOption);
        }
    }
}
