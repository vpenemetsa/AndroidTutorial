package com.example.androidtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class RecipeListActivity extends FragmentActivity {

    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }



    }

    protected boolean getDeviceType() {
        return mTwoPane;
    }

    protected void itemClicked(String url, String title) {
        if (mTwoPane) {
            Bundle bundle = new Bundle();
            bundle.putString("URL", url);
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.recipe_detail_container, fragment).commit();
        } else {
            Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
            detailIntent.putExtra("URL", url);
            detailIntent.putExtra("TITLE", title);
            startActivity(detailIntent);
        }
    }
}