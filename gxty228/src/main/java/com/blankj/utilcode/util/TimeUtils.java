package com.blankj.utilcode.util;

import android.support.annotation.NonNull;
import com.blankj.utilcode.constant.TimeConstants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class TimeUtils {
    private static final String[] CHINESE_ZODIAC = new String[]{"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final ThreadLocal<SimpleDateFormat> SDF_THREAD_LOCAL = new ThreadLocal();
    private static final String[] ZODIAC = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
    private static final int[] ZODIAC_FLAGS = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};

    private static SimpleDateFormat getDefaultFormat() {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SDF_THREAD_LOCAL.get();
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SDF_THREAD_LOCAL.set(simpleDateFormat);
        return simpleDateFormat;
    }

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String millis2String(long j) {
        return millis2String(j, getDefaultFormat());
    }

    public static String millis2String(long j, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(new Date(j));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long string2Millis(String str) {
        return string2Millis(str, getDefaultFormat());
    }

    public static long string2Millis(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat == null) {
            throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        try {
            return dateFormat.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Date string2Date(String str) {
        return string2Date(str, getDefaultFormat());
    }

    public static Date string2Date(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat == null) {
            throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String date2String(Date date) {
        return date2String(date, getDefaultFormat());
    }

    public static String date2String(Date date, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(date);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long date2Millis(Date date) {
        return date.getTime();
    }

    public static Date millis2Date(long j) {
        return new Date(j);
    }

    public static long getTimeSpan(String str, String str2, int i) {
        return getTimeSpan(str, str2, getDefaultFormat(), i);
    }

    public static long getTimeSpan(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return millis2TimeSpan(string2Millis(str, dateFormat) - string2Millis(str2, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long getTimeSpan(Date date, Date date2, int i) {
        return millis2TimeSpan(date2Millis(date) - date2Millis(date2), i);
    }

    public static long getTimeSpan(long j, long j2, int i) {
        return millis2TimeSpan(j - j2, i);
    }

    public static String getFitTimeSpan(String str, String str2, int i) {
        return millis2FitTimeSpan(string2Millis(str, getDefaultFormat()) - string2Millis(str2, getDefaultFormat()), i);
    }

    public static String getFitTimeSpan(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return millis2FitTimeSpan(string2Millis(str, dateFormat) - string2Millis(str2, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getFitTimeSpan(Date date, Date date2, int i) {
        return millis2FitTimeSpan(date2Millis(date) - date2Millis(date2), i);
    }

    public static String getFitTimeSpan(long j, long j2, int i) {
        return millis2FitTimeSpan(j - j2, i);
    }

    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    public static String getNowString() {
        return millis2String(System.currentTimeMillis(), getDefaultFormat());
    }

    public static String getNowString(@NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return millis2String(System.currentTimeMillis(), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static long getTimeSpanByNow(String str, int i) {
        return getTimeSpan(str, getNowString(), getDefaultFormat(), i);
    }

    public static long getTimeSpanByNow(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getTimeSpan(str, getNowString(dateFormat), dateFormat, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long getTimeSpanByNow(Date date, int i) {
        return getTimeSpan(date, new Date(), i);
    }

    public static long getTimeSpanByNow(long j, int i) {
        return getTimeSpan(j, System.currentTimeMillis(), i);
    }

    public static String getFitTimeSpanByNow(String str, int i) {
        return getFitTimeSpan(str, getNowString(), getDefaultFormat(), i);
    }

    public static String getFitTimeSpanByNow(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getFitTimeSpan(str, getNowString(dateFormat), dateFormat, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getFitTimeSpanByNow(Date date, int i) {
        return getFitTimeSpan(date, getNowDate(), i);
    }

    public static String getFitTimeSpanByNow(long j, int i) {
        return getFitTimeSpan(j, System.currentTimeMillis(), i);
    }

    public static String getFriendlyTimeSpanByNow(String str) {
        return getFriendlyTimeSpanByNow(str, getDefaultFormat());
    }

    public static String getFriendlyTimeSpanByNow(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getFriendlyTimeSpanByNow(string2Millis(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getFriendlyTimeSpanByNow(Date date) {
        return getFriendlyTimeSpanByNow(date.getTime());
    }

    public static String getFriendlyTimeSpanByNow(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis < 0) {
            return String.format("%tc", new Object[]{Long.valueOf(j)});
        } else if (currentTimeMillis < 1000) {
            return "刚刚";
        } else {
            if (currentTimeMillis < 60000) {
                return String.format(Locale.getDefault(), "%d秒前", new Object[]{Long.valueOf(currentTimeMillis / 1000)});
            } else if (currentTimeMillis < 3600000) {
                return String.format(Locale.getDefault(), "%d分钟前", new Object[]{Long.valueOf(currentTimeMillis / 60000)});
            } else {
                currentTimeMillis = getWeeOfToday();
                if (j >= currentTimeMillis) {
                    return String.format("今天%tR", new Object[]{Long.valueOf(j)});
                } else if (j >= currentTimeMillis - LogBuilder.MAX_INTERVAL) {
                    return String.format("昨天%tR", new Object[]{Long.valueOf(j)});
                } else {
                    return String.format("%tF", new Object[]{Long.valueOf(j)});
                }
            }
        }
    }

    private static long getWeeOfToday() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public static long getMillis(long j, long j2, int i) {
        return timeSpan2Millis(j2, i) + j;
    }

    public static long getMillis(String str, long j, int i) {
        return getMillis(str, getDefaultFormat(), j, i);
    }

    public static long getMillis(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return string2Millis(str, dateFormat) + timeSpan2Millis(j, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long getMillis(Date date, long j, int i) {
        return date2Millis(date) + timeSpan2Millis(j, i);
    }

    public static String getString(long j, long j2, int i) {
        return getString(j, getDefaultFormat(), j2, i);
    }

    public static String getString(long j, @NonNull DateFormat dateFormat, long j2, int i) {
        if (dateFormat != null) {
            return millis2String(timeSpan2Millis(j2, i) + j, dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(String str, long j, int i) {
        return getString(str, getDefaultFormat(), j, i);
    }

    public static String getString(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return millis2String(string2Millis(str, dateFormat) + timeSpan2Millis(j, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(Date date, long j, int i) {
        return getString(date, getDefaultFormat(), j, i);
    }

    public static String getString(Date date, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return millis2String(date2Millis(date) + timeSpan2Millis(j, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Date getDate(long j, long j2, int i) {
        return millis2Date(timeSpan2Millis(j2, i) + j);
    }

    public static Date getDate(String str, long j, int i) {
        return getDate(str, getDefaultFormat(), j, i);
    }

    public static Date getDate(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return millis2Date(string2Millis(str, dateFormat) + timeSpan2Millis(j, i));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Date getDate(Date date, long j, int i) {
        return millis2Date(date2Millis(date) + timeSpan2Millis(j, i));
    }

    public static long getMillisByNow(long j, int i) {
        return getMillis(getNowMills(), j, i);
    }

    public static String getStringByNow(long j, int i) {
        return getStringByNow(j, getDefaultFormat(), i);
    }

    public static String getStringByNow(long j, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getString(getNowMills(), dateFormat, j, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Date getDateByNow(long j, int i) {
        return getDate(getNowMills(), j, i);
    }

    public static boolean isToday(String str) {
        return isToday(string2Millis(str, getDefaultFormat()));
    }

    public static boolean isToday(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return isToday(string2Millis(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isToday(Date date) {
        return isToday(date.getTime());
    }

    public static boolean isToday(long j) {
        long weeOfToday = getWeeOfToday();
        return j >= weeOfToday && j < weeOfToday + LogBuilder.MAX_INTERVAL;
    }

    public static boolean isLeapYear(String str) {
        return isLeapYear(string2Date(str, getDefaultFormat()));
    }

    public static boolean isLeapYear(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return isLeapYear(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isLeapYear(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return isLeapYear(instance.get(1));
    }

    public static boolean isLeapYear(long j) {
        return isLeapYear(millis2Date(j));
    }

    public static boolean isLeapYear(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }

    public static String getChineseWeek(String str) {
        return getChineseWeek(string2Date(str, getDefaultFormat()));
    }

    public static String getChineseWeek(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getChineseWeek(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getChineseWeek(Date date) {
        return new SimpleDateFormat("E", Locale.CHINA).format(date);
    }

    public static String getChineseWeek(long j) {
        return getChineseWeek(new Date(j));
    }

    public static String getUSWeek(String str) {
        return getUSWeek(string2Date(str, getDefaultFormat()));
    }

    public static String getUSWeek(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getUSWeek(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getUSWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }

    public static String getUSWeek(long j) {
        return getUSWeek(new Date(j));
    }

    public static int getValueByCalendarField(String str, int i) {
        return getValueByCalendarField(string2Date(str, getDefaultFormat()), i);
    }

    public static int getValueByCalendarField(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getValueByCalendarField(string2Date(str, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static int getValueByCalendarField(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(i);
    }

    public static int getValueByCalendarField(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(i);
    }

    public static String getChineseZodiac(String str) {
        return getChineseZodiac(string2Date(str, getDefaultFormat()));
    }

    public static String getChineseZodiac(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getChineseZodiac(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getChineseZodiac(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return CHINESE_ZODIAC[instance.get(1) % 12];
    }

    public static String getChineseZodiac(long j) {
        return getChineseZodiac(millis2Date(j));
    }

    public static String getChineseZodiac(int i) {
        return CHINESE_ZODIAC[i % 12];
    }

    public static String getZodiac(String str) {
        return getZodiac(string2Date(str, getDefaultFormat()));
    }

    public static String getZodiac(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getZodiac(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getZodiac(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return getZodiac(instance.get(2) + 1, instance.get(5));
    }

    public static String getZodiac(long j) {
        return getZodiac(millis2Date(j));
    }

    public static String getZodiac(int i, int i2) {
        return ZODIAC[i2 >= ZODIAC_FLAGS[i + -1] ? i - 1 : (i + 10) % 12];
    }

    private static long timeSpan2Millis(long j, int i) {
        return ((long) i) * j;
    }

    private static long millis2TimeSpan(long j, int i) {
        return j / ((long) i);
    }

    private static String millis2FitTimeSpan(long j, int i) {
        if (i <= 0) {
            return null;
        }
        int min = Math.min(i, 5);
        String[] strArr = new String[]{"天", "小时", "分钟", "秒", "毫秒"};
        if (j == 0) {
            return 0 + strArr[min - 1];
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (j < 0) {
            stringBuilder.append("-");
            j = -j;
        }
        int[] iArr = new int[]{TimeConstants.DAY, TimeConstants.HOUR, TimeConstants.MIN, 1000, 1};
        long j2 = j;
        for (int i2 = 0; i2 < min; i2++) {
            if (j2 >= ((long) iArr[i2])) {
                long j3 = j2 / ((long) iArr[i2]);
                j2 -= ((long) iArr[i2]) * j3;
                stringBuilder.append(j3).append(strArr[i2]);
            }
        }
        return stringBuilder.toString();
    }
}
