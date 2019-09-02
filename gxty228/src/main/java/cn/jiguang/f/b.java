package cn.jiguang.f;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class b {
    private static Map<String, Method> a = new HashMap();

    public static Object a(Class cls, String str, Object... objArr) {
        Object[] b = c.b(objArr);
        Class[] a = c.a(c.a(b));
        b = c.b(b);
        Method c = c(cls, str, a);
        if (c != null) {
            return c.invoke(null, b);
        }
        throw new NoSuchMethodException("No such accessible method: " + str + "() on object: " + cls.getName());
    }

    public static <T> T a(Class<T> cls, Object[] objArr, Class<?>[] clsArr) {
        Object[] b = c.b(objArr);
        Constructor a = a(cls, c.a((Class[]) clsArr));
        if (a != null) {
            return a.newInstance(b);
        }
        throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
    }

    private static <T> Constructor<T> a(Class<T> cls, Class<?>... clsArr) {
        d.a(cls != null, "class cannot be null", new Object[0]);
        try {
            AccessibleObject constructor = cls.getConstructor(clsArr);
            a.a(constructor);
            return constructor;
        } catch (NoSuchMethodException e) {
            Constructor<T> constructor2 = null;
            for (Member member : cls.getConstructors()) {
                if (a.a((Class[]) clsArr, member.getParameterTypes(), true)) {
                    AccessibleObject accessibleObject;
                    d.a(member != null, "constructor cannot be null", new Object[0]);
                    if (a.a(member)) {
                        int i;
                        for (Class declaringClass = member.getDeclaringClass(); declaringClass != null; declaringClass = declaringClass.getEnclosingClass()) {
                            if (!Modifier.isPublic(declaringClass.getModifiers())) {
                                i = 0;
                                break;
                            }
                        }
                        boolean z = true;
                        if (i != 0) {
                            accessibleObject = member;
                            if (accessibleObject != null) {
                                a.a(accessibleObject);
                                if (constructor2 != null || a.a(accessibleObject.getParameterTypes(), constructor2.getParameterTypes(), (Class[]) clsArr) < 0) {
                                    constructor2 = accessibleObject;
                                }
                            }
                        }
                    }
                    accessibleObject = null;
                    if (accessibleObject != null) {
                        a.a(accessibleObject);
                        if (constructor2 != null) {
                        }
                        constructor2 = accessibleObject;
                    }
                }
            }
            return constructor2;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method = null;
        Class superclass = cls.getSuperclass();
        while (superclass != null) {
            if (Modifier.isPublic(superclass.getModifiers())) {
                try {
                    method = superclass.getMethod(str, clsArr);
                    break;
                } catch (NoSuchMethodException e) {
                }
            } else {
                superclass = superclass.getSuperclass();
            }
        }
        return method;
    }

    private static Method b(Class<?> cls, String str, Class<?>... clsArr) {
        Class superclass;
        while (superclass != null) {
            Class[] interfaces = superclass.getInterfaces();
            int length = interfaces.length;
            int i = 0;
            while (i < length) {
                Class cls2 = interfaces[i];
                if (Modifier.isPublic(cls2.getModifiers())) {
                    try {
                        return cls2.getDeclaredMethod(str, clsArr);
                    } catch (NoSuchMethodException e) {
                        Method b = b(cls2, str, clsArr);
                        if (b != null) {
                            return b;
                        }
                    }
                } else {
                    i++;
                }
            }
            superclass = superclass.getSuperclass();
        }
        return null;
    }

    private static Method c(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        AccessibleObject method2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cls.toString()).append("#").append(str);
        if (clsArr == null || clsArr.length <= 0) {
            stringBuilder.append(Void.class.toString());
        } else {
            for (Class cls2 : clsArr) {
                stringBuilder.append(cls2.toString()).append("#");
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        synchronized (a) {
            method = (Method) a.get(stringBuilder2);
        }
        if (method == null) {
            try {
                method2 = cls.getMethod(str, clsArr);
                a.a(method2);
                synchronized (a) {
                    a.put(stringBuilder2, method2);
                }
            } catch (NoSuchMethodException e) {
                method = null;
                for (Method method3 : cls.getMethods()) {
                    Method method32;
                    if (method32.getName().equals(str) && a.a((Class[]) clsArr, method32.getParameterTypes(), true)) {
                        if (a.a((Member) method32)) {
                            Class declaringClass = method32.getDeclaringClass();
                            if (!Modifier.isPublic(declaringClass.getModifiers())) {
                                String name = method32.getName();
                                Class[] parameterTypes = method32.getParameterTypes();
                                method32 = b(declaringClass, name, parameterTypes);
                                if (method32 == null) {
                                    method32 = a(declaringClass, name, parameterTypes);
                                }
                            }
                        } else {
                            method32 = null;
                        }
                        if (method32 != null && (method2 == null || a.a(method32.getParameterTypes(), method2.getParameterTypes(), (Class[]) clsArr) < 0)) {
                            method = method32;
                        }
                    }
                }
                if (method2 != null) {
                    a.a(method2);
                }
                synchronized (a) {
                    a.put(stringBuilder2, method2);
                }
            }
        } else if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method;
    }
}
