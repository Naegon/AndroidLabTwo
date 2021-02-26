package com.test.labtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Authentication extends AppCompatActivity {
    private Button btnAuthenticate;
    private TextInputEditText textInputUsername;
    private TextInputEditText textInputPassword;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        btnAuthenticate = findViewById(R.id.btnLogin);
        btnAuthenticate.setOnClickListener(Authenticate);

        textInputUsername = findViewById(R.id.textInputUsername);
        textInputPassword = findViewById(R.id.textInputPassword);
        textViewResult = findViewById(R.id.textViewResult);
    }

    private final View.OnClickListener Authenticate = v -> {
        new Thread(() -> {
            URL url;
            try {
                url = new URL("https://httpbin.org/basic-auth/bob/sympa");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                String basicAuth = "Basic " + Base64.encodeToString(
                        (textInputUsername.getEditableText().toString() + ":"
                                + textInputPassword.getEditableText().toString()
                        ).getBytes(), Base64.NO_WRAP);
                urlConnection.setRequestProperty ("Authorization", basicAuth);
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    String s = readStream(in);
                    Log.i("JFL", s);

                    JSONObject result = new JSONObject(s);
                    String res = result.getString("authenticated");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textViewResult.setVisibility(View.VISIBLE);
                            textViewResult.setText(res);
                        }
                    });
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    };

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
