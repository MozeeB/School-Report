package com.mozeeb.schoolreport.user.peraturan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPeraturan extends AppCompatActivity {

    @BindView(R.id.tv_judul_peraturan)
    TextView tvJudulPeraturan;
    @BindView(R.id.tv_penulis_detailPeraturan)
    TextView tvPenulisDetailPeraturan;
    @BindView(R.id.tv_isi_peraturan)
    TextView tvIsiPeraturan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peraturan);
        ButterKnife.bind(this);

        tvJudulPeraturan.setText(getIntent().getStringExtra("judul"));
        tvPenulisDetailPeraturan.setText(getIntent().getStringExtra("penulis"));
        tvIsiPeraturan.setText(getIntent().getStringExtra("isi"));
    }
}
