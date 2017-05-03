package com.star.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NewsTitleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_title, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        NewsAdapter newsAdapter = new NewsAdapter(getNews());

        recyclerView.setAdapter(newsAdapter);

        return view;
    }

    private List<News> getNews() {

        List<News> newsList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            News news = new News();

            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + "."));

            newsList.add(news);
        }

        return newsList;
    }

    private String getRandomLengthContent(String newsContent) {

        Random random = new Random();

        int length = random.nextInt(20) + 1;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(newsContent);
        }

        return stringBuilder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.news_item, parent, false
            );

            final ViewHolder viewHolder = new ViewHolder(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    News news = mNewsList.get(viewHolder.getAdapterPosition());

                    if (getActivity().findViewById(R.id.news_content_layout) != null) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);

                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(),
                                news.getContent());
                    }
                }
            });

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);

            holder.mNewsTitleTextView.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView mNewsTitleTextView;

            public ViewHolder(View itemView) {
                super(itemView);

                mNewsTitleTextView = (TextView) itemView.findViewById(R.id.news_title);
            }
        }
    }
}
