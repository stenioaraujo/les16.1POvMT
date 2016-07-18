package br.edu.ufcg.les.povmt.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.edu.ufcg.les.povmt.R;

/**
 * Created by luiz on 17/07/16.
 */
public class AtividadeFormularioDialog  extends DialogFragment {
    private EditText mEditText;

    public AtividadeFormularioDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.atividade_fragment_dialog, container);
        mEditText = (EditText) view.findViewById(R.id.editTextNomeAtividade);
        getDialog().setTitle("Hello");

        return view;
    }
}
