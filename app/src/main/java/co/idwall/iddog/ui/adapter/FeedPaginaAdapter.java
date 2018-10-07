package co.idwall.iddog.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import co.idwall.iddog.ui.fragment.FeedFragment;

public class FeedPaginaAdapter extends FragmentPagerAdapter {
    final int NUMERO_PAGINAS = 4;
    private String tabTitles[] = new String[] { "husky", "hound", "pug","labrador"};

    public FeedPaginaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FeedFragment.newInstance(tabTitles[position]);
    }

    @Override
    public int getCount() {
        return NUMERO_PAGINAS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
