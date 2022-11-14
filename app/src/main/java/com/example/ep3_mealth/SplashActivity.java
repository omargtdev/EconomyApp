package com.example.ep3_mealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(()->{
            checkLogged();
        },2000);
    }

    private void checkLogged(){
        SharedPreferences sharedPreferences = getSharedPreferences("sessionData", Context.MODE_PRIVATE);
        String datos = sharedPreferences.getString("userData",null);
        if(datos != null){
            startActivity(new Intent(this, EconomyActivity.class));
            finish();
            return;
        }

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}