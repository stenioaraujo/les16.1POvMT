package br.edu.ufcg.les.povmt.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.adapters.TiRecyclerAdapter;
import br.edu.ufcg.les.povmt.datahandlers.DAO;
import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.TiView;
import br.edu.ufcg.les.povmt.models.TimeInput;

/**
 * Created by Isaque on 15-Jul-16.
 */

public class TabFragment1 extends Fragment {
    RecyclerView mRecycler;
    private Atividade atividade;
    public TiRecyclerAdapter mAdapter;
    View rootView;
    private EditText edtDesc;
    private EditText edtH;
    private EditText edtM;
    private DAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        edtDesc = (EditText) rootView.findViewById(R.id.edtDesc);
        edtH = (EditText) rootView.findViewById(R.id.edtH);
        edtM = (EditText) rootView.findViewById(R.id.edtM);

        atividade = new Atividade();
        /**
        ArrayList<TiView> tis = new ArrayList<TiView>();
        tis.add(new TiView(getContext(), "6", "29", "Les", 2));
        tis.add(new TiView(getContext(), "4", "30", "Empsoft", 1));
        tis.add(new TiView(getContext(), "3", "54", "So", 0));
        tis.add(new TiView(getContext(), "2", "10", "Irc", 0));
        tis.add(new TiView(getContext(), "10", "45", "Festar", 2));
         **/
        dao = DAO.getInstance();
        List<TiView> tis = dao.getTiViews(getContext(), new Date(0), new Date());

        mRecycler = (RecyclerView) rootView.findViewById(R.id.recycler);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new TiRecyclerAdapter(tis, this);
        mRecycler.setAdapter(mAdapter);

//
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
            }
        });


        return rootView;
    }

    public void showEditDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        AtividadeFormularioDialog editNameDialog = new AtividadeFormularioDialog();
        editNameDialog.setTabFragment1(this);
        editNameDialog.show(fm, "editNameDialog");
    }

    public void onFinishEditDialog(String inputText) {
        Toast.makeText(getActivity(), "Hi", Toast.LENGTH_SHORT).show();
    }

    public void addTis(int priority) {
        TiView ti = new TiView(getContext());
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

        Long hora = Long.parseLong(h);
        Long min = Long.parseLong(m);
        TimeInput timeInput = new TimeInput(hora*60 + min, atv);

        dao.add(atv);
        dao.add(timeInput);
        dao.update();

        mAdapter.add(ti);
    }


    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

}