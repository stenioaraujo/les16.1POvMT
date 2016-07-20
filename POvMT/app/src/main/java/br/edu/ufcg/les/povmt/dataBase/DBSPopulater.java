package br.edu.ufcg.les.povmt.database;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;

import br.edu.ufcg.les.povmt.datahandlers.DAO;
import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.TimeInput;

/**
 * Created by kallynnykarlla on 19/07/16.
 */

public class DBSPopulater {

    /** The m db. */
    private static DAO dao;

    /**
     * Instantiates a new DBS populater.
     *
     *
     */
    public DBSPopulater(DAO dao) {

        this.dao = dao;
    }

    /**
     * Populate bd.
     */
    public static void populateBD() {

        Atividade task1 = new Atividade();
        task1.setName("Task1");
        task1.setPriority(1);

        Atividade task2 = new Atividade();
        task2.setName("Task2");
        task2.setPriority(3);

        Atividade task3 = new Atividade();
        task3.setName("Task3");
        task3.setPriority(1);

        Atividade task4 = new Atividade();
        task4.setName("Task4");
        task4.setPriority(2);


        TimeInput timeInput1  = new TimeInput(120, task1);
        TimeInput timeInput2  = new TimeInput(150, task1);

        TimeInput timeInput3  = new TimeInput(200, task2);
        TimeInput timeInput4  = new TimeInput(240, task2);

        TimeInput timeInput5  = new TimeInput(50, task3);
        TimeInput timeInput6  = new TimeInput(30, task3);

        TimeInput timeInput7  = new TimeInput(60, task4);
        TimeInput timeInput8  = new TimeInput(80, task4);

        dao.add(task1);
        dao.add(task2);
        dao.add(task3);
        dao.add(task4);

        dao.add(timeInput1);
        dao.add(timeInput2);
        dao.add(timeInput3);
        dao.add(timeInput4);
        dao.add(timeInput5);
        dao.add(timeInput6);
        dao.add(timeInput7);
        dao.add(timeInput8);



    }
}
