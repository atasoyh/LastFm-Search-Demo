package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.SearchItems;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.BaseView;

import java.util.List;

/**
 * Created by atasoyh on 07/08/2017.
 */

public class SearchContract {

    public interface View<T extends BasePresenter> extends BaseView<T> {
        void setItems(SearchItems items);
        void refreshItems();
        void showEmptyView();
        void hideEmptyView();

    }

    public interface Presenter extends BasePresenter {
        void search(String keyword);
    }
}
