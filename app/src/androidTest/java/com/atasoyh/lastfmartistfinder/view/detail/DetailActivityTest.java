package com.atasoyh.lastfmartistfinder.view.detail;


import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {


    @Rule
    public ActivityTestRule<DetailActivity> mActivityTestRule = new ActivityTestRule<>(DetailActivity.class,false,false);

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        Intent i = new Intent();
        i.putExtra(DetailActivity.TAG_ARTIST, "tarkan");
        i.putExtra(DetailActivity.TAG_MBID,"mbid");
        i.putExtra(DetailActivity.TAG_NAME,"tarkan");
        i.putExtra(DetailActivity.TAG_TYPE, SearchMoreFragment.Type.ARTIST);
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
