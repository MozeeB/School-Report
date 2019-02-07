package com.mozeeb.schoolreport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mozeeb.schoolreport.model.register.ResponseRegister;
import com.mozeeb.schoolreport.network.ApiService;
import com.mozeeb.schoolreport.network.ConfigRetrofit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.edt_nama_register)
    EditText edtNamaRegister;
    @BindView(R.id.edt_username_register)
    EditText edtUsernameRegister;
    @BindView(R.id.edt_notelp_register)
    EditText edtNotelpRegister;
    @BindView(R.id.edt_alamat_register)
    EditText edtAlamatRegister;
    @BindView(R.id.edt_email_register)
    EditText edtEmailRegister;
    @BindView(R.id.spinner_kelamin_register)
    Spinner spinnerKelaminRegister;
    @BindView(R.id.edt_password_register)
    EditText edtPasswordRegister;
    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;
    @BindView(R.id.spinner_level)
    Spinner spinnerLevel;
    @BindView(R.id.img_upload)
    ImageView imgUpload;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private ApiService apiService;

    Uri selectedImage;
    private static final int REQUEST_CAMERA = 1888;
    private static final int SELECT_FILE = 1887;
    private static final int PICK_FROM_GALLERY = 2;
    String filePath = "";
    private String encodedImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        spinnerKelamin();
        spinnerLevel();
    }

    @OnClick({R.id.btn_upload, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                break;
            case R.id.btn_register:
                registerUser();
                break;
        }
    }

    public void spinnerKelamin() {

        List<String> adapter = new ArrayList<>();
        adapter.add("Laki - laki");
        adapter.add("Perempuan");
        adapter.add("Lainnya");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKelaminRegister.setAdapter(dataAdapter);
    }

    public void spinnerLevel() {

        List<String> adapter = new ArrayList<>();
        adapter.add("Guru");
        adapter.add("Wali murid");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(dataAdapter);
    }


    private void registerUser() {
        apiService = ConfigRetrofit.getClient().create(ApiService.class);
        Call<ResponseRegister> call = apiService.postRegister(edtNamaRegister.getText().toString(), edtUsernameRegister.getText().toString(), edtNotelpRegister.getText().toString(), edtAlamatRegister.getText().toString(), edtEmailRegister.getText().toString(), spinnerKelaminRegister.toString(), edtPasswordRegister.getText().toString(), imgUpload.toString(), spinnerLevel.toString());
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    Toasty.success(RegisterActivity.this, "Daftar Sukses!!!", Toasty.LENGTH_LONG).show();
                } else {
                    Toasty.error(RegisterActivity.this, "Gagal Register", Toasty.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toasty.error(RegisterActivity.this, "Gagal Register", Toasty.LENGTH_LONG).show();

            }
        });

    }

    @OnClick(R.id.img_upload)
    public void onViewClicked() {
        ChooseGallerOrCamera();
    }

    private void ChooseGallerOrCamera() {
    }


}
