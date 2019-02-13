package com.mozeeb.schoolreport.network;

import com.mozeeb.schoolreport.model.berita.read.ResponseBerita;
import com.mozeeb.schoolreport.model.guru.insert.ResponseGuru;
import com.mozeeb.schoolreport.model.guru.read.ResponseDaftarGuru;
import com.mozeeb.schoolreport.model.kegiatan.read.ResponseKegiatan;
import com.mozeeb.schoolreport.model.laporan.delete.ResponseLapor;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporan;
import com.mozeeb.schoolreport.model.login.Data;
import com.mozeeb.schoolreport.model.login.ResponseLogin;
import com.mozeeb.schoolreport.model.peraturan.read.ResponsePeraturan;
import com.mozeeb.schoolreport.model.register.ResponseRegister;
import com.mozeeb.schoolreport.model.siswa.insert.ResponseDataSiswa;
import com.mozeeb.schoolreport.model.siswa.read.ResponseSiswa;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @POST("api/login")
    Call<ResponseLogin> postLogin(@Body Data loginBody);


    //Insert siswa pelanggar
    @FormUrlEncoded
    @Multipart
    @POST("api/register")
    Call<ResponseRegister> postRegister(@Field("nama") String nama,
                                        @Field("username") String username,
                                        @Field("no_telp") String no_telp,
                                        @Field("alamat") String alamat,
                                        @Field("email") String email,
                                        @Field("jenis_kelamin") String jenis_kelamin,
                                        @Field("password") String password,
                                        @Part MultipartBody.Part foto,
                                        @Field("level") String level);
    //getdata laporan
    @GET("api/get_laporan")
    Call<ResponseLaporan> getLaporan();

    @FormUrlEncoded
    @POST("api/post_laporan")
    Call<ResponseLaporan> postLaporan(@Field("nama") String nama,
                                      @Field("kelas") String kelas,
                                      @Field("wali") String wali,
                                      @Field("poin") Integer poin,
                                      @Field("melanggar") String melanggar,
                                      @Field("tgl_lapor") String tgl_laporan,
                                      @Field("foto") String foto);
    @DELETE("api/hapus_laporan")
    Call<ResponseLapor> delLapo(@Field("id") Integer id);

    //getData berita
    @GET("api/get_berita")
    Call<ResponseBerita> getBerita();

    //getkegiatan
    @GET("api/get_kegiatan")
    Call<ResponseKegiatan> getKegiatan();

    //getDataDaftarSiswa
    @GET("api/get_siswa")
    Call<ResponseSiswa> getDaftarSiswa();
    //postSiswa/menambah
    @FormUrlEncoded
    @POST("api/tambah_siswa")
    Call<ResponseDataSiswa> postDaftarSiswa(@Field("nama") String nama,
                                            @Field("kelas") String kelas,
                                            @Field("alamat") String alamt,
                                            @Field("umur") String umur,
                                            @Field("tgl_lahir") String tgl_lahir,
                                            @Field("foto") String foto);

    //getDataGuru
    @GET("api/get_guru")
    Call<ResponseDaftarGuru> getGuru();
    //postDataGuru
    @FormUrlEncoded
    @POST
    Call<ResponseGuru> postDaftarGuru(@Field("nama") String nama,
                                      @Field("umur") String umur,
                                      @Field("tgl_lahir") String tgl_lahir,
                                      @Field("no_telp") String no_telp,
                                      @Field("alamat") String alamat,
                                      @Field("foto") String foto);
    //getPeraturan
    @GET("api/get_peraturan")
    Call<ResponsePeraturan> getPeraturan();
}

