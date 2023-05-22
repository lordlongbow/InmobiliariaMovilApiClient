package com.example.inmobiliariaac.menu.ui.logout;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliariaac.R;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        alertDialog();

        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        // TODO: Use the ViewModel
    }

    public void alertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getContext())
                .setTitle(" Cerrar Sesión")
                .setMessage("¿Desea cerrar sesión?").setPositiveButton("Si", (dialog, which) -> {
                    System.exit(0);
                }).setNegativeButton("No", (dialog, which) -> {
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu).navigate(R.id.nav_home);
                });
        alertDialog.show();
    }
}