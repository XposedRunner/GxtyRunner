package com.example.gita.gxty_runner.hook.map;

public class RouteRandom {
    private double xMin;
    private double yMin;
    private double xMax;
    private double yMax;

    private double xRandom;
    private double yRandom;

    private double x;
    private double y;

    private RouteRandom(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMax = yMax;
        this.yMin = yMin;
        xRandom = xMin + (xMax - xMin) * Math.random();
        yRandom = yMin + (yMax - yMin) * Math.random();
        yRandom = Math.random() > 0.5 ? -yRandom : yRandom;
        x = 0;
        y = 0;
    }


    public static RouteRandom create(double xMin, double xMax, double yMin, double yMax) {
        return new RouteRandom(xMin, xMax, yMin, yMax);
    }


    public double next(double d) {
        x += d;
        if (x > xRandom) {
            double t = x - xRandom;
            x = 0;
            xRandom = xMin + (xMax - xMin) * Math.random();
            yRandom = yMin + (yMax - yMin) * Math.random();
            yRandom = Math.random() > 0.5 ? -yRandom : yRandom;
            return next(t);
        }
//        double t = x / xRandom;
//        y = yRandom * 2 * t * (1 - t);
        if (x > xRandom / 2) {
            y = yRandom * (xRandom - x) / xRandom;
        } else {
            y = yRandom * x / xRandom;
        }
        return y;
    }

}
