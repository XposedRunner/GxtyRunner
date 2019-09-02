package com.example.gita.gxty.fragment;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.h;
import java.util.ArrayList;
import java.util.List;

public class AMapShowFragment extends Fragment implements OnMyLocationChangeListener {
    public static List<LatLng> a = new ArrayList();
    private AMap b;
    private MapView c;
    private MyLocationStyle d;
    private Boolean e = Boolean.valueOf(true);
    private LatLng f;
    private Marker g;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fg_show_amap, viewGroup, false);
        this.c = (MapView) inflate.findViewById(R.id.map);
        this.c.onCreate(bundle);
        a();
        return inflate;
    }

    private void a() {
        if (this.b == null) {
            this.b = this.c.getMap();
            b();
        }
        this.b.setOnMyLocationChangeListener(this);
    }

    private void b() {
        this.d = new MyLocationStyle();
        this.d.strokeColor(Color.argb(0, 0, 0, 0));
        this.d.radiusFillColor(Color.argb(0, 0, 0, 0));
        this.b.setMyLocationStyle(this.d);
        this.b.getUiSettings().setMyLocationButtonEnabled(false);
        this.b.setMyLocationEnabled(true);
        this.b.setMyLocationStyle(this.d.myLocationType(2));
        this.b.setMinZoomLevel(17.0f);
    }

    public void onMyLocationChange(Location location) {
        if (location != null) {
            h.a("onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle extras = location.getExtras();
            if (extras != null) {
                int i = extras.getInt(MyLocationStyle.ERROR_CODE);
                String string = extras.getString(MyLocationStyle.ERROR_INFO);
                int i2 = extras.getInt(MyLocationStyle.LOCATION_TYPE);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                if (this.e.booleanValue()) {
                    this.f = latLng;
                    this.e = Boolean.valueOf(false);
                    this.g = this.b.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.sport_16)));
                }
                if (((double) AMapUtils.calculateLineDistance(this.f, latLng)) > 0.5d) {
                    a(this.f, latLng);
                    a.add(latLng);
                    this.f = latLng;
                }
                h.a("定位信息， code: " + i + " errorInfo: " + string + " locationType: " + i2);
                return;
            }
            h.a((Object) "定位信息， bundle is null ");
            return;
        }
        h.a((Object) "定位失败");
    }

    private void a(LatLng latLng, LatLng latLng2) {
        this.b.addPolyline(new PolylineOptions().add(latLng, latLng2).geodesic(true).color(-16711936));
    }

    public void onResume() {
        super.onResume();
        this.c.onResume();
    }

    public void onPause() {
        super.onPause();
        this.c.onPause();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.c.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.c.onDestroy();
    }
}
