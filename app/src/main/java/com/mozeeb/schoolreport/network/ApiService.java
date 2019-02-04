package com.mozeeb.schoolreport.network;

import com.mozeeb.schoolreport.model.berita.read.ResponseBerita;
import com.mozeeb.schoolreport.model.kegiatan.read.ResponseKegiatan;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporan;
import com.mozeeb.schoolreport.model.login.Data;
import com.mozeeb.schoolreport.model.login.ResponseLogin;
import com.mozeeb.schoolreport.model.register.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/login")
    Call<ResponseLogin> postLogin(@Body Data loginBody);


    //Insert siswa pelanggar
    @FormUrlEncoded
    @POST("api/register")
    Call<ResponseRegister> postRegister(@Field("nama") String nama,
                                     @Field("username") String username,
                                     @Field("no_telp") String no_telp,
                                     @Field("alamat") String alamat,
                                     @Field("email") String email,
                                     @Field("jenis_kelamin") String jenis_kelamin,
                                     @Field("password") String password,
                                     @Field("foto") String foto,
                                     @Field("level") String level);
    //getdata laporan
    @GET("api/get_laporan")
    Call<ResponseLaporan> getLaporan();

    //getData berita
    @GET("api/get_berita")
    Call<ResponseBerita> getBerita();

    //getkegiatan
    @GET("api/get_kegiatan")
    Call<ResponseKegiatan> getKegiatan();
}
