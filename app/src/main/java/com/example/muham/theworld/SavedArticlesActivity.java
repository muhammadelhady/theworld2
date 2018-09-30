package com.example.muham.theworld;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.muham.theworld.Adapters.SavedArticlesGreenAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SavedArticlesActivity extends AppCompatActivity implements SavedArticlesGreenAdapter.ListItemClickListener {
RecyclerView recyclerView;
SavedArticlesGreenAdapter adapter;
ArrayList<String>urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_articles);
        recyclerView=(RecyclerView)findViewById(R.id.savedArticlesRec);
        urls=new ArrayList<>();
        GetData();


    }

    void  GetData()
    {
        String mail=  PreferenceManager.getDefaultSharedPreferences(SavedArticlesActivity.this).getString("email", "defaultStringIfNothingFound").replace("@gmail.com","");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(mail);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    // easy
                   urls.add( dataSnapshot1.getValue(String.class));
                }
                ConfigRecyclerView(urls);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                }
        });

    }


    private void ConfigRecyclerView(ArrayList<String> urls)
    {

        adapter=new SavedArticlesGreenAdapter(urls,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onListItemClicked(int clickedItemIndex) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("url",urls.get(clickedItemIndex));
        startActivity(intent);
    }
}
