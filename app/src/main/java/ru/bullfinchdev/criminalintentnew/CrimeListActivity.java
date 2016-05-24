package ru.bullfinchdev.criminalintentnew;

import android.support.v4.app.Fragment;

/**
 * Created by alexander on 24.05.2016.
 */
public class CrimeListActivity extends AbstractFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
