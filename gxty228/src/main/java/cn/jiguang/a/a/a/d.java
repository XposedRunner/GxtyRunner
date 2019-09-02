package cn.jiguang.a.a.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

final class d {
    HandlerThread a = null;
    Handler b = null;
    private int c;
    private String d;

    public d(String str, int i) {
        this.c = i;
        this.d = str;
        this.a = new HandlerThread("ping timer");
        this.a.start();
        this.b = new Handler(this.a.getLooper(), new e(this));
    }

    private void b(byte[] bArr, int i, int i2) {
        Process process = null;
        byte[] bArr2 = new byte[]{bArr[0], bArr[1], bArr[2], (byte) 0};
        Thread currentThread = Thread.currentThread();
        while (i < i2) {
            bArr2[3] = (byte) i;
            if (bArr2[3] != bArr[3]) {
                try {
                    String a = c.a(bArr2);
                    if (a.equalsIgnoreCase(this.d)) {
                        cn.jiguang.e.d.c("ArpUtil", "this ip equal with route, continue.");
                        if (process != null) {
                            try {
                                process.exitValue();
                            } catch (Exception e) {
                                try {
                                    process.destroy();
                                } catch (Exception e2) {
                                }
                            }
                        }
                    } else {
                        this.b.removeCallbacksAndMessages(null);
                        Message obtainMessage = this.b.obtainMessage(1);
                        obtainMessage.obj = currentThread;
                        Bundle bundle = new Bundle();
                        bundle.putString("ip", a);
                        obtainMessage.setData(bundle);
                        this.b.sendMessageDelayed(obtainMessage, (long) this.c);
                        process = c.b("ping -c 1 -w 1 " + a);
                        if (process != null) {
                            try {
                                process.exitValue();
                            } catch (Exception e3) {
                                try {
                                    process.destroy();
                                } catch (Exception e4) {
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (process != null) {
                        try {
                            process.exitValue();
                        } catch (Exception e5) {
                            try {
                                process.destroy();
                            } catch (Exception e6) {
                            }
                        }
                    }
                }
            }
            i++;
        }
    }

    public final void a(byte[] bArr, int i, int i2) {
        b(bArr, 0, 255);
        this.a.quit();
    }
}
