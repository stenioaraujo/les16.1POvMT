package br.edu.ufcg.les.povmt.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by stenio on 7/16/2016.
 */
public class UserData {
    private List<Atividade> atividades;
    private List<TimeInput> timeInputs;
    private String nome;
    private Date lastLogin;
    private long lastLoginTimestamp;
    private String uid;

    private UserData() {}

    public UserData(String uid) {
        this.uid = uid;
        atividades = new ArrayList<>();
        timeInputs = new ArrayList<>();
    }

    public List<Atividade> getAtividades() {
        return this.atividades;
    }

    public List<TimeInput> getTimeInputs() { return this.timeInputs; }

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
        this.atividades = atividades;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        this.lastLoginTimestamp = this.lastLogin.getTime();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
