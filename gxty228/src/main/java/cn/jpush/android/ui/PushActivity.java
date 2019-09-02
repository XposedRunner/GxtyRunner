package cn.jpush.android.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jpush.android.a.d;
import cn.jpush.android.c.h;
import cn.jpush.android.d.e;
import cn.jpush.android.data.b;
import cn.jpush.android.data.g;
import java.io.File;
import java.lang.ref.WeakReference;

public class PushActivity extends Activity {
    private int a = 0;
    private boolean b = false;
    private String c;
    private FullScreenView d = null;
    private Handler e;

    private static class a extends Handler {
        private final WeakReference<PushActivity> a;

        public a(PushActivity pushActivity) {
            this.a = new WeakReference(pushActivity);
        }

        public final void handleMessage(Message message) {
            b bVar = (b) message.obj;
            PushActivity pushActivity = (PushActivity) this.a.get();
            if (pushActivity != null) {
                switch (message.what) {
                    case 1:
                        pushActivity.setRequestedOrientation(1);
                        PushActivity.a(pushActivity, bVar);
                        return;
                    case 2:
                        pushActivity.b();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = new a(this);
        c();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        c();
    }

    private void c() {
        if (getIntent() != null) {
            try {
                this.b = getIntent().getBooleanExtra("from_way", false);
                Intent intent = getIntent();
                b bVar = (b) intent.getSerializableExtra("body");
                if (bVar == null) {
                    e.d("PushActivity", "parse entity form plugin plateform");
                    String str = null;
                    if (intent.getData() != null) {
                        str = intent.getData().toString();
                    }
                    if (TextUtils.isEmpty(str) && intent.getExtras() != null) {
                        str = intent.getExtras().getString("JMessageExtra");
                    }
                    bVar = h.a(this, str, "");
                }
                if (bVar != null) {
                    this.c = bVar.c;
                    if (bVar != null) {
                        switch (bVar.q) {
                            case 0:
                                Message message = new Message();
                                message.what = 1;
                                message.obj = bVar;
                                this.e.sendMessageDelayed(message, 500);
                                return;
                            default:
                                e.g("PushActivity", "Invalid msg type to show - " + bVar.q);
                                cn.jpush.android.api.b.a((Context) this, bVar, 0);
                                finish();
                                return;
                        }
                    }
                    e.h("PushActivity", "Null message entity! Close PushActivity!");
                    finish();
                    return;
                }
                e.h("PushActivity", "Warning，null message entity! Close PushActivity!");
                finish();
                return;
            } catch (Exception e) {
                e.j("PushActivity", "Extra data is not serializable!");
                e.printStackTrace();
                finish();
                return;
            }
        }
        e.h("PushActivity", "PushActivity get NULL intent!");
        finish();
    }

    public final void a() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ PushActivity a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.d != null) {
                    this.a.d.showTitleBar();
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (this.d != null) {
            this.d.resume();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.d != null) {
            this.d.pause();
        }
    }

    protected void onDestroy() {
        if (this.d != null) {
            this.d.destory();
        }
        if (this.e.hasMessages(2)) {
            this.e.removeMessages(2);
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (this.d == null || !this.d.webviewCanGoBack()) {
            d.a(this.c, PointerIconCompat.TYPE_CELL, null, this);
            b();
            return;
        }
        this.d.webviewGoBack();
    }

    public final void b() {
        finish();
        if (1 == this.a) {
            try {
                ActivityManager activityManager = (ActivityManager) getSystemService("activity");
                ComponentName componentName = ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).baseActivity;
                ComponentName componentName2 = ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity;
                e.c("PushActivity", "baseActivity  = " + componentName.toString());
                e.c("PushActivity", "topActivity  = " + componentName2.toString());
                if (componentName != null && componentName2 != null && componentName2.toString().equals(componentName.toString())) {
                    cn.jpush.android.d.a.b(this);
                }
            } catch (Exception e) {
                e.h("PushActivity", "Get running tasks failed.");
                cn.jpush.android.d.a.b(this);
            }
        }
    }

    static /* synthetic */ void a(PushActivity pushActivity, b bVar) {
        e.e("PushActivity", "Action: processShow");
        if (bVar == null) {
            e.h("PushActivity", "Null message entity! Close PushActivity!");
            pushActivity.finish();
            return;
        }
        g gVar = (g) bVar;
        if (gVar.M == 0) {
            pushActivity.a = gVar.K;
            int identifier = pushActivity.getResources().getIdentifier("jpush_webview_layout", "layout", pushActivity.getPackageName());
            if (identifier == 0) {
                e.j("PushActivity", "Please add layout resource jpush_webview_layout.xml to res/layout !");
                pushActivity.finish();
                return;
            }
            pushActivity.setContentView(identifier);
            String str = gVar.a;
            if (cn.jpush.android.a.g.a(str)) {
                String str2 = gVar.Q;
                if (gVar.r) {
                    int identifier2 = pushActivity.getResources().getIdentifier("actionbarLayoutId", "id", pushActivity.getPackageName());
                    if (identifier2 == 0) {
                        e.j("PushActivity", "Please use default code in jpush_webview_layout.xml!");
                        pushActivity.finish();
                        return;
                    }
                    pushActivity.d = (FullScreenView) pushActivity.findViewById(identifier2);
                    pushActivity.d.initModule(pushActivity, bVar);
                    if (TextUtils.isEmpty(str2) || !new File(str2.replace("file://", "")).exists() || pushActivity.b) {
                        pushActivity.d.loadUrl(str);
                    } else {
                        pushActivity.d.loadUrl(str2);
                    }
                }
                if (!pushActivity.b) {
                    d.a(pushActivity.c, 1000, null, pushActivity);
                    return;
                }
                return;
            }
            cn.jpush.android.api.b.a((Context) pushActivity, bVar, 0);
            pushActivity.finish();
        }
    }
}
