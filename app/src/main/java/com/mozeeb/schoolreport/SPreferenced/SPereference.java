package com.mozeeb.schoolreport.SPreferenced;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mozeeb.schoolreport.LoginActivity;
import com.mozeeb.schoolreport.user.Splashscreen;

public class SPereference {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NO_TELEPON = "no_tlp";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_JENIS_KELAMIN = "jenis_kelamin";
//    private static final String KEY_FOTO = "foto";
    private static final String is_login = "loginstatus";
    private final String SHARED_NAME = "loginsesion";
    private final int MODE_PRIVATE = 0;
    private Context Mcontext;

    public SPereference(Context context) {
        this.Mcontext = context;
        sp = Mcontext.getSharedPreferences(SHARED_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(String user){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_USERNAME, user);
        editor.commit();
    }

    public void setIdUser(String idUser){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_USERNAME, idUser);
        editor.commit();
    }
    public String getIdUser() {
        return sp.getString(KEY_USERNAME, "");
    }
    public void set_telp(String telp){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NO_TELEPON, telp);
        editor.commit();
    }
    public String getTelp(){
        return sp.getString(KEY_NO_TELEPON, "");

    }
    public void setAlamatPref(String alamat){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_ALAMAT, alamat);
        editor.commit();

    }
    public String getAlamatPref(){
        return sp.getString(KEY_ALAMAT, "");
    }
    public void setEmailPref(String emailPref){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_EMAIL, emailPref);
        editor.commit();

    }
    public String getEmailPref(){
        return sp.getString(KEY_EMAIL,"");
    }
    public void setKelamin(String kelamin){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_JENIS_KELAMIN, kelamin);
        editor.commit();
    }

    public String getKelamin(){
        return sp.getString(KEY_JENIS_KELAMIN, "");
    }

//    public void setFotoPref(String fotoPref){
//        editor.putBoolean(is_login, true);
//        editor.putString(KEY_FOTO, fotoPref);
//        editor.commit();
//    }
//    public String getFotoPref(){
//        return sp.getString(KEY_FOTO,"");
//    }


}