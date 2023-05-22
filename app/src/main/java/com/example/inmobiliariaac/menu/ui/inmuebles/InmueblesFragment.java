package com.example.inmobiliariaac.menu.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.modelos.Inmueble;

import java.util.List;

public class InmueblesFragment extends Fragment {
    private RecyclerView rv;
    private InmueblesViewModel inmueblesViewModel;
    public InmuebleAdapter adapter;
    private Context contexto;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_inmuebles, container, false);
        contexto=root.getContext();
        InmueblesFragmentInit(root);
        return root;
    }

    private void InmueblesFragmentInit(View root) {
        rv = root.findViewById(R.id.rvRecyclerView);
        inmueblesViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);

        inmueblesViewModel.getInmuebles().observe(getViewLifecycleOwner(), (Observer)(inmuebles)-> {

               GridLayoutManager glm=new GridLayoutManager(contexto,1, RecyclerView.VERTICAL,false);
               rv.setLayoutManager(glm);
               adapter = new InmuebleAdapter(contexto, (List<Inmueble>) inmuebles, getLayoutInflater());
               rv.setAdapter(adapter);

       } );
        inmueblesViewModel.obtenerInmuebles();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inmueblesViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);
        // TODO: Use the ViewModel
    }

}