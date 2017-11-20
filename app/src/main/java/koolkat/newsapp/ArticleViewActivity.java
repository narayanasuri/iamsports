package koolkat.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleViewActivity extends AppCompatActivity {

    private WebView webView;
    private String url, channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        url = getIntent().getStringExtra("url");
        channel = getIntent().getStringExtra("channel");

        if(channel.equals("BBC")) {
            url = url.replace("co.uk", "com");
        } else if(channel.equals("ESPN")) {
            url = url.replace("go.com", "in");
        }

        Log.i("ArticleViewActivity", url);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

}
