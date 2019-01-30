package com.mozeeb.schoolreport.network;

import com.mozeeb.schoolreport.model.login.ResponseLogin;
import com.mozeeb.schoolreport.model.siswa.view.ResponseSiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin> postLogin(@Field("email") String email,
                                  @Field("password") String password);


    @GET("view.php")
    Call<ResponseSiswa> getAllData();
}
