package br.edu.ufcg.les.povmt.listviewitems;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.data.ChartData;

/**
 * Created by Treinamento-Xiaomi on 26/07/2016.
 */
public abstract class ChartItem {

    protected static final int TYPE_BARCHART = 0;
    protected static final int TYPE_LINECHART = 1;
    protected static final int TYPE_PIECHART = 2;

    protected ChartData<?> mChartData;

    public ChartItem(ChartData<?> cd) {
        this.mChartData = cd;
    }

    public abstract int getItemType();

    public abstract View getView(int position, View convertView, Context c);
}
