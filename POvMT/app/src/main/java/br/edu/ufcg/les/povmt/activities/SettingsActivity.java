package br.edu.ufcg.les.povmt.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.datahandlers.DAO;

/**
 * Created by stenio on 7/16/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    Button back;
    Switch notificacao;
    TextView textClockNotificacao;
    Context context = this;
    DAO dao;
    View dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dao = DAO.getInstance();

        back = (Button) findViewById(R.id.back_button);
        notificacao = (Switch) findViewById(R.id.notificacaoSwitch);
        textClockNotificacao = (TextView) findViewById(R.id.textClockNotificacao);

        notificacao.setChecked(dao.isNotificationOn());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });

        notificacao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkVisibilidadeNotificacao();
            }
        });

        textClockNotificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_clock);
                dialog.setTitle("Hora da Notificação");

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);

                final TimePicker timePicker  = (TimePicker) dialog.findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);
                timePicker.setCurrentHour(dao.getNotificationHour());
                timePicker.setCurrentMinute(dao.getNotificationMinute());

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(timePicker != null) {
                            dao.setNotificationTime(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                            dao.update();

                            textClockNotificacao.setText(
                                    String.format("%02d", timePicker.getCurrentHour()) + ":"
                                            + String.format("%02d", timePicker.getCurrentMinute()));
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });

        checkVisibilidadeNotificacao();
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkVisibilidadeNotificacao();
    }

    private void checkVisibilidadeNotificacao() {
        if (notificacao != null && notificacao.isChecked()) {
            textClockNotificacao.setVisibility(View.VISIBLE);
            textClockNotificacao.setText(String.format("%02d", dao.getNotificationHour()) + ":"
            + String.format("%02d", dao.getNotificationMinute()));
        } else {
            textClockNotificacao.setVisibility(View.INVISIBLE);
        }

        dao.setNotificationOn(notificacao.isChecked());
        dao.update();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
