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

import com.mozeeb.schoolreport.SPreferenced.SPereference;
import com.mozeeb.schoolreport.model.login.Data;
import com.mozeeb.schoolreport.model.login.ResponseLogin;
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
    private Data loginBody;
    private ApiService apiInterface;
    private Data dataUser;
    private Context mContext;

    SPereference sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);



    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                showProgress();
                getData();
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
        Call<ResponseLogin> call = apiInterface.postLogin(loginBody);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    //menampilkan response api berupa pesan ke dalam toast
                    Toasty.success(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    dataUser=response.body().getData();
//                    SPereference sPereference = new SPereference(LoginActivity.this);
//                    sPereference.saveSPString("spUser", dataUser.getUsername());
//                    sPereference.saveSPString("spPass", dataUser.getPassword());
                    //berpindah halaman ke mainactivity
                    startActivity(new Intent(LoginActivity.this, Splashscreen.class));
                    finish();
                }else {
                    //menampilkan response api berupa pesan ke dalam toast
                    Toasty.success(LoginActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toasty.error(LoginActivity.this,"Koneksi tidak ada",Toast.LENGTH_LONG).show();
            }
        });

    }
    private void getData() {
        //membuat object LoginBody
        loginBody = new Data();
        //mengisi LoginBody
        loginBody.setUsername(edtUsername.getText().toString());
        loginBody.setPassword(edtPasswordLogin.getText().toString());
    }

    private void showProgress() {
        //membuat object progress dialog
        progressDialog = new ProgressDialog(LoginActivity.this);
        //menambahkan message pada loading
        progressDialog.setMessage("Loading.....");
        //menampilkan progress dialog
        progressDialog.show();

    }
//    private void setPreference(Data du){
//        Prefs.putInt(SPref.getId(), Integer.parseInt(du.getId()));
//        Prefs.putString(SPref.getNama(),du.getNama());
//        Prefs.putString(SPref.getUsername(),du.getUsername());
//        Prefs.putInt(SPref.getNo_telp(), Integer.parseInt(du.getNoTelp()));
//        Prefs.putString(SPref.getAlamat(),du.getAlamat());
//        Prefs.putString(SPref.getEmail(), du.getEmail());
//        Prefs.putString(SPref.getJenis_kelamin(), du.getJenisKelamin().toString());
//        Prefs.putString(SPref.getPassword(), du.getPassword().toString());
//        Prefs.putString(SPref.getFoto(), du.getFoto());
//        Prefs.putString(SPref.getLevel(), du.getLevel());
//    }
}
