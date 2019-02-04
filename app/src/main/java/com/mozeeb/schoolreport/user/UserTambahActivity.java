package com.mozeeb.schoolreport.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mozeeb.schoolreport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserTambahActivity extends AppCompatActivity {


    @BindView(R.id.edt_namaSiswa)
    EditText edtNamaSiswa;
    @BindView(R.id.edt_kelas)
    EditText edtKelas;
    @BindView(R.id.edt_pelanggaran)
    EditText edtPelanggaran;
    @BindView(R.id.edt_namaGuru)
    EditText edtNamaGuru;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.edt_tanggal)
    EditText edtTanggal;

    @BindView(R.id.edt_poin)
    EditText edtPoin;

    private String namaSiswa, kelas, pelanggaran, namaGuru, tanggal;
    private String poin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:

                getDataUser();


                if (TextUtils.isEmpty(namaGuru)) {
                    edtNamaGuru.setError(getString(R.string.error_message));
                    edtNamaGuru.requestFocus();
                } else if (TextUtils.isEmpty(namaSiswa)) {
                    edtNamaSiswa.setError(getString(R.string.error_message));
                    edtNamaSiswa.requestFocus();
                } else if (TextUtils.isEmpty(kelas)) {
                    edtKelas.setError(getString(R.string.error_message));
                    edtKelas.requestFocus();
                } else if (TextUtils.isEmpty(pelanggaran)) {
                    edtPelanggaran.setError(getString(R.string.error_message));
                    edtPelanggaran.requestFocus();
                } else if (TextUtils.isEmpty(tanggal)) {
                    edtTanggal.setError(getString(R.string.error_message));
                    edtTanggal.requestFocus();
                }else if (TextUtils.isEmpty(poin)) {
                    edtPoin.setError(getString(R.string.error_message));
                    edtPoin.requestFocus();
                } else {
//                    sendData();
                }
                break;
        }
    }

    private void getDataUser() {
        namaGuru = edtNamaGuru.getText().toString();
        namaSiswa = edtNamaSiswa.getText().toString();
        kelas = edtKelas.getText().toString();
        pelanggaran = edtPelanggaran.getText().toString();
        tanggal = edtTanggal.getText().toString();
        poin = edtPoin.getText().toString();
    }

//    private void sendData() {
//        ConfigRetrofit.getInstance()
////                .postRegister(null)
////                .enqueue(new Callback<ResponseInsertData>() {
//            @Override
//            public void onResponse(Call<ResponseInsertData> call, Response<ResponseInsertData> response) {
//                if (response != null && response.isSuccessful()) {
//                    String msg = response.body().getMessage();
//                    String result = String.valueOf(response.body().getCode());
//
//                    Log.d("UserTambahActivity", msg);
//
//                    Toast.makeText(UserTambahActivity.this, "Success To Add " + msg, Toast.LENGTH_SHORT).show();
//
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseInsertData> call, Throwable t) {
//                Toast.makeText(UserTambahActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                finish();
//
//            }
//        });
//    }
}
