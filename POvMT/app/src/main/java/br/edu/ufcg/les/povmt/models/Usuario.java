package br.edu.ufcg.les.povmt.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by stenio on 7/16/2016.
 */
public class Usuario {
    private ArrayList<Atividade> atividades;
    private String nome;
    private Date lastLogin;
    private String uid;

    private Usuario() {}

    public Usuario(String uid) {
        this.uid = uid;
    }

    public ArrayList<Atividade> getAtividades() {
        return this.atividades;
    }

    public String getNome() {
        return this.nome;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }

    public String getUid() {
        return uid;
    }

    public void setAtividades(ArrayList<Atividade> atividades) {
        this.atividades = atividades;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
