package br.edu.ufcg.les.povmt.models;

/**
 * Created by Treinamento Xiaomi on 14/07/2016.
 */
public enum Prioridades {
    LOW (1), MEDIUM(2), HIGH(3);


    public int value;

    Prioridades(int value){
        this.value = value;
    }
}
