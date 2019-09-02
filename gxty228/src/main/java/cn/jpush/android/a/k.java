package cn.jpush.android.a;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.a.c;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.api.a;
import cn.jpush.android.d.e;
import cn.jpush.android.d.g;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.service.d;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.lzy.okgo.model.Progress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class k {
    private static ConcurrentLinkedQueue<Long> a = new ConcurrentLinkedQueue();

    private static void a(Context context, String str, Set<String> set, a aVar) {
        long nextRid = JCoreInterface.getNextRid();
        if (aVar != null) {
            d.a().a(context, Long.valueOf(nextRid), aVar);
        }
        e.e("TagAliasHelper", "tag alias rid = " + nextRid);
        if (ServiceInterface.d(context)) {
            d.a().a(context, JPushInterface.a.m, nextRid, aVar);
            return;
        }
        Context context2;
        if (context instanceof Application) {
            context2 = context;
        } else {
            context2 = context.getApplicationContext();
        }
        if (cn.jpush.android.a.a(context2)) {
            if (aVar.e == 0) {
                d.a().a(context2);
            }
            ServiceInterface.a(context2, str, (Set) set, nextRid, aVar);
            return;
        }
        d.a().a(context2, JPushInterface.a.j, nextRid, aVar);
    }

    public static Set<String> a(Set<String> set) {
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return set;
        }
        Set<String> linkedHashSet = new LinkedHashSet();
        int i = 0;
        for (String str : set) {
            int i2;
            if (TextUtils.isEmpty(str) || !g.a(str)) {
                e.j("TagAliasHelper", "Invalid tag : " + str);
                i2 = i;
            } else {
                linkedHashSet.add(str);
                i2 = i + 1;
                if (i2 >= 1000) {
                    e.h("TagAliasHelper", "The lenght of tags maybe more than 1000.");
                    return linkedHashSet;
                }
            }
            i = i2;
        }
        return linkedHashSet;
    }

    public static String b(Set<String> set) {
        String str = null;
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return "";
        }
        int i = 0;
        for (String str2 : set) {
            int i2;
            String str3;
            if (TextUtils.isEmpty(str2) || !g.a(str2)) {
                e.j("TagAliasHelper", "Invalid tag: " + str2);
                i2 = i;
                str3 = str;
            } else {
                if (str == null) {
                    str = str2;
                } else {
                    str = str + "," + str2;
                }
                i2 = i + 1;
                if (i2 >= 1000) {
                    return str;
                }
                str3 = str;
            }
            str = str3;
            i = i2;
        }
        return str;
    }

    public static void a(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback, int i, int i2) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        a(context, str, (Set) set, new a(str, set, tagAliasCallback, System.currentTimeMillis(), 0, 0));
    }

    public static void a(Context context, int i, String str, int i2, int i3) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        a(context, str, null, new a(i, str, System.currentTimeMillis(), 2, i3));
    }

    public static void a(Context context, int i, Set<String> set, int i2, int i3) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        a(context, null, (Set) set, new a(i, (Set) set, System.currentTimeMillis(), 1, i3));
    }

    private static int a(long j) {
        if (a.size() < 10) {
            a.offer(Long.valueOf(j));
            return 0;
        }
        long longValue = j - ((Long) a.element()).longValue();
        if (longValue < 0) {
            a.clear();
            return 2;
        } else if (longValue <= 10000) {
            return 1;
        } else {
            while (a.size() >= 10) {
                a.poll();
            }
            a.offer(Long.valueOf(j));
            return 0;
        }
    }

    public static void a(Context context, Bundle bundle) {
        int parseInt;
        int i = 0;
        String string = bundle.getString("alias");
        List stringArrayList = bundle.getStringArrayList("tags");
        long j = bundle.getLong("seq_id", 0);
        try {
            parseInt = Integer.parseInt(bundle.getString("proto_type"));
        } catch (Throwable th) {
            e.g("TagAliasHelper", "load tag/alias proto type failed - error:" + th);
            a(context, i, JPushInterface.a.j, j);
            parseInt = i;
        }
        try {
            i = Integer.parseInt(bundle.getString("protoaction_type"));
        } catch (Throwable th2) {
            e.g("TagAliasHelper", "load tag/alias action type failed - error:" + th2);
            a(context, parseInt, JPushInterface.a.j, j);
        }
        int a = a(System.currentTimeMillis());
        if (a != 0) {
            if (a == 1) {
                e.g("TagAliasHelper", "set tags/alias too soon,over 10 times in 10s");
            } else {
                e.g("TagAliasHelper", "set tags/alias failed,time shaft error，please try again");
            }
            if (a == 1) {
                a = JPushInterface.a.l;
            } else {
                a = JPushInterface.a.n;
            }
            a(context, parseInt, a, j);
            return;
        }
        l.a();
        if (l.a(context, parseInt, j)) {
            a(context, parseInt, JPushInterface.a.u, j);
            return;
        }
        c cVar = null;
        if (parseInt == 0) {
            e.c("TagAliasHelper", "old tag/alias proto");
            cVar = a(context, stringArrayList, string, j);
        } else if (parseInt == 1) {
            cVar = a(context, stringArrayList, j, i, -1);
        } else if (parseInt == 2) {
            cVar = a(context, string, j, i);
        }
        if (cVar != null && (parseInt == 1 || parseInt == 2)) {
            if (l.a().a(parseInt)) {
                l.a().a(parseInt, i, j, (ArrayList) stringArrayList, string);
            } else {
                e.g("TagAliasHelper", (parseInt == 1 ? Progress.TAG : "alias") + " is operatoring, please wait last operator complete");
                a(context, parseInt, parseInt == 1 ? JPushInterface.a.v : JPushInterface.a.w, j);
                return;
            }
        }
        a(context, cVar);
    }

    protected static void a(Context context, c cVar) {
        e.d("TagAliasHelper", "tagalias:" + cVar);
        if (cVar != null) {
            e.a(context).a(cVar, 20000);
        } else {
            e.g("TagAliasHelper", "tagaliasRequest was null");
        }
    }

    protected static void a(Context context, int i, int i2, long j) {
        try {
            Intent intent = new Intent();
            intent.addCategory(context.getPackageName());
            intent.setPackage(context.getPackageName());
            if (i == 0) {
                intent.setAction("cn.jpush.android.intent.TAG_ALIAS_CALLBACK");
            } else {
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                if (i == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
            }
            intent.putExtra("tagalias_errorcode", i2);
            intent.putExtra("tagalias_seqid", j);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            e.h("TagAliasHelper", "NotifyTagAliasError:" + th.getMessage());
        }
    }

    private static boolean a(Context context, int i, String str, long j) {
        int b = g.b(str);
        if (b == 0) {
            return true;
        }
        e.d("TagAliasHelper", "Invalid alias: " + str + ", will not set alias this time.");
        a(context, i, b, j);
        return false;
    }

    private static boolean a(Context context, int i, Set<String> set, long j) {
        int a = g.a((Set) set);
        if (a == 0) {
            return true;
        }
        e.d("TagAliasHelper", "Invalid tags, will not set tags this time.");
        a(context, i, a, j);
        return false;
    }

    private static boolean b(Context context, String str, long j, int i) {
        if (str != null) {
            boolean z;
            int i2;
            Object replaceAll = str.replaceAll(",", "");
            if (i != 0) {
                z = true;
            } else {
                z = false;
            }
            if (TextUtils.isEmpty(replaceAll)) {
                i2 = 0;
            } else {
                i2 = replaceAll.getBytes().length + 0;
            }
            e.a("TagAliasHelper", "tags length:" + i2);
            z = z ? i2 <= GLMapStaticValue.TMC_REFRESH_TIMELIMIT : i2 <= 7000;
            if (!z) {
                a(context, i, JPushInterface.a.i, j);
                e.h("TagAliasHelper", "The length of tags should be less than " + (i != 0 ? GLMapStaticValue.TMC_REFRESH_TIMELIMIT : 7000) + " bytes.");
                return false;
            }
        }
        return true;
    }

    private static c a(Context context, List<String> list, String str, long j) {
        Set hashSet;
        if (list != null) {
            hashSet = new HashSet(list);
        } else {
            hashSet = null;
        }
        if (str != null && !a(context, 0, str, j)) {
            return null;
        }
        if (hashSet != null && !a(context, 0, hashSet, j)) {
            return null;
        }
        String b = b(hashSet);
        if (!b(context, b, j, 0)) {
            return null;
        }
        if (b == null && str == null) {
            e.j("TagAliasHelper", "NULL alias and tags. Give up action.");
            a(context, 0, JPushInterface.a.b, j);
            return null;
        }
        e.d("TagAliasHelper", "action:setAliasAndTags - alias:" + str + ", tags:" + b);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("platform", "a");
            if (str != null) {
                jSONObject.put("alias", str);
            }
            if (hashSet != null) {
                jSONObject.put("tags", b);
            }
            Object jSONObject2 = jSONObject.toString();
            e.c("TagAliasHelper", "tagalias str:" + jSONObject2);
            if (TextUtils.isEmpty(jSONObject2)) {
                e.c("TagAliasHelper", "tagalias request action was empty");
                return null;
            }
            return new c(4, 10, j, JCoreInterface.getAppKey(), jSONObject2);
        } catch (Throwable th) {
            e.g("TagAliasHelper", "tagalias exception:" + th);
            a(context, 0, JPushInterface.a.j, j);
        }
    }

    protected static c a(Context context, List<String> list, long j, int i, int i2) {
        if (i != 0) {
            try {
                int i3;
                String str;
                JSONObject a = a(i);
                if (i == 1 || i == 2 || i == 3 || i == 6) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                if (i3 != 0) {
                    if (list == null || list.isEmpty()) {
                        e.j("TagAliasHelper", "tags was empty. Give up action.");
                        a(context, 1, JPushInterface.a.b, j);
                        return null;
                    }
                    Set hashSet = new HashSet(list);
                    if (!a(context, 1, hashSet, j)) {
                        return null;
                    }
                    CharSequence b = b(hashSet);
                    if (!b(context, b, j, 1)) {
                        return null;
                    }
                    if (i != 6) {
                        JSONArray jSONArray = new JSONArray();
                        for (String str2 : list) {
                            jSONArray.put(str2);
                        }
                        a.put("tags", jSONArray);
                    } else if (TextUtils.isEmpty(b)) {
                        e.j("TagAliasHelper", "stags was empty. Give up action.");
                        a(context, 1, JPushInterface.a.b, j);
                        return null;
                    } else {
                        a.put("tags", b);
                    }
                }
                if (i == 5) {
                    str2 = "curr";
                    if (i2 == -1) {
                        i2 = 1;
                    }
                    a.put(str2, i2);
                }
                Object jSONObject = a.toString();
                e.c("TagAliasHelper", "tag str:" + jSONObject);
                if (TextUtils.isEmpty(jSONObject)) {
                    e.c("TagAliasHelper", "tag request action was empty");
                } else {
                    return new c(1, 28, j, JCoreInterface.getAppKey(), jSONObject);
                }
            } catch (Throwable th) {
                e.g("TagAliasHelper", "tag exception:" + th);
                a(context, 1, JPushInterface.a.j, j);
            }
        } else {
            e.i("TagAliasHelper", "unsupport tag action type");
            a(context, 1, JPushInterface.a.j, j);
        }
        return null;
    }

    protected static c a(Context context, String str, long j, int i) {
        Object obj;
        Object obj2 = 1;
        if (i == 2 || i == 3 || i == 5) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            try {
                JSONObject a = a(i);
                if (i != 2) {
                    obj2 = null;
                }
                if (obj2 != null) {
                    if (TextUtils.isEmpty(str)) {
                        e.j("TagAliasHelper", "alias was empty. Give up action.");
                        a(context, 2, JPushInterface.a.b, j);
                        return null;
                    } else if (!a(context, 2, str, j)) {
                        return null;
                    } else {
                        a.put("alias", str);
                    }
                }
                Object jSONObject = a.toString();
                e.c("TagAliasHelper", "alias str:" + jSONObject);
                if (TextUtils.isEmpty(jSONObject)) {
                    e.c("TagAliasHelper", "alias request action was empty");
                } else {
                    return new c(1, 29, j, JCoreInterface.getAppKey(), jSONObject);
                }
            } catch (Throwable th) {
                e.g("TagAliasHelper", "alias exception:" + th);
                a(context, 2, JPushInterface.a.j, j);
            }
        } else {
            e.i("TagAliasHelper", "unsupport alias action type");
            a(context, 2, JPushInterface.a.j, j);
        }
        return null;
    }

    private static JSONObject a(int i) throws JSONException {
        Object obj;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("platform", "a");
        String str = "op";
        switch (i) {
            case 1:
                obj = "add";
                break;
            case 2:
                obj = "set";
                break;
            case 3:
                obj = "del";
                break;
            case 4:
                obj = "clean";
                break;
            case 5:
                obj = "get";
                break;
            case 6:
                obj = "valid";
                break;
            default:
                obj = null;
                break;
        }
        jSONObject.put(str, obj);
        return jSONObject;
    }

    protected static long a(Context context, String str, int i, long j) {
        e.c("TagAliasHelper", "action - onRecvTagAliasCallBack");
        try {
            long optLong;
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code", JPushInterface.a.j);
            if (i == 0) {
                optLong = jSONObject.optLong("sequence");
            } else {
                optLong = j;
            }
            Intent intent = new Intent();
            intent.addCategory(cn.jpush.android.a.c);
            intent.putExtra("proto_type", i);
            intent.setPackage(context.getPackageName());
            if (i == 0) {
                intent.setAction("cn.jpush.android.intent.TAG_ALIAS_CALLBACK");
            } else {
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                if (i == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
            }
            intent.putExtra("tagalias_errorcode", optInt);
            intent.putExtra("tagalias_seqid", optLong);
            Intent a = l.a().a(context, optLong, optInt, jSONObject, intent);
            if (a == null) {
                return optLong;
            }
            context.sendBroadcast(a);
            return optLong;
        } catch (Throwable th) {
            e.g("TagAliasHelper", "tagalias msgContent:" + str);
            return -1;
        }
    }
}
