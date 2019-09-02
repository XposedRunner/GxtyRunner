package com.sina.weibo.sdk.statistic;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import com.sina.weibo.sdk.utils.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

class WBAgentHandler {
    private static int MAX_CACHE_SIZE = 5;
    private static List<PageLog> mActivePages;
    private static WBAgentHandler mInstance;
    private static Map<String, PageLog> mPages;
    private static Timer mTimer;

    public static synchronized WBAgentHandler getInstance() {
        WBAgentHandler wBAgentHandler;
        synchronized (WBAgentHandler.class) {
            if (mInstance == null) {
                mInstance = new WBAgentHandler();
            }
            wBAgentHandler = mInstance;
        }
        return wBAgentHandler;
    }

    private WBAgentHandler() {
        mActivePages = new ArrayList();
        mPages = new HashMap();
        LogUtil.i(WBAgent.TAG, "init handler");
    }

    public void onPageStart(String str) {
        if (!StatisticConfig.ACTIVITY_DURATION_OPEN) {
            PageLog pageLog = new PageLog(str);
            pageLog.setType(LogType.FRAGMENT);
            synchronized (mPages) {
                mPages.put(str, pageLog);
            }
            LogUtil.d(WBAgent.TAG, new StringBuilder(String.valueOf(str)).append(", ").append(pageLog.getStartTime() / 1000).toString());
        }
    }

    public void onPageEnd(String str) {
        if (!StatisticConfig.ACTIVITY_DURATION_OPEN) {
            if (mPages.containsKey(str)) {
                PageLog pageLog = (PageLog) mPages.get(str);
                pageLog.setDuration(System.currentTimeMillis() - pageLog.getStartTime());
                synchronized (mActivePages) {
                    mActivePages.add(pageLog);
                }
                synchronized (mPages) {
                    mPages.remove(str);
                }
                LogUtil.d(WBAgent.TAG, new StringBuilder(String.valueOf(str)).append(", ").append(pageLog.getStartTime() / 1000).append(", ").append(pageLog.getDuration() / 1000).toString());
            } else {
                LogUtil.e(WBAgent.TAG, "please call onPageStart before onPageEnd");
            }
            if (mActivePages.size() >= MAX_CACHE_SIZE) {
                saveActivePages(mActivePages);
                mActivePages.clear();
            }
        }
    }

    public void onResume(Context context) {
        if (LogReport.getPackageName() == null) {
            LogReport.setPackageName(context.getPackageName());
        }
        if (mTimer == null) {
            mTimer = timerTask(context, 500, StatisticConfig.getUploadInterval());
        }
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        checkNewSession(context, currentTimeMillis);
        if (StatisticConfig.ACTIVITY_DURATION_OPEN) {
            PageLog pageLog = new PageLog(name, currentTimeMillis);
            pageLog.setType(LogType.ACTIVITY);
            synchronized (mPages) {
                mPages.put(name, pageLog);
            }
        }
        LogUtil.d(WBAgent.TAG, new StringBuilder(String.valueOf(name)).append(", ").append(currentTimeMillis / 1000).toString());
    }

    public void onPause(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        LogUtil.i(WBAgent.TAG, "update last page endtime:" + (currentTimeMillis / 1000));
        PageLog.updateSession(context, null, Long.valueOf(0), Long.valueOf(currentTimeMillis));
        if (StatisticConfig.ACTIVITY_DURATION_OPEN) {
            if (mPages.containsKey(name)) {
                PageLog pageLog = (PageLog) mPages.get(name);
                pageLog.setDuration(currentTimeMillis - pageLog.getStartTime());
                synchronized (mActivePages) {
                    mActivePages.add(pageLog);
                }
                synchronized (mPages) {
                    mPages.remove(name);
                }
                LogUtil.d(WBAgent.TAG, new StringBuilder(String.valueOf(name)).append(", ").append(pageLog.getStartTime() / 1000).append(", ").append(pageLog.getDuration() / 1000).toString());
            } else {
                LogUtil.e(WBAgent.TAG, "please call onResume before onPause");
            }
            if (mActivePages.size() >= MAX_CACHE_SIZE) {
                saveActivePages(mActivePages);
                mActivePages.clear();
            }
        }
        checkAppStatus(context);
    }

    public void onEvent(String str, String str2, Map<String, String> map) {
        EventLog eventLog = new EventLog(str, str2, map);
        eventLog.setType(LogType.EVENT);
        synchronized (mActivePages) {
            mActivePages.add(eventLog);
        }
        if (map == null) {
            LogUtil.d(WBAgent.TAG, "event--- page:" + str + " ,event name:" + str2);
        } else {
            LogUtil.d(WBAgent.TAG, "event--- page:" + str + " ,event name:" + str2 + " ,extend:" + map.toString());
        }
        if (mActivePages.size() >= MAX_CACHE_SIZE) {
            saveActivePages(mActivePages);
            mActivePages.clear();
        }
    }

    public void uploadAppLogs(final Context context) {
        long currentTimeMillis = System.currentTimeMillis() - LogReport.getTime(context);
        if (LogReport.getTime(context) <= 0 || currentTimeMillis >= StatisticConfig.MIN_UPLOAD_INTERVAL) {
            WBAgentExecutor.execute(new Runnable() {
                public void run() {
                    LogReport.uploadAppLogs(context, WBAgentHandler.this.getLogsInMemory());
                }
            });
            return;
        }
        timerTask(context, StatisticConfig.MIN_UPLOAD_INTERVAL - currentTimeMillis, 0);
    }

    public void onStop(Context context) {
        checkAppStatus(context);
    }

    private void checkAppStatus(Context context) {
        if (isBackground(context)) {
            saveActivePages(mActivePages);
            mActivePages.clear();
        }
    }

    private boolean isBackground(Context context) {
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 400) {
                    LogUtil.i(WBAgent.TAG, "后台:" + runningAppProcessInfo.processName);
                    return true;
                }
                LogUtil.i(WBAgent.TAG, "前台:" + runningAppProcessInfo.processName);
                return false;
            }
        }
        return false;
    }

    public void onKillProcess() {
        LogUtil.i(WBAgent.TAG, "save applogs and close timer and shutdown thread executor");
        saveActivePages(mActivePages);
        mInstance = null;
        closeTimer();
        WBAgentExecutor.shutDownExecutor();
    }

    private void checkNewSession(Context context, long j) {
        if (PageLog.isNewSession(context, j)) {
            PageLog pageLog = new PageLog(context);
            pageLog.setType(LogType.SESSION_END);
            PageLog pageLog2 = new PageLog(context, j);
            pageLog2.setType(LogType.SESSION_START);
            synchronized (mActivePages) {
                if (pageLog.getEndTime() > 0) {
                    mActivePages.add(pageLog);
                } else {
                    LogUtil.d(WBAgent.TAG, "is a new install");
                }
                mActivePages.add(pageLog2);
            }
            LogUtil.d(WBAgent.TAG, "last session--- starttime:" + pageLog.getStartTime() + " ,endtime:" + pageLog.getEndTime());
            LogUtil.d(WBAgent.TAG, "is a new session--- starttime:" + pageLog2.getStartTime());
            return;
        }
        LogUtil.i(WBAgent.TAG, "is not a new session");
    }

    private synchronized void saveActivePages(List<PageLog> list) {
        final String pageLogs = LogBuilder.getPageLogs(list);
        WBAgentExecutor.execute(new Runnable() {
            public void run() {
                LogFileUtil.writeToFile(LogFileUtil.getAppLogPath(LogFileUtil.ANALYTICS_FILE_NAME), pageLogs, true);
            }
        });
    }

    private synchronized String getLogsInMemory() {
        String str;
        str = "";
        if (mActivePages.size() > 0) {
            str = LogBuilder.getPageLogs(mActivePages);
            mActivePages.clear();
        }
        return str;
    }

    private Timer timerTask(final Context context, long j, long j2) {
        Timer timer = new Timer();
        TimerTask anonymousClass3 = new TimerTask() {
            public void run() {
                LogReport.uploadAppLogs(context, WBAgentHandler.this.getLogsInMemory());
            }
        };
        if (j2 == 0) {
            timer.schedule(anonymousClass3, j);
        } else {
            timer.schedule(anonymousClass3, j, j2);
        }
        return timer;
    }

    private void closeTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
