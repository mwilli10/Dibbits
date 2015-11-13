package com.example.kj.dibbits;

/**
 * Created by KJ on 11/12/15.
 */

import android.content.Context;
        import android.content.Intent;
        import android.support.v4.app.Fragment;

import java.util.UUID;

public class DibbitActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.example.kj.criminalintent.crime_id";

    @Override
    protected Fragment createFragment() {

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return DibbitFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, DibbitActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

}