package cn.jiguang.d.b;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.jiguang.api.JAction;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jiguang.d.e.a.a.b;
import cn.jiguang.d.e.a.a.c;
import cn.jiguang.e.d;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public final class g {
    private static g f;
    private Map<String, h> a = new ConcurrentHashMap();
    private Deque<h> b = new LinkedBlockingDeque();
    private Deque<h> c = new LinkedBlockingDeque();
    private Handler d;
    private Context e;

    public static g a() {
        if (f == null) {
            f = new g();
        }
        return f;
    }

    private synchronized h a(String str) {
        h hVar;
        d.a("RequestCacheManager", "Action - dequeSentQueue");
        hVar = null;
        for (h hVar2 : this.c) {
            h hVar22;
            if (str.equals(hVar22.a())) {
                this.c.remove(hVar22);
            } else {
                hVar22 = hVar;
            }
            hVar = hVar22;
        }
        return hVar;
    }

    private static String b(long j, String str) {
        return j + "|" + str;
    }

    private synchronized void c(h hVar) {
        d.a("RequestCacheManager", "Action - enqueSentQueue");
        if (hVar == null) {
            d.g("RequestCacheManager", "enqueSentQueue requesting is null");
        } else {
            Object obj;
            for (h a : this.c) {
                if (a.a().equals(hVar.a())) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                this.c.offerLast(hVar);
            }
        }
    }

    private void d(h hVar) {
        if (hVar == null) {
            d.g("RequestCacheManager", "endRequestTimeout requesting is null");
            return;
        }
        d.a("RequestCacheManager", "Action - endRequestTimeout:" + hVar.a());
        if (((h) this.a.remove(hVar.a())) == null) {
            d.g("RequestCacheManager", "Unexpected - failed to remove requesting from cache.");
        }
        this.b.remove(hVar);
        this.d.removeMessages(7403, hVar);
    }

    private void e(h hVar) {
        if (hVar == null) {
            d.c("RequestCacheManager", "sendCommandWithLoggedIn failed,requesting is null");
            return;
        }
        try {
            int a = cn.jiguang.d.a.d.a();
            long d = cn.jiguang.d.a.d.d(null);
            byte[] bArr = hVar.d;
            d.c("CorePackage", "action - correctDataSidAndUid,sid:" + a + ",uid:" + d);
            OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
            outputDataUtil.writeByteArray(bArr);
            outputDataUtil.writeU32At((long) a, 12);
            if (d != 0) {
                outputDataUtil.writeU64At(d, 16);
            }
            byte[] a2 = b.a(outputDataUtil.toByteArray(), 1);
            if (a2 == null || f.a.get() == 0) {
                d.h("RequestCacheManager", "sendCommandWithLoggedIn failed:sendData is null");
                c(hVar);
                if (hVar != null) {
                    d.g("RequestCacheManager", "startSentTimeout failed,requesting is null");
                }
                d.a("RequestCacheManager", "Action - startSentTimeout :" + hVar.a());
                this.d.sendMessageDelayed(Message.obtain(this.d, 7404, hVar), 9800);
                return;
            }
            cn.jiguang.d.f.d.a().b().a(a2);
            c(hVar);
            if (hVar != null) {
                d.a("RequestCacheManager", "Action - startSentTimeout :" + hVar.a());
                this.d.sendMessageDelayed(Message.obtain(this.d, 7404, hVar), 9800);
                return;
            }
            d.g("RequestCacheManager", "startSentTimeout failed,requesting is null");
        } catch (Throwable th) {
            d.g("RequestCacheManager", "Send data failed - error:" + th);
        }
    }

    public final void a(long j, String str, Object obj) {
        if (obj == null) {
            d.g("RequestCacheManager", "handleResponseInternal obj  is null");
        } else if (obj instanceof c) {
            c cVar = (c) obj;
            d.c("RequestCacheManager", "Action - handleResponseInternal - connection:" + j + ", response:" + cVar.toString() + ",sdktype:" + str);
            if (j != f.a.get()) {
                d.g("RequestCacheManager", "Response connection is out-dated. ");
            }
            h a = a(b(cVar.b().longValue(), str));
            if (a == null) {
                d.g("RequestCacheManager", "Not found the request in SentQueue when response.");
                return;
            }
            if (a == null) {
                d.g("RequestCacheManager", "endSentTimeout failed,requesting is null");
            } else {
                d.a("RequestCacheManager", "Action - endSentTimeout - requestKey:" + a.a());
                this.d.removeMessages(7404, a);
            }
            a = (h) this.a.get(a.a());
            if (a != null) {
                d(a);
            } else {
                d.g("RequestCacheManager", "Not found requesting in RequestingCache when response.");
            }
        } else {
            d.g("RequestCacheManager", "handleResponseInternal obj  is not JHead");
        }
    }

    public final void a(Context context, Handler handler) {
        this.e = context;
        this.d = handler;
    }

    public final void a(h hVar) {
        if (hVar == null) {
            d.g("RequestCacheManager", "onSentTimeout requesting is null");
            return;
        }
        d.a("RequestCacheManager", "Action - onSentTimeout - " + hVar.toString());
        if (a(hVar.a()) == null) {
            d.g("RequestCacheManager", "not found sent request in SentQueue, drop it");
        } else if (hVar.a > 0) {
            d.a();
            if (d.d()) {
                d.a("RequestCacheManager", "Retry to send request - " + hVar.toString());
                hVar.a -= 10000;
                hVar.b++;
                e(hVar);
            } else {
                d.a("RequestCacheManager", "Want retry to send but not logged in. Sent move to RequestingQueue");
                this.b.offerFirst(hVar);
            }
            if (hVar.b >= 2) {
                this.d.sendEmptyMessageDelayed(1005, 2000);
            }
        } else {
            b(hVar);
        }
    }

    public final void a(String str, Object obj) {
        Message obtain = Message.obtain(this.d, 7402, obj);
        Bundle bundle = new Bundle();
        bundle.putLong("connection", f.a.get());
        bundle.putString("request_sdktype", str);
        obtain.setData(bundle);
        obtain.sendToTarget();
    }

    public final void a(byte[] bArr, String str, int i) {
        if (bArr == null) {
            d.g("RequestCacheManager", "sendRequest failed,request data is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("request_timeout", i);
        bundle.putByteArray("request_data", bArr);
        bundle.putString("request_sdktype", str);
        Message obtain = Message.obtain(this.d, 7401);
        obtain.setData(bundle);
        obtain.sendToTarget();
    }

    public final void b() {
        d.c("RequestCacheManager", "Action - restoreSentQueue - sentQueueSize:" + this.c.size());
        this.d.removeMessages(7404);
        while (true) {
            h hVar = (h) this.c.pollLast();
            if (hVar != null) {
                this.b.offerFirst(hVar);
            } else {
                return;
            }
        }
    }

    public final void b(h hVar) {
        if (hVar == null) {
            d.g("RequestCacheManager", "onRequestTimeout requesting is null");
            return;
        }
        d.a("RequestCacheManager", "Action - onRequestTimeout - " + hVar.toString());
        d(hVar);
        cn.jiguang.d.d.b.a();
        Context context = this.e;
        Object obj = hVar.f;
        long j = hVar.c;
        int i = hVar.e;
        if (TextUtils.isEmpty(obj)) {
            d.c("ActionManager", "sdktype is null,will dispatchTimeoutRequest to others");
            cn.jiguang.d.d.b.b(context, j, i);
            return;
        }
        JAction jAction = (JAction) cn.jiguang.d.d.b.a.get(obj);
        if (jAction == null) {
            d.c("ActionManager", "can not found action with" + obj + ",will dispatchTimeoutRequest to others");
            cn.jiguang.d.d.b.b(context, j, i);
            return;
        }
        jAction.dispatchTimeOutMessage(context, f.a.get(), j, i);
    }

    public final void b(byte[] bArr, String str, int i) {
        d.d("RequestCacheManager", "Action - sendRequestInternal - connection:" + f.a.get() + ", timeout:" + i + ",sdkType:" + str + ", threadId:" + Thread.currentThread().getId());
        if (bArr == null) {
            d.g("RequestCacheManager", "sendRequestInternal failed,request data is null");
        } else if (this.a.size() > 200) {
            d.h("RequestCacheManager", "sendRequestInternal failed,cache is full");
        } else {
            h hVar = new h(bArr, str, i);
            this.a.put(hVar.a(), hVar);
            if (i > ByteBufferUtils.ERROR_CODE) {
                d.a("RequestCacheManager", "Action - startRequestTimeout");
                this.d.sendMessageDelayed(Message.obtain(this.d, 7403, hVar), (long) hVar.a);
            }
            e(hVar);
        }
    }

    public final void c() {
        d.a("RequestCacheManager", "Action - resendRequestingQueue - size:" + this.b.size());
        while (true) {
            h hVar = (h) this.b.pollFirst();
            if (hVar == null) {
                return;
            }
            if (hVar.e == 2) {
                this.b.remove(hVar);
                this.a.remove(hVar.a());
            } else {
                e(hVar);
            }
        }
    }
}
