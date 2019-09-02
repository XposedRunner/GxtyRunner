package cn.jpush.android.a;

import android.content.Context;
import android.content.Intent;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.JRequest;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jpush.a.c;
import cn.jpush.android.a;
import cn.jpush.android.api.JPushInterface;

public final class e {
    private static e b = null;
    private Context a = null;

    private e(Context context) {
        this.a = context;
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e(context);
            }
            eVar = b;
        }
        return eVar;
    }

    public final void a(JRequest jRequest, int i) {
        if (jRequest == null) {
            cn.jpush.android.d.e.g("JPushRequestHelper", "sendRequest failed,request is null");
            return;
        }
        cn.jpush.android.d.e.d("JPushRequestHelper", "Action - sendJPushRequest, timeout:" + 20000 + ", threadId:" + Thread.currentThread().getId());
        cn.jpush.android.d.e.a("JPushRequestHelper", jRequest.toString());
        Long rid = jRequest.getRid();
        int command = jRequest.getCommand();
        long uid = JCoreInterface.getUid();
        int sid = JCoreInterface.getSid();
        cn.jpush.android.d.e.c("JPushRequestHelper", "Request params - cmd:" + command + ", sid:" + sid + ", juid:" + uid + ",rid:" + rid);
        switch (command) {
            case 10:
            case 28:
            case 29:
                long longValue = rid.longValue();
                String appKey = JCoreInterface.getAppKey();
                short version = (short) jRequest.getVersion();
                short command2 = (short) jRequest.getCommand();
                String a = ((c) jRequest).a();
                OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
                outputDataUtil.writeU16(0);
                outputDataUtil.writeU8(version);
                outputDataUtil.writeU8(command2);
                outputDataUtil.writeU64(longValue);
                outputDataUtil.writeU32((long) sid);
                outputDataUtil.writeU64(uid);
                if (command2 == (short) 10) {
                    outputDataUtil.writeByteArrayincludeLength(appKey.getBytes());
                }
                outputDataUtil.writeByteArrayincludeLength(a.getBytes());
                outputDataUtil.writeU16At(outputDataUtil.current(), 0);
                JCoreInterface.sendRequestData(this.a, a.a, 20000, outputDataUtil.toByteArray());
                return;
            default:
                cn.jpush.android.d.e.g("JPushRequestHelper", "Unprocessed request yet.");
                return;
        }
    }

    public final void a(long j) {
        try {
            cn.jpush.android.d.e.c("JPushRequestHelper", "Action - onTagaliasTimeout");
            int a = l.a().a(j);
            Intent intent = new Intent();
            intent.addCategory(a.c);
            intent.setPackage(this.a.getPackageName());
            if (a == 0) {
                intent.setAction("cn.jpush.android.intent.TAG_ALIAS_TIMEOUT");
            } else {
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                if (a == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
            }
            intent.putExtra("tagalias_errorcode", JPushInterface.a.c);
            intent.putExtra("tagalias_seqid", j);
            this.a.sendBroadcast(intent);
        } catch (Throwable th) {
            cn.jpush.android.d.e.h("JPushRequestHelper", "onTagaliasTimeout error:" + th.getMessage());
        }
    }
}
