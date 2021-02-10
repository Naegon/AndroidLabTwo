package com.test.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.ref.WeakReference;

public class ListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MyAdapter myAdapter = new MyAdapter(getApplicationContext());
        listView = findViewById(R.id.list);
        listView.setAdapter(myAdapter);

        AsyncFlickrJSONDataForList task = new AsyncFlickrJSONDataForList(new WeakReference<>(myAdapter));

        SharedPreferences sharedPref = this.getSharedPreferences("Test", Context.MODE_PRIVATE);
        Log.i("JFL", String.valueOf(sharedPref.getBoolean("IsCustomWord", false)));
        String customWord = sharedPref.getBoolean("IsCustomWord", false)?
            sharedPref.getString("CustomWord", "trees") : "trees";

        String url = "https://www.flickr.com/services/feeds/photos_public.gne?tags=" + customWord + "&format=json";
        Log.i("JFL", "url: " + url);
        task.execute(url);
    }
}