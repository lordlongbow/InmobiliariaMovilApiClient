package com.example.inmobiliariaac.menu.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.menu.ui.inmuebles.InmueblesViewModel;
import com.example.inmobiliariaac.modelos.Inmueble;

import java.util.ArrayList;

public class ContratosFragment extends Fragment {

    private ContratosViewModel mViewModel;
    private RecyclerView rv;
    private ContratosAdapter adapter;
    private Context contexto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contratos, container, false);
        contexto = root.getContext();
        ContratosFragmentInit(root);

        return root;
    }

    private void ContratosFragmentInit(View root) {
    rv = root.findViewById(R.id.rvRecyclerContratos);
    mViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);
    mViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
        @Override
        public void onChanged(ArrayList<Inmueble> inmuebles) {
            GridLayoutManager glm = new GridLayoutManager(contexto, 1, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(glm);
            adapter = new ContratosAdapter(contexto, inmuebles, getLayoutInflater());
            rv.setAdapter(adapter);
        }
    });

     mViewModel.obtenerPropiedadesAlquiladas();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);
        // TODO: Use the ViewModel
    }

}