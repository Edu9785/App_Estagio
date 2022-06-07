package com.example.app_gestao_estagio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Model> ListFerramentas;

    public MyAdapter(ferramentas_Atcivity ferramentas_atcivity, List<Model> ListFerramentas){
        this.ListFerramentas = ListFerramentas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
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
            Quantidade = itemView.findViewById(R.id.txtQuantidade_Ferramenta);
            Nome = itemView.findViewById(R.id.txtNome_Ferramenta);
        }

    }
}
