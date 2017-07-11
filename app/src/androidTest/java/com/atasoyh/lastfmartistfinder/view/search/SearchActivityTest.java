package com.atasoyh.lastfmartistfinder.view.search;


import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.TestAppComponent;
import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;
import com.google.gson.Gson;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    private String mockDataLess = "{\"results\":{\"opensearch:Query\":{\"#text\":\"\",\"role\":\"request\",\"searchTerms\":\"tarkan\",\"startPage\":\"1\"},\"opensearch:totalResults\":\"25\",\"opensearch:startIndex\":\"0\",\"opensearch:itemsPerPage\":\"10\",\"artistmatches\":{\"artist\":[{\"name\":\"Tarkan\",\"listeners\":\"180252\",\"mbid\":\"4ec2451d-ed0c-4273-b683-4c1312df25fd\",\"url\":\"https://www.last.fm/music/Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/272288aded964783287ef0277379a28d.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/272288aded964783287ef0277379a28d.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/272288aded964783287ef0277379a28d.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/272288aded964783287ef0277379a28d.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/272288aded964783287ef0277379a28d.png\",\"size\":\"mega\"}]},{\"name\":\"Tarakany!\",\"listeners\":\"4405\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakany%21\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan\",\"listeners\":\"4652\",\"mbid\":\"a6a70a9c-a17a-46cd-8a12-ba3fe69f1036\",\"url\":\"https://www.last.fm/music/DJ+Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány Művek\",\"listeners\":\"284\",\"mbid\":\"e35ae7bb-e482-4240-8d1c-602f7aa15907\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny+M%C5%B1vek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"mega\"}]},{\"name\":\"Tarkan - WwW.PrensesBoard.De.tt\",\"listeners\":\"747\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+-+WwW.PrensesBoard.De.tt\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány-Müvek\",\"listeners\":\"205\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny-M%C3%BCvek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"mega\"}]},{\"name\":\"Dj Tarkan At Frisky Radio\",\"listeners\":\"338\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Dj+Tarkan+At+Frisky+Radio\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarakani\",\"listeners\":\"209\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakani\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarkan Tevetoglu\",\"listeners\":\"409\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+Tevetoglu\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan & Dammex\",\"listeners\":\"310\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/DJ+Tarkan+&+Dammex\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"mega\"}]}]},\"@attr\":{\"for\":\"tarkan\"}}}";

    @Rule
    public ActivityTestRule<SearchActivity> mActivityTestRule = new ActivityTestRule<>(SearchActivity.class);

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        DefaultApplication app
                = (DefaultApplication) instrumentation.getTargetContext().getApplicationContext();
        TestAppComponent component = (TestAppComponent) app.appComponent;
        component.inject(this);
    }


    @Test
    public void isTextviewAndSearchButtonVisibleAtivityTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.tvEmpty), withText("Let's search someone...:)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contentFrame),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Let's search someone...:)")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tvEmpty), withText("Let's search someone...:)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contentFrame),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

    }

    @Test
    public void testSearchViewButton() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_search), withContentDescription("Search…"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction searchView = onView(
                allOf(withId(R.id.searchTextView),
                        childAtPosition(
                                allOf(withId(R.id.search_top_bar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        searchView.check(matches(isDisplayed()));
    }


    @Test
    public void testSearchResult() {
        //mockAPi();
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_search), withContentDescription("Search…"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.searchTextView),
                        childAtPosition(
                                allOf(withId(R.id.search_top_bar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        editText.perform(replaceText("tarkan"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv), withText("Tarkan"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Tarkan")));



    }






    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @AfterClass
    public static void tearDownClass() {
        RxAndroidPlugins.reset();
    }
}
