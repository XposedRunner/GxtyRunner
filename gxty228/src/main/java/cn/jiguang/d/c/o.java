package cn.jiguang.d.c;

import com.lzy.okgo.cookie.SerializableCookie;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class o {
    private static o d;
    private String[] a = null;
    private j[] b = null;
    private int c = -1;

    static {
        o oVar = new o();
        synchronized (o.class) {
            d = oVar;
        }
    }

    public o() {
        if (!c() && !d()) {
            if (this.a == null || this.b == null) {
                String property = System.getProperty("os.name");
                String property2 = System.getProperty("java.vendor");
                if (property.indexOf("Windows") != -1) {
                    if (property.indexOf("95") == -1 && property.indexOf("98") == -1 && property.indexOf("ME") == -1) {
                        try {
                            Process exec = Runtime.getRuntime().exec("ipconfig /all");
                            a(exec.getInputStream());
                            exec.destroy();
                            return;
                        } catch (Exception e) {
                            return;
                        }
                    }
                    property = "winipcfg.out";
                    try {
                        Runtime.getRuntime().exec("winipcfg /all /batch " + property).waitFor();
                        a(new FileInputStream(new File(property)));
                        new File(property).delete();
                    } catch (Exception e2) {
                    }
                } else if (property.indexOf("NetWare") != -1) {
                    b("sys:/etc/resolv.cfg");
                } else if (property2.indexOf("Android") != -1) {
                    e();
                } else {
                    b("/etc/resolv.conf");
                }
            }
        }
    }

    private static int a(String str) {
        try {
            int parseInt = Integer.parseInt(str.substring(6));
            if (parseInt >= 0) {
                return parseInt;
            }
        } catch (NumberFormatException e) {
        }
        return -1;
    }

    private void a(InputStream inputStream) {
        int intValue = Integer.getInteger("org.xbill.DNS.windows.parse.buffer", 8192).intValue();
        InputStream bufferedInputStream = new BufferedInputStream(inputStream, intValue);
        bufferedInputStream.mark(intValue);
        a(bufferedInputStream, null);
        if (this.a == null) {
            try {
                bufferedInputStream.reset();
                a(bufferedInputStream, new Locale("", ""));
            } catch (IOException e) {
            }
        }
    }

    private void a(InputStream inputStream, Locale locale) {
        String str = o.class.getPackage().getName() + ".windows.DNSServer";
        ResourceBundle bundle = locale != null ? ResourceBundle.getBundle(str, locale) : ResourceBundle.getBundle(str);
        String string = bundle.getString("host_name");
        String string2 = bundle.getString("primary_dns_suffix");
        String string3 = bundle.getString("dns_suffix");
        String string4 = bundle.getString("dns_servers");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        Object obj = null;
        Object obj2 = null;
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                if (stringTokenizer.hasMoreTokens()) {
                    try {
                        Object obj3;
                        String nextToken = stringTokenizer.nextToken();
                        if (readLine.indexOf(":") != -1) {
                            obj2 = null;
                            obj = null;
                        } else {
                            obj3 = obj2;
                            obj2 = obj;
                            obj = obj3;
                        }
                        if (readLine.indexOf(string) != -1) {
                            while (stringTokenizer.hasMoreTokens()) {
                                nextToken = stringTokenizer.nextToken();
                            }
                            try {
                                if (j.a(nextToken, null).b() != 1) {
                                    b(nextToken, arrayList2);
                                    obj3 = obj;
                                    obj = obj2;
                                    obj2 = obj3;
                                }
                            } catch (s e) {
                                obj3 = obj;
                                obj = obj2;
                                obj2 = obj3;
                            }
                        } else if (readLine.indexOf(string2) != -1) {
                            while (stringTokenizer.hasMoreTokens()) {
                                nextToken = stringTokenizer.nextToken();
                            }
                            if (!nextToken.equals(":")) {
                                b(nextToken, arrayList2);
                                obj = obj2;
                                obj2 = 1;
                            }
                        } else if (obj == null && readLine.indexOf(string3) == -1) {
                            if (!(obj2 == null && readLine.indexOf(string4) == -1)) {
                                while (stringTokenizer.hasMoreTokens()) {
                                    nextToken = stringTokenizer.nextToken();
                                }
                                if (!nextToken.equals(":")) {
                                    a(nextToken, arrayList);
                                    obj2 = 1;
                                }
                            }
                            obj3 = obj;
                            obj = obj2;
                            obj2 = obj3;
                        } else {
                            while (stringTokenizer.hasMoreTokens()) {
                                nextToken = stringTokenizer.nextToken();
                            }
                            if (!nextToken.equals(":")) {
                                b(nextToken, arrayList2);
                                obj = obj2;
                                obj2 = 1;
                            }
                        }
                        obj3 = obj;
                        obj = obj2;
                        obj2 = obj3;
                    } catch (IOException e2) {
                        return;
                    }
                }
                obj = null;
                obj2 = null;
            } else {
                a(arrayList, arrayList2);
                return;
            }
        }
    }

    private static void a(String str, List list) {
        if (!list.contains(str)) {
            list.add(str);
        }
    }

    private void a(List list, List list2) {
        if (this.a == null && list.size() > 0) {
            this.a = (String[]) list.toArray(new String[0]);
        }
        if (this.b == null && list2.size() > 0) {
            this.b = (j[]) list2.toArray(new j[0]);
        }
    }

    public static synchronized o b() {
        o oVar;
        synchronized (o.class) {
            oVar = d;
        }
        return oVar;
    }

    private void b(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
            List arrayList = new ArrayList(0);
            List arrayList2 = new ArrayList(0);
            int i = -1;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.startsWith("nameserver")) {
                    r5 = new StringTokenizer(readLine);
                    r5.nextToken();
                    a(r5.nextToken(), arrayList);
                } else {
                    try {
                        if (readLine.startsWith(SerializableCookie.DOMAIN)) {
                            r5 = new StringTokenizer(readLine);
                            r5.nextToken();
                            if (r5.hasMoreTokens() && arrayList2.isEmpty()) {
                                b(r5.nextToken(), arrayList2);
                            }
                        } else if (readLine.startsWith("search")) {
                            if (!arrayList2.isEmpty()) {
                                arrayList2.clear();
                            }
                            r5 = new StringTokenizer(readLine);
                            r5.nextToken();
                            while (r5.hasMoreTokens()) {
                                b(r5.nextToken(), arrayList2);
                            }
                        } else if (readLine.startsWith("options")) {
                            r5 = new StringTokenizer(readLine);
                            r5.nextToken();
                            while (r5.hasMoreTokens()) {
                                readLine = r5.nextToken();
                                if (readLine.startsWith("ndots:")) {
                                    i = a(readLine);
                                }
                            }
                        }
                    } catch (IOException e) {
                    }
                }
            }
            bufferedReader.close();
            a(arrayList, arrayList2);
            if (this.c < 0 && i > 0) {
                this.c = i;
            }
        } catch (FileNotFoundException e2) {
        }
    }

    private static void b(String str, List list) {
        try {
            j a = j.a(str, j.a);
            if (!list.contains(a)) {
                list.add(a);
            }
        } catch (s e) {
        }
    }

    private boolean c() {
        StringTokenizer stringTokenizer;
        List arrayList = new ArrayList(0);
        List arrayList2 = new ArrayList(0);
        String property = System.getProperty("dns.server");
        if (property != null) {
            stringTokenizer = new StringTokenizer(property, ",");
            while (stringTokenizer.hasMoreTokens()) {
                a(stringTokenizer.nextToken(), arrayList);
            }
        }
        property = System.getProperty("dns.search");
        if (property != null) {
            stringTokenizer = new StringTokenizer(property, ",");
            while (stringTokenizer.hasMoreTokens()) {
                b(stringTokenizer.nextToken(), arrayList2);
            }
        }
        a(arrayList, arrayList2);
        return (this.a == null || this.b == null) ? false : true;
    }

    private boolean d() {
        List arrayList = new ArrayList(0);
        List arrayList2 = new ArrayList(0);
        try {
            Class[] clsArr = new Class[0];
            Object[] objArr = new Object[0];
            Class cls = Class.forName("sun.net.dns.ResolverConfiguration");
            Object invoke = cls.getDeclaredMethod("open", clsArr).invoke(null, objArr);
            List<String> list = (List) cls.getMethod("nameservers", clsArr).invoke(invoke, objArr);
            List<String> list2 = (List) cls.getMethod("searchlist", clsArr).invoke(invoke, objArr);
            if (list.size() == 0) {
                return false;
            }
            if (list.size() > 0) {
                for (String a : list) {
                    a(a, arrayList);
                }
            }
            if (list2.size() > 0) {
                for (String a2 : list2) {
                    b(a2, arrayList2);
                }
            }
            a(arrayList, arrayList2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void e() {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
            String[] strArr = new String[]{"net.dns1", "net.dns2", "net.dns3", "net.dns4"};
            for (int i = 0; i < 4; i++) {
                String str = (String) method.invoke(null, new Object[]{strArr[i]});
                if (str != null && ((str.matches("^\\d+(\\.\\d+){3}$") || str.matches("^[0-9a-f]+(:[0-9a-f]*)+:[0-9a-f]+$")) && !arrayList.contains(str))) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
        }
        a(arrayList, arrayList2);
    }

    public final String[] a() {
        return this.a;
    }
}
