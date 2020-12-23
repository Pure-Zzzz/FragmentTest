package com.example.fragmenttest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static android.view.KeyEvent.KEYCODE_BACK;

public class MainActivity extends AppCompatActivity {
    private static String[] tabs = {"热点", "体育", "财经", "娱乐", "科技"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout tabLayout;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment_Hot hot = new Fragment_Hot();
        Fragment_Sport sport = new Fragment_Sport();
        Fragment_Financial financial = new Fragment_Financial();
        Fragment_amusement amusement = new Fragment_amusement();
        Fragment_Tec tec = new Fragment_Tec();
        fragments.add(hot);
        fragments.add(sport);
        fragments.add(financial);
        fragments.add(amusement);
        fragments.add(tec);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setOffscreenPageLimit(fragments.size() - 1);//fragment预加载
        FragmentManager fragmentManager = getSupportFragmentManager();
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(fragmentManager, fragments);
        vp.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(vp);
    }
}

