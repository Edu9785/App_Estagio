package com.example.app_gestao_estagio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TouchHelper extends ItemTouchHelper.SimpleCallback {


    public TouchHelper(MyAdapter adapter) {
        super(0, ItemTouchHelper.LEFT);
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        TextView txtnome;
        txtnome = viewHolder.itemView.findViewById(R.id.lbnomeferramenta);
        String nome = txtnome.getText().toString();
        if(direction == ItemTouchHelper.LEFT){
            Intent intent = new Intent(viewHolder.itemView.getContext(), historicoFerramenta_Activity.class);
            Log.i("lfe", "onSwipedWEEB: " + nome);
            intent.putExtra("nome", nome);
            viewHolder.itemView.getContext().startActivity(intent);
        }
    }
}
