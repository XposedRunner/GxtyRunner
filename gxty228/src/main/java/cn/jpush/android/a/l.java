package cn.jpush.android.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.jiguang.api.MultiSpHelper;
import cn.jpush.a.c;
import cn.jpush.android.d.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public final class l {
    private static volatile l a;
    private static final Object b = new Object();
    private ConcurrentHashMap<Long, a> c = new ConcurrentHashMap();

    private class a {
        public int a;
        public int b;
        public long c;
        public ArrayList<String> d;
        public String e;
        public int f = -1;
        public int g = -1;
        public int h = 0;
        public int i = 0;
        final /* synthetic */ l j;

        public a(l lVar, int i, int i2, long j, ArrayList<String> arrayList, String str) {
            this.j = lVar;
            this.a = i;
            this.b = i2;
            this.c = j;
            this.d = arrayList;
            if (i == 1 && this.d == null) {
                this.d = new ArrayList();
            }
            this.e = str;
            this.i = 1;
        }

        public final String toString() {
            return "TagAliasCacheBean{protoType=" + this.a + ", actionType=" + this.b + ", seqID=" + this.c + ", tags=" + this.d + ", alias='" + this.e + '\'' + ", totalPage=" + this.f + ", currPage=" + this.g + ", retryCount=" + this.h + '}';
        }
    }

    public static l a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new l();
                }
            }
        }
        return a;
    }

    public final void a(int i, int i2, long j, ArrayList<String> arrayList, String str) {
        a aVar = new a(this, i, i2, j, arrayList, str);
        e.c("TagAliasNewProtoRetryHelper", "action - createNewCacheNode, tagAliasCacheNode:" + aVar);
        this.c.put(Long.valueOf(j), aVar);
    }

    public final boolean a(int i) {
        if (!(this.c == null || this.c.isEmpty())) {
            for (Entry value : this.c.entrySet()) {
                a aVar = (a) value.getValue();
                if (aVar != null && aVar.a == i) {
                    return false;
                }
            }
        }
        return true;
    }

    public final Intent a(Context context, long j, int i, JSONObject jSONObject, Intent intent) {
        e.c("TagAliasNewProtoRetryHelper", "action - onTagAliasResponse, seqID:" + j + ",errorCode:" + i + ",intent:" + intent);
        a aVar = (a) this.c.get(Long.valueOf(j));
        e.c("TagAliasNewProtoRetryHelper", "tagAliasCacheNode:" + aVar);
        this.c.remove(Long.valueOf(j));
        if (aVar == null) {
            e.g("TagAliasNewProtoRetryHelper", "tagAliasCacheNode was null");
            return intent;
        }
        Object obj;
        long optLong;
        e.c("TagAliasNewProtoRetryHelper", "action - CheckAndSendAgain, errorCode:" + i + ",tagAliasCacheNode:" + aVar);
        if (i == 1 && aVar.h == 0) {
            aVar.h++;
            if (a(context, aVar.a, aVar.c)) {
                obj = 1;
            } else if (a(context, aVar)) {
                obj = 1;
            }
            if (obj != null) {
                e.c("TagAliasNewProtoRetryHelper", "retry action was sended");
                return null;
            } else if (i == 0) {
                if (i == 100) {
                    optLong = jSONObject.optLong("wait", -1);
                    e.h("TagAliasNewProtoRetryHelper", "set tag/alias action will freeze " + optLong + " seconds");
                    if (optLong > 0) {
                        if (optLong >= 0) {
                            e.g("JPushCommon", "freeze end time was incorrect!");
                        } else {
                            if (optLong > 1800) {
                                e.e("JPushCommon", "freeze end time was greate than half an hour");
                                optLong = 1800;
                            }
                            MultiSpHelper.commitLong(context, "TAFreezeSetTime", System.currentTimeMillis());
                            MultiSpHelper.commitLong(context, "TAFreezeEndTime", optLong * 1000);
                        }
                    }
                }
                if (aVar.a != 0) {
                    switch (i) {
                        case 1:
                        case 2:
                            i = cn.jpush.android.api.JPushInterface.a.o;
                            break;
                        case 3:
                            i = cn.jpush.android.api.JPushInterface.a.p;
                            break;
                        case 4:
                            i = cn.jpush.android.api.JPushInterface.a.q;
                            break;
                        case 5:
                            i = cn.jpush.android.api.JPushInterface.a.r;
                            break;
                        case 6:
                            i = cn.jpush.android.api.JPushInterface.a.s;
                            break;
                        case 7:
                        case 8:
                            i = cn.jpush.android.api.JPushInterface.a.t;
                            break;
                        case 100:
                            i = cn.jpush.android.api.JPushInterface.a.u;
                            break;
                    }
                }
                intent.putExtra("tagalias_errorcode", i);
                e.c("TagAliasNewProtoRetryHelper", "mapped errorCode:" + i);
                return intent;
            } else {
                aVar.h = 0;
                if (aVar.b == 5) {
                    aVar.f = jSONObject.optInt("total", -1);
                    aVar.g = jSONObject.optInt("curr", -1);
                    a(jSONObject, aVar);
                }
                if (aVar != null) {
                    e.c("TagAliasNewProtoRetryHelper", "tagAlias cache was null");
                    obj = null;
                } else {
                    e.c("TagAliasNewProtoRetryHelper", "action - onTagAliasResponse, tagAliasCacheNode:" + aVar);
                    if (aVar.g < aVar.f) {
                        e.c("TagAliasNewProtoRetryHelper", "all tags info was loaded");
                        obj = null;
                    } else {
                        obj = 1;
                    }
                }
                if (obj != null) {
                    aVar.g++;
                    e.c("TagAliasNewProtoRetryHelper", "load next page, currpage:" + aVar.g + ",totalPage:" + aVar.f);
                    if (a(context, aVar.a, aVar.c)) {
                        return null;
                    }
                    if (a(context, aVar)) {
                        e.c("TagAliasNewProtoRetryHelper", "get next page request was sended");
                        return null;
                    }
                }
                if (aVar.b != 5) {
                    if (aVar.a != 1) {
                        if (aVar.d.size() > 0) {
                            return intent;
                        }
                        intent.putStringArrayListExtra("tags", aVar.d);
                        return intent;
                    } else if (aVar.a != 2 && aVar.e != null) {
                        intent.putExtra("alias", aVar.e);
                        return intent;
                    }
                } else if (aVar.b == 6) {
                    return intent;
                } else {
                    if (aVar.a != 1) {
                        intent.putExtra("validated", jSONObject.optBoolean("validated", false));
                        return intent;
                    }
                    e.g("TagAliasNewProtoRetryHelper", "unsupport  proto type");
                    return intent;
                }
            }
        }
        obj = null;
        if (obj != null) {
            e.c("TagAliasNewProtoRetryHelper", "retry action was sended");
            return null;
        } else if (i == 0) {
            aVar.h = 0;
            if (aVar.b == 5) {
                aVar.f = jSONObject.optInt("total", -1);
                aVar.g = jSONObject.optInt("curr", -1);
                a(jSONObject, aVar);
            }
            if (aVar != null) {
                e.c("TagAliasNewProtoRetryHelper", "action - onTagAliasResponse, tagAliasCacheNode:" + aVar);
                if (aVar.g < aVar.f) {
                    obj = 1;
                } else {
                    e.c("TagAliasNewProtoRetryHelper", "all tags info was loaded");
                    obj = null;
                }
            } else {
                e.c("TagAliasNewProtoRetryHelper", "tagAlias cache was null");
                obj = null;
            }
            if (obj != null) {
                aVar.g++;
                e.c("TagAliasNewProtoRetryHelper", "load next page, currpage:" + aVar.g + ",totalPage:" + aVar.f);
                if (a(context, aVar.a, aVar.c)) {
                    return null;
                }
                if (a(context, aVar)) {
                    e.c("TagAliasNewProtoRetryHelper", "get next page request was sended");
                    return null;
                }
            }
            if (aVar.b != 5) {
                if (aVar.b == 6) {
                    return intent;
                }
                if (aVar.a != 1) {
                    e.g("TagAliasNewProtoRetryHelper", "unsupport  proto type");
                    return intent;
                }
                intent.putExtra("validated", jSONObject.optBoolean("validated", false));
                return intent;
            } else if (aVar.a != 1) {
                return aVar.a != 2 ? intent : intent;
            } else {
                if (aVar.d.size() > 0) {
                    return intent;
                }
                intent.putStringArrayListExtra("tags", aVar.d);
                return intent;
            }
        } else {
            if (i == 100) {
                optLong = jSONObject.optLong("wait", -1);
                e.h("TagAliasNewProtoRetryHelper", "set tag/alias action will freeze " + optLong + " seconds");
                if (optLong > 0) {
                    if (optLong >= 0) {
                        if (optLong > 1800) {
                            e.e("JPushCommon", "freeze end time was greate than half an hour");
                            optLong = 1800;
                        }
                        MultiSpHelper.commitLong(context, "TAFreezeSetTime", System.currentTimeMillis());
                        MultiSpHelper.commitLong(context, "TAFreezeEndTime", optLong * 1000);
                    } else {
                        e.g("JPushCommon", "freeze end time was incorrect!");
                    }
                }
            }
            if (aVar.a != 0) {
                switch (i) {
                    case 1:
                    case 2:
                        i = cn.jpush.android.api.JPushInterface.a.o;
                        break;
                    case 3:
                        i = cn.jpush.android.api.JPushInterface.a.p;
                        break;
                    case 4:
                        i = cn.jpush.android.api.JPushInterface.a.q;
                        break;
                    case 5:
                        i = cn.jpush.android.api.JPushInterface.a.r;
                        break;
                    case 6:
                        i = cn.jpush.android.api.JPushInterface.a.s;
                        break;
                    case 7:
                    case 8:
                        i = cn.jpush.android.api.JPushInterface.a.t;
                        break;
                    case 100:
                        i = cn.jpush.android.api.JPushInterface.a.u;
                        break;
                }
            }
            intent.putExtra("tagalias_errorcode", i);
            e.c("TagAliasNewProtoRetryHelper", "mapped errorCode:" + i);
            return intent;
        }
    }

    private static a a(JSONObject jSONObject, a aVar) {
        e.c("TagAliasNewProtoRetryHelper", "action - onUpdateCacheNode,responseJson:" + jSONObject + ",tagAliasCacheNode:" + aVar);
        if (aVar == null) {
            e.g("TagAliasNewProtoRetryHelper", "tagAliasCacheNode was null");
            return null;
        } else if (!TextUtils.equals(jSONObject.optString("op"), "get")) {
            return aVar;
        } else {
            if (aVar.a == 1) {
                try {
                    JSONArray optJSONArray = jSONObject.optJSONArray("tags");
                    if (optJSONArray == null || optJSONArray.length() == 0) {
                        return aVar;
                    }
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(optJSONArray.getString(i));
                    }
                    if (arrayList.size() <= 0) {
                        return aVar;
                    }
                    aVar.d.addAll(arrayList);
                    return aVar;
                } catch (Throwable th) {
                    e.i("TagAliasNewProtoRetryHelper", "parse tag list failed - error:" + th);
                    return aVar;
                }
            }
            String optString = jSONObject.optString("alias");
            if (optString == null) {
                return aVar;
            }
            aVar.e = optString;
            return aVar;
        }
    }

    public final int a(long j) {
        e.c("TagAliasNewProtoRetryHelper", "action - onTagAliasTimeOut :" + j);
        a aVar = (a) this.c.remove(Long.valueOf(j));
        e.c("TagAliasNewProtoRetryHelper", "onTagAliasTimeOut,removed cachenode:" + aVar);
        if (aVar != null) {
            return aVar.a;
        }
        return 0;
    }

    private boolean a(Context context, a aVar) {
        e.c("TagAliasNewProtoRetryHelper", "action - onSendAgain, tagAliasCacheNode:" + aVar);
        if (aVar == null) {
            e.g("TagAliasNewProtoRetryHelper", "onSendAgain - tagAliasCacheNode was null");
            return false;
        }
        c a;
        if (aVar.a == 1) {
            a = k.a(context, aVar.d, aVar.c, aVar.b, aVar.g);
        } else if (aVar.a == 2) {
            a = k.a(context, aVar.e, aVar.c, aVar.a);
        } else {
            e.c("TagAliasNewProtoRetryHelper", "unsupport proto type");
            return false;
        }
        if (a == null) {
            return false;
        }
        if (aVar.h > 200) {
            this.c.remove(Long.valueOf(aVar.c));
            k.a(context, aVar.a, cn.jpush.android.api.JPushInterface.a.o, aVar.c);
            e.c("TagAliasNewProtoRetryHelper", "same tag/alias request times greate than 200");
        } else {
            k.a(context, a);
            aVar.h++;
            this.c.put(Long.valueOf(aVar.c), aVar);
            e.c("TagAliasNewProtoRetryHelper", "send request success");
        }
        return true;
    }

    protected static boolean a(Context context, int i, long j) {
        if (i == 1 || i == 2) {
            Object obj;
            long j2 = MultiSpHelper.getLong(context, "TAFreezeEndTime", -1);
            if (j2 > 1800) {
                j2 = 0;
            }
            long j3 = MultiSpHelper.getLong(context, "TAFreezeSetTime", -1);
            if (j2 == -1 || j3 == -1) {
                obj = null;
            } else if (System.currentTimeMillis() - j3 < 0 || System.currentTimeMillis() - j3 > j2) {
                MultiSpHelper.commitLong(context, "TAFreezeSetTime", -1);
                MultiSpHelper.commitLong(context, "TAFreezeEndTime", -1);
                e.g("JPushCommon", "incorrect timestamp");
                int i2 = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                e.g("TagAliasNewProtoRetryHelper", "tag/alias action was freezed");
                k.a(context, i, cn.jpush.android.api.JPushInterface.a.u, j);
                return true;
            }
        }
        return false;
    }
}
