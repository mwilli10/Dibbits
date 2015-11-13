package com.example.kj.dibbits;

import android.support.v4.app.Fragment;

/**
 * Created by KJ on 10/8/15.
 */
public class DibbitListActivity extends SingleFragmentActivity {

    protected Fragment createFragment(){
        return new DibbitListFragment();

    }
}