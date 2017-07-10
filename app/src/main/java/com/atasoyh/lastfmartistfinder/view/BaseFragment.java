package com.atasoyh.lastfmartistfinder.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.atasoyh.lastfmartistfinder.DefaultApplication;

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
}
