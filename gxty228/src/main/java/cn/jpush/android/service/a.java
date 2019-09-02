package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.d.e;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.data.d;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.altbeacon.beacon.BeaconManager;

public final class a {
    private static volatile a a = null;
    private static ExecutorService b = Executors.newSingleThreadExecutor();
    private static final Object f = new Object();
    private Handler c = null;
    private Context d = null;
    private String e = "";

    private a(Context context) {
        e.c("JPushLocalNotificationCenter", "Constructor : JPushLocalNotificationCenter");
        this.c = new Handler(Looper.getMainLooper());
        this.d = context;
        this.e = this.d.getPackageName();
    }

    public static a a(Context context) {
        e.c("JPushLocalNotificationCenter", "getInstance");
        if (a == null) {
            synchronized (f) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public final synchronized void b(Context context) {
        e.d("JPushLocalNotificationCenter", "clear all local notification ");
        int[] a = d.a(context).a();
        if (a != null && a.length > 0) {
            e.c("JPushLocalNotificationCenter", " success");
            for (int clearNotificationById : a) {
                JPushInterface.clearNotificationById(this.d, clearNotificationById);
            }
        }
    }

    public final synchronized boolean a(Context context, long j) {
        boolean z = false;
        synchronized (this) {
            e.d("JPushLocalNotificationCenter", "remove LocalNotification ");
            try {
                d a = d.a(context);
                cn.jpush.android.data.e a2 = a.a(j, 0);
                if (a2 != null) {
                    e.c("JPushLocalNotificationCenter", "remove local count : " + a2.b());
                    a.a(j);
                    JPushInterface.clearNotificationById(this.d, (int) j);
                    z = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.i("JPushLocalNotificationCenter", "exception:" + e.getMessage());
            }
        }
        return z;
    }

    public final synchronized boolean a(Context context, JPushLocalNotification jPushLocalNotification, boolean z) {
        if (z) {
            a(context, jPushLocalNotification);
        } else {
            Bundle bundle = new Bundle();
            c.a(context, bundle, "intent.MULTI_PROCESS");
            bundle.putInt("multi_type", 6);
            bundle.putSerializable("local_notification", jPushLocalNotification);
            JCoreInterface.sendAction(context, cn.jpush.android.a.a, bundle);
        }
        return true;
    }

    private synchronized boolean a(Context context, JPushLocalNotification jPushLocalNotification) {
        boolean z;
        e.d("JPushLocalNotificationCenter", "add LocalNotification");
        long currentTimeMillis = System.currentTimeMillis();
        long broadcastTime = jPushLocalNotification.getBroadcastTime() - currentTimeMillis;
        if (ServiceInterface.d(context)) {
            e.c("JPushLocalNotificationCenter", "push has stopped");
        }
        long notificationId = jPushLocalNotification.getNotificationId();
        try {
            d a = d.a(context);
            if (a.a(notificationId, 0) != null) {
                a.b(notificationId, 1, 0, 0, jPushLocalNotification.toJSON(), jPushLocalNotification.getBroadcastTime(), currentTimeMillis);
            } else {
                a.a(notificationId, 1, 0, 0, jPushLocalNotification.toJSON(), jPushLocalNotification.getBroadcastTime(), currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.i("JPushLocalNotificationCenter", "exception:" + e.getMessage());
        }
        if (broadcastTime < BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD) {
            a(jPushLocalNotification.getNotificationId(), broadcastTime);
            z = true;
        } else {
            z = true;
        }
        return z;
    }

    public final synchronized void c(Context context) {
        Cursor cursor;
        e.c("JPushLocalNotificationCenter", "init LocalNotification");
        d a = d.a(context);
        if (a.a(false)) {
            cursor = null;
            try {
                long currentTimeMillis = System.currentTimeMillis();
                cursor = a.a(currentTimeMillis, (long) BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD);
                if (cursor.moveToFirst()) {
                    do {
                        cn.jpush.android.data.e a2 = d.a(cursor);
                        if (a2 != null) {
                            a(a2.a(), a2.f() - currentTimeMillis);
                        }
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                a.b(false);
            } catch (Exception e) {
                e.i("JPushLocalNotificationCenter", "init LocalNotification cast expt:" + e);
                if (cursor != null) {
                    cursor.close();
                }
                a.b(false);
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                a.b(false);
            }
        } else {
            e.i("JPushLocalNotificationCenter", "init LocalNotification cast expt: db open failed");
        }
    }

    public final void d(final Context context) {
        e.c("JPushLocalNotificationCenter", "LocalNotification onHeartBeat");
        b.execute(new Runnable(this) {
            final /* synthetic */ a b;

            public final void run() {
                this.b.e(context);
                this.b.c(context);
            }
        });
    }

    private synchronized void e(Context context) {
        Exception e;
        Throwable th;
        e.c("JPushLocalNotificationCenter", "triggerLNKillProcess");
        d a = d.a(context);
        if (a.a(false)) {
            Cursor cursor = null;
            Cursor a2;
            try {
                a2 = a.a(1, System.currentTimeMillis());
                try {
                    if (a2.moveToFirst()) {
                        do {
                            cn.jpush.android.data.e a3 = d.a(a2);
                            if (a3 != null) {
                                a(context, a3.d(), this.e, "");
                                a.b(a3.a(), 0, 0, 0, a3.d(), a3.f(), a3.e());
                            }
                        } while (a2.moveToNext());
                    }
                    if (a2 != null) {
                        a2.close();
                    }
                    a.b(false);
                } catch (Exception e2) {
                    e = e2;
                    cursor = a2;
                    try {
                        e.h("JPushLocalNotificationCenter", "triggerLNKillProcess: " + e.getMessage());
                        if (cursor != null) {
                            cursor.close();
                        }
                        a.b(false);
                    } catch (Throwable th2) {
                        th = th2;
                        a2 = cursor;
                        if (a2 != null) {
                            a2.close();
                        }
                        a.b(false);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (a2 != null) {
                        a2.close();
                    }
                    a.b(false);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                e.h("JPushLocalNotificationCenter", "triggerLNKillProcess: " + e.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                a.b(false);
            } catch (Throwable th4) {
                th = th4;
                a2 = null;
                if (a2 != null) {
                    a2.close();
                }
                a.b(false);
                throw th;
            }
        }
        e.i("JPushLocalNotificationCenter", "init LocalNotification cast expt: db open failed");
    }

    private synchronized void a(final long j, long j2) {
        e.c("JPushLocalNotificationCenter", "LocalNotification scheduleReadiedLN");
        if (this.c != null) {
            Runnable anonymousClass2 = new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    try {
                        d a = d.a(this.b.d);
                        cn.jpush.android.data.e a2 = a.a(j, 0);
                        if (a2 == null) {
                            return;
                        }
                        if (a2.c() == 1) {
                            e.c("JPushLocalNotificationCenter", "remove ");
                            a.b(a2.a(), 0, 1, 0, a2.d(), a2.f(), a2.e());
                        } else if (a2.b() > 1) {
                            e.c("JPushLocalNotificationCenter", "repeat add");
                            a.b(a2.a(), a2.b() - 1, 0, 0, a2.d(), a2.f(), a2.e());
                        } else if (a2.b() == 1) {
                            e.c("JPushLocalNotificationCenter", "send broadcast");
                            if (a2.f() > System.currentTimeMillis()) {
                                e.c("JPushLocalNotificationCenter", "repeat add");
                                return;
                            }
                            this.b.a(this.b.d, a2.d(), this.b.e, "");
                            a.b(a2.a(), 0, 0, 0, a2.d(), a2.f(), a2.e());
                        } else {
                            e.c("JPushLocalNotificationCenter", "already triggered");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.i("JPushLocalNotificationCenter", "exception:" + e.getMessage());
                    }
                }
            };
            if (j2 <= 0) {
                e.c("JPushLocalNotificationCenter", "post right now ");
                this.c.post(anonymousClass2);
            } else {
                e.c("JPushLocalNotificationCenter", "post delayed : " + j2);
                this.c.postDelayed(anonymousClass2, j2);
            }
        }
    }

    private void a(Context context, String str, String str2, String str3) {
        e.c("JPushLocalNotificationCenter", "start LocalNotification broadCastToPushReceiver");
        Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
        intent.putExtra("senderId", str3);
        intent.putExtra("appId", str2);
        intent.putExtra("message", str);
        intent.putExtra("notificaion_type", 1);
        intent.addCategory(str2);
        context.sendOrderedBroadcast(intent, str2 + ".permission.JPUSH_MESSAGE");
        e.c("JPushLocalNotificationCenter", "end LocalNotification broadCastToPushReceiver");
    }
}
