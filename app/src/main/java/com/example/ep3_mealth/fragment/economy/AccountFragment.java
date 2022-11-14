package com.example.ep3_mealth.fragment.economy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ep3_mealth.R;
import com.example.ep3_mealth.databinding.FragmentAccountBinding;
import com.example.ep3_mealth.databinding.FragmentExpensesBinding;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private FragmentAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnAccountImage.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        if(binding.ivAccount.getVisibility() == View.INVISIBLE){
            binding.ivAccount.setVisibility(View.VISIBLE);
            binding.btnAccountImage.setText("Ocultar imagen");
            return;
        }

        binding.ivAccount.setVisibility(View.INVISIBLE);
        binding.btnAccountImage.setText("Mostrar imagen");
    }
}