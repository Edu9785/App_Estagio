package com.example.app_gestao_estagio;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterRequesicoes extends RecyclerView.Adapter<MyAdapterRequesicoes.MyViewHolder> {

    private List<Requesicoes> ListRequesicoes;
    ferramentas_Atcivity activity;

    public MyAdapterRequesicoes(historicoFerramenta_Activity activity, List<Requesicoes> ListRequesicoes) {
        this.ListRequesicoes = ListRequesicoes;
    }

    public void MudarActivity(){
        Intent intent = new Intent(activity, historicoFerramenta_Activity.class);
        activity.startActivity(intent);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layouthistorico, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Data_Requesicao.setText(ListRequesicoes.get(position).Data_Requesicao);
        holder.Data_Devolucao.setText(ListRequesicoes.get(position).Data_Devolucao);
    }

    @Override
    public int getItemCount() {
        return ListRequesicoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Data_Requesicao;
        TextView Data_Devolucao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Requesicao = itemView.findViewById(R.id.txtRequesicao);
            Data_Devolucao = itemView.findViewById(R.id.txtDevulocao);
        }
    }
}
