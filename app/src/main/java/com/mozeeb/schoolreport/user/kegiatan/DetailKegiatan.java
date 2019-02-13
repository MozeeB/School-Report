package com.mozeeb.schoolreport.user.kegiatan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailKegiatan extends AppCompatActivity {

    @BindView(R.id.img_kegiatan_detail)
    ImageView imgKegiatanDetail;
    @BindView(R.id.tv_nama_kegiatan)
    TextView tvNamaKegiatan;
    @BindView(R.id.tv_tujuan)
    TextView tvTujuan;
    @BindView(R.id.tv_jam_kegiatan_detail)
    TextView tvJamKegiatanDetail;
    @BindView(R.id.tv_tanggal_kegiatan_detail)
    TextView tvTanggalKegiatanDetail;
    @BindView(R.id.tv_lokasi_kegiatan_details)
    TextView tvLokasiKegiatanDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kegiatan);
        ButterKnife.bind(this);

        Glide.with(this).load(getIntent().getStringExtra("img")).into(imgKegiatanDetail);
        tvNamaKegiatan.setText(getIntent().getStringExtra("judul"));
        tvTujuan.setText(getIntent().getStringExtra("tujuan"));
        tvJamKegiatanDetail.setText(getIntent().getStringExtra("jam"));
        tvTanggalKegiatanDetail.setText(getIntent().getStringExtra("tgl"));
        tvLokasiKegiatanDetails.setText(getIntent().getStringExtra("lokasi"));

    }
}
