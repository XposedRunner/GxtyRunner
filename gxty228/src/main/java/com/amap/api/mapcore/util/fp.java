package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.amap.api.maps.model.CameraPosition;

/* compiled from: CompassView */
public class fp extends LinearLayout {
    Bitmap a;
    Bitmap b;
    Bitmap c;
    ImageView d;
    lj e;
    Matrix f = new Matrix();

    public void a() {
        try {
            removeAllViews();
            if (this.a != null) {
                this.a.recycle();
            }
            if (this.b != null) {
                this.b.recycle();
            }
            if (this.c != null) {
                this.c.recycle();
            }
            if (this.f != null) {
                this.f.reset();
                this.f = null;
            }
            this.c = null;
            this.a = null;
            this.b = null;
        } catch (Throwable th) {
            gz.c(th, "CompassView", "destroy");
            th.printStackTrace();
        }
    }

    public fp(Context context, lj ljVar) {
        super(context);
        this.e = ljVar;
        try {
            this.c = en.a(context, "maps_dav_compass_needle_large.png");
            this.b = en.a(this.c, le.a * 0.8f);
            this.c = en.a(this.c, le.a * 0.7f);
            if (this.b != null && this.c != null) {
                this.a = Bitmap.createBitmap(this.b.getWidth(), this.b.getHeight(), Config.ARGB_8888);
                Canvas canvas = new Canvas(this.a);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setFilterBitmap(true);
                canvas.drawBitmap(this.c, ((float) (this.b.getWidth() - this.c.getWidth())) / 2.0f, ((float) (this.b.getHeight() - this.c.getHeight())) / 2.0f, paint);
                this.d = new ImageView(context);
                this.d.setScaleType(ScaleType.MATRIX);
                this.d.setImageBitmap(this.a);
                this.d.setClickable(true);
                b();
                this.d.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ fp a;

                    {
                        this.a = r1;
                    }

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        try {
                            if (this.a.e.isMaploaded()) {
                                if (motionEvent.getAction() == 0) {
                                    this.a.d.setImageBitmap(this.a.b);
                                } else if (motionEvent.getAction() == 1) {
                                    this.a.d.setImageBitmap(this.a.a);
                                    CameraPosition cameraPosition = this.a.e.getCameraPosition();
                                    this.a.e.b(p.a(new CameraPosition(cameraPosition.target, cameraPosition.zoom, 0.0f, 0.0f)));
                                }
                            }
                        } catch (Throwable th) {
                            gz.c(th, "CompassView", "onTouch");
                            th.printStackTrace();
                        }
                        return false;
                    }
                });
                addView(this.d);
            }
        } catch (Throwable th) {
            gz.c(th, "CompassView", "create");
            th.printStackTrace();
        }
    }

    public void b() {
        try {
            if (this.e != null && this.d != null) {
                float o = this.e.o(1);
                float n = this.e.n(1);
                if (this.f == null) {
                    this.f = new Matrix();
                }
                this.f.reset();
                this.f.postRotate(-n, ((float) this.d.getDrawable().getBounds().width()) / 2.0f, ((float) this.d.getDrawable().getBounds().height()) / 2.0f);
                this.f.postScale(1.0f, (float) Math.cos((((double) o) * 3.141592653589793d) / 180.0d), ((float) this.d.getDrawable().getBounds().width()) / 2.0f, ((float) this.d.getDrawable().getBounds().height()) / 2.0f);
                this.d.setImageMatrix(this.f);
            }
        } catch (Throwable th) {
            gz.c(th, "CompassView", "invalidateAngle");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
            return;
        }
        setVisibility(8);
    }
}
