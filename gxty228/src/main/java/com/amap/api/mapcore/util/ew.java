package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.mapcore.util.fs.a;

/* compiled from: ZoomControllerView */
class ew extends LinearLayout {
    private Bitmap a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;
    private Bitmap i;
    private Bitmap j;
    private Bitmap k;
    private Bitmap l;
    private ImageView m;
    private ImageView n;
    private lj o;

    public void a() {
        try {
            removeAllViews();
            this.a.recycle();
            this.b.recycle();
            this.c.recycle();
            this.d.recycle();
            this.e.recycle();
            this.f.recycle();
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            if (this.g != null) {
                this.g.recycle();
                this.g = null;
            }
            if (this.h != null) {
                this.h.recycle();
                this.h = null;
            }
            if (this.i != null) {
                this.i.recycle();
                this.i = null;
            }
            if (this.j != null) {
                this.j.recycle();
                this.g = null;
            }
            if (this.k != null) {
                this.k.recycle();
                this.k = null;
            }
            if (this.l != null) {
                this.l.recycle();
                this.l = null;
            }
            this.m = null;
            this.n = null;
        } catch (Throwable th) {
            gz.c(th, "ZoomControllerView", "destory");
            th.printStackTrace();
        }
    }

    public ew(Context context, lj ljVar) {
        super(context);
        this.o = ljVar;
        try {
            this.g = en.a(context, "zoomin_selected.png");
            this.a = en.a(this.g, le.a);
            this.h = en.a(context, "zoomin_unselected.png");
            this.b = en.a(this.h, le.a);
            this.i = en.a(context, "zoomout_selected.png");
            this.c = en.a(this.i, le.a);
            this.j = en.a(context, "zoomout_unselected.png");
            this.d = en.a(this.j, le.a);
            this.k = en.a(context, "zoomin_pressed.png");
            this.e = en.a(this.k, le.a);
            this.l = en.a(context, "zoomout_pressed.png");
            this.f = en.a(this.l, le.a);
            this.m = new ImageView(context);
            this.m.setImageBitmap(this.a);
            this.m.setClickable(true);
            this.n = new ImageView(context);
            this.n.setImageBitmap(this.c);
            this.n.setClickable(true);
            this.m.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ ew a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                        if (this.a.o.g() < this.a.o.getMaxZoomLevel() && this.a.o.isMaploaded()) {
                            if (motionEvent.getAction() == 0) {
                                this.a.m.setImageBitmap(this.a.e);
                            } else if (motionEvent.getAction() == 1) {
                                this.a.m.setImageBitmap(this.a.a);
                                this.a.o.b(p.a());
                            }
                        }
                    } catch (Throwable e) {
                        gz.c(e, "ZoomControllerView", "zoomin ontouch");
                        e.printStackTrace();
                    } catch (Throwable e2) {
                        e2.printStackTrace();
                    }
                    return false;
                }
            });
            this.n.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ ew a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                        if (this.a.o.g() > this.a.o.getMinZoomLevel() && this.a.o.isMaploaded()) {
                            if (motionEvent.getAction() == 0) {
                                this.a.n.setImageBitmap(this.a.f);
                            } else if (motionEvent.getAction() == 1) {
                                this.a.n.setImageBitmap(this.a.c);
                                this.a.o.b(p.b());
                            }
                        }
                    } catch (Throwable th) {
                        gz.c(th, "ZoomControllerView", "zoomout ontouch");
                        th.printStackTrace();
                    }
                    return false;
                }
            });
            this.m.setPadding(0, 0, 20, -2);
            this.n.setPadding(0, 0, 20, 20);
            setOrientation(1);
            addView(this.m);
            addView(this.n);
        } catch (Throwable th) {
            gz.c(th, "ZoomControllerView", "create");
            th.printStackTrace();
        }
    }

    public void a(float f) {
        try {
            if (f < this.o.getMaxZoomLevel() && f > this.o.getMinZoomLevel()) {
                this.m.setImageBitmap(this.a);
                this.n.setImageBitmap(this.c);
            } else if (f == this.o.getMinZoomLevel()) {
                this.n.setImageBitmap(this.d);
                this.m.setImageBitmap(this.a);
            } else if (f == this.o.getMaxZoomLevel()) {
                this.m.setImageBitmap(this.b);
                this.n.setImageBitmap(this.c);
            }
        } catch (Throwable th) {
            gz.c(th, "ZoomControllerView", "setZoomBitmap");
            th.printStackTrace();
        }
    }

    public void a(int i) {
        try {
            a aVar = (a) getLayoutParams();
            if (i == 1) {
                aVar.d = 16;
            } else if (i == 2) {
                aVar.d = 80;
            }
            setLayoutParams(aVar);
        } catch (Throwable th) {
            gz.c(th, "ZoomControllerView", "setZoomPosition");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
