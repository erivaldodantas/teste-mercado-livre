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
import com.mercadolivre.testeerivaldo.model.Type;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm = getSupportFragmentManager();

    private ValueFragment vlFragment = (ValueFragment) fm.findFragmentByTag(fgtValue);
    private BankFragment bkFragment = (BankFragment) fm.findFragmentByTag(fgtBank);
    private TypeFragment tpFragment = (TypeFragment)fm.findFragmentByTag(fgtType);

    public static String fgtType = "type-payment", fgtBank = "bank-payment",fgtPlots = "plots-payment",fgtValue = "value-payment";

    private String currentStep = null;

    private Type typeSelected = null;
    private Bank bankSelected = null;
    private String value;

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

        if(fragment==fgtValue){

            vlFragment = new ValueFragment();
            ft.replace(R.id.main_content, vlFragment, fgtValue);
        }else if(fragment==fgtType){

            tpFragment = new TypeFragment();
            ft.replace(R.id.main_content, tpFragment, fgtType);
        }else if(fragment==fgtBank){

            bkFragment = new BankFragment();
            ft.replace(R.id.main_content, bkFragment, fgtBank);
        }else if(fragment==fgtPlots){

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

    public void setTypeAndValue(Type type, String value){
        this.value = value;
        this.typeSelected = type;
        this.openFragment(fgtBank);
    }

    public void setBank(Bank bank) {
        this.bankSelected = bank;
    }

    public String getValue() {
        return this.value;
    }
}
