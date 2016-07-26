package br.edu.ufcg.les.povmt.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.listviewitems.ChartItem;
import br.edu.ufcg.les.povmt.listviewitems.PieChartItem;
import br.edu.ufcg.les.povmt.models.DemoBase;

/**
 * Created by Isaque on 15-Jul-16.
 */
public class TabFragment2 extends SimpleFragment {


    private PieChart mChart;
    private Typeface tf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.tab_fragment_2, container, false);

        ListView lv = (ListView) v.findViewById(R.id.listViewCharts);

        ArrayList<ChartItem> list = new ArrayList<ChartItem>();

        // 30 items
        for (int i = 0; i < 3; i++) {
            list.add(new PieChartItem(generateDataPie(i + 1), v.getContext()));
        }

        ChartDataAdapter cda = new ChartDataAdapter(v.getContext(), list);
        lv.setAdapter(cda);
        return v;

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
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie(int cnt) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i+1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(d);
        return cd;
    }

}