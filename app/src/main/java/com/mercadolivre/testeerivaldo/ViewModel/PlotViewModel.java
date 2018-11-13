package com.mercadolivre.testeerivaldo.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mercadolivre.testeerivaldo.model.Bank;
import com.mercadolivre.testeerivaldo.model.Plot;
import com.mercadolivre.testeerivaldo.model.PlotBase;
import com.mercadolivre.testeerivaldo.model.Type;
import com.mercadolivre.testeerivaldo.model.repository.MercadoPagoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlotViewModel extends ViewModel {
    private MutableLiveData<List<PlotBase>> plotBase;

    public LiveData<List<PlotBase>> getPlots(Type type, Bank bank, String value) {
        if (plotBase == null) {
            plotBase = new MutableLiveData<List<PlotBase>>();
            loadPlots(type, value, bank);
        }

        return plotBase;
    }


    private void loadPlots(Type type, String value, Bank bank) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MercadoPagoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MercadoPagoAPI api = retrofit.create(MercadoPagoAPI.class);
        Call<List<PlotBase>> call = api.getPlots(MercadoPagoAPI.PUBLIC_KEY, value, type.getId(), bank.getId());


        call.enqueue(new Callback<List<PlotBase>>() {
            @Override
            public void onResponse(Call<List<PlotBase>> call, Response<List<PlotBase>> response) {

                plotBase.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<PlotBase>> call, Throwable t) {
                t.getStackTrace();
                Log.d("Error", t.toString());
            }
        });
    }
}
