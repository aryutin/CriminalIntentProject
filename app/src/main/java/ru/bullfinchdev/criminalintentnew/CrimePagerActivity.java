package ru.bullfinchdev.criminalintentnew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends FragmentActivity {
    private static final String EXTRA_CRIME_ID = "ru.bullfinchdev.criminalintentnew.crime_id";

    private ViewPager viewPager;
    private List<Crime> crimeList;

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        viewPager = (ViewPager) findViewById(R.id.viewPager_crimes);
        crimeList = CrimeLab.get(this).getCrimeList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = crimeList.get(position);
                return CrimeEditFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return crimeList.size();
            }
        });
        setCrime(crimeId);

    }
    private void setCrime(UUID cId){
        for (int i=0; i<crimeList.size(); i++){
            if (crimeList.get(i).getId().equals(cId)){
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
