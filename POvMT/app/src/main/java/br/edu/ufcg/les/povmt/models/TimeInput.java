package br.edu.ufcg.les.povmt.models;

import com.google.firebase.database.Exclude;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Isaque on 12-Jul-16.
 */
public class TimeInput implements Comparable<TimeInput> {
    private long timestamp;
    private long time = 0;
    private Atividade atvPai;

    private TimeInput() {}

    public TimeInput(long minutos, Atividade atv){
        setDataCriacao(new Date());
        setTime(minutos);
        this.atvPai = atv;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return "Time input com duração de " + Float.toString(time) + " horas, criado em: " + dateFormat.format(getDataCriacao()); //2014/08/06 15:59:48;
    }

    //Getters and Setters
    @Exclude
    public Date getDataCriacao() { return new Date(this.timestamp); }

    public long getTime() { return time;  }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Atividade getAtvPai() { return atvPai; }

    @Exclude
    public void setDataCriacao(Date dataCriacao) {
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
