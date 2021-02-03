package com.test.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetImg = findViewById(R.id.btnGetImg);
        AppCompatImageView img = findViewById(R.id.image);
        btnGetImg.setOnClickListener(new GetImageOnClickListener(img));
    }
}