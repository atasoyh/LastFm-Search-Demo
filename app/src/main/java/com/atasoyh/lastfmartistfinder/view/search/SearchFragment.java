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
import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.SearchItems;
import com.atasoyh.lastfmartistfinder.model.Track;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;
import com.atasoyh.lastfmartistfinder.view.RxBus;
import com.atasoyh.lastfmartistfinder.view.artistdetail.ArtistInfoActivity;
import com.atasoyh.lastfmartistfinder.view.events.ShowMoreResult;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchComponent;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchFragment extends BaseFragment implements SearchActivity.OnTextListener,SearchContract.View<SearchContract.Presenter>, SearchListAdapter.OnItemClickListener, SearchListAdapter.OnMoreClickLister {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    @Inject
    SearchContract.Presenter presenter;

    private String lastQuery;

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
        recyclerView.setHasFixedSize(true);
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
        adapter = new SearchListAdapter(items);
        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.isItemHeader(position))
                    return 2;
                return 1;

            }
        });
        adapter.setOnItemClickListener(this);
        adapter.setOnMoreClickLister(this);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshItems() {
        // adapter.setItems(new ArrayList<>());

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
    public void onItemClick(Artist item) {
        //todo refactor to RXBus
        Intent intent = new Intent(getContext(), ArtistInfoActivity.class);
        intent.putExtra(ArtistInfoActivity.TAG_ARTIST, item.getName());
        intent.putExtra(ArtistInfoActivity.TAG_MBID, item.getMbid());
        startActivity(intent);

    }

    @Override
    public void onItemClick(Track track) {
        Intent intent = new Intent(getContext(), ArtistInfoActivity.class);
        intent.putExtra(ArtistInfoActivity.TAG_ARTIST, track.getName());
        intent.putExtra(ArtistInfoActivity.TAG_MBID, track.getMbid());
        startActivity(intent);
    }

    @Override
    public void onItemClick(Album album) {
        Intent intent = new Intent(getContext(), ArtistInfoActivity.class);
        intent.putExtra(ArtistInfoActivity.TAG_ARTIST, album.getName());
        intent.putExtra(ArtistInfoActivity.TAG_MBID, album.getMbid());
        startActivity(intent);
    }

    @Override
    public void onArtistMoreClicked() {
        RxBus.publish(new ShowMoreResult(SearchMoreFragment.Type.ARTIST, lastQuery));
    }

    @Override
    public void onAlbumMoreClicked() {
        RxBus.publish(new ShowMoreResult(SearchMoreFragment.Type.ALBUM, lastQuery));

    }

    @Override
    public void onTrackMoreClicked() {
        RxBus.publish(new ShowMoreResult(SearchMoreFragment.Type.TRACK, lastQuery));

    }

    @Override
    public void onTextChanged(String text) {
        presenter.search(text);
        lastQuery = text;

    }
}
