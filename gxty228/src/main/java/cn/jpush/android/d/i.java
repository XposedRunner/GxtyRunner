package cn.jpush.android.d;

import java.lang.reflect.Method;

public final class i {
    public static Object a(Object obj, String str, Class[] clsArr, Object[] objArr) throws Exception {
        Exception exception = null;
        if (objArr.length != clsArr.length) {
            throw new IllegalArgumentException("argClasses' size is not equal to args' size");
        }
        Object invoke;
        Method method = obj.getClass().getMethod(str, clsArr);
        boolean isAccessible = method.isAccessible();
        if (!isAccessible) {
            method.setAccessible(true);
        }
        try {
            invoke = method.invoke(obj, objArr);
        } catch (Exception e) {
            Exception exception2 = e;
            invoke = null;
            exception = exception2;
        }
        if (!isAccessible) {
            method.setAccessible(false);
        }
        if (exception == null) {
            return invoke;
        }
        throw exception;
    }
}
