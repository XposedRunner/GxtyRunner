package cn.jiguang.api;

import cn.jiguang.api.utils.ByteBufferUtils;
import java.nio.ByteBuffer;

public abstract class JResponse extends JProtocol {
    public int code;

    public JResponse(int i, int i2, long j, long j2, int i3, String str) {
        super(false, i, i2, j, -1, j2);
        this.code = i3;
    }

    public JResponse(Object obj, ByteBuffer byteBuffer) {
        super(false, obj, byteBuffer);
    }

    public JResponse(ByteBuffer byteBuffer, byte[] bArr) {
        super(false, byteBuffer, bArr);
    }

    protected void parseBody() {
        if (isNeedParseeErrorMsg()) {
            this.code = ByteBufferUtils.getShort(this.body, this);
        }
    }

    public String toString() {
        return "JResponse{code=" + this.code + '}';
    }

    protected void writeBody() {
        if (this.code >= 0) {
            writeInt2(this.code);
        }
    }
}
