package com.mozeeb.schoolreport.user.laporan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsLaporan extends AppCompatActivity {

    @BindView(R.id.img_lapor)
    ImageView imgLapor;
    @BindView(R.id.tv_nama_lapor)
    TextView tvNamaLapor;
    @BindView(R.id.tv_kelas_lapor)
    TextView tvKelasLapor;
    @BindView(R.id.tv_wali_lapor)
    TextView tvWaliLapor;
    @BindView(R.id.tv_poin_lapor)
    TextView tvPoinLapor;
    @BindView(R.id.tv_jenis_pelangar)
    TextView tvJenisPelangar;
    @BindView(R.id.tv_keterangan_lapor)
    TextView tvKeteranganLapor;
    @BindView(R.id.tgl_lapor)
    TextView tglLapor;
    @BindView(R.id.tv_pelapor)
    TextView tvPelapor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_laporan);
        ButterKnife.bind(this);

        final String URL = "https://lombaidn.000webhostapp.com/apisekolah/foto/";

        Glide.with(this).load(URL + getIntent().getStringExtra("img")).into(imgLapor);
        tvNamaLapor.setText(getIntent().getStringExtra("nama"));
        tvKelasLapor.setText(getIntent().getStringExtra("kelas"));
        tvWaliLapor.setText(getIntent().getStringExtra("wali"));
        tvPoinLapor.setText(getIntent().getStringExtra("poin"));
        tvJenisPelangar.setText(getIntent().getStringExtra("melanggar"));
        tvKeteranganLapor.setText(getIntent().getStringExtra("keterangan"));
        tglLapor.setText(getIntent().getStringExtra("tgl"));
        tvPelapor.setText(getIntent().getStringExtra("pelapor"));


    }


}
