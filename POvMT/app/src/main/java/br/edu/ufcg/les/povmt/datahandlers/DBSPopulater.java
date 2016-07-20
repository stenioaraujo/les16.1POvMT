package br.edu.ufcg.les.povmt.datahandlers;

import java.util.Date;

import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.TimeInput;

/**
 * Created by kallynnykarlla on 19/07/16.
 */

public class DBSPopulater {

    /**
     * Populate bd.
     */
    public static void populateBD() {

        DAO dao = DAO.getInstance();

        Atividade task1 = new Atividade();
        task1.setName("Task1");
        task1.setPriority(1);

        Atividade task2 = new Atividade();
        task2.setName("Task2");
        task2.setPriority(0);

        Atividade task3 = new Atividade();
        task3.setName("Task3");
        task3.setPriority(1);

        Atividade task4 = new Atividade();
        task4.setName("Task4");
        task4.setPriority(2);

        TimeInput timeInput1  = new TimeInput(120, task1);
        timeInput1.setDataCriacao(new Date(0));
        TimeInput timeInput2  = new TimeInput(150, task1);
        timeInput2.setDataCriacao(new Date(100));

        TimeInput timeInput3  = new TimeInput(200, task2);
        timeInput3.setDataCriacao(new Date(200));
        TimeInput timeInput4  = new TimeInput(240, task2);
        timeInput4.setDataCriacao(new Date(300));

        TimeInput timeInput5  = new TimeInput(50, task3);
        timeInput5.setDataCriacao(new Date(400));
        TimeInput timeInput6  = new TimeInput(30, task3);
        timeInput6.setDataCriacao(new Date(500));

        TimeInput timeInput7  = new TimeInput(60, task4);
        timeInput7.setDataCriacao(new Date(600));
        TimeInput timeInput8  = new TimeInput(80, task4);
        timeInput8.setDataCriacao(new Date(700));

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
