package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LogBuilder {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType = null;
    private static final String APPKEY = "WEIBO_APPKEY";
    private static final String CHANNEL = "WEIBO_CHANNEL";
    public static final String KEY_AID = "aid";
    public static final String KEY_APPKEY = "appkey";
    public static final String KEY_CHANNEL = "channel";
    private static final String KEY_DURATION = "duration";
    public static final String KEY_END_TIME = "endtime";
    private static final String KEY_EVENT_ID = "event_id";
    private static final String KEY_EXTEND = "extend";
    public static final String KEY_HASH = "key_hash";
    public static final String KEY_PACKAGE_NAME = "packagename";
    private static final String KEY_PAGE_ID = "page_id";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_START_TIME = "starttime";
    private static final String KEY_TIME = "time";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VERSION = "version";
    private static final int MAX_COUNT = 500;
    public static final long MAX_INTERVAL = 86400000;

    static /* synthetic */ int[] $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType() {
        int[] iArr = $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType;
        if (iArr == null) {
            iArr = new int[LogType.values().length];
            try {
                iArr[LogType.ACTIVITY.ordinal()] = 5;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LogType.EVENT.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LogType.FRAGMENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LogType.SESSION_END.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[LogType.SESSION_START.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType = iArr;
        }
        return iArr;
    }

    LogBuilder() {
    }

    public static String getAppKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get(APPKEY);
                if (obj != null) {
                    LogUtil.i(WBAgent.TAG, "APPKEY: " + String.valueOf(obj));
                    return String.valueOf(obj);
                }
                LogUtil.e(WBAgent.TAG, "Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, "Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml." + e);
        }
        return null;
    }

    public static String getChannel(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString(CHANNEL);
                if (string != null) {
                    LogUtil.i(WBAgent.TAG, "CHANNEL: " + string.trim());
                    return string.trim();
                }
                LogUtil.e(WBAgent.TAG, "Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, "Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml." + e);
        }
        return null;
    }

    public static String getVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            LogUtil.i(WBAgent.TAG, "versionName: " + packageInfo.versionName);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            LogUtil.e(WBAgent.TAG, "Could not read versionName from AndroidManifest.xml." + e);
            return null;
        }
    }

    public static String getPageLogs(List<PageLog> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PageLog logInfo : list) {
            stringBuilder.append(getLogInfo(logInfo).toString()).append(",");
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject getLogInfo(com.sina.weibo.sdk.statistic.PageLog r6) {
        /*
        r1 = new org.json.JSONObject;
        r1.<init>();
        r0 = $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType();	 Catch:{ Exception -> 0x002a }
        r2 = r6.getType();	 Catch:{ Exception -> 0x002a }
        r2 = r2.ordinal();	 Catch:{ Exception -> 0x002a }
        r0 = r0[r2];	 Catch:{ Exception -> 0x002a }
        switch(r0) {
            case 1: goto L_0x0017;
            case 2: goto L_0x0040;
            case 3: goto L_0x005f;
            case 4: goto L_0x0087;
            case 5: goto L_0x00a9;
            default: goto L_0x0016;
        };	 Catch:{ Exception -> 0x002a }
    L_0x0016:
        return r1;
    L_0x0017:
        r0 = "type";
        r2 = 0;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        goto L_0x0016;
    L_0x002a:
        r0 = move-exception;
        r2 = "WBAgent";
        r3 = new java.lang.StringBuilder;
        r4 = "get page log error.";
        r3.<init>(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.sina.weibo.sdk.utils.LogUtil.e(r2, r0);
        goto L_0x0016;
    L_0x0040:
        r0 = "type";
        r2 = 1;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "time";
        r2 = r6.getEndTime();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "duration";
        r2 = r6.getDuration();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        goto L_0x0016;
    L_0x005f:
        r0 = "type";
        r2 = 2;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "page_id";
        r2 = r6.getPage_id();	 Catch:{ Exception -> 0x002a }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "duration";
        r2 = r6.getDuration();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        goto L_0x0016;
    L_0x0087:
        r0 = "type";
        r2 = 3;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "page_id";
        r2 = r6.getPage_id();	 Catch:{ Exception -> 0x002a }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r6 = (com.sina.weibo.sdk.statistic.EventLog) r6;	 Catch:{ Exception -> 0x002a }
        addEventData(r1, r6);	 Catch:{ Exception -> 0x002a }
        goto L_0x0016;
    L_0x00a9:
        r0 = "type";
        r2 = 4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "page_id";
        r2 = r6.getPage_id();	 Catch:{ Exception -> 0x002a }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        r0 = "duration";
        r2 = r6.getDuration();	 Catch:{ Exception -> 0x002a }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x002a }
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.LogBuilder.getLogInfo(com.sina.weibo.sdk.statistic.PageLog):org.json.JSONObject");
    }

    private static JSONObject addEventData(JSONObject jSONObject, EventLog eventLog) {
        try {
            jSONObject.put(KEY_EVENT_ID, eventLog.getEvent_id());
            if (eventLog.getExtend() != null) {
                Map extend = eventLog.getExtend();
                StringBuilder stringBuilder = new StringBuilder();
                int i = 0;
                for (String str : extend.keySet()) {
                    if (i >= 10) {
                        break;
                    } else if (!TextUtils.isEmpty((CharSequence) extend.get(str))) {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append("|");
                        }
                        stringBuilder.append(str).append(":").append((String) extend.get(str));
                        i++;
                    }
                }
                jSONObject.put(KEY_EXTEND, stringBuilder.toString());
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, "add event log error." + e);
        }
        return jSONObject;
    }

    public static List<JSONArray> getValidUploadLogs(String str) {
        Object buildUploadLogs = buildUploadLogs(str);
        if (TextUtils.isEmpty(buildUploadLogs)) {
            return null;
        }
        List<JSONArray> arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONArray jSONArray2 = new JSONObject(buildUploadLogs).getJSONArray("applogs");
            int i = 0;
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                if (isDataValid(currentTimeMillis, jSONObject.getLong(KEY_TIME) * 1000)) {
                    if (i < 500) {
                        jSONArray.put(jSONObject);
                        i++;
                    } else {
                        arrayList.add(jSONArray);
                        jSONArray = new JSONArray();
                        i = 0;
                    }
                }
            }
            if (jSONArray.length() > 0) {
                arrayList.add(jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static String buildUploadLogs(String str) {
        Object appLogs = LogFileUtil.getAppLogs(LogFileUtil.getAppLogPath(LogFileUtil.ANALYTICS_FILE_NAME));
        if (TextUtils.isEmpty(appLogs) && TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{applogs:[");
        if (!TextUtils.isEmpty(appLogs)) {
            stringBuilder.append(appLogs);
        }
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    private static boolean isDataValid(long j, long j2) {
        if (j - j2 < MAX_INTERVAL) {
            return true;
        }
        return false;
    }
}
