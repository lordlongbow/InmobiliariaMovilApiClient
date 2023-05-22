package com.example.inmobiliariaac.menu.ui.contratos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaac.modelos.Contrato;
import com.example.inmobiliariaac.modelos.Inmueble;
import com.example.inmobiliariaac.request.ApiClient;

public class ContratoDetalleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private MutableLiveData<Contrato> contrato;

    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public LiveData<Contrato> getContrato() {
        if (contrato == null) {
            contrato = new MutableLiveData<>();
        }

        return contrato;
    }

    public void recuperaInmuebleYContrato(Bundle b){
        ApiClient ap = ApiClient.getApi();
       // Inmueble inmueble = (Inmueble) b.getSerializable("inmueble");
       // this.inmueble.setValue(inmueble);
        Contrato contrato = ap.obtenerContratoVigente((Inmueble) b.getSerializable("inmueble"));
        this.contrato.setValue(contrato);
    }

}