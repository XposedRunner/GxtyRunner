package cn.jpush.android.a;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jpush.a.b;
import cn.jpush.android.a;
import cn.jpush.android.d.e;
import cn.jpush.android.d.f;
import cn.jpush.android.d.j;
import cn.jpush.android.data.c;
import cn.jpush.android.service.ServiceInterface;
import java.io.LineNumberReader;
import java.io.StringReader;

public final class h {
    public static long a(Context context, long j, JResponse jResponse) {
        b bVar = (b) jResponse;
        int a = bVar.a();
        long b = bVar.b();
        long longValue = bVar.getRid().longValue();
        long uid = JCoreInterface.getUid();
        byte b2 = (byte) a;
        int sid = JCoreInterface.getSid();
        OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(1);
        outputDataUtil.writeU8(4);
        outputDataUtil.writeU64(longValue);
        outputDataUtil.writeU32((long) sid);
        outputDataUtil.writeU64(uid);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(b2);
        outputDataUtil.writeU64(b);
        outputDataUtil.writeU16At(outputDataUtil.current(), 0);
        JCoreInterface.sendData(a.e, a.a, 4, outputDataUtil.toByteArray());
        longValue = bVar.b();
        int a2 = bVar.a();
        String c = bVar.c();
        e.c("PushMessageProcessor", "msgType = " + a2 + ", msgId = " + longValue);
        e.a("PushMessageProcessor", "msgContent: \n" + c);
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(c));
        try {
            Object readLine = lineNumberReader.readLine();
            if (readLine == null) {
                e.i("PushMessageProcessor", "NO appId");
                return -1;
            }
            Object readLine2 = lineNumberReader.readLine();
            if (readLine2 == null) {
                e.i("PushMessageProcessor", "NO senderId");
                return -1;
            }
            String substring;
            int length = (readLine.length() + readLine2.length()) + 2;
            if (c.length() > length + 1) {
                substring = c.substring(length);
            } else {
                e.c("PushMessageProcessor", "No msgContent");
                substring = "";
            }
            e.a("PushMessageProcessor", "Message Fields - appId:" + readLine + ", senderId:" + readLine2 + ", msgContent:" + substring);
            switch (a2) {
                case 0:
                case 2:
                    try {
                        e.c("PushMessageProcessor", "parseNormal -  msgId:" + longValue);
                        j jVar = new j("PushMessageProcessor", "Time to process received msg.");
                        if (TextUtils.isEmpty(readLine) || TextUtils.isEmpty(readLine2)) {
                            e.g("PushMessageProcessor", "Empty senderid or appid. Give up parser.");
                        } else if (TextUtils.isEmpty(substring)) {
                            e.i("PushMessageProcessor", "Empty msg. Give up parser.");
                        } else {
                            a(context, readLine, readLine2, substring, longValue, (byte) 0);
                        }
                        jVar.a();
                        break;
                    } catch (Throwable e) {
                        e.e("PushMessageProcessor", "Unknown error", e);
                        break;
                    }
                    break;
                case 20:
                    return k.a(context, substring, 0, -1);
                default:
                    JCoreInterface.processCtrlReport(a2);
                    break;
            }
            return jResponse.getRid().longValue();
        } catch (Throwable e2) {
            e.e("PushMessageProcessor", "Parse msgContent failed", e2);
            return -1;
        }
    }

    public static void a(Context context, String str, String str2, String str3, long j, byte b) {
        e.c("PushMessageProcessor", "action:receivedPushMessage msgId = " + j);
        cn.jpush.android.data.b a = g.a(context, str3, str, str2, j);
        if (a != null && !f.a(context, new c(a))) {
            a.e = b;
            int i = 0;
            if (str2.equalsIgnoreCase("7fef6a7d76c782b1f0eda446b2c6c40a")) {
                g.a(context, (cn.jpush.android.data.a) a);
            } else if (!a.f) {
                i = 2;
            } else if (a.b == 4) {
                i = 3;
            } else {
                i = 1;
            }
            new StringBuilder().append(j);
            e.c("PushMessageProcessor", "processBasicEntity type:" + i);
            if ((i & 1) != 0) {
                e.c("PushMessageProcessor", "processBasicEntity notification");
                if (ServiceInterface.d(context)) {
                    e.e("PushMessageProcessor", "Service is stoped, give up all the message");
                    return;
                } else if (cn.jpush.android.d.a.c(context)) {
                    a.i = true;
                    g.a(context, (cn.jpush.android.data.a) a);
                } else {
                    e.e("PushMessageProcessor", "push is closed，Intercept the message");
                    return;
                }
            }
            if ((i & 2) != 0) {
                e.c("PushMessageProcessor", "processBasicEntity user-defined message.");
                if (!TextUtils.isEmpty(a.j) || !TextUtils.isEmpty(a.n)) {
                    cn.jpush.android.d.a.a(context, a);
                }
            }
        }
    }
}
