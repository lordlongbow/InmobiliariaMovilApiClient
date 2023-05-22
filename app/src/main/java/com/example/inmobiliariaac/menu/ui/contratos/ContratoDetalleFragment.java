package com.example.inmobiliariaac.menu.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.menu.ui.inmuebles.InmuebleDetalleViewModel;
import com.example.inmobiliariaac.modelos.Contrato;
import com.example.inmobiliariaac.modelos.Inmueble;

public class ContratoDetalleFragment extends Fragment {

    private TextView tvCodigo, tvDireccion, tvInquilino, tvFechaInicio, tvFechaFin;
    private Button btnPagos;
    private ContratoDetalleViewModel mViewModel;
private Context contexto;
    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contrato_detalle, container, false);
        ContratoDetalleFragmentInit(root);

        return root; }

    private void ContratoDetalleFragmentInit(View root) {
    tvCodigo = root.findViewById(R.id.tvCodigoContratoDetalles);
    tvDireccion = root.findViewById(R.id.tvDireccionContratoDetalles);
    tvInquilino = root.findViewById(R.id.tvInquilinoContratoDetalles);
    tvFechaFin = root.findViewById(R.id.tvFechadeFinalizacionContrato);
    tvFechaInicio = root.findViewById(R.id.tvFechaInicioDetallesContrato);
    btnPagos = root.findViewById(R.id.btnPagos);
    mViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoDetalleViewModel.class);

    mViewModel.getContrato().observe(getActivity(), new Observer<Contrato>() {
        @Override
        public void onChanged(Contrato contrato) {
            tvCodigo.setText(contrato.getIdContrato() +"");
            tvDireccion.setText(contrato.getInmueble().getDireccion());
            tvInquilino.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
            tvFechaInicio.setText(contrato.getFechaInicio());
            tvFechaFin.setText(contrato.getFechaFin());
        }
    });
    btnPagos.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Contrato c = new Contrato();
            c.setIdContrato(Integer.parseInt(tvCodigo.getText().toString()));
            Bundle bundle = new Bundle();
            bundle.putSerializable("contrato", c);

           Navigation.findNavController(v).navigate(R.id.action_contratoDetalleFragment_to_pagosFragment, bundle);
        }
    });

    mViewModel.recuperaInmuebleYContrato(getArguments());


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}