package cn.jpush.android.c;

import android.app.Activity;
import android.content.Context;
import cn.jpush.android.d.e;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.api.HuaweiApiClient.Builder;
import com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks;
import com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.huawei.hms.support.api.push.TokenResult;

public final class a implements ConnectionCallbacks, OnConnectionFailedListener {
    protected HuaweiApiClient a;
    private c b;

    public a(Context context, c cVar) {
        this.b = cVar;
        try {
            this.a = new Builder(context).addApi(HuaweiPush.PUSH_API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        } catch (Throwable th) {
            e.e("PluginHuaweiApiClientCallBack", "new PluginHuaweiPlatformAction object failed e:" + th);
        }
    }

    private Activity a() {
        return this.b != null ? this.b.a : null;
    }

    public final void onConnected() {
        e.f("PluginHuaweiApiClientCallBack", "onConnected");
        b();
    }

    public final void onConnectionSuspended(int i) {
        e.f("PluginHuaweiApiClientCallBack", "onConnected:" + i);
        try {
            this.a.connect();
        } catch (Throwable th) {
            e.h("PluginHuaweiApiClientCallBack", "onConnectionSuspended reconnect failed:" + th);
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Object obj = null;
        String str = "PluginHuaweiApiClientCallBack";
        StringBuilder append = new StringBuilder("onConnectionFailed:").append(a()).append(", errorCode:");
        if (connectionResult != null) {
            obj = Integer.valueOf(connectionResult.getErrorCode());
        }
        e.f(str, append.append(obj).toString());
        try {
            boolean isUserResolvableError = HuaweiApiAvailability.getInstance().isUserResolvableError(connectionResult.getErrorCode());
            e.f("PluginHuaweiApiClientCallBack", "is user Resolvable Error - " + isUserResolvableError);
            if (!isUserResolvableError) {
                g.a().a(a(), null);
            } else if (a() != null) {
                HuaweiApiAvailability.getInstance().resolveError(a(), connectionResult.getErrorCode(), 10001);
            } else {
                e.h("PluginHuaweiApiClientCallBack", "onConnectionFailed activity was null");
            }
        } catch (Throwable th) {
            e.h("PluginHuaweiApiClientCallBack", "resolverError failed error:" + th);
        }
    }

    private String b() {
        try {
            if (c()) {
                HuaweiPush.HuaweiPushApi.getToken(this.a).setResultCallback(new ResultCallback<TokenResult>(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public final /* synthetic */ void onResult(Object obj) {
                        try {
                            e.f("PluginHuaweiApiClientCallBack", "invoke get token interface success,result:" + ((TokenResult) obj));
                        } catch (Throwable th) {
                            e.g("PluginHuaweiApiClientCallBack", "get token failed - error:" + th);
                        }
                    }
                });
            } else {
                e.g("PluginHuaweiApiClientCallBack", "get token failed, HMS is disconnect.");
            }
        } catch (Throwable th) {
            e.f("PluginHuaweiApiClientCallBack", "get RegID failed error:" + th);
        }
        return null;
    }

    private boolean c() {
        try {
            if (this.a != null && this.a.isConnected()) {
                return true;
            }
        } catch (Throwable th) {
            e.f("PluginHuaweiApiClientCallBack", "load connect state faile -  error:" + th);
        }
        return false;
    }
}
