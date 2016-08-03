package br.edu.ufcg.les.povmt.models;

/**
 * Created by Isaque on 14/07/2016.
 */
public class Prioridades {
    public static final int LOW = 0;
    public static final int MEDIUM = 1;
    public static final int HIGH = 2;

    private Prioridades() {}

    public static String getPrioridade(int priority) {
        String prio = "HIGH";

        switch (priority) {
            case 0: prio = "LOW"; break;
            case 1: prio = "MEDIUM"; break;
        }

        return prio;
    }
}
