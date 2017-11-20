package koolkat.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Suri on 11/20/2017.
 */

public class NewsArticlesAdapter extends RecyclerView.Adapter<NewsArticlesAdapter.MyViewHolder> {

    private Context context;
    private List<NewsArticle> articles;

    public NewsArticlesAdapter(Context context, List<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
        Log.i("NewsArticleAdapter", articles.size() + "");
    }

    public NewsArticlesAdapter() {
        super();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public ImageView thumbnail;
        public TextView title, desc;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.newsTileTitleTextView);
//            thumbnail = view.findViewById(R.id.newsTileImgView);
            desc = view.findViewById(R.id.newsTileDescTextView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsArticle article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.desc.setText(article.getDescription());

//        Picasso.with(context).load(article.getUrlToImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
