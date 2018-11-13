package com.mercadolivre.testeerivaldo.View;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.mercadolivre.testeerivaldo.R;
import com.mercadolivre.testeerivaldo.model.Bank;
import com.mercadolivre.testeerivaldo.model.Plot;
import com.mercadolivre.testeerivaldo.model.Type;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    private boolean modeInit = true;

    FragmentManager fm = getSupportFragmentManager();

    private ValueFragment vlFragment = (ValueFragment) fm.findFragmentByTag(fgtValue);
    private BankFragment bkFragment = (BankFragment) fm.findFragmentByTag(fgtBank);
    private TypeFragment tpFragment = (TypeFragment)fm.findFragmentByTag(fgtType);
    private PlotsFragment ptFragment = (PlotsFragment) fm.findFragmentByTag(fgtPlots);

    public static String fgtType = "type-payment", fgtBank = "bank-payment",fgtPlots = "plots-payment",fgtValue = "value-payment";

    private String currentStep = null;

    private Type typeSelected = null;
    private Bank bankSelected = null;
    private Plot plot = null;
    private String value;

    @BindView(R.id.btnAction) Button btnAction;

    @OnClick(R.id.btnAction) void onPressButtonMain(){
        if(!modeInit && vlFragment!=null){
            typeSelected = null;
            bankSelected = null;
            plot = null;
            value = "";

            vlFragment.handleFieds();

            return;
        }
        if(currentStep==fgtValue){
            vlFragment.pressButton();
        }else if(currentStep==fgtType){
            openFragment(fgtValue);
        }else if(currentStep==fgtBank){
            openFragment(fgtType);
        }else if(currentStep==fgtPlots){
            openFragment(fgtBank);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if(savedInstanceState == null)
            this.openFragment(fgtValue);
    }

    public void openFragment(String fragment){
        FragmentTransaction ft = fm.beginTransaction();

        this.currentStep = fragment;

        modeInit = true;

        if(fragment==fgtValue){
            if(isCompletePayment()){
                modeInit = false;
                btnAction.setText(R.string.cleanPayment);
            }else
                btnAction.setText(R.string.iniciarPayment);
            vlFragment = new ValueFragment();
            ft.replace(R.id.main_content, vlFragment, fgtValue);
        }else if(fragment==fgtType){
            btnAction.setText(R.string.labelBack);
            tpFragment = new TypeFragment();
            ft.replace(R.id.main_content, tpFragment, fgtType);
        }else if(fragment==fgtBank){
            btnAction.setText(R.string.labelBack);
            bkFragment = new BankFragment();
            ft.replace(R.id.main_content, bkFragment, fgtBank);
        }else if(fragment==fgtPlots){
            btnAction.setText(R.string.labelBack);
            ptFragment = new PlotsFragment();
            ft.replace(R.id.main_content, ptFragment, fgtPlots);
        }
        ft.addToBackStack("pilha");
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    public ArrayList<Type> getTypes() {
        return new ArrayList<Type>();
    }

    public Type getType(){
        return typeSelected;
    }

    public void setType(Type type){
        this.typeSelected = type;
        this.openFragment(fgtBank);
    }

    public void setBank(Bank bank) {
        this.bankSelected = bank;
        this.openFragment(fgtPlots);
    }

    public Bank getBank() {
        return bankSelected;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isCompletePayment(){
        return getType()!=null && getValue() !=null && getBank() !=null ;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
        this.openFragment(fgtValue);
    }

    public Plot getPlot(){
        return this.plot;
    }
}
