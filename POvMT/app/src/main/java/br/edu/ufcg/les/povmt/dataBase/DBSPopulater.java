package br.edu.ufcg.les.povmt.database;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;

import br.edu.ufcg.les.povmt.models.Atividade;

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
        long keyAtividade1 = mDb.addAtividade(Atividade);

        Atividade task1 = new Atividade(keyAtividade1, "Task1", "Task1 description", 100, 1);

        Atividade task2 = new Atividade(keyAtividade1, "Task2", "Task2 description", 12, 2);

        Atividade task3 = new Atividade(keyAtividade1, "Task3", "Task3 description", 20, 3);

        Atividade task4 = new Atividade(keyAtividade1, "Task4", "Task4 description", 15, 1);

        mDb.addAtividade(task1);
        mDb.addAtividade(task2);
        mDb.addAtividade(task3);
        mDb.addAtividade(task4);

    }
}
