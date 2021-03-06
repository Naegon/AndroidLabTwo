package com.test.flickrapp;

import android.os.AsyncTask;
import android.util.Log;
import androidx.appcompat.widget.AppCompatImageView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

class AsyncFlickrJSONData extends AsyncTask<String, Void, JSONObject> {
    private URL url;
    private JSONObject result;
    private final WeakReference<AppCompatImageView> image;

    public AsyncFlickrJSONData(WeakReference<AppCompatImageView> image) {
        this.image = image;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = readStream(in);
                Log.i("JFL", s);

                result = new JSONObject(s);
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        try {
            Log.i("JFL", result.getJSONArray("items").getJSONObject(0).getString("link"));
            AsyncBitmapDownloader task = new AsyncBitmapDownloader(image);
            task.execute(result.getJSONArray("items").getJSONObject(0).getJSONObject("media").getString("m"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString().replaceFirst("jsonFlickrFeed\\(", "");
        } catch (IOException e) {
            return "";
        }
    }
}
