package co.idwall.iddog.ui.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.idwall.iddog.R;
import co.idwall.iddog.controllers.FeedController;

import static android.content.Context.MODE_PRIVATE;
import static co.idwall.iddog.ui.activity.ConstantesActivity.PREFERENCIAS_DO_USUARIO;
import static co.idwall.iddog.ui.activity.ConstantesActivity.TAB_TITLES;
import static co.idwall.iddog.ui.activity.ConstantesActivity.TOKEN;

public class FeedFragment extends Fragment {

    public static final String PAGINA = "ARG_PAGE";
    private ViewGroup container;


    public static FeedFragment newInstance(int pagina) {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        this.container = container;

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        buscaFeed((ViewPager) container);
    }

    private void buscaFeed(ViewPager container) {
        ViewPager tabLayout = container;
        int numeroPagina = tabLayout.getCurrentItem();

        SharedPreferences preferences = getContext().getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        if(preferences.contains(TOKEN)){
            String token = preferences.getString(TOKEN,null);
            new FeedController(getContext()).buscarFeed(token, TAB_TITLES[numeroPagina]);
        }
    }
}
