package com.amap.api.maps.offlinemap;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.amap.api.mapcore.util.fe;
import com.amap.api.mapcore.util.ff;
import com.amap.api.mapcore.util.fh;
import com.amap.api.offlineservice.AMapPermissionActivity;
import com.amap.api.offlineservice.a;

public class OfflineMapActivity extends AMapPermissionActivity implements OnClickListener {
    private static int a = 0;
    private a b;
    private fe c;
    private fe[] d = new fe[32];
    private int e = -1;
    private ff f;

    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            getWindow().setSoftInputMode(32);
            getWindow().setFormat(-3);
            requestWindowFeature(1);
            fh.a(getApplicationContext());
            this.e = -1;
            a = 0;
            b(new fe(1, null));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showScr() {
        try {
            setContentView(this.b.b());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(fe feVar) {
        try {
            if (this.b != null) {
                this.b.c();
                this.b = null;
            }
            this.b = c(feVar);
            if (this.b != null) {
                this.c = feVar;
                this.b.a(this);
                this.b.a(this.c.b);
                this.b.a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b(fe feVar) {
        try {
            a++;
            a(feVar);
            this.e = (this.e + 1) % 32;
            this.d[this.e] = feVar;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private a c(fe feVar) {
        try {
            switch (feVar.a) {
                case 1:
                    if (this.f == null) {
                        this.f = new ff();
                    }
                    return this.f;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    protected void onStart() {
        try {
            super.onStart();
            if (this.b != null) {
                this.b.f();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void onResume() {
        try {
            super.onResume();
            if (this.b != null) {
                this.b.g();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void onPause() {
        try {
            super.onPause();
            if (this.b != null) {
                this.b.i();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void onStop() {
        try {
            super.onStop();
            if (this.b != null) {
                this.b.h();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        try {
            super.onConfigurationChanged(configuration);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void closeScr() {
        try {
            if (!a(null)) {
                if (this.b != null) {
                    this.b.c();
                }
                finish();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void closeScr(Bundle bundle) {
        try {
            if (!a(bundle)) {
                if (this.b != null) {
                    this.b.c();
                }
                finish();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean a(Bundle bundle) {
        try {
            if ((a == 1 && this.b != null) || a <= 1) {
                return false;
            }
            a--;
            this.e = ((this.e - 1) + 32) % 32;
            fe feVar = this.d[this.e];
            feVar.b = bundle;
            a(feVar);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    protected void onDestroy() {
        try {
            super.onDestroy();
            if (this.b != null) {
                this.b.c();
                this.b = null;
            }
            this.c = null;
            this.d = null;
            if (this.f != null) {
                this.f.c();
                this.f = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onClick(View view) {
        try {
            if (this.b != null) {
                this.b.a(view);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            try {
                if (this.b != null && !this.b.e()) {
                    return true;
                }
                if (a(null)) {
                    return false;
                }
                if (keyEvent == null) {
                    if (a == 1) {
                        finish();
                    }
                    return false;
                }
                this.e = -1;
                a = 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
