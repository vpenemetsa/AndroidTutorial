package com.example.androidtutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtutorial.api.Recipe;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by vpenemetsa on 8/12/13.
 */
public class RecipeAdapter extends ArrayAdapter<Recipe> {

    LayoutInflater mInflater;
    ImageCache mCache;

    public RecipeAdapter(Context context, ImageCache cache) {
        super(context, -1);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCache = cache;
    }

    public void setData(List<Recipe> recipes) {
        addAll(recipes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Recipe recipe = getItem(position);
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.row_recipe, parent, false);
            holder.mImage = (ImageView) convertView.findViewById(R.id.image);
            holder.mTitle = (TextView) convertView.findViewById(R.id.title);
            holder.mSubtitle = (TextView) convertView.findViewById(R.id.subtitle);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mImage.setImageBitmap(null);
        }

        holder.mTitle.setText(recipe.getRecipeName());
        holder.mSubtitle.setText(recipe.getSource());
        new DownloadImageTask(holder.mImage).execute(recipe.getImage());

        return convertView;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Recipe recipe = getItem(position);
//
//        convertView = mInflater.inflate(R.layout.row_recipe, parent, false);
//        ImageView mImage = (ImageView) convertView.findViewById(R.id.image);
//        TextView mTitle = (TextView) convertView.findViewById(R.id.title);
//        TextView mSubtitle = (TextView) convertView.findViewById(R.id.subtitle);
//
//        mTitle.setText(recipe.getRecipeName());
//        mSubtitle.setText(recipe.getSource());
//
//        return convertView;
//    }

    class ViewHolder {
        ImageView mImage;
        TextView mTitle;
        TextView mSubtitle;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView mImageView;

        public DownloadImageTask(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap image = mCache.get(url);

            if (image == null) {
                try {
                    InputStream in = new URL(url).openStream();
                    image = BitmapFactory.decodeStream(in);
                    mCache.put(url, image);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }

            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}