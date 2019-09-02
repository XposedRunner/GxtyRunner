package com.example.gita.gxty_runner.hook.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;

import com.example.gita.gxty_runner.GxtyRunner;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class AMapSetLocationListenerMethodHook extends XC_MethodHook {
    //            boolean isMethodHooked = false;
    private List<Class> hookedClasses = new ArrayList<>();
    ;

    @Override
    protected void afterHookedMethod(MethodHookParam param) {

//                if (isMethodHooked) return;
        if (hookedClasses.contains(param.args[0].getClass())) {
            return;
        }
        hookedClasses.add(param.args[0].getClass());

        if (param.args[0].getClass().getName().contains(".service.BaseService")) {
            XposedHelpers.findAndHookMethod(param.args[0].getClass(), "onLocationChanged", "com.amap.api.location.AMapLocation", new XC_MethodHook() {


                @Override
                protected void beforeHookedMethod(final MethodHookParam param) {
                    final ClassLoader classLoader = param.args[0].getClass().getClassLoader();


                    if (Common.routeProcessor == null) {
                        double latitude = (double) XposedHelpers.callMethod(param.args[0], "getLatitude");
                        double longitude = (double) XposedHelpers.callMethod(param.args[0], "getLongitude");
                        Common.routeProcessor = RouteProcessor.newRouteProcessor();
                        Common.routeProcessor.withContext(Common.context.get())
                                .start(new LatLng(latitude, longitude))
                                .via(Common.dPointList)
                                .target(Common.gpsCount, Common.beaconCount, Common.target)
                                .withCallback(new RouteProcessor.AllRouteSearchedCallback() {
                                    Class LatLngClazz = XposedHelpers.findClass("com.amap.api.maps.model.LatLng", classLoader);

                                    @Override
                                    public void onAllRouteSearched(List<LatLng> route) {
                                        route = RouteFaker.fakeRoute(route);
                                        Class PolylineOptionsClazz = XposedHelpers.findClass("com.amap.api.maps.model.PolylineOptions", classLoader);
                                        List<Object> mapRoute = new ArrayList<>();
                                        for (LatLng node : route) {
                                            Object mapLatLng = XposedHelpers.newInstance(LatLngClazz, new Class[]{double.class, double.class}, node.latitude, node.longitude);
                                            mapRoute.add(mapLatLng);
                                        }
                                        Object polylineOptions = XposedHelpers.newInstance(PolylineOptionsClazz);
                                        polylineOptions = XposedHelpers.callMethod(polylineOptions, "addAll", new Class[]{List.class}, mapRoute);
                                        polylineOptions = XposedHelpers.callMethod(polylineOptions, "color", new Class[]{int.class}, Color.GREEN);
                                        XposedHelpers.callMethod(Common.aMap, "addPolyline", new Class[]{PolylineOptionsClazz}, polylineOptions);
                                        Common.emulator = Emulator.create(route, 2.5f, new Emulator.Listener() {
                                            Object marker;
                                            Class CameraUpdateFactoryClazz = XposedHelpers.findClass("com.amap.api.maps.CameraUpdateFactory", classLoader);
                                            //Class CameraPositionClazz = XposedHelpers.findClass("com.amap.api.maps.model.CameraPosition", classLoader);
                                            Class CameraUpdateClazz = XposedHelpers.findClass("com.amap.api.maps.CameraUpdate", classLoader);

                                            @Override
                                            public void onEmulating(Emulator emulator, LatLng latLng, Angle angle, int extra) {
                                                switch (extra) {
                                                    case Emulator.EXTRA_START:
                                                        Common.currentLatLng = latLng;
                                                        Common.lastLatLng = latLng;
                                                        Common.currentVirtualLatLng = latLng;
                                                        Common.lastVirtualLatLng = latLng;
                                                        Common.routeRandom = RouteRandom.create(50, 100, 0, 25);

                                                        Class MarkerOptionsClazz = XposedHelpers.findClass("com.amap.api.maps.model.MarkerOptions", classLoader);
                                                        Object mapLatLng = XposedHelpers.newInstance(LatLngClazz, new Class[]{double.class, double.class}, Common.currentVirtualLatLng.latitude, Common.currentVirtualLatLng.longitude);
                                                        Object markerOptions = XposedHelpers.newInstance(MarkerOptionsClazz);
                                                        markerOptions = XposedHelpers.callMethod(markerOptions, "position", new Class[]{LatLngClazz}, mapLatLng);
                                                        Class BitmapDescriptorFactoryClazz = XposedHelpers.findClass("com.amap.api.maps.model.BitmapDescriptorFactory", classLoader);
                                                        String base64String = "iVBORw0KGgoAAAANSUhEUgAAADYAAAA2CAMAAAC7m5rvAAAALVBMVEVHcEwBXqoEf9UFjeUCdt4CeukAbs0AYLsATZcGnvEKuP8AAgQAAAAAAQIADhnFgqZjAAAAD3RSTlMALXO92P/+/7v8/11FKwghOavIAAABSElEQVR4AZ3RCW7DMAxE0VjyxqSd+x+3kVWHwi/CmKWxCw8f8NzeX9XtHzdZff9YgpeSb0lmS96p3G1ZlI81VpSONTZnmR1smZSMdZbKSbWzXE7T/WQ1w+qLLVLi7zsrSvwQZ2EOMWeJXI8dLLGBzBk2iGMny+RUnTEXTw1WlYg5u7CBZGDYIJgaDLkgBlZ0LQY2f2IGxg3iGFickyoZc/HUYPHkqmRXNpDsPSuKfwhZnGMMLMh5DCzcQBYybsAYWZxTDRlynDpgVRdiy7bRSR///tZu5QYfpl633wtziNmJGCxijCm/YAMZUwhyA8RsTDGInFSZYtBznBqpfd8R5OTqsWUwj34DXbmBZGNqPw1kh8VZ8RTMSQcneaynBvJ1HKINeq601P7HfD+/07p8baB5HUwXfoxua99Amp6Pj/7mRseRttfGfgBm3i+BaNCYvwAAAABJRU5ErkJggg==";
                                                        byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);
                                                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                                        Object bitmapDescriptor = XposedHelpers.callStaticMethod(BitmapDescriptorFactoryClazz, "fromBitmap", new Class[]{Bitmap.class}, bitmap);
                                                        Class BitmapDescriptorClazz = XposedHelpers.findClass("com.amap.api.maps.model.BitmapDescriptor", classLoader);
                                                        markerOptions = XposedHelpers.callMethod(markerOptions, "icon", new Class[]{BitmapDescriptorClazz}, bitmapDescriptor);
                                                        marker = XposedHelpers.callMethod(Common.aMap, "addMarker", new Class[]{MarkerOptionsClazz}, markerOptions);
                                                        Object cameraUpdate = XposedHelpers.callStaticMethod(CameraUpdateFactoryClazz, "newLatLngZoom", new Class[]{LatLngClazz, float.class}, mapLatLng, 15);
                                                        XposedHelpers.callMethod(Common.aMap, "moveCamera", new Class[]{CameraUpdateClazz}, cameraUpdate);

                                                        break;
                                                    case Emulator.EXTRA_EMULATING:
                                                        if (marker != null && !Common.isBackground) {
                                                            mapLatLng = XposedHelpers.newInstance(LatLngClazz, new Class[]{double.class, double.class}, Common.currentVirtualLatLng.latitude, Common.currentVirtualLatLng.longitude);
                                                            XposedHelpers.callMethod(marker, "setPosition", new Class[]{LatLngClazz}, mapLatLng);
                                                            XposedHelpers.callMethod(marker, "setRotateAngle", new Class[]{float.class}, MapUtils.toRotateAngle(angle.rad()));
//                                                            Object cameraUpdate = XposedHelpers.callStaticMethod(CameraUpdateFactoryClazz, "newLatLng", new Class[]{LatLngClazz}, mapLatLng);
//                                                            XposedHelpers.callMethod(aMap, "moveCamera", new Class[]{CameraUpdateClazz}, cameraUpdate);
                                                        }

                                                        Common.lastVirtualLatLng = Common.currentVirtualLatLng;
                                                        Common.currentVirtualLatLng = latLng;
                                                        double random = Common.routeRandom.next(MapUtils.getDistance(Common.lastVirtualLatLng, Common.currentVirtualLatLng));
                                                        Common.currentLatLng = MapUtils.nextOffsetLatLng(latLng, angle, random);
//                                                        Common.direction = (float) Angle.elevate(lastLatLng, currentLatLng).deg();

//                                                    XposedBridge.log("direction: " + direction);
//                                                    lastLatLng = currentLatLng;

                                                        break;
                                                    case Emulator.EXTRA_STOP:

                                                        break;
                                                }
                                            }
                                        });
                                        Common.emulator.start();
//                                        Common.emulator.setRoute(route);
//                                        Common.emulator.setSpeed(3.0f);
//                                        Common.emulator.registerListener(new Emulator.EmulatorListener() {
//                                            Object marker;
//                                            Class CameraUpdateFactoryClazz = XposedHelpers.findClass("com.amap.api.maps.CameraUpdateFactory", classLoader);
//                                            //Class CameraPositionClazz = XposedHelpers.findClass("com.amap.api.maps.model.CameraPosition", classLoader);
//                                            Class CameraUpdateClazz = XposedHelpers.findClass("com.amap.api.maps.CameraUpdate", classLoader);
//
//                                            @Override
//                                            public void onStart(double latitude, double longitude, double bearing) {
//                                                Class MarkerOptionsClazz = XposedHelpers.findClass("com.amap.api.maps.model.MarkerOptions", classLoader);
//                                                Object mapLatLng = XposedHelpers.newInstance(LatLngClazz, new Class[]{double.class, double.class}, latitude, longitude);
//                                                Object markerOptions = XposedHelpers.newInstance(MarkerOptionsClazz);
//                                                markerOptions = XposedHelpers.callMethod(markerOptions, "position", new Class[]{LatLngClazz}, mapLatLng);
//                                                Class BitmapDescriptorFactoryClazz = XposedHelpers.findClass("com.amap.api.maps.model.BitmapDescriptorFactory", classLoader);
//                                                String base64String = "iVBORw0KGgoAAAANSUhEUgAAADYAAAA2CAMAAAC7m5rvAAAALVBMVEVHcEwBXqoEf9UFjeUCdt4CeukAbs0AYLsATZcGnvEKuP8AAgQAAAAAAQIADhnFgqZjAAAAD3RSTlMALXO92P/+/7v8/11FKwghOavIAAABSElEQVR4AZ3RCW7DMAxE0VjyxqSd+x+3kVWHwi/CmKWxCw8f8NzeX9XtHzdZff9YgpeSb0lmS96p3G1ZlI81VpSONTZnmR1smZSMdZbKSbWzXE7T/WQ1w+qLLVLi7zsrSvwQZ2EOMWeJXI8dLLGBzBk2iGMny+RUnTEXTw1WlYg5u7CBZGDYIJgaDLkgBlZ0LQY2f2IGxg3iGFickyoZc/HUYPHkqmRXNpDsPSuKfwhZnGMMLMh5DCzcQBYybsAYWZxTDRlynDpgVRdiy7bRSR///tZu5QYfpl633wtziNmJGCxijCm/YAMZUwhyA8RsTDGInFSZYtBznBqpfd8R5OTqsWUwj34DXbmBZGNqPw1kh8VZ8RTMSQcneaynBvJ1HKINeq601P7HfD+/07p8baB5HUwXfoxua99Amp6Pj/7mRseRttfGfgBm3i+BaNCYvwAAAABJRU5ErkJggg==";
//                                                byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);
//                                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                                                Object bitmapDescriptor = XposedHelpers.callStaticMethod(BitmapDescriptorFactoryClazz, "fromBitmap", new Class[]{Bitmap.class}, bitmap);
//                                                Class BitmapDescriptorClazz = XposedHelpers.findClass("com.amap.api.maps.model.BitmapDescriptor", classLoader);
//                                                markerOptions = XposedHelpers.callMethod(markerOptions, "icon", new Class[]{BitmapDescriptorClazz}, bitmapDescriptor);
//                                                marker = XposedHelpers.callMethod(Common.aMap, "addMarker", new Class[]{MarkerOptionsClazz}, markerOptions);
//                                                Object cameraUpdate = XposedHelpers.callStaticMethod(CameraUpdateFactoryClazz, "newLatLngZoom", new Class[]{LatLngClazz, float.class}, mapLatLng, 15);
//                                                XposedHelpers.callMethod(Common.aMap, "moveCamera", new Class[]{CameraUpdateClazz}, cameraUpdate);
//                                            }
//
//                                            @Override
//                                            public void onEmulating(double latitude, double longitude, double bearing) {
//                                                if (marker != null && !Common.isBackground) {
//                                                    Object mapLatLng = XposedHelpers.newInstance(LatLngClazz, new Class[]{double.class, double.class}, latitude, longitude);
//                                                    XposedHelpers.callMethod(marker, "setPosition", new Class[]{LatLngClazz}, mapLatLng);
//                                                    XposedHelpers.callMethod(marker, "setRotateAngle", new Class[]{float.class}, MapUtils.toRotateAngle(bearing));
////                                                            Object cameraUpdate = XposedHelpers.callStaticMethod(CameraUpdateFactoryClazz, "newLatLng", new Class[]{LatLngClazz}, mapLatLng);
////                                                            XposedHelpers.callMethod(aMap, "moveCamera", new Class[]{CameraUpdateClazz}, cameraUpdate);
//                                                }
//                                                Common.latLng = new LatLng(latitude, longitude);
//                                            }
//
//                                            @Override
//                                            public void onFinish() {
//
//                                            }
//                                        });
//                                        Common.emulator.start();

                                    }
                                }).process();
                    }
                    if (Common.currentLatLng != null) {
                        XposedHelpers.callMethod(param.args[0], "setLatitude", new Class[]{double.class}, Common.currentLatLng.latitude);
                        XposedHelpers.callMethod(param.args[0], "setLongitude", new Class[]{double.class}, Common.currentLatLng.longitude);
                        Common.lastLatLng = new LatLng(Common.currentLatLng.latitude, Common.currentLatLng.longitude);
                    }
                    XposedHelpers.callMethod(param.args[0], "setSpeed", new Class[]{float.class}, (float) (2 + Math.random()));
                    XposedHelpers.callMethod(param.args[0], "setAccuracy", new Class[]{float.class}, (float) (5 + 10 * Math.random()));
                    XposedHelpers.callMethod(param.args[0], "setErrorCode", new Class[]{int.class}, 0);
                    XposedHelpers.callMethod(param.args[0], "setErrorInfo", new Class[]{String.class}, "success");
                    XposedHelpers.callMethod(param.args[0], "setMock", new Class[]{boolean.class}, false);
                    XposedHelpers.callMethod(param.args[0], "setSatellites", new Class[]{int.class}, 16);
                    XposedHelpers.callMethod(param.args[0], "setLocationType", new Class[]{int.class}, 1);

//                            XposedBridge.log(param.method.getName() + "(" + param.args[0].toString() + ")");
                }
            });

//                    isMethodHooked = true;
        }


    }
}
