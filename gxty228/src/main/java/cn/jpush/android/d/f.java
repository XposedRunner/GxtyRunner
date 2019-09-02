package cn.jpush.android.d;

import android.content.Context;
import cn.jpush.android.data.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class f {
    public static Queue<c> a = null;

    private static synchronized <T> void a(Context context, String str, ArrayList<T> arrayList) {
        synchronized (f.class) {
            e.a("MsgQueueUtils", "Action - saveObjects");
            if (context == null) {
                e.g("MsgQueueUtils", "unexcepted , context is null");
            } else if (arrayList == null) {
                e.g("MsgQueueUtils", "mObjectList is null");
            } else {
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput(str, 0));
                    objectOutputStream.writeObject(arrayList);
                    objectOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.h("MsgQueueUtils", "save Objects FileNotFoundException error:" + e.getMessage());
                } catch (IOException e2) {
                    e.h("MsgQueueUtils", "save Objects IOException error:" + e2.getMessage());
                }
            }
        }
    }

    private static synchronized void a(Context context, String str) {
        synchronized (f.class) {
            if (context == null) {
                e.g("MsgQueueUtils", "unexcepted , context is null");
            } else {
                File file = new File(context.getFilesDir(), str);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    private static synchronized <T> ArrayList<T> b(Context context, String str) {
        ObjectInputStream objectInputStream;
        Exception e;
        Throwable th;
        ArrayList<T> arrayList = null;
        synchronized (f.class) {
            if (context == null) {
                e.g("MsgQueueUtils", "unexcepted , context is null");
            } else {
                ArrayList<T> arrayList2 = new ArrayList();
                try {
                    objectInputStream = new ObjectInputStream(context.openFileInput(str));
                    try {
                        arrayList = (ArrayList) objectInputStream.readObject();
                        try {
                            objectInputStream.close();
                        } catch (IOException e2) {
                            e.g("MsgQueueUtils", "InputStream close failed:" + e2.getMessage());
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            e.g("MsgQueueUtils", "load objects error:" + e.getMessage());
                            a(context, str);
                            if (objectInputStream == null) {
                                try {
                                    objectInputStream.close();
                                    arrayList = arrayList2;
                                } catch (IOException e4) {
                                    e.g("MsgQueueUtils", "InputStream close failed:" + e4.getMessage());
                                    arrayList = arrayList2;
                                }
                            } else {
                                arrayList = arrayList2;
                            }
                            return arrayList;
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e22) {
                                    e.g("MsgQueueUtils", "InputStream close failed:" + e22.getMessage());
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e5) {
                    Exception exception = e5;
                    objectInputStream = null;
                    e = exception;
                    e.g("MsgQueueUtils", "load objects error:" + e.getMessage());
                    a(context, str);
                    if (objectInputStream == null) {
                        arrayList = arrayList2;
                    } else {
                        objectInputStream.close();
                        arrayList = arrayList2;
                    }
                    return arrayList;
                } catch (Throwable th3) {
                    objectInputStream = null;
                    th = th3;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return arrayList;
    }

    public static boolean a(Context context, c cVar) {
        ArrayList b;
        if (a == null) {
            a = new ConcurrentLinkedQueue();
            try {
                b = b(context, "msg_queue");
                if (!(b == null || b.isEmpty())) {
                    Iterator it = b.iterator();
                    while (it.hasNext()) {
                        a.offer((c) it.next());
                    }
                }
            } catch (Exception e) {
                e.h("MsgQueueUtils", "init lastMsgQueue failed:" + e.getMessage());
            }
        }
        if (context == null) {
            e.h("MsgQueueUtils", "#unexcepted - context was null");
            return false;
        }
        if (cVar == null) {
            e.h("MsgQueueUtils", "#unexcepted - entityKey was null");
        }
        if (a.contains(cVar)) {
            e.i("MsgQueueUtils", "Duplicated msg. Give up processing - " + cVar);
            return true;
        }
        if (a.size() >= 200) {
            a.poll();
        }
        a.offer(cVar);
        try {
            b = b(context, "msg_queue");
            if (b == null) {
                b = new ArrayList();
            }
            if (b.size() >= 50) {
                b.remove(0);
            }
            b.add(cVar);
            a(context, "msg_queue", b);
        } catch (Exception e2) {
            e.h("MsgQueueUtils", "msg save in sp failed:" + e2.getMessage());
        }
        return false;
    }
}
