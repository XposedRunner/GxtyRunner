package com.example.gita.gxty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* compiled from: PreferUtils */
public class n {
    private static n a = null;
    private SharedPreferences b;

    private n(Context context) {
        this.b = context.getSharedPreferences("gxty_prefer5", 0);
    }

    public static n a(Context context) {
        if (a == null) {
            a = new n(context);
        }
        return a;
    }

    public void a() {
        Editor edit = this.b.edit();
        edit.clear();
        edit.commit();
    }

    public void a(int i, boolean z) {
        Editor edit = this.b.edit();
        edit.putBoolean("Notice_Flag" + i, z);
        edit.commit();
    }

    public boolean a(int i) {
        return this.b.getBoolean("Notice_Flag" + i, false);
    }

    public void a(String str) {
        Editor edit = this.b.edit();
        edit.putString("caidan_flag", str);
        edit.commit();
    }

    public String b() {
        return this.b.getString("caidan_flag", "");
    }

    public void a(String str, String str2) {
        Editor edit = this.b.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public String b(String str) {
        return this.b.getString(str, "");
    }

    public void c(String str) {
        Editor edit = this.b.edit();
        edit.putString("ads_data_url", str);
        edit.commit();
    }

    public String c() {
        return this.b.getString("ads_data_url", "");
    }

    public void d(String str) {
        Editor edit = this.b.edit();
        edit.putString("ads_data_type", str);
        edit.commit();
    }

    public String d() {
        return this.b.getString("ads_data_type", "");
    }

    public void e(String str) {
        Editor edit = this.b.edit();
        edit.putString("ads_data_img", str);
        edit.commit();
    }

    public String e() {
        return this.b.getString("ads_data_img", "");
    }

    public void f(String str) {
        Editor edit = this.b.edit();
        edit.putString("ads_data_title", str);
        edit.commit();
    }

    public String f() {
        return this.b.getString("ads_data_title", "");
    }

    public void b(int i) {
        Editor edit = this.b.edit();
        edit.putInt("OPEN_AD_FLAG", i);
        edit.commit();
    }

    public boolean g() {
        return 1 == this.b.getInt("OPEN_AD_FLAG", 0);
    }

    public void c(int i) {
        Editor edit = this.b.edit();
        edit.putInt("OPEN_AD_XHFLAG", i);
        edit.commit();
    }

    public boolean h() {
        return 1 == this.b.getInt("OPEN_AD_XHFLAG", 0);
    }

    public void d(int i) {
        Editor edit = this.b.edit();
        edit.putInt("androidBeaconScanTime", i);
        edit.commit();
    }

    public int i() {
        return this.b.getInt("androidBeaconScanTime", 0);
    }

    public void e(int i) {
        Editor edit = this.b.edit();
        edit.putInt("androidBeaconBetweenScanTime", i);
        edit.commit();
    }

    public int j() {
        return this.b.getInt("androidBeaconBetweenScanTime", 0);
    }
}
