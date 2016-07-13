package br.edu.ufcg.les.povmt;

import java.util.ArrayList;

/**
 * Created by Isaque on 12-Jul-16.
 */
public class Atividade {

    private String name;
    private String description;
    private float totalTime;
    private ArrayList<TimeInput> timeInputs = new ArrayList<>();

    Atividade(){
        this.name = "Lorem";
        this.description = "Ipsum";
        this.totalTime = totalTime();
    }

    private float totalTime() {
        int totalTime = 0;
        for (int i=0; i<timeInputs.size(); i++){
            totalTime += timeInputs.get(i).getTime();
        }
        return totalTime;
    }


}
