package koolkat.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView bbcTile, espnTile, espnCricTile, fourFourTwoTile, footballItaliaTile, foxSportsTile,
            nflNewsTile, nhlNewsTile, talkSportTile, sportBibleTile;

    public static final String BBC_URL = "https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=95d226232b7941988d2505f555807794";
    public static final String ESPN_URL = "https://newsapi.org/v2/top-headlines?sources=espn&apiKey=95d226232b7941988d2505f555807794";
    public static final String ESPN_CRICK_URL = "https://newsapi.org/v2/top-headlines?sources=espn-cric-info&apiKey=95d226232b7941988d2505f555807794";
    public static final String FOUR_FOUR_TWO_URL = "https://newsapi.org/v2/top-headlines?sources=four-four-two&apiKey=95d226232b7941988d2505f555807794";
    public static final String FOOTBALL_ITALIA_URL = "https://newsapi.org/v2/top-headlines?sources=football-italia&apiKey=95d226232b7941988d2505f555807794";
    public static final String FOX_SPORTS_URL = "https://newsapi.org/v2/top-headlines?sources=fox-sports&apiKey=95d226232b7941988d2505f555807794";
    public static final String NFL_NEWS_URL = "https://newsapi.org/v2/top-headlines?sources=nfl-news&apiKey=95d226232b7941988d2505f555807794";
    public static final String NHL_NEWS_URL = "https://newsapi.org/v2/top-headlines?sources=nhl-news&apiKey=95d226232b7941988d2505f555807794";
    public static final String TALK_SPORT_URL = "https://newsapi.org/v2/top-headlines?sources=talksport&apiKey=95d226232b7941988d2505f555807794";
    public static final String SPORT_BIBLE_URL = "https://newsapi.org/v2/top-headlines?sources=the-sport-bible&apiKey=95d226232b7941988d2505f555807794";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bbcTile = findViewById(R.id.bbc_tile);
        bbcTile.setOnClickListener(this);

        espnTile = findViewById(R.id.espn_tile);
        espnTile.setOnClickListener(this);

        espnCricTile = findViewById(R.id.espn_cric_tile);
        espnCricTile.setOnClickListener(this);

        fourFourTwoTile = findViewById(R.id.four_four_two_tile);
        fourFourTwoTile.setOnClickListener(this);

        footballItaliaTile = findViewById(R.id.football_italia_tile);
        footballItaliaTile.setOnClickListener(this);

        foxSportsTile = findViewById(R.id.fox_sports_tile);
        foxSportsTile.setOnClickListener(this);

        nflNewsTile = findViewById(R.id.nfl_tile);
        nflNewsTile.setOnClickListener(this);

        nhlNewsTile = findViewById(R.id.nhl_tile);
        nhlNewsTile.setOnClickListener(this);

        talkSportTile = findViewById(R.id.talk_sport_tile);
        talkSportTile.setOnClickListener(this);

        sportBibleTile = findViewById(R.id.sport_bible_tile);
        sportBibleTile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.bbc_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "BBC");
                intent.putExtra("url", BBC_URL);
                startActivity(intent);
                break;

            case R.id.espn_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "ESPN");
                intent.putExtra("url", ESPN_URL);
                startActivity(intent);
                break;

            case R.id.espn_cric_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "ESPN Cric Info");
                intent.putExtra("url", ESPN_CRICK_URL);
                startActivity(intent);
                break;

            case R.id.four_four_two_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "FourFourTwo");
                intent.putExtra("url", FOUR_FOUR_TWO_URL);
                startActivity(intent);
                break;

            case R.id.football_italia_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "Football Italia");
                intent.putExtra("url", FOOTBALL_ITALIA_URL);
                startActivity(intent);
                break;

            case R.id.fox_sports_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "FOX Sports");
                intent.putExtra("url", FOX_SPORTS_URL);
                startActivity(intent);
                break;

            case R.id.nfl_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "NFL News");
                intent.putExtra("url", NFL_NEWS_URL);
                startActivity(intent);
                break;

            case R.id.nhl_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "NHL_NEWS");
                intent.putExtra("url", NHL_NEWS_URL);
                startActivity(intent);
                break;

            case R.id.talk_sport_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "TalkSport");
                intent.putExtra("url", TALK_SPORT_URL);
                startActivity(intent);
                break;

            case R.id.sport_bible_tile:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("channel", "The Sport Bible");
                intent.putExtra("url", SPORT_BIBLE_URL);
                startActivity(intent);
                break;
        }

    }
}
