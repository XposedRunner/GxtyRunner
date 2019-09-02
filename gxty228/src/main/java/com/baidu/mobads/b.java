package com.baidu.mobads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.baidu.mobads.AppActivity.ActionBarColorTheme;
import com.baidu.mobads.command.XAdCommandExtraInfo;
import com.baidu.mobads.command.XAdLandingPageExtraInfo;
import com.baidu.mobads.g.g;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.utils.l;
import com.baidu.mobads.vo.XAdInstanceInfo;
import dalvik.system.DexClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public class b {
    private static boolean b = false;
    private static Class<?> d;
    private static AtomicBoolean f = new AtomicBoolean(false);
    private static ActionBarColorTheme g;
    private Activity a;
    private Object c;
    private Method[] e = null;

    public void a(Activity activity) {
        this.a = activity;
    }

    public static boolean a() {
        return f.get();
    }

    private Method b(String str) {
        if (this.e == null) {
            return null;
        }
        for (Method method : this.e) {
            if (method.getName().equals(str)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    public void a(String str, Object... objArr) {
        int i = 0;
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            if (objArr != null) {
                i = objArr.length;
            }
            objArr2[1] = Integer.valueOf(i);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method b = b(str);
            if (b == null) {
                return;
            }
            if (objArr == null || objArr.length == 0) {
                b.invoke(null, new Object[0]);
            } else {
                b.invoke(null, objArr);
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
    }

    private void b(String str, Object... objArr) {
        int i = 0;
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            if (objArr != null) {
                i = objArr.length;
            }
            objArr2[1] = Integer.valueOf(i);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method b = b(str);
            if (b == null) {
                return;
            }
            if (objArr == null || objArr.length == 0) {
                b.invoke(this.c, new Object[0]);
            } else {
                b.invoke(this.c, objArr);
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
    }

    private boolean c(String str, Object... objArr) {
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            objArr2[1] = Integer.valueOf(objArr != null ? objArr.length : 0);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method b = b(str);
            if (b != null) {
                if (objArr == null || objArr.length == 0) {
                    return ((Boolean) b.invoke(this.c, new Object[0])).booleanValue();
                }
                return ((Boolean) b.invoke(this.c, objArr)).booleanValue();
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
        return false;
    }

    private Object d(String str, Object... objArr) {
        int i = 0;
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            if (objArr != null) {
                i = objArr.length;
            }
            objArr2[1] = Integer.valueOf(i);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method b = b(str);
            if (b != null) {
                if (objArr == null || objArr.length == 0) {
                    return b.invoke(this.c, new Object[0]);
                }
                return b.invoke(this.c, objArr);
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
        return null;
    }

    public boolean a(KeyEvent keyEvent) {
        return c("dispatchKeyEvent", keyEvent);
    }

    public boolean a(MotionEvent motionEvent) {
        return c("dispatchTouchEvent", motionEvent);
    }

    public boolean b(MotionEvent motionEvent) {
        return c("dispatchTrackballEvent", motionEvent);
    }

    protected void a(int i, int i2, Intent intent) {
        b("onActivityResult", Integer.valueOf(i), Integer.valueOf(i2), intent);
    }

    protected void a(Theme theme, int i, boolean z) {
        b("onApplyThemeResource", theme, Integer.valueOf(i), Boolean.valueOf(z));
    }

    protected void a(Activity activity, CharSequence charSequence) {
        b("onChildTitleChanged", activity, charSequence);
    }

    public void a(Configuration configuration) {
        b("onConfigurationChanged", configuration);
    }

    public void b() {
        b("onContentChanged", new Object[0]);
    }

    public boolean a(MenuItem menuItem) {
        return c("onContextItemSelected", menuItem);
    }

    public void a(Menu menu) {
        b("onContextMenuClosed", menu);
    }

    protected void a(Bundle bundle) {
        try {
            f.set(true);
            Intent intent = this.a.getIntent();
            if (intent != null) {
                intent.setExtrasClassLoader(getClass().getClassLoader());
                g = (ActionBarColorTheme) intent.getSerializableExtra("theme");
                b = intent.getBooleanExtra("showWhenLocked", true);
            }
            intent.putExtra("multiProcess", new com.baidu.mobads.utils.b().webviewMultiProcess(this.a));
            if (AppActivity.a() && intent.getParcelableExtra("EXTRA_DATA") == null) {
                Object xAdLandingPageExtraInfo = new XAdLandingPageExtraInfo(null, new XAdInstanceInfo(new JSONObject()));
                a(XAdLandingPageExtraInfo.class, xAdLandingPageExtraInfo, this.a.getIntent().getStringExtra("EXTRA_DATA_STRING"));
                a(XAdCommandExtraInfo.class, xAdLandingPageExtraInfo, this.a.getIntent().getStringExtra("EXTRA_DATA_STRING_COM"));
                a(XAdInstanceInfo.class, xAdLandingPageExtraInfo.getAdInstanceInfo(), this.a.getIntent().getStringExtra("EXTRA_DATA_STRING_AD"));
                intent.putExtra("EXTRA_DATA", xAdLandingPageExtraInfo);
            }
            String str = "com.baidu.mobads.container.landingpage.App2Activity";
            ClassLoader d = com.baidu.mobads.g.b.d();
            if (d == null) {
                d = a(str);
            } else {
                d = Class.forName(str, true, d);
            }
            this.e = d.getDeclaredMethods();
            this.c = d.getConstructor(new Class[]{Activity.class}).newInstance(new Object[]{this.a});
            a("canLpShowWhenLocked", Boolean.valueOf(b));
            a("setActionBarColor", Integer.valueOf(g.closeColor), Integer.valueOf(g.titleColor), Integer.valueOf(g.progressColor), Integer.valueOf(g.backgroundColor));
            l.a().d(str, d, this.c);
        } catch (Throwable e) {
            l.a().e(e);
        }
        b("onCreate", bundle);
    }

    public Class<?> a(String str) {
        Class<?> cls = null;
        IXAdLogger a = l.a();
        try {
            cls = Class.forName(str, true, new DexClassLoader(g.a(this.a), this.a.getFilesDir().getAbsolutePath(), null, getClass().getClassLoader()));
        } catch (Throwable e) {
            a.e(e);
        }
        a.i("jar.path=, clz=" + cls);
        return cls;
    }

    public void a(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        b("onCreateContextMenu", contextMenu, view, contextMenuInfo);
    }

    public CharSequence c() {
        return (CharSequence) d("onCreateDescription", new Object[0]);
    }

    protected Dialog a(int i) {
        Dialog dialog = (Dialog) d("onCreateDialog", Integer.valueOf(i));
        return dialog != null ? dialog : dialog;
    }

    public boolean b(Menu menu) {
        return c("onCreateOptionsMenu", menu);
    }

    public boolean a(int i, Menu menu) {
        return c("onCreatePanelMenu", Integer.valueOf(i), menu);
    }

    public View b(int i) {
        try {
            return (View) d("onCreatePanelView", Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean a(Bitmap bitmap, Canvas canvas) {
        return c("onCreateThumbnail", bitmap, canvas);
    }

    public View a(String str, Context context, AttributeSet attributeSet) {
        try {
            return (View) d("onCreateView", str, context, attributeSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void d() {
        f.set(false);
        b("onDestroy", new Object[0]);
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return c("onKeyDown", Integer.valueOf(i), keyEvent);
    }

    public boolean a(int i, int i2, KeyEvent keyEvent) {
        return c("onKeyMultiple", Integer.valueOf(i), Integer.valueOf(i2), keyEvent);
    }

    public boolean b(int i, KeyEvent keyEvent) {
        return c("onKeyUp", Integer.valueOf(i), keyEvent);
    }

    public void e() {
        b("onLowMemory", new Object[0]);
    }

    public boolean a(int i, MenuItem menuItem) {
        return c("onMenuItemSelected", Integer.valueOf(i), menuItem);
    }

    public boolean b(int i, Menu menu) {
        return c("onMenuOpened", Integer.valueOf(i), menu);
    }

    protected void a(Intent intent) {
        b("onNewIntent", intent);
    }

    public boolean b(MenuItem menuItem) {
        return c("onOptionsItemSelected", menuItem);
    }

    public void c(Menu menu) {
        b("onOptionsMenuClosed", menu);
    }

    public void c(int i, Menu menu) {
        b("onPanelClosed", Integer.valueOf(i), menu);
    }

    protected void f() {
        b("onPause", new Object[0]);
    }

    protected void b(Bundle bundle) {
        b("onPostCreate", bundle);
    }

    protected void g() {
        b("onPostResume", new Object[0]);
    }

    protected void a(int i, Dialog dialog) {
        b("onPrepareDialog", Integer.valueOf(i), dialog);
    }

    public boolean d(Menu menu) {
        return c("onPrepareOptionsMenu", menu);
    }

    public boolean a(int i, View view, Menu menu) {
        return c("onPreparePanel", Integer.valueOf(i), view, menu);
    }

    protected void h() {
        b("onRestart", new Object[0]);
    }

    protected void c(Bundle bundle) {
        b("onRestoreInstanceState", bundle);
    }

    protected void i() {
        b("onResume", new Object[0]);
    }

    public Object j() {
        return d("onRetainNonConfigurationInstance", new Object[0]);
    }

    protected void d(Bundle bundle) {
        b("onSaveInstanceState", bundle);
    }

    public boolean k() {
        return c("onSearchRequested", new Object[0]);
    }

    protected void l() {
        b("onStart", new Object[0]);
    }

    protected void m() {
        b("onStop", new Object[0]);
    }

    protected void a(CharSequence charSequence, int i) {
        b("onTitleChanged", charSequence, Integer.valueOf(i));
    }

    public boolean c(MotionEvent motionEvent) {
        return c("onTouchEvent", motionEvent);
    }

    public boolean d(MotionEvent motionEvent) {
        return c("onTrackballEvent", motionEvent);
    }

    public void n() {
        b("onUserInteraction", new Object[0]);
    }

    protected void o() {
        b("onUserLeaveHint", new Object[0]);
    }

    public void a(LayoutParams layoutParams) {
        b("onWindowAttributesChanged", layoutParams);
    }

    public void a(boolean z) {
        b("onWindowFocusChanged", Boolean.valueOf(z));
    }

    public static String a(Class<?> cls, Object obj) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Field field : cls.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    String name = field.getName();
                    if (a(field.getType())) {
                        jSONObject.put(name, field.get(obj));
                    }
                } catch (Throwable e) {
                    l.a().e("" + null);
                    l.a().e(e);
                }
            }
            return jSONObject.toString();
        } catch (Throwable e2) {
            l.a().e(e2);
            return "";
        }
    }

    private static void a(Class<?> cls, Object obj, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            for (Field field : cls.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    String name = field.getName();
                    Class type = field.getType();
                    if (!jSONObject.isNull(name) && a(type)) {
                        field.set(obj, jSONObject.get(name));
                    }
                } catch (Throwable e) {
                    l.a().e("" + null);
                    l.a().e(e);
                }
            }
        } catch (Throwable e2) {
            l.a().e(e2);
        }
    }

    private static boolean a(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        if (cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Integer.TYPE) || cls.equals(Boolean.class) || cls.equals(Boolean.TYPE) || cls.equals(Double.class) || cls.equals(Double.TYPE) || cls.equals(Long.class) || cls.equals(Long.TYPE) || cls.equals(JSONArray.class) || cls.equals(JSONObject.class)) {
            return true;
        }
        return false;
    }
}
