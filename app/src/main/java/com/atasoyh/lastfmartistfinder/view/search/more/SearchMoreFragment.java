package com.atasoyh.lastfmartistfinder.view.search.more;

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
import com.atasoyh.lastfmartistfinder.model.LastFMDisplayableInterface;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchMoreContract;
import com.atasoyh.lastfmartistfinder.util.TextUtils;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;
import com.atasoyh.lastfmartistfinder.view.artistdetail.ArtistInfoActivity;
import com.atasoyh.lastfmartistfinder.view.search.SearchActivity;
import com.atasoyh.lastfmartistfinder.view.search.more.dpi.SearchMoreComponent;
import com.atasoyh.lastfmartistfinder.view.search.more.dpi.SearchMoreModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchMoreFragment extends BaseFragment implements SearchActivity.OnTextListener, SearchMoreContract.View<SearchMoreContract.Presenter>, SearchMoreListAdapter.OnItemClickListener, SearchMoreListAdapter.OnNeededLoadMoreListener {

    private String keyword;
    private Type type;


    public enum Type {ARTIST, ALBUM, TRACK}


    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    @Inject
    SearchMoreContract.Presenter presenter;

    private SearchMoreComponent searchMoreComponent;
    private SearchMoreListAdapter adapter;

    public static SearchMoreFragment newInstance(Type type, String keyword) {
        SearchMoreFragment fragment = new SearchMoreFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", type);
        bundle.putString("keyword", keyword);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void injectDependencies(DefaultApplication application) {
        keyword = getArguments().getString("keyword", null);
        type = (Type) getArguments().getSerializable("type");
        searchMoreComponent = DefaultApplication.get(getContext()).getAppComponent().plus(new SearchMoreModule(this, type));
        searchMoreComponent.inject(this);
    }

    @Override
    protected void releaseSubComponents(DefaultApplication application) {
        searchMoreComponent = null;
    }

    @Override
    public void setPresenter(SearchMoreContract.Presenter presenter) {
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
        adapter = new SearchMoreListAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        if (!TextUtils.isEmpty(keyword))
            onTextChanged(keyword);

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
    public void addItems(List<LastFMDisplayableInterface> items) {
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

    @Override
    public void onTextChanged(String text) {
        presenter.search(text);
    }

    @Override
    public void onItemClick(LastFMDisplayableInterface item) {
        Intent intent = new Intent(getContext(), ArtistInfoActivity.class);
        intent.putExtra(ArtistInfoActivity.TAG_ARTIST, item.getName());
        intent.putExtra(ArtistInfoActivity.TAG_MBID, item.getMbid());
        intent.putExtra(ArtistInfoActivity.TAG_MBID, type);
        startActivity(intent);

    }

    @Override
    public void onNeededLoadMore() {
        presenter.loadMore();
    }
}
