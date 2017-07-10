package com.atasoyh.lastfmartistfinder.presenter.artistinfo;

import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.BaseView;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoContract {

    public interface View<T extends BasePresenter> extends BaseView<T> {
        void showLoading(boolean visible);
    }

    public interface Presenter extends BasePresenter {
        public void loadArtistInfo(String name, String mbid);
    }
}
