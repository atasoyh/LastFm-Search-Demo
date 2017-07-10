package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.BaseView;

import java.util.List;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchContract {

    public interface View<T extends BasePresenter> extends BaseView<T> {
        void addItems(List<Artist> items);
        void refreshItems();
        void enableLoadMore();
        void disableLoadMore();
    }

    public interface Presenter extends BasePresenter {
        void search(String keyword);
        void loadMore();
    }
}
