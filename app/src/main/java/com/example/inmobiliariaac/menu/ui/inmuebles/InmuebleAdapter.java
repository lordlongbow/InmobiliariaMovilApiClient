package com.example.inmobiliariaac.menu.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.modelos.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {
    private Context contexto;
    private List<Inmueble> inmuebles;
    private LayoutInflater li;
    public InmuebleAdapter(Context contexto, List<Inmueble> inmuebles, LayoutInflater li) {
        this.contexto = contexto;
        this.inmuebles = inmuebles;
        this.li = li;
    }
    //Aca se pone el item que va a inflar en el recyclerview
    @NonNull
    @Override
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.inmueble_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
       holder.tvDireccion.setText(inmuebles.get(position).getDireccion());
       holder.tvPrecio.setText(String.valueOf("$ " + inmuebles.get(position).getPrecio()));
       Glide.with(contexto).load(inmuebles.get(position).getImagen()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivInmueble);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccion, tvPrecio;
        Button btnDetalles;
        ImageView ivInmueble;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            ivInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            btnDetalles = itemView.findViewById(R.id.btnDetalleInmueble);
            btnDetalles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               Bundle buendle = new Bundle();
               Inmueble i = inmuebles.get(getAdapterPosition());
               buendle.putSerializable("inmueble", i);
               Navigation.findNavController((Activity) contexto, R.id.nav_host_fragment_content_menu).navigate(R.id.inmuebleDetalleFragment, buendle);
                }
            });
        }
    }
}
