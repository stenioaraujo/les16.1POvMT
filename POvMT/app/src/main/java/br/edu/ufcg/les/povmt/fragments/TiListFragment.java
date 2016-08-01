package br.edu.ufcg.les.povmt.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.adapters.AtividadeRecyclerAdapter;
import br.edu.ufcg.les.povmt.adapters.TimeInputRecyclerAdapter;
import br.edu.ufcg.les.povmt.datahandlers.DAO;
import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.AtividadeView;
import br.edu.ufcg.les.povmt.models.TimeInput;
import br.edu.ufcg.les.povmt.models.TimeInputView;


public class TiListFragment extends DialogFragment {
    RecyclerView mRecycler;
    private AtividadeView atividadeView;
    public TimeInputRecyclerAdapter mAdapter;
    View rootView;
    private TextView txtName;
    private DAO dao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.ti_list_fragment, container, false);

        txtName = (TextView) rootView.findViewById(R.id.txt_name);
        txtName.setText(atividadeView.getTxtName().getText());

        rootView.findViewById(R.id.btBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(3000, 12, 31);
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(1900, 12, 31);

        dao = DAO.getInstance();
        // List<TimeInput> tis = dao.getTimeInputs(new Date(cal.getTimeInMillis()), new Date(cal2.getTimeInMillis()),atividadeView.getAtividade());
        List<TimeInput> tis = atividadeView.getTimeInputs();
        System.out.println(tis.size());
//        System.out.println(new Date(cal.getTimeInMillis()));
//        System.out.println(new Date(cal2.getTimeInMillis()));
        List<TimeInputView> tisViews = new ArrayList<>();
        for (TimeInput ti : tis) {
            tisViews.add(new TimeInputView(getContext(), ti));
        }

        mRecycler = (RecyclerView) rootView.findViewById(R.id.recycler);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new TimeInputRecyclerAdapter(tisViews, this);
        mRecycler.setAdapter(mAdapter);


        //mAdapter.addDBListener();


        return rootView;
    }

    public void showEditDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        AtividadeFormularioDialog editNameDialog = new AtividadeFormularioDialog();
        //editNameDialog.setTabFragment1(this);
        editNameDialog.show(fm, "editNameDialog");
    }

    public AtividadeView getAtividadeView() {
        return atividadeView;
    }

    public void setAtividadeView(AtividadeView atividadeView) {
        this.atividadeView = atividadeView;
    }

    public DAO getDao() {
    return dao;
    }

}