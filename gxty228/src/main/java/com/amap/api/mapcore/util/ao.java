package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: OfflineMapDataVerify */
public class ao extends Thread {
    private Context a;
    private ba b;

    public ao(Context context) {
        this.a = context;
        this.b = ba.a(context);
    }

    public void run() {
        a();
    }

    private av a(File file) {
        String a = en.a(file);
        av avVar = new av();
        avVar.b(a);
        return avVar;
    }

    private void a() {
        ArrayList arrayList = new ArrayList();
        ArrayList a = this.b.a();
        a(arrayList, "vmap/");
        a(arrayList, "map/");
        b(arrayList, "map/");
        ArrayList b = b();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            av avVar = (av) it.next();
            if (!(avVar == null || avVar.d() == null)) {
                String c;
                int indexOf;
                if (avVar.l == 4 || avVar.l == 7) {
                    boolean contains = arrayList.contains(avVar.i());
                    if (!contains) {
                        c = bk.c(avVar.g());
                        if (c != null) {
                            indexOf = arrayList.indexOf(c);
                            if (indexOf != -1) {
                                arrayList.set(indexOf, avVar.i());
                                contains = true;
                            }
                        }
                    }
                    if (!contains) {
                        this.b.b(avVar);
                    }
                } else if (avVar.l == 0 || avVar.l == 1) {
                    r1 = (b.contains(avVar.f()) || b.contains(avVar.i())) ? 1 : null;
                    if (r1 == null) {
                        c = bk.c(avVar.g());
                        if (c != null) {
                            indexOf = b.indexOf(c);
                            if (indexOf != -1) {
                                b.set(indexOf, avVar.i());
                                r1 = 1;
                            }
                        }
                    }
                    if (r1 == null) {
                        this.b.b(avVar);
                    }
                } else if (avVar.l == 3 && avVar.h() != 0) {
                    r1 = (b.contains(avVar.f()) || b.contains(avVar.i())) ? 1 : null;
                    if (r1 == null) {
                        c = bk.c(avVar.g());
                        if (c != null) {
                            indexOf = b.indexOf(c);
                            if (indexOf != -1) {
                                b.set(indexOf, avVar.i());
                                r1 = 1;
                            }
                        }
                    }
                    if (r1 == null) {
                        this.b.b(avVar);
                    }
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            if (!a(str, a)) {
                avVar = a(str);
                if (avVar != null) {
                    this.b.a(avVar);
                }
            }
        }
        al a2 = al.a(this.a);
        if (a2 != null) {
            a2.a(null);
        }
    }

    private av a(String str) {
        av avVar = null;
        if (str.equals("quanguo")) {
            str = "quanguogaiyaotu";
        }
        al a = al.a(this.a);
        if (a != null) {
            CharSequence f = a.f(str);
            File[] listFiles = new File(en.c(this.a)).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    Object obj;
                    if ((file.getName().contains(f) || file.getName().contains(str)) && file.getName().endsWith(".zip.tmp.dt")) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        avVar = a(file);
                        if (!(avVar == null || avVar.d() == null)) {
                            break;
                        }
                    }
                }
            }
        }
        return avVar;
    }

    private boolean a(String str, ArrayList<av> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (str.equals(((av) it.next()).i())) {
                return true;
            }
        }
        return false;
    }

    private void a(ArrayList<String> arrayList, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(en.b(this.a));
        stringBuilder.append(str);
        File file = new File(stringBuilder.toString());
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.getName().endsWith(".dat")) {
                        String name = file2.getName();
                        int lastIndexOf = name.lastIndexOf(46);
                        if (lastIndexOf > -1 && lastIndexOf < name.length()) {
                            name = name.substring(0, lastIndexOf);
                            if (!arrayList.contains(name)) {
                                arrayList.add(name);
                            }
                        }
                    }
                }
            }
        }
    }

    private void b(ArrayList<String> arrayList, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(en.a(this.a));
        stringBuilder.append(str);
        File file = new File(stringBuilder.toString());
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        Object name = file2.getName();
                        if (!TextUtils.isEmpty(name)) {
                            String[] list = file2.list();
                            if (!(list == null || list.length < 1 || arrayList.contains(name))) {
                                Object obj;
                                int i;
                                if (name.equals("a0")) {
                                    for (Object equals : list) {
                                        if ("m1.ans".equals(equals)) {
                                            obj = 1;
                                            break;
                                        }
                                    }
                                    obj = null;
                                } else {
                                    Object obj2 = null;
                                    obj = null;
                                    for (Object obj3 : list) {
                                        if ("m1.ans".equals(obj3)) {
                                            obj = 1;
                                        }
                                        if ("m3.ans".equals(obj3)) {
                                            obj2 = 1;
                                        }
                                    }
                                    if (!(obj == null || r3 == null)) {
                                        i = 1;
                                    }
                                    obj = null;
                                }
                                if (obj != null) {
                                    arrayList.add(name);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList<String> b() {
        ArrayList<String> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(en.c(this.a));
        File file = new File(stringBuilder.toString());
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.getName().endsWith(".zip")) {
                        String name = file2.getName();
                        int lastIndexOf = name.lastIndexOf(46);
                        if (lastIndexOf > -1 && lastIndexOf < name.length()) {
                            arrayList.add(name.substring(0, lastIndexOf));
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
