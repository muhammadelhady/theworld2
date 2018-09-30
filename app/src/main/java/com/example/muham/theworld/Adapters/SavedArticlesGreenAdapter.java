package com.example.muham.theworld.Adapters;

import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.leocardz.link.preview.library.LinkPreviewCallback;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;


public class SavedArticlesGreenAdapter extends RecyclerView.Adapter<SavedArticlesGreenAdapter.ViewHolder>  {





    ArrayList<String> urls= new ArrayList<>();
    final private ListItemClickListener listItemClickListener;

    public SavedArticlesGreenAdapter( ArrayList<String>urls,ListItemClickListener listItemClickListener)
    {
        this.urls=urls;
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
        SavedArticlesGreenAdapter.ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



      String url = urls.get(position);

        holder.Bind(url);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {


        TextView articleTitle;
        ImageView articleIimage;

        public ViewHolder(View itemView) {
            super(itemView);


            articleTitle=(TextView)itemView.findViewById(R.id.articleTitle);
            articleIimage=(ImageView)itemView.findViewById(R.id.articleImageView);


            itemView.setOnClickListener(this);
        }

        void Bind(String url)
        {




            TextCrawler textCrawler = new TextCrawler();

// ..

// Create the callbacks to handle pre and post exicution of the preview generation.
            LinkPreviewCallback linkPreviewCallback = new LinkPreviewCallback() {
                @Override
                public void onPre() {
                    // Any work that needs to be done before generating the preview. Usually inflate
                    // your custom preview layout here.
                }

                @Override
                public void onPos(SourceContent sourceContent, boolean b) {
                    // Populate your preview layout with the results of sourceContent.
                    Picasso.get().load( sourceContent.getImages().get(0)).into(articleIimage);
                    articleTitle.setText(sourceContent.getTitle());
                }
            };
            textCrawler.makePreview( linkPreviewCallback, url);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.onListItemClicked(clickedPosition);
        }
    }
}
