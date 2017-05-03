package com.star.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NewsContentFragment extends Fragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_news_content, container, false);

        return mView;
    }

    public void refresh(String newsTitle, String newsContent) {

        View visibilityLayout = mView.findViewById(R.id.visibility_layout);

        visibilityLayout.setVisibility(View.VISIBLE);

        TextView newsTitleTextView = (TextView) mView.findViewById(R.id.news_title);
        TextView newsContentTextView = (TextView) mView.findViewById(R.id.news_content);

        newsTitleTextView.setText(newsTitle);
        newsContentTextView.setText(newsContent);
    }
}
