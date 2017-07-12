package com.atasoyh.lastfmartistfinder.presenter;

/**
 * Created by atasoyh on 09/07/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
    void showLoading(boolean visible);
    void showError(String message);
}
