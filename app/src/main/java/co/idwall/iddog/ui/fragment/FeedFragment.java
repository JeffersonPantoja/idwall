package co.idwall.iddog.ui.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.idwall.iddog.R;
import co.idwall.iddog.controllers.FeedController;
import co.idwall.iddog.model.FeedCategoria;
import co.idwall.iddog.ui.adapter.ListaFeedAdapter;

import static android.content.Context.MODE_PRIVATE;
import static co.idwall.iddog.ui.Constantes.PREFERENCIAS_DO_USUARIO;
import static co.idwall.iddog.ui.Constantes.TOKEN;

public class FeedFragment extends Fragment {

    public static final String CATEGORIA = "CATEGORIA";
    private View view;

    public static FeedFragment newInstance(String categoria) {
        FeedFragment fragment = new FeedFragment();
        Bundle argumentos = new Bundle();
        argumentos.putString(CATEGORIA, categoria);
        fragment.setArguments(argumentos);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        buscaFeed();
    }

    private void buscaFeed() {
        SharedPreferences preferences = getContext().getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        if(preferences.contains(TOKEN)){
            String token = preferences.getString(TOKEN,null);
            String categoria = getArguments().getString(CATEGORIA);
            new FeedController(getContext(), this).buscarFeed(token, categoria);
        }
    }


    public void configuraRecycleView(FeedCategoria feedCategoria) {
        RecyclerView listaFeed = view.findViewById(R.id.feed_lista);
        configuraAdapter(feedCategoria.getListaUrlsDog(), listaFeed);
    }

    private void configuraAdapter(List<String> listaUrlsDog, RecyclerView listaFeed) {
        ListaFeedAdapter adapter = new ListaFeedAdapter(getContext(), listaUrlsDog);
        listaFeed.setAdapter(adapter);
    }
}
