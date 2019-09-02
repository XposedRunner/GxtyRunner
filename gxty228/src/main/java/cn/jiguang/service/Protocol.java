package cn.jiguang.service;

import cn.jiguang.e.d;

public class Protocol {
    static {
        try {
            System.loadLibrary("jcore119");
        } catch (Throwable th) {
            d.i("PushProtocol", "System.loadLibrary::jcore119" + th);
            th.printStackTrace();
        }
    }

    public static native int GetSdkVersion();

    public static native String getCerTificate();
}
