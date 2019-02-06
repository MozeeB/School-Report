package com.mozeeb.schoolreport.user.laporan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporan;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTambahActivity extends AppCompatActivity {


    @BindView(R.id.edt_nama_pelanggar)
    EditText edtNamaPelanggar;
    @BindView(R.id.spinner_kelas_pelanggar)
    Spinner spinnerKelasPelanggar;
    @BindView(R.id.edt_wali_murid_pelanggar)
    EditText edtWaliMuridPelanggar;
    @BindView(R.id.edt_poin_pelanggar)
    EditText edtPoinPelanggar;
    @BindView(R.id.edt_tanggal_pelanggar)
    EditText edtTanggalPelanggar;
    @BindView(R.id.edt_pelapor)
    EditText edtPelapor;
    @BindView(R.id.iv_laporan_tambah)
    ImageView ivLaporanTambah;
    @BindView(R.id.btn_pilih_foto_pelanggar)
    Button btnPilihFotoPelanggar;
    @BindView(R.id.btn_kirim_lporan)
    Button btnKirimLporan;

    DatePickerDialog datePickerDialog;
    @BindView(R.id.edt_jenis_pelanggarannya)
    Spinner edtJenisPelanggarannya;
    private String namaSiswa, kelas, pelanggaran, namaGuru, tanggal;
    private String poin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        ButterKnife.bind(this);

        spinnerKelas();
        spinnerJenis();

    }

    public void spinnerKelas() {

        List<String> adapter = new ArrayList<>();
        adapter.add("X RPL A");
        adapter.add("X RPL B");
        adapter.add("X RPL C");
        adapter.add("X RPL D");
        adapter.add("X TKJ A");
        adapter.add("X TKJ D");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKelasPelanggar.setAdapter(dataAdapter);
    }
    public void spinnerJenis() {

        List<String> adapter = new ArrayList<>();
        adapter.add("Ringan");
        adapter.add("Sedang");
        adapter.add("Berat");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtJenisPelanggarannya.setAdapter(dataAdapter);
    }


    @OnClick({R.id.btn_pilih_foto_pelanggar, R.id.btn_kirim_lporan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pilih_foto_pelanggar:
                break;
            case R.id.btn_kirim_lporan:
                sendData();
                break;
        }
    }

    private void sendData() {
        ConfigRetrofit.getInstance().postLaporan(null, null, null, null, null, null, null)
                .enqueue(new Callback<ResponseLaporan>() {
                    @Override
                    public void onResponse(Call<ResponseLaporan> call, Response<ResponseLaporan> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseLaporan> call, Throwable t) {

                    }
                });
    }

    public void postData() {
        edtNamaPelanggar.getText().toString();
        kelas.toString();
        edtWaliMuridPelanggar.getText().toString();
        edtPoinPelanggar.getText().toString();
        edtJenisPelanggarannya.toString();
        edtTanggalPelanggar.getText().toString();
        edtPelapor.getText().toString();
        ivLaporanTambah.toString();
    }

    @OnClick(R.id.edt_tanggal_pelanggar)
    public void onViewClicked() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //date picker dialog
        datePickerDialog = new DatePickerDialog(UserTambahActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // set day of month , month and year value in the edit text
                edtTanggalPelanggar.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
