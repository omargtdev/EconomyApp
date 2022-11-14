package com.example.ep3_mealth.fragment.economy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ep3_mealth.R;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private final int[] TAB_TITLES = new int[]{R.string.tab_economy_register, R.string.tab_economy_movements, R.string.tab_economy_bills, R.string.tab_account};

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new RegisterFragment();
            case 1: return new TransactionsFragment();
            case 2: return new ExpensesFragment();
            case 3: return new AccountFragment();
        }
        return new RegisterFragment();
    }

    public int[] getTabTitles() {
        return TAB_TITLES;
    }

    @Override
    public int getItemCount() {
        return TAB_TITLES.length;
    }
}
