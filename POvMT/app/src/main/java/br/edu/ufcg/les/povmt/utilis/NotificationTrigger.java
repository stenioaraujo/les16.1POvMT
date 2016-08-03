package br.edu.ufcg.les.povmt.utilis;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.activities.SettingsActivity;
import br.edu.ufcg.les.povmt.activities.YesterdayTiActivity;

/**
 * Created by Victor on 02-Aug-16.
 */
public class NotificationTrigger extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("Notifications", "Broadcast");
        SharedPreferences prefs = context.getSharedPreferences(SettingsActivity.MY_PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.getBoolean("isnotficationon", true) && !prefs.getBoolean("hasyesterdayti", true)) {

            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent it = new Intent(context, YesterdayTiActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), it, 0);
            Intent it2 = new Intent(context, SettingsActivity.class);
            PendingIntent pIntent2 = PendingIntent.getActivity(context, (int) System.currentTimeMillis()+5, it2, 0);

            Notification n = new Notification.Builder(context)
                    .setContentTitle("Sentimos sua falta")
                    .setContentText("Você não cadastrou uma ti ontem !")
                    .setSmallIcon(R.drawable.ic_notf)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true)
                    .addAction(R.drawable.ic_menu_send, "Cadastrar ti", pIntent)
                    .addAction(R.drawable.ic_edit, "Configurações", pIntent2).build();

            notificationManager.notify(0, n);

            Log.i("Notifications", "Notification created");
        }
    }
}
