package com.example.inmobiliariaac.menu.ui.home;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private SensorManager sensorManager;
    private Sensor accelerometro;
    private boolean agitado = false;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SupportMapFragment smf=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaFragment);
        smf.getMapAsync(new MapaActual());

        SensorManager manager=(SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensores=manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensores.size()>0){

            Sensor acelerometro=sensores.get(0);
            manager.registerListener(new LeeSensor(),acelerometro,SensorManager.SENSOR_DELAY_GAME);

        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void llamar() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            String numeroTelefono = "3471353343";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numeroTelefono));
            startActivity(intent);
        }
    }
    private class LeeSensor implements SensorEventListener {


        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
            if (acceleration > 2.0) {
                agitado = true;
                llamar();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    private class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            LatLng coordenadas = new LatLng(-32.9605166, -61.5475294);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(coordenadas).title("Inmobiliaria Labotres"));
            CameraPosition camPos=new CameraPosition.Builder()
                    .target(coordenadas)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate update= CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(update);

        }
    }
}