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
import com.mercadolivre.testeerivaldo.model.Type;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.TypeViewHolder> {

    Context mCtx;
    List<Type> typeList;
    SelectActions tpActions;

    public TypesAdapter(Context mCtx, List<Type> typeList, SelectActions tpActions) {
        this.mCtx = mCtx;
        this.typeList = typeList;
        this.tpActions = tpActions;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_type, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {


        Type type = typeList.get(position);

        holder.registerOnPress(()->tpActions.onSelect(type));

        Glide.with(mCtx)
                .load(type.getThumbnail())
                .into(holder.imvType);

        holder.txvType.setText(type.getName());

    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {

        TypeViewAction tvAction;

        @BindView(R.id.imgFlag) ImageButton imvType;
        @BindView(R.id.txvName) TextView txvType;

        @OnClick(R.id.lnlItemType) void clickInType(){
            tvAction.onPressType();
        }

        public void registerOnPress(TypeViewAction tvAction){
            this.tvAction = tvAction;
        }

        public TypeViewHolder(View itemView) {
            super(itemView);


            ButterKnife.bind(this,itemView);
        }
    }

    interface TypeViewAction{
        void onPressType();
    }
}
