package cn.jpush.a;

import cn.jiguang.api.JResponse;
import cn.jiguang.api.utils.ProtocolUtil;
import cn.jpush.android.d.e;
import java.nio.ByteBuffer;

public final class d extends JResponse {
    String a;
    long b = -1;

    public final void writeBody() {
        super.writeBody();
    }

    public d(Object obj, ByteBuffer byteBuffer) {
        super(obj, byteBuffer);
    }

    protected final boolean isNeedParseeErrorMsg() {
        return getCommand() == 10;
    }

    public final void parseBody() {
        super.parseBody();
        if (this.code > 0) {
            e.i("ContentValues", "Response error - code:" + this.code);
        } else {
            this.a = ProtocolUtil.getTlv2(this.body, this);
        }
    }

    public final String getName() {
        return "TagaliasResponse";
    }

    public final String a() {
        return this.a;
    }

    public final String toString() {
        return "[TagaliasResponse] - action:" + this.a + " - " + super.toString();
    }
}
