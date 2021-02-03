package com.test.flickrapp;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import java.lang.ref.WeakReference;

class GetImageOnClickListener implements View.OnClickListener {
    private final WeakReference<AppCompatImageView> image;

    public GetImageOnClickListener(AppCompatImageView image) {
        this.image = new WeakReference<>(image);
    }

    @Override
    public void onClick(View v) {
        AsyncFlickrJSONData task = new AsyncFlickrJSONData(image);
        task.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json");
    }
}
