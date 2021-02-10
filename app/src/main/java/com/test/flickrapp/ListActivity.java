package com.test.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
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
        task.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json");
    }
}