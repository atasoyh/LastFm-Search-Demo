package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by atasoyh on 09/07/2017.
 */
public class SearchPresenterTest {

    @Mock
    SearchContract.View view;

    @Mock
    LastFmApi api;

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