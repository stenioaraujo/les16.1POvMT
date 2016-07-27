package br.edu.ufcg.les.povmt.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
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
        this.atividades = new LinkedList<Atividade>();
        this.timeInputs = new LinkedList<TimeInput>();
    }

    public List<Atividade> getAtividades() {
        //List<Atividade> result = new ArrayList<Atividade>(Arrays.asList(atividades));

        return this.atividades;
    }

    public List<TimeInput> getTimeInputs() {
        //List<TimeInput> result = new ArrayList<TimeInput>(Arrays.asList(timeInputs));

        return this.timeInputs;
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

        this.atividades = new LinkedList<Atividade>(atividades);
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        this.lastLoginTimestamp = this.lastLogin.getTime();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTimeInputs(List<TimeInput> timeInputs) {
        this.timeInputs = new LinkedList<TimeInput>(timeInputs);
    }
}
