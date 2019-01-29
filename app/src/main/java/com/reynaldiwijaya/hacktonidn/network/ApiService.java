package com.reynaldiwijaya.hacktonidn.network;

import com.reynaldiwijaya.hacktonidn.model.insert.ResponseInsertData;
import com.reynaldiwijaya.hacktonidn.model.view.ResponseSiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseInsertData> insertData (
            @Field("nama") String nama,
            @Field("kelas") String kelas,
            @Field("pelanggaran") String pelanggaran,
            @Field("tanggal") String tanggal,
            @Field("poin") String poin,
            @Field("ustad") String ustad
    );

    @GET("view.php")
    Call<ResponseSiswa> getAllData();
}
