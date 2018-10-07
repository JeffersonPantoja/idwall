package co.idwall.iddog.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import co.idwall.iddog.ui.fragment.FeedFragment;

import static co.idwall.iddog.ui.activity.ConstantesActivity.TAB_TITLES;

public class FeedPaginaAdapter extends FragmentPagerAdapter {
    final int NUMERO_PAGINAS = 4;
    public FeedPaginaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FeedFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return NUMERO_PAGINAS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }
}
