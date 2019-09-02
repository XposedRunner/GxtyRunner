package com.amap.api.mapcore.util;

import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* compiled from: BaseTileProvider */
public class do implements TileProvider {
    Random a = new Random();
    private final int b;
    private final int c;
    private MapConfig d;

    /* compiled from: BaseTileProvider */
    class a extends dl {
        final /* synthetic */ do b;
        private int c;
        private int d;
        private int e;
        private String i;
        private String j = "";

        public a(do doVar, int i, int i2, int i3, String str) {
            this.b = doVar;
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.i = str;
            this.j = f();
            a(gg.a(lo.a));
            a((int) GLMapStaticValue.TMC_REFRESH_TIMELIMIT);
            b(50000);
        }

        public Map<String, String> a() {
            Map<String, String> hashtable = new Hashtable(16);
            hashtable.put(HttpHeaders.HEAD_KEY_USER_AGENT, le.c);
            hashtable.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
            hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", new Object[]{"6.4.1", "3dmap"}));
            hashtable.put("x-INFO", gb.a(lo.a));
            hashtable.put(CacheEntity.KEY, fx.f(lo.a));
            hashtable.put("logversion", "2.1");
            return hashtable;
        }

        private String e() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=").append(fx.f(lo.a));
            stringBuffer.append("&channel=amapapi");
            if (ee.a(this.c, this.d, this.e) || this.e < 7) {
                stringBuffer.append("&z=").append(this.e).append("&x=").append(this.c).append("&y=").append(this.d).append("&lang=en&size=1&scale=1&style=7");
            } else if (MapsInitializer.isLoadWorldGridMap()) {
                stringBuffer.append("&x=").append(this.c);
                stringBuffer.append("&y=").append(this.d);
                stringBuffer.append("&z=").append(this.e);
                stringBuffer.append("&ds=0");
                stringBuffer.append("&dpitype=webrd");
                stringBuffer.append("&lang=").append(this.i);
                stringBuffer.append("&scale=2");
            }
            String stringBuffer2 = stringBuffer.toString();
            String a = a(stringBuffer2);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(stringBuffer2);
            stringBuffer2 = gb.a();
            stringBuffer3.append("&ts=" + stringBuffer2);
            stringBuffer3.append("&scode=" + gb.a(lo.a, stringBuffer2, a));
            return stringBuffer3.toString();
        }

        private String a(String str) {
            String[] split = str.split(HttpUtils.PARAMETERS_SEPARATOR);
            Arrays.sort(split);
            StringBuffer stringBuffer = new StringBuffer();
            for (String b : split) {
                stringBuffer.append(b(b));
                stringBuffer.append(HttpUtils.PARAMETERS_SEPARATOR);
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() > 1) {
                return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
            }
            return str;
        }

        private String b(String str) {
            if (str == null) {
                return str;
            }
            try {
                return URLDecoder.decode(str, "utf-8");
            } catch (Throwable e) {
                gz.c(e, "AbstractProtocalHandler", "strReEncoder");
                return "";
            } catch (Throwable e2) {
                gz.c(e2, "AbstractProtocalHandler", "strReEncoderException");
                return "";
            }
        }

        public Map<String, String> b() {
            return null;
        }

        private String f() {
            if (ee.a(this.c, this.d, this.e) || this.e < 7) {
                int nextInt = this.b.a.nextInt(100000) % 4;
                return String.format(Locale.US, "http://wprd0%d.is.autonavi.com/appmaptile?", new Object[]{Integer.valueOf(nextInt + 1)});
            } else if (MapsInitializer.isLoadWorldGridMap()) {
                return "http://restapi.amap.com/v4/gridmap?";
            } else {
                return null;
            }
        }

        public String c() {
            if (TextUtils.isEmpty(this.j)) {
                return null;
            }
            return this.j + e();
        }
    }

    public do(int i, int i2, MapConfig mapConfig) {
        this.b = i;
        this.c = i2;
        this.d = mapConfig;
    }

    public final Tile getTile(int i, int i2, int i3) {
        try {
            byte[] a = a(i, i2, i3, this.d != null ? this.d.getMapLanguage() : "zh_cn");
            if (a == null) {
                return NO_TILE;
            }
            return Tile.obtain(this.b, this.c, a);
        } catch (IOException e) {
            return NO_TILE;
        }
    }

    private byte[] a(int i, int i2, int i3, String str) throws IOException {
        try {
            return new a(this, i, i2, i3, str).d();
        } catch (Throwable th) {
            return null;
        }
    }

    public int getTileWidth() {
        return this.b;
    }

    public int getTileHeight() {
        return this.c;
    }
}
