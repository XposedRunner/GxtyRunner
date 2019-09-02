package com.blankj.utilcode.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class ReflectUtils {
    private final Object object;
    private final Class<?> type;

    private static class NULL {
        private NULL() {
        }
    }

    public static class ReflectException extends RuntimeException {
        private static final long serialVersionUID = 858774075258496016L;

        public ReflectException(String str) {
            super(str);
        }

        public ReflectException(String str, Throwable th) {
            super(str, th);
        }

        public ReflectException(Throwable th) {
            super(th);
        }
    }

    private ReflectUtils(Class<?> cls) {
        this(cls, cls);
    }

    private ReflectUtils(Class<?> cls, Object obj) {
        this.type = cls;
        this.object = obj;
    }

    public static ReflectUtils reflect(String str) throws ReflectException {
        return reflect(forName(str));
    }

    public static ReflectUtils reflect(String str, ClassLoader classLoader) throws ReflectException {
        return reflect(forName(str, classLoader));
    }

    public static ReflectUtils reflect(Class<?> cls) throws ReflectException {
        return new ReflectUtils(cls);
    }

    public static ReflectUtils reflect(Object obj) throws ReflectException {
        return new ReflectUtils(obj == null ? Object.class : obj.getClass(), obj);
    }

    private static Class<?> forName(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> forName(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (Throwable e) {
            throw new ReflectException(e);
        }
    }

    public ReflectUtils newInstance() {
        return newInstance(new Object[0]);
    }

    public ReflectUtils newInstance(Object... objArr) {
        Class[] argsType = getArgsType(objArr);
        try {
            return newInstance(type().getDeclaredConstructor(argsType), objArr);
        } catch (Throwable e) {
            List arrayList = new ArrayList();
            for (Constructor constructor : type().getDeclaredConstructors()) {
                if (match(constructor.getParameterTypes(), argsType)) {
                    arrayList.add(constructor);
                }
            }
            if (arrayList.isEmpty()) {
                throw new ReflectException(e);
            }
            sortConstructors(arrayList);
            return newInstance((Constructor) arrayList.get(0), objArr);
        }
    }

    private Class<?>[] getArgsType(Object... objArr) {
        int i = 0;
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        while (i < objArr.length) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? NULL.class : obj.getClass();
            i++;
        }
        return clsArr;
    }

    private void sortConstructors(List<Constructor<?>> list) {
        Collections.sort(list, new Comparator<Constructor<?>>() {
            public int compare(Constructor<?> constructor, Constructor<?> constructor2) {
                Class[] parameterTypes = constructor.getParameterTypes();
                Class[] parameterTypes2 = constructor2.getParameterTypes();
                int length = parameterTypes.length;
                int i = 0;
                while (i < length) {
                    if (parameterTypes[i].equals(parameterTypes2[i])) {
                        i++;
                    } else if (ReflectUtils.this.wrapper(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.wrapper(parameterTypes2[i]))) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return 0;
            }
        });
    }

    private ReflectUtils newInstance(Constructor<?> constructor, Object... objArr) {
        try {
            return new ReflectUtils(constructor.getDeclaringClass(), ((Constructor) accessible(constructor)).newInstance(objArr));
        } catch (Throwable e) {
            throw new ReflectException(e);
        }
    }

    public ReflectUtils field(String str) {
        try {
            Field field = getField(str);
            return new ReflectUtils(field.getType(), field.get(this.object));
        } catch (Throwable e) {
            throw new ReflectException(e);
        }
    }

    public ReflectUtils field(String str, Object obj) {
        try {
            getField(str).set(this.object, unwrap(obj));
            return this;
        } catch (Throwable e) {
            throw new ReflectException(e);
        }
    }

    private Field getField(String str) throws IllegalAccessException {
        Field accessibleField = getAccessibleField(str);
        if ((accessibleField.getModifiers() & 16) == 16) {
            try {
                Field declaredField = Field.class.getDeclaredField("modifiers");
                declaredField.setAccessible(true);
                declaredField.setInt(accessibleField, accessibleField.getModifiers() & -17);
            } catch (NoSuchFieldException e) {
            }
        }
        return accessibleField;
    }

    private Field getAccessibleField(String str) {
        Class type = type();
        try {
            return (Field) accessible(type.getField(str));
        } catch (Throwable e) {
            Throwable th = e;
            while (true) {
                try {
                    break;
                } catch (NoSuchFieldException e2) {
                    Class superclass = type.getSuperclass();
                    if (superclass == null) {
                        throw new ReflectException(th);
                    }
                    type = superclass;
                }
            }
            return (Field) accessible(type.getDeclaredField(str));
        }
    }

    private Object unwrap(Object obj) {
        if (obj instanceof ReflectUtils) {
            return ((ReflectUtils) obj).get();
        }
        return obj;
    }

    public ReflectUtils method(String str) throws ReflectException {
        return method(str, new Object[0]);
    }

    public ReflectUtils method(String str, Object... objArr) throws ReflectException {
        ReflectUtils method;
        Class[] argsType = getArgsType(objArr);
        try {
            method = method(exactMethod(str, argsType), this.object, objArr);
        } catch (NoSuchMethodException e) {
            try {
                method = method(similarMethod(str, argsType), this.object, objArr);
            } catch (Throwable e2) {
                throw new ReflectException(e2);
            }
        }
        return method;
    }

    private ReflectUtils method(Method method, Object obj, Object... objArr) {
        try {
            accessible(method);
            if (method.getReturnType() != Void.TYPE) {
                return reflect(method.invoke(obj, objArr));
            }
            method.invoke(obj, objArr);
            return reflect(obj);
        } catch (Throwable e) {
            throw new ReflectException(e);
        }
    }

    private Method exactMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Method method;
        Class type = type();
        try {
            method = type.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            do {
                try {
                    method = type.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException e2) {
                    type = type.getSuperclass();
                    if (type != null) {
                        throw new NoSuchMethodException();
                    }
                }
            } while (type != null);
            throw new NoSuchMethodException();
        }
        return method;
    }

    private Method similarMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class type = type();
        List arrayList = new ArrayList();
        for (Method method : type.getMethods()) {
            if (isSimilarSignature(method, str, clsArr)) {
                arrayList.add(method);
            }
        }
        if (arrayList.isEmpty()) {
            do {
                for (Method method2 : type.getDeclaredMethods()) {
                    if (isSimilarSignature(method2, str, clsArr)) {
                        arrayList.add(method2);
                    }
                }
                if (arrayList.isEmpty()) {
                    type = type.getSuperclass();
                } else {
                    sortMethods(arrayList);
                    return (Method) arrayList.get(0);
                }
            } while (type != null);
            throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + type() + ".");
        }
        sortMethods(arrayList);
        return (Method) arrayList.get(0);
    }

    private void sortMethods(List<Method> list) {
        Collections.sort(list, new Comparator<Method>() {
            public int compare(Method method, Method method2) {
                Class[] parameterTypes = method.getParameterTypes();
                Class[] parameterTypes2 = method2.getParameterTypes();
                int length = parameterTypes.length;
                int i = 0;
                while (i < length) {
                    if (parameterTypes[i].equals(parameterTypes2[i])) {
                        i++;
                    } else if (ReflectUtils.this.wrapper(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.wrapper(parameterTypes2[i]))) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return 0;
            }
        });
    }

    private boolean isSimilarSignature(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && match(method.getParameterTypes(), clsArr);
    }

    private boolean match(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        int i = 0;
        while (i < clsArr2.length) {
            if (clsArr2[i] != NULL.class && !wrapper(clsArr[i]).isAssignableFrom(wrapper(clsArr2[i]))) {
                return false;
            }
            i++;
        }
        return true;
    }

    private <T extends AccessibleObject> T accessible(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (t.isAccessible()) {
            return t;
        }
        t.setAccessible(true);
        return t;
    }

    public <P> P proxy(Class<P> cls) {
        final boolean z = this.object instanceof Map;
        InvocationHandler anonymousClass3 = new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) {
                String name = method.getName();
                try {
                    return ReflectUtils.reflect(ReflectUtils.this.object).method(name, objArr).get();
                } catch (ReflectException e) {
                    ReflectException reflectException = e;
                    if (z) {
                        Map map = (Map) ReflectUtils.this.object;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(ReflectUtils.property(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith("is")) {
                            return map.get(ReflectUtils.property(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith("set")) {
                            map.put(ReflectUtils.property(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw reflectException;
                }
            }
        };
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, anonymousClass3);
    }

    private static String property(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private Class<?> type() {
        return this.type;
    }

    private Class<?> wrapper(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (!cls.isPrimitive()) {
            return cls;
        }
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        if (Short.TYPE == cls) {
            return Short.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        if (Void.TYPE == cls) {
            return Void.class;
        }
        return cls;
    }

    public <T> T get() {
        return this.object;
    }

    public int hashCode() {
        return this.object.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ReflectUtils) && this.object.equals(((ReflectUtils) obj).get());
    }

    public String toString() {
        return this.object.toString();
    }
}
