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
    private float totalTime;
    private int priority;

    public Atividade(){
        this.name = "Lorem";
        this.description = "Ipsum";
        this.priority = Prioridades.MEDIUM;
    }

    //Getters and Setters
    public float getTotalTime() {
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

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) { return this.getName().equals(o); }
}
