package com.amap.api.mapcore.util;

import android.opengl.GLES20;

/* compiled from: GlShader */
public class da {
    private boolean a;
    public int d;
    public int e;
    public int f;

    protected boolean a(String str, String str2) {
        this.d = b(str, str2);
        return this.d != 0;
    }

    protected boolean a(String str) {
        this.d = d(str);
        return this.d != 0;
    }

    protected int b(String str) {
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.d, str);
        return glGetAttribLocation < 0 ? glGetAttribLocation : glGetAttribLocation;
    }

    protected int c(String str) {
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.d, str);
        return glGetUniformLocation < 0 ? glGetUniformLocation : glGetUniformLocation;
    }

    public void a() {
        GLES20.glUseProgram(this.d);
    }

    public int d(String str) {
        String str2 = "amap_sdk_shaders/" + str;
        String a = dm.a(str2);
        if (a == null) {
            throw new IllegalArgumentException("shader file not found: " + str2);
        }
        int indexOf = a.indexOf(36);
        if (indexOf < 0 || a.charAt(indexOf + 1) != '$') {
            throw new IllegalArgumentException("not a shader file " + str2);
        }
        return b(a.substring(0, indexOf), a.substring(indexOf + 2));
    }

    public int b(String str, String str2) {
        this.e = a(35633, str);
        this.f = a(35632, str2);
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, this.e);
        GLES20.glAttachShader(glCreateProgram, this.f);
        GLES20.glLinkProgram(glCreateProgram);
        return glCreateProgram;
    }

    public int a(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        return glCreateShader;
    }

    public void b() {
        if (this.d >= 0) {
            GLES20.glDeleteProgram(this.d);
        }
        if (this.e >= 0) {
            GLES20.glDeleteShader(this.e);
        }
        if (this.f >= 0) {
            GLES20.glDeleteShader(this.f);
        }
        this.a = true;
    }

    public boolean c() {
        return this.a;
    }
}
