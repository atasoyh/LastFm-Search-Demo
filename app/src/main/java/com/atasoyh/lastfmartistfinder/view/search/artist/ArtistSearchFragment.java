package com.atasoyh.lastfmartistfinder.view.search.artist;

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
import com.atasoyh.lastfmartistfinder.presenter.search.ArtistSearchContract;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;
import com.atasoyh.lastfmartistfinder.view.artistdetail.ArtistInfoActivity;
import com.atasoyh.lastfmartistfinder.view.search.artist.dpi.ArtistSearchComponent;
import com.atasoyh.lastfmartistfinder.view.search.artist.dpi.ArtistSearchModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class ArtistSearchFragment extends BaseFragment implements ArtistSearchContract.View<ArtistSearchContract.Presenter>, SearchListAdapter.OnItemClickListener, SearchListAdapter.OnNeededLoadMoreListener {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    @Inject
    ArtistSearchContract.Presenter presenter;

    private ArtistSearchComponent artistSearchComponent;
    private SearchListAdapter adapter;

    public static ArtistSearchFragment newInstance() {
        ArtistSearchFragment fragment = new ArtistSearchFragment();
        return fragment;
    }

    @Override
    protected void injectDependencies(DefaultApplication application) {
        artistSearchComponent = DefaultApplication.get(getContext()).getAppComponent().plus(new ArtistSearchModule(this));
        artistSearchComponent.inject(this);
    }

    @Override
    protected void releaseSubComponents(DefaultApplication application) {
        artistSearchComponent = null;
    }

    @Override
    public void setPresenter(ArtistSearchContract.Presenter presenter) {
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
    public void addItems(List<Artist> items) {
        adapter.addItems(items);
    }

    @Override
    public void refreshItems() {
        adapter.setItems(new ArrayList<>());

    }

    @Override
    public void enableLoadMore() {
        adapter.setOnNeededLoadMoreListener(this);

    }

    @Override
    public void disableLoadMore() {
        adapter.setOnNeededLoadMoreListener(null);
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
        presenter.loadMore();
    }
}
