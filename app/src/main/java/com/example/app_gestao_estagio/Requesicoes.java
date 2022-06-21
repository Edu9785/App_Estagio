package com.example.app_gestao_estagio;

public class Requesicoes {
    String Data_Requesicao, Data_Devolucao;
    public Requesicoes(String Data_Requesicao, String Data_Devolucao){
        this.Data_Devolucao = Data_Devolucao;
        this.Data_Requesicao = Data_Requesicao;
    }

    public String getData_Devolucao() {
        return Data_Devolucao;
    }

    public String getData_Requesicao() {
        return Data_Requesicao;
    }

    public void setData_Devolucao(String data_Devolucao) {
        Data_Devolucao = data_Devolucao;
    }

    public void setData_Requesicao(String data_Requesicao) {
        Data_Requesicao = data_Requesicao;
    }
}
