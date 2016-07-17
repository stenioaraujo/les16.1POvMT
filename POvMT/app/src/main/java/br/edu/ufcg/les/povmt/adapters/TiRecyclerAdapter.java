package br.edu.ufcg.les.povmt.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.models.TiView;

/**
 * Created by Victor on 16-Jul-16.
 */
public class TiRecyclerAdapter extends RecyclerView.Adapter<TiRecyclerAdapter.ViewHolder> {
    private ArrayList<TiView> mDataset;

    public TiRecyclerAdapter(List<TiView> data) {
        mDataset = (ArrayList<TiView>) data;

    }

    @Override
    public TiRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ti_recycler_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(new TiView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(TiRecyclerAdapter.ViewHolder holder, int position) {

        holder.currentTi.getTxtHour().setText(mDataset.get(position).getTxtHour().getText());
        holder.currentTi.getTxtMin().setText(mDataset.get(position).getTxtMin().getText());
        holder.currentTi.getTxtName().setText(mDataset.get(position).getTxtName().getText());
        holder.currentTi.getTxtPercent().setText(mDataset.get(position).getTxtPercent().getText());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TiView currentTi;

        public ViewHolder(View itemView) {
            super(itemView);

            currentTi = (TiView) itemView;
        }
    }
}
