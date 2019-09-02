package com.github.mikephil.charting.data.filter;

import android.annotation.TargetApi;
import java.util.Arrays;

public class Approximator {

    private class Line {
        private float dx;
        private float dy;
        private float exsy;
        private float length = ((float) Math.sqrt((double) ((this.dx * this.dx) + (this.dy * this.dy))));
        private float[] points;
        private float sxey;

        public Line(float f, float f2, float f3, float f4) {
            this.dx = f - f3;
            this.dy = f2 - f4;
            this.sxey = f * f4;
            this.exsy = f3 * f2;
            this.points = new float[]{f, f2, f3, f4};
        }

        public float distance(float f, float f2) {
            return Math.abs((((this.dy * f) - (this.dx * f2)) + this.sxey) - this.exsy) / this.length;
        }

        public float[] getPoints() {
            return this.points;
        }
    }

    @TargetApi(9)
    public float[] reduceWithDouglasPeucker(float[] fArr, float f) {
        Line line = new Line(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1]);
        float f2 = 0.0f;
        int i = 0;
        for (int i2 = 2; i2 < fArr.length - 2; i2 += 2) {
            float distance = line.distance(fArr[i2], fArr[i2 + 1]);
            if (distance > f2) {
                f2 = distance;
                i = i2;
            }
        }
        if (f2 <= f) {
            return line.getPoints();
        }
        float[] reduceWithDouglasPeucker = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, 0, i + 2), f);
        float[] reduceWithDouglasPeucker2 = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, i, fArr.length), f);
        reduceWithDouglasPeucker2 = Arrays.copyOfRange(reduceWithDouglasPeucker2, 2, reduceWithDouglasPeucker2.length);
        return concat(reduceWithDouglasPeucker, reduceWithDouglasPeucker2);
    }

    float[] concat(float[]... fArr) {
        int i = 0;
        for (float[] length : fArr) {
            i += length.length;
        }
        float[] length2 = new float[i];
        int length3 = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length3) {
            i = i3;
            for (float f : fArr[i2]) {
                length2[i] = f;
                i++;
            }
            i2++;
            i3 = i;
        }
        return length2;
    }
}
