package test.fandb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import test.fandb.FoodPagerFragment;

/**
 * Created by yellamobile-android on 19/01/18.
 */

public class PagerAdapter extends FragmentPagerAdapter
{

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FoodPagerFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
