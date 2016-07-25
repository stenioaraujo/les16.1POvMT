package br.edu.ufcg.les.povmt.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.models.TipoAtividade;

/**
 * Created by luiz on 17/07/16.
 */
public class AtividadeFiltroDialog extends DialogFragment {
    private RadioButton rbTrabalho;
    private RadioButton rbLazer;
    private Button filtrar;
    private TabFragment1 tabFragment1;
    private TipoAtividade tipo;

    private Bundle saved;

    public AtividadeFiltroDialog() {
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
        getDialog().setTitle("Filtro de Atividade");
        View view = inflater.inflate(R.layout.filtro_atividade_fragment_dialog, container);
        rbTrabalho =  (RadioButton) view.findViewById(R.id.filtro_radio_trabalho);
        rbLazer =  (RadioButton) view.findViewById(R.id.filtro_radio_lazer);
        filtrar =  (Button) view.findViewById(R.id.botao_filtrar);

        filtrar.setOnClickListener(onBtClick);

        return view;
    }

    private Button.OnClickListener onBtClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (rbLazer.isChecked()){
                getTabFragment1().filtrar(TipoAtividade.LAZER);
                getDialog().dismiss();
            }else if (rbTrabalho.isChecked()){
                getTabFragment1().filtrar(TipoAtividade.TRABALHO);
                getDialog().dismiss();
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
