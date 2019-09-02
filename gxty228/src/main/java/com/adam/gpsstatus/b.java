package com.adam.gpsstatus;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: GpsStatusProxy */
public class b {
    private static volatile b a;
    private Context b;
    private LocationManager c;
    private List<WeakReference<a>> d;
    private List<c> e;
    private boolean f = false;
    private boolean g = false;
    private boolean h = true;
    private Listener i = new Listener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onGpsStatusChanged(int i) {
            GpsStatus gpsStatus = null;
            int i2 = 0;
            try {
                if (!this.a.g) {
                    if (this.a.h) {
                        this.a.h = false;
                        if (this.a.d != null && this.a.d.size() != 0) {
                            switch (i) {
                                case 1:
                                    for (WeakReference weakReference : this.a.d) {
                                        if (weakReference.get() != null) {
                                            ((a) weakReference.get()).a();
                                        }
                                    }
                                    return;
                                case 2:
                                    for (WeakReference weakReference2 : this.a.d) {
                                        if (weakReference2.get() != null) {
                                            ((a) weakReference2.get()).b();
                                        }
                                    }
                                    return;
                                case 3:
                                    for (WeakReference weakReference22 : this.a.d) {
                                        if (weakReference22.get() != null) {
                                            ((a) weakReference22.get()).c();
                                        }
                                    }
                                    return;
                                case 4:
                                    if (ContextCompat.checkSelfPermission(this.a.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                                        if (this.a.c != null) {
                                            gpsStatus = this.a.c.getGpsStatus(null);
                                        }
                                        if (gpsStatus != null) {
                                            int maxSatellites = gpsStatus.getMaxSatellites();
                                            Iterator it = gpsStatus.getSatellites().iterator();
                                            this.a.e = new ArrayList();
                                            int i3 = 0;
                                            while (it.hasNext() && i3 <= maxSatellites) {
                                                GpsSatellite gpsSatellite = (GpsSatellite) it.next();
                                                i3++;
                                                if (gpsSatellite.usedInFix()) {
                                                    i2++;
                                                }
                                                if (gpsSatellite.getSnr() > 0.0f) {
                                                    this.a.e.add(new c(gpsSatellite));
                                                }
                                            }
                                            Collections.sort(this.a.e);
                                            for (WeakReference weakReference222 : this.a.d) {
                                                if (weakReference222.get() != null) {
                                                    ((a) weakReference222.get()).a(i2, i3);
                                                }
                                            }
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        }
                        return;
                    }
                    this.a.h = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static b a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context);
                }
            }
        }
        return a;
    }

    public b(Context context) {
        this.b = context;
    }

    public void a() {
        b();
        if (ContextCompat.checkSelfPermission(this.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.c = (LocationManager) this.b.getSystemService("location");
            this.c.addGpsStatusListener(this.i);
        }
    }

    public synchronized void b() {
        if (this.c != null) {
            this.c.removeGpsStatusListener(this.i);
            this.c = null;
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    void c() {
        if (this.f) {
            for (WeakReference weakReference : this.d) {
                if (weakReference.get() != null) {
                    ((a) weakReference.get()).c();
                }
            }
        } else if (b(this.b)) {
            for (WeakReference weakReference2 : this.d) {
                if (weakReference2.get() != null) {
                    ((a) weakReference2.get()).d();
                }
            }
        } else {
            for (WeakReference weakReference22 : this.d) {
                if (weakReference22.get() != null) {
                    ((a) weakReference22.get()).b();
                }
            }
        }
    }

    void a(a aVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        } else {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                if (weakReference.get() == null) {
                    it.remove();
                } else if (weakReference.get() == aVar) {
                    return;
                }
            }
        }
        this.d.add(new WeakReference(aVar));
    }

    void b(a aVar) {
        if (this.d != null) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                if (weakReference.get() == null || weakReference.get() == aVar) {
                    it.remove();
                }
            }
        }
    }

    private boolean b(Context context) {
        if (((LocationManager) context.getSystemService("location")).isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }
}
