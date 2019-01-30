package com.mozeeb.schoolreport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.mozeeb.schoolreport.SPreferenced.SPref;
import com.mozeeb.schoolreport.model.login.Data;
import com.mozeeb.schoolreport.model.login.LoginBody;
import com.mozeeb.schoolreport.model.login.ResponseLogin;
import com.mozeeb.schoolreport.network.ApiService;
import com.mozeeb.schoolreport.network.ConfigRetrofit;
import com.mozeeb.schoolreport.user.Splashscreen;
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
    EditText edtUsernameLogin;
    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    //TODO 1 Membuat vaiabel yang dibutuhkan
    //membuat variable untuk animasi loading menggunkan progress dialog
    private ProgressDialog progressDialog;
    private LoginBody loginBody;
    private ApiService apiInterface;
    private Data dataUser;
    private Context mContext;

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
                LoginUser();
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void LoginUser() {
        apiInterface = ConfigRetrofit.getInstance();
        Call<ResponseLogin> call = apiInterface.postLogin(edtUsernameLogin.getText().toString(), edtPasswordLogin.getText().toString());
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    if (response.body().getSukses()){
                        dataUser = response.body().getData();
                        Toasty.success(mContext,"Login berhasil!!!",Toasty.LENGTH_LONG).show();
                        Log.d("Data User",dataUser.toString());
                        setPreference(dataUser);
                        startActivity(new Intent(LoginActivity.this, Splashscreen.class));
                        finish();
                    }else {
                        Toasty.error(mContext,"Username dan password salah",Toasty.LENGTH_LONG).show();
                    }
                }else {
                    Toasty.error(mContext,"Username dan password salah",Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    private void showProgress() {
        //membuat object progress dialog
        progressDialog = new ProgressDialog(LoginActivity.this);
        //menambahkan message pada loading
        progressDialog.setMessage("Loading.....");
        //menampilkan progress dialog
        progressDialog.show();

    }
    private void setPreference(Data du){
        Prefs.putInt(SPref.getId(), Integer.parseInt(du.getId()));
        Prefs.putString(SPref.getNama(),du.getNama());
        Prefs.putString(SPref.getUsername(),du.getUsername());
        Prefs.putInt(SPref.getNo_telp(), Integer.parseInt(du.getNoTelp()));
        Prefs.putString(SPref.getAlamat(),du.getAlamat());
        Prefs.putString(SPref.getJenis_kelamin(), du.getJenisKelamin().toString());
        Prefs.putString(SPref.getPassword(), du.getPassword().toString());
        Prefs.putString(SPref.getFoto(), du.getFoto());
        Prefs.putString(SPref.getLevel(), du.getLevel());
    }
}
