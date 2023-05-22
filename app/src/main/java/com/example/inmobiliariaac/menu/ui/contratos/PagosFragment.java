package com.example.inmobiliariaac.menu.ui.contratos;

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
import com.example.inmobiliariaac.modelos.Pago;

import java.util.ArrayList;

public class PagosFragment extends Fragment {

    private PagosViewModel mViewModel;
    private RecyclerView rv;
    private PagosAdapter adapter;
    private Context Contexto;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pagos, container, false);
        PagosFragmentInit(root);
        return root;
                }

    private void PagosFragmentInit(View root) {
    rv = root.findViewById(R.id.rvPagos);
    mViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
    mViewModel.getListaPagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
        @Override
        public void onChanged(ArrayList<Pago> pago) {
            GridLayoutManager glm = new GridLayoutManager(Contexto, 1, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(glm);
            adapter = new PagosAdapter(Contexto, pago, getLayoutInflater());
            rv.setAdapter(adapter);
        }
    });
mViewModel.recuperaPagos(getArguments());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
        // TODO: Use the ViewModel
    }

}