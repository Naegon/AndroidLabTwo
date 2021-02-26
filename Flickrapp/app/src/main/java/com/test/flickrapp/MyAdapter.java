package com.test.flickrapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.Vector;

class MyAdapter extends BaseAdapter {
    private Vector<String> vector;
    private Context context;

    public MyAdapter(Context context) {
        this.vector = new Vector<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return vector.size();
    }

    @Override
    public Object getItem(int position) {
        return vector.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //We must create a View:
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.bitmap_layout, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.image);

        Response.Listener<Bitmap> rep_listener = image::setImageBitmap;

        ImageRequest imageRequest = new ImageRequest(vector.get(position), rep_listener, 0, 0, ImageView.ScaleType.CENTER_CROP, null, null);
        MySingleton.getInstance(context).addToRequestQueue(imageRequest);

        return convertView;
    }

    public void add(String url) {
        Log.i("JFL", "Adding to adapter url: " + url);
        vector.add(url);
    }
}
