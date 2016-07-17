package br.edu.ufcg.les.povmt.models;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.edu.ufcg.les.povmt.R;

/**
 * Created by Victor on 16-Jul-16.
 */
public class TiView extends RelativeLayout{
    private TextView txtPercent;
    private TextView txtHour;
    private TextView txtMin;
    private TextView txtName;
    private View progress;
    private View btEdit;
    private View priority;

    public TiView(Context context) {
        super(context,null);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ti_recycler_item, this, true);
    }

    public TiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ti_recycler_item, this, true);
    }

    public TiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ti_recycler_item, this, true);
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
