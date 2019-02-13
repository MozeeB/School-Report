package com.mozeeb.schoolreport.user.tabDaftar.guru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailGuru extends AppCompatActivity {

    @BindView(R.id.img_detail_guru)
    ImageView imgDetailGuru;
    @BindView(R.id.tv_nama_guru_detail)
    TextView tvNamaGuruDetail;
    @BindView(R.id.tv_detail_umur)
    TextView tvDetailUmur;
    @BindView(R.id.tv_tgl_lahir_detail)
    TextView tvTglLahirDetail;
    @BindView(R.id.tv_detail_notelp)
    TextView tvDetailNotelp;
    @BindView(R.id.tv_detail_alamat)
    TextView tvDetailAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guru);
        ButterKnife.bind(this);

        Glide.with(this).load(getIntent().getStringExtra("img")).into(imgDetailGuru);
        tvNamaGuruDetail.setText(getIntent().getStringExtra("nama"));
        tvDetailUmur.setText(getIntent().getStringExtra("umur"));
        tvTglLahirDetail.setText(getIntent().getStringExtra("tgl"));
        tvDetailNotelp.setText(getIntent().getStringExtra("notelp"));
        tvDetailAlamat.setText(getIntent().getStringExtra("alamat"));
    }
}
