package com.example.gita.gxty.adapter;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.h;

/* compiled from: OfflineChild */
public class a implements OnClickListener, OnLongClickListener {
    public static String a;
    Dialog b;
    private Context c;
    private TextView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private int h = 0;
    private OfflineDownloadedAdapter i;
    private OfflineMapManager j;
    private OfflineMapCity k;
    private boolean l = false;
    private boolean m = false;
    private Handler n = new Handler(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int intValue = ((Integer) message.obj).intValue();
            switch (message.what) {
                case -1:
                    this.a.e();
                    return;
                case 0:
                    this.a.e(intValue);
                    return;
                case 1:
                    this.a.d(intValue);
                    return;
                case 2:
                    this.a.b(intValue);
                    return;
                case 3:
                    this.a.c(intValue);
                    return;
                case 4:
                    this.a.f();
                    return;
                case 6:
                    this.a.c();
                    return;
                case 7:
                    this.a.d();
                    return;
                case 101:
                case 102:
                case 103:
                    this.a.e();
                    return;
                default:
                    return;
            }
        }
    };
    private View o;

    public void a(boolean z) {
        this.m = z;
    }

    public a(Context context, OfflineMapManager offlineMapManager) {
        this.c = context;
        b();
        this.j = offlineMapManager;
    }

    public a(Context context, OfflineMapManager offlineMapManager, OfflineDownloadedAdapter offlineDownloadedAdapter, int i) {
        this.c = context;
        a(1);
        this.j = offlineMapManager;
        this.h = i;
        this.i = offlineDownloadedAdapter;
    }

    public View a() {
        return this.o;
    }

    private void b() {
        this.o = ((LayoutInflater) this.c.getSystemService("layout_inflater")).inflate(R.layout.offlinemap_child, null);
        this.d = (TextView) this.o.findViewById(R.id.name);
        this.e = (TextView) this.o.findViewById(R.id.name_size);
        this.f = (TextView) this.o.findViewById(R.id.download_progress_status);
        this.o.setOnClickListener(this);
        this.o.setOnLongClickListener(this);
    }

    private void a(int i) {
        this.o = ((LayoutInflater) this.c.getSystemService("layout_inflater")).inflate(R.layout.offlinemap_child_custom, null);
        this.d = (TextView) this.o.findViewById(R.id.name);
        this.e = (TextView) this.o.findViewById(R.id.name_size);
        this.f = (TextView) this.o.findViewById(R.id.download_progress_status);
        this.g = (ImageView) this.o.findViewById(R.id.check);
        this.o.setOnClickListener(this);
    }

    public void a(OfflineMapCity offlineMapCity) {
        if (offlineMapCity != null) {
            this.k = offlineMapCity;
            this.d.setText(offlineMapCity.getCity());
            this.e.setText(String.valueOf(((double) ((int) (((((double) offlineMapCity.getSize()) / 1024.0d) / 1024.0d) * 100.0d))) / 100.0d) + " M");
            a(this.k.getState(), this.k.getcompleteCode(), this.l);
        }
    }

    public void a(OfflineMapCity offlineMapCity, int i) {
        if (offlineMapCity != null) {
            this.k = offlineMapCity;
            this.d.setText(offlineMapCity.getCity());
            this.e.setText(String.valueOf(((double) ((int) (((((double) offlineMapCity.getSize()) / 1024.0d) / 1024.0d) * 100.0d))) / 100.0d) + " M");
            if (offlineMapCity.getCity().equals(a)) {
                this.g.setImageResource(R.mipmap.circlesel);
                h.a("111zbc" + a);
            } else {
                this.g.setImageResource(R.mipmap.circle);
                h.a("222zbc" + a);
            }
            a(this.k.getState(), this.k.getcompleteCode(), this.l);
        }
    }

    private void a(int i, int i2, boolean z) {
        h.a((Object) "notifyViewDisplay");
        if (this.k != null) {
            this.k.setState(i);
            this.k.setCompleteCode(i2);
        }
        Message message = new Message();
        message.what = i;
        message.obj = Integer.valueOf(i2);
        this.n.sendMessage(message);
    }

    private void c() {
        this.f.setVisibility(0);
        this.f.setTextColor(Color.parseColor("#68c4af"));
        this.f.setText("点我下载");
    }

    private void d() {
        this.f.setVisibility(0);
        this.f.setTextColor(Color.parseColor("#80ffffff"));
        this.f.setText("已下载-有更新");
    }

    private void b(int i) {
        this.f.setVisibility(0);
        this.f.setTextColor(Color.parseColor("#80ffffff"));
        this.f.setText("等待中");
    }

    private void e() {
        this.f.setVisibility(0);
        this.f.setTextColor(Color.parseColor("#80ffffff"));
        this.f.setText("下载出现异常");
    }

    private void c(int i) {
        if (this.k != null) {
            i = this.k.getcompleteCode();
        }
        this.f.setVisibility(0);
        this.f.setTextColor(Color.parseColor("#80ffffff"));
        this.f.setText("暂停中:" + i + "%");
    }

    private void f() {
        this.f.setVisibility(0);
        this.f.setTextColor(Color.parseColor("#80ffffff"));
        this.f.setText("安装成功");
    }

    private void d(int i) {
        this.f.setVisibility(0);
        this.f.setTextColor(Color.parseColor("#80ffffff"));
        this.f.setText("正在解压: " + i + "%");
    }

    private void e(int i) {
        if (this.k != null) {
            this.f.setVisibility(0);
            this.f.setText(this.k.getcompleteCode() + "%");
        }
    }

    private synchronized void g() {
        this.j.pause();
        this.j.restart();
    }

    private synchronized boolean h() {
        boolean z;
        try {
            if (this.m) {
                this.j.downloadByProvinceName(this.k.getCity());
            } else {
                this.j.downloadByCityName(this.k.getCity());
            }
            z = true;
        } catch (AMapException e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public void onClick(View view) {
        if (this.h == 1) {
            if (this.k != null) {
                this.k.getState();
                this.k.getcompleteCode();
                h.a(this.k.getCity() + " " + this.k.getState());
                a = this.k.getCity();
                this.i.notifyDataSetChanged();
            }
        } else if (this.k != null) {
            int state = this.k.getState();
            int i = this.k.getcompleteCode();
            switch (state) {
                case 0:
                    g();
                    c(i);
                    break;
                case 1:
                case 4:
                    break;
                default:
                    if (!h()) {
                        e();
                        h.a((Object) "下载失败");
                        break;
                    }
                    b(i);
                    break;
            }
            h.a(this.k.getCity() + " " + this.k.getState());
        }
    }

    public synchronized void a(final String str) {
        Builder builder = new Builder(this.c);
        builder.setTitle(str);
        builder.setSingleChoiceItems(new String[]{"删除"}, -1, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.b.dismiss();
                if (this.b.j != null) {
                    switch (i) {
                        case 0:
                            this.b.j.remove(str);
                            return;
                        default:
                            return;
                    }
                }
            }
        });
        builder.setNegativeButton("取消", null);
        this.b = builder.create();
        this.b.show();
    }

    public void b(final String str) {
        Builder builder = new Builder(this.c);
        builder.setTitle(str);
        builder.setSingleChoiceItems(new String[]{"删除", "检查更新"}, -1, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.b.dismiss();
                if (this.b.j != null) {
                    switch (i) {
                        case 0:
                            this.b.j.remove(str);
                            return;
                        case 1:
                            try {
                                this.b.j.updateOfflineCityByName(str);
                                return;
                            } catch (AMapException e) {
                                e.printStackTrace();
                                return;
                            }
                        default:
                            return;
                    }
                }
            }
        });
        builder.setNegativeButton("取消", null);
        this.b = builder.create();
        this.b.show();
    }

    public boolean onLongClick(View view) {
        h.a(this.k.getCity() + " : " + this.k.getState());
        if (this.k.getState() == 4) {
            b(this.k.getCity());
        } else if (this.k.getState() != 6) {
            a(this.k.getCity());
        }
        return false;
    }
}
