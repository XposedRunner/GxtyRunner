package cn.jpush.a;

import cn.jiguang.api.JRequest;
import cn.jiguang.api.utils.ProtocolUtil;
import java.nio.ByteBuffer;

public final class c extends JRequest {
    String a;
    String b;

    public final void parseBody() {
        ByteBuffer byteBuffer = this.body;
        this.a = ProtocolUtil.getTlv2(byteBuffer);
        this.b = ProtocolUtil.getTlv2(byteBuffer);
    }

    protected final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public c(int i, int i2, long j, String str, String str2) {
        super(i, i2, j);
        this.a = str;
        this.b = str2;
    }

    public final void writeBody() {
        writeTlv2(this.a);
        writeTlv2(this.b);
    }

    public final String getName() {
        return "TagaliasRequest";
    }

    public final String a() {
        return this.b;
    }

    public final String toString() {
        return "[TagaliasRequest] - appKey:" + this.a + ", action:" + this.b + " - " + super.toString();
    }
}
