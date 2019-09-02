package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

public class LargeValueFormatter implements IAxisValueFormatter, IValueFormatter {
    private static final int MAX_LENGTH = 5;
    private static String[] SUFFIX = new String[]{"", "k", "m", "b", "t"};
    private DecimalFormat mFormat;
    private String mText;

    public LargeValueFormatter() {
        this.mText = "";
        this.mFormat = new DecimalFormat("###E00");
    }

    public LargeValueFormatter(String str) {
        this();
        this.mText = str;
    }

    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return makePretty((double) f) + this.mText;
    }

    public String getFormattedValue(float f, AxisBase axisBase) {
        return makePretty((double) f) + this.mText;
    }

    public void setAppendix(String str) {
        this.mText = str;
    }

    public void setSuffix(String[] strArr) {
        SUFFIX = strArr;
    }

    private String makePretty(double d) {
        String format = this.mFormat.format(d);
        format = format.replaceAll("E[0-9][0-9]", SUFFIX[Integer.valueOf(Character.getNumericValue(format.charAt(format.length() - 2)) + "" + Character.getNumericValue(format.charAt(format.length() - 1))).intValue() / 3]);
        while (true) {
            if (format.length() <= 5 && !format.matches("[0-9]+\\.[a-z]")) {
                return format;
            }
            format = format.substring(0, format.length() - 2) + format.substring(format.length() - 1);
        }
    }

    public int getDecimalDigits() {
        return 0;
    }
}
