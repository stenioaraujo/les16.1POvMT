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
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.adapters.TiRecyclerAdapter;
import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.TiView;

/**
 * Created by Isaque on 15-Jul-16.
 */

public class TabFragment1 extends Fragment {
    RecyclerView mRecycler;
    private Atividade atividade;
   public  TiRecyclerAdapter mAdapter;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        atividade = new Atividade();
        ArrayList<TiView> tis = new ArrayList<TiView>();
        tis.add(new TiView(getContext(),"6","29","Les",2));
        tis.add(new TiView(getContext(),"4","30","Empsoft",1));
        tis.add(new TiView(getContext(),"3","54","So",0));
        tis.add(new TiView(getContext(),"2","10","Irc",0));
        tis.add(new TiView(getContext(),"10","45","Festar",2));

        mRecycler = (RecyclerView) rootView.findViewById(R.id.recycler);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new TiRecyclerAdapter(tis);
        mRecycler.setAdapter(mAdapter);

//
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TiView ti = new TiView(getContext());
                ti.set("1","29","Atividade Legal",1);
                mAdapter.add(mAdapter.getItemCount(),ti);
                showEditDialog();
            }
        });


        return rootView;

    }

    private void showEditDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        AtividadeFormularioDialog editNameDialog = new AtividadeFormularioDialog();
        editNameDialog.setTabFragment1(this);
        editNameDialog.show(fm, "editNameDialog");
    }

    public void onFinishEditDialog(String inputText) {
        Toast.makeText(getActivity(), "Hi", Toast.LENGTH_SHORT).show();
    }

    public void addTis(){
        TiView ti = new TiView(getContext());
        ti.set("1","29","Atividade Legal",0);
        mAdapter.add(mAdapter.getItemCount(),ti);
    }


    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

}