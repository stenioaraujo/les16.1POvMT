package br.edu.ufcg.les.povmt.models;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.edu.ufcg.les.povmt.R;

/**
 * Created by Victor on 16-Jul-16.
 */
public class TiView extends RelativeLayout {
    private TextView txtPercent;
    private TextView txtHour;
    private TextView txtMin;
    private TextView txtName;
    private View progress;
    private View btEdit;
    private FrameLayout priority;

    public TiView(Context context) {
        super(context, null);
        init(context);
    }


    public TiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.ti_recycler_item, this, true);

        txtPercent = (TextView) root.findViewById(R.id.txt_percent);
        txtHour = (TextView) root.findViewById(R.id.txt_h);
        txtMin = (TextView) root.findViewById(R.id.txt_min);
        txtName = (TextView) root.findViewById(R.id.txt_name);
        progress = root.findViewById(R.id.progress);
        btEdit = root.findViewById(R.id.bt_edit);
        priority = (FrameLayout) root.findViewById(R.id.priority);
    }

    public void set(int percent,String hour,String min,String name,int priority){
        txtPercent.setText(percent+"");
        txtHour.setText(hour);
        txtMin.setText(min);
        txtName.setText(name);
        switch (priority){
            case 0:
                this.priority.setBackgroundColor(getResources().getColor(R.color.priortyLowColor));
                break;
            case 1:
                this.priority.setBackgroundColor(getResources().getColor(R.color.priortyMediumColor));
                break;
            case 2:
                this.priority.setBackgroundColor(getResources().getColor(R.color.priortyHighColor));
                break;

        }

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int) (progress.getLayoutParams().width*percent/100.0f), progress.getLayoutParams().height);
        progress.setLayoutParams(lp);

    }

    public TextView getTxtPercent() {
        return txtPercent;
    }

    public TextView getTxtHour() {
        return txtHour;
    }

    public TextView getTxtMin() {
        return txtMin;
    }

    public TextView getTxtName() {
        return txtName;
    }

    public View getProgress() {
        return progress;
    }

    public View getBtEdit() {
        return btEdit;
    }

    public View getPriority() {
        return priority;
    }
}
