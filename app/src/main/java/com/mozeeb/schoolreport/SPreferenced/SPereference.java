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
    }
}