package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.amap.api.mapcore.util.iv.a;
import java.util.List;

/* compiled from: SoDownloadThread */
public final class ih implements a {
    private iy a;
    private iv b;
    private id c;
    private iz d;
    private iz e;
    private iz f;
    private hz g;
    private gk h;
    private Context i;

    public ih(Context context, id idVar, gk gkVar, iz izVar, iz izVar2, iz izVar3, hz hzVar) {
        this.d = izVar;
        this.e = izVar2;
        this.f = izVar3;
        this.g = hzVar;
        try {
            this.i = context.getApplicationContext();
            this.h = gkVar;
            this.c = idVar;
            this.a = new ig(this.c);
            this.b = new iv(this.a);
        } catch (Throwable th) {
        }
    }

    private boolean b() {
        try {
            Object obj;
            int i;
            iz izVar = this.d;
            if (izVar == null) {
                obj = null;
            } else if (!izVar.a().equals(this.h.a()) || !izVar.b().equals(this.h.b())) {
                obj = null;
            } else if (izVar.d() == null) {
                obj = null;
            } else {
                i = 1;
            }
            if (obj != null) {
                if (this.d == null || this.d.d() == null) {
                    obj = 1;
                } else {
                    for (ij b : this.d.d()) {
                        if (ik.b(this.i, b)) {
                            i = 1;
                            break;
                        }
                    }
                    if (!(this.e == null && this.f == null)) {
                        if (if.a(this.h.b(), this.d.c()) >= 0) {
                            i = 1;
                        } else if (!a(this.e, this.d)) {
                            i = 1;
                        } else if (!a(this.f, this.d)) {
                            i = 1;
                        }
                    }
                    obj = null;
                }
                if (obj == null) {
                    Context context = this.i;
                    if (!this.d.f) {
                        if (gd.q(context) == 1) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (obj == null) {
                            obj = null;
                            if (obj != null) {
                                izVar = this.d;
                                if (izVar != null) {
                                    obj = null;
                                } else if (VERSION.SDK_INT >= izVar.b || VERSION.SDK_INT > izVar.a) {
                                    obj = null;
                                } else if (izVar.d() == null) {
                                    obj = null;
                                } else {
                                    i = 1;
                                }
                                if (obj != null) {
                                    return true;
                                }
                            }
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        izVar = this.d;
                        if (izVar != null) {
                            if (VERSION.SDK_INT >= izVar.b) {
                            }
                            obj = null;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean a(iz izVar, iz izVar2) {
        if (izVar2 == null || izVar2.d() == null || TextUtils.isEmpty(izVar2.c())) {
            return false;
        }
        if (izVar == null || izVar.d() == null || TextUtils.isEmpty(izVar.c())) {
            return true;
        }
        if (if.a(izVar.c(), izVar2.c()) > 0) {
            return false;
        }
        List d = izVar.d();
        List d2 = izVar2.d();
        for (int i = 0; i < d2.size(); i++) {
            ij ijVar = (ij) d2.get(i);
            for (int i2 = 0; i2 < d.size(); i2++) {
                ij ijVar2 = (ij) d.get(i2);
                if (ijVar.f().equals(ijVar2.f()) && if.a(ijVar.e(), ijVar2.e()) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void a(byte[] bArr, long j) {
        this.c.a(bArr, j);
    }

    public final void d() {
    }

    public final void a(Throwable th) {
        this.c.b();
    }

    public final void e() {
        this.c.a(this.i);
    }

    public final void a() {
        try {
            if (b()) {
                hz.e(this.g.a(this.h.a()));
                this.b.a(this);
            }
        } catch (Throwable th) {
        }
    }
}
