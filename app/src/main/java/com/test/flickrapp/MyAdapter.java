package com.test.flickrapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){
            //We must create a View:
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.bitmap_layout, parent, false);
        }
//        TextView textView = convertView.findViewById(R.id.textView);
//        textView.setText(vector.get(position));

        RequestQueue queue = MySingleton.getInstance(parent.getContext()).getRequestQueue();
        ImageView image = convertView.findViewById(R.id.image);
//        image.setImageBitmap(vector.get(position));

        return convertView;    }

    public void add(String url) {
        Log.i("JFL", "Adding to adapter url: " + url);
        vector.add(url);
    }
}
