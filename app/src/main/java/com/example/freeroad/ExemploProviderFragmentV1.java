package com.example.freeroad;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ExemploProviderFragmentV1 extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    private GoogleMap mMap;

    private LocationManager locationManager; //Ele trabalha com o provider para buscar a nossa localização automaticamente e outros recurasos

    private static final String TAG = "ExemProvFragmV1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
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
        try {

            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            //Usando o critéria que permite realizar buscas específicas relacionadas ao provider
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);//Tenta localizar qual o melhor provider

            Toast.makeText(getActivity(), "Provider: "+provider, Toast.LENGTH_LONG).show();

            mMap = googleMap;

            //Implementando o evento de click no mapa
            mMap.setOnMapClickListener(this);

            //Habilitando o botão de ZOOM
            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);

        }catch (SecurityException e){
            Log.e(TAG, "ERRO: ", e);
        }
            // Add a marker in Sydney and move the camera
            LatLng lubango = new LatLng(-14.9171700, 13.4925000);

            MarkerOptions marker = new MarkerOptions();
            marker.position(lubango);
            marker.title("Cidade do Lubango");
            mMap.addMarker(marker);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lubango));



    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(getContext(), "Coordenadas: "+latLng.toString(), Toast.LENGTH_LONG).show();
    }
}
