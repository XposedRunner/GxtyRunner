package cn.jiguang.d.f;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.jiguang.e.d;

public final class b extends Handler {
    final /* synthetic */ a a;

    public b(a aVar, Looper looper) {
        this.a = aVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        byte[] bArr;
        try {
            bArr = (byte[]) message.obj;
        } catch (Exception e) {
            d.h("BaseSocket", "#unexcepted - get send data failed e:" + e.getMessage());
            bArr = null;
        }
        this.a.a(bArr);
    }
}
