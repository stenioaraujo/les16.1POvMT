package br.edu.ufcg.les.povmt.models;

/**
 * Created by Luiz Henrique 24/07/2017
 */
public enum TipoAtividade {
    TRABALHO("Trabalho"),
    LAZER("Lazer");

    private String tipo;

    TipoAtividade(String tipo) {
        this.tipo = tipo;
    }

    public String tipo() {
        return tipo;
    }
}
