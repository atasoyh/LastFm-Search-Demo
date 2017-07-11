package com.atasoyh.lastfmartistfinder.view.artistdetail;


import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingPolicies;
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
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.view.search.SearchActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ArtistInfoActivityTest {


    @Rule
    public ActivityTestRule<ArtistInfoActivity> mActivityTestRule = new ActivityTestRule<>(ArtistInfoActivity.class,false,false);

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        Intent i = new Intent();
        i.putExtra(ArtistInfoActivity.TAG_ARTIST, "tarkan");
        i.putExtra(ArtistInfoActivity.TAG_MBID,"mbid");
        mActivityTestRule.launchActivity(i);
    }


    @Test
    public void artistBasicInfoIsExist(){
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tvName), withText("Tarkan"),isDisplayed()));
        textView4.check(matches(withText("Tarkan")));
    }

    @Test
    public void bioIsExistTest(){
        ViewInteraction textView = onView(
                allOf(withId(R.id.tvBio), withText("Bio"),isDisplayed()));
        textView.check(matches(withText("Bio")));

    }

    @Test
    public void similarIsExistTest(){
        ViewInteraction textView = onView(
                allOf(withId(R.id.tvSimilar), withText("Similar"),isDisplayed()));
        textView.check(matches(withText("Similar")));
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
