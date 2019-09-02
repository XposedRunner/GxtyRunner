package me.shaohui.shareutil.share;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SharePlatform {
    public static final int DEFAULT = 0;
    public static final int QQ = 1;
    public static final int QZONE = 2;
    public static final int WEIBO = 5;
    public static final int WX = 3;
    public static final int WX_TIMELINE = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Platform {
    }
}
