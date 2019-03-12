package com.mozeeb.schoolreport.network;

import com.mozeeb.schoolreport.model.berita.read.ResponseBerita;
import com.mozeeb.schoolreport.model.guru.read.ResponseGuru;
import com.mozeeb.schoolreport.model.kegiatan.read.ResponseKegiatan;
import com.mozeeb.schoolreport.model.laporan.delete.ResponseLapor;
import com.mozeeb.schoolreport.model.laporan.insert.ResponseLaporan;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporanRead;
import com.mozeeb.schoolreport.model.login.ResponseLogin;
import com.mozeeb.schoolreport.model.login.User;
import com.mozeeb.schoolreport.model.peraturan.read.ResponsePeraturan;
import com.mozeeb.schoolreport.model.register.ResponseRegister;
import com.mozeeb.schoolreport.model.siswa.insert.ResponseDataSiswa;
import com.mozeeb.schoolreport.model.siswa.read.ResponseSiswa;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @FormUrlEncoded
    @POST("user/register.php")
    Call<ResponseRegister> postRegister(@Field("nama") String nama,
                                        @Field("username") String username,
                                        @Field("no_telp") String no_telp,
                                        @Field("alamat") String alamat,
                                        @Field("email") String email,
                                        @Field("jenis_kelamin") String jenis_kelamin,
                                        @Field("password") String password,
                                        @Field("confirm_password") String confirm_password);
//                                        @Part MultipartBody.Part foto);
//                                        @Part("level") String level);
    //getdata laporan
    @GET("laporan/read.php")
    Call<ResponseLaporanRead> getLaporan();

    @Multipart
    @POST("laporan/create.php")
    Call<ResponseLaporan> postLaporan(@Part("nama") RequestBody nama,
                                      @Part("kelas") RequestBody kelas,
                                      @Part("wali") RequestBody wali,
                                      @Part("poin") RequestBody poin,
                                      @Part("melanggar") RequestBody melanggar,
                                      @Part("keterangan")RequestBody keterangan,
                                      @Part("tgl_lapor") RequestBody tgl_laporan,
                                      @Part("pelapor") RequestBody pelapor,
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

