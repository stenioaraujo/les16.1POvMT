package br.edu.ufcg.les.povmt.listviewitems;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.listviewitems.ChartItem;

/**
 * Created by Treinamento-Xiaomi on 26/07/2016.
 */
public class PieChartItem extends ChartItem {
//    private Typeface mTf;
    private SpannableString mCenterText;

    public PieChartItem(String title, String subtitle, ChartData<?> cd, Context c) {
        super(cd);

//        mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
        mCenterText = generateCenterText(title, subtitle);
    }

    @Override
    public int getItemType() {
        return TYPE_PIECHART;
    }


    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(R.layout.list_item_piechart, null);
//            ListView lv = (ListView) convertView.findViewById(R.id.listViewCharts);
//            lv.

            holder.chart = (com.github.mikephil.charting.charts.PieChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        holder.chart.setDescription("");
        holder.chart.setHoleRadius(52f);
        holder.chart.setTransparentCircleRadius(57f);
        holder.chart.setCenterText(mCenterText);
//        holder.chart.setCenterTextTypeface(mTf);
        holder.chart.setCenterTextSize(9f);
        holder.chart.setUsePercentValues(true);
        holder.chart.setExtraOffsets(5, 10, 50, 10);

        mChartData.setValueFormatter(new PercentFormatter());
//        mChartData.setValueTypeface(mTf);
        mChartData.setValueTextSize(11f);
        mChartData.setValueTextColor(Color.WHITE);
        // set data
        holder.chart.setData((PieData) mChartData);

        Legend l = holder.chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        holder.chart.animateY(900);

        return convertView;
    }

    private SpannableString generateCenterText(String title, String subtitle) {
        SpannableString s = new SpannableString(title + "\n" + subtitle);

        s.setSpan(new RelativeSizeSpan(1.6f), 0, title.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), 0);

        s.setSpan(new RelativeSizeSpan(.9f), title.length(), title.length()+subtitle.length()+1, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), title.length(), title.length()+subtitle.length()+1, 0);

//        s.setSpan(new RelativeSizeSpan(1.4f), 25, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 25, s.length(), 0);
        return s;
    }

    private static class ViewHolder {
        com.github.mikephil.charting.charts.PieChart chart;
    }
}
