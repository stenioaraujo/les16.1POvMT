package br.edu.ufcg.les.povmt.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.adapters.AtividadeRecyclerAdapter;
import br.edu.ufcg.les.povmt.datahandlers.DAO;
import br.edu.ufcg.les.povmt.fragments.TabFragment1;
import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.AtividadeView;
import br.edu.ufcg.les.povmt.models.TimeInput;

public class YesterdayTiActivity extends AppCompatActivity {

    private AutoCompleteTextView edtDesc;
    private EditText edtH;
    private EditText edtM;
    private DAO dao;
    private Button baixa;
    private Button media;
    private Button alta;
    private int prioridade = 0;
    private EditText nome;
    private AtividadeView atv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesterday_ti);


        fragmentInit();
        dialogInit();

        selecionaPrioridadeBotao(prioridade);
    }

    private void fragmentInit() {
        edtDesc = (AutoCompleteTextView) findViewById(R.id.edtDesc);
        edtH = (EditText) findViewById(R.id.edtH);
        edtM = (EditText) findViewById(R.id.edtM);


        dao = DAO.getInstance();
        List<AtividadeView> tis = dao.getAtividadeViews(this, new Date(0), new Date());

        List<String> names = new ArrayList<String>();
        for (AtividadeView av : tis) {
            names.add(av.getTxtName().getText() + "");
        }
        updateAutoComplete(names);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTis(prioridade, edtDesc.getText() + "");
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.layout), "Ti adicionado !", Snackbar.LENGTH_LONG).setAction("Sair", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                startActivity(new Intent(v.getContext(), MainActivity.class));
                            }
                        });

                snackbar.show();
            }
        });


    }

    public void addTis(int priority, String nome) {
        if (atv == null) {
            AtividadeView ti = new AtividadeView(this);
            String h = edtH.getText().toString();
            String m = edtM.getText().toString();
            if (h.equals("")) h = "0";
            if (m.equals("")) m = "0";
            ti.set(h, m, edtDesc.getText().toString(), priority);

            Atividade atv = dao.getAtividade(ti.getTxtName().getText() + "");
            if (atv == null) {
                atv = new Atividade();
                atv.setName(ti.getTxtName().getText() + "");
                atv.setPriority(ti.getPriorityId());

            }
            ti.setAtividade(atv);

            Long hora = Long.parseLong(h);
            Long min = Long.parseLong(m);
            TimeInput timeInput = new TimeInput(hora * 60 + min, atv);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(cal.getTimeInMillis() - 24 * 60 * 60 * 1000);
            timeInput.setDataCriacao(cal.getTime());

            dao.add(atv);
            dao.add(timeInput);
            dao.update();


            edtDesc.setText("");
            edtH.setText("");
            edtM.setText("");
        } else {
            AtividadeView ti = new AtividadeView(this);
            Atividade antiga = dao.getAtividade(atv.getTxtName().getText() + "");
            String h = atv.getTxtHour().getText() + "";
            String m = atv.getTxtMin().getText() + "";
            ti.set(h, m, nome, priority);

            Atividade atv = new Atividade();

            atv.setName(ti.getTxtName().getText() + "");
            atv.setPriority(ti.getPriorityId());
            ti.setAtividade(atv);

            Long hora = Long.parseLong(h);
            Long min = Long.parseLong(m);
            //TimeInput timeInput = new TimeInput(hora*60 + min, atv);

            dao.updateAtividade(antiga, ti.getAtividade());
            //dao.add(timeInput);
            dao.update();

            edtDesc.setText("");
            edtH.setText("");
            edtM.setText("");
        }

        SharedPreferences.Editor editor = this.getSharedPreferences(SettingsActivity.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean("hasyesterdayti", true);
        editor.putInt("lasttiday", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        editor.commit();
    }

    private void dialogInit() {
        baixa = (Button) findViewById(R.id.button_baixa);
        media = (Button) findViewById(R.id.button_media);
        alta = (Button) findViewById(R.id.button_alta);
        nome = (EditText) findViewById(R.id.nomeAtividade);


        baixa.setOnClickListener(onBtClicktipo);
        media.setOnClickListener(onBtClicktipo);
        alta.setOnClickListener(onBtClicktipo);


    }

    private void selecionaPrioridadeBotao(int prioridade) {
        if (prioridade == 0) {
            selecionarPrioridade(R.id.button_baixa);
        } else if (prioridade == 1) {
            selecionarPrioridade(R.id.button_media);
        } else if (prioridade == 2) {
            selecionarPrioridade(R.id.button_alta);
        }
    }

    private Button.OnClickListener onBtClicktipo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selecionarPrioridade(view.getId());

        }
    };


    private void selecionarPrioridade(int id) {
        switch (id) {
            case R.id.button_baixa:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    baixa.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_action_name));
                } else {
                    baixa.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_action_name));
                }
                media.setBackgroundColor(getResources().getColor(R.color.priortyMediumColor));
                alta.setBackgroundColor(getResources().getColor(R.color.priortyHighColor));

                prioridade = 0;
                break;
            case R.id.button_media:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    media.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_action_name));
                } else {
                    media.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_action_name));
                }
                baixa.setBackgroundColor(getResources().getColor(R.color.priortyLowColor));
                alta.setBackgroundColor(getResources().getColor(R.color.priortyHighColor));
                prioridade = 1;
                break;
            case R.id.button_alta:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    alta.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_action_name));
                } else {
                    alta.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_action_name));
                }
                media.setBackgroundColor(getResources().getColor(R.color.priortyMediumColor));
                baixa.setBackgroundColor(getResources().getColor(R.color.priortyLowColor));
                prioridade = 2;
                break;
            default:
                break;
        }
    }

    public void updateAutoComplete(List<String> names) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        edtDesc.setAdapter(adapter);

    }


}
