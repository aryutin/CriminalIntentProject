package ru.bullfinchdev.criminalintentnew;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public class CrimeEditActivity extends AbstractFragmentActivity{

    private static final String EXTRA_CRIME_ID = "ru.bullfinchdev.criminalintentnew.crime_id";

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeEditFragment.newInstance(id);
    }

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context, CrimeEditActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

}
