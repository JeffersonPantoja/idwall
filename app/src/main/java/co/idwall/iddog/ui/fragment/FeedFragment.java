package co.idwall.iddog.ui.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import co.idwall.iddog.R;
import co.idwall.iddog.api.client.FeedProcessado;
import co.idwall.iddog.controllers.FeedController;
import co.idwall.iddog.ui.activity.DogExpandidoActivity;
import co.idwall.iddog.ui.adapter.ListaFeedAdapter;
import co.idwall.iddog.ui.listener.OnItemClickListener;
import co.idwall.iddog.util.ActivityUtil;
import co.idwall.iddog.util.DialogUtil;

import static android.content.Context.MODE_PRIVATE;
import static co.idwall.iddog.Constantes.ERRO_DE_TRANSMISSÃO;
import static co.idwall.iddog.Constantes.PREFERENCIAS_DO_USUARIO;
import static co.idwall.iddog.Constantes.TOKEN;
import static co.idwall.iddog.Constantes.URL_DOG;

public class FeedFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener{

    private static final String CATEGORIA = "CATEGORIA";
    private View view;
    public ProgressBar progressBar;
    public SwipeRefreshLayout swipeToRefresh;
    public ImageView swipeInfo;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        inicializaViews();
        buscaFeed();
        return view;
    }

    private void inicializaViews() {
        progressBar = view.findViewById(R.id.feed_progress);
        swipeInfo = view.findViewById(R.id.feed_swipe_info);
        swipeToRefresh = view.findViewById(R.id.feed_swipe);
        swipeToRefresh.setOnRefreshListener(this);
    }

    public void buscaFeed() {
        SharedPreferences preferences = getContext().getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        if (preferences.contains(TOKEN)) {
            String token = preferences.getString(TOKEN, null);
            String categoria = getArguments().getString(CATEGORIA);
            new FeedController().buscarFeed(token, categoria, processaFeed());
        }
    }

    @NonNull
    private FeedProcessado processaFeed() {
        return new FeedProcessado() {
            @Override
            public void sucesso(List<String> urlsDog) {
                configuraRecycleView(urlsDog);
            }

            @Override
            public void falha(String mensagemErro) {
                if(mensagemErro != null){
                    ActivityUtil.negaAcesso(getContext(), mensagemErro);
                }else {
                    new DialogUtil(getContext()).exibirMensagemErro(ERRO_DE_TRANSMISSÃO);
                    configuraRecycleView(new ArrayList<String>());
                }
            }
        };
    }



    public void configuraRecycleView(List<String> urlsDog) {
        RecyclerView listaFeed = view.findViewById(R.id.feed_lista);
        configuraAdapter(urlsDog, listaFeed);
        configuraViews(urlsDog);

    }

    private void configuraViews(List<String> urlsDog) {
        progressBar.setVisibility(View.INVISIBLE);
        swipeToRefresh.setRefreshing(false);
        if(!urlsDog.isEmpty())
            swipeInfo.setVisibility(View.INVISIBLE);
        else
            swipeInfo.setVisibility(View.VISIBLE);
    }

    private void configuraAdapter(List<String> listaUrlsDog, RecyclerView listaFeed) {
        ListaFeedAdapter adapter = new ListaFeedAdapter(getContext(), listaUrlsDog);
        adapter.setOnItemClickListener(exibiDogExpandido());
        listaFeed.setAdapter(adapter);
    }

    @NonNull
    private OnItemClickListener exibiDogExpandido() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(String urldog) {
                Intent vaiParaExibeDogExpandido = new Intent(getContext(), DogExpandidoActivity.class);
                vaiParaExibeDogExpandido.putExtra(URL_DOG,urldog);
                startActivity(vaiParaExibeDogExpandido);
            }
        };
    }

    @Override
    public void onRefresh() {
        buscaFeed();
    }
}
