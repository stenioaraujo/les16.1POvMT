package br.edu.ufcg.les.povmt.fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.datahandlers.DAO;
import br.edu.ufcg.les.povmt.datahandlers.DBSPopulater;
import br.edu.ufcg.les.povmt.listviewitems.ChartItem;
import br.edu.ufcg.les.povmt.listviewitems.PieChartItem;
import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.AtividadeView;
import br.edu.ufcg.les.povmt.models.TimeInput;

/**
 * Created by Isaque on 15-Jul-16.
 */
public class TabFragment2 extends Fragment {


    private PieChart mChart;
    private Typeface tf;
    private DAO dao;
    View v;
    ListView lv;
    ArrayList<ChartItem> list;
    public ChartDataAdapter cda;

    public TabFragment2() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        v = inflater.inflate(R.layout.tab_fragment_2, container, false);
        lv = (ListView) v.findViewById(R.id.listViewCharts);
        dao = dao.getInstance();

        generateGraphics();
        cda.addDBListener();

        return v;

    }

    public void updateGraphics(){
        cda.notifyDataSetChanged();
    }

    public void generateGraphics(){
        list = new ArrayList<ChartItem>();

        DateTime dt = new DateTime();
        final Date back7Days = dt.minusDays(7).toDate();
        final Date back14Days = dt.minusDays(14).toDate();
        final Date back21Days = dt.minusDays(21).toDate();

        // Semana Atual
        list.add(new PieChartItem("Semana Atual", "Ultimos 7 dias", generateDataPie(back7Days, new DateTime().toDate()), v.getContext()));

        // Semana Passada
        list.add(new PieChartItem("Semana Passada", "Entre 7 e 14 dias atrás", generateDataPie(back14Days, back7Days), v.getContext()));

        // Semana Retrasada
        list.add(new PieChartItem("Semana Retrasada", "Entre 14 e 21 dias atrás", generateDataPie(back21Days, back14Days), v.getContext()));

        cda = new ChartDataAdapter(v.getContext(), list);
        lv.setAdapter(cda);
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Atividades\nda última semana");
        s.setSpan(new RelativeSizeSpan(2f), 0, 10, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 10, s.length(), 0);
        return s;
    }
    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            return getItem(position).getItemType();
        }

        @Override
        public int getViewTypeCount() {
            return 1; // we have 3 different item-types
        }

        public void addDBListener() {
            final DAO dao = DAO.getInstance();
            dao.addListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //TODO essa parte aqui tem que pegar um limite de tempo, nao sei qual
                    try {
                        generateGraphics();

                        Log.v("DB_UPDATED", "Adapter notificado, usuario atual " + dao.getUid());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie(Date inicio, Date fim) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        ArrayList<Atividade> listAllTasks = dao.getAllTasks();
//        ArrayList<TimeInput> listAllTimeInputs = dao.getAllTimeInputs();

        List<TimeInput> listTimesInInterval;

        for(int i = 0; i < listAllTasks.size();i++ ){
            int timeTotal = 0;
            listTimesInInterval = dao.getTimeInputs(inicio,fim, listAllTasks.get(i));

            for(int j = 0; j < listTimesInInterval.size(); j++){
                Log.e("data criacao ti", listTimesInInterval.get(j).getDataCriacao().toString());

                if(listAllTasks.get(i).getName().equals(listTimesInInterval.get(j).getAtvPai().getName())){
                    timeTotal += listTimesInInterval.get(j).getTime();
                }
            }
            if (timeTotal != 0) {
                entries.add(new PieEntry((float) timeTotal, listAllTasks.get(i).getName()));
            }
        }

        //for (int i = 0; i < listAllTimeInputs.size(); i++) {
          //  entries.add(new PieEntry((float) ((listAllTimeInputs.get(i).getTime())), listAllTimeInputs.get(i).getAtvPai().getName()));
        //}

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData cd = new PieData(d);
        return cd;
    }

}