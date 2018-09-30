package com.example.muham.theworld;

import android.app.Service;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.muham.theworld.Adapters.ArticlesListGreenAdapter;
import com.example.muham.theworld.utile.Article;
import com.example.muham.theworld.utile.NewsUtile;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsListActivity extends AppCompatActivity implements ArticlesListGreenAdapter.ListItemClickListener {
String category;
    RecyclerView recyclerView;
    ArticlesListGreenAdapter adapter;
    NewsUtile newsUtile;
    String searchQuery="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        Intent intent = getIntent();
        category = intent.getStringExtra("Category");
        if (category.equals("search"))
        {
            category="general";
            searchQuery=intent.getStringExtra("query");
        }


        recyclerView = (RecyclerView)findViewById(R.id.articleListRecView);

CallNewsApi();
    }



    public void CallNewsApi()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(getString(R.string.NEWS_URL)).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        ServiceInterface service = retrofit.create(ServiceInterface.class);
        service.GetNews(category,searchQuery).enqueue(new Callback<NewsUtile>() {
            @Override
            public void onResponse(Call<NewsUtile> call, Response<NewsUtile> response) {
                newsUtile=response.body();
                ConfigRecyclerView(newsUtile);
            }

            @Override
            public void onFailure(Call<NewsUtile> call, Throwable t) {
//Snackbar.make(recyclerView,"Netowrk Connection",Snackbar.LENGTH_LONG).show();
            }
        });
    }



    private void ConfigRecyclerView(NewsUtile news)
    {

        adapter=new ArticlesListGreenAdapter(news,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
Intent intent = new Intent(this, ArticleActivity.class);
intent.putExtra("url",newsUtile.getArticles().get(clickedItemIndex).getUrl());
startActivity(intent);
    }
}
