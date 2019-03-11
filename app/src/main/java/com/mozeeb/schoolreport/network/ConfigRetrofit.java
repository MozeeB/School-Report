package com.mozeeb.schoolreport.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

        private static Retrofit retrofit = null;
        private static final String BASE_URL = "https://lombaidn.000webhostapp.com/apisekolah/";

        //membuat metdod return getClient
        public static Retrofit getClient() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;

    }
    public static ApiService getInstance() {
        return getClient().create(ApiService.class);

    }
}
