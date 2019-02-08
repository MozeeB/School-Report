package com.mozeeb.schoolreport.user.kegiatan;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.user.laporan.UserTambahActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserTambahKegiatan extends AppCompatActivity {

    @BindView(R.id.edt_nama_kegiatan)
    EditText edtNamaKegiatan;
    @BindView(R.id.edt_tujuan_kegiatan)
    EditText edtTujuanKegiatan;
    @BindView(R.id.edt_keterangan_kegiatan)
    EditText edtKeteranganKegiatan;
    @BindView(R.id.edt_jam_kegiatan)
    EditText edtJamKegiatan;
    @BindView(R.id.edt_tgl_kegiatan)
    EditText edtTglKegiatan;
    @BindView(R.id.edt_lokasi_kegiatan)
    EditText edtLokasiKegiatan;
    @BindView(R.id.img_target_kegiatan)
    ImageView imgTargetKegiatan;
    @BindView(R.id.btn_pilih_foto_kegiatan)
    Button btnPilihFotoKegiatan;
    @BindView(R.id.btn_kirim_kegiatan)
    Button btnKirimKegiatan;

    DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tambah_kegiatan);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt_jam_kegiatan, R.id.edt_tgl_kegiatan, R.id.btn_pilih_foto_kegiatan, R.id.btn_kirim_kegiatan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edt_jam_kegiatan:
                jamPicker();
                break;
            case R.id.edt_tgl_kegiatan:
                datePicker();
                break;
            case R.id.btn_pilih_foto_kegiatan:
                break;
            case R.id.btn_kirim_kegiatan:
                break;
        }
    }

    private void jamPicker() {
//        /**
//         * Calendar untuk mendapatkan waktu saat ini
//         */
        Calendar calendar = Calendar.getInstance();
//        /**
//         * Initialize TimePicker Dialog
//         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                /**
//                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
//                 */
                edtJamKegiatan.setText(+hourOfDay+" : "+minute);
            }
        },
//            Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

//                /**
//                 * Cek apakah format waktu menggunakan 24-hour format
//                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    private void datePicker() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //date picker dialog
        datePickerDialog = new DatePickerDialog(UserTambahKegiatan.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // set day of month , month and year value in the edit text
                edtTglKegiatan.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
