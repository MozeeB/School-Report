package com.mozeeb.schoolreport.utils;

import android.content.Context;
import android.content.Intent;

public class Move {
    public static void moveActivity(Context mContext, Class<?> activity){
        Intent i=new Intent(mContext,activity);
        mContext.startActivity(i);
    }
}
