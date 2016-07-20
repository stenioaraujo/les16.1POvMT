package br.edu.ufcg.les.povmt.database;

import android.content.Context;

import com.bumptech.glide.util.Util;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by kallynnykarlla on 19/07/16.
 */

public class DBSPopulater {

    /** The m db. */
    private static FirebaseAnalytics mDb;

    /**
     * Instantiates a new DBS populater.
     *
     * @param context
     *            the context
     */
    public DBSPopulater(Context context) {

        mDb = new FirebaseAnalytics(context);
    }

    /**
     * Populate bd.
     */
    public static void populateBD() {
        long keyAtividade1 = mDb.addAtividade(Atividade1);

        Task task1 = new Task(keyAtividade1, "Task1", "Task4 description", 12, "priority","[2,4,5]");

        Task task2 = new Task(keyAtividade1, "Task2", "Task4 description", 12, "priority","[2,4,5]");

        Task task3 = new Task(keyAtividade1, "Task3", "Task4 description", 12, "priority","[2,3,4,5]");

        Task task4 = new Task(keyAtividade1, "Task4", "Task4 description", 12, "priority","[2,4,5]");

        mDb.addTask(task1);
        mDb.addTask(task2);
        mDb.addTask(task3);
        mDb.addTask(task4);

    }
}
