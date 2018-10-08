package co.idwall.iddog.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import co.idwall.iddog.ui.fragment.FeedFragment;

import static co.idwall.iddog.ui.Constantes.TAB_TITLES;

public class FeedPaginaAdapter extends FragmentPagerAdapter {

    public FeedPaginaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FeedFragment.newInstance(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }
}
