package com.test.flickrapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<AppCompatImageView> image;

    public AsyncBitmapDownloader(WeakReference<AppCompatImageView> image) {
        this.image = image;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        AppCompatImageView imageView = image.get();
        imageView.setImageBitmap(bitmap);
    }
}
