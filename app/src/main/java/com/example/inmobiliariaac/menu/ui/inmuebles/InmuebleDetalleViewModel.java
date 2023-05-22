package com.example.inmobiliariaac.menu.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.inmobiliariaac.modelos.Inmueble;

public class InmuebleDetalleViewModel extends AndroidViewModel {
  private MutableLiveData<Inmueble> inmueble;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void recuperaInmueble(Bundle b){
        Inmueble inmueble = (Inmueble) b.getSerializable("inmueble");
        this.inmueble.setValue(inmueble);
    }
}