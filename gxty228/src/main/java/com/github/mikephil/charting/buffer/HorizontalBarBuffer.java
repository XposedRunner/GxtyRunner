package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class HorizontalBarBuffer extends BarBuffer {
    public HorizontalBarBuffer(int i, int i2, boolean z) {
        super(i, i2, z);
    }

    public void feed(IBarDataSet iBarDataSet) {
        float entryCount = ((float) iBarDataSet.getEntryCount()) * this.phaseX;
        float f = this.mBarWidth / 2.0f;
        for (int i = 0; ((float) i) < entryCount; i++) {
            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i);
            if (barEntry != null) {
                float x = barEntry.getX();
                float y = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                float f2;
                float f3;
                float f4;
                if (!this.mContainsStacks || yVals == null) {
                    float f5;
                    f2 = x - f;
                    f3 = x + f;
                    if (this.mInverted) {
                        f4 = y >= 0.0f ? y : 0.0f;
                        f5 = y <= 0.0f ? y : 0.0f;
                        y = f4;
                    } else {
                        f5 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                    }
                    if (f5 > 0.0f) {
                        f5 *= this.phaseY;
                    } else {
                        y *= this.phaseY;
                    }
                    addBar(y, f3, f5, f2);
                } else {
                    f4 = 0.0f;
                    y = -barEntry.getNegativeSum();
                    for (float f32 : yVals) {
                        if (f32 >= 0.0f) {
                            f2 = f4 + f32;
                            f32 = f4;
                            f4 = f2;
                        } else {
                            f2 = Math.abs(f32) + y;
                            float abs = Math.abs(f32) + y;
                            f32 = y;
                            y = abs;
                        }
                        float f6 = x - f;
                        float f7 = x + f;
                        float f8;
                        if (this.mInverted) {
                            if (f32 >= f2) {
                                f8 = f32;
                            } else {
                                f8 = f2;
                            }
                            if (f32 > f2) {
                                f32 = f2;
                            }
                            f2 = f8;
                        } else {
                            if (f32 >= f2) {
                                f8 = f32;
                            } else {
                                f8 = f2;
                            }
                            if (f32 > f2) {
                                f32 = f2;
                            }
                            f2 = f32;
                            f32 = f8;
                        }
                        addBar(f2 * this.phaseY, f7, f32 * this.phaseY, f6);
                    }
                }
            }
        }
        reset();
    }
}
