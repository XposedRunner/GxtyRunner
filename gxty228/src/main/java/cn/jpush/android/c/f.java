package cn.jpush.android.c;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jpush.android.b;
import cn.jpush.android.d.e;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class f {
    private static volatile f b;
    private Map<Byte, a> a = new HashMap();

    private class a {
        public byte a;
        public String b;
        public long c;
        public byte[] d;
        public int e = 0;
        final /* synthetic */ f f;

        public a(f fVar, byte b, String str, long j, byte[] bArr) {
            this.f = fVar;
            this.a = b;
            this.b = str;
            this.c = j;
            this.d = bArr;
        }

        public final String toString() {
            return "PluginPlatformRegIDBean{pluginPlatformType=" + this.a + ", regid='" + this.b + '\'' + ", rid=" + this.c + ", retryCount=" + this.e + '}';
        }
    }

    private f() {
    }

    public static f a() {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    b = new f();
                }
            }
        }
        return b;
    }

    public final synchronized void a(Context context, Bundle bundle) {
        byte byteValue = bundle.getByte("plugin.platform.type", (byte) 0).byteValue();
        if (byteValue == (byte) 0) {
            e.g("PluginPlatformRidUpdate", "unknown plugin platform type");
        } else if (JCoreInterface.isTcpConnected()) {
            String string = bundle.getString("plugin.platform.regid ");
            if (this.a.containsKey(Byte.valueOf(byteValue)) && TextUtils.equals(((a) this.a.get(Byte.valueOf(byteValue))).b, string)) {
                e.c("PluginPlatformRidUpdate", "same regid request, drop it");
            } else {
                long nextRid = JCoreInterface.getNextRid();
                e.a("PluginPlatformRidUpdate", "sendUpdateRidInfo regid:" + string + ",rid:" + nextRid);
                long uid = JCoreInterface.getUid();
                int sid = JCoreInterface.getSid();
                OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
                outputDataUtil.writeU16(0);
                outputDataUtil.writeU8(1);
                outputDataUtil.writeU8(27);
                outputDataUtil.writeU64(nextRid);
                outputDataUtil.writeU32((long) sid);
                outputDataUtil.writeU64(uid);
                outputDataUtil.writeByteArrayincludeLength(TextUtils.isEmpty(string) ? new byte[0] : string.getBytes());
                outputDataUtil.writeU8(byteValue);
                outputDataUtil.writeU16At(outputDataUtil.current(), 0);
                a aVar = new a(this, byteValue, string, nextRid, outputDataUtil.toByteArray());
                this.a.put(Byte.valueOf(byteValue), aVar);
                a(context, aVar);
            }
        } else {
            e.c("PluginPlatformRidUpdate", "tcp disconnected");
        }
    }

    private synchronized void a(Context context, a aVar) {
        JCoreInterface.sendRequestData(context, cn.jpush.android.a.a, ByteBufferUtils.ERROR_CODE, aVar.d);
    }

    public final void a(Context context, long j, int i) {
        a a = a(j);
        e.f("PluginPlatformRidUpdate", "onUpdateRidFailed rid:" + j + ",errorCode:" + i + " ,pluginPlatformRegIDBean:" + String.valueOf(a));
        if (a == null) {
            return;
        }
        if (a.e < 3) {
            a.e++;
            a(context, a);
            return;
        }
        e.c("PluginPlatformRidUpdate", "updateRegId failed");
        this.a.remove(Byte.valueOf(a.a));
    }

    private a a(long j) {
        for (Entry entry : this.a.entrySet()) {
            if (((a) entry.getValue()).c == j) {
                return (a) entry.getValue();
            }
        }
        e.c("PluginPlatformRidUpdate", "can not find uploadBean by rid");
        return null;
    }

    public final void a(Context context, long j) {
        a a = a(j);
        e.f("PluginPlatformRidUpdate", "onUpdateRidSuccess rid:" + j + " ,pluginPlatformRegIDBean:" + String.valueOf(a));
        if (a != null) {
            b.a(context, a.a, a.b);
            b.b(context, a.a, true);
            this.a.remove(Byte.valueOf(a.a));
        }
    }

    public final void b(Context context, long j) {
        a a = a(j);
        e.f("PluginPlatformRidUpdate", "onUpdateRidTimeout rid:" + j + " ,pluginPlatformRegIDBean:" + String.valueOf(a));
        if (a == null) {
            return;
        }
        if (a.e < 3) {
            a.e++;
            a(context, a);
            return;
        }
        e.c("PluginPlatformRidUpdate", "updateRegId failed by timeout");
        this.a.remove(Byte.valueOf(a.a));
    }
}
