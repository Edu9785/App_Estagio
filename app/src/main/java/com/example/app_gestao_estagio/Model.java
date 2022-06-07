package com.example.app_gestao_estagio;

public class Model {

    String Quant, nome;

    public Model(String Quant, String nome){
        this.Quant = Quant;
        this.nome = nome;
    }

    public String getQuant() {
        return Quant;
    }

    public String getNome() {
        return nome;
    }

    public void setQuant(String quant) {
        Quant = quant;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
