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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

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
                allOf(withId(R.id.tvEmpty), withText(R.string.let_s_search_someone),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contentFrame),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText(R.string.let_s_search_someone)));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tvEmpty), withText(R.string.let_s_search_someone),
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
                allOf(withId(R.id.action_search),
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
        editText.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.searchTextView),
                        childAtPosition(
                                allOf(withId(R.id.search_top_bar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        editText2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.searchTextView),
                        childAtPosition(
                                allOf(withId(R.id.search_top_bar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        editText3.perform(replaceText("tarkan"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv), withText("Tarkan"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Tarkan")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tvTitle), withText("ARTIST"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("ARTIST")));

        ViewInteraction button = onView(
                allOf(withId(R.id.btnMore),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv),
                                        0),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

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
