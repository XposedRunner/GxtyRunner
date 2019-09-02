package cn.jiguang.d.e.a;

import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.d.e.a.a.c;
import java.nio.ByteBuffer;

public final class a extends JResponse {
    int a;
    int b;
    int c;
    long d;

    public a(c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    public final int a() {
        return this.a;
    }

    public final String getName() {
        return "ACK Response";
    }

    protected final boolean isNeedParseeErrorMsg() {
        return false;
    }

    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        this.a = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.b = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.c = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.d = ByteBufferUtils.getLong(byteBuffer, this);
    }

    public final String toString() {
        return "[AckNormal] - requestCommand:" + this.a + ", step:" + this.b + ", status:" + this.c + ", stime:" + this.d + " - " + super.toString();
    }

    public final void writeBody() {
        super.writeBody();
        writeInt1(this.a);
        writeInt1(this.b);
        writeInt1(this.c);
        writeLong8(this.d);
    }
}
