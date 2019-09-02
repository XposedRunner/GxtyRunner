package com.google.gson.internal;

import cn.jiguang.net.HttpUtils;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/* renamed from: com.google.gson.internal.$Gson$Types */
public final class C$Gson$Types {
    static final Type[] a = new Type[0];

    /* compiled from: $Gson$Types */
    private static final class GenericArrayTypeImpl implements Serializable, GenericArrayType {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = C$Gson$Types.d(type);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && C$Gson$Types.a((Type) this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return C$Gson$Types.f(this.componentType) + "[]";
        }
    }

    /* compiled from: $Gson$Types */
    private static final class ParameterizedTypeImpl implements Serializable, ParameterizedType {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            int i;
            int i2 = 0;
            if (type2 instanceof Class) {
                boolean z;
                Class cls = (Class) type2;
                i = (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null) ? 1 : 0;
                if (type == null && i == 0) {
                    z = false;
                } else {
                    z = true;
                }
                a.a(z);
            }
            this.ownerType = type == null ? null : C$Gson$Types.d(type);
            this.rawType = C$Gson$Types.d(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            i = this.typeArguments.length;
            while (i2 < i) {
                a.a(this.typeArguments[i2]);
                C$Gson$Types.h(this.typeArguments[i2]);
                this.typeArguments[i2] = C$Gson$Types.d(this.typeArguments[i2]);
                i2++;
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public Type getRawType() {
            return this.rawType;
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && C$Gson$Types.a((Type) this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ C$Gson$Types.a(this.ownerType);
        }

        public String toString() {
            int length = this.typeArguments.length;
            if (length == 0) {
                return C$Gson$Types.f(this.rawType);
            }
            StringBuilder stringBuilder = new StringBuilder((length + 1) * 30);
            stringBuilder.append(C$Gson$Types.f(this.rawType)).append("<").append(C$Gson$Types.f(this.typeArguments[0]));
            for (int i = 1; i < length; i++) {
                stringBuilder.append(", ").append(C$Gson$Types.f(this.typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    /* compiled from: $Gson$Types */
    private static final class WildcardTypeImpl implements Serializable, WildcardType {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            boolean z;
            boolean z2 = true;
            a.a(typeArr2.length <= 1);
            if (typeArr.length == 1) {
                z = true;
            } else {
                z = false;
            }
            a.a(z);
            if (typeArr2.length == 1) {
                a.a(typeArr2[0]);
                C$Gson$Types.h(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z2 = false;
                }
                a.a(z2);
                this.lowerBound = C$Gson$Types.d(typeArr2[0]);
                this.upperBound = Object.class;
                return;
            }
            a.a(typeArr[0]);
            C$Gson$Types.h(typeArr[0]);
            this.lowerBound = null;
            this.upperBound = C$Gson$Types.d(typeArr[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            if (this.lowerBound == null) {
                return C$Gson$Types.a;
            }
            return new Type[]{this.lowerBound};
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && C$Gson$Types.a((Type) this, (WildcardType) obj);
        }

        public int hashCode() {
            return (this.lowerBound != null ? this.lowerBound.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                return "? super " + C$Gson$Types.f(this.lowerBound);
            }
            if (this.upperBound == Object.class) {
                return HttpUtils.URL_AND_PARA_SEPARATOR;
            }
            return "? extends " + C$Gson$Types.f(this.upperBound);
        }
    }

    public static ParameterizedType a(Type type, Type type2, Type... typeArr) {
        return new ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static GenericArrayType a(Type type) {
        return new GenericArrayTypeImpl(type);
    }

    public static WildcardType b(Type type) {
        return new WildcardTypeImpl(type instanceof WildcardType ? ((WildcardType) type).getUpperBounds() : new Type[]{type}, a);
    }

    public static WildcardType c(Type type) {
        return new WildcardTypeImpl(new Type[]{Object.class}, type instanceof WildcardType ? ((WildcardType) type).getLowerBounds() : new Type[]{type});
    }

    public static Type d(Type type) {
        if (type instanceof Class) {
            GenericArrayTypeImpl genericArrayTypeImpl;
            Class cls = (Class) type;
            if (cls.isArray()) {
                genericArrayTypeImpl = new GenericArrayTypeImpl(C$Gson$Types.d(cls.getComponentType()));
            } else {
                Object obj = cls;
            }
            return genericArrayTypeImpl;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> e(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            a.a(rawType instanceof Class);
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(C$Gson$Types.e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return C$Gson$Types.e(((WildcardType) type).getUpperBounds()[0]);
            }
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
        }
    }

    static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean a(Type type, Type type2) {
        boolean z = true;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!(C$Gson$Types.a(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()))) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return C$Gson$Types.a(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!(Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds()))) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (!(typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName()))) {
                z = false;
            }
            return z;
        }
    }

    static int a(Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    public static String f(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return C$Gson$Types.a(cls.getGenericInterfaces()[i], interfaces[i], (Class) cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return C$Gson$Types.a(cls.getGenericSuperclass(), (Class) superclass, (Class) cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    static Type b(Type type, Class<?> cls, Class<?> cls2) {
        a.a(cls2.isAssignableFrom(cls));
        return C$Gson$Types.a(type, (Class) cls, C$Gson$Types.a(type, (Class) cls, (Class) cls2));
    }

    public static Type g(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        return ((Class) type).getComponentType();
    }

    public static Type a(Type type, Class<?> cls) {
        Type b = C$Gson$Types.b(type, cls, Collection.class);
        if (b instanceof WildcardType) {
            b = ((WildcardType) b).getUpperBounds()[0];
        }
        if (b instanceof ParameterizedType) {
            return ((ParameterizedType) b).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type b = C$Gson$Types.b(type, cls, Map.class);
        if (b instanceof ParameterizedType) {
            return ((ParameterizedType) b).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type a(Type type, Class<?> cls, Type type2) {
        Type type3 = type2;
        while (type3 instanceof TypeVariable) {
            type3 = (TypeVariable) type3;
            type2 = C$Gson$Types.a(type, (Class) cls, (TypeVariable) type3);
            if (type2 == type3) {
                return type2;
            }
            type3 = type2;
        }
        Type componentType;
        Type a;
        if ((type3 instanceof Class) && ((Class) type3).isArray()) {
            Class cls2 = (Class) type3;
            componentType = cls2.getComponentType();
            a = C$Gson$Types.a(type, (Class) cls, componentType);
            if (componentType != a) {
                return C$Gson$Types.a(a);
            }
            return cls2;
        } else if (type3 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type3;
            componentType = genericArrayType.getGenericComponentType();
            a = C$Gson$Types.a(type, (Class) cls, componentType);
            if (componentType != a) {
                return C$Gson$Types.a(a);
            }
            return genericArrayType;
        } else if (type3 instanceof ParameterizedType) {
            int i;
            ParameterizedType parameterizedType = (ParameterizedType) type3;
            componentType = parameterizedType.getOwnerType();
            Type a2 = C$Gson$Types.a(type, (Class) cls, componentType);
            if (a2 != componentType) {
                i = 1;
            } else {
                i = 0;
            }
            r4 = parameterizedType.getActualTypeArguments();
            int length = r4.length;
            int i2 = i;
            r1 = r4;
            for (int i3 = 0; i3 < length; i3++) {
                Type a3 = C$Gson$Types.a(type, (Class) cls, r1[i3]);
                if (a3 != r1[i3]) {
                    if (i2 == 0) {
                        r1 = (Type[]) r1.clone();
                        i2 = 1;
                    }
                    r1[i3] = a3;
                }
            }
            if (i2 != 0) {
                return C$Gson$Types.a(a2, parameterizedType.getRawType(), r1);
            }
            return parameterizedType;
        } else if (!(type3 instanceof WildcardType)) {
            return type3;
        } else {
            WildcardType wildcardType = (WildcardType) type3;
            r1 = wildcardType.getLowerBounds();
            r4 = wildcardType.getUpperBounds();
            if (r1.length == 1) {
                a = C$Gson$Types.a(type, (Class) cls, r1[0]);
                if (a != r1[0]) {
                    return C$Gson$Types.c(a);
                }
                return wildcardType;
            } else if (r4.length != 1) {
                return wildcardType;
            } else {
                componentType = C$Gson$Types.a(type, (Class) cls, r4[0]);
                if (componentType != r4[0]) {
                    return C$Gson$Types.b(componentType);
                }
                return wildcardType;
            }
        }
    }

    static Type a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class a = C$Gson$Types.a((TypeVariable) typeVariable);
        if (a == null) {
            return typeVariable;
        }
        Type a2 = C$Gson$Types.a(type, (Class) cls, a);
        if (!(a2 instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) a2).getActualTypeArguments()[C$Gson$Types.a(a.getTypeParameters(), (Object) typeVariable)];
    }

    private static int a(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> a(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
    }

    static void h(Type type) {
        boolean z = ((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true;
        a.a(z);
    }
}
