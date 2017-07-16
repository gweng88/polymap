package com.teamlake.thinkingemoji.tourguide;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.List;

public class MapsActivity extends FragmentActivity
        implements
            OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View customMarkerView;

        CustomInfoWindowAdapter() {
            customMarkerView = getLayoutInflater()
                    .inflate(R.layout.custom_info_contents, null);
        }

        public View getInfoWindow(Marker marker) {
            render(marker, customMarkerView);
            return customMarkerView;
        }

        public View getInfoContents(Marker marker) {
            return null;
        }

        private void render(Marker marker, View view) {
            // Add the code to set the required values
            // for each element in your custominfowindow layout file
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //List<LatLng> points = ;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        //   for (LatLng item : points) {
        //    builder.include(item);
       //    }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng houston = new LatLng(29.76,-93.37);
        builder.include(sydney);
        builder.include(houston);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(houston).title("Marker in Houston"));
        Polyline polyline1 = mMap.addPolyline(new PolylineOptions().width(11).startCap(new RoundCap()).endCap(new RoundCap()).color(Color.RED).add(sydney, houston));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0));
    }
}
