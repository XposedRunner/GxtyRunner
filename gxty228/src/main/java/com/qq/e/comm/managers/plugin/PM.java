package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.constants.Constants.PLUGIN;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PM {
    private static final Map<Class<?>, String> l = new HashMap<Class<?>, String>() {
        {
            put(POFactory.class, "com.qq.e.comm.plugin.POFactoryImpl");
        }
    };
    private ExecutorService a = Executors.newSingleThreadExecutor();
    private final Context b;
    private String c;
    private File d;
    private int e;
    private DexClassLoader f;
    private RandomAccessFile g;
    private FileLock h;
    private boolean i;
    private a j;
    private a k = new a(this) {
        private /* synthetic */ PM a;

        {
            this.a = r1;
        }

        static File a(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.jar");
        }

        static File b(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.next");
        }

        static File c(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.jar.sig");
        }

        static File d(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.next.sig");
        }

        public final void a() {
            PM.a(this.a);
        }

        public final void b() {
            this.a.e();
        }
    };

    public interface a {

        public interface a {
            void a();

            void b();
        }

        void onLoadFail();

        void onLoadSuccess();
    }

    public PM(Context context, a aVar) {
        this.b = context.getApplicationContext();
        this.j = aVar;
        this.i = d();
        if (b()) {
            a();
        }
    }

    private void a() {
        GDTLogger.d("PluginFile:\t" + (this.d == null ? "null" : this.d.getAbsolutePath()));
        if (this.c == null || this.d == null) {
            this.f = null;
            return;
        }
        try {
            this.f = new DexClassLoader(this.d.getAbsolutePath(), this.b.getDir("e_qq_com_dex", 0).getAbsolutePath(), null, getClass().getClassLoader());
            if (this.j != null) {
                this.j.onLoadSuccess();
            }
        } catch (Throwable th) {
            GDTLogger.e("exception while init plugin class loader", th);
            e();
        }
    }

    static /* synthetic */ void a(PM pm) {
        try {
            if (pm.f == null && pm.c()) {
                pm.a();
            }
        } catch (Throwable e) {
            GDTLogger.report("Exception while init online plugin: ", e);
            pm.e();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b() {
        /*
        r6 = this;
        r1 = 1;
        r0 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x006a }
        r3 = "TimeStap_BEFORE_PLUGIN_INIT:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x006a }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x006a }
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x006a }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x006a }
        com.qq.e.comm.util.GDTLogger.d(r2);	 Catch:{ Throwable -> 0x006a }
        r2 = r6.c();	 Catch:{ Throwable -> 0x006a }
        if (r2 != 0) goto L_0x0025;
    L_0x001e:
        r2 = r6.i;	 Catch:{ Throwable -> 0x006a }
        if (r2 != 0) goto L_0x003d;
    L_0x0022:
        r2 = r0;
    L_0x0023:
        if (r2 == 0) goto L_0x0026;
    L_0x0025:
        r0 = r1;
    L_0x0026:
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
    L_0x003c:
        return r0;
    L_0x003d:
        r2 = r6.b;	 Catch:{ Throwable -> 0x006a }
        r3 = r6.b;	 Catch:{ Throwable -> 0x006a }
        r3 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.a(r3);	 Catch:{ Throwable -> 0x006a }
        r4 = r6.b;	 Catch:{ Throwable -> 0x006a }
        r4 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.c(r4);	 Catch:{ Throwable -> 0x006a }
        r2 = com.qq.e.comm.a.a(r2, r3, r4);	 Catch:{ Throwable -> 0x006a }
        if (r2 == 0) goto L_0x0063;
    L_0x0051:
        r2 = "DicyqNOhDaXKemy4iVehs9e2dZlHrJmDsVOvtIzkSQnirHCfYHOpWOstTGwz2jlBmI8BwqQQLPnOCvXc9sm3QWr8i6cKubAyzuA0JiMiTHaKKnyqqmZ4Rk0xtkUDSwwezrb1t9TPhJvqtMJsB+zt2lgP/QZXcCuKBnB6GpsCECE=";
        r6.c = r2;	 Catch:{ Throwable -> 0x006a }
        r2 = r6.b;	 Catch:{ Throwable -> 0x006a }
        r2 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.a(r2);	 Catch:{ Throwable -> 0x006a }
        r6.d = r2;	 Catch:{ Throwable -> 0x006a }
        r2 = 574; // 0x23e float:8.04E-43 double:2.836E-321;
        r6.e = r2;	 Catch:{ Throwable -> 0x006a }
        r2 = r1;
        goto L_0x0023;
    L_0x0063:
        r2 = "Fail to prepair Defult plugin ";
        com.qq.e.comm.util.GDTLogger.e(r2);	 Catch:{ Throwable -> 0x006a }
        r2 = r0;
        goto L_0x0023;
    L_0x006a:
        r1 = move-exception;
        r2 = "Exception while init plugin manager";
        com.qq.e.comm.util.GDTLogger.report(r2, r1);	 Catch:{ all -> 0x0087 }
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
        goto L_0x003c;
    L_0x0087:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.plugin.PM.b():boolean");
    }

    private boolean c() {
        c cVar;
        if (this.i) {
            cVar = new c(AnonymousClass1.b(this.b), AnonymousClass1.d(this.b));
            if (cVar.a()) {
                GDTLogger.d("NextExist,Updated=" + cVar.a(AnonymousClass1.a(this.b), AnonymousClass1.c(this.b)));
            }
        }
        cVar = new c(AnonymousClass1.a(this.b), AnonymousClass1.c(this.b));
        if (!cVar.a()) {
            return false;
        }
        if (cVar.b() < PLUGIN.ASSET_PLUGIN_VERSION) {
            GDTLogger.d("last updated plugin version =" + this.e + ";asset plugin version=574");
            return false;
        }
        this.c = cVar.c();
        this.e = cVar.b();
        this.d = AnonymousClass1.a(this.b);
        return true;
    }

    private boolean d() {
        try {
            File file = new File(this.b.getDir("e_qq_com_plugin", 0), "update_lc");
            if (!file.exists()) {
                file.createNewFile();
                StringUtil.writeTo("lock", file);
            }
            if (!file.exists()) {
                return false;
            }
            this.g = new RandomAccessFile(file, "rw");
            this.h = this.g.getChannel().tryLock();
            if (this.h == null) {
                return false;
            }
            this.g.writeByte(37);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private void e() {
        if (this.j != null) {
            this.j.onLoadFail();
        }
    }

    public <T> T getFactory(Class<T> cls) throws b {
        boolean z = false;
        GDTLogger.d("GetFactoryInstaceforInterface:" + cls);
        ClassLoader classLoader = this.f;
        StringBuilder stringBuilder = new StringBuilder("PluginClassLoader is parent");
        if (getClass().getClassLoader() == classLoader) {
            z = true;
        }
        GDTLogger.d(stringBuilder.append(z).toString());
        if (classLoader == null) {
            throw new b("Fail to init GDTADPLugin,PluginClassLoader == null;while loading factory impl for:" + cls);
        }
        try {
            String str = (String) l.get(cls);
            if (StringUtil.isEmpty(str)) {
                throw new b("factory  implemention name is not specified for interface:" + cls.getName());
            }
            Class loadClass = classLoader.loadClass(str);
            T cast = cls.cast(loadClass.getDeclaredMethod("getInstance", new Class[0]).invoke(loadClass, new Object[0]));
            GDTLogger.d("ServiceDelegateFactory =" + cast);
            return cast;
        } catch (Throwable th) {
            b bVar = new b("Fail to getfactory implement instance for interface:" + cls.getName(), th);
        }
    }

    public String getLocalSig() {
        return this.c;
    }

    public POFactory getPOFactory() throws b {
        return (POFactory) getFactory(POFactory.class);
    }

    public int getPluginVersion() {
        return this.e;
    }

    public void update(String str, String str2) {
        if (this.i) {
            a aVar = new a(this.b, this.a);
            aVar.a(this.k);
            aVar.a(str, str2);
        }
    }
}
