package com.atasoyh.lastfmartistfinder.view.artistdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.Bio;
import com.atasoyh.lastfmartistfinder.model.Similar;
import com.atasoyh.lastfmartistfinder.model.Tags;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoContract;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoComponent;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoModule;
import com.atasoyh.lastfmartistfinder.view.customview.ArtistView;
import com.atasoyh.lastfmartistfinder.view.customview.BioView;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoFragment extends BaseFragment implements ArtistInfoContract.View<ArtistInfoContract.Presenter> {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.sdv)
    SimpleDraweeView sdvArtistImage;

    @BindView(R.id.tvName)
    TextView tvArtistName;

    @BindView(R.id.bv)
    BioView bioView;

    @BindView(R.id.llBio)
    LinearLayout llBio;

    @BindView(R.id.llSimilars)
    LinearLayout llSimilars;

    @Inject
    ArtistInfoContract.Presenter presenter;

    private ArtistInfoComponent artistInfoComponent;

    public static ArtistInfoFragment newInstance(String artistName, String mbid, SearchMoreFragment.Type type) {
        ArtistInfoFragment fragment = new ArtistInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("artist", artistName);
        bundle.putString("mbid", mbid);
        bundle.putSerializable("type",type);
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
        ButterKnife.bind(this, view);
        presenter.loadArtistInfo();
    }

    @Override
    protected void injectDependencies(DefaultApplication application) {
        String artistName = getArguments().getString("artist", null);
        String mbid = getArguments().getString("mbid", null);
        SearchMoreFragment.Type type= (SearchMoreFragment.Type) getArguments().getSerializable("type");
        artistInfoComponent = DefaultApplication.get(getContext()).getAppComponent().plus(new ArtistInfoModule(this, artistName, mbid,type));
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
        if (visible)
            progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);


    }

    @Override
    public void showName(String name) {
        tvArtistName.setText(name);

    }

    @Override
    public void showImage(String url) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sdvArtistImage.setImageURI(url);
            }
        });
    }

    @Override
    public void showTags(@NonNull Tags tags) {

    }

    @Override
    public void showBio(@NonNull Bio bio) {
        llBio.setVisibility(View.VISIBLE);
        bioView.setBio(bio);
    }

    @Override
    public void showSimilars(@NonNull Similar similar) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<Artist> artists = similar.getArtist();
                if (artists.size() == 0) return;

                llSimilars.setVisibility(View.VISIBLE);
                for (Artist artist : artists) {
                    ArtistView artistView = new ArtistView(getContext());
                    artistView.setArtist(artist);
                    llSimilars.addView(artistView);
                }
            }
        });

    }
}
