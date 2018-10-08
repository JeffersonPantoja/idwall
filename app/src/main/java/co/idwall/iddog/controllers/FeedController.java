package co.idwall.iddog.controllers;

import android.content.Context;

import co.idwall.iddog.R;
import co.idwall.iddog.model.FeedCategoria;
import co.idwall.iddog.services.RetrofitInicializador;
import co.idwall.iddog.ui.activity.FeedActivity;
import co.idwall.iddog.ui.fragment.FeedFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedController {

    private final Context context;
    private FeedFragment feed;

    public FeedController(Context context, FeedFragment feed) {
        this.context = context;
        this.feed = feed;
    }

    public void buscarFeed(String token, String categoria){
        Call<FeedCategoria> call = new RetrofitInicializador(context).getDogSerice().feed(token, categoria);
        call.enqueue(new Callback<FeedCategoria>() {
            @Override
            public void onResponse(Call<FeedCategoria> call, Response<FeedCategoria> response) {
                trataRespostaApiDog(response);
            }

            @Override
            public void onFailure(Call<FeedCategoria> call, Throwable t) {
                exibemensagemErro();
            }
        });
    }

    private void trataRespostaApiDog(Response<FeedCategoria> response) {
        FeedCategoria feedCategoria = response.body();
        if(feedCategoria != null){
            feed.configuraRecycleView(feedCategoria);
        }else{
            exibemensagemErro();
        }
    }

    private void exibemensagemErro() {
        FeedActivity feedActivity = (FeedActivity) context;
        feedActivity.exibirMensagemErro(context.getResources().getString(R.string.controller_erro_transmissao));
    }

}
