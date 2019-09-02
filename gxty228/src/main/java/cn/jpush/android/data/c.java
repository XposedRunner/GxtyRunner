package cn.jpush.android.data;

import android.text.TextUtils;
import java.io.Serializable;

public final class c implements Serializable {
    private static final long serialVersionUID = -942487107643335186L;
    public String a;
    public String b;

    public c(b bVar) {
        this.a = bVar.c;
        this.b = bVar.d;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(cVar.a) || !TextUtils.equals(this.a, cVar.a)) {
            return false;
        }
        if (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(cVar.b)) {
            return true;
        }
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(cVar.b) || !TextUtils.equals(this.b, cVar.b)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "msg_id = " + this.a + ",  override_msg_id = " + this.b;
    }
}
