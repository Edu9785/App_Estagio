package com.example.app_gestao_estagio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Model> ListFerramentas;
    private Context context;


    public MyAdapter(Context context, List<Model> ListFerramentas){
        this.ListFerramentas = ListFerramentas;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Nome.setText(ListFerramentas.get(position).getNome());
        holder.Quantidade.setText(ListFerramentas.get(position).getQuant());

    }

    @Override
    public int getItemCount() {
        return ListFerramentas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Nome;
        TextView Quantidade;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            Nome = itemView.findViewById(R.id.lbquantidadeferramenta);
            Quantidade = itemView.findViewById(R.id.lbnomeferramenta);
        }
    }
}
