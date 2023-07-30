package com.example.khoaactivity.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.khoaactivity.Fragments.Frag_Khoa;
import com.example.khoaactivity.Fragments.Frag_Lop;


public class MyAdapterPager extends FragmentStateAdapter {
    int soLuongPage = 2;

    public MyAdapterPager(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new Frag_Khoa();
            case 1:
                return new Frag_Lop();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return soLuongPage;
    }
}
