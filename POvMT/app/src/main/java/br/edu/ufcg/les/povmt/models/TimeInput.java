package br.edu.ufcg.les.povmt.models;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Isaque on 12-Jul-16.
 */
public class TimeInput implements Comparable<TimeInput> {

    private Date dataCriacao;
    private long timestamp;
    private long time = 0;
    private Atividade atvPai;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private TimeInput() {}

    public TimeInput(long minutos, Atividade atv){
        setDataCriacao(new Date());
        setTime(minutos);
        this.atvPai = atv;
    }

    @Override
    public String toString() {
        return "Time input com duração de " + Float.toString(time) + " horas, criado em: " + dateFormat.format(dataCriacao); //2014/08/06 15:59:48;
    }

    public Date getDataCriacao() { return dataCriacao; }

    //Getters and Setters

    public long getTime() { return time;  }

    public Atividade getAtvPai() { return atvPai; }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
        this.timestamp = dataCriacao.getTime();
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int compareTo(TimeInput another) {
        return this.getDataCriacao().compareTo(another.getDataCriacao());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass())
            return false;

        TimeInput ti2 = (TimeInput) o;

        return ti2.getAtvPai().equals(this.getAtvPai()) &&
                ti2.getDataCriacao().equals(this.getDataCriacao());
    }
}
