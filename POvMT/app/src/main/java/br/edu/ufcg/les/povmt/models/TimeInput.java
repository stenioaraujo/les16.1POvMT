package br.edu.ufcg.les.povmt.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Isaque on 12-Jul-16.
 */
public class TimeInput {

    private Date dataCriacao;
    private float time = 0;
    private Atividade atvPai;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



    public TimeInput(float timeInput, Atividade atv){
        this.dataCriacao = new Date();
        this.time = timeInput;
        this.atvPai = atv;
    }


    @Override
    public String toString() {
        return "Time input com duração de " + Float.toString(time) + " horas, criado em: " + dateFormat.format(dataCriacao); //2014/08/06 15:59:48;
    }

    //Getters and Setters
    public float getTime() {
        return time;
    }
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;

    }

    public void setTime(float time) {
        this.time = time;
    }
}
