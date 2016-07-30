package br.edu.ufcg.les.povmt.models;

import com.google.firebase.database.Exclude;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by stenio on 7/16/2016.
 */
public class UserData {
    private Map<String, Atividade> atividades;
    private Map<String, TimeInput> timeInputs;
    private String nome;
    private String uid;

    private UserData() {}

    public UserData(String uid) {
        this.uid = uid;
        atividades = new HashMap<>();
        timeInputs = new HashMap<>();
    }

    public Map<String, Atividade> getAtividades() {
        return this.atividades;
    }

    public Map<String, TimeInput> getTimeInputs() { return this.timeInputs; }

    public String getNome() {
        return this.nome;
    }

    public String getUid() {
        return uid;
    }

    public void setAtividades(Map<String, Atividade> atividades) {
        this.atividades = atividades;
    }

    public void setTimeInputs(Map<String, TimeInput> timeInputs) { this.timeInputs = timeInputs; }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
