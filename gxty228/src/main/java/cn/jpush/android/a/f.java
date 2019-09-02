package cn.jpush.android.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.MultiSpHelper;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.d.e;
import cn.jpush.android.d.g;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class f {
    private static ConcurrentLinkedQueue<Long> a = new ConcurrentLinkedQueue();
    private static final Object c = new Object();
    private static f d;
    private ConcurrentHashMap<Long, a> b = new ConcurrentHashMap();

    private class a {
        public int a;
        public String b;
        final /* synthetic */ f c;

        public a(f fVar, int i, String str) {
            this.c = fVar;
            this.a = i;
            this.b = str;
        }

        public final String toString() {
            return "MobileBean{sequence=" + this.a + ", mobileNumber='" + this.b + '\'' + '}';
        }
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (d == null) {
                synchronized (c) {
                    if (d == null) {
                        d = new f();
                    }
                }
            }
            fVar = d;
        }
        return fVar;
    }

    public final void a(Context context, Bundle bundle) {
        int i = bundle.getInt("sequence", 0);
        String string = bundle.getString("mobile_number");
        CharSequence string2 = MultiSpHelper.getString(context, "mobile_number", null);
        e.c("MobileNumberHelper", "sequence:" + i + ",mobileNumber:" + string + ",lastMobileNumber:" + string2);
        if (string2 == null || !TextUtils.equals(string, string2)) {
            if (string2 != null) {
                MultiSpHelper.commitString(context, "mobile_number", null);
            }
            int a = a(System.currentTimeMillis());
            if (a != 0) {
                if (a == 1) {
                    e.g("MobileNumberHelper", "set mobile number too soon,over 3 times in 10s");
                } else {
                    e.g("MobileNumberHelper", "set mobile number failed,time shaft error，please try again");
                }
                a(context, i, a == 1 ? cn.jpush.android.api.JPushInterface.a.l : cn.jpush.android.api.JPushInterface.a.n, string);
                return;
            }
            a = g.c(string);
            if (a != 0) {
                e.d("MobileNumberHelper", "Invalid mobile number: " + string + ", will not set mobile number this time.");
                a(context, i, a, string);
                return;
            }
            long nextRid = JCoreInterface.getNextRid();
            long uid = JCoreInterface.getUid();
            a = JCoreInterface.getSid();
            OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
            outputDataUtil.writeU16(0);
            outputDataUtil.writeU8(1);
            outputDataUtil.writeU8(26);
            outputDataUtil.writeU64(nextRid);
            outputDataUtil.writeU32((long) a);
            outputDataUtil.writeU64(uid);
            outputDataUtil.writeU8(7);
            outputDataUtil.writeU8(1);
            outputDataUtil.writeByteArrayincludeLength(string != null ? string.getBytes() : new byte[0]);
            outputDataUtil.writeU16At(outputDataUtil.current(), 0);
            byte[] toByteArray = outputDataUtil.toByteArray();
            this.b.put(Long.valueOf(nextRid), new a(this, i, string));
            JCoreInterface.sendRequestData(context, cn.jpush.android.a.a, 20000, toByteArray);
            return;
        }
        e.d("MobileNumberHelper", "already set this mobile number");
        a(context, i, cn.jpush.android.api.JPushInterface.a.a, string);
    }

    public static void a(Context context, int i, int i2, String str) {
        try {
            e.c("MobileNumberHelper", "Action - onResult,sequence:" + i + ",code:" + i2 + ",mobileNumber:" + str);
            Intent intent = new Intent();
            intent.addCategory(cn.jpush.android.a.c);
            intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
            intent.setPackage(context.getPackageName());
            intent.putExtra("message_type", 3);
            intent.putExtra("sequence", i);
            intent.putExtra("code", i2);
            intent.putExtra("mobile_number", str);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            e.h("MobileNumberHelper", "onResult error:" + th);
        }
    }

    private static int a(long j) {
        if (a.size() < 3) {
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
            while (a.size() >= 3) {
                a.poll();
            }
            a.offer(Long.valueOf(j));
            return 0;
        }
    }

    public final void a(Context context, long j, int i) {
        if (this.b.size() != 0) {
            a aVar = (a) this.b.remove(Long.valueOf(j));
            e.c("MobileNumberHelper", "mobileBean:" + aVar);
            if (aVar != null) {
                if (i == 0) {
                    MultiSpHelper.commitString(context, "mobile_number", aVar.b);
                } else if (i == 11) {
                    i = cn.jpush.android.api.JPushInterface.a.y;
                } else if (i == 10) {
                    i = cn.jpush.android.api.JPushInterface.a.z;
                }
                a(context, aVar.a, i, aVar.b);
                return;
            }
            e.c("MobileNumberHelper", "#unexcepted, do not find mobile number request cache");
        }
    }

    public static JPushMessage a(Intent intent) {
        JPushMessage jPushMessage;
        Object th;
        if (intent == null) {
            return null;
        }
        try {
            int intExtra = intent.getIntExtra("sequence", -1);
            int intExtra2 = intent.getIntExtra("code", -1);
            String stringExtra = intent.getStringExtra("mobile_number");
            jPushMessage = new JPushMessage();
            try {
                jPushMessage.setSequence(intExtra);
                jPushMessage.setErrorCode(intExtra2);
                jPushMessage.setMobileNumber(stringExtra);
                return jPushMessage;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            jPushMessage = null;
            th = th4;
            e.g("MobileNumberHelper", "parese mobile number response to JPushMessage failed, error:" + th);
            return jPushMessage;
        }
    }
}
