package com.amap.api.mapcore.util;

import java.util.Locale;
import java.util.Random;

/* compiled from: RandomUtil */
public class ed {
    private static String a = "0123456789";

    /* compiled from: RandomUtil */
    static class a {
        private String a;
        private int b;
        private int c;

        public a(String str, int i) {
            this.b = 1103515245;
            this.c = 12345;
            this.a = a(str, i, str.length());
        }

        public a() {
            this(11);
        }

        public a(int i) {
            this("ABCDEFGHIJKLMNOPQRSTUVWXYZ", i);
        }

        public char a(int i, boolean z) {
            int length = this.a.length();
            if (z) {
                i = length - i;
            }
            return this.a.charAt(i);
        }

        public int a(char c, boolean z) {
            int length = this.a.length();
            int indexOf = this.a.indexOf(c);
            return z ? length - indexOf : indexOf;
        }

        public int a(int i) {
            return (int) (((((long) i) * ((long) this.b)) + ((long) this.c)) & 2147483647L);
        }

        public String a(String str, int i, int i2) {
            StringBuffer stringBuffer = new StringBuffer(str);
            int length = str.length();
            for (int i3 = 0; i3 < i2; i3++) {
                int a = a(i);
                int i4 = a % length;
                i = a(a);
                a = i % length;
                char charAt = stringBuffer.charAt(i4);
                stringBuffer.setCharAt(i4, stringBuffer.charAt(a));
                stringBuffer.setCharAt(a, charAt);
            }
            return stringBuffer.toString();
        }

        public String a(boolean z, int i, String str) {
            StringBuilder stringBuilder = new StringBuilder();
            int length = this.a.length();
            int length2 = str.length();
            for (int i2 = 0; i2 < length2; i2++) {
                int a = a(str.charAt(i2), z);
                if (a < 0) {
                    break;
                }
                stringBuilder.append(a(((a + i) + i2) % length, z));
            }
            if (stringBuilder.length() == length2) {
                return stringBuilder.toString();
            }
            return null;
        }

        public String a(int i, String str) {
            return a(false, i, str);
        }
    }

    public static String a() {
        Random random = new Random();
        int nextInt = random.nextInt(10);
        String format = String.format(Locale.US, "%05d", new Object[]{Integer.valueOf(nextInt)});
        return new a(a, random.nextInt(100)).a(random.nextInt(10), format) + String.format(Locale.US, "%01d", new Object[]{Integer.valueOf(r2)}) + String.format(Locale.US, "%02d", new Object[]{Integer.valueOf(r0)});
    }
}
