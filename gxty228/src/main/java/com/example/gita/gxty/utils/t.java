package com.example.gita.gxty.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.v5kf.client.lib.V5ClientAgent;
import com.v5kf.client.lib.V5ClientAgent.ClientOpenMode;
import com.v5kf.client.lib.V5ClientConfig;
import com.v5kf.client.lib.callback.V5InitCallback;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: V5KUtils */
public class t {
    public static void a(Application application) {
        try {
            if (b(application)) {
                V5ClientConfig.FILE_PROVIDER = "com.example.gita.gxty.fileprovider";
                h.a("MyApplication", "onCreate isMainProcess V5ClientAgent.init");
                V5ClientAgent.init(application, "150995", "24dd3080122c1", new V5InitCallback() {
                    public void onSuccess(String str) {
                        h.b("V5ClientAgent.init ok (): " + str);
                    }

                    public void onFailure(String str) {
                        h.b("V5ClientAgent.init fail (): " + str);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean b(Application application) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) application.getSystemService("activity")).getRunningAppProcesses();
        String packageName = application.getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public static void a(Activity activity) {
        V5ClientConfig instance = V5ClientConfig.getInstance(activity);
        String b = q.a((Context) activity).b();
        String str = (String) q.a((Context) activity).b("username", "");
        String str2 = (String) q.a((Context) activity).b("photo", "");
        String str3 = (String) q.a((Context) activity).b("school", "");
        String str4 = (String) q.a((Context) activity).b("schoolId", "");
        instance.setShowLog(h.a);
        instance.setNickname(str);
        instance.setGender(0);
        instance.setAvatar(str2);
        instance.setOpenId(b);
        instance.setUid(b);
        instance.setVip(0);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("用户名", str);
            jSONObject.put("用户ID", b);
            jSONObject.put("学校", str3);
            jSONObject.put("学校ID", str4);
            jSONObject.put("手机型号", DeviceUtils.getModel());
            jSONObject.put("客户端版本", AppUtils.getAppVersionName() + " build(" + AppUtils.getAppVersionCode() + ")");
            jSONObject.put("系统版本", DeviceUtils.getSDKVersionName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        instance.setUserInfo(jSONObject);
        Bundle bundle = new Bundle();
        bundle.putInt("numOfMessagesOnRefresh", 10);
        bundle.putInt("numOfMessagesOnOpen", 10);
        bundle.putBoolean("enableVoice", false);
        bundle.putBoolean("showAvatar", true);
        bundle.putInt("clientOpenMode", ClientOpenMode.clientOpenModeDefault.ordinal());
        V5ClientAgent.getInstance().startV5ChatActivityWithBundle(activity, bundle);
    }
}
