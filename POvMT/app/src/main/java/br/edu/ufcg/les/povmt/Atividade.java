package br.edu.ufcg.les.povmt;

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

    Atividade(){
        this.name = "Lorem";
        this.description = "Ipsum";
        timeInputs = new ArrayList<>();
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


}
