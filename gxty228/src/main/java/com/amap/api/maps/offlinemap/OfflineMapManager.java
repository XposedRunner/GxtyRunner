package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.os.Handler;
import com.amap.api.mapcore.util.al;
import com.amap.api.mapcore.util.al.a;
import com.amap.api.mapcore.util.ap;
import com.amap.api.mapcore.util.be;
import com.amap.api.mapcore.util.en;
import com.amap.api.mapcore.util.gz;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import java.util.ArrayList;
import java.util.Iterator;

public final class OfflineMapManager {
    ap a;
    al b;
    private Context c;
    private OfflineMapDownloadListener d;
    private OfflineLoadedListener e;
    private Handler f = new Handler(this.c.getMainLooper());
    private Handler g = new Handler(this.c.getMainLooper());

    public interface OfflineLoadedListener {
        void onVerifyComplete();
    }

    public interface OfflineMapDownloadListener {
        void onCheckUpdate(boolean z, String str);

        void onDownload(int i, int i2, String str);

        void onRemove(boolean z, String str, String str2);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener) {
        this.d = offlineMapDownloadListener;
        this.c = context.getApplicationContext();
        a(context);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener, AMap aMap) {
        this.d = offlineMapDownloadListener;
        this.c = context.getApplicationContext();
        try {
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(Context context) {
        this.c = context.getApplicationContext();
        al.b = false;
        this.b = al.a(this.c);
        this.b.a(new a(this) {
            final /* synthetic */ OfflineMapManager a;

            {
                this.a = r1;
            }

            public void a(final be beVar) {
                if (this.a.d != null && beVar != null) {
                    this.a.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            try {
                                this.b.a.d.onDownload(beVar.c().b(), beVar.getcompleteCode(), beVar.getCity());
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void b(final be beVar) {
                if (this.a.d != null && beVar != null) {
                    this.a.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            try {
                                if (beVar.c().equals(beVar.g) || beVar.c().equals(beVar.a)) {
                                    this.b.a.d.onCheckUpdate(true, beVar.getCity());
                                } else {
                                    this.b.a.d.onCheckUpdate(false, beVar.getCity());
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void c(final be beVar) {
                if (this.a.d != null && beVar != null) {
                    this.a.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            try {
                                if (beVar.c().equals(beVar.a)) {
                                    this.b.a.d.onRemove(true, beVar.getCity(), "");
                                } else {
                                    this.b.a.d.onRemove(false, beVar.getCity(), "");
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            }

            public void a() {
                if (this.a.e != null) {
                    this.a.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            try {
                                this.a.a.e.onVerifyComplete();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        try {
            this.b.a();
            this.a = this.b.f;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void downloadByCityCode(String str) throws AMapException {
        try {
            this.b.e(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void downloadByCityName(String str) throws AMapException {
        try {
            this.b.d(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void downloadByProvinceName(String str) throws AMapException {
        try {
            a();
            OfflineMapProvince itemByProvinceName = getItemByProvinceName(str);
            if (itemByProvinceName == null) {
                throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
            }
            Iterator it = itemByProvinceName.getCityList().iterator();
            while (it.hasNext()) {
                final String city = ((OfflineMapCity) it.next()).getCity();
                this.g.post(new Runnable(this) {
                    final /* synthetic */ OfflineMapManager b;

                    public void run() {
                        try {
                            this.b.b.d(city);
                        } catch (Throwable e) {
                            gz.c(e, "OfflineMapManager", "downloadByProvinceName");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            if (th instanceof AMapException) {
                AMapException aMapException = (AMapException) th;
            } else {
                gz.c(th, "OfflineMapManager", "downloadByProvinceName");
            }
        }
    }

    public void remove(String str) {
        try {
            if (this.b.b(str)) {
                this.b.c(str);
                return;
            }
            OfflineMapProvince c = this.a.c(str);
            if (c != null && c.getCityList() != null) {
                Iterator it = c.getCityList().iterator();
                while (it.hasNext()) {
                    final String city = ((OfflineMapCity) it.next()).getCity();
                    this.g.post(new Runnable(this) {
                        final /* synthetic */ OfflineMapManager b;

                        public void run() {
                            this.b.b.c(city);
                        }
                    });
                }
            } else if (this.d != null) {
                this.d.onRemove(false, str, "没有该城市");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public ArrayList<OfflineMapProvince> getOfflineMapProvinceList() {
        return this.a.a();
    }

    public OfflineMapCity getItemByCityCode(String str) {
        return this.a.a(str);
    }

    public OfflineMapCity getItemByCityName(String str) {
        return this.a.b(str);
    }

    public OfflineMapProvince getItemByProvinceName(String str) {
        return this.a.c(str);
    }

    public ArrayList<OfflineMapCity> getOfflineMapCityList() {
        return this.a.b();
    }

    public ArrayList<OfflineMapCity> getDownloadingCityList() {
        return this.a.e();
    }

    public ArrayList<OfflineMapProvince> getDownloadingProvinceList() {
        return this.a.f();
    }

    public ArrayList<OfflineMapCity> getDownloadOfflineMapCityList() {
        return this.a.c();
    }

    public ArrayList<OfflineMapProvince> getDownloadOfflineMapProvinceList() {
        return this.a.d();
    }

    private void a(String str, String str2) throws AMapException {
        this.b.a(str);
    }

    public void updateOfflineCityByCode(String str) throws AMapException {
        OfflineMapCity itemByCityCode = getItemByCityCode(str);
        if (itemByCityCode == null || itemByCityCode.getCity() == null) {
            throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
        }
        a(itemByCityCode.getCity(), "cityname");
    }

    public void updateOfflineCityByName(String str) throws AMapException {
        a(str, "cityname");
    }

    public void updateOfflineMapProvinceByName(String str) throws AMapException {
        a(str, "cityname");
    }

    private void a() throws AMapException {
        if (!en.d(this.c)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    public void restart() {
    }

    public void stop() {
        this.b.c();
    }

    public void pause() {
        this.b.d();
    }

    public void destroy() {
        try {
            if (this.b != null) {
                this.b.e();
            }
            b();
            if (this.f != null) {
                this.f.removeCallbacksAndMessages(null);
            }
            this.f = null;
            if (this.g != null) {
                this.g.removeCallbacksAndMessages(null);
            }
            this.g = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b() {
        this.d = null;
    }

    public void setOnOfflineLoadedListener(OfflineLoadedListener offlineLoadedListener) {
        this.e = offlineLoadedListener;
    }
}
