package com.mercadolivre.testeerivaldo.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mercadolivre.testeerivaldo.model.Type;
import com.mercadolivre.testeerivaldo.model.repository.MercadoPagoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypeViewModel extends ViewModel {

    private MutableLiveData<List<Type>> typeList;

    public LiveData<List<Type>> getTypes() {
        if (typeList == null) {
            typeList = new MutableLiveData<List<Type>>();
            loadTypes();
        }

        return typeList;
    }


    private void loadTypes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MercadoPagoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MercadoPagoAPI api = retrofit.create(MercadoPagoAPI.class);
        Call<List<Type>> call = api.getTypes(MercadoPagoAPI.PUBLIC_KEY);


        call.enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {

                typeList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }
}
