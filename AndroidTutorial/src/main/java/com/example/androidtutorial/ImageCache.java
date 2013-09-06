package com.example.androidtutorial;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by vpenemetsa on 8/13/13.
 */
public class ImageCache extends LruCache<String, Bitmap> {

    public ImageCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount() / 1024;
    }

    public void addBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null) {
            put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String key) {
        return get(key);
    }
}
