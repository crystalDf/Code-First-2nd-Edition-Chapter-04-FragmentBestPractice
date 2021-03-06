package com.star.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewsContentActivity extends AppCompatActivity {

    private static final String NEWS_TITLE = "news_title";
    private static final String NEWS_CONTENT = "news_content";

    public static void actionStart(Context context, String newsTitle, String newsContent) {

        Intent intent = new Intent(context, NewsContentActivity.class);

        intent.putExtra(NEWS_TITLE, newsTitle);
        intent.putExtra(NEWS_CONTENT, newsContent);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String newsTitle = getIntent().getStringExtra(NEWS_TITLE);
        String newsContent = getIntent().getStringExtra(NEWS_CONTENT);

        NewsContentFragment newsContentFragment = (NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);

        newsContentFragment.refresh(newsTitle, newsContent);
    }
}
