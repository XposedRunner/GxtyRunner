package cn.jpush.a;

import cn.jiguang.api.JResponse;
import java.nio.ByteBuffer;

public final class a extends JResponse {
    public final void writeBody() {
        super.writeBody();
    }

    public a(Object obj, ByteBuffer byteBuffer) {
        super(obj, byteBuffer);
    }

    protected final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public final void parseBody() {
        super.parseBody();
    }

    public final String getName() {
        return "CommonResponse";
    }

    public final String toString() {
        return "[CommonResponse] - " + super.toString();
    }
}
