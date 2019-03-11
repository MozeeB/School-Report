package com.mozeeb.schoolreport.network;

import com.mozeeb.schoolreport.model.berita.read.ResponseBerita;
import com.mozeeb.schoolreport.model.guru.insert.ResponseGuruTambah;
import com.mozeeb.schoolreport.model.guru.read.ResponseGuru;
import com.mozeeb.schoolreport.model.kegiatan.read.ResponseKegiatan;
import com.mozeeb.schoolreport.model.laporan.delete.ResponseLapor;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporan;
import com.mozeeb.schoolreport.model.login.ResponseLogin;
import com.mozeeb.schoolreport.model.login.User;
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

    @POST("user/login.php")
    Call<ResponseLogin> postLogin(@Body User loginBody);


    //Insert siswa pelanggar
//    @FormUrlEncoded
    @Multipart
    @POST("api/register")
    Call<ResponseRegister> postRegister(@Part("nama") String nama,
                                        @Part("username") String username,
                                        @Part("no_telp") String no_telp,
                                        @Part("alamat") String alamat,
                                        @Part("email") String email,
                                        @Part("jenis_kelamin") String jenis_kelamin,
                                        @Part("password") String password,
                                        @Part MultipartBody.Part foto);
//                                        @Part("level") String level);
    //getdata laporan
    @GET("laporan/read.php")
    Call<ResponseLaporan> getLaporan();

    @Multipart
    @POST("api/post_laporan")
    Call<ResponseLaporan> postLaporan(@Part("nama") String nama,
                                      @Part("kelas") String kelas,
                                      @Part("wali") String wali,
                                      @Part("poin") String poin,
                                      @Part("melanggar") String melanggar,
                                      @Part("keterangan")String keterangan,
                                      @Part("tgl_lapor") String tgl_laporan,
                                      @Part("perlapor") String pelapor,
                                      @Part MultipartBody.Part  foto);
    @DELETE("api/hapus_laporan")
    Call<ResponseLapor> delLapo(@Field("id") Integer id);

    //getData berita
    @GET("berita/read.php")
    Call<ResponseBerita> getBerita();

    //getkegiatan
    @GET("kegiatan/read.php")
    Call<ResponseKegiatan> getKegiatan();

    //getDataDaftarSiswa
    @GET("siswa/read.php")
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
    @GET("guru/read.php")
    Call<ResponseGuru> getGuru();

    //getPeraturan
    @GET("peraturan/read.php")
    Call<ResponsePeraturan> getPeraturan();
}

