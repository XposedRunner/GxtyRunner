package com.example.gita.gxty.utils.a;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.qq.e.comm.constants.Constants.KEYS;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/* compiled from: CheckVirtual */
public class c {
    public static boolean a() {
        CharSequence b = b();
        if (b == null || b.length() == 0) {
            return false;
        }
        String b2 = b(KEYS.PLACEMENTS);
        if (b2 == null || b2.isEmpty()) {
            return false;
        }
        String[] split = b2.split("\n");
        if (split == null || split.length <= 0) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < split.length; i2++) {
            if (split[i2].contains(b)) {
                int lastIndexOf = split[i2].lastIndexOf(" ");
                String substring = split[i2].substring(lastIndexOf <= 0 ? 0 : lastIndexOf + 1, split[i2].length());
                if (new File(String.format("/data/data/%s", new Object[]{substring, Locale.CHINA})).exists()) {
                    i++;
                }
            }
        }
        if (i > 1) {
            return true;
        }
        return false;
    }

    private static String b(String str) {
        Process exec;
        BufferedOutputStream bufferedOutputStream;
        Object obj;
        Object obj2;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        Throwable th2;
        String str2 = null;
        try {
            exec = Runtime.getRuntime().exec(IXAdRequestInfo.SCREEN_HEIGHT);
            try {
                bufferedOutputStream = new BufferedOutputStream(exec.getOutputStream());
            } catch (Exception e) {
                obj = str2;
                obj2 = str2;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (exec != null) {
                    exec.destroy();
                }
                return str2;
            } catch (Throwable th3) {
                obj2 = str2;
                String str3 = str2;
                th = th3;
                obj = str3;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                if (exec != null) {
                    exec.destroy();
                }
                throw th;
            }
            try {
                bufferedInputStream = new BufferedInputStream(exec.getInputStream());
                try {
                    bufferedOutputStream.write(str.getBytes());
                    bufferedOutputStream.write(10);
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    exec.waitFor();
                    str2 = a(bufferedInputStream);
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                } catch (Exception e4) {
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    return str2;
                } catch (Throwable th4) {
                    th = th4;
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                obj = str2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                return str2;
            } catch (Throwable th32) {
                th2 = th32;
                obj = str2;
                th = th2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                throw th;
            }
        } catch (Exception e6) {
            exec = str2;
            bufferedInputStream = str2;
            bufferedOutputStream = str2;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            return str2;
        } catch (Throwable th5) {
            bufferedInputStream = str2;
            bufferedOutputStream = str2;
            th2 = th5;
            exec = str2;
            th = th2;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            throw th;
        }
        return str2;
    }

    private static String a(BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[512];
        StringBuilder stringBuilder = new StringBuilder();
        int read;
        do {
            try {
                read = bufferedInputStream.read(bArr);
                if (read > 0) {
                    stringBuilder.append(new String(bArr, 0, read));
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (read >= 512);
        return stringBuilder.toString();
    }

    public static String b() {
        String b = b("cat /proc/self/cgroup");
        if (b == null || b.length() == 0) {
            return null;
        }
        int lastIndexOf = b.lastIndexOf("uid");
        int lastIndexOf2 = b.lastIndexOf("/pid");
        if (lastIndexOf < 0) {
            return null;
        }
        if (lastIndexOf2 <= 0) {
            lastIndexOf2 = b.length();
        }
        try {
            if (!a(b.substring(lastIndexOf + 4, lastIndexOf2).replaceAll("\n", ""))) {
                return null;
            }
            return String.format("u0_a%d", new Object[]{Integer.valueOf(Integer.valueOf(b.substring(lastIndexOf + 4, lastIndexOf2).replaceAll("\n", "")).intValue() - 10000)});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
