package com.example.inmobiliariaac.menu.ui.contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaac.modelos.Contrato;
import com.example.inmobiliariaac.modelos.Inmueble;
import com.example.inmobiliariaac.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends AndroidViewModel {

    private Context contexto;
    private ApiClient ap;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        this.contexto = contexto;
        this.ap = ApiClient.getApi();
    }

    public LiveData getInmuebles() {
        if(inmuebles == null){
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void obtenerPropiedadesAlquiladas(){
        ArrayList<Inmueble> propiedades = ap.obtenerPropiedadesAlquiladas();
        this.inmuebles.setValue(propiedades);
    }
}