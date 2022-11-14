package com.example.ep3_mealth;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ep3_mealth.databinding.ActivityLoginBinding;
import com.example.ep3_mealth.tools.Util;
import com.example.ep3_mealth.web_service.ILoginApiService;
import com.example.ep3_mealth.web_service.RetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private ILoginApiService apiService;

    private String username;
    private String password;

    private int intentCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(this);

        apiService = RetrofitClient.getRetrofitInstance().create(ILoginApiService.class);

    }

    private void login(String data){
        // Get user ID
        Gson gson = new Gson();
        HashMap<String, Object> jsonData = gson.fromJson(data.replace("=", ":"), HashMap.class);
        jsonData = gson.fromJson(jsonData.get("user").toString().replace("=", ":"), HashMap.class);
        int userId = (int) Double.parseDouble(jsonData.get("UserID").toString());


        if(binding.swSaveSession.isChecked()){
            // Creating JSON object to store en preferences
            HashMap<String, String> userData = new HashMap<>();
            userData.put("id", String.valueOf(userId));
            userData.put("username", username);
            userData.put("password", password);
            String userDataStr = gson.toJson(userData);

            // Save session
            SharedPreferences sharedPreferences = getSharedPreferences("sessionData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userData", userDataStr);
            editor.apply();
        }

        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, EconomyActivity.class));
        finish();

    }

    @Override
    public void onClick(View view) {
        username = binding.etLoginUser.getText().toString().trim();
        password = binding.etLoginPassword.getText().toString().trim();

        if(Util.isEmptyFields(new String[]{username, password}, this)) return;

        Call call = apiService.getUser(username, password);

        intentCount++;

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(!response.isSuccessful()){
                    Log.e(TAG, "onLogin: " + response.code());
                    Log.e(TAG, "onLogin: " + response.message());
                    Log.e(TAG, "onLogin: " + response.errorBody());
                    return;
                }

                Gson gson = new Gson();
                HashMap<String, Object> jsonData = gson.fromJson(gson.toJson(response.body()), HashMap.class);
                int loginCode = (int) Double.parseDouble(jsonData.get("loginCode").toString());

                if(loginCode == 1){
                    intentCount = 0;
                    login(jsonData.get("data").toString());
                    return;
                }

                if(loginCode == -1){
                    Toast.makeText(LoginActivity.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                }

                if(loginCode == 0){
                    Toast.makeText(LoginActivity.this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                }

                if(intentCount == 3){
                    Toast.makeText(LoginActivity.this, "Se alcanzo el maximo numero de intentos", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


    }
}