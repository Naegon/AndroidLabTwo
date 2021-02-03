package com.test.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetImg = findViewById(R.id.btnGetImg);
        btnGetImg.setOnClickListener(new GetImageOnClickListener(findViewById(R.id.image)));

        Button btnListActivity = findViewById(R.id.btnListActivity);
        btnListActivity.setOnClickListener(v -> {
            startActivity(new Intent(this, ListActivity.class));
        });


    }
}