package com.mozeeb.schoolreport.SPreferenced;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mozeeb.schoolreport.LoginActivity;
import com.mozeeb.schoolreport.user.Splashscreen;

public class SPereference {

    public static final String SP_USER_APP = "tb_user";
    public static final String SP_USERNAME = "spUser";
    public static final String SP_PASS = "spPass";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    private Context mContext;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SPereference(Context context){
        this.mContext = context;
        sp = mContext.getSharedPreferences(SP_USER_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }
    public void saveSPBooleon(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public  String getSpUsername() {
        return sp.getString(SP_USERNAME,"");
    }

    public  String getSpPass() {
        return sp.getString(SP_PASS, "");
    }

    public  boolean getSpSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN,false);
    }

    public void checkLogin() {
        if (!this.Login()) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } else {
            Intent intent = new Intent(mContext, Splashscreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }

    }

    public Boolean Login() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public void Logout(){
        spEditor.clear();
        spEditor.commit();

        Intent i = new Intent(mContext, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }
}
