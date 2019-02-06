package com.mozeeb.schoolreport.user.tabDaftar.guru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.guru.insert.ResponseGuru;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertGuru extends AppCompatActivity {

    @BindView(R.id.edt_nama_guru)
    EditText edtNamaGuru;
    @BindView(R.id.edt_umur_guru)
    EditText edtUmurGuru;
    @BindView(R.id.edt_tgl_lahir_guru)
    EditText edtTglLahirGuru;
    @BindView(R.id.edt_notlp_guru)
    EditText edtNotlpGuru;
    @BindView(R.id.edt_alamat_guru)
    EditText edtAlamatGuru;
    @BindView(R.id.iv_upload_guru)
    ImageView ivUploadGuru;
    @BindView(R.id.btn_simpan_guru)
    Button btnSimpanGuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_guru);
        ButterKnife.bind(this);

        insetDataGuru();

    }

    private void insetDataGuru() {
        ConfigRetrofit.getInstance().postDaftarGuru(edtNamaGuru.getText().toString(),edtUmurGuru.getText().toString(),edtTglLahirGuru.getText().toString(),edtNotlpGuru.getText().toString(),edtAlamatGuru.getText().toString(),ivUploadGuru.toString())
                .enqueue(new Callback<ResponseGuru>() {
                    @Override
                    public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseGuru> call, Throwable t) {

                    }
                });
    }

    @OnClick({R.id.iv_upload_guru, R.id.btn_simpan_guru})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_upload_guru:
                break;
            case R.id.btn_simpan_guru:
                break;
        }
    }
}
