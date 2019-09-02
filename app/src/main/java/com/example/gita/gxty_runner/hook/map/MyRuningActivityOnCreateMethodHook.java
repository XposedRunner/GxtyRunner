package com.example.gita.gxty_runner.hook.map;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class MyRuningActivityOnCreateMethodHook extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) {

        if (Common.runType != 1) return;

        ClassLoader classLoader = param.thisObject.getClass().getClassLoader();
        Class mapViewClazz = XposedHelpers.findClass("com.amap.api.maps.TextureMapView", classLoader);
        Common.mapView = XposedHelpers.newInstance(mapViewClazz, new Class[]{Context.class}, (Context) param.thisObject);
        XposedHelpers.callMethod(Common.mapView, "onCreate", new Class[]{Bundle.class}, (Bundle) param.args[0]);
        Common.aMap = XposedHelpers.callMethod(Common.mapView, "getMap");
        CircleLayout circleLayout = new CircleLayout((Context) param.thisObject, 6, Color.WHITE);
        circleLayout.addView((View) Common.mapView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout mapLayout = (RelativeLayout) XposedHelpers.getObjectField(param.thisObject, "sport_mapLayout");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(360, 360);
        mapLayout.addView(circleLayout, layoutParams);
        Object uiSettings = XposedHelpers.callMethod(Common.aMap, "getUiSettings");
        XposedHelpers.callMethod(uiSettings, "setMyLocationButtonEnabled", new Class[]{Boolean.class}, false);
        XposedHelpers.callMethod(uiSettings, "setZoomControlsEnabled", new Class[]{Boolean.class}, false);
    }
}
