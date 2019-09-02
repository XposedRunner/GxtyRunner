package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

/* compiled from: OfflineMapRemoveTask */
public class ar {
    private Context a;

    public ar(Context context) {
        this.a = context;
    }

    public void a(be beVar) {
        b(beVar);
    }

    private boolean b(be beVar) {
        if (beVar == null) {
            return false;
        }
        String pinyin = beVar.getPinyin();
        boolean a = a(pinyin, this.a, "vmap/");
        if (pinyin.equals("quanguogaiyaotu")) {
            pinyin = "quanguo";
        }
        boolean z = a(pinyin, this.a, "map/") || a;
        if (b(bk.c(beVar.getUrl()), this.a, "map/") || z) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            beVar.i();
            return z;
        }
        beVar.h();
        return false;
    }

    private boolean a(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String b = en.b(context);
        try {
            File file = new File(b + str2 + str + ".dat");
            if (!file.exists() || bk.b(file)) {
                try {
                    bk.b(b + str2);
                    bk.b(str, context);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
            bk.a("deleteDownload delete some thing wrong!");
            return false;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e22) {
            e22.printStackTrace();
            return false;
        }
    }

    private boolean b(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String a = en.a(context);
        try {
            File file = new File(a + str2 + str);
            if (!file.exists() || bk.b(file)) {
                try {
                    bk.b(a + str2);
                    bk.b(str, context);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
            bk.a("deleteDownload delete some thing wrong!");
            return false;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e22) {
            e22.printStackTrace();
            return false;
        }
    }
}
