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
import com.mercadolivre.testeerivaldo.model.Bank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BanksAdapter extends RecyclerView.Adapter<BanksAdapter.BankViewHolder> {

    Context mCtx;
    List<Bank> bankList;
    SelectActions tpActions;

    public BanksAdapter(Context mCtx, List<Bank> heroList, SelectActions tpActions) {
        this.mCtx = mCtx;
        this.bankList = heroList;
        this.tpActions = tpActions;
    }

    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewBank) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.fragment_bank_item, parent, false);
        return new BankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder holder, int position) {


        Bank bank = bankList.get(position);

        holder.registerOnPress(()->tpActions.onSelect(bank));

        Glide.with(mCtx)
                .load(bank.getThumbnail())
                .into(holder.imvBank);

        holder.txvBank.setText(bank.getName());

    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    class BankViewHolder extends RecyclerView.ViewHolder {

        BankViewAction tvAction;

        @BindView(R.id.imgFlag) ImageButton imvBank;
        @BindView(R.id.txvName) TextView txvBank;

        @OnClick(R.id.lnlItemBank) void clickInBank(){
            tvAction.onPressBank();
        }

        public void registerOnPress(BankViewAction tvAction){
            this.tvAction = tvAction;
        }

        public BankViewHolder(View itemView) {
            super(itemView);


            ButterKnife.bind(this,itemView);
        }
    }

    interface BankViewAction{
        void onPressBank();
    }
}
