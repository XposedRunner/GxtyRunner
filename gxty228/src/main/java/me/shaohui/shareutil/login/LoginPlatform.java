package me.shaohui.shareutil.login;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class LoginPlatform {
    public static final int QQ = 1;
    public static final int WEIBO = 5;
    public static final int WX = 3;

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Platform {
    }
}
