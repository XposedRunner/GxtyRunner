package cn.jiguang.d.e.a;

import android.support.v4.view.PointerIconCompat;
import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.api.utils.ProtocolUtil;
import cn.jiguang.c.a;
import cn.jiguang.d.e.a.a.c;
import cn.jiguang.e.d;
import java.nio.ByteBuffer;

public final class e extends JResponse {
    long a;
    String b;
    String c;
    String d;
    String e;
    String f;

    public e(c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final long getJuid() {
        return this.a;
    }

    public final String getName() {
        return "RegisterResponse";
    }

    protected final boolean isNeedParseeErrorMsg() {
        return true;
    }

    protected final void parseBody() {
        super.parseBody();
        if (this.code > 0) {
            d.i("RegisterResponse", "Response error - code:" + this.code);
        }
        ByteBuffer byteBuffer = this.body;
        if (this.code == 0) {
            this.a = ByteBufferUtils.getLong(byteBuffer, this);
            this.b = ProtocolUtil.getTlv2(byteBuffer, this);
            this.c = ProtocolUtil.getTlv2(byteBuffer, this);
        } else if (this.code == PointerIconCompat.TYPE_CROSSHAIR) {
            this.e = ProtocolUtil.getTlv2(byteBuffer, this);
        } else if (this.code == PointerIconCompat.TYPE_NO_DROP) {
            this.f = ProtocolUtil.getTlv2(byteBuffer, this);
            a.a(this.f);
        }
    }

    public final String toString() {
        return "[RegisterResponse] - juid:" + this.a + ", password:" + this.b + ", regId:" + this.c + ", deviceId:" + this.d + ", connectInfo:" + this.f + " - " + super.toString();
    }

    protected final void writeBody() {
        super.writeBody();
        writeLong8(this.a);
        writeTlv2(this.b);
        writeTlv2(this.c);
        writeTlv2(this.d);
    }
}
