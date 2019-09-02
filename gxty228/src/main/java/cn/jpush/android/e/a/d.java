package cn.jpush.android.e.a;

import android.text.TextUtils;
import android.webkit.WebView;
import cn.jpush.android.d.e;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d {
    private HashMap<String, Method> a;
    private String b;
    private String c;

    public d(String str, Class cls) {
        try {
            if (TextUtils.isEmpty(str)) {
                throw new Exception("injected name can not be null");
            }
            this.b = str;
            this.a = new HashMap();
            Method[] declaredMethods = cls.getDeclaredMethods();
            StringBuilder stringBuilder = new StringBuilder("javascript:(function(b){console.log(\"");
            stringBuilder.append(this.b);
            stringBuilder.append(" initialization begin\");var a={queue:[],callback:function(){var d=Array.prototype.slice.call(arguments,0);var c=d.shift();var e=d.shift();this.queue[c].apply(this,d);if(!e){delete this.queue[c]}}};");
            for (Method method : declaredMethods) {
                if (method.getModifiers() == 9) {
                    String a = a(method);
                    if (a != null) {
                        this.a.put(a, method);
                        stringBuilder.append(String.format(Locale.ENGLISH, "a.%s=", new Object[]{method.getName()}));
                    }
                }
            }
            stringBuilder.append("function(){var f=Array.prototype.slice.call(arguments,0);if(f.length<1){throw\"");
            stringBuilder.append(this.b);
            stringBuilder.append(" call error, message:miss method name\"}var e=[];for(var h=1;h<f.length;h++){var c=f[h];var j=typeof c;e[e.length]=j;if(j==\"function\"){var d=a.queue.length;a.queue[d]=c;f[h]=d}}var g=JSON.parse(prompt(JSON.stringify({method:f.shift(),types:e,args:f})));if(g.code!=200){throw\"");
            stringBuilder.append(this.b);
            stringBuilder.append(" call error, code:\"+g.code+\", message:\"+g.result}return g.result};Object.getOwnPropertyNames(a).forEach(function(d){var c=a[d];if(typeof c===\"function\"&&d!==\"callback\"){a[d]=function(){return c.apply(a,[d].concat(Array.prototype.slice.call(arguments,0)))}}});b.");
            stringBuilder.append(this.b);
            stringBuilder.append("=a;console.log(\"");
            stringBuilder.append(this.b);
            stringBuilder.append(" initialization end\")})(window);");
            this.c = stringBuilder.toString();
            e.c("JsCallJava", "----------" + stringBuilder.toString());
        } catch (Exception e) {
            e.i("JsCallJava", "init js error:" + e.getMessage());
        }
    }

    private static String a(Method method) {
        String name = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        int length = parameterTypes.length;
        if (length <= 0 || parameterTypes[0] != WebView.class) {
            e.g("JsCallJava", "method(" + name + ") must use webview to be first parameter, will be pass");
            return null;
        }
        String str = name;
        for (int i = 1; i < length; i++) {
            Class cls = parameterTypes[i];
            if (cls == String.class) {
                str = str + "_S";
            } else if (cls == Integer.TYPE || cls == Long.TYPE || cls == Float.TYPE || cls == Double.TYPE) {
                str = str + "_N";
            } else if (cls == Boolean.TYPE) {
                str = str + "_B";
            } else if (cls == JSONObject.class) {
                str = str + "_O";
            } else {
                str = str + "_P";
            }
        }
        return str;
    }

    public final String a() {
        return this.c;
    }

    public final String a(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            return a(str, 500, "call data empty");
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("method");
            JSONArray jSONArray = jSONObject.getJSONArray("types");
            JSONArray jSONArray2 = jSONObject.getJSONArray("args");
            int length = jSONArray.length();
            Object[] objArr = new Object[(length + 1)];
            objArr[0] = webView;
            int i = 0;
            String str2 = string;
            int i2 = 0;
            while (i < length) {
                int i3;
                String optString = jSONArray.optString(i);
                int i4;
                if ("string".equals(optString)) {
                    optString = str2 + "_S";
                    objArr[i + 1] = jSONArray2.isNull(i) ? null : jSONArray2.getString(i);
                    i4 = i2;
                    string = optString;
                    i3 = i4;
                } else if ("number".equals(optString)) {
                    str2 = str2 + "_N";
                    i3 = ((i2 * 10) + i) + 1;
                    string = str2;
                } else if ("boolean".equals(optString)) {
                    optString = str2 + "_B";
                    objArr[i + 1] = Boolean.valueOf(jSONArray2.getBoolean(i));
                    i4 = i2;
                    string = optString;
                    i3 = i4;
                } else if ("object".equals(optString)) {
                    optString = str2 + "_O";
                    objArr[i + 1] = jSONArray2.isNull(i) ? null : jSONArray2.getJSONObject(i);
                    i4 = i2;
                    string = optString;
                    i3 = i4;
                } else {
                    i4 = i2;
                    string = str2 + "_P";
                    i3 = i4;
                }
                i++;
                str2 = string;
                i2 = i3;
            }
            Method method = (Method) this.a.get(str2);
            if (method == null) {
                return a(str, 500, "not found method(" + str2 + ") with valid parameters");
            }
            if (i2 > 0) {
                Class[] parameterTypes = method.getParameterTypes();
                while (i2 > 0) {
                    i = i2 - ((i2 / 10) * 10);
                    Class cls = parameterTypes[i];
                    if (cls == Integer.TYPE) {
                        objArr[i] = Integer.valueOf(jSONArray2.getInt(i - 1));
                    } else if (cls == Long.TYPE) {
                        objArr[i] = Long.valueOf(Long.parseLong(jSONArray2.getString(i - 1)));
                    } else {
                        objArr[i] = Double.valueOf(jSONArray2.getDouble(i - 1));
                    }
                    i2 /= 10;
                }
            }
            return a(str, 200, method.invoke(null, objArr));
        } catch (Exception e) {
            if (e.getCause() != null) {
                return a(str, 500, "method execute error:" + e.getCause().getMessage());
            }
            return a(str, 500, "method execute error:" + e.getMessage());
        }
    }

    private String a(String str, int i, Object obj) {
        String str2 = "";
        if (obj == null) {
            str2 = "null";
        } else if (obj instanceof String) {
            str2 = "\"" + ((String) obj).replace("\"", "\\\"") + "\"";
        } else if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof JSONObject)) {
            str2 = String.valueOf(obj);
        }
        str2 = String.format(Locale.ENGLISH, "{\"code\": %d, \"result\": %s}", new Object[]{Integer.valueOf(i), str2});
        e.c("JsCallJava", this.b + " call json: " + str + " result:" + str2);
        return str2;
    }
}
