package cn.jpush.a;

import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.api.utils.ProtocolUtil;
import java.nio.ByteBuffer;

public final class b extends JResponse {
    int a;
    long b;
    String c;

    public final void writeBody() {
        super.writeBody();
        writeInt1(this.a);
        writeLong8(this.b);
        writeTlv2(this.c);
    }

    public b(Object obj, ByteBuffer byteBuffer) {
        super(obj, byteBuffer);
    }

    protected final boolean isNeedParseeErrorMsg() {
        return false;
    }

    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        this.a = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.b = ByteBufferUtils.getLong(byteBuffer, this);
        this.c = ProtocolUtil.getTlv2(byteBuffer, this);
    }

    public final String getName() {
        return "MessagePush";
    }

    public final int a() {
        return this.a;
    }

    public final long b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String toString() {
        return "[MessagePush] - msgType:" + this.a + ", msgId:" + this.b + ", msgContent:" + this.c + " - " + super.toString();
    }
}
