package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.model.ArtistMatches;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Type;

import io.reactivex.Observer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by atasoyh on 09/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchMorePresenterTest {

    private String mockDataLess = "{\"results\":{\"opensearch:Query\":{\"#text\":\"\",\"role\":\"request\",\"searchTerms\":\"tarkan\",\"startPage\":\"1\"},\"opensearch:totalResults\":\"25\",\"opensearch:startIndex\":\"0\",\"opensearch:itemsPerPage\":\"10\",\"artistmatches\":{\"artist\":[{\"name\":\"Tarkan\",\"listeners\":\"180252\",\"mbid\":\"4ec2451d-ed0c-4273-b683-4c1312df25fd\",\"url\":\"https://www.last.fm/music/Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/272288aded964783287ef0277379a28d.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/272288aded964783287ef0277379a28d.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/272288aded964783287ef0277379a28d.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/272288aded964783287ef0277379a28d.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/272288aded964783287ef0277379a28d.png\",\"size\":\"mega\"}]},{\"name\":\"Tarakany!\",\"listeners\":\"4405\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakany%21\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan\",\"listeners\":\"4652\",\"mbid\":\"a6a70a9c-a17a-46cd-8a12-ba3fe69f1036\",\"url\":\"https://www.last.fm/music/DJ+Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány Művek\",\"listeners\":\"284\",\"mbid\":\"e35ae7bb-e482-4240-8d1c-602f7aa15907\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny+M%C5%B1vek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"mega\"}]},{\"name\":\"Tarkan - WwW.PrensesBoard.De.tt\",\"listeners\":\"747\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+-+WwW.PrensesBoard.De.tt\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány-Müvek\",\"listeners\":\"205\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny-M%C3%BCvek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"mega\"}]},{\"name\":\"Dj Tarkan At Frisky Radio\",\"listeners\":\"338\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Dj+Tarkan+At+Frisky+Radio\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarakani\",\"listeners\":\"209\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakani\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarkan Tevetoglu\",\"listeners\":\"409\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+Tevetoglu\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan & Dammex\",\"listeners\":\"310\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/DJ+Tarkan+&+Dammex\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"mega\"}]}]},\"@attr\":{\"for\":\"tarkan\"}}}";
    private String mockDataMore = "{\"results\":{\"opensearch:Query\":{\"#text\":\"\",\"role\":\"request\",\"searchTerms\":\"tarkan\",\"startPage\":\"1\"},\"opensearch:totalResults\":\"3886\",\"opensearch:startIndex\":\"0\",\"opensearch:itemsPerPage\":\"10\",\"artistmatches\":{\"artist\":[{\"name\":\"Tarkan\",\"listeners\":\"180252\",\"mbid\":\"4ec2451d-ed0c-4273-b683-4c1312df25fd\",\"url\":\"https://www.last.fm/music/Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/272288aded964783287ef0277379a28d.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/272288aded964783287ef0277379a28d.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/272288aded964783287ef0277379a28d.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/272288aded964783287ef0277379a28d.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/272288aded964783287ef0277379a28d.png\",\"size\":\"mega\"}]},{\"name\":\"Tarakany!\",\"listeners\":\"4405\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakany%21\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan\",\"listeners\":\"4652\",\"mbid\":\"a6a70a9c-a17a-46cd-8a12-ba3fe69f1036\",\"url\":\"https://www.last.fm/music/DJ+Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány Művek\",\"listeners\":\"284\",\"mbid\":\"e35ae7bb-e482-4240-8d1c-602f7aa15907\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny+M%C5%B1vek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"mega\"}]},{\"name\":\"Tarkan - WwW.PrensesBoard.De.tt\",\"listeners\":\"747\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+-+WwW.PrensesBoard.De.tt\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány-Müvek\",\"listeners\":\"205\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny-M%C3%BCvek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"mega\"}]},{\"name\":\"Dj Tarkan At Frisky Radio\",\"listeners\":\"338\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Dj+Tarkan+At+Frisky+Radio\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarakani\",\"listeners\":\"209\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakani\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarkan Tevetoglu\",\"listeners\":\"409\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+Tevetoglu\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan & Dammex\",\"listeners\":\"310\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/DJ+Tarkan+&+Dammex\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"mega\"}]}]},\"@attr\":{\"for\":\"tarkan\"}}}";

    @Mock
    SearchMoreContract.View<BasePresenter> view;

    @Mock
    SearchInteractor api;

    private SearchMorePresenter artistSearchPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        artistSearchPresenter = new SearchMorePresenter(view, SearchMoreFragment.Type.ARTIST, api);

        when(api.search(eq("lessData"), anyInt(), anyInt())).thenReturn(new io.reactivex.Observable<SearchResponse>() {
            @Override
            protected void subscribeActual(Observer<? super SearchResponse> observer) {
                Gson gson = new Gson();
                Type type = new TypeToken<SearchResponse>() {
                }.getType();
                SearchResponse response = gson.fromJson(mockDataLess, type);
                observer.onNext(response);
                observer.onComplete();
            }
        });

        when(api.search(eq("moreData"), anyInt(), anyInt())).thenReturn(new io.reactivex.Observable<SearchResponse>() {
            @Override
            protected void subscribeActual(Observer<? super SearchResponse> observer) {
                Gson gson = new Gson();
                Type type = new TypeToken<SearchResponse>() {
                }.getType();
                SearchResponse response = gson.fromJson(mockDataMore, type);
                observer.onNext(response);
                observer.onComplete();
            }
        });


    }

    @Test
    public void setupListener() throws Exception {
        artistSearchPresenter.setupListener();
        verify(view).setPresenter(artistSearchPresenter);

    }


    @Test
    public void testInputIsNull() throws Exception {
        String input = null;
        artistSearchPresenter.search(input);
        verify(view).refreshItems();
        verify(view).disableLoadMore();
        verifyNoMoreInteractions(api);
    }

    @Test
    public void testSearchWithLessReult() throws Exception {
        artistSearchPresenter.search("lessData");
        verify(api).search(any(), anyInt(), anyInt());
        verify(view, times(1)).showLoading(true);
        verify(view, never()).enableLoadMore();
        verify(view, times(1)).disableLoadMore();
        verify(view, times(1)).showLoading(false);
        verify(view, times(1)).addItems(any());
    }


    @Test
    public void searchWithMoreResult() throws Exception {
        artistSearchPresenter.search("moreData");
        verify(api).search(any(), anyInt(), anyInt());
        verify(view, times(1)).showLoading(true);
        verify(view, never()).disableLoadMore();
        verify(view, times(1)).enableLoadMore();
        verify(view, times(1)).showLoading(false);
        verify(view, times(1)).addItems(any());
    }


}