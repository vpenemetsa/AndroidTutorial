package com.example.androidtutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidtutorial.dummy.DummyContent;

public class RecipeListFragment extends Fragment {

    private RecipeAdapter mAdapter;
    private ListView mList;
    private ImageCache mCache;

    public RecipeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mCache = new ImageCache(cacheSize);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mList = (ListView) rootView.findViewById(R.id.list);
        mAdapter = new RecipeAdapter(getActivity(), mCache);
        mAdapter.setData(DummyContent.getDummyContent());
        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), mAdapter.getItem(i).getRecipeName() + " clicked", Toast.LENGTH_SHORT).show();
                ((RecipeListActivity) getActivity()).itemClicked(mAdapter.getItem(i).getSourceUrl(), mAdapter.getItem(i).getRecipeName());
            }
        });

        return rootView;
    }
}