package com.atasoyh.lastfmartistfinder.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.SearchItems;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;
import com.atasoyh.lastfmartistfinder.view.artistdetail.ArtistInfoActivity;
import com.atasoyh.lastfmartistfinder.view.search.artist.SearchListAdapter;
import com.atasoyh.lastfmartistfinder.view.search.artist.dpi.ArtistSearchComponent;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchComponent;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchModule;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View<SearchContract.Presenter>, SearchListAdapter.OnItemClickListener, SearchListAdapter.OnNeededLoadMoreListener {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    @Inject
    SearchContract.Presenter presenter;

    private SearchComponent searchComponent;
    private SearchListAdapter adapter;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    protected void injectDependencies(DefaultApplication application) {
        searchComponent = DefaultApplication.get(getContext()).getAppComponent().plus(new SearchModule(this));
        searchComponent.inject(this);
    }

    @Override
    protected void releaseSubComponents(DefaultApplication application) {
        searchComponent = null;
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        adapter = new SearchListAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }


    @Override
    public void setItems(SearchItems items) {

    }

    @Override
    public void refreshItems() {
        adapter.setItems(new ArrayList<>());

    }

    @Override
    public void showEmptyView() {
        tvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        tvEmpty.setVisibility(View.GONE);
    }

    public void onQueryTextChange(String newText) {
        presenter.search(newText);

    }

    @Override
    public void onItemClick(Artist item) {
        Intent intent = new Intent(getContext(), ArtistInfoActivity.class);
        intent.putExtra(ArtistInfoActivity.TAG_ARTIST, item.getName());
        intent.putExtra(ArtistInfoActivity.TAG_MBID, item.getMbid());
        startActivity(intent);

    }

    @Override
    public void onNeededLoadMore() {

    }
}
