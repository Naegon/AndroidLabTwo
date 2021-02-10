package com.test.flickrapp;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

class AsyncFlickrJSONDataForList extends AsyncTask<String, Void, JSONObject> {
    private URL url;
    private JSONObject result;
    private final WeakReference<MyAdapter> myAdapter;

    public AsyncFlickrJSONDataForList(WeakReference<MyAdapter> myAdapter) {
        this.myAdapter = myAdapter;
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
            MyAdapter adapter = myAdapter.get();
            Log.i("JFL", result.getJSONArray("items").getJSONObject(0).getString("link"));
            JSONArray items = result.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                adapter.add(items.getJSONObject(i).getJSONObject("media").getString("m"));
            }

            adapter.notifyDataSetChanged();
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
