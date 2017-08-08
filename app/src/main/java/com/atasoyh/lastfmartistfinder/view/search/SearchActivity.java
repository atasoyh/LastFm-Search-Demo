package com.atasoyh.lastfmartistfinder.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.util.RxSearch;
import com.atasoyh.lastfmartistfinder.view.BaseActivity;
import com.atasoyh.lastfmartistfinder.view.RxBus;
import com.atasoyh.lastfmartistfinder.view.detail.DetailActivity;
import com.atasoyh.lastfmartistfinder.view.events.OpenItemDetail;
import com.atasoyh.lastfmartistfinder.view.events.ShowMoreResult;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {


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
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        RxSearch.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(item -> item.length() > 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    ((OnTextListener) activityUtils.getTopFragment(getSupportFragmentManager())).onTextChanged(query);
                });


        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (searchFragment == null) {
            // Create the fragment
            searchFragment = SearchFragment.newInstance();
            activityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), searchFragment, R.id.contentFrame);
        }

        RxBus.subscribe(message -> {
            if (message instanceof ShowMoreResult) {
                ShowMoreResult showMoreResult = (ShowMoreResult) message;
                activityUtils.addFragmentToBackstack(getSupportFragmentManager(), SearchMoreFragment.newInstance(showMoreResult.type, showMoreResult.keyword), R.id.contentFrame);
            }else if(message instanceof OpenItemDetail){
                OpenItemDetail openItemDetail=(OpenItemDetail)message;
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra(DetailActivity.TAG_ARTIST, openItemDetail.item.getArtistName());
                intent.putExtra(DetailActivity.TAG_NAME,openItemDetail.item.getName());
                intent.putExtra(DetailActivity.TAG_MBID, openItemDetail.item.getMbid());
                intent.putExtra(DetailActivity.TAG_TYPE,openItemDetail.type);
                startActivity(intent);
            }
        });

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

    @Override
    public void onBackStackChanged() {
        // track what type of page we are currently on. Can be either native or webview
        final Fragment topFragment = activityUtils.getTopFragment(getSupportFragmentManager());
        if (topFragment != null) {
            if (topFragment.isHidden()) {
                getSupportFragmentManager().beginTransaction().show(topFragment).commit();
            }
        }
    }

    public interface OnTextListener {
        void onTextChanged(String text);
    }
}
