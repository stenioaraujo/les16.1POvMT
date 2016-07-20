package br.edu.ufcg.les.povmt.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.models.Atividade;

/**
 * Created by luiz on 17/07/16.
 */
public class AtividadeFormularioDialog  extends DialogFragment {
    private EditText nomeAtividade;
    private RadioButton rbTrabalho;
    private RadioButton rbLazer;
    private Button baixa;
    private Button media;
    private Button alta;
    private Button deletar;
    private Button salvar;
    private TabFragment1 tabFragment1;
    private int prioridade = 0;

    private Bundle saved;

    public AtividadeFormularioDialog() {
        // Empty constructor required for DialogFragment
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saved = savedInstanceState;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.atividade_fragment_dialog, container);
        getDialog().setTitle("Categorias da Atividade");
        nomeAtividade = (EditText) view.findViewById(R.id.editTextNomeAtividade);
        nomeAtividade.setText(getTabFragment1().getAtividade().getName());
        rbTrabalho =  (RadioButton) view.findViewById(R.id.radio_trabalho);
        rbLazer =  (RadioButton) view.findViewById(R.id.radio_lazer);
        baixa =  (Button) view.findViewById(R.id.button_baixa);
        media =  (Button) view.findViewById(R.id.button_media);
        alta =  (Button) view.findViewById(R.id.button_alta);
        deletar =  (Button) view.findViewById(R.id.botao_deletar);
        salvar =  (Button) view.findViewById(R.id.botao_salvar);

        salvar.setOnClickListener(onBtClick);
        deletar.setOnClickListener(onBtClick);

        baixa.setOnClickListener(onBtClicktipo);
        media.setOnClickListener(onBtClicktipo);
        alta.setOnClickListener(onBtClicktipo);



        return view;
    }

    private Button.OnClickListener onBtClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.botao_salvar:
                    getTabFragment1().addTis(prioridade);
                    getDialog().dismiss();
                case R.id.botao_deletar:
                    //setChecked(saved);
                    getDialog().dismiss();
                    break;
                default:
                    break;
            }

        }
    };

    private Button.OnClickListener onBtClicktipo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_baixa:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        baixa.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_name));
                    }else{
                        baixa.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_name));
                    }
                    media.setBackgroundColor(getResources().getColor(R.color.priortyMediumColor));
                    alta.setBackgroundColor(getResources().getColor(R.color.priortyHighColor));

                    prioridade = 0;
                    break;
                case R.id.button_media:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        media.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_name));
                    }else{
                        media.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_name));
                    }
                    baixa.setBackgroundColor(getResources().getColor(R.color.priortyLowColor));
                    alta.setBackgroundColor(getResources().getColor(R.color.priortyHighColor));
                    prioridade = 1;
                    break;
                case R.id.button_alta:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        alta.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_name));
                    }else{
                        alta.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_name));
                    }
                    media.setBackgroundColor(getResources().getColor(R.color.priortyMediumColor));
                    baixa.setBackgroundColor(getResources().getColor(R.color.priortyLowColor));
                    prioridade = 2;
                    break;
                default:
                    break;
            }

        }
    };


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //setChecked(savedInstanceState);
    }



    public TabFragment1 getTabFragment1() {
        return tabFragment1;
    }

    public void setTabFragment1(TabFragment1 tabFragment1) {
        this.tabFragment1 = tabFragment1;
    }
}
