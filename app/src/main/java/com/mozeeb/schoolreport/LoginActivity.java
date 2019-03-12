package com.mozeeb.schoolreport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mozeeb.schoolreport.SPreferenced.Spereferen;
import com.mozeeb.schoolreport.SPreferenced.SharedPref;
import com.mozeeb.schoolreport.model.login.ResponseLogin;
import com.mozeeb.schoolreport.model.login.User;
import com.mozeeb.schoolreport.network.ApiService;
import com.mozeeb.schoolreport.network.ConfigRetrofit;
import com.mozeeb.schoolreport.user.Splashscreen;
import com.mozeeb.schoolreport.utils.Validate;
import com.pixplicity.easyprefs.library.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edt_username_login)
    EditText edtUsername;
    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    //TODO 1 Membuat vaiabel yang dibutuhkan
    //membuat variable untuk animasi loading menggunkan progress dialog
    private ProgressDialog progressDialog;
    private User loginBody;
    private ApiService apiInterface;
    private User dataUser;
    private Context mContext;

    Spereferen sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedPrefManager = new Spereferen(this);
        sharedPrefManager.checkLogin();

    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                showProgress();
                LoginUser();
                if (validateLogin())
                LoginUser();
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
    public boolean validateLogin(){
        return (!Validate.cek(edtUsername)&&!Validate.cek(edtPasswordLogin)) ? true : false;
    }

    private void LoginUser() {
        apiInterface = ConfigRetrofit.getClient().create(ApiService.class);
        Call<ResponseLogin> call = apiInterface.postLogin(edtUsername.getText().toString(),edtPasswordLogin.getText().toString());
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progressDialog.dismiss();
                if (response.body().getResult().equals("1")){
                    dataUser = response.body().getUser();
                    Toasty.success(LoginActivity.this,"login berhasil",Toast.LENGTH_LONG).show();
                    Spereferen sPereference = new Spereferen(LoginActivity.this);
                    sPereference.setUsernama(response.body().getUser().getNama());
                    sPereference.setUsername(response.body().getUser().getUsername());
                    sPereference.set_telp(response.body().getUser().getNoTelp());
                    sPereference.setAlamatPref(response.body().getUser().getAlamat());
                    sPereference.setEmailPref(response.body().getUser().getEmail());
                    sPereference.setKelamin(response.body().getUser().getJenisKelamin());
                    sPereference.setPassword(response.body().getUser().getPassword());
//                    setPref(dataUser);
                    startActivity(new Intent(LoginActivity.this, Splashscreen.class));
                }else {
                    //menampilkan response api berupa pesan ke dalam toast
                    Toasty.error(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toasty.error(LoginActivity.this,"Koneksi tidak ada",Toast.LENGTH_LONG).show();
            }
        });

    }


    private void showProgress() {
        //membuat object progress dialog
        progressDialog = new ProgressDialog(LoginActivity.this);
        //menambahkan message pada loading
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);
        //menampilkan progress dialog
        progressDialog.show();

    }
    private void setPref(User dataUser){
        Prefs.putString(SharedPref.getID(), dataUser.getId());
        Prefs.putString(SharedPref.getNAMA(), dataUser.getNama());
        Prefs.putString(SharedPref.getUSERNAME(), dataUser.getUsername());
        Prefs.putString(SharedPref.getNoTelp(), dataUser.getNoTelp());
        Prefs.putString(SharedPref.getALAMAT(), dataUser.getAlamat());
        Prefs.putString(SharedPref.getEMAIL(), dataUser.getEmail());
        Prefs.putString(SharedPref.getJenisKelamin(), dataUser.getJenisKelamin());
        Prefs.putString(SharedPref.getPASSWORD(), dataUser.getPassword());
    }
}
