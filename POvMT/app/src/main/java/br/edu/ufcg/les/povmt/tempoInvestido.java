package br.edu.ufcg.les.povmt;

import java.util.Date;

/**
 * Created by kallynnykarlla on 15/07/16.
 */
public class tempoInvestido {

    public long criado = new Date().getTime();
    public Atividade atividade;
    public Integer hours;
    public Integer minutes;​

    public tempoInvestido() {}

    ​
    public tempoInvestido(Atividade atividade, Integer hours, Integer minutes) {
        setMinutos(minutes);
        setAtividade(atividade);
        setHours(hours);
    }

    public Integer getMinutos() {
        return this.minutes;
    }

    public Atividade getAtividade() {
        return this.atividade;
    }

    public Integer getHours() {
        return this.hours;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    ​private void setMinutos(Integer minutes) {
        this.minutes = minutes;
    }

    private void setHours(Integer hours) {
        this.hours = hours;
    }

}
