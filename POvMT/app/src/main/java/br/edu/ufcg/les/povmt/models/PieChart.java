package br.edu.ufcg.les.povmt.models;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import br.edu.ufcg.les.povmt.R;


/**
 * Created by Isaque on 21-Jul-16.
 */
public class PieChart extends FragmentActivity implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

     protected String[] mMonths = new String[] {
             "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
     };

     protected String[] mParties = new String[] {
             "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
             "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
             "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
             "Party Y", "Party Z"
     };

     protected Typeface mTfRegular;
     protected Typeface mTfLight;

     private com.github.mikephil.charting.charts.PieChart mChart;
     private SeekBar mSeekBarX, mSeekBarY;
     private TextView tvX, tvY;

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
          mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);
          setContentView(R.layout.pie_chart);

          tvX = (TextView) findViewById(R.id.tvXMax);
          tvY = (TextView) findViewById(R.id.tvYMax);

          mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
          mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
          mSeekBarX.setProgress(4);
          mSeekBarY.setProgress(10);

          mChart = (com.github.mikephil.charting.charts.PieChart) findViewById(R.id.chart1);
          mChart.setUsePercentValues(true);
          mChart.setDescription("");
          mChart.setExtraOffsets(5, 10, 5, 5);

          mChart.setDragDecelerationFrictionCoef(0.95f);

          mChart.setCenterTextTypeface(mTfLight);
          mChart.setCenterText(generateCenterSpannableText());

          mChart.setDrawHoleEnabled(true);
          mChart.setHoleColor(Color.WHITE);

          mChart.setTransparentCircleColor(Color.WHITE);
          mChart.setTransparentCircleAlpha(110);

          mChart.setHoleRadius(58f);
          mChart.setTransparentCircleRadius(61f);

          mChart.setDrawCenterText(true);

          mChart.setRotationAngle(0);
          // enable rotation of the chart by touch
          mChart.setRotationEnabled(true);
          mChart.setHighlightPerTapEnabled(true);

          // mChart.setUnit(" €");
          // mChart.setDrawUnitsInChart(true);

          // add a selection listener
          mChart.setOnChartValueSelectedListener(this);

          setData(4, 100);

          mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
          // mChart.spin(2000, 0, 360);

          mSeekBarX.setOnSeekBarChangeListener(this);
          mSeekBarY.setOnSeekBarChangeListener(this);

          Legend l = mChart.getLegend();
          l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
          l.setXEntrySpace(7f);
          l.setYEntrySpace(0f);
          l.setYOffset(0f);

          // entry label styling
          mChart.setEntryLabelColor(Color.BLACK);
          mChart.setEntryLabelTypeface(mTfRegular);
          mChart.setEntryLabelTextSize(12f);
     }

     protected float getRandom(float range, float startsfrom) {
          return (float) (Math.random() * range) + startsfrom;
     }

//     @Override
//     public void onBackPressed() {
//          super.onBackPressed();
//          overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
//     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.pie, menu);
          return true;
     }

     @Override
     public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

          tvX.setText("" + (mSeekBarX.getProgress()));
          tvY.setText("" + (mSeekBarY.getProgress()));

          setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
     }

     private void setData(int count, float range) {

          float mult = range;

          ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

          // NOTE: The order of the entries when being added to the entries array determines their position around the center of
          // the chart.
          for (int i = 0; i < count ; i++) {
               entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), mParties[i % mParties.length]));
          }

          PieDataSet dataSet = new PieDataSet(entries, "Election Results");
          dataSet.setSliceSpace(3f);
          dataSet.setSelectionShift(5f);

          // add a lot of colors

          ArrayList<Integer> colors = new ArrayList<Integer>();

          for (int c : ColorTemplate.VORDIPLOM_COLORS)
               colors.add(c);

          for (int c : ColorTemplate.JOYFUL_COLORS)
               colors.add(c);

          for (int c : ColorTemplate.COLORFUL_COLORS)
               colors.add(c);

          for (int c : ColorTemplate.LIBERTY_COLORS)
               colors.add(c);

          for (int c : ColorTemplate.PASTEL_COLORS)
               colors.add(c);

          colors.add(ColorTemplate.getHoloBlue());

          dataSet.setColors(colors);
          //dataSet.setSelectionShift(0f);

          PieData data = new PieData(dataSet);
          data.setValueFormatter(new PercentFormatter());
          data.setValueTextSize(11f);
          data.setValueTextColor(Color.BLACK);
          data.setValueTypeface(mTfLight);
          mChart.setData(data);

          // undo all highlights
          mChart.highlightValues(null);

          mChart.invalidate();
     }

     private SpannableString generateCenterSpannableText() {

          SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
          s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
          s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
          s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
          s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
          s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
          s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
          return s;
     }

     @Override
     public void onValueSelected(Entry e, Highlight h) {

          if (e == null)
               return;
          Log.i("VAL SELECTED",
                  "Value: " + e.getY() + ", index: " + h.getX()
                          + ", DataSet index: " + h.getDataSetIndex());
     }

     @Override
     public void onNothingSelected() {
          Log.i("PieChart", "nothing selected");
     }

     @Override
     public void onStartTrackingTouch(SeekBar seekBar) {
          // TODO Auto-generated method stub

     }

     @Override
     public void onStopTrackingTouch(SeekBar seekBar) {
          // TODO Auto-generated method stub

     }


}
