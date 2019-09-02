package com.amap.api.mapcore.util;

import com.amap.api.maps.MapsInitializer;
import java.util.Map;

/* compiled from: AbstractAMapRequest */
public abstract class dl extends iy {
    protected boolean a = true;

    protected byte[] d() throws gp {
        int protocol = MapsInitializer.getProtocol();
        ix a = ix.a(false);
        if (protocol == 1) {
            if (this.a) {
                return a.b(this);
            }
            return a.d(this);
        } else if (protocol != 2) {
            return null;
        } else {
            if (this.a) {
                return a.a(this);
            }
            return a.e(this);
        }
    }

    public Map<String, String> b() {
        return null;
    }

    public Map<String, String> a() {
        return null;
    }
}
