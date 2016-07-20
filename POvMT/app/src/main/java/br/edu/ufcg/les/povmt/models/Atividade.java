package br.edu.ufcg.les.povmt.models;

import java.util.ArrayList;

/**
 * Created by Isaque on 12-Jul-16.
 */
public class Atividade {

    private String name;
    private String description;
    private float totalTime;
    private Enum<Prioridades> priority;
    private ArrayList<TimeInput> timeInputs;

    public Atividade(){
        this.name = "Lorem";
        this.description = "Ipsum";
        this.timeInputs = new ArrayList<>();
        this.totalTime = totalTime();
        this.priority = Prioridades.MEDIUM;
    }

    private float totalTime() {
        int totalTime = 0;
        for (int i=0; i<timeInputs.size(); i++){
            totalTime += timeInputs.get(i).getTime();
        }
        return totalTime;
    }

    public void addTimeInput(int time){
        timeInputs.add(new TimeInput(time, this));
    }

    //Ordena as atividades de acordo com os últimos timeInputs
    public int compareTo(Atividade atv)
    {
        if (this.timeInputs.get(-1).getDataCriacao().after(atv.timeInputs.get(-1).getDataCriacao())){
            return 1;
        } else
            return -1;
    }

    //Getters and Setters
    public float getTotalTime() {
        return totalTime;
    }

    public Enum<Prioridades> getPriority() {
        return priority;
    }

    public ArrayList<TimeInput> getTimeInputs() {
        return timeInputs;
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

    public void setPriority(Enum<Prioridades> priority) {
        this.priority = priority;
    }

    public void setTimeInputs(ArrayList<TimeInput> timeInputs) {
        this.timeInputs = timeInputs;
    }
}
