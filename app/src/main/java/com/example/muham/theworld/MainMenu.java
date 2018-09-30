package com.example.muham.theworld;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
String Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();


        ab.setDisplayHomeAsUpEnabled(true);




    }

    public void onSportClicked(View view)
    {
        Category ="sport";
        CallingNewsActivity();
    }
    public void onScienceClicked(View view)
    {
       Category ="science";
        CallingNewsActivity();
    }
    public void onTechnologyClicked(View view)
    {
        Category ="technology";
        CallingNewsActivity();
    }
    public void onBusinessClicked(View view)
    {
        Category ="business";
        CallingNewsActivity();
    }
    public void onHealthClicked(View view)
    {
      Category ="health";
        CallingNewsActivity();
    }
    public void onEntertainmentClicked(View view)
    {
        Category ="entertainment";
        CallingNewsActivity();
    }

public void CallingNewsActivity()
{
    Intent intent=new Intent(this,NewsListActivity.class);
    intent.putExtra("Category",Category);;
    startActivity(intent);
    finish();
}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_saved:
            {

                Intent intent= new Intent(this,SavedArticlesActivity.class);
                startActivity(intent);
                return true;
            }

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
               Intent  intent = new Intent(MainMenu.this,NewsListActivity.class);
               intent.putExtra("Category","search");
               intent.putExtra("query",query);
               startActivity(intent);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
    }
}



