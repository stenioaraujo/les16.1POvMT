package br.edu.ufcg.les.povmt.models;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.datahandlers.DAO;

/**
 * Created by Victor on 16-Jul-16.
 */
public class AtividadeView extends RelativeLayout implements Comparable<AtividadeView> {
    private TextView txtPercent;
    private TextView txtHour;
    private TextView txtMin;
    private TextView txtName;
    private FrameLayout progress;
    private View btEdit;
    private FrameLayout priority;
    private int percent;
    private int priorityId;
    private List<TimeInput> timeInputs;
    private Atividade atividade;

    public AtividadeView(Context context) {
        super(context, null);
        init(context);
    }

    public AtividadeView(Context context, String hour, String min, String name, int priority) {
        super(context, null);
        init(context);
        set(hour,min,name,priority);
    }

    public AtividadeView(Context context, String hour, String min, Atividade atividade, List<TimeInput> timeInputs) {
        super(context, null);
        init(context);
        set(hour, min, atividade, timeInputs);
    }

    public AtividadeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AtividadeView(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public void set(String hour, String min, Atividade atv, List<TimeInput> timeInputs) {
        txtHour.setText(hour);
        txtMin.setText(min);
        txtName.setText(atv.getName());
        priorityId = atv.getPriority();
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

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public int getTimeToMin(){
        try {
            return Integer.parseInt(txtHour.getText() + "")*60 + Integer.parseInt(txtMin.getText() + "");
        }catch(NumberFormatException e){
            return 0;
        }
    }

    public void increment(Long minutes) {
        Long min = getTimeToMin() + minutes;
        Long hours = DAO.getHours(min);

        txtHour.setText(String.valueOf(hours));
        txtMin.setText(String.valueOf(min - hours*60));
    }

    @Override
    public int compareTo(AtividadeView another) {
        return -1 * (this.getTimeToMin() - another.getTimeToMin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(o instanceof AtividadeView){
            AtividadeView a = (AtividadeView) o;
            if (a.getTxtName().getText().equals(this.getTxtName().getText())){
                return true;
            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        int result = txtName.getText().hashCode();
        return result;
    }
}
