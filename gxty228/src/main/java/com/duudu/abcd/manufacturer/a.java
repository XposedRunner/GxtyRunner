package com.duudu.abcd.manufacturer;

import android.os.Build;

/* compiled from: ManufacturerUtils */
public class a {
    public static ManufacturerType a() {
        if ("Huawei".equalsIgnoreCase(Build.BRAND) || "HONOR".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.HUAWEI;
        }
        if ("vivo".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.VIVO;
        }
        if ("OPPO".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.OPPO;
        }
        if ("Xiaomi".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.XIAOMI;
        }
        if ("Meizu".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.MEIZU;
        }
        if ("samsung".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.SAMSUNG;
        }
        if ("smartisan".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.SMARTISAN;
        }
        if ("LeEco".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.LETV;
        }
        if ("Lenovo".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.LENOVO;
        }
        if ("Coolpad".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.COOLPAD;
        }
        if ("ZTE".equalsIgnoreCase(Build.BRAND)) {
            return ManufacturerType.ZTE;
        }
        return ManufacturerType.OTHERS;
    }
}
