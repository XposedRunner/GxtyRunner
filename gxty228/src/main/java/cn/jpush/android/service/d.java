package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.a;
import cn.jpush.android.d.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class d {
    private static volatile d a;
    private static final Object d = new Object();
    private TagAliasReceiver b;
    private ConcurrentHashMap<Long, a> c = new ConcurrentHashMap();
    private AtomicBoolean e = new AtomicBoolean(false);

    public static d a() {
        if (a == null) {
            synchronized (d) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    private d() {
    }

    public final void a(Context context, Long l, a aVar) {
        b(context);
        this.c.put(l, aVar);
    }

    private a a(long j) {
        return (a) this.c.get(Long.valueOf(j));
    }

    private void b(long j) {
        this.c.remove(Long.valueOf(j));
    }

    private void b(Context context) {
        if (this.c != null && !this.c.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Entry entry : this.c.entrySet()) {
                if (((a) entry.getValue()).a(20000)) {
                    arrayList.add(entry.getKey());
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Long l = (Long) it.next();
                e.g("TagAliasOperator", "cleanTimeOutCallback timeout rid:" + l);
                a(context, l.longValue());
            }
        }
    }

    public final synchronized void a(Context context) {
        if (this.e.get()) {
            e.c("TagAliasOperator", "tag alias callback register is called");
        } else {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addCategory(cn.jpush.android.a.c);
                intentFilter.addAction("cn.jpush.android.intent.TAG_ALIAS_TIMEOUT");
                intentFilter.addAction("cn.jpush.android.intent.TAG_ALIAS_CALLBACK");
                if (this.b == null) {
                    this.b = new TagAliasReceiver();
                }
                context.registerReceiver(this.b, intentFilter);
                this.e.set(true);
            } catch (Exception e) {
                e.i("TagAliasOperator", "setTagAndAlias e:" + e.getMessage());
            }
        }
    }

    private synchronized void c(Context context) {
        b(context);
        if (this.e.get() && this.c != null && this.c.isEmpty()) {
            try {
                if (this.b != null) {
                    context.unregisterReceiver(this.b);
                    this.b = null;
                }
            } catch (Throwable e) {
                e.d("TagAliasOperator", "Receiver not registered, cannot call unregisterReceiver", e);
            } catch (Throwable e2) {
                e.d("TagAliasOperator", "other exception", e2);
            }
            this.e.set(false);
            e.a("TagAliasOperator", "unRegister tag alias callback");
        } else {
            e.a("TagAliasOperator", "tagAliasCallbacks is not empty");
        }
    }

    public final void a(Context context, long j, int i, Intent intent) {
        e.a("TagAliasOperator", "action - onTagAliasResponse rid:" + j + " tagAliasCallbacks :" + String.valueOf(a().c));
        if ("cn.jpush.android.intent.TAG_ALIAS_TIMEOUT".equals(intent.getAction())) {
            a(context, j);
        } else {
            e.a("TagAliasOperator", "SetAliasAndTags finish : errorCode:" + i + " rid:" + j);
            a a = a(j);
            if (a == null) {
                e.g("TagAliasOperator", "tagalias callback is null; rid=" + j);
            } else {
                a().b(j);
                if (intent != null) {
                    try {
                        if (a.f == 5) {
                            if (a.e == 1) {
                                Collection stringArrayListExtra = intent.getStringArrayListExtra("tags");
                                if (stringArrayListExtra != null) {
                                    a.b = new HashSet(stringArrayListExtra);
                                    e.c("TagAliasOperator", "all tags was loaded, value:" + a.b);
                                }
                            } else if (a.e == 2) {
                                a.a = intent.getStringExtra("alias");
                                e.c("TagAliasOperator", "alias was loaded, value:" + a.b);
                            }
                        } else if (a.f == 6) {
                            intent.getBooleanExtra("validated", false);
                        }
                    } catch (Throwable th) {
                        e.c("TagAliasOperator", "get tag or alias failed - error:" + th);
                    }
                }
                a(a, i);
            }
        }
        c(context);
    }

    public final JPushMessage a(Intent intent) {
        boolean z = false;
        long longExtra = intent.getLongExtra("tagalias_seqid", -1);
        int intExtra = intent.getIntExtra("tagalias_errorcode", 0);
        e.a("TagAliasOperator", "parseTagAliasResponse2JPushMessage, errorCode:" + intExtra + " rid:" + longExtra);
        a a = a(longExtra);
        if (a == null) {
            e.g("TagAliasOperator", "tagalias callback is null; rid=" + longExtra);
            return null;
        }
        a().b(longExtra);
        if (intExtra == 0) {
            try {
                if (a.f == 5) {
                    if (a.e == 1) {
                        Collection stringArrayListExtra = intent.getStringArrayListExtra("tags");
                        if (stringArrayListExtra != null) {
                            a.b = new HashSet(stringArrayListExtra);
                            e.c("TagAliasOperator", "all tags was loaded, value:" + a.b);
                        }
                    } else if (a.e == 2) {
                        a.a = intent.getStringExtra("alias");
                        e.c("TagAliasOperator", "alias was loaded, value:" + a.b);
                    }
                } else if (a.f == 6) {
                    z = intent.getBooleanExtra("validated", false);
                }
            } catch (Throwable th) {
                e.c("TagAliasOperator", "get tag or alias failed - error:" + th);
            }
        }
        JPushMessage jPushMessage = new JPushMessage();
        jPushMessage.setErrorCode(intExtra);
        jPushMessage.setSequence(a.d);
        if (a.e != 1) {
            jPushMessage.setAlias(a.a);
        } else if (a.f == 6) {
            jPushMessage.setCheckTag(a(a));
            jPushMessage.setTagCheckStateResult(z);
            jPushMessage.setTagCheckOperator(true);
        } else {
            jPushMessage.setTags(a.b);
        }
        return jPushMessage;
    }

    private void a(Context context, long j) {
        e.a("TagAliasOperator", "action - onTimeout rid:" + j);
        a a = a(j);
        if (a == null) {
            e.g("TagAliasOperator", "tagalias callback is null; rid=" + j);
            return;
        }
        a(a, JPushInterface.a.c);
        b(j);
    }

    private static void a(a aVar, int i) {
        e.c("TagAliasOperator", "action - invokeUserCallback, errorCode:" + i + ",callBack:" + aVar);
        if (aVar.e != 0) {
            e.g("TagAliasOperator", "new proto type do not call user callback");
        } else if (aVar.c != null) {
            aVar.c.gotResult(i, aVar.a, aVar.b);
        }
    }

    private static String a(a aVar) {
        if (aVar == null) {
            e.g("TagAliasOperator", "callBack was null");
            return null;
        }
        try {
            if (aVar.b != null && aVar.b.size() > 0) {
                return (String) aVar.b.toArray()[0];
            }
        } catch (Throwable th) {
            e.c("TagAliasOperator", "get origin check tag failed, error:" + th);
        }
        return null;
    }

    public final void a(Context context, int i, long j, a aVar) {
        if (aVar == null) {
            return;
        }
        if (aVar.e != 0) {
            try {
                e.c("TagAliasOperator", "Action - onTagaliasTimeout");
                Intent intent = new Intent();
                intent.addCategory(cn.jpush.android.a.c);
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                intent.setPackage(context.getPackageName());
                if (aVar.e == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
                intent.putExtra("tagalias_errorcode", i);
                intent.putExtra("tagalias_seqid", j);
                context.sendBroadcast(intent);
            } catch (Throwable th) {
                e.h("TagAliasOperator", "onTagaliasTimeout error:" + th.getMessage());
            }
        } else if (aVar.c != null) {
            aVar.c.gotResult(i, aVar.a, aVar.b);
            b(j);
        }
    }
}
