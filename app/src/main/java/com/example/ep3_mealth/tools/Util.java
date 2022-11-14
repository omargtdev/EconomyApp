package com.example.ep3_mealth.tools;

import android.content.Context;
import android.widget.Toast;

public class Util {

    public static boolean isEmptyFields(String[] fields, Context context){
        for (String str: fields) {
            if(str.trim().equals("")){
                Toast.makeText(context, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        return false;
    }
}
