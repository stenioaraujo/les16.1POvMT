package br.edu.ufcg.les.povmt.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import br.edu.ufcg.les.povmt.R;
import br.edu.ufcg.les.povmt.fragments.TabFragment1;
import br.edu.ufcg.les.povmt.models.AtividadeView;

/**
 * Created by Victor on 16-Jul-16.
 */
public class AtividadeRecyclerAdapter extends RecyclerView.Adapter<AtividadeRecyclerAdapter.ViewHolder> {
    private List<AtividadeView> mDataset;
    private TabFragment1 owner;

    public AtividadeRecyclerAdapter(List<AtividadeView> data, TabFragment1 owner) {
        mDataset = data;
        Collections.sort(data);

        this.owner = owner;
        calcPercentage();
    }

    @Override
    public AtividadeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ti_recycler_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(new AtividadeView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final AtividadeRecyclerAdapter.ViewHolder holder, int position) {
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

        holder.currentTi.getProgress().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        private AtividadeView currentTi;

        public ViewHolder(View itemView) {
            super(itemView);

            currentTi = (AtividadeView) itemView;
        }
    }

    public int getPosTiView(String atvName) {
        for (int i = 0; i < mDataset.size(); i++) {
            String txtName = mDataset.get(i).getTxtName().getText() + "";
            if (txtName.equals(atvName))
                return i;
        }

        return -1;
    }

    public void add(AtividadeView item) {
        int pos = getPosTiView(item.getTxtName().getText() + "");

        if (pos == -1) {
            add(mDataset.size(), item);
        } else {
            AtividadeView tiv = mDataset.get(pos);
            tiv.increment((long) item.getTimeToMin());
            update(pos);
        }
    }

    public void add(int position, AtividadeView item) {
//        for (AtividadeView av: mDataset) {
//            if(av.getTxtName().getText().equals(item.getTxtName().getText())){
//                av.set
//            }
//        }
        mDataset.add(position, item);
        update(position);
    }

    public void update(int position) {
        Collections.sort(mDataset);

        calcPercentage();
        notifyItemInserted(position);
        notifyDataSetChanged();
    }


    public void remove(AtividadeView item) {
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
        for (AtividadeView ti: mDataset) {
            totalMin += ti.getTimeToMin();
        }
        for (AtividadeView ti: mDataset) {
            if (totalMin == 0) break;

            ti.setPercent(((ti.getTimeToMin()*100)/totalMin));
            ti.getTxtPercent().setText(ti.getPercent()+"");
        }
    }

}
