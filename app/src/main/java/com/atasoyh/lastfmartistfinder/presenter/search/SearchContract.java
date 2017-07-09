package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.BaseView;

import java.util.List;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchContract {

    interface View extends BaseView {
        void setItems(List<Object> items);
        void enableLoadMore();
        void disableLoadMore();
    }

    interface SearchPresenter extends BasePresenter {
        void search(String keyword);
        void loadMore();
    }
}
