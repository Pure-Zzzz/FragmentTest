package com.example.fragmenttest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Hot extends Fragment {
    private static String url = "https://3g.163.com/touch/reconstruct/article/list/BBM54PGAwangning/1-20.html";
    MyRecycleViewAdapter adapter;
    private RecyclerView rv_hot;
    private List<NewsItem> newsItemList = new ArrayList<NewsItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ATTENTION!", "Fragment_Hot is coming!!");
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(newsItemList);
        try {
            Thread.currentThread().sleep(2000);//阻断0.2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rv_hot = (RecyclerView) view.findViewById(R.id.rv_hot);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        rv_hot.setLayoutManager(linearLayout);
        rv_hot.setAdapter(adapter);
        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (isVisibleToUser) {
            Log.d("ATTENTION!", "Fragment_Hot have the DATA!!");
        } else {
            GetNews getNewsUtil = new GetNews();
            getNewsUtil.getNews(url, "BBM54PGAwangning", newsItemList);
            Log.d("ATTENTION!", "setUserVisibleHint is not loaded");
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


}