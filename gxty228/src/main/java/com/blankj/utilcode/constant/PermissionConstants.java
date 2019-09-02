package com.blankj.utilcode.constant;

import android.annotation.SuppressLint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint({"InlinedApi"})
public final class PermissionConstants {
    public static final String CALENDAR = "android.permission-group.CALENDAR";
    public static final String CAMERA = "android.permission-group.CAMERA";
    public static final String CONTACTS = "android.permission-group.CONTACTS";
    private static final String[] GROUP_CALENDAR = new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
    private static final String[] GROUP_CAMERA = new String[]{"android.permission.CAMERA"};
    private static final String[] GROUP_CONTACTS = new String[]{"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS"};
    private static final String[] GROUP_LOCATION = new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final String[] GROUP_MICROPHONE = new String[]{"android.permission.RECORD_AUDIO"};
    private static final String[] GROUP_PHONE = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.ANSWER_PHONE_CALLS", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS"};
    private static final String[] GROUP_SENSORS = new String[]{"android.permission.BODY_SENSORS"};
    private static final String[] GROUP_SMS = new String[]{"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS"};
    private static final String[] GROUP_STORAGE = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final String LOCATION = "android.permission-group.LOCATION";
    public static final String MICROPHONE = "android.permission-group.MICROPHONE";
    public static final String PHONE = "android.permission-group.PHONE";
    public static final String SENSORS = "android.permission-group.SENSORS";
    public static final String SMS = "android.permission-group.SMS";
    public static final String STORAGE = "android.permission-group.STORAGE";

    @Retention(RetentionPolicy.SOURCE)
    public @interface Permission {
    }

    public static String[] getPermissions(String str) {
        int i = -1;
        switch (str.hashCode()) {
            case -1639857183:
                if (str.equals(CONTACTS)) {
                    i = 2;
                    break;
                }
                break;
            case -1410061184:
                if (str.equals(PHONE)) {
                    i = 5;
                    break;
                }
                break;
            case -1250730292:
                if (str.equals(CALENDAR)) {
                    i = 0;
                    break;
                }
                break;
            case -1140935117:
                if (str.equals(CAMERA)) {
                    i = 1;
                    break;
                }
                break;
            case 421761675:
                if (str.equals(SENSORS)) {
                    i = 6;
                    break;
                }
                break;
            case 828638019:
                if (str.equals(LOCATION)) {
                    i = 3;
                    break;
                }
                break;
            case 852078861:
                if (str.equals(STORAGE)) {
                    i = 8;
                    break;
                }
                break;
            case 1581272376:
                if (str.equals(MICROPHONE)) {
                    i = 4;
                    break;
                }
                break;
            case 1795181803:
                if (str.equals(SMS)) {
                    i = 7;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return GROUP_CALENDAR;
            case 1:
                return GROUP_CAMERA;
            case 2:
                return GROUP_CONTACTS;
            case 3:
                return GROUP_LOCATION;
            case 4:
                return GROUP_MICROPHONE;
            case 5:
                return GROUP_PHONE;
            case 6:
                return GROUP_SENSORS;
            case 7:
                return GROUP_SMS;
            case 8:
                return GROUP_STORAGE;
            default:
                return new String[]{str};
        }
    }
}
