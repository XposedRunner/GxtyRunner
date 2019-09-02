package cn.jiguang.d.b;

import android.content.Context;
import java.io.File;

final class c extends Thread {
    final /* synthetic */ Context a;

    c(Context context) {
        this.a = context;
    }

    public final void run() {
        File filesDir = this.a.getFilesDir();
        if (filesDir != null) {
            File file = new File(filesDir, ".servicesaveFile");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
}
