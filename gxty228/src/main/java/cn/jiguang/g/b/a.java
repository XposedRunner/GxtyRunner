package cn.jiguang.g.b;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public final class a {
    private HashMap<String, Serializable> a = new HashMap(5);

    public static int a(Serializable serializable) {
        return serializable == null ? 0 : serializable instanceof String ? 1 : serializable instanceof Integer ? 2 : serializable instanceof Long ? 4 : serializable instanceof Boolean ? 3 : serializable instanceof Float ? 5 : serializable instanceof HashSet ? 6 : 0;
    }

    public static HashSet<String> a(SharedPreferences sharedPreferences, String str) {
        if (VERSION.SDK_INT >= 11) {
            Set<String> stringSet = sharedPreferences.getStringSet(str, null);
            if (stringSet != null) {
                if (stringSet instanceof HashSet) {
                    return (HashSet) stringSet;
                }
                HashSet<String> hashSet = new HashSet();
                for (String add : stringSet) {
                    hashSet.add(add);
                }
                return hashSet;
            }
        }
        return null;
    }

    public final a a(String str, int i) {
        this.a.put(str, Integer.valueOf(i));
        return this;
    }

    public final a a(String str, long j) {
        this.a.put(str, Long.valueOf(j));
        return this;
    }

    public final a a(String str, Serializable serializable) {
        this.a.put(str, serializable);
        return this;
    }

    public final a a(String str, String str2) {
        this.a.put(str, str2);
        return this;
    }

    public final Set<Entry<String, Serializable>> a() {
        return this.a.entrySet();
    }

    public final boolean a(SharedPreferences sharedPreferences, boolean z) {
        if (sharedPreferences == null) {
            return false;
        }
        Editor edit = sharedPreferences.edit();
        for (Entry entry : this.a.entrySet()) {
            String str = (String) entry.getKey();
            Serializable serializable = (Serializable) entry.getValue();
            if (serializable == null) {
                edit.remove(str);
            } else if (serializable instanceof String) {
                edit.putString(str, (String) serializable);
            } else if (serializable instanceof Integer) {
                edit.putInt(str, ((Integer) serializable).intValue());
            } else if (serializable instanceof Boolean) {
                edit.putBoolean(str, ((Boolean) serializable).booleanValue());
            } else if (serializable instanceof Long) {
                edit.putLong(str, ((Long) serializable).longValue());
            } else if (serializable instanceof Float) {
                edit.putFloat(str, ((Float) serializable).floatValue());
            } else if ((serializable instanceof HashSet) && VERSION.SDK_INT >= 11) {
                try {
                    edit.putStringSet(str, (HashSet) serializable);
                } catch (ClassCastException e) {
                }
            }
        }
        edit.apply();
        return true;
    }

    public final int b() {
        return this.a.size();
    }

    public final <T extends Serializable> T b(String str, T t) {
        try {
            T t2 = (Serializable) this.a.get(str);
            return t2 == null ? t : t2;
        } catch (Throwable th) {
            return t;
        }
    }

    public final String toString() {
        return "values=" + this.a;
    }
}
