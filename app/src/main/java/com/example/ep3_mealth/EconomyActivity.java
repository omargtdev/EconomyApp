package com.example.ep3_mealth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ep3_mealth.databinding.ActivityEconomyBinding;
import com.example.ep3_mealth.databinding.ActivityLoginBinding;
import com.example.ep3_mealth.fragment.economy.ViewPagerFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class EconomyActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEconomyBinding binding;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =  ActivityEconomyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(this);
        binding.viewPagerEconomy.setAdapter(viewPagerFragmentAdapter);
        
        binding.btnLogout.setOnClickListener(this);

        new TabLayoutMediator(
                binding.tabLayoutEconomy,
                binding.viewPagerEconomy,
                (tab, position) -> tab.setText(viewPagerFragmentAdapter.getTabTitles()[position])
        ).attach();

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea cerrar la sesión?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        logout();
                    }
                })
                .setNegativeButton("No", null);
        builder.create();
        builder.show();
    }

    private void logout(){
        SharedPreferences sharedPreferences = getSharedPreferences("sessionData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}