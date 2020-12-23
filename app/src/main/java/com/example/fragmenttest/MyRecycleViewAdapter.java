package com.example.fragmenttest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private List<NewsItem> newsList;
    private Context context;

    public MyRecycleViewAdapter(List<NewsItem> newsItemList) {
        this.newsList = newsItemList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    /**
     * 当需要新的ViewHolder来显示列表项时，会调用onCreateViewHolder方法去创建ViewHolder
     */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        /**
         * 为图片设置监听器，将对于新闻的url传递给webview界面显示,变量名称为“address”
         */
        holder.siv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, Webtest.class);
                intent.putExtra("address", newsList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    /**
     *  onBindViewHolder是具体实现数据更新的地方
     * onBindViewHolder的入参为ViewHolder holder, int position，
     * 因此，首先通过position获取数据，然后对ViewHolder的控件依次设置
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewAdapter.ViewHolder holder, int position) {
        NewsItem news = newsList.get(position);
        holder.tv_title.setText(news.getTitle());
        holder.tv_source.setText("来源:" + news.getSource());
        holder.tv_time.setText("时间:" + news.getTime());
        holder.siv.setImageUrl(news.getImg());
    }

    @Override
    /**
     * 返回总共要显示的列表的数量(创建的ViewHolder数量比前者要小得多)。
     */
    public int getItemCount() {
        return newsList.size();
    }

    /**
     * ViewHolder承载的是每一个列表项的视图，所以当使用RecyclerView的时候需要先对ViewHolder进行初始化定义。
     * 每一个viewholder含有新闻图片--siv,新闻标题--title,新闻发布时间--time,原网页地址--url
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private SmartImageView siv;
        private TextView tv_title;
        private TextView tv_time;
        private TextView tv_source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_source = (TextView) itemView.findViewById(R.id.tv_source);
            siv = (SmartImageView) itemView.findViewById(R.id.siv);
        }
    }
}
