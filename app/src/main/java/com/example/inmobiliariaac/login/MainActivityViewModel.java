package com.example.inmobiliariaac.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliariaac.menu.MenuActivity;
import com.example.inmobiliariaac.modelos.Propietario;
import com.example.inmobiliariaac.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context contexto;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        contexto = application.getApplicationContext();
    }

public void login (String mail, String password){
    ApiClient apiClient = ApiClient.getApi();
    Propietario propietario = apiClient.login(mail, password);
    if(propietario!=null){
        propietario= apiClient.obtenerUsuarioActual();
        Intent intent = new Intent(contexto, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("login", true);
        contexto.startActivity(intent);
    }
}


}
