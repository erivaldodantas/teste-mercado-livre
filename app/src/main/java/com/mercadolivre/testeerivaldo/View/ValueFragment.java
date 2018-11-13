package com.mercadolivre.testeerivaldo.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mercadolivre.testeerivaldo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValueFragment extends Fragment {

    @BindView(R.id.edtValue) EditText edtValue;
    @BindView(R.id.txvType) TextView txvLabelType;
    @BindView(R.id.txvBank) TextView txvLabelBank;
    @BindView(R.id.txvPlots) TextView txvLabelPlots;
    @BindView(R.id.imvType) ImageView imvType;
    @BindView(R.id.imvBank) ImageView imvBank;

    public ValueFragment() {

    }

    public void pressButton(){
        String value = edtValue.getText().toString();
        if(value.isEmpty()){
            Toast.makeText(getContext(), "Informe o valor antes de iniciar.", Toast.LENGTH_SHORT).show();
        }else{
            getMainActivity().setValue(value);
            getMainActivity().openFragment(MainActivity.fgtType);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_value, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.handleFieds();

    }

    private void handleType(){
        if(getMainActivity().getType()!=null){
            this.txvLabelType.setText(getMainActivity().getType().getName());
            Glide.with(this)
                    .load(getMainActivity().getType().getThumbnail())
                    .into(this.imvType);
        }else{
            this.txvLabelType.setText(R.string.typeNotSet);
            imvType.setColorFilter(android.R.color.darker_gray);
        }
    }

    private void handleBank(){
        if(getMainActivity().getBank()!=null){
            this.txvLabelBank.setText(getMainActivity().getBank().getName());
            Glide.with(this)
                    .load(getMainActivity().getBank().getThumbnail())
                    .into(this.imvBank);
        }else{
            this.txvLabelBank.setText(R.string.bankNotSet);
            imvBank.setColorFilter(android.R.color.darker_gray);
        }
    }


    private MainActivity getMainActivity(){
        return ((MainActivity) getActivity());
    }

    public void handleFieds() {
        this.edtValue.setText(getMainActivity().getValue());

        this.handleBank();
        this.handleType();
        if(((MainActivity) getActivity()).getPlot()!=null)
            this.txvLabelPlots.setText(getMainActivity().getPlot().getRecommendedMessage());
        else
            this.txvLabelPlots.setText(R.string.plotsNotSet);


    }
}
