package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractor;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by atasoyh on 09/07/2017.
 */
public class SearchPresenterTest {

    @Mock
    SearchContract.View<BasePresenter> view;

    @Mock
    SearchArtistInteractor api;

    private SearchPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SearchPresenter(view, api);
    }

    @Test
    public void testInputIsNull() throws Exception {
        String input=null;
        presenter.search(input);
    }

    @Test
    public void searchWithLessReult() throws Exception {
        //response has no more value..
    }

    @Test
    void searchWithMoreResult() throws Exception {
        //response has more value...
    }

}