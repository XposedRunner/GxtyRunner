package cn.jpush.android.service;

import cn.jpush.android.c.g;
import cn.jpush.android.d.e;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class PluginFCMInstanceIdService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        String str = null;
        try {
            str = FirebaseInstanceId.getInstance().getToken();
        } catch (Throwable th) {
            e.d("PluginFCMInstanceIdService", "get fcm token error:", th);
        }
        e.f("PluginFCMInstanceIdService", "fcm token is " + String.valueOf(str));
        g.a().b(this, str);
    }
}
