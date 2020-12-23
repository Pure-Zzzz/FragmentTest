package com.example.fragmenttest;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.*;

public class GetNews {

    public int getNews(String url, String category, List<NewsItem> newsItems) {
        HttpUtil.sendOkhttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("error", "获取数据失败！");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                switch (category) {
                    case "BBM54PGAwangning":
                        Log.d("success", "成功获取到头条信息!");
                        break;
                    case "BA10TA81wangning":
                        Log.d("success", "成功获取到娱乐信息!");
                        break;
                    case "BA8E6OEOwangning":
                        Log.d("success", "成功获取到体育信息!");
                        break;
                    case "BA8EE5GMwangning":
                        Log.d("success", "成功获取到财经信息!");
                        break;
                    case "BA8D4A3Rwangning":
                        Log.d("success", "成功获取到科技信息!");
                        break;
                }
                String text = response.body().string();
                Log.d("response", text);
                char standard[] = text.toCharArray();
                for (int i = 0; i < 9; i++)
                    standard[i] = ' ';
                standard[standard.length - 1] = ' ';
                Log.d("Standarded String", String.valueOf(standard));
                text = String.valueOf(standard);
                try {
                    parseJsonWithJsonObject(text, category, newsItems);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        return 1;
    }

    private void parseJsonWithJsonObject(String json, String category, List<NewsItem> newsItems) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        Log.d("hello", "hello");
        Log.d("test", jsonObject.toString());
        final JSONArray array = jsonObject.getJSONArray(category);
        for (int i = 1; i < array.length(); i++) {
            NewsItem news = new NewsItem();
            JSONObject object = array.getJSONObject(i);
            news.setImg(object.getString("imgsrc"));
            news.setSource(object.getString("source"));
            news.setTime(object.getString("ptime"));
            news.setTitle(object.getString("title"));
            news.setUrl(object.getString("url"));
            Log.d("NEWS", news.getTitle() + "  " + news.getUrl() + "  " + news.getTime());
            /*if(!object.optString("hasImg").isEmpty()){
                Log.d("IMG NULL!!", "NULL");
                continue;
            }*/
            if (news.getUrl().equals("")) {
                Log.d("URL NULL!!", "NULL");
                continue;
            }
            boolean check = false;
            for (NewsItem n : newsItems) {
                if (n.getTitle().equals(news.getTitle())) {
                    check = true;
                    break;
                }
            }
            if (!check)
                newsItems.add(news);
        }


    }
}
