package br.edu.ufcg.les.povmt.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.activities.MainActivity;
import br.edu.ufcg.les.povmt.fragments.TabFragment1;
import br.edu.ufcg.les.povmt.models.TiView;

/**
 * Created by Victor on 16-Jul-16.
 */
public class TiRecyclerAdapter extends RecyclerView.Adapter<TiRecyclerAdapter.ViewHolder> {
    private List<TiView> mDataset;
    private TabFragment1 owner;

    public TiRecyclerAdapter(List<TiView> data, TabFragment1 owner) {
        mDataset = (List<TiView>) data;
        this.owner = owner;
        calcPercentage();
    }

    @Override
    public TiRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ti_recycler_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(new TiView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final TiRecyclerAdapter.ViewHolder holder, int position) {
        holder.currentTi.getTxtHour().setText(mDataset.get(position).getTxtHour().getText());
        holder.currentTi.getTxtMin().setText(mDataset.get(position).getTxtMin().getText());
        holder.currentTi.getTxtName().setText(mDataset.get(position).getTxtName().getText());
        holder.currentTi.getTxtPercent().setText(mDataset.get(position).getTxtPercent().getText());
        holder.currentTi.setPercent(mDataset.get(position).getPercent());
        holder.currentTi.setPriorityId(mDataset.get(position).getPriorityId());



        holder.currentTi.getBtEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                owner.showEditDialog();
            }
        });



        switch (holder.currentTi.getPriorityId()) {
            case 0:
                holder.currentTi.getPriority().setBackgroundColor(holder.itemView.getResources().getColor(R.color.priortyLowColor));
                break;
            case 1:
                holder.currentTi.getPriority().setBackgroundColor(holder.itemView.getResources().getColor(R.color.priortyMediumColor));
                break;
            case 2:
                holder.currentTi.getPriority().setBackgroundColor(holder.itemView.getResources().getColor(R.color.priortyHighColor));
                break;

        }


        ViewGroup.LayoutParams lp = holder.currentTi.getProgress().getLayoutParams();
        ViewGroup.LayoutParams lp2 = mDataset.get(position).getProgress().getLayoutParams();
        lp.width = (int) (+lp2.width * mDataset.get(position).getPercent() / 100.0f);
        holder.currentTi.getProgress().setLayoutParams(lp);
        holder.currentTi.getProgress().invalidate();
        holder.currentTi.getProgress().requestLayout();

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TiView currentTi;

        public ViewHolder(View itemView) {
            super(itemView);

            currentTi = (TiView) itemView;
        }
    }


    public void add(int position, TiView item) {
        mDataset.add(position, item);
        calcPercentage();
        notifyItemInserted(position);
        notifyDataSetChanged();

    }


    public void remove(TiView item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        calcPercentage();
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.currentTi.getTxtHour().setText("");
        holder.currentTi.getTxtMin().setText("");
        holder.currentTi.getTxtName().setText("");
        holder.currentTi.getTxtPercent().setText("");
        holder.currentTi.setPercent(0);
        holder.currentTi.setPriorityId(0);


        holder.currentTi.getPriority().setBackgroundColor(holder.itemView.getResources().getColor(R.color.priortyLowColor));


        ViewGroup.LayoutParams lp = holder.currentTi.getProgress().getLayoutParams();
        lp.width = 0;
        holder.currentTi.getProgress().setLayoutParams(lp);
        holder.currentTi.getProgress().invalidate();
        holder.currentTi.getProgress().requestLayout();
    }

    private void calcPercentage() {
        int totalMin = 0;
        for (TiView ti: mDataset) {
            totalMin += ti.getTimeToMin();
        }
        for (TiView ti: mDataset) {
            Log.v("AAAAAAAAAA-----", String.valueOf(((ti.getTimeToMin()*100)/totalMin)));
            if (totalMin == 0) break;

            ti.setPercent(((ti.getTimeToMin()*100)/totalMin));
            ti.getTxtPercent().setText(ti.getPercent()+"");
        }
    }

}
