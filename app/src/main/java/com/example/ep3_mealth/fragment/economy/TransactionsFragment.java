package com.example.ep3_mealth.fragment.economy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ep3_mealth.R;
import com.example.ep3_mealth.adapter.TransactionsAdapter;
import com.example.ep3_mealth.databinding.FragmentRegisterBinding;
import com.example.ep3_mealth.databinding.FragmentTransactionsBinding;
import com.example.ep3_mealth.sql.DataSQLite;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionsFragment extends Fragment {

    private FragmentTransactionsBinding binding;

    private DataSQLite dataSQLite;
    private TransactionsAdapter transactionsAdapter;
    private ArrayList transactions = new ArrayList<HashMap <String, String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTransactionsBinding.inflate(inflater, container, false);
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

    private void showData(){
        transactions = dataSQLite.readTransactions();
        transactionsAdapter.removeAll();

        transactionsAdapter.addAll(transactions);
        binding.rvTransactions.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false
        ));
        binding.rvTransactions.setAdapter(transactionsAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}