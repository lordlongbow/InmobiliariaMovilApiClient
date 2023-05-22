package com.example.inmobiliariaac.menu.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariaac.modelos.Contrato;
import com.example.inmobiliariaac.modelos.Inmueble;
import com.example.inmobiliariaac.modelos.Pago;
import com.example.inmobiliariaac.request.ApiClient;

import java.util.ArrayList;

public class PagosViewModel extends AndroidViewModel {
        private Context contexto;
        private MutableLiveData<ArrayList<Pago>> listaPagos;
        private MutableLiveData<Contrato> contrato;

        private ApiClient ap;


    public PagosViewModel(@NonNull Application application) {
        super(application);
        this.contexto = contexto;
        this.ap = ApiClient.getApi();
    }

    public LiveData<ArrayList<Pago>> getListaPagos(){
        if(listaPagos == null){
            listaPagos = new MutableLiveData<>();
        }
        return listaPagos;
    }

    public void recuperaPagos(Bundle bundle){
        ArrayList<Pago> listaPagosRecuperados = ap.obtenerPagos((Contrato) bundle.getSerializable("contrato"));
        this.listaPagos.setValue(listaPagosRecuperados);
    }

}