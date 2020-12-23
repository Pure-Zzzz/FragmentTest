package com.example.fragmenttest;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    List<String> mTitleLists;


    public MyPagerAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragmentList = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public CharSequence getPageTitle(int position) {
        mTitleLists = new ArrayList<>();
        mTitleLists.add("订阅");
        mTitleLists.add("体育");
        mTitleLists.add("经济");
        mTitleLists.add("娱乐");
        mTitleLists.add("科技");
        return mTitleLists.get(position);

    }


}
