package com.github.mikephil.charting.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.util.ArrayList;
import java.util.List;

public class ColorTemplate {
    public static final int[] COLORFUL_COLORS = new int[]{Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0), Color.rgb(106, TinkerReport.KEY_APPLIED_PACKAGE_CHECK_SIGNATURE, 31), Color.rgb(179, 100, 53)};
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] JOYFUL_COLORS = new int[]{Color.rgb(217, 80, 138), Color.rgb(TinkerReport.KEY_LOADED_EXCEPTION_RESOURCE, 149, 7), Color.rgb(TinkerReport.KEY_LOADED_EXCEPTION_RESOURCE, 247, 120), Color.rgb(106, 167, 134), Color.rgb(53, 194, TinkerReport.KEY_APPLIED_FAIL_COST_OTHER)};
    public static final int[] LIBERTY_COLORS = new int[]{Color.rgb(TinkerReport.KEY_APPLIED_FAIL_COST_30S_LESS, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187), Color.rgb(118, 174, 175), Color.rgb(42, 109, 130)};
    public static final int[] MATERIAL_COLORS = new int[]{rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};
    public static final int[] PASTEL_COLORS = new int[]{Color.rgb(64, 89, 128), Color.rgb(149, 165, TinkerReport.KEY_APPLIED_INFO_CORRUPTED), Color.rgb(217, TinkerReport.KEY_APPLIED_RESOURCE_EXTRACT, 162), Color.rgb(191, 134, 134), Color.rgb(179, 48, 80)};
    public static final int[] VORDIPLOM_COLORS = new int[]{Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, TinkerReport.KEY_APPLIED_FAIL_COST_60S_LESS, 140), Color.rgb(140, 234, 255), Color.rgb(255, 140, TinkerReport.KEY_APPLIED_PACKAGE_CHECK_RES_META)};

    public static int rgb(String str) {
        int parseLong = (int) Long.parseLong(str.replace("#", ""), 16);
        return Color.rgb((parseLong >> 16) & 255, (parseLong >> 8) & 255, (parseLong >> 0) & 255);
    }

    public static int getHoloBlue() {
        return Color.rgb(51, TinkerReport.KEY_APPLIED_PATCH_FILE_EXTRACT, 229);
    }

    public static int colorWithAlpha(int i, int i2) {
        return (ViewCompat.MEASURED_SIZE_MASK & i) | ((i2 & 255) << 24);
    }

    public static List<Integer> createColors(Resources resources, int[] iArr) {
        List<Integer> arrayList = new ArrayList();
        for (int color : iArr) {
            arrayList.add(Integer.valueOf(resources.getColor(color)));
        }
        return arrayList;
    }

    public static List<Integer> createColors(int[] iArr) {
        List<Integer> arrayList = new ArrayList();
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }
}
