package com.mercadolivre.testeerivaldo.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mercadolivre.testeerivaldo.model.Bank;
import com.mercadolivre.testeerivaldo.model.Bank;
import com.mercadolivre.testeerivaldo.model.Type;
import com.mercadolivre.testeerivaldo.model.repository.MercadoPagoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankViewModel extends ViewModel {

    private MutableLiveData<List<Bank>> bankList;

    public LiveData<List<Bank>> getBanks(Type type) {
        if (bankList == null) {
            bankList = new MutableLiveData<List<Bank>>();
            loadBanks(type);
        }

        return bankList;
    }


    private void loadBanks(Type type) {

        if(type==null){
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MercadoPagoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MercadoPagoAPI api = retrofit.create(MercadoPagoAPI.class);
        Call<List<Bank>> call = api.getBanks(MercadoPagoAPI.PUBLIC_KEY, type.getId());


        call.enqueue(new Callback<List<Bank>>() {
            @Override
            public void onResponse(Call<List<Bank>> call, Response<List<Bank>> response) {

                bankList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Bank>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }
}
