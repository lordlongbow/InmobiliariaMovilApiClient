package com.example.inmobiliariaac.menu.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaac.modelos.Propietario;
import com.example.inmobiliariaac.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private Context contexto;
    private MutableLiveData<Propietario> propietarioData;
    private MutableLiveData<Boolean> estados;
    private MutableLiveData<String> txtBoton;
    private  ApiClient apiClient;

    public LiveData<Propietario> getPropietarioData() {

        if(propietarioData == null){
            propietarioData = new MutableLiveData<>();
        }
        return propietarioData;
    }
        public LiveData<Boolean> getEstados() {
        if (estados == null) {
            estados = new MutableLiveData<>();
        }
        return estados;
        }

        public LiveData<String> getTxtBoton() {
        if (txtBoton == null) {
            txtBoton = new MutableLiveData<>();
        }
        return txtBoton;
        }

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        contexto = application.getApplicationContext();
        apiClient = new ApiClient().getApi();
    }

public void leerPropietario(){
    propietarioData.setValue(apiClient.obtenerUsuarioActual());
}

public void cambioEstadoBoton(String txBoton, Propietario p){

        if(txBoton.equals("Editar")){
            txtBoton.setValue("Guardar");
            estados.setValue(true);

        }else{
            txtBoton.setValue("Editar");
            estados.setValue(false);
apiClient.actualizarPerfil(p);
        }
}

}