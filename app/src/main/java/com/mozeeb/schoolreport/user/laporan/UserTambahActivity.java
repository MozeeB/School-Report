package com.mozeeb.schoolreport.user.laporan;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.mozeeb.schoolreport.R;
import com.mozeeb.schoolreport.RegisterActivity;
import com.mozeeb.schoolreport.model.laporan.read.ResponseLaporan;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @BindView(R.id.edt_keterangan_pelanggar)
    EditText edtKeteranganPelanggar;
    private String namaSiswa, kelas, pelanggaran, namaGuru, tanggal;
    private String poin;


    private Uri filepath;
    private String mediapath;
    private Bitmap mPhoto;
    String part_image;

    //permission granted
    private int STORAGE_PERMISSION_CODE = 1;

    public final int REQ_CHOOSE_FILE_REGISTER = 100;
    public static final String UPLOAD_REGISTER_URL = "https://lombaidn.000webhostapp.com/apisekolah/user/register.php";



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
                ChooseImage(REQ_CHOOSE_FILE_REGISTER);
                break;
            case R.id.btn_kirim_lporan:
                sendData();
                break;
        }
    }

    private void sendData() {
        try{
            mediapath = getPath(filepath);
            Toasty.success(this,"Succes to Send", Toasty.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            new MultipartUploadRequest(this, UPLOAD_REGISTER_URL )
                    .addParameter("nama", edtNamaPelanggar.getText().toString())
                    .addParameter("kelas", spinnerKelasPelanggar.toString())
                    .addParameter("wali", edtWaliMuridPelanggar.getText().toString())
                    .addParameter("poin", edtPoinPelanggar.getText().toString())
                    .addParameter("melanggar", edtJenisPelanggarannya.toString())
                    .addParameter("keterangan", edtKeteranganPelanggar.toString())
                    .addParameter("tgl_lapor", edtTanggalPelanggar.toString())
                    .addParameter("pelapor", edtPelapor.getText().toString())
                    .addFileToUpload(mediapath, "foto")
                    .setMaxRetries(2)
                    .startUpload();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        part_image = getPath(filepath);
////
//        File imagefile = new File(part_image);
//        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-data"),imagefile);
//        MultipartBody.Part partImage = MultipartBody.Part.createFormData("foto", imagefile.getName(),reqBody);
//
//        ConfigRetrofit.getInstance().postLaporan(edtNamaPelanggar.getText().toString(),
//                kelas.toString(), edtWaliMuridPelanggar.getText().toString(),
//                edtPoinPelanggar.getText().toString(), edtJenisPelanggarannya.toString(), edtKeteranganPelanggar.getText().toString(),
//                edtTanggalPelanggar.getText().toString(), edtPelapor.getText().toString(),partImage)
//                .enqueue(new Callback<ResponseLaporan>() {
//            @Override
//            public void onResponse(Call<ResponseLaporan> call, Response<ResponseLaporan> response) {
//                if (response.body().isStatus()){
//                    Toasty.success(UserTambahActivity.this, response.message(), Toasty.LENGTH_SHORT).show();
//                }else {
//                    Toasty.error(UserTambahActivity.this, response.message(), Toasty.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseLaporan> call, Throwable t) {
//
//            }
//        });
    }

    private void ChooseImage(int requestCode){
        Intent toGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(toGalery, requestCode);
        Log.i("Gallery", "Masuk Gallery");

        if (ContextCompat.checkSelfPermission(UserTambahActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toasty.success(UserTambahActivity.this, "You have already granted this permission!", Toasty.LENGTH_SHORT).show();
        } else {
            requestStoragePermission();
        }
    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(UserTambahActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == REQ_CHOOSE_FILE_REGISTER){
                if (data.getData() != null){
                    filepath = data.getData();
//                    Uri seletedImage = data.getData();
                    Log.i("datanya disini",filepath.toString());
                }
                try {
                    mPhoto = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    ivLaporanTambah.setImageBitmap(mPhoto);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String getPath(Uri filepath){
        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images
                        .Media._ID +  " = ? ", new String[]{document_id}, null);
//        String provider = "com.android.providers.media.MediaProvider";
//
//        Uri uri = Uri.parse("content://media/external/images/media");
//
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        grantUriPermission(provider, uri, Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);


        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;


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
