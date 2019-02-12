package com.mozeeb.schoolreport.user.tabDaftar.siswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.siswa.insert.ResponseDataSiswa;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertSiswa extends AppCompatActivity {


    @BindView(R.id.edt_nama_siswa)
    EditText edtNamaSiswa;
    @BindView(R.id.spinner_kelas_siswa)
    Spinner spinnerKelasSiswa;
    @BindView(R.id.edt_alamat_siswa)
    EditText edtAlamatSiswa;
    @BindView(R.id.edt_umur_siswa)
    EditText edtUmurSiswa;
    @BindView(R.id.edt_tanggal_pelanggar)
    EditText edtTanggalPelanggar;
    @BindView(R.id.img_siswa_upload)
    ImageView imgSiswaUpload;
    @BindView(R.id.btn_pilih_foto_siswa)
    Button btnPilihFotoSiswa;
    @BindView(R.id.btn_simpan_siswa)
    Button btnSimpanSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_siswa);
        ButterKnife.bind(this);

        insertDataSiswa();
    }

    private void insertDataSiswa() {
        ConfigRetrofit.getInstance().postDaftarSiswa(edtNamaSiswa.getText().toString(), spinnerKelasSiswa.toString(), edtAlamatSiswa.getText().toString(), edtUmurSiswa.getText().toString(), edtTanggalPelanggar.getText().toString(), imgSiswaUpload.toString())
                .enqueue(new Callback<ResponseDataSiswa>() {
                    @Override
                    public void onResponse(Call<ResponseDataSiswa> call, Response<ResponseDataSiswa> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseDataSiswa> call, Throwable t) {

                    }
                });
    }


    @OnClick({R.id.btn_pilih_foto_siswa, R.id.btn_simpan_siswa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pilih_foto_siswa:
                break;
            case R.id.btn_simpan_siswa:
                break;
        }
    }
}
