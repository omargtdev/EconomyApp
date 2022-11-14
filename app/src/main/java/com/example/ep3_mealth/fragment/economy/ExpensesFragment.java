package com.example.ep3_mealth.fragment.economy;

import android.os.Bundle;
import static android.content.ContentValues.TAG;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ep3_mealth.R;
import com.example.ep3_mealth.adapter.TransactionsAdapter;
import com.example.ep3_mealth.databinding.FragmentExpensesBinding;
import com.example.ep3_mealth.databinding.FragmentRegisterBinding;
import com.example.ep3_mealth.sql.DataSQLite;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpensesFragment extends Fragment {

    private FragmentExpensesBinding binding;

    private DataSQLite dataSQLite;
    private TransactionsAdapter transactionsAdapter;
    private ArrayList expenses = new ArrayList<HashMap<String, String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExpensesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        transactionsAdapter = new TransactionsAdapter(getActivity());

        dataSQLite = new DataSQLite(getActivity());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showData();
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void showData(){
        expenses = dataSQLite.readExpenses();
        transactionsAdapter.removeAll();

        transactionsAdapter.addAll(expenses);
        binding.rvExpenses.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false
        ));
        binding.rvExpenses.setAdapter(transactionsAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}