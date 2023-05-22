package com.example.inmobiliariaac.menu.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariaac.modelos.Inmueble;
import com.example.inmobiliariaac.request.ApiClient;

import java.util.ArrayList;
import java.util.List;


public class InmueblesViewModel extends AndroidViewModel {

    private Context contexto;
    private ApiClient ap;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if(inmuebles==null){
            inmuebles=new MutableLiveData<>();
        }
        return inmuebles;
    }

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        contexto=application.getApplicationContext();
        ap = ApiClient.getApi();
    }

    public void obtenerInmuebles(){
        ArrayList<Inmueble> inmuebles = ap.obtnerPropiedades();
        this.inmuebles.setValue(inmuebles);
        Log.d("Inmuebles", inmuebles.toString());
    }
}