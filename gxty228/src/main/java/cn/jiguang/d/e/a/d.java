package cn.jiguang.d.e.a;

import android.support.v4.view.PointerIconCompat;
import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.api.utils.ProtocolUtil;
import cn.jiguang.c.a;
import cn.jiguang.d.e.a.a.c;
import java.nio.ByteBuffer;

public final class d extends JResponse {
    int a;
    int b;
    String c;
    int d;
    int e;
    String f;

    public d(c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    public final int a() {
        return this.d;
    }

    public final String getName() {
        return "LoginResponse";
    }

    public final int getSid() {
        return this.a;
    }

    protected final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public final void parseBody() {
        super.parseBody();
        if (this.code > 0) {
            cn.jiguang.e.d.i("LoginResponse", "Response error - code:" + this.code);
        }
        ByteBuffer byteBuffer = this.body;
        this.e = -1;
        if (this.code == 0) {
            this.a = ByteBufferUtils.getInt(byteBuffer, this);
            this.b = ByteBufferUtils.getShort(byteBuffer, this);
            this.c = ProtocolUtil.getTlv2(byteBuffer, this);
            this.d = ByteBufferUtils.getInt(byteBuffer, this);
            try {
                this.e = byteBuffer.get();
                cn.jiguang.e.d.c("LoginResponse", "idc parse success, value:" + this.e);
            } catch (Throwable th) {
                cn.jiguang.e.d.g("LoginResponse", "parse idc failed, error:" + th);
            }
        } else if (this.code == PointerIconCompat.TYPE_NO_DROP) {
            this.f = ProtocolUtil.getTlv2(byteBuffer, this);
            a.a(this.f);
        }
        cn.jiguang.d.a.a.a(this.e);
    }

    public final String toString() {
        return "[LoginResponse] - sid:" + this.a + ", serverVersion:" + this.b + ", sessionKey:" + this.c + ", serverTime:" + this.d + ", idc:" + this.e + ", connectInfo:" + this.f + " - " + super.toString();
    }

    public final void writeBody() {
        super.writeBody();
        writeInt4(this.a);
        writeInt2(this.b);
        writeTlv2(this.c);
        writeInt4(this.d);
    }
}
