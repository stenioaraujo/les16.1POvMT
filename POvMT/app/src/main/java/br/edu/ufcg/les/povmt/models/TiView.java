package br.edu.ufcg.les.povmt.models;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private FrameLayout progress;
    private View btEdit;
    private FrameLayout priority;
    private int percent;
    private int priorityId;

    public TiView(Context context) {
        super(context, null);
        init(context);
    }
    public TiView(Context context, String hour, String min, String name, int priority) {
        super(context, null);
        init(context);
        set(hour,min,name,priority);
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
        progress = (FrameLayout) root.findViewById(R.id.progress);
        btEdit = root.findViewById(R.id.bt_edit);
        priority = (FrameLayout) root.findViewById(R.id.priority);
    }

    public void set( String hour, String min, String name, int priority) {
        //txtPercent.setText(percent + "");
        txtHour.setText(hour);
        txtMin.setText(min);
        txtName.setText(name);
        //this.percent = percent;
        priorityId = priority;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        ViewGroup.LayoutParams lp = progress.getLayoutParams();
//        lp.width = (int) (progress.getWidth() * percent / 100.0f);
//        Log.d("tivew", progress.getWidth()+" --- "+lp.width + " --- "+ percent );
//        progress.setLayoutParams(lp);
//        progress.invalidate();
//        progress.requestLayout();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);




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

    public FrameLayout getProgress() {
        return progress;
    }

    public View getBtEdit() {
        return btEdit;
    }

    public View getPriority() {
        return priority;
    }

    public int getPercent() {
        return percent;
    }

    public void setTxtPercent(TextView txtPercent) {
        this.txtPercent = txtPercent;
    }

    public void setTxtHour(TextView txtHour) {
        this.txtHour = txtHour;
    }

    public void setTxtMin(TextView txtMin) {
        this.txtMin = txtMin;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public void setProgress(FrameLayout progress) {
        this.progress = progress;
    }

    public void setBtEdit(View btEdit) {
        this.btEdit = btEdit;
    }

    public void setPriority(FrameLayout priority) {
        this.priority = priority;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public int getTimeToMin(){
        try {
            return Integer.parseInt(txtHour.getText() + "") + Integer.parseInt(txtMin.getText() + "") / 60;
        }catch(NumberFormatException e){
            return 0;
        }
    }
}
