package com.mozeeb.schoolreport.user.tabDaftar.siswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSiswa extends AppCompatActivity {

    @BindView(R.id.img_siswa_details)
    ImageView imgSiswaDetails;
    @BindView(R.id.tv_kelas_details)
    TextView tvKelasDetails;
    @BindView(R.id.tv_nama_siswa_detail)
    TextView tvNamaSiswaDetail;
    @BindView(R.id.tv_detail_alamat_siswa)
    TextView tvDetailAlamatSiswa;
    @BindView(R.id.tv_details_umur_siswa)
    TextView tvDetailsUmurSiswa;
    @BindView(R.id.tv_tgl_lahirdetail_siswa)
    TextView tvTglLahirdetailSiswa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_siswa);
        ButterKnife.bind(this);

        Log.e("img", getIntent().getStringExtra("img"));

        final String URL = "https://lombaidn.000webhostapp.com/apisekolah/foto/";

        Glide.with(this).load(URL +getIntent().getStringExtra("img")).into(imgSiswaDetails);
        tvNamaSiswaDetail.setText(getIntent().getStringExtra("nama"));
        tvKelasDetails.setText(getIntent().getStringExtra("kelas"));
        tvDetailAlamatSiswa.setText(getIntent().getStringExtra("alamat"));
        tvDetailsUmurSiswa.setText(getIntent().getStringExtra("umur"));
        tvTglLahirdetailSiswa.setText(getIntent().getStringExtra("tgl"));
    }

}
