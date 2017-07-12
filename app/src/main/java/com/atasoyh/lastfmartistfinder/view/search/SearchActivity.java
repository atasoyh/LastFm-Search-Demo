package com.atasoyh.lastfmartistfinder.view.search;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.util.RxSearch;
import com.atasoyh.lastfmartistfinder.view.BaseActivity;
import com.google.common.eventbus.Subscribe;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private SearchFragment searchFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        RxSearch.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(item -> item.length() > 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    searchFragment.onQueryTextChange(query);
                });


        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (searchFragment == null) {
            // Create the fragment
            searchFragment = SearchFragment.newInstance();
            activityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), searchFragment, R.id.contentFrame);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    protected void injectDependencies(DefaultApplication application) {
        application.getAppComponent().inject(this);
    }

    @Override
    protected void releaseSubComponents(DefaultApplication application) {

    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen())
            searchView.closeSearch();
        else
            super.onBackPressed();
    }
}
