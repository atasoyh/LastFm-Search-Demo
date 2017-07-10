package com.atasoyh.lastfmartistfinder.presenter.artistinfo;

import com.atasoyh.lastfmartistfinder.model.Bio;
import com.atasoyh.lastfmartistfinder.model.Tags;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.BaseView;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoContract {

    public interface View<T extends BasePresenter> extends BaseView<T> {
        void showName(String name);

        void showImage(String url);

        void showTags(Tags tags);

        void showBio(Bio bio);
    }

    public interface Presenter extends BasePresenter {
        void loadArtistInfo();
    }
}
