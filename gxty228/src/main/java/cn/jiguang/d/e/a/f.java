package cn.jiguang.d.e.a;

import cn.jiguang.api.JResponse;
import cn.jiguang.d.e.a.a.c;
import java.nio.ByteBuffer;

public final class f extends JResponse {
    public f(c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    public final String getName() {
        return "UserCtrlResponse";
    }

    protected final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public final void parseBody() {
        super.parseBody();
    }

    public final void writeBody() {
        super.writeBody();
    }
}
