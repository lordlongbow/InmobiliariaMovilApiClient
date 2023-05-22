package com.example.inmobiliariaac.menu.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.modelos.Inmueble;

public class InmuebleDetalleFragment extends Fragment {

    private TextView tvCodigo, tvHabitaciones, tvDireccion, tvPrecio, tvTipo, tvUso;
    private ImageView ivImagenInmueble;
    private CheckBox cbDisponible;
    private InmuebleDetalleViewModel mViewModel;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inmueble_detalle, container, false);
        InmuebleFragmentInit(root);
        return root;
    }

    private void InmuebleFragmentInit(View root) {
        tvCodigo = root.findViewById(R.id.tvCodigoInmueble);
        tvHabitaciones = root.findViewById(R.id.tvHabitacionesInmueble);
        tvDireccion = root.findViewById(R.id.tvDireccionInmueble);
        tvPrecio = root.findViewById(R.id.tvPrecioInmueble);
        tvTipo = root.findViewById(R.id.tvTipo);
        tvUso = root.findViewById(R.id.tvUso);
        ivImagenInmueble = root.findViewById(R.id.ivInmuebleDetalle);
        cbDisponible = root.findViewById(R.id.cbDisponibilidad);
        mViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleDetalleViewModel.class);
        mViewModel.getInmueble().observe(getActivity(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                tvCodigo.setText(inmueble.getIdInmueble() + "");
                tvHabitaciones.setText(inmueble.getAmbientes() + "");
                tvDireccion.setText(inmueble.getDireccion());
                tvPrecio.setText("$ " + inmueble.getPrecio() + "");
                tvTipo.setText(inmueble.getTipo() + " ");
                tvUso.setText(inmueble.getUso() + "");
                cbDisponible.setChecked(inmueble.isEstado());
                Glide.with(getContext()).load(inmueble.getImagen()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivImagenInmueble);
            }

        });

        mViewModel.recuperaInmueble(getArguments());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}