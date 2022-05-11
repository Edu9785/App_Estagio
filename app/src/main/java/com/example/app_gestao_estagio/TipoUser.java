package com.example.app_gestao_estagio;

import java.io.Serializable;

public class TipoUser implements Serializable {
    private String Tipo;

    public TipoUser(){
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

}
