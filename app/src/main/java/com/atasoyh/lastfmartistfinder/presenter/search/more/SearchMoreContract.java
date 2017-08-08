package com.atasoyh.lastfmartistfinder.presenter.search.more;

import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.LastFMDisplayableInterface;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.BaseView;

import java.util.List;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchMoreContract {

    public interface View<T extends BasePresenter> extends BaseView<T> {
        void addItems(List<LastFMDisplayableInterface> items);
        void refreshItems();
        void enableLoadMore();
        void disableLoadMore();
        void showEmptyView();
        void hideEmptyView();
    }

    public interface Presenter extends BasePresenter {
        void search(String keyword);
        void loadMore();
    }


}
