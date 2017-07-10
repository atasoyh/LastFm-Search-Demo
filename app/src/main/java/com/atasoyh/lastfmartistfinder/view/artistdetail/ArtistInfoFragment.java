package com.atasoyh.lastfmartistfinder.view.artistdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Bio;
import com.atasoyh.lastfmartistfinder.model.Tags;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoContract;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoComponent;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoModule;

import javax.inject.Inject;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoFragment extends BaseFragment implements ArtistInfoContract.View<ArtistInfoContract.Presenter> {

    @Inject
    ArtistInfoContract.Presenter presenter;

    private ArtistInfoComponent artistInfoComponent;

    public static ArtistInfoFragment newInstance(String artistName, String mbid) {
        ArtistInfoFragment fragment = new ArtistInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("artist", artistName);
        bundle.putString("mbid", mbid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artistinfo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadArtistInfo();
    }

    @Override
    protected void injectDependencies(DefaultApplication application) {
        String artistName = getArguments().getString("artist", null);
        String mbid = getArguments().getString("mbid", null);
        artistInfoComponent = DefaultApplication.get(getContext()).getAppComponent().plus(new ArtistInfoModule(this, artistName, mbid));
        artistInfoComponent.inject(this);
    }

    @Override
    protected void releaseSubComponents(DefaultApplication application) {
        artistInfoComponent = null;
    }

    @Override
    public void setPresenter(ArtistInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading(boolean visible) {

    }

    @Override
    public void showName(String name) {

    }

    @Override
    public void showImage(String url) {

    }

    @Override
    public void showTags(Tags tags) {

    }

    @Override
    public void showBio(Bio bio) {

    }
}
