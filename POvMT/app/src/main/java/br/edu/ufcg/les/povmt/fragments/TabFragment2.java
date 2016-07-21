package br.edu.ufcg.les.povmt.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import br.edu.ufcg.les.povmt.R;

/**
 * Created by Isaque on 15-Jul-16.
 */
public class TabFragment2 extends SimpleFragment {


    private PieChart mChart;
    private Typeface tf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.tab_fragment_2, container, false);

        View v = inflater.inflate(R.layout.frag_simple_pie, container, false);

        mChart = (PieChart) v.findViewById(R.id.pieChart1);
        mChart.setDescription("");


        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        mChart.setData(generatePieData());

        return v;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Atividades\nda última semana");
        s.setSpan(new RelativeSizeSpan(2f), 0, 10, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 10, s.length(), 0);
        return s;
    }




}