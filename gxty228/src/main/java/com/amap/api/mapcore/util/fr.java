package com.amap.api.mapcore.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps.model.LatLng;

/* compiled from: LocationView */
public class fr extends LinearLayout {
    Bitmap a;
    Bitmap b;
    Bitmap c;
    Bitmap d;
    Bitmap e;
    Bitmap f;
    ImageView g;
    lj h;
    boolean i = false;

    public void a() {
        try {
            removeAllViews();
            if (this.a != null) {
                this.a.recycle();
            }
            if (this.b != null) {
                this.b.recycle();
            }
            if (this.b != null) {
                this.c.recycle();
            }
            this.a = null;
            this.b = null;
            this.c = null;
            if (this.d != null) {
                this.d.recycle();
                this.d = null;
            }
            if (this.e != null) {
                this.e.recycle();
                this.e = null;
            }
            if (this.f != null) {
                this.f.recycle();
                this.f = null;
            }
        } catch (Throwable th) {
            gz.c(th, "LocationView", "destroy");
            th.printStackTrace();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public fr(Context context, lj ljVar) {
        super(context);
        this.h = ljVar;
        try {
            this.d = en.a(context, "location_selected.png");
            this.a = en.a(this.d, le.a);
            this.e = en.a(context, "location_pressed.png");
            this.b = en.a(this.e, le.a);
            this.f = en.a(context, "location_unselected.png");
            this.c = en.a(this.f, le.a);
            this.g = new ImageView(context);
            this.g.setImageBitmap(this.a);
            this.g.setClickable(true);
            this.g.setPadding(0, 20, 20, 0);
            this.g.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ fr a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (this.a.i) {
                        if (motionEvent.getAction() == 0) {
                            this.a.g.setImageBitmap(this.a.b);
                        } else if (motionEvent.getAction() == 1) {
                            try {
                                this.a.g.setImageBitmap(this.a.a);
                                this.a.h.setMyLocationEnabled(true);
                                Location myLocation = this.a.h.getMyLocation();
                                if (myLocation != null) {
                                    LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                                    this.a.h.a(myLocation);
                                    this.a.h.a(p.a(latLng, this.a.h.g()));
                                }
                            } catch (Throwable th) {
                                gz.c(th, "LocationView", "onTouch");
                                th.printStackTrace();
                            }
                        }
                    }
                    return false;
                }
            });
            addView(this.g);
        } catch (Throwable th) {
            gz.c(th, "LocationView", "create");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        this.i = z;
        if (z) {
            try {
                this.g.setImageBitmap(this.a);
            } catch (Throwable th) {
                gz.c(th, "LocationView", "showSelect");
                th.printStackTrace();
                return;
            }
        }
        this.g.setImageBitmap(this.c);
        this.g.invalidate();
    }
}
