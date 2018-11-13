package com.mercadolivre.testeerivaldo.model.repository;

import com.mercadolivre.testeerivaldo.model.Bank;
import com.mercadolivre.testeerivaldo.model.PlotBase;
import com.mercadolivre.testeerivaldo.model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MercadoPagoAPI {

    String BASE_URL = "https://api.mercadopago.com/v1/";

    String PUBLIC_KEY = "444a9ef5-8a6b-429f-abdf-587639155d88";

    @GET("payment_methods")
    Call<List<Type>> getTypes(@Query("public_key") String public_key);

    @GET("payment_methods/card_issuers")
    Call<List<Bank>> getBanks(@Query("public_key") String public_key, @Query("payment_method_id") String payment_method_id);

    @GET("payment_methods/installments")
    Call<List<PlotBase>> getPlots(@Query("public_key") String public_key, @Query("amount")String amount, @Query("payment_method_id") String payment_method_id, @Query("issuer.id") String bank);

}
