package com.test.flickrapp;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Vector;

class MyAdapter extends BaseAdapter {
    private Vector<String> vector;

    public void dd(String url) {
        vector.add(url);
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
        Log.i("JFL", "TODO");
        return null;
    }
}
