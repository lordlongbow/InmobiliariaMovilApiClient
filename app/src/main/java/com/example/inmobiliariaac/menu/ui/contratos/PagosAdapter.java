package com.example.inmobiliariaac.menu.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariaac.R;
import com.example.inmobiliariaac.modelos.Pago;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder>{
    private Context contexto;
    private ArrayList<Pago> listaPagos;
    private LayoutInflater li;


    public PagosAdapter(Context contexto, ArrayList<Pago> listaPagos, LayoutInflater li) {
        this.contexto = contexto;
        this.listaPagos = listaPagos;
        this.li = li;
    }

    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.pagos_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {
        holder.tvContrato.setText(listaPagos.get(position).getContrato().getIdContrato() + "");
        holder.tvIdPago.setText(String.valueOf(listaPagos.get(position).getIdPago() + ""));
        holder.tvFecha.setText(listaPagos.get(position).getFechaDePago() + "");
        holder.tvImporte.setText(String.valueOf(listaPagos.get(position).getImporte()));
        holder.tvNumero.setText(String.valueOf(listaPagos.get(position).getNumero()));
    }

    @Override
    public int getItemCount() {
        return listaPagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdPago, tvNumero, tvContrato, tvImporte, tvFecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdPago = itemView.findViewById(R.id.tvIdPago);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvContrato = itemView.findViewById(R.id.tvContrato);
            tvImporte = itemView.findViewById(R.id.tvImporte);
            tvFecha = itemView.findViewById(R.id.tvFechaDePago);

        }
    }
}
