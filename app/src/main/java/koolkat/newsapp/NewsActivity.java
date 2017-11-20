package koolkat.newsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NewsActivity extends AppCompatActivity {

    String url;
    String channel;
    private RecyclerView articlesRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NewsArticlesAdapter newsArticlesAdapter;
    private List<NewsArticle> articles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        articlesRecyclerView = findViewById(R.id.articles_recyclerview);

        channel = getIntent().getExtras().getString("channel");

        setTitle(channel);

        articles = new ArrayList<>();
        newsArticlesAdapter = new NewsArticlesAdapter(this, articles);

        layoutManager = new LinearLayoutManager(this);
        articlesRecyclerView.setLayoutManager(layoutManager);
        articlesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        articlesRecyclerView.setAdapter(newsArticlesAdapter);

        articlesRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                    new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    final int i = rv.getChildAdapterPosition(child);

                    Intent intent = new Intent(NewsActivity.this, ArticleViewActivity.class);
                    intent.putExtra("channel", channel);
                    intent.putExtra("url", articles.get(i).getUrl());
                    startActivity(intent);

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

        });

        url = getIntent().getExtras().getString("url");

        DownloadTask task = new DownloadTask();
        task.execute(url);

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpsURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result+=current;

                    data = reader.read();
                }

                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONObject jsonObject = new JSONObject(result);

                String articlesStr = jsonObject.getString("articles");

                JSONArray jsonArray = new JSONArray(articlesStr);

                Log.i("JSON Array Length", jsonArray.length()+ "");

                for(int i=0; i<jsonArray.length(); i++) {

                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    String title = jsonPart.getString("title");
                    String desc = jsonPart.getString("description");
                    String url = jsonPart.getString("url");
                    String urlToImage = jsonPart.getString("urlToImage");
                    if(!title.isEmpty() && !url.isEmpty()) {
                        NewsArticle article = new NewsArticle(title, desc, url, urlToImage);
                        articles.add(article);
                    }

                }

                Log.i("JSON Articles", articles.size()+ "");

                newsArticlesAdapter = new NewsArticlesAdapter(getApplicationContext(), articles);

                layoutManager = new LinearLayoutManager(getApplicationContext());
                articlesRecyclerView.setLayoutManager(layoutManager);
                articlesRecyclerView.setItemAnimator(new DefaultItemAnimator());
                articlesRecyclerView.setAdapter(newsArticlesAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i("Website Content", result);
        }
    }

}
