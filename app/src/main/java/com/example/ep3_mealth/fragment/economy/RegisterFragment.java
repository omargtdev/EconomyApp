package com.example.ep3_mealth.fragment.economy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ep3_mealth.R;
import com.example.ep3_mealth.databinding.FragmentRegisterBinding;
import com.example.ep3_mealth.sql.DataSQLite;
import com.example.ep3_mealth.tools.Util;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class RegisterFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private FragmentRegisterBinding binding;

    private String description;
    private double amount;

    private int typeSelected = DataSQLite.INCOME;

    private ArrayList<EditText> editTexts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.transaction_type_array,
                android.R.layout.simple_spinner_item
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spType.setAdapter(spinnerAdapter);


        binding.spType.setOnItemSelectedListener(this);

        //Register
        binding.btnRegister.setOnClickListener(this);

        editTexts.add(binding.etDescription);
        editTexts.add(binding.etAmount);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        description = binding.etDescription.getText().toString().trim();
        String amountStr = binding.etAmount.getText().toString().trim();

        if(Util.isEmptyFields(new String[]{description, amountStr}, getActivity())) return;

        amount = Double.parseDouble(amountStr);

        // Insert
        DataSQLite dataSQLite = new DataSQLite(getActivity());
        dataSQLite.insertTransaction(description, amount, typeSelected);
        Toast.makeText(getActivity(), "Insertado correctamente", Toast.LENGTH_SHORT).show();
        cleanFields();
    }

    public void cleanFields(){
        binding.etAmount.setText("");
        binding.etDescription.setText("");
        binding.spType.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        typeSelected = i == 0 ? DataSQLite.INCOME : DataSQLite.EXPENSE;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        typeSelected = DataSQLite.INCOME;
    }
}