package com.example.ep3_mealth.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ep3_mealth.R;
import com.example.ep3_mealth.databinding.ItemTransactionBinding;
import com.example.ep3_mealth.fragment.economy.TransactionsFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    private Context context;

    public TransactionsAdapter(Context context){
        this.context = context;
    }

    private ArrayList transactions = new ArrayList();

    public void addAll(ArrayList<HashMap<String, String>> transactions){
        this.transactions.addAll(transactions);
        notifyDataSetChanged();
    }


    public void removeAll(){
        this.transactions.removeAll(transactions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(layoutInflater.inflate(
                R.layout.item_transaction,
                parent,
                false
        ));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String, String> transaction = (HashMap<String, String>) transactions.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTransactionBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemTransactionBinding.bind(itemView);
        }

        public void bind(HashMap<String, String> transaction){
            int typeTransaction = Integer.parseInt(transaction.get("type"));

            if(typeTransaction == -1){
                binding.tvTransactionDescription.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                binding.tvTransactionDate.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                binding.tvTransactionAmount.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            }

            binding.tvTransactionDescription.setText(transaction.get("description"));
            binding.tvTransactionAmount.setText(transaction.get("amount"));
            binding.tvTransactionDate.setText(transaction.get("datetime"));
        }
    }

}
