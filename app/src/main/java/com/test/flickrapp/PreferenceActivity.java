package com.test.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PreferenceActivity extends AppCompatActivity {
    private SwitchMaterial btnSwitch;
    private TextInputLayout editTextCustomWord;
    private TextInputEditText textInputCustomWord;
    private MaterialButton btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        SharedPreferences sharedPref = this.getSharedPreferences("Test", Context.MODE_PRIVATE);

        btnSwitch = findViewById(R.id.btnSwitch);
        editTextCustomWord = findViewById(R.id.editTextCustomWord);
        textInputCustomWord = findViewById(R.id.textInputCustomWord);
        btnValidate = findViewById(R.id.btnValidate);

        boolean isChecked = sharedPref.getBoolean("IsCustomWord", false);
        btnSwitch.setChecked(isChecked);
        if (isChecked) {
            String btnSwitchText = "Custom text (" + sharedPref.getString("CustomWord", "Default") + ")";
            btnSwitch.setText(btnSwitchText);
        }
        btnSwitch.setOnCheckedChangeListener(switchListener);
        btnValidate.setOnClickListener(validateListener);
    }

    private CompoundButton.OnCheckedChangeListener switchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int visibility = (isChecked?View.VISIBLE:View.GONE);
            editTextCustomWord.setVisibility(visibility);
            btnValidate.setVisibility(visibility);

            SharedPreferences sharedPref = getSharedPreferences("Test", Context.MODE_PRIVATE);

            if (isChecked) {
                String customWord = sharedPref.getString("CustomWord", "");
                Log.i("JFL", "Current customWord: " + customWord);
                textInputCustomWord.setText(customWord);
            }

            else {
                Log.i("JFL", "New preference: isCustomWord false");
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("IsCustomWord", false);
                btnSwitch.setText("Custom text");
                editor.apply();
            }
        }
    };

    private View.OnClickListener validateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String customWord = textInputCustomWord.getEditableText().toString();

            SharedPreferences sharedPref = getSharedPreferences("Test", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("IsCustomWord", true);
            editor.putString("CustomWord", customWord);
            editor.apply();

            String btnSwitchText = "Custom text (" + sharedPref.getString("CustomWord", "Default") + ")";
            btnSwitch.setText(btnSwitchText);

            Log.i("JFL", "New preference: isCustomWord true");
            Log.i("JFL", "New preference: customWord " + customWord);

            editTextCustomWord.setVisibility(View.GONE);
            btnValidate.setVisibility(View.GONE);
        }
    };
}