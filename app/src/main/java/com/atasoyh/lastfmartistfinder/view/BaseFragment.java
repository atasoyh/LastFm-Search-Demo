package com.atasoyh.lastfmartistfinder.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.BaseView;

/**
 * Created by atasoyh on 09/07/2017.
 */

public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(DefaultApplication.get(getContext()));
    }

    protected abstract void injectDependencies(DefaultApplication application);

    @Override
    public void onDestroy() {
        releaseSubComponents(DefaultApplication.get(getContext()));
        super.onDestroy();
    }

    protected abstract void releaseSubComponents(DefaultApplication application);


    public void showError(String message, View.OnClickListener onClickListener) {

        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        if (onClickListener != null) {
            snackbar.setAction(getString(R.string.ok), onClickListener);
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }
        snackbar.show();
    }

    public void showError(String message) {
        showError(message, null);
    }

    public void showErrorThanFinish(String message) {
        showError(message, view -> getActivity().finish());
    }

}
