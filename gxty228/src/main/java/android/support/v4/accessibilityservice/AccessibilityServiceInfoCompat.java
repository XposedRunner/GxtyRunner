package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;

public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    @Deprecated
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoBaseImpl IMPL;

    static class AccessibilityServiceInfoBaseImpl {
        AccessibilityServiceInfoBaseImpl() {
        }

        public int getCapabilities(AccessibilityServiceInfo accessibilityServiceInfo) {
            if (AccessibilityServiceInfoCompat.getCanRetrieveWindowContent(accessibilityServiceInfo)) {
                return 1;
            }
            return 0;
        }

        public String loadDescription(AccessibilityServiceInfo accessibilityServiceInfo, PackageManager packageManager) {
            return null;
        }
    }

    @RequiresApi(16)
    static class AccessibilityServiceInfoApi16Impl extends AccessibilityServiceInfoBaseImpl {
        AccessibilityServiceInfoApi16Impl() {
        }

        public String loadDescription(AccessibilityServiceInfo accessibilityServiceInfo, PackageManager packageManager) {
            return accessibilityServiceInfo.loadDescription(packageManager);
        }
    }

    @RequiresApi(18)
    static class AccessibilityServiceInfoApi18Impl extends AccessibilityServiceInfoApi16Impl {
        AccessibilityServiceInfoApi18Impl() {
        }

        public int getCapabilities(AccessibilityServiceInfo accessibilityServiceInfo) {
            return accessibilityServiceInfo.getCapabilities();
        }
    }

    static {
        if (VERSION.SDK_INT >= 18) {
            IMPL = new AccessibilityServiceInfoApi18Impl();
        } else if (VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityServiceInfoApi16Impl();
        } else {
            IMPL = new AccessibilityServiceInfoBaseImpl();
        }
    }

    private AccessibilityServiceInfoCompat() {
    }

    @Deprecated
    public static String getId(AccessibilityServiceInfo accessibilityServiceInfo) {
        return accessibilityServiceInfo.getId();
    }

    @Deprecated
    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityServiceInfo) {
        return accessibilityServiceInfo.getResolveInfo();
    }

    @Deprecated
    public static String getSettingsActivityName(AccessibilityServiceInfo accessibilityServiceInfo) {
        return accessibilityServiceInfo.getSettingsActivityName();
    }

    @Deprecated
    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityServiceInfo) {
        return accessibilityServiceInfo.getCanRetrieveWindowContent();
    }

    @Deprecated
    public static String getDescription(AccessibilityServiceInfo accessibilityServiceInfo) {
        return accessibilityServiceInfo.getDescription();
    }

    public static String loadDescription(AccessibilityServiceInfo accessibilityServiceInfo, PackageManager packageManager) {
        return IMPL.loadDescription(accessibilityServiceInfo, packageManager);
    }

    public static String feedbackTypeToString(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (i > 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i);
            i &= numberOfTrailingZeros ^ -1;
            if (stringBuilder.length() > 1) {
                stringBuilder.append(", ");
            }
            switch (numberOfTrailingZeros) {
                case 1:
                    stringBuilder.append("FEEDBACK_SPOKEN");
                    break;
                case 2:
                    stringBuilder.append("FEEDBACK_HAPTIC");
                    break;
                case 4:
                    stringBuilder.append("FEEDBACK_AUDIBLE");
                    break;
                case 8:
                    stringBuilder.append("FEEDBACK_VISUAL");
                    break;
                case 16:
                    stringBuilder.append("FEEDBACK_GENERIC");
                    break;
                default:
                    break;
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String flagToString(int i) {
        switch (i) {
            case 1:
                return "DEFAULT";
            case 2:
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            case 4:
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            case 8:
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case 16:
                return "FLAG_REPORT_VIEW_IDS";
            case 32:
                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
            default:
                return null;
        }
    }

    public static int getCapabilities(AccessibilityServiceInfo accessibilityServiceInfo) {
        return IMPL.getCapabilities(accessibilityServiceInfo);
    }

    public static String capabilityToString(int i) {
        switch (i) {
            case 1:
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            case 2:
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            case 4:
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case 8:
                return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
            default:
                return "UNKNOWN";
        }
    }
}
