package com.example.app_gestao_estagio;

import java.util.ArrayList;
import java.util.List;

public class valores {

    public static List<TipoUser> getTipoList(){
        List<TipoUser> listatipo = new ArrayList<>();

        TipoUser Admin = new TipoUser();
        Admin.setTipo("Admin");
        listatipo.add(Admin);

        TipoUser Trabalhador = new TipoUser();
        Admin.setTipo("Admin");
        listatipo.add(Admin);

        return listatipo;
    }
}
