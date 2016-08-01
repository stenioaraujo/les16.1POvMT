package br.edu.ufcg.les.povmt.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.fragments.TabFragment1;
import br.edu.ufcg.les.povmt.fragments.TiListFragment;
import br.edu.ufcg.les.povmt.models.TimeInputView;

/**
 * Created by Victor on 16-Jul-16.
 */
public class TimeInputRecyclerAdapter extends RecyclerView.Adapter<TimeInputRecyclerAdapter.ViewHolder> {
    private List<TimeInputView> mDataset;
    private TiListFragment owner;

    public TimeInputRecyclerAdapter(List<TimeInputView> data, TiListFragment owner) {
        mDataset = data;
        Collections.sort(data);

        this.owner = owner;
    }

    @Override
    public TimeInputRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ti_recycler_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(new TimeInputView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final TimeInputRecyclerAdapter.ViewHolder holder, final int position) {
        holder.currentTi.setTi(mDataset.get(position).getTi());
        holder.currentTi.getTxtHour().setText(mDataset.get(position).getTi().getHour()+"");
        holder.currentTi.getTxtMin().setText(mDataset.get(position).getTi().getMin()+"");



        holder.currentTi.getBtDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //owner.delete();
                owner.getDao().removeTimeInput(mDataset.get(position).getTi());
                remove(mDataset.get(position));
                owner.getDao().update();
            }
        });

        holder.currentTi.getTxtTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //owner.showEditDialog();
            }
        });




    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TimeInputView currentTi;

        public ViewHolder(View itemView) {
            super(itemView);

            currentTi = (TimeInputView) itemView;
        }
    }

//    public int getPosTiView(String atvName) {
//        for (int i = 0; i < mDataset.size(); i++) {
//            String txtName = mDataset.get(i).getTxtName().getText() + "";
//            if (txtName.equals(atvName))
//                return i;
//        }
//
//        return -1;
//    }
//
//    public void add(TimeInputView item) {
//        int pos = getPosTiView(item.getTxtName().getText() + "");
//
//        if (pos == -1) {
//            add(mDataset.size(), item);
//        } else {
//            TimeInputView tiv = mDataset.get(pos);
//            tiv.increment((long) item.getTimeToMin());
//            update(pos);
//        }
//    }

    public void add(int position, TimeInputView item) {
        mDataset.add(position, item);
        update(position);
    }

    public void update(int position) {
        Collections.sort(mDataset);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }


    public void remove(TimeInputView item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.currentTi.setTi(null);
        holder.currentTi.getTxtHour().setText("");
        holder.currentTi.getTxtMin().setText("");

    }


}
