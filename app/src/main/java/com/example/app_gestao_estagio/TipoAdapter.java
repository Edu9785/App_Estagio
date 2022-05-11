 package com.example.app_gestao_estagio;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class TipoAdapter extends BaseAdapter {
    private Context context;
    private List<TipoUser> tipoList;

    public TipoAdapter(Context context, List<TipoUser> tipoList){
        this.context = context = context;
        this.tipoList = tipoList;
    }
    @Override
    public int getCount() {
        return tipoList != null ? tipoList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
