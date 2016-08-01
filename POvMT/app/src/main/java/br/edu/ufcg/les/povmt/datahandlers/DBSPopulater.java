package br.edu.ufcg.les.povmt.datahandlers;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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



        DateTime dt = new DateTime();

        TimeInput timeInput1  = new TimeInput(120, task1);
        timeInput1.setDataCriacao(dt.minusDays(7).toDate());

        TimeInput timeInput2  = new TimeInput(150, task1);
        timeInput2.setDataCriacao(dt.minusDays(3).toDate());

        TimeInput timeInput3  = new TimeInput(200, task2);
        timeInput3.setDataCriacao(dt.minusDays(5).toDate());

        TimeInput timeInput4  = new TimeInput(240, task2);
        timeInput4.setDataCriacao(dt.minusDays(7).toDate());

        TimeInput timeInput5  = new TimeInput(50, task3);
        timeInput5.setDataCriacao(dt.minusDays(14).toDate());

        TimeInput timeInput6  = new TimeInput(30, task3);
        timeInput6.setDataCriacao(dt.minusDays(16).toDate());

        TimeInput timeInput7  = new TimeInput(60, task4);
        timeInput7.setDataCriacao(dt.minusDays(10).toDate());

        TimeInput timeInput8  = new TimeInput(80, task4);
        timeInput8.setDataCriacao(dt.minusDays(20).toDate());

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
        dao.update();
    }
}
