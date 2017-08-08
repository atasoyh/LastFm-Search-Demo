/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atasoyh.lastfmartistfinder.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.atasoyh.lastfmartistfinder.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * Adds a new Fragment on top of the current Fragment back stack
     *
     * @param fragment {@link android.support.v4.app.Fragment} Fragment to add
     */
    public void addFragmentToBackstack(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        Fragment topFragment=getTopFragment(fragmentManager);
        if (topFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .hide(topFragment)
                    .commit();
        }
        fragmentManager
                .beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    /**
     * Get the current top Fragment displayed
     *
     * @return top Fragment
     */
    public Fragment getTopFragment(@NonNull FragmentManager fragmentManager) {
        checkNotNull(fragmentManager);
        return fragmentManager.findFragmentById(R.id.contentFrame);
    }

    /**
     * Sets a new root Fragment in the Fragment back stack. Clears all current Fragments that
     * may exist in the back stack
     *
     * @param fragment {@link android.support.v4.app.Fragment} Fragment to add
     */
    private void setRootFragment(@NonNull FragmentManager fragmentManager,@NonNull final Fragment fragment) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        clearFragmentBackstack(fragmentManager);
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.contentFrame, fragment, fragment.getClass().getSimpleName());
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    /**
     * Clears the current top Fragment from the backstack and sends the user to the previous Fragment
     */
    private void popFragment(FragmentManager fragmentManager) {
        checkNotNull(fragmentManager);
        fragmentManager.popBackStack();
    }

    /**
     * Clears all Fragments from the Fragment back stack
     */
    private void clearFragmentBackstack(@NonNull FragmentManager fragmentManager) {
        checkNotNull(fragmentManager);
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStackImmediate(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}
