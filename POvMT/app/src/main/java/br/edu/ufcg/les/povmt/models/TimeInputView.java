package br.edu.ufcg.les.povmt.models;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.datahandlers.DAO;

/**
 * Created by Victor on 16-Jul-16.
 */
public class TimeInputView extends RelativeLayout implements Comparable<TimeInputView> {
    private TextView txtHour;
    private View btDelete;
    private View txtTime;
    private TextView txtMin;
    private TimeInput ti;

    public TimeInputView(Context context) {
        super(context, null);
        init(context);
    }

    public TimeInputView(Context context, TimeInput ti) {
        super(context, null);
        this.ti = ti;
        init(context);
    }

    public TimeInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.time_input_recycler_item, this, true);

        txtHour = (TextView) root.findViewById(R.id.txt_h);
        txtMin = (TextView) root.findViewById(R.id.txt_min);
        btDelete = root.findViewById(R.id.bt_delete);
        txtTime = root.findViewById(R.id.txt_time);

    }


    public int getTimeToMin(){
        try {
            return Integer.parseInt(txtHour.getText() + "")*60 + Integer.parseInt(txtMin.getText() + "");
        }catch(NumberFormatException e){
            return 0;
        }
    }



    public TextView getTxtHour() {
        return txtHour;
    }

    public TextView getTxtMin() {
        return txtMin;
    }

    public void setTxtHour(TextView txtHour) {
        this.txtHour = txtHour;
    }

    public void setTxtMin(TextView txtMin) {
        this.txtMin = txtMin;
    }

    public View getBtDelete() {
        return btDelete;
    }

    public void setBtDelete(View btEdit) {
        this.btDelete = btEdit;
    }

    public View getTxtTime() {
        return txtTime;
    }

    public void setTxtTime(View txtTime) {
        this.txtTime = txtTime;
    }

    public TimeInput getTi() {
        return ti;
    }

    public void setTi(TimeInput ti) {
        this.ti = ti;
    }

    public void increment(Long minutes) {
        Long min = getTimeToMin() + minutes;
        Long hours = DAO.getHours(min);

        txtHour.setText(String.valueOf(hours));
        txtMin.setText(String.valueOf(min - hours*60));
    }

    @Override
    public int compareTo(TimeInputView another) {
        return -1 * (this.getTimeToMin() - another.getTimeToMin());
    }
}
