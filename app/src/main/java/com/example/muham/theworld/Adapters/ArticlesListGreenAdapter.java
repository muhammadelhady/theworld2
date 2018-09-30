package com.example.muham.theworld.Adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muham.theworld.R;
import com.example.muham.theworld.utile.NewsUtile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticlesListGreenAdapter extends RecyclerView.Adapter<ArticlesListGreenAdapter.ViewHolder>  {





   NewsUtile news= new NewsUtile();
    final private ListItemClickListener listItemClickListener;

    public ArticlesListGreenAdapter( NewsUtile news,ListItemClickListener listItemClickListener)
    {
        this.news=news;
        this.listItemClickListener=listItemClickListener;
    }

    public interface ListItemClickListener
    {
        void onListItemClicked(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_list_row,parent,false);
        ArticlesListGreenAdapter.ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        String source=news.getArticles().get(position).getSource().getName();
        String title=news.getArticles().get(position).getTitle();
        String imageUrl=news.getArticles().get(position).getUrlToImage();

        holder.Bind(source,imageUrl,title);
    }

    @Override
    public int getItemCount() {
        return news.getArticles().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {


        TextView articleSource,articleTitle;
        ImageView articleIimage;

        public ViewHolder(View itemView) {
            super(itemView);

            articleSource=(TextView)itemView.findViewById(R.id.newsSourceTextView);
            articleTitle=(TextView)itemView.findViewById(R.id.articleTitle);
            articleIimage=(ImageView)itemView.findViewById(R.id.articleImageView);


            itemView.setOnClickListener(this);
        }

        void Bind(String source,String imageUrl,String title)
        {

          articleSource.setText(source);
          articleTitle.setText(title);

          if (imageUrl==null)
          {
              Picasso.get().load(R.drawable.article).into(articleIimage);
          }
          else {
              Picasso.get().load(imageUrl).into(articleIimage);
          }




        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.onListItemClicked(clickedPosition);
        }
    }
}
