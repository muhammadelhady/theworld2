package com.example.muham.theworld;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ShareActionProvider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ArticleActivity extends AppCompatActivity {
   FloatingActionButton shareFab,saveFab;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent = getIntent();
         url = intent.getStringExtra("url");
        WebView webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setUserAgentString("Android");
        webView.loadUrl(url);

        shareFab=(FloatingActionButton)findViewById(R.id.shareFab);
        saveFab=(FloatingActionButton)findViewById(R.id.saveFab);
        Share();
        Save();

    }


     void Share()
    {
        shareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = url;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }



    void  Save()
    {
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String mail=  PreferenceManager.getDefaultSharedPreferences(ArticleActivity.this).getString("email", "defaultStringIfNothingFound").replace("@gmail.com","");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(mail);

                myRef.push().setValue(url);

            }
        });

    }
}
