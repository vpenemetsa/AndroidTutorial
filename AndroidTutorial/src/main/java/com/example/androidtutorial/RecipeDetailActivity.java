package com.example.androidtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class RecipeDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getStringExtra("TITLE"));

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("URL", getIntent().getStringExtra("URL"));
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.recipe_detail_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpTo(this, new Intent(this, RecipeListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
