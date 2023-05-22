package com.example.inmobiliariaac.menu.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.databinding.FragmentPerfilBinding;
import com.example.inmobiliariaac.modelos.Propietario;

public class PerfilFragment extends Fragment {

    private EditText etNombrePerfil, etApellidoPerfil, etTelefonoPerfil, etEmailPerfil, etContrasenaPerfil, etDniPerfil;
    private Button btnEditarPerfil;
    private PerfilViewModel vm;


    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root =  inflater.inflate(R.layout.fragment_perfil, container, false);
        vistaInit(root);
        vm.getPropietarioData().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etNombrePerfil.setText(propietario.getNombre());
                etApellidoPerfil.setText(propietario.getApellido());
                etTelefonoPerfil.setText(propietario.getTelefono());
                etEmailPerfil.setText(propietario.getEmail());
                etContrasenaPerfil.setText(propietario.getContraseña());
                etDniPerfil.setText(String.valueOf(propietario.getDni()));

            }
        });
        vm.getEstados().observe(getViewLifecycleOwner(), new Observer<Boolean>() {

            @Override
            public void onChanged(Boolean estado) {
             etNombrePerfil.setEnabled(estado);
             etApellidoPerfil.setEnabled(estado);
             etTelefonoPerfil.setEnabled(estado);
             etEmailPerfil.setEnabled(estado);
             etContrasenaPerfil.setEnabled(estado);
             etDniPerfil.setEnabled(estado);}
        });
        vm.getTxtBoton().observe(getViewLifecycleOwner(), new Observer<String>() {
           @Override
           public void onChanged(String txtBoton) {
               btnEditarPerfil.setText(txtBoton);
           }
        });
        vm.leerPropietario();
        return root;
    }

    private void vistaInit(View root) {
        etNombrePerfil = root.findViewById(R.id.etNombrePerfil);
        etApellidoPerfil = root.findViewById(R.id.etApellidoPerfil);
        etTelefonoPerfil = root.findViewById(R.id.etTelefonoPerfil);
        etEmailPerfil = root.findViewById(R.id.etEmailPerfil);
        etContrasenaPerfil = root.findViewById(R.id.etContraseña);
        etDniPerfil = root.findViewById(R.id.etDniPerfil);
        btnEditarPerfil = root.findViewById(R.id.btnPerfil);

        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Propietario p = new Propietario();
                p.setNombre(etNombrePerfil.getText().toString());
                p.setApellido(etApellidoPerfil.getText().toString());
                p.setTelefono(etTelefonoPerfil.getText().toString());
                p.setEmail(etEmailPerfil.getText().toString());
                p.setContraseña(etContrasenaPerfil.getText().toString());
                p.setDni(Long.parseLong(etDniPerfil.getText().toString()));

                String textoBoton = btnEditarPerfil.getText().toString();

                vm.cambioEstadoBoton(textoBoton, p);
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
   }

}