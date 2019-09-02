package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: AndroidAssets */
public class dm {
    static dm b;
    Context a;

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream b = b.b(str);
        if (b == null) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(b, "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine).append('\n');
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void a(Context context) {
        b = new dm(context);
    }

    private dm(Context context) {
        this.a = context;
    }

    public InputStream b(String str) {
        try {
            return this.a.getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
