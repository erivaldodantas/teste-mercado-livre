package com.mercadolivre.testeerivaldo.View.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mercadolivre.testeerivaldo.R;
import com.mercadolivre.testeerivaldo.interfaces.SelectActions;
import com.mercadolivre.testeerivaldo.model.Plot;
import com.mercadolivre.testeerivaldo.model.Plot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlotsAdapter extends RecyclerView.Adapter<PlotsAdapter.PlotViewHolder> {

    Context mCtx;
    List<Plot> plotList;
    SelectActions tpActions;

    public PlotsAdapter(Context mCtx, List<Plot> plotList, SelectActions tpActions) {
        this.mCtx = mCtx;
        this.plotList = plotList;
        this.tpActions = tpActions;
    }

    @NonNull
    @Override
    public PlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewPlot) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_plots, parent, false);
        return new PlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlotViewHolder holder, int position) {


        Plot plot = plotList.get(position);

        holder.registerOnPress(()->tpActions.onSelect(plot));


        holder.txvPlot.setText(plot.getRecommendedMessage());

    }

    @Override
    public int getItemCount() {

        return plotList.size();
    }

    class PlotViewHolder extends RecyclerView.ViewHolder {

        PlotViewAction tvAction;

        @BindView(R.id.txvInfoPlot) TextView txvPlot;

        @OnClick(R.id.lnlItemPlot) void clickInPlot(){
            tvAction.onPressPlot();
        }

        public void registerOnPress(PlotViewAction tvAction){
            this.tvAction = tvAction;
        }

        public PlotViewHolder(View itemView) {
            super(itemView);


            ButterKnife.bind(this,itemView);
        }
    }

    interface PlotViewAction{
        void onPressPlot();
    }
}
