package br.edu.ufcg.les.povmt.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import java.util.Date;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.adapters.AtividadeRecyclerAdapter;
import br.edu.ufcg.les.povmt.adapters.TimeInputRecyclerAdapter;
import br.edu.ufcg.les.povmt.datahandlers.DAO;
import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.AtividadeView;
import br.edu.ufcg.les.povmt.models.TimeInput;


public class TiListFragment extends Fragment {
//    RecyclerView mRecycler;
//    private Atividade atividade;
//    public TimeInputRecyclerAdapter mAdapter;
//    View rootView;
//    private AutoCompleteTextView edtDesc;
//    private EditText edtH;
//    private EditText edtM;
//    private DAO dao;
//    private Button buttonFiltrar;
//    private TextView hora;
//    private TextView minuto;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);
//
//        edtDesc = (AutoCompleteTextView) rootView.findViewById(R.id.edtDesc);
//        edtH = (EditText) rootView.findViewById(R.id.edtH);
//        edtM = (EditText) rootView.findViewById(R.id.edtM);
//
//
//        dao = DAO.getInstance();
//        List<AtividadeView> tis = dao.getAtividadeViews(getContext(), new Date(0), new Date());
//
//        mRecycler = (RecyclerView) rootView.findViewById(R.id.recycler);
//        // use a linear layout manager
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mRecycler.setLayoutManager(mLayoutManager);
//        mAdapter = new AtividadeRecyclerAdapter(tis, this);
//        mRecycler.setAdapter(mAdapter);
//
//        hora = (TextView) rootView.findViewById(R.id.textViewTempoInvestidoHora);
//        minuto = (TextView) rootView.findViewById(R.id.textViewTempoInvestidoMin);
//
//        atualizarTempoInvestido(tis);
//        mAdapter.addDBListener();
//
//        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showEditDialog();
//            }
//        });
//
//
////        buttonFiltrar = (Button) rootView.findViewById(R.id.button_filtrar);
////        buttonFiltrar.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                showFiltrarDialog();
////            }
////        });
//
//        return rootView;
//    }
//
//    public void showEditDialog() {
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        AtividadeFormularioDialog editNameDialog = new AtividadeFormularioDialog();
//        editNameDialog.setTabFragment1(this);
//        editNameDialog.show(fm, "editNameDialog");
//    }
//
//    public void showFiltrarDialog(){
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        AtividadeFiltroDialog filtroDialog = new AtividadeFiltroDialog();
//        filtroDialog.setTabFragment1(this);
//        filtroDialog.show(fm, "filtroDialog");
//    }
//
//
//
//    public void addTis(int priority) {
//        AtividadeView ti = new AtividadeView(getContext());
//        String h = edtH.getText().toString();
//        String m = edtM.getText().toString();
//        if (h.equals("")) h = "0";
//        if (m.equals("")) m = "0";
//        ti.set(h, m, edtDesc.getText().toString(), priority);
//
//        Atividade atv = dao.getAtividade(ti.getTxtName().getText() + "");
//        if (atv == null) {
//            atv = new Atividade();
//            atv.setName(ti.getTxtName().getText() + "");
//            atv.setPriority(ti.getPriorityId());
//
//        }
//
//        Long hora = Long.parseLong(h);
//        Long min = Long.parseLong(m);
//        TimeInput timeInput = new TimeInput(hora*60 + min, atv);
//
//        dao.add(atv);
//        dao.add(timeInput);
//        dao.update();
//
//        mAdapter.add(ti);
//    }
//
//
//    public void filtrar(String tipo){
//        // TODO getAtividade by type
//        Toast.makeText(getActivity(), tipo, Toast.LENGTH_SHORT).show();
//
//    }
//
//    private int calcMin(List<AtividadeView> mDataset) {
//        int totalMin = 0;
//        for (AtividadeView ti : mDataset) {
//            totalMin += ti.getTimeToMin();
//        }
//        return totalMin;
//    }
//
//    public void atualizarTempoInvestido(List<AtividadeView> tis){
//        hora.setText(String.valueOf(calcMin(tis) /60));
//        int minutos = calcMin(tis) % 60;
//        minuto.setText(String.valueOf(minutos));
//    }
//
//    public Atividade getAtividade() {
//        return atividade;
//    }
//
//    public void setAtividade(Atividade atividade) {
//        this.atividade = atividade;
//    }
//
//    public void updateAutoComplete(List<String> names){
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,names);
//        edtDesc.setAdapter(adapter);
//        rootView.findViewById(R.id.progressBar).setVisibility(View.GONE);
//
//    }

}