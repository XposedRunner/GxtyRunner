package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;
import android.view.ViewStub;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: PluginContext */
public class fg extends ContextThemeWrapper {
    private static final String[] d = new String[]{"android.widget", "android.webkit", "android.app"};
    private Resources a = fh.a();
    private LayoutInflater b;
    private ClassLoader c;
    private a e = new a(this);
    private Factory f = new Factory(this) {
        final /* synthetic */ fg a;

        {
            this.a = r1;
        }

        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.a.a(str, context, attributeSet);
        }
    };

    /* compiled from: PluginContext */
    public class a {
        public HashSet<String> a = new HashSet();
        public HashMap<String, Constructor<?>> b = new HashMap();
        final /* synthetic */ fg c;

        public a(fg fgVar) {
            this.c = fgVar;
        }
    }

    public fg(Context context, int i, ClassLoader classLoader) {
        super(context, i);
        this.c = classLoader;
    }

    public Resources getResources() {
        if (this.a != null) {
            return this.a;
        }
        return super.getResources();
    }

    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return super.getSystemService(str);
        }
        if (this.b == null) {
            LayoutInflater layoutInflater = (LayoutInflater) super.getSystemService(str);
            if (layoutInflater != null) {
                this.b = layoutInflater.cloneInContext(this);
            }
            this.b.setFactory(this.f);
            this.b = this.b.cloneInContext(this);
        }
        return this.b;
    }

    private final View a(String str, Context context, AttributeSet attributeSet) {
        Class loadClass;
        Class cls;
        Object obj;
        if (this.e.a.contains(str)) {
            return null;
        }
        View view;
        Constructor constructor = (Constructor) this.e.b.get(str);
        if (constructor == null) {
            try {
                int i;
                if (str.contains("api.navi")) {
                    loadClass = this.c.loadClass(str);
                } else {
                    String[] strArr = d;
                    int length = strArr.length;
                    i = 0;
                    while (i < length) {
                        try {
                            loadClass = this.c.loadClass(strArr[i] + "." + str);
                            break;
                        } catch (Throwable th) {
                            i++;
                        }
                    }
                    loadClass = null;
                }
                if (loadClass == null) {
                    cls = loadClass;
                    obj = null;
                } else {
                    try {
                        if (loadClass == ViewStub.class) {
                            cls = loadClass;
                            obj = null;
                        } else if (loadClass.getClassLoader() != this.c) {
                            cls = loadClass;
                            obj = null;
                        } else {
                            cls = loadClass;
                            i = 1;
                        }
                    } catch (Throwable th2) {
                        cls = loadClass;
                        obj = null;
                        if (obj == null) {
                            try {
                                constructor = cls.getConstructor(new Class[]{Context.class, AttributeSet.class});
                                this.e.b.put(str, constructor);
                            } catch (Throwable th3) {
                            }
                            if (constructor != null) {
                                view = null;
                            } else {
                                try {
                                    view = (View) constructor.newInstance(new Object[]{context, attributeSet});
                                } catch (Throwable th4) {
                                    return null;
                                }
                            }
                            return view;
                        }
                        this.e.a.add(str);
                        return null;
                    }
                }
            } catch (Throwable th5) {
                loadClass = null;
                cls = loadClass;
                obj = null;
                if (obj == null) {
                    this.e.a.add(str);
                    return null;
                }
                constructor = cls.getConstructor(new Class[]{Context.class, AttributeSet.class});
                this.e.b.put(str, constructor);
                if (constructor != null) {
                    view = (View) constructor.newInstance(new Object[]{context, attributeSet});
                } else {
                    view = null;
                }
                return view;
            }
            if (obj == null) {
                this.e.a.add(str);
                return null;
            }
            constructor = cls.getConstructor(new Class[]{Context.class, AttributeSet.class});
            this.e.b.put(str, constructor);
        }
        if (constructor != null) {
            view = (View) constructor.newInstance(new Object[]{context, attributeSet});
        } else {
            view = null;
        }
        return view;
    }
}
