package com.atasoyh.lastfmartistfinder.view.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

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
        } else {
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

    public void onQueryTextChange(String newText) {
        presenter.search(newText);
    }

    @Override
    public void onItemClick(Artist item) {

    }

    @Override
    public void onNeededLoadMore() {
        presenter.loadMore();
    }
}
