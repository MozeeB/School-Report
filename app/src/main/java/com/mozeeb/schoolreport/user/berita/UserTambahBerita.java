package com.mozeeb.schoolreport.user.berita;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserTambahBerita extends AppCompatActivity {

    @BindView(R.id.edt_judul_berita)
    EditText edtJudulBerita;
    @BindView(R.id.edt_konten_berita)
    EditText edtKontenBerita;
    @BindView(R.id.edt_tgl_terbit)
    EditText edtTglTerbit;
    @BindView(R.id.edt_penerbit)
    EditText edtPenerbit;
    @BindView(R.id.img_berita_target)
    ImageView imgBeritaTarget;
    @BindView(R.id.btn_choose_berita)
    Button btnChooseBerita;
    @BindView(R.id.btn_kirim_berita)
    Button btnKirimBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tambah_berita);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_berita_target, R.id.btn_choose_berita, R.id.btn_kirim_berita})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_berita_target:
                break;
            case R.id.btn_choose_berita:
                break;
            case R.id.btn_kirim_berita:
                break;
        }
    }

    public void postBerita(){

    }
}
