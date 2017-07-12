package com.atasoyh.lastfmartistfinder.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class RxSearch {

    public static Observable<String> fromSearchView(@NonNull final MaterialSearchView searchView) {
        final BehaviorSubject<String> subject = BehaviorSubject.create();

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    subject.onNext(newText);
                }
                return true;
            }
        });

        return subject;
    }
}