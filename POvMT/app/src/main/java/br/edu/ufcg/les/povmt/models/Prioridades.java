package br.edu.ufcg.les.povmt.models;

/**
 * Created by Treinamento Xiaomi on 14/07/2016.
 */
public class Prioridades {
    public static final int LOW = 1;
    public static final int MEDIUM = 1;
    public static final int HIGH = 1;

    private Prioridades() {}

    public static String getPrioridade(int priority) {
        String prio = "HIGH";

        switch (priority) {
            case 1: prio = "LOW"; break;
            case 2: prio = "MEDIUM"; break;
        }

        return prio;
    }
}
