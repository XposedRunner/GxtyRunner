package com.amap.api.mapcore.util;

import android.content.Context;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.amap.mapcore.FileUtil;

/* compiled from: CustomStyleTextureTask */
public class df implements Runnable {
    private Context a;
    private de b;
    private dk c;
    private a d;

    /* compiled from: CustomStyleTextureTask */
    public interface a {
        void a(String str, dk dkVar);
    }

    public df(Context context) {
        this.a = context;
        if (this.b == null) {
            this.b = new de(this.a, "");
        }
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }

    public void run() {
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                if (this.b != null) {
                    String str;
                    com.amap.api.mapcore.util.de.a aVar = (com.amap.api.mapcore.util.de.a) this.b.e();
                    if (aVar == null || aVar.a == null) {
                        str = null;
                    } else {
                        String str2 = a(this.a) + HttpUtils.PATHS_SEPARATOR + "custom_texture_data";
                        a(str2, aVar.a);
                        str = str2;
                    }
                    if (this.d != null) {
                        this.d.a(str, this.c);
                    }
                }
                gz.a(this.a, en.e());
            }
        } catch (Throwable th) {
            gz.c(th, "CustomStyleTask", "download customStyle");
            th.printStackTrace();
        }
    }

    private void a(String str, byte[] bArr) {
        FileUtil.writeDatasToFile(str, bArr);
    }

    private String a(Context context) {
        return FileUtil.getMapBaseStorage(context);
    }

    public void a() {
        this.a = null;
        if (this.b != null) {
            this.b = null;
        }
    }

    public void b() {
        em.a().a(this);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a(dk dkVar) {
        this.c = dkVar;
    }
}
