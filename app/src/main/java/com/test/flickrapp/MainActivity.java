package com.test.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(getString("CustomWord"), true);
//        editor.apply();

        Button btnGetImg = findViewById(R.id.btnGetImg);
        btnGetImg.setOnClickListener(new GetImageOnClickListener(findViewById(R.id.image)));

        Button btnListActivity = findViewById(R.id.btnListActivity);
        btnListActivity.setOnClickListener(v -> {
            startActivity(new Intent(this, ListActivity.class));
        });

        Button btnPreferences = findViewById(R.id.btnPreferences);
        btnPreferences.setOnClickListener(v -> {
            startActivity(new Intent(this, PreferenceActivity.class));
        });
    }
}