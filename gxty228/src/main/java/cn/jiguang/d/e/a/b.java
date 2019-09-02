package cn.jiguang.d.e.a;

import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.api.utils.ProtocolUtil;
import cn.jiguang.d.e.a.a.c;
import java.nio.ByteBuffer;

public final class b extends JResponse {
    long a;
    String b;

    public b(c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    public final long a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String getName() {
        return "CtrlResponse";
    }

    protected final boolean isNeedParseeErrorMsg() {
        return false;
    }

    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        this.a = ByteBufferUtils.getLong(byteBuffer, this);
        this.b = ProtocolUtil.getTlv2(byteBuffer, this);
    }

    public final String toString() {
        return "[CtrlResponse] - senderId:" + this.a + ", msgContent:" + this.b + " - " + super.toString();
    }

    public final void writeBody() {
        super.writeBody();
        writeLong8(this.a);
        writeTlv2(this.b);
    }
}
