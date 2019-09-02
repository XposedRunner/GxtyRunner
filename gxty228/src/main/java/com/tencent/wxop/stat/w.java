package com.tencent.wxop.stat;

import android.content.Context;

final class w implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ StatSpecifyReportedInfo c;

    w(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.a = str;
        this.b = context;
        this.c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            synchronized (StatServiceImpl.o) {
                if (StatServiceImpl.o.size() >= StatConfig.getMaxParallelTimmingEvents()) {
                    StatServiceImpl.q.error("The number of page events exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
                    return;
                }
                StatServiceImpl.m = this.a;
                if (StatServiceImpl.o.containsKey(StatServiceImpl.m)) {
                    StatServiceImpl.q.e("Duplicate PageID : " + StatServiceImpl.m + ", onResume() repeated?");
                    return;
                }
                StatServiceImpl.o.put(StatServiceImpl.m, Long.valueOf(System.currentTimeMillis()));
                StatServiceImpl.a(this.b, true, this.c);
            }
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.b, th);
        }
    }
}
