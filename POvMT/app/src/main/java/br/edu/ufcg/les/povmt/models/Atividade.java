package br.edu.ufcg.les.povmt.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Isaque on 12-Jul-16.
 */
public class Atividade {
    private String name;
    private String description;
    private int priority;
    private Long totalTime;
    private String tipoAtividade;

    public Atividade(){
        this.name = "Lorem";
        this.description = "Ipsum";
        this.priority = Prioridades.MEDIUM;
        this.tipoAtividade = TipoAtividade.TRABALHO;
    }


    //Getters and Setters
    public Long getTotalTime() {
        return totalTime;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !o.getClass().equals(this.getClass()))
            return false;

        Atividade atv2 = (Atividade) o;
        return this.getName().equals(atv2.getName());
    }
}
