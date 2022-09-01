package com.flyai.navermap_source;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

import java.util.Map;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById((R.id.map));
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();

        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        LatLng objLocation;
        double dLatitude = 37.55085498266212;
        double dLongitude = 126.92550255463921;
        naverMap.setMapType(NaverMap.MapType.Basic);

        naverMap.setSymbolScale(1.5f);
        objLocation = new LatLng(dLatitude, dLongitude);

        CameraUpdate cameraUpdate1 = CameraUpdate.scrollTo(objLocation);
        naverMap.moveCamera(cameraUpdate1);

        CameraUpdate cameraUpdate2 = CameraUpdate.zoomTo(15);
        naverMap.moveCamera(cameraUpdate2);

        Marker objMK = new Marker();
        objMK.setPosition(objLocation);
        objMK.setMap(naverMap);

        objMK.setSubCaptionText("홍익대학교");
        objMK.setSubCaptionColor(Color.RED);
        objMK.setCaptionHaloColor(Color.GREEN);
        objMK.setCaptionTextSize(10);

        InfoWindow infoWindow1 = new InfoWindow();
        infoWindow1.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "홍익대학교";
            }
        });
        infoWindow1.open(objMK);

        dLatitude = 37.55425235339918;
        dLongitude = 126.93018447417218;
        objLocation = new LatLng(dLatitude, dLongitude);

        Marker objMK2 = new Marker();
        objMK2.setPosition(objLocation);
        objMK2.setMap(naverMap);

        objMK2.setSubCaptionText("자취방");
        objMK2.setSubCaptionColor(Color.RED);
        objMK2.setCaptionHaloColor(Color.YELLOW);
        objMK2.setSubCaptionTextSize(10);

        InfoWindow infoWindow2 = new InfoWindow();
        infoWindow2.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "자취방";
            }
        });
        infoWindow2.open(objMK2);
    }

//    private void MyOverlayMarker() {
//        int markerId = NMapPOIflagType.PIN;
//        NMapPOIdata
//    }
}