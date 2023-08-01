package com.example.myapplication.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ViewpagerAdapter  extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayList = new ArrayList<>();
    private ArrayList<String> arrayListTitle = new ArrayList<>();

    public ViewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
    public  void addFragment(Fragment fm,String title){
        arrayList.add(fm);
        arrayListTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayListTitle.get(position);
    }
}
