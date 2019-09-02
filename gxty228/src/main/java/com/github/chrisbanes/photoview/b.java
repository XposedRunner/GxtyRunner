package com.github.chrisbanes.photoview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* compiled from: CustomGestureDetector */
class b {
    private int a = -1;
    private int b = 0;
    private final ScaleGestureDetector c;
    private VelocityTracker d;
    private boolean e;
    private float f;
    private float g;
    private final float h;
    private final float i;
    private c j;

    b(Context context, c cVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.i = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.h = (float) viewConfiguration.getScaledTouchSlop();
        this.j = cVar;
        this.c = new ScaleGestureDetector(context, new OnScaleGestureListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                this.a.j.a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }
        });
    }

    private float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.b);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    private float c(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.b);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    public boolean a() {
        return this.c.isInProgress();
    }

    public boolean b() {
        return this.e;
    }

    public boolean a(MotionEvent motionEvent) {
        try {
            this.c.onTouchEvent(motionEvent);
            return d(motionEvent);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean d(MotionEvent motionEvent) {
        int i = 0;
        float yVelocity;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.a = motionEvent.getPointerId(0);
                this.d = VelocityTracker.obtain();
                if (this.d != null) {
                    this.d.addMovement(motionEvent);
                }
                this.f = b(motionEvent);
                this.g = c(motionEvent);
                this.e = false;
                break;
            case 1:
                this.a = -1;
                if (this.e && this.d != null) {
                    this.f = b(motionEvent);
                    this.g = c(motionEvent);
                    this.d.addMovement(motionEvent);
                    this.d.computeCurrentVelocity(1000);
                    float xVelocity = this.d.getXVelocity();
                    yVelocity = this.d.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.i) {
                        this.j.a(this.f, this.g, -xVelocity, -yVelocity);
                    }
                }
                if (this.d != null) {
                    this.d.recycle();
                    this.d = null;
                    break;
                }
                break;
            case 2:
                yVelocity = b(motionEvent);
                float c = c(motionEvent);
                float f = yVelocity - this.f;
                float f2 = c - this.g;
                if (!this.e) {
                    this.e = Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.h);
                }
                if (this.e) {
                    this.j.a(f, f2);
                    this.f = yVelocity;
                    this.g = c;
                    if (this.d != null) {
                        this.d.addMovement(motionEvent);
                        break;
                    }
                }
                break;
            case 3:
                this.a = -1;
                if (this.d != null) {
                    this.d.recycle();
                    this.d = null;
                    break;
                }
                break;
            case 6:
                int a = j.a(motionEvent.getAction());
                if (motionEvent.getPointerId(a) == this.a) {
                    if (a == 0) {
                        a = 1;
                    } else {
                        a = 0;
                    }
                    this.a = motionEvent.getPointerId(a);
                    this.f = motionEvent.getX(a);
                    this.g = motionEvent.getY(a);
                    break;
                }
                break;
        }
        if (this.a != -1) {
            i = this.a;
        }
        this.b = motionEvent.findPointerIndex(i);
        return true;
    }
}
