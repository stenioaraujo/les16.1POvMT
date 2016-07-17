package br.edu.ufcg.les.povmt.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.adapters.TiRecyclerAdapter;
import br.edu.ufcg.les.povmt.models.TiView;

/**
 * Created by Isaque on 15-Jul-16.
 */

public class TabFragment1 extends Fragment {
    RecyclerView mRecycler;
    TiRecyclerAdapter mAdapter;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        ArrayList<TiView> tis = new ArrayList<TiView>();
        tis.add(new TiView(getContext()));
        tis.add(new TiView(getContext()));
//
        mRecycler = (RecyclerView) rootView.findViewById(R.id.recycler);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new TiRecyclerAdapter(tis);
        mRecycler.setAdapter(mAdapter);


        return rootView;

    }


}