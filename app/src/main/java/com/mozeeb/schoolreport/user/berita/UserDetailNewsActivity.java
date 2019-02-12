package com.mozeeb.schoolreport.user.berita;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailNewsActivity extends AppCompatActivity {

    @BindView(R.id.iv_details_berita)
    ImageView ivDetailsBerita;
    @BindView(R.id.konten_berita)
    TextView kontenBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Glide.with(this).load(getIntent().getIntExtra("img", 1)).into(ivDetailsBerita);
        kontenBerita.setText(getIntent().getStringExtra("konten"));
    }



}
