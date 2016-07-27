package br.edu.ufcg.les.povmt.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by stenio on 7/16/2016.
 */
public class UserData {
    private Atividade[] atividades;
    private TimeInput[] timeInputs;
    private String nome;
    private Date lastLogin;
    private long lastLoginTimestamp;
    private String uid;

    private UserData() {}

    public UserData(String uid) {
        this.uid = uid;
        this.atividades = new Atividade[0];
        this.timeInputs = new TimeInput[0];
    }

    public List<Atividade> getAtividades() {
        List<Atividade> result = new ArrayList<Atividade>(Arrays.asList(atividades));

        return result;
    }

    public List<TimeInput> getTimeInputs() {
        List<TimeInput> result = new ArrayList<TimeInput>(Arrays.asList(timeInputs));

        return result;
    }

    public String getNome() {
        return this.nome;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }

    public long getLastLoginTimestamp() { return this.lastLoginTimestamp; }

    public String getUid() {
        return uid;
    }

    public void setAtividades(List<Atividade> atividades) {
        Atividade [] input = atividades.toArray(new Atividade[0]);

        this.atividades = input;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        this.lastLoginTimestamp = this.lastLogin.getTime();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTimeInputs(List<TimeInput> timeInputs) {
        TimeInput [] input = timeInputs.toArray(new TimeInput[0]);

        this.timeInputs = input;
    }
}
